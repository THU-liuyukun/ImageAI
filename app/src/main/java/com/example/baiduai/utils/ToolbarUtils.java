package com.example.baiduai.utils;

import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.baiduai.R;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.snackbar.Snackbar;

public class ToolbarUtils implements View.OnClickListener, Toolbar.OnMenuItemClickListener{
    private AppCompatActivity mActivity;
    private ImageView mTopAppBarImage;
    private MaterialToolbar mTopAppBar;

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
                        .setPositiveButton(mActivity.getResources().getString(R.string.btn_close), (dialog, which) -> {})
                        .show();
                return true;
            case R.id.favorite:
                MaterialAlertDialogBuilder favoriteDialogBuilder = new MaterialAlertDialogBuilder(mActivity)
                        .setTitle(mActivity.getResources().getString(R.string.favorite_title))
                        .setMessage(mActivity.getResources().getString(R.string.favorite_supporting_text));

                ImageView imageView = new ImageView(mActivity);
                imageView.setImageResource(R.drawable.github);
                favoriteDialogBuilder.setView(imageView);

                favoriteDialogBuilder.setPositiveButton(mActivity.getResources().getString(R.string.btn_close), (dialog, which) -> {})
                        .show();
                return true;
            default:
                return false;
        }
    }
}
