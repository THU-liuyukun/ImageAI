import base64
import urllib


def img_to_base64(img_path, urlencoded=False):
    """
    获取文件base64编码
    :param img_path: 文件路径
    :param urlencoded: 是否对结果进行urlencoded
    :return: base64编码信息
    """
    with open(img_path, "rb") as f:
        base64_data = base64.b64encode(f.read()).decode("utf8")
        if urlencoded:
            base64_data = urllib.parse.quote_plus(base64_data)
    return base64_data

def base64_to_img(img_base64):
    img = base64.b64decode(img_base64)
    return img
