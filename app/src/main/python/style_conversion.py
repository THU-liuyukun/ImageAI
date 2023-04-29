# 图像风格转换
from utils.baidu import *

api_key = "dn0oP2SR2l1TSjSdYm7NOm6B"
secret_key = "E9DzPHG5mATOuukUUohI6mmK0RLRrrCQ"

def style_con(img_base64, option):
    request_url = "https://aip.baidubce.com/rest/2.0/image-process/v1/style_trans"

    access_token = get_token(api_key, secret_key)
    request_url = request_url + "?access_token=" + access_token

    params = {
        "image": img_base64,
        "option": option
    }

    headers = {
        'content-type': 'application/x-www-form-urlencoded',
        'Accept': 'application/json'
    }

    response = requests.post(request_url, data=params, headers=headers)

    if response:
        return response.json()['image']

def main(img_base64, style):
    options = {
        '卡通画风格': 'cartoon',
        '铅笔画风格': 'pencil',
        '彩色铅笔画风格': 'color_pencil',
        '彩色糖块油画风格': 'warm',
        '神奈川冲浪里油画风格': 'wave',
        '薰衣草油画风格': 'lavender',
        '奇异油画风格': 'mononoke',
        '呐喊油画风格': 'scream',
        '哥特油画风格': 'gothic'
    }
    option = options[style]
    return style_con(img_base64, option)