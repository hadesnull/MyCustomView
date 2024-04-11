package com.cai.yi.myapplication0.wight;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.ComposeShader;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

import com.cai.yi.myapplication0.R;

/**
 * Created by qf on 2017/9/22.
 */

public class ShaderDemoView extends View {

    public ShaderDemoView(Context context) {
        super(context);
    }

    public ShaderDemoView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public ShaderDemoView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Bitmap bitmap1 = BitmapFactory.decodeResource(getResources(), R.drawable.loan_no_ic_supplementt);
        BitmapShader bitmapShader = new BitmapShader(bitmap1, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);

        Bitmap bitmap2 = BitmapFactory.decodeResource(getResources(), R.drawable.loan_zhima_score_round_bg);
        BitmapShader bitmapShader2 = new BitmapShader(bitmap2, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);

        ComposeShader composeShader = new ComposeShader(bitmapShader, bitmapShader2, PorterDuff.Mode.SRC_OVER);

        Paint paint = new Paint();
        paint.setShader(composeShader);

        canvas.drawCircle(getWidth()/2, getHeight()/2, getWidth()/2, paint);


    }
}
