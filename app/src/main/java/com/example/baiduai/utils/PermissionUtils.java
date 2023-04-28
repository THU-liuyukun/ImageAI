package com.example.baiduai.utils;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PermissionUtils {

    // 用于标识权限申请的请求码
    public static final int REQUEST_CODE_PERMISSIONS = 123;
    private static final Map<String, String> permissionMap = new HashMap<>();

    static {
        permissionMap.put(Manifest.permission.INTERNET, "允许应用程序打开网络套接字");
        permissionMap.put(Manifest.permission.READ_EXTERNAL_STORAGE, "允许应用程序从外部存储读取");
        permissionMap.put(Manifest.permission.WRITE_EXTERNAL_STORAGE, "允许应用程序写入外部存储");
    }

    public static void checkPermissions(Activity activity) {
        String[] permissions = {
                Manifest.permission.INTERNET,
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE
        };
        List<String> permissionList = new ArrayList<>();

        for (String permission : permissions) {
            if (ContextCompat.checkSelfPermission(activity, permission) != PackageManager.PERMISSION_GRANTED) {
                permissionList.add(permission);
            }
        }

        if (!permissionList.isEmpty()) {
            ActivityCompat.requestPermissions(activity, permissionList.toArray(new String[0]), REQUEST_CODE_PERMISSIONS);
        }
    }

    public static void handlePermissionsResult(Activity activity, int requestCode, String[] permissions, int[] grantResults) {
        if (requestCode == REQUEST_CODE_PERMISSIONS) {
            for (int i = 0; i < grantResults.length; i++) {
                if (grantResults[i] == PackageManager.PERMISSION_DENIED) {
                    String permission = permissions[i];
                    String message = "您需要开启" + permissionMap.get(permission) + "权限，才能使用该应用的全部功能";
                    Snackbar.make(activity.findViewById(android.R.id.content), message, Snackbar.LENGTH_LONG).show();
                }
            }
        }
    }
}
