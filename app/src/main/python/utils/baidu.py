import requests

def get_token(api_key, secret_key):
    url1 = "https://aip.baidubce.com/oauth/2.0/token?grant_type=client_credentials&client_id="
    url2 = "&client_secret="
    url = url1 + api_key + url2 + secret_key

    payload = ""
    headers = {
        'Content-Type': 'application/json',
        'Accept': 'application/json'
    }

    response = requests.request("POST", url, headers=headers, data=payload)

    return response.json()['access_token']

if __name__ == '__main__':
    ak = "eKYsErw8YTG84q5QWlATb3Sm"
    sk = "5hvA5tpYImo1OvHGWh9grAAx9UHKND2q"
    print(get_token(ak, sk))