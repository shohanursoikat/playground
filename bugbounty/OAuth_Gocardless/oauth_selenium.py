import time
from urllib.parse import urlparse, parse_qs

from selenium import webdriver
from selenium.webdriver.chrome.service import Service
from selenium.webdriver.chrome.options import Options
from webdriver_manager.chrome import ChromeDriverManager


# ---------------- CONFIG ----------------
BASE = "https://connect-sandbox.gocardless.com"
CLIENT_ID = "HObcHCa6I9A6qLEK9Dp0sC90orA_cHN7eeLuJGGvHw4zhoJVs5sQ6LmjrLnXSd6x"
REDIRECT_URI = "https://webhook.site/cda54e31-a1c7-4103-8f92-c4d73d4a9c51"

AUTH_URL = (
    f"{BASE}/oauth/authorize?"
    f"scope=read_write&"
    f"client_id={CLIENT_ID}&"
    f"redirect_uri={REDIRECT_URI}&"
    f"response_type=code&"
    f"state=research"
)


# ---------------- DRIVER SETUP ----------------
def create_driver():
    chrome_options = Options()

    # Use your real Chrome profile so you're already logged in
    chrome_options.add_argument(
        r"--user-data-dir=C:\Users\hp\AppData\Local\Google\Chrome\User Data"
    )
    chrome_options.add_argument("--profile-directory=Default")

    chrome_options.add_argument("--start-maximized")

    service = Service(ChromeDriverManager().install())
    driver = webdriver.Chrome(service=service, options=chrome_options)

    return driver


# ---------------- EXTRACT CODE ----------------
def extract_code_from_url(url):
    parsed = urlparse(url)
    query = parse_qs(parsed.query)
    return query.get("code", [None])[0]


# ---------------- MAIN FLOW ----------------
def run_oauth():
    driver = create_driver()

    print("[+] Opening OAuth URL...")
    driver.get(AUTH_URL)

    print("[+] Waiting for redirect...")

    timeout = 60  # seconds
    start_time = time.time()

    while time.time() - start_time < timeout:
        current_url = driver.current_url

        if "code=" in current_url:
            print("[+] Redirect detected!")
            code = extract_code_from_url(current_url)
            print("[+] Authorization Code:", code)
            driver.quit()
            return

        time.sleep(1)

    print("[-] Timeout reached. No code found.")
    driver.quit()


if __name__ == "__main__":
    run_oauth()
