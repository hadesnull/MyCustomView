package com.cai.yi.myapplication0;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;



public class SimpPieView extends View {

    private Paint mOutCirclePaint;
    private Paint mlightPaint;
    private TextPaint mTextPaint;

    private float current = 0;

    private int runScroe = 0;

    private float offset = 15f;

    private boolean isShow = true;
    /**
     * 整个弧度
     */
    private int all_arc = 252;

    private float out_circle_size = dip2px(getContext(), 2);

    private float out_light_circle_size = dip2px(getContext(), 1);

    private float big_text_size = dip2px(getContext(), 45);

    private float smaill_text_size = dip2px(getContext(), 13);

    public SimpPieView(Context context) {
        this(context, null);
    }

    public SimpPieView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SimpPieView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        init(context);

    }

    private void init(Context context) {
        mOutCirclePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mOutCirclePaint.setColor(getContext().getResources().getColor(R.color.loan_credit_blue));
        mOutCirclePaint.setStyle(Paint.Style.STROKE);
        mOutCirclePaint.setStrokeWidth(out_circle_size);
        mOutCirclePaint.setStrokeCap(Paint.Cap.ROUND);

        mlightPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mlightPaint.setColor(getContext().getResources().getColor(R.color.loan_blue_light_color));
        mlightPaint.setStyle(Paint.Style.STROKE);
        mlightPaint.setStrokeWidth(out_light_circle_size);
        mlightPaint.setStrokeCap(Paint.Cap.ROUND);

        mTextPaint = new TextPaint(Paint.ANTI_ALIAS_FLAG);
        mTextPaint.setColor(getContext().getResources().getColor(R.color.loan_credit_blue));
        mTextPaint.setStyle(Paint.Style.STROKE);

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        setScore(81);
    }

    @Override
    protected void onDraw(Canvas canvas) {

        int singleArc = all_arc / 6;

        canvas.save();

        RectF rectF = new RectF(offset, offset, getWidth()-offset, getHeight()-offset);

        //canvas.drawRect(rectF, mOutCirclePaint);
        //canvas.drawLine(getWidth()/2,0,getWidth()/2,getHeight(),mOutCirclePaint);
        //canvas.drawLine(0,getHeight()/2,getWidth(),getHeight()/2,mOutCirclePaint);

        /*逆旋转到第一个刻度的位置 方便后面旋转画布画刻度*/
        canvas.rotate(-((90-(360-all_arc)/2 - singleArc/2)), getWidth()/2 +offset, getHeight()/2 + offset);  // 20 = 90 - (360 - 250)/2 - 15

        /*绘制母版弧度*/
        canvas.drawArc(rectF, 180 - singleArc/2,all_arc,false, mlightPaint);

        if (current > 0) {
            /*动态绘制比例弧度*/
            mOutCirclePaint.setStyle(Paint.Style.STROKE);
            canvas.drawArc(rectF, 180 - singleArc/2, current, false, mOutCirclePaint);
            /*绘制移动的圆点*/
            mOutCirclePaint.setStyle(Paint.Style.FILL);
            float x = getWidth()/2  + (float) ((Math.cos((180 - singleArc/2 + current) * Math.PI / 180) * ((getWidth() / 2) - offset)));
            float y = getHeight()/2  + (float) ((Math.sin((180 - singleArc/2 + current) * Math.PI / 180) * ((getHeight() / 2) - offset)) );
            canvas.drawCircle(x,y,dip2px(getContext(), 4),mOutCirclePaint);
        }

        /*绘制刻度*/
        for (int i=0; i<6; i++) {
            canvas.drawLine(offset + offset +20.0f, getHeight()/2, offset + offset+50.0f,getHeight()/2 , mlightPaint);
            canvas.rotate(singleArc, rectF.centerX(), rectF.centerY());
        }

        canvas.restore();

        /*绘制百分比*/
        mTextPaint.setTextSize(big_text_size);
        Paint.FontMetrics fontMetrics = mTextPaint.getFontMetrics();
        float textLen = mTextPaint.measureText(runScroe+"%");
        float textheight = fontMetrics.bottom - fontMetrics.top;
        canvas.drawText(runScroe+"%", getWidth()/2 + offset - textLen/2, getHeight()/2 + textheight/4  + offset ,mTextPaint);

        /*绘制描述*/
        mTextPaint.setTextSize(smaill_text_size);
        float textLenLit = mTextPaint.measureText("相似度");
        canvas.drawText("相似度", getWidth()/2 + offset - textLenLit/2 , getHeight()/2 + textheight /2+ dip2px(getContext(), 30) ,mTextPaint);

    }

    public void setScore(float scroe){
        if (scroe < 0 ) {
            scroe = 0;
        }
        if (scroe > 100) {
            scroe = 100;
        }

        float currentScroe = (scroe / 100.0f * all_arc);
        animation(currentScroe);
    }

    private void animation(float number) {
        ValueAnimator valueAnimator = ValueAnimator.ofFloat(0, number);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float animatedValue = (float) animation.getAnimatedValue();
                Log.i("value", "value="+animatedValue);
                current =  animatedValue;
                runScroe =  (int)( current * 100.0f / 250.0f);
                invalidate();
            }
        });
        valueAnimator.setDuration(3*1000);
        valueAnimator.start();
    }

    public static int dip2px(Context var0, float var1) {
        float var2 = var0.getResources().getDisplayMetrics().density;
        return (int)(var1 * var2 + 0.5F);
    }

}
