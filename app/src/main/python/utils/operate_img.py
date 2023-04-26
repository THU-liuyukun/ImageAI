def save_img(path, img):
    # path：包含文件名及拓展名
    with open(path, 'wb') as f:
        f.write(img)