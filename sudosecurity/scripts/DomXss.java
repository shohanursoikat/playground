package burp;

import burp.api.montoya.MontoyaApi;
import burp.api.montoya.extension.Extension;
import burp.api.montoya.http.handler.RequestToBeSent;
import burp.api.montoya.http.handler.RequestToBeSentAction;
import burp.api.montoya.http.handler.ResponseReceived;
import burp.api.montoya.http.handler.ResponseReceivedAction;
import burp.api.montoya.http.message.HttpRequest;
import burp.api.montoya.http.message.HttpResponse;
import burp.api.montoya.logging.Logging;
import burp.api.montoya.scanner.audit.issues.AuditIssue;
import burp.api.montoya.scanner.audit.issues.AuditIssueSeverity;
import burp.api.montoya.scanner.audit.issues.AuditIssueType;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.nio.charset.StandardCharsets;

/**
 * DOM-XSS Analyzer v2 (Montoya)
 *
 * Features:
 *  - Source + sink detection
 *  - Lightweight taint-tracking (variable propagation within a single JS resource)
 *  - Source map detection and optional fetch (best-effort)
 *  - Creates Burp issues and optionally opens Repeater
 *
 * Notes:
 *  - Extend parseTaintPropagation to integrate AST-based analysis later (esprima/graal)
 *  - Adjust constants / patterns to tune sensitivity
 */
public class DomXss implements Extension
{
    private MontoyaApi api;
    private Logging logging;

    // ====== Config ======
    private static final boolean ENABLE_SOURCEMAP_FETCH = true;
    private static final boolean AUTO_OPEN_REPEATER_ON_FIND = true;
    private static final int MAX_LINES_TO_SCAN = 10000; // avoid crazy huge responses

    // Source regex patterns (strings we consider attacker-controlled)
    private static final Pattern[] SOURCE_PATTERNS = new Pattern[] {
            Pattern.compile("\\blocation\\.hash\\b"),
            Pattern.compile("\\blocation\\.search\\b"),
            Pattern.compile("\\blocation\\.href\\b"),
            Pattern.compile("\\bdocument\\.URL\\b"),
            Pattern.compile("\\bdocument\\.documentURI\\b"),
            Pattern.compile("\\bdocument\\.referrer\\b"),
            Pattern.compile("\\blocalStorage\\.getItem\\s*\\("),
            Pattern.compile("\\bsessionStorage\\.getItem\\s*\\("),
            Pattern.compile("\\bwindow\\.postMessage\\s*\\(")
    };

    // Sink patterns (dangerous sinks)
    private static final Pattern[] SINK_PATTERNS = new Pattern[] {
            Pattern.compile("\\.innerHTML\\b"),
            Pattern.compile("\\.outerHTML\\b"),
            Pattern.compile("document\\.write\\s*\\("),
            Pattern.compile("document\\.writeln\\s*\\("),
            Pattern.compile("\\beval\\s*\\("),
            Pattern.compile("\\bFunction\\s*\\("),
            Pattern.compile("\\bsetTimeout\\s*\\("),
            Pattern.compile("\\bsetInterval\\s*\\("),
            Pattern.compile("\\.html\\s*\\("),
            Pattern.compile("\\.append\\s*\\("),
            Pattern.compile("\\.after\\s*\\("),
            Pattern.compile("\\.before\\s*\\(")
    };

    // Basic assignment and var capture patterns
    private static final Pattern VAR_ASSIGN = Pattern.compile(
            "\\b(?:var|let|const)\\s+([A-Za-z_$][A-Za-z0-9_$]*)\\s*=\\s*([^;\\n]+);?"
    );

    private static final Pattern SIMPLE_ASSIGN = Pattern.compile(
            "([A-Za-z_$][A-Za-z0-9_$]*)\\s*=\\s*([^;\\n]+);?"
    );

    // capture sourceMappingURL comment
    private static final Pattern SOURCE_MAP_PATTERN = Pattern.compile(
            "sourceMappingURL\\s*=\\s*([^\\s\\*;]+)"
    );

    @Override
    public void initialize(MontoyaApi montoyaApi)
    {
        this.api = montoyaApi;
        this.logging = api.logging();

        api.extension().setName("DOM-XSS");

        logging.output("DOM-XSS");
        logging.output(" - Lightweight taint-tracking enabled");
        logging.output(" - Sourcemap fetch: " + (ENABLE_SOURCEMAP_FETCH ? "ON" : "OFF"));

        // Register HTTP handler to inspect responses
        api.http().registerHttpHandler(new burp.api.montoya.http.handler.HttpHandler() {
            @Override
            public RequestToBeSentAction handleRequestToBeSent(RequestToBeSent request) {
                return RequestToBeSentAction.continueWith(request);
            }

            @Override
            public ResponseReceivedAction handleResponseReceived(ResponseReceived responseReceived) {
                try {
                    handleResponse(responseReceived);
                } catch (Exception ex) {
                    logging.error("DOM-XSS Analyzer error: " + ex.getMessage());
                }
                return ResponseReceivedAction.continueWith(responseReceived);
            }
        });
    }

    private void handleResponse(ResponseReceived responseReceived) {
        HttpResponse response = responseReceived.response();
        String contentType = response.headers().contentType();
        String url = responseReceived.initiatingRequest().url().toString();

        if (contentType == null) {
            return;
        }

        // Only scan JS and inline script content types (text/javascript, application/javascript)
        if (!contentType.contains("javascript") && !contentType.contains("ecmascript")) {
            return;
        }

        // Decode body
        String body = response.bodyToString();
        if (body == null || body.isEmpty()) return;

        String[] lines = body.split("\n");
        if (lines.length > MAX_LINES_TO_SCAN) {
            logging.output("Skipping huge JS file (> " + MAX_LINES_TO_SCAN + " lines) : " + url);
            return;
        }

        // 1) quick detection: any sources or sinks at all?
        boolean hasSource = false;
        boolean hasSink = false;
        for (String line : lines) {
            for (Pattern p : SOURCE_PATTERNS) {
                if (p.matcher(line).find()) { hasSource = true; break; }
            }
            for (Pattern p : SINK_PATTERNS) {
                if (p.matcher(line).find()) { hasSink = true; break; }
            }
            if (hasSource && hasSink) break;
        }

        if (!hasSource && !hasSink) {
            // nothing obvious
            return;
        }

        // 2) build basic taint map by scanning assignments
        Map<String, TaintInfo> taintMap = new HashMap<>(); // var -> taint info
        List<SourceHit> sourceHits = new ArrayList<>();
        List<SinkHit> sinkHits = new ArrayList<>();

        // first pass: record direct occurrences of source expressions and mark assigned variables
        for (int i = 0; i < lines.length; i++) {
            String raw = lines[i];
            String line = raw.trim();

            // direct source occurrences
            for (Pattern sp : SOURCE_PATTERNS) {
                Matcher m = sp.matcher(line);
                if (m.find()) {
                    sourceHits.add(new SourceHit(i + 1, line, m.group(0)));
                }
            }

            // var declarations like: var x = location.search;
            Matcher varM = VAR_ASSIGN.matcher(line);
            while (varM.find()) {
                String varName = varM.group(1);
                String rhs = varM.group(2);
                boolean rhsIsSource = matchesAnyPattern(rhs, SOURCE_PATTERNS);
                if (rhsIsSource) {
                    taintMap.put(varName, new TaintInfo(varName, i + 1, "assigned-from-source", rhs.trim()));
                } else {
                    // sometimes RHS contains previously tainted vars or concatenations
                    // we'll process in propagation stage
                }
            }

            // simple assignment: foo = bar;
            Matcher simpleM = SIMPLE_ASSIGN.matcher(line);
            while (simpleM.find()) {
                String left = simpleM.group(1);
                String right = simpleM.group(2);
                boolean rightHasSource = matchesAnyPattern(right, SOURCE_PATTERNS);
                if (rightHasSource && !taintMap.containsKey(left)) {
                    taintMap.put(left, new TaintInfo(left, i + 1, "assigned-from-source", right.trim()));
                }
            }
        }

        // 3) propagate taint for simple flows (x = y; z = x + "something"; foo.innerHTML = z)
        // do a few passes to reach stable fixed-point
        boolean changed = true;
        int passes = 0;
        while (changed && passes < 10) {
            changed = false;
            passes++;
            for (int i = 0; i < lines.length; i++) {
                String line = lines[i].trim();

                // match assignments
                Matcher assignM = SIMPLE_ASSIGN.matcher(line);
                while (assignM.find()) {
                    String left = assignM.group(1);
                    String right = assignM.group(2);

                    // if RHS contains a tainted variable
                    for (String var : new HashSet<>(taintMap.keySet())) {
                        Pattern varPat = Pattern.compile("\\b" + Pattern.quote(var) + "\\b");
                        if (varPat.matcher(right).find()) {
                            if (!taintMap.containsKey(left)) {
                                taintMap.put(left, new TaintInfo(left, i + 1, "propagated-from-" + var, right.trim()));
                                changed = true;
                            }
                        }
                    }

                    // if RHS is concatenation that includes a source pattern string literal etc.
                    if (matchesAnyPattern(right, SOURCE_PATTERNS)) {
                        if (!taintMap.containsKey(left)) {
                            taintMap.put(left, new TaintInfo(left, i + 1, "assigned-from-source", right.trim()));
                            changed = true;
                        }
                    }
                }
            }
        }

        // 4) search sinks and check if they involve tainted vars or direct sources
        for (int i = 0; i < lines.length; i++) {
            String line = lines[i];
            for (Pattern sink : SINK_PATTERNS) {
                Matcher m = sink.matcher(line);
                if (m.find()) {
                    // is any tainted var present in this line?
                    List<TaintInfo> usedTaints = new ArrayList<>();
                    for (Map.Entry<String, TaintInfo> e : taintMap.entrySet()) {
                        String var = e.getKey();
                        Pattern varPat = Pattern.compile("\\b" + Pattern.quote(var) + "\\b");
                        if (varPat.matcher(line).find()) {
                            usedTaints.add(e.getValue());
                        }
                    }

                    // also directly if sink contains a SOURCE (e.g., element.innerHTML = location.hash)
                    boolean directSource = matchesAnyPattern(line, SOURCE_PATTERNS);

                    sinkHits.add(new SinkHit(i + 1, line.trim(), m.group(0), usedTaints, directSource));
                }
            }
        }

        // 5) If we have any sinkHits with taints or directSource -> report issue
        boolean report = false;
        StringBuilder detailBuilder = new StringBuilder();
        if (!sourceHits.isEmpty()) {
            detailBuilder.append("<b>Detected Sources:</b><br>");
            for (SourceHit s : sourceHits) {
                detailBuilder.append("Line ").append(s.lineNumber).append(": ").append(escapeHtml(s.code)).append("<br>");
            }
            detailBuilder.append("<br>");
        }

        if (!taintMap.isEmpty()) {
            detailBuilder.append("<b>Tainted Variables (propagation trace):</b><br>");
            for (TaintInfo t : taintMap.values()) {
                detailBuilder.append(t.toHtml()).append("<br>");
            }
            detailBuilder.append("<br>");
        }

        if (!sinkHits.isEmpty()) {
            detailBuilder.append("<b>Sink Hits:</b><br>");
            for (SinkHit sh : sinkHits) {
                if (!sh.taintedVariables.isEmpty() || sh.directSource) {
                    report = true;
                    detailBuilder.append("Line ").append(sh.lineNumber).append(": ").append(escapeHtml(sh.code)).append("<br>");
                    if (sh.directSource) detailBuilder.append("<i> - direct source used in sink</i><br>");
                    if (!sh.taintedVariables.isEmpty()) {
                        detailBuilder.append("<i> - tainted vars:</i><br>");
                        for (TaintInfo tv : sh.taintedVariables) {
                            detailBuilder.append("&nbsp;&nbsp;• ").append(tv.toHtml()).append("<br>");
                        }
                    }
                    detailBuilder.append("<br>");
                }
            }
        }

        // check for sourceMappingURL comment
        Matcher smm = SOURCE_MAP_PATTERN.matcher(body);
        List<String> sourceMapUrls = new ArrayList<>();
        if (smm.find()) {
            String smUrl = smm.group(1).trim();
            sourceMapUrls.add(smUrl);
            detailBuilder.append("<b>SourceMap detected:</b> ").append(escapeHtml(smUrl)).append("<br><br>");
            if (ENABLE_SOURCEMAP_FETCH) {
                // attempt to fetch the source map (best-effort)
                try {
                    // Use Montoya API to issue a GET (note: API method names might vary between SDK releases)
                    // We will build a request and ask Montoya to send it synchronously
                    burp.api.montoya.http.HttpRequest request = api.utilities().httpRequest()
                            .buildGet(smUrl)
                            .withHeader("User-Agent", "DOM-XSS-Analyzer-v2")
                            .build();

                    burp.api.montoya.http.HttpResponse smResp = api.http().sendRequestAndWait(request);
                    if (smResp != null && smResp.statusCode() == 200) {
                        String smBody = smResp.bodyToString();
                        detailBuilder.append("<b>SourceMap fetched (length):</b> ").append(smBody.length()).append("<br>");
                        // naive attempt: find 'sourcesContent' in the map and scan it (very helpful when available)
                        if (smBody.contains("\"sourcesContent\"")) {
                            // quick parse - not a full JSON parse to avoid extra dependency here
                            int idx = smBody.indexOf("\"sourcesContent\"");
                            detailBuilder.append("<i>sourcesContent found in sourcemap.</i><br>");
                            // NOTE: for robust parsing add a JSON library like Gson and parse sourcesContent array
                        } else {
                            detailBuilder.append("<i>No inline sourcesContent found — consider fetching source files referenced by the map.</i><br>");
                        }
                    } else {
                        detailBuilder.append("<i>Could not fetch sourceMap (HTTP ").append(smResp != null ? smResp.statusCode() : -1).append(")</i><br>");
                    }
                } catch (Exception ex) {
                    detailBuilder.append("<i>Source map fetch failed: ").append(escapeHtml(ex.getMessage())).append("</i><br>");
                }
            }
        }

        if (report) {
            String detail = detailBuilder.toString();

            AuditIssue issue = AuditIssue.auditIssue(
                    "Potential DOM-Based XSS (v2 analyzer)",
                    detail,
                    responseReceived.receivedFromTool(),
                    responseReceived.initiatingRequest().url(),
                    AuditIssueSeverity.HIGH,
                    AuditIssueType.DOM_BASED_XSS
            );

            // attach issue to the response
            responseReceived.addAuditIssue(issue);

            logging.output("⚠️ DOM-XSS flow detected -> " + url);

            // Optionally push the initiating request to Repeater for quick manual PoC work
            if (AUTO_OPEN_REPEATER_ON_FIND) {
                try {
                    HttpRequest req = responseReceived.initiatingRequest();
                    api.tool().openRepeaterTab(req); // NOTE: method name may differ per Montoya API version
                    logging.out
                    put("Opened Repeater for: " + url);
                } catch (Exception ex) {
                    logging.output("Could not open Repeater automatically: " + ex.getMessage());
                }
            }
        }
    }

    // Utility: check if text matches any pattern in array
    private boolean matchesAnyPattern(String text, Pattern[] arr) {
        if (text == null) return false;
        for (Pattern p : arr) {
            if (p.matcher(text).find()) return true;
        }
        return false;
    }

    private String escapeHtml(String s) {
        if (s == null) return "";
        return s.replace("&", "&amp;").replace("<", "&lt;").replace(">", "&gt;");
    }

    // ====== Helper data classes ======
    private static class TaintInfo {
        final String varName;
        final int lineNumber;
        final String reason; // assigned-from-source or propagated-from-X
        final String snippet;

        TaintInfo(String varName, int lineNumber, String reason, String snippet) {
            this.varName = varName;
            this.lineNumber = lineNumber;
            this.reason = reason;
            this.snippet = snippet;
        }

        String toHtml() {
            return String.format("var <b>%s</b> (line %d) - %s - <code>%s</code>", escape(varName), lineNumber, escape(reason), escape(snippet));
        }

        private static String escape(String t) {
            return t.replace("&", "&amp;").replace("<","&lt;").replace(">","&gt;");
        }
    }

    private static class SourceHit {
        final int lineNumber;
        final String code;
        final String matched;
        SourceHit(int l, String c, String m) { lineNumber = l; code = c; matched = m; }
    }

    private static class SinkHit {
        final int lineNumber;
        final String code;
        final String matched;
        final List<TaintInfo> taintedVariables;
        final boolean directSource;
        SinkHit(int l, String c, String m, List<TaintInfo> t, boolean d) {
            lineNumber = l; code = c; matched = m; taintedVariables = t; directSource = d;
        }
    }
}
