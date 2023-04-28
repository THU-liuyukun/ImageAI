package com.example.baiduai;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.baiduai.utils.ImagePickerUtils;
import com.example.baiduai.utils.PermissionUtils;
import com.example.baiduai.utils.ToolbarUtils;

public class FaceDetection extends AppCompatActivity implements View.OnClickListener {

    private ToolbarUtils mToolbarUtils;
    private ImageView face_dect_img1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.face_detection);

        // 权限申请
        PermissionUtils.checkPermissions(this);

        // 加载topAppBar
        mToolbarUtils = new ToolbarUtils(this);

        // 选择图片
        face_dect_img1 = findViewById(R.id.face_dect_img1);
        face_dect_img1.setOnClickListener(this);
    }

    // 动态申请权限
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        PermissionUtils.handlePermissionsResult(this, requestCode, permissions, grantResults);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.face_dect_img1:
                // 选择图片
                ImagePickerUtils.pickImage(this, face_dect_img1);
                break;
        }
    }

    // 处理选择的图片的base64
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        String base64Image = ImagePickerUtils.handleActivityResult(this, requestCode, resultCode, data, face_dect_img1);
        if (base64Image != null) {
            Log.d("lyk", base64Image);
        }
    }
}