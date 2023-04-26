# 图像对比度增强
from utils.img_and_base64 import *
from utils.operate_img import *
from utils.baidu import *

api_key = "dn0oP2SR2l1TSjSdYm7NOm6B"
secret_key = "E9DzPHG5mATOuukUUohI6mmK0RLRrrCQ"

def def_enhance(img_base64):
    request_url = "https://aip.baidubce.com/rest/2.0/image-process/v1/image_definition_enhance"

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

def main(img_path, save_img_path):
    img_base64_input = img_to_base64(img_path)
    img_base64_ouput = def_enhance(img_base64_input)
    img = base64_to_img(img_base64_ouput)
    save_img(save_img_path, img)

if __name__ == '__main__':
    main("before_imgs/exo.jpg", "after_imgs/exo2.jpg")