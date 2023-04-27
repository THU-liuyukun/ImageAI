import base64


def img_to_base64(img_path):
    with open(img_path, "rb") as f:
        base64_data = base64.b64encode(f.read()).decode("utf8")
    return base64_data

def base64_to_img(img_base64):
    img = base64.b64decode(img_base64)
    return img
