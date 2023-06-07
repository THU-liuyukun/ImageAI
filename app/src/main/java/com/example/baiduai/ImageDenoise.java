package com.example.baiduai;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.chaquo.python.Kwarg;
import com.chaquo.python.PyObject;
import com.chaquo.python.Python;
import com.chaquo.python.android.AndroidPlatform;
import com.example.baiduai.utils.ImagePickerUtils;
import com.example.baiduai.utils.ImageUtils;
import com.example.baiduai.utils.StatusBar;
import com.example.baiduai.utils.ToolbarUtils;
import com.google.android.material.slider.Slider;

public class ImageDenoise extends AppCompatActivity implements View.OnClickListener, Slider.OnChangeListener {
    private ToolbarUtils mToolbarUtils;
    private ImageView img1;
    private String base64Image = null;
    private ImageView img2;
    private Python py;
    private Slider slider;
    private String intensity = "0";
    private Button btn_main;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        StatusBar statusBar = new StatusBar(ImageDenoise.this);
        statusBar.setColor(R.color.transparent);

        setContentView(R.layout.image_denoise);

        // 加载topAppBar
        mToolbarUtils = new ToolbarUtils(this);

        // 选择图片
        img1 = findViewById(R.id.img1);
        img1.setOnClickListener(this);

        // 显示图片
        img2 = findViewById(R.id.img2);

        // 去噪强度
        slider = findViewById(R.id.intensity_slider);
        slider.addOnChangeListener(this);

        btn_main = findViewById(R.id.btn_main);
        btn_main.setOnClickListener(this);

        initPython();
        py = Python.getInstance();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.img1:
                // 选择图片
                ImagePickerUtils.pickImage(this, img1);
                break;

            case R.id.btn_main:
                btn_main.setEnabled(false);
                btn_main.setText(R.string.button_waiting);
                Thread thread = new Thread(() -> {
                    PyObject obj1 = py.getModule("image_denoise")
                            .callAttr("main",
                                    new Kwarg("img_base64", base64Image),
                                    new Kwarg("intensity", intensity));
                    base64Image = obj1.toJava(String.class);
                    runOnUiThread(() -> {
                        btn_main.setEnabled(true);
                        btn_main.setText(R.string.button_name);
                        ImageUtils.saveBase64ImageToGallery(v, img2, ImageDenoise.this, base64Image);
                    });
                });
                thread.start();
                break;
        }
    }

    @Override
    public void onValueChange(@NonNull Slider slider, float value, boolean fromUser) {
        int intValue = (int) value;
        intensity = String.valueOf(intValue);
    }

    // 将选择的图片的转成base64
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        base64Image = ImagePickerUtils.handleActivityResult(this, requestCode, resultCode, data, img1);
    }

    void initPython() {
        if (!Python.isStarted()) {
            Python.start(new AndroidPlatform(this));
        }
    }

}
