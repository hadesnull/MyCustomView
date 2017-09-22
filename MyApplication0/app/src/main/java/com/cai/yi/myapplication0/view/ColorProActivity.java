package com.cai.yi.myapplication0.view;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.cai.yi.myapplication0.R;
import com.cai.yi.myapplication0.wight.ColorProgressView;

/**
 * Created by qf on 2017/9/22.
 */

public class ColorProActivity extends Activity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_color_pro);
        ColorProgressView colorProgressView = (ColorProgressView) findViewById(R.id.view_pro);
        colorProgressView.setScroce("88");
    }
}
