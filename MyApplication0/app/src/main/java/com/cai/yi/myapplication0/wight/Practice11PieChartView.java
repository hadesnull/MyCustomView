package com.cai.yi.myapplication0.wight;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class Practice11PieChartView extends View {
    private static final String TAG = "Practice11PieChartView";
    //circle
    private RectF mOvalShape;
    private RectF mOvalShapeMax;
    //circle attr
    private int mCircleLeft = 100;
    private int mCircleTop = 50;
    private int mCircleRadius = 200;
    private int[] degreeRange = {0, 2, 7, 7, 30, 129, 120, 58};

    //paint
    private Paint mPaintPurple = new Paint(Paint.ANTI_ALIAS_FLAG);
    private Paint mPaintGray = new Paint(Paint.ANTI_ALIAS_FLAG);
    private Paint mPaintGreen = new Paint(Paint.ANTI_ALIAS_FLAG);
    private Paint mPaintBlue = new Paint(Paint.ANTI_ALIAS_FLAG);
    private Paint mPaintRed = new Paint(Paint.ANTI_ALIAS_FLAG);
    private Paint mPaintYellow = new Paint(Paint.ANTI_ALIAS_FLAG);
    private Paint mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    //paints集合
    private List<Paint> mPaints = new ArrayList<>();

    //line
    private Paint mPaintPath = new Paint(Paint.ANTI_ALIAS_FLAG);
    private List<Path> mPaths = new ArrayList<>();

    private List<Point> mPoints = new ArrayList<>();

    //text
    private Paint mPaintText = new Paint(Paint.ANTI_ALIAS_FLAG);
    private String[] mStrings = {"Froyo", "Gingerbread", "Ice Cream Sandwich", "Jelly Bean", "KitKat", "Lollipop", "Marshmallow"};

    private Rect mRect = new Rect();

    public Practice11PieChartView(Context context) {
        this(context, null);
    }

    public Practice11PieChartView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public Practice11PieChartView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        initCircle();

        mPaintPath.setColor(Color.argb(120, 232, 232, 232));
        mPaintPath.setStyle(Paint.Style.STROKE);
        mPaintPath.setStrokeWidth(1.5f);

        mPaintText.setColor(Color.WHITE);
        mPaintText.setTextSize(15);
        int[] degree = new int[7];
        int start = -1;
        for (int i = 0; i < 7; i++) {
            start += degreeRange[i] + 1;
            degree[i] = degreeRange[i + 1] / 2 + start;
        }

        int left = 0;
        for (int i = 0; i < 7; i++) {
            Path path = new Path();
            Point p = getPoint(degree[i]);

            path.moveTo(p.x, p.y);

            if (i == 0) {
                path.lineTo(p.x + 70, p.y);
                left = p.x += 75;
            }

            if (i == 1 || i == 2) {
                path.lineTo(p.x + 25, p.y);
                path.rLineTo(20, 20);
                path.rLineTo(25, 0);
                p.x = left;
                p.y += 20;
            }

            if (i == 3) {
                path.lineTo(p.x + 25, p.y + 25);
                path.lineTo(left - 5, p.y + 25);
                p.x = left;
                p.y += 25;
            }

            if (i == 4) {
                path.lineTo(p.x - 25, p.y + 25);
                path.rLineTo(-70, 0);
                p.x = p.x - 95;
                p.y += 25;
            }

            if (i == 5) {
                path.moveTo(p.x - 10, p.y -10);
                path.lineTo(p.x - 20, p.y - 20);
                path.rLineTo(-80, 0);
                p.x = p.x - 100;
                p.y -= 20;
            }

            if (i == 6) {
                path.lineTo(p.x + 25, p.y - 25);
                path.lineTo(left - 5, p.y - 25);
                p.x = left;
                p.y -= 25;
            }
            mPaths.add(path);
            mPoints.add(p);
        }
    }

    private void initCircle() {
        mPaints.add(mPaint);
        mOvalShape = new RectF(mCircleLeft, mCircleTop, 500, 450);
        mPaintPurple.setColor(Color.argb(190, 115, 15, 189));
        mPaints.add(mPaintPurple);
        mPaintGray.setColor(Color.argb(190, 144, 138, 143));
        mPaints.add(mPaintGray);
        mPaintGreen.setColor(Color.argb(190, 138, 216, 91));
        mPaints.add(mPaintGreen);
        mPaintBlue.setColor(Color.argb(190, 97, 176, 237));
        mPaints.add(mPaintBlue);
        mPaintRed.setColor(Color.argb(190, 216, 44, 37));
        mPaints.add(mPaintRed);
        mPaintYellow.setColor(Color.argb(190, 228, 227, 17));
        mPaints.add(mPaintYellow);

        mOvalShapeMax = new RectF(90, 40, 490, 440);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

//        综合练习
//        练习内容：使用各种 Canvas.drawXXX() 方法画饼图
        pie(canvas);

        for (int i = 0; i < 7; i++) {
            canvas.drawPath(mPaths.get(i), mPaintPath);
            if (i == 4 || i == 5) {
                mPaintText.getTextBounds(mStrings[i], 0, mStrings[i].length(), mRect);
                canvas.drawText(mStrings[i], mPoints.get(i).x - mRect.width() - 5, mPoints.get(i).y, mPaintText);
                continue;
            }
            canvas.drawText(mStrings[i], mPoints.get(i).x, mPoints.get(i).y, mPaintText);
        }
    }

    private void pie(Canvas canvas) {
        int start = -1;

        for (int i = 0; i < 7; i ++) {
            if (i == 5) {
                canvas.drawArc(mOvalShapeMax, start += degreeRange[i] + 1, degreeRange[i + 1], true, mPaints.get(i));
                continue;
            }
            canvas.drawArc(mOvalShape, start += degreeRange[i] + 1, degreeRange[i + 1], true, mPaints.get(i));
            Log.i(TAG, "pie: start :" + start + " degreeRange:" + degreeRange[i + 1]);
        }
    }

    private Point getPoint(int degree) {
        Log.i(TAG, "getPoint: " + degree);
        Point point = new Point();
        int r = mCircleRadius;
        point.x = (int) (mCircleLeft + r + r * Math.cos(degree * Math.PI / 180));
        point.y = (int) (mCircleTop + r + r * Math.sin(degree * Math.PI / 180));
        return point;
    }
}