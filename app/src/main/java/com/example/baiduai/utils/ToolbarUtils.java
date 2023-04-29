package com.example.baiduai.utils;

import android.content.Intent;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.baiduai.ColorEnhance;
import com.example.baiduai.ContrastEnhance;
import com.example.baiduai.FaceDetection;
import com.example.baiduai.ImageColourize;
import com.example.baiduai.ImageDefinitionEnhance;
import com.example.baiduai.ImageDehazing;
import com.example.baiduai.ImageDenoise;
import com.example.baiduai.R;
import com.example.baiduai.StyleConversion;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.snackbar.Snackbar;

public class ToolbarUtils implements View.OnClickListener, Toolbar.OnMenuItemClickListener {
    private AppCompatActivity mActivity;
    private ImageView mTopAppBarImage;
    private MaterialToolbar mTopAppBar;
    private Intent intent;

    public ToolbarUtils(AppCompatActivity activity) {
        mActivity = activity;
        mTopAppBarImage = mActivity.findViewById(R.id.topAppBarImage);
        mTopAppBar = mActivity.findViewById(R.id.topAppBar);
        mTopAppBar.setOnMenuItemClickListener(this);
        mTopAppBarImage.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.topAppBarImage:
                Snackbar.make(v, R.string.snackbar_text_label, Snackbar.LENGTH_SHORT).show();
                break;
        }
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.tips:
                new MaterialAlertDialogBuilder(mActivity)
                        .setTitle(mActivity.getResources().getString(R.string.tips_title))
                        .setMessage(mActivity.getResources().getString(R.string.tips_supporting_text))
                        .setPositiveButton(mActivity.getResources().getString(R.string.btn_close), (dialog, which) -> {
                        })
                        .show();
                return true;
            case R.id.favorite:
                MaterialAlertDialogBuilder favoriteDialogBuilder = new MaterialAlertDialogBuilder(mActivity)
                        .setTitle(mActivity.getResources().getString(R.string.favorite_title))
                        .setMessage(mActivity.getResources().getString(R.string.favorite_supporting_text));

                ImageView imageView = new ImageView(mActivity);
                imageView.setImageResource(R.drawable.github);
                favoriteDialogBuilder.setView(imageView);

                favoriteDialogBuilder.setPositiveButton(mActivity.getResources().getString(R.string.btn_close), (dialog, which) -> {
                        })
                        .show();
                return true;
            case R.id.style_conversion:
                intent = new Intent(mActivity, StyleConversion.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                mActivity.startActivity(intent);
                return true;
            case R.id.image_colourize:
                intent = new Intent(mActivity, ImageColourize.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                mActivity.startActivity(intent);
                return true;
            case R.id.fece_dect:
                intent = new Intent(mActivity, FaceDetection.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                mActivity.startActivity(intent);
                return true;
            case R.id.image_dehazing:
                intent = new Intent(mActivity, ImageDehazing.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                mActivity.startActivity(intent);
                return true;
            case R.id.contrast_enhance:
                intent = new Intent(mActivity, ContrastEnhance.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                mActivity.startActivity(intent);
                return true;
            case R.id.image_definition_enhance:
                intent = new Intent(mActivity, ImageDefinitionEnhance.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                mActivity.startActivity(intent);
                return true;
            case R.id.color_enhance:
                intent = new Intent(mActivity, ColorEnhance.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                mActivity.startActivity(intent);
                return true;
            case R.id.image_denoise:
                intent = new Intent(mActivity, ImageDenoise.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                mActivity.startActivity(intent);
                return true;
            default:
                return false;
        }
    }
}
