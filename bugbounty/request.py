import requests

headers = {
    "Authorization": "Bearer sandbox_QpsrQFihCetuy8ebANHC4ZoMxjV16cXGd526OFrA",
    "GoCardless-Version": "2015-07-06",
    "Content-Type": "application/json",
}

for i in range(5):
    r = requests.get(
        "https://api-sandbox.gocardless.com/customers",
        headers=headers
    )
    print(r.status_code)
