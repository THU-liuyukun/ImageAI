package com.example.baiduai.utils;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.util.Base64;
import android.widget.ImageView;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class ImagePickerUtils {
    private static final int REQUEST_CODE_PICK_IMAGE = 1;

    // 打开系统相册，选择一张图片，并将其显示在传入的ImageView中
    public static void pickImage(Activity activity, ImageView imageView) {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        activity.startActivityForResult(intent, REQUEST_CODE_PICK_IMAGE);
        setImageView(imageView, null);
    }

    private static void setImageView(ImageView imageView, Bitmap bitmap) {
        if (bitmap != null) {
            imageView.setImageBitmap(bitmap);
        }
    }

    // 将选择的图片转换为Base64，返回值为base64。如果选择的图片无法转换或未选择图片，则返回null。
    public static String handleActivityResult(Activity activity, int requestCode, int resultCode, Intent data, ImageView imageView) {
        if (requestCode == REQUEST_CODE_PICK_IMAGE && resultCode == Activity.RESULT_OK && data != null) {
            Uri selectedImage = data.getData();
            if (selectedImage != null) {
                try {
                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(activity.getContentResolver(), selectedImage);
                    setImageView(imageView, bitmap);
                    return convertBitmapToBase64(bitmap);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    private static String convertBitmapToBase64(Bitmap bitmap) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        // 判断图片格式并指定压缩格式
        Bitmap.CompressFormat compressFormat = bitmap.hasAlpha() ? Bitmap.CompressFormat.PNG : Bitmap.CompressFormat.JPEG;
        bitmap.compress(compressFormat, 100, byteArrayOutputStream);
        byte[] byteArray = byteArrayOutputStream.toByteArray();
        return Base64.encodeToString(byteArray, Base64.DEFAULT);
    }

}
