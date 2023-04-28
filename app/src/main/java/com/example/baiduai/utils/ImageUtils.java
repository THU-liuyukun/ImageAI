package com.example.baiduai.utils;

import android.content.ContentValues;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.util.Base64;
import android.view.View;
import android.widget.ImageView;

import com.google.android.material.snackbar.Snackbar;

import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class ImageUtils {

    //将Base64格式的字符串转换成Bitmap
    public static Bitmap base64ToBitmap(String base64String) {
        byte[] decodedString = Base64.decode(base64String, Base64.DEFAULT);
        return BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
    }

    //将Bitmap显示在ImageView上
    public static void setImageView(ImageView imageView, Bitmap bitmap) {
        if (bitmap != null) {
            imageView.setImageBitmap(bitmap);
        }
    }

    //将Bitmap保存到系统相册中，文件名以当前时间命名，后缀为png
    public static boolean saveBitmapToGallery(Context context, Bitmap bitmap) {
        String fileName = "IMG_" + new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(new Date()) + ".png";
        ContentValues values = new ContentValues();
        values.put(MediaStore.Images.Media.DISPLAY_NAME, fileName);
        values.put(MediaStore.Images.Media.MIME_TYPE, "image/png");
        Uri uri = context.getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);
        OutputStream outputStream = null;
        try {
            outputStream = context.getContentResolver().openOutputStream(uri);
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, outputStream);
            outputStream.flush();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (outputStream != null) {
                    outputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    // 封装后
    public static boolean saveBase64ImageToGallery(View view, ImageView imageView, Context context, String base64Image) {

        Bitmap bitmap = base64ToBitmap(base64Image);
        if (bitmap == null) {
            return false;
        }

        // 显示Bitmap到ImageView上
        setImageView(imageView, bitmap);

        // 保存Bitmap到系统相册
        boolean success = saveBitmapToGallery(context, bitmap);
        if (success) {
            Snackbar.make(view, "图片保存成功", Snackbar.LENGTH_SHORT).show();
        } else {
            Snackbar.make(view, "图片保存失败", Snackbar.LENGTH_SHORT).show();
        }

        return success;
    }
}
