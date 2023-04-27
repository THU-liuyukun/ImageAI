package com.example.baiduai;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

public class FaceDetection extends AppCompatActivity implements View.OnClickListener {

    private ImageView face_dect_img1;
    private ActivityResultLauncher<Intent> resultLauncher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.face_detection);
        face_dect_img1 = findViewById(R.id.face_dect_img1);
        face_dect_img1.setOnClickListener(this);

        // 跳转到系统相册，选择图片并返回
        resultLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
            @Override
            public void onActivityResult(ActivityResult result) {
                if (result.getResultCode() == RESULT_OK) {
                    Intent intent = result.getData();
                    //获取选中图片的路径对象
                    Uri picUri = intent.getData();
                    if (picUri != null) {
                        face_dect_img1.setImageURI(picUri);
                        Log.d("lyk", "picUri:" + picUri.toString());
                    }
                }
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.face_dect_img1:
                // 跳转到系统相册，选择图片，并返回
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                // 设置内容类型为图片类型
                intent.setType("image/*");
                // 打开系统相册，并等待图片选择结果
                resultLauncher.launch(intent);
                break;
        }
    }
}