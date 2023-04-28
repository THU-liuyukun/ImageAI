import cv2
import base64

def cv2_to_base64(img):

    # 将图片转换为JPEG格式的字节流
    _, img_encoded = cv2.imencode('.jpg', img)

    # 将字节流转换为base64编码
    img_base64 = base64.b64encode(img_encoded).decode('utf-8')
    return img_base64

