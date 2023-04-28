# 图像去雾
from utils.baidu import *

api_key = "dn0oP2SR2l1TSjSdYm7NOm6B"
secret_key = "E9DzPHG5mATOuukUUohI6mmK0RLRrrCQ"

def img_dehazing(img_base64):
    request_url = "https://aip.baidubce.com/rest/2.0/image-process/v1/dehaze"

    access_token = get_token(api_key, secret_key)
    request_url = request_url + "?access_token=" + access_token

    params = {
        "image": img_base64
    }

    headers = {
        'content-type': 'application/x-www-form-urlencoded',
        'Accept': 'application/json'
    }

    response = requests.post(request_url, data=params, headers=headers)

    if response:
        return response.json()['image']

def main(img_base64):
    return img_dehazing(img_base64)