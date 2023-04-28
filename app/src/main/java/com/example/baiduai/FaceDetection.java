package com.example.baiduai;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.baiduai.utils.PermissionUtils;
import com.example.baiduai.utils.ToolbarUtils;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.snackbar.Snackbar;

public class FaceDetection extends AppCompatActivity implements View.OnClickListener {

    private ToolbarUtils mToolbarUtils;
    private ImageView face_dect_img1;
    private ActivityResultLauncher<Intent> resultLauncher;
    private ImageView top_app_bar_image;
    private MaterialToolbar top_app_bar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.face_detection);
        // 权限申请
        PermissionUtils.checkPermissions(this);

        // 添加图片
        face_dect_img1 = findViewById(R.id.face_dect_img1);
        face_dect_img1.setOnClickListener(this);

        // topAppBar
//        top_app_bar_image = findViewById(R.id.topAppBarImage);
//        top_app_bar = findViewById(R.id.topAppBar);
//        top_app_bar.setOnMenuItemClickListener(this);
//        top_app_bar_image.setOnClickListener(this);
        mToolbarUtils = new ToolbarUtils(this);

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
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        PermissionUtils.handlePermissionsResult(this, requestCode, permissions, grantResults);
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
//            case R.id.topAppBarImage:
//                Snackbar.make(v, R.string.snackbar_text_label, Snackbar.LENGTH_SHORT).show();
//                break;
        }
    }

//    @Override
//    public boolean onMenuItemClick(MenuItem item) {
//        switch (item.getItemId()) {
//            case R.id.tips:
//                new MaterialAlertDialogBuilder(this)
//                        .setTitle(this.getResources().getString(R.string.tips_title))
//                        .setMessage(this.getResources().getString(R.string.tips_supporting_text))
//                        .setPositiveButton(this.getResources().getString(R.string.btn_close), (dialog, which) -> {
//                        })
//                        .show();
//                return true;
//            case R.id.favorite:
//                MaterialAlertDialogBuilder favoriteDialogBuilder = new MaterialAlertDialogBuilder(this)
//                        .setTitle(this.getResources().getString(R.string.favorite_title))
//                        .setMessage(this.getResources().getString(R.string.favorite_supporting_text));
//
//                ImageView imageView = new ImageView(this);
//                imageView.setImageResource(R.drawable.github);
//                favoriteDialogBuilder.setView(imageView);
//
//                favoriteDialogBuilder.setPositiveButton(this.getResources().getString(R.string.btn_close), (dialog, which) -> {
//                        })
//                        .show();
//                return true;
//            default:
//                return false;
//        }
//    }
}