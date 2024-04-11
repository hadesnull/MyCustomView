package com.cai.yi.myapplication0;

import android.animation.Animator;
import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

/**
 * Created by qf on 2017/9/21.
 */

public class ViewAnimationActivity extends Activity{

    private ImageView mImg;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animation);

        mImg = (ImageView) findViewById(R.id.img_bg);


        mImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int height = mImg.getHeight();
                int width = mImg.getWidth();
                Animator circularReveal = ViewAnimationUtils.createCircularReveal(mImg, 0, 0,  (float) Math.sqrt(height*height + width*width), 0);
                circularReveal.setDuration(1000);
                circularReveal.addListener(new Animator.AnimatorListener() {
                    @Override
                    public void onAnimationStart(Animator animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animator animation) {

                    }

                    @Override
                    public void onAnimationCancel(Animator animation) {

                    }

                    @Override
                    public void onAnimationRepeat(Animator animation) {

                    }
                });
                circularReveal.start();
            }
        });



    }
}
