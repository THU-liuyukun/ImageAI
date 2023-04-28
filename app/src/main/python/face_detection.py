# 人脸检测
import math
from utils.img_and_base64 import *
from utils.baidu import *
import numpy as np

api_key = "eKYsErw8YTG84q5QWlATb3Sm"
secret_key = "5hvA5tpYImo1OvHGWh9grAAx9UHKND2q"

def face_detect(img_base64):
    request_url = "https://aip.baidubce.com/rest/2.0/face/v3/detect"

    access_token = get_token(api_key, secret_key)
    request_url = request_url + "?access_token=" + access_token

    params = {
        "image": img_base64,
        "image_type": "BASE64",
        "max_face_num": "120"
    }

    headers = {
        'content-type': 'application/json'
    }

    response = requests.post(request_url, data=params, headers=headers)

    if response:
        return response.json()


def main(img_base64):

    result = face_detect(img_base64)['result']
    face_num = result['face_num']
    face_list = result['face_list']

    # 将 base64 字符串解码为二进制数据
    img_data = base64.b64decode(img_base64)
    # 将二进制数据转换为 NumPy 数组
    np_array = np.frombuffer(img_data, np.uint8)
    # 读取图像
    img = cv2.imdecode(np_array, cv2.IMREAD_COLOR)

    for i in range(face_num):
        location = face_list[i]['location']

        # rotation是以左上为圆心旋转
        Theta = location['rotation'] / 60
        # A是左上
        A = (int(location['left']), int(location['top']))

        # B是右上
        B = (int(location['left']) + int(location['width'] * math.cos(Theta)),
             int(location['top']) + int(location['width'] * math.sin(Theta)))

        AC_Len = math.sqrt(location['width'] ** 2 + location['height'] ** 2)
        AC_Theta = math.atan(location['height'] / location['width']) + location['rotation'] / 60

        # C是右下
        C = (int(location['left']) + int(AC_Len * math.cos(AC_Theta)),
             int(location['top']) + int(AC_Len * math.sin(AC_Theta)))

        # D是左下
        D = (int(location['left']) - int(location['height'] * math.sin(Theta)),
             int(location['top']) + int(location['height'] * math.cos(Theta)))

        cv2.line(img, A, B, (0, 255, 0), 2)
        cv2.line(img, B, C, (0, 255, 0), 2)
        cv2.line(img, C, D, (0, 255, 0), 2)
        cv2.line(img, D, A, (0, 255, 0), 2)

    return cv2_to_base64(img)
