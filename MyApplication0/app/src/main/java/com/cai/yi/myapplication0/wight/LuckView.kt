package com.cai.yi.myapplication0.wight

import android.animation.Animator
import android.animation.ValueAnimator
import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Rect
import android.os.Build
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import android.view.View
import android.view.animation.DecelerateInterpolator
import androidx.annotation.RequiresApi
import com.cai.yi.myapplication0.R
import java.util.ArrayList
import kotlin.random.Random

class LuckView : View {

    private val TAG = "LuckView"

    companion object {
        enum class LuckViewType {
            Coin, Gift, Null
        }
    }

    constructor(context: Context) : this(context, null)
    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {

        init()
    }

    private var mPaint = Paint(Paint.ANTI_ALIAS_FLAG)
    private var mTopPaint = Paint(Paint.ANTI_ALIAS_FLAG)
    private var divSpace = dp2px(context,10F)

    private lateinit var bitmap1: Bitmap
    private lateinit var bitmap2: Bitmap
    private lateinit var bitmap3: Bitmap
    private lateinit var bitmapText: Bitmap
    private lateinit var bitmapCenter: Bitmap


    private var stopListener: LuckViewStop? = null
    private fun init() {

        mPaint.style = Paint.Style.STROKE
        mPaint.color = Color.BLUE

        mTopPaint.color = Color.parseColor("#88FFB17C")
        mTopPaint.style = Paint.Style.FILL

        bitmap1 = BitmapFactory.decodeResource(resources, R.drawable.walk_luck_gift1)
        bitmap2 = BitmapFactory.decodeResource(resources, R.drawable.walk_luck_gift2)
        bitmap3 = BitmapFactory.decodeResource(resources, R.drawable.walk_luck_gift3)
        bitmapText = BitmapFactory.decodeResource(resources, R.drawable.walk_luck_gift_text)
        bitmapCenter = BitmapFactory.decodeResource(resources, R.drawable.walk_luck_spin)
    }

    public fun setStopListener(listener: LuckViewStop) {
        stopListener = listener
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {

        var customW = dp2px(context,220F)
        var customH = dp2px(context,220F)
        var width = 0
        var height = 0
        var specModeW = MeasureSpec.getMode(widthMeasureSpec)
        var specSizeW = MeasureSpec.getSize(widthMeasureSpec)
        when (specModeW) {
            MeasureSpec.UNSPECIFIED -> {
            }//父视图不约束子视图(一般不用)
            MeasureSpec.EXACTLY -> {
                width = specSizeW
            }// 明确指定了 当指定了控件大小或者使用Match_Parent
            MeasureSpec.AT_MOST -> {//一般使用Wrap_Content会进入这个条件
                width = customW.coerceAtMost(specSizeW)
            }// 一般为WARP_CONTENT
        }
        var specModeH = MeasureSpec.getMode(heightMeasureSpec)
        var specSizeH = MeasureSpec.getSize(heightMeasureSpec)
        when (specModeH) {
            MeasureSpec.UNSPECIFIED -> {}//父视图不约束子视图(一般不用)
            MeasureSpec.EXACTLY -> {
                height = specSizeH
            }// 明确指定了
            MeasureSpec.AT_MOST -> {
                height = customH.coerceAtMost(specSizeH)
            }// 一般为WARP_CONTENT
        }

        setMeasuredDimension(width.coerceAtMost(height), width.coerceAtMost(height))


    }

    //边缘
    private var bigRect = Rect()

    //按钮
    private var centerRect = Rect()

    //全部小矩形
    private var allSmallRect = arrayOfNulls<Rect>(9)

    //顺序排列的小礼物矩形
    private var luckData = ArrayList<LuckDrawBean>()

    var offset = dp2px(context,2F)
    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)

        bigRect.left = paddingLeft + offset
        bigRect.top = paddingTop + offset
        bigRect.right = width - paddingRight - offset
        bigRect.bottom = height - paddingBottom - offset


        var startX = bigRect.left
        var startY = bigRect.top
        val smallRectWidth = (bigRect.width() - divSpace * 2) / 3
        allSmallRect = arrayOfNulls<Rect>(9)
        for (i in 0..8) {
            startY = if (i <= 2) {
                bigRect.top
            } else if (i <= 5) {
                bigRect.top + smallRectWidth + divSpace
            } else {
                bigRect.top + (smallRectWidth + divSpace) * 2
            }

            val smallRect = Rect(startX, startY, startX + smallRectWidth, startY + smallRectWidth)

            Log.d(TAG, "onSizeChanged: ----$i---$smallRect")
            allSmallRect[i] = smallRect

            startX = smallRect.right + divSpace
            if (startX >= bigRect.width()) {
                startX = bigRect.left
            }
        }

        centerRect = allSmallRect[4]!!


        luckData.clear()
        luckData.add(LuckDrawBean(rect = allSmallRect[0], image = bitmap1))
        luckData.add(LuckDrawBean(rect = allSmallRect[1], image = bitmap2))
        luckData.add(LuckDrawBean(rect = allSmallRect[2], image = bitmap3))
        luckData.add(LuckDrawBean(rect = allSmallRect[5], image = bitmap2))
        luckData.add(LuckDrawBean(rect = allSmallRect[8], image = bitmap1))
        luckData.add(LuckDrawBean(rect = allSmallRect[7], image = bitmap2))
        luckData.add(LuckDrawBean(rect = allSmallRect[6], image = bitmapText))
        luckData.add(LuckDrawBean(rect = allSmallRect[3], image = bitmap3))
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)


        mPaint.color = Color.YELLOW
        for (smallRect in allSmallRect) {
            if (smallRect != null) {
                mPaint.color = Color.parseColor("#FFEBB9FD")
                mPaint.style = Paint.Style.FILL
                canvas.drawRoundRect(
                    smallRect.left.toFloat(),
                    smallRect.top.toFloat(),
                    smallRect.right.toFloat(),
                    smallRect.bottom.toFloat(),
                    dp2px(context,11F).toFloat(),
                    dp2px(context,11F).toFloat(),
                    mPaint
                )

                mPaint.color = Color.parseColor("#FFF9DAFF")
                mPaint.style = Paint.Style.STROKE
                mPaint.strokeWidth = dp2px(context,3F).toFloat()
                canvas.drawRoundRect(
                    smallRect.left.toFloat(),
                    smallRect.top.toFloat(),
                    smallRect.right.toFloat(),
                    smallRect.bottom.toFloat(),
                    dp2px(context,11F).toFloat(),
                    dp2px(context,11F).toFloat(),
                    mPaint
                )
            }
        }

        //中间按钮
        val bitmapCenter = bitmapCenter
        val bitmapLeft = centerRect.left + (centerRect.width() - bitmapCenter.width) / 2f
        val bitmapTop = centerRect.top + (centerRect.height() - bitmapCenter.height) / 2f
        canvas.drawBitmap(bitmapCenter, bitmapLeft, bitmapTop, mPaint)

        for (i in 0..luckData.size-1) {
            val item = luckData[i]
            item.rect?.let {
                val bitmapLeft = it.left + (it.width() - item.image.width) / 2f
                val bitmapTop = it.top + (it.height() - item.image.height) / 2f
                canvas.drawBitmap(item.image, bitmapLeft, bitmapTop, mPaint)


                if (i == currentIndex) {
                    mPaint.strokeWidth = dp2px(context,3F).toFloat()
                    canvas.drawRoundRect(
                        it.left.toFloat(),
                        it.top.toFloat(),
                        it.right.toFloat(),
                        it.bottom.toFloat(),
                        dp2px(context,11F).toFloat(),
                        dp2px(context,11F).toFloat(),
                        mTopPaint
                    )
                }
            }
        }
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        parent.requestDisallowInterceptTouchEvent(true)
        event?.let { event ->
            if (event.action == MotionEvent.ACTION_DOWN) {
                Log.d(TAG, "onTouchEvent: ----${event.x}--${event.y}---${centerRect.toString()}")
                if (centerRect.contains(event.x.toInt(), event.y.toInt())) {
                    onStart()
                }
                return super.onTouchEvent(event)
            }
        }
        return super.onTouchEvent(event)

    }

    override fun onDetachedFromWindow() {
        bitmap1.recycle()
        bitmap2.recycle()
        bitmap3.recycle()
        bitmapText.recycle()
        bitmapCenter.recycle()
        animator?.let {
            it.removeAllListeners()
            it.cancel()
        }

        super.onDetachedFromWindow()
    }


    private var currentIndex = 0

    private var animator: ValueAnimator? = null
    private fun initAnimator(stopIndex: Int) {
        animator?.let {
            it.removeAllListeners()
            it.cancel()
        }

        animator = ValueAnimator.ofInt(0, (7 + 8 * 10) + stopIndex + 1)
        animator?.apply {
            duration = 6 * 1000
            interpolator = DecelerateInterpolator()

            addUpdateListener { animation ->
                val currentNumber = animation.animatedValue as Int
                Log.d(TAG, "initAnimator: -----$currentNumber")
                currentIndex = currentNumber % 8
                postInvalidate()
            }
            addListener(object : Animator.AnimatorListener {
                override fun onAnimationStart(animation: Animator) {
                }

                override fun onAnimationEnd(animation: Animator) {
                    if (null != stopListener) {
                        stopListener!!.stop()
                    }
                }

                override fun onAnimationCancel(animation: Animator) {

                }

                override fun onAnimationRepeat(animation: Animator) {

                }
            })
            start()
        }

    }


    private fun onStart() {
        when (currentStopType) {
            LuckViewType.Coin -> stopToCoin()
            LuckViewType.Gift -> stopToGift()
            LuckViewType.Null -> {
                //ToastUtils.showLong(context.getString(R.string.time_over))
            }
        }
    }

    private var currentStopType: LuckViewType = LuckViewType.Coin

    public fun stopType(type: LuckViewType) {
        currentStopType = type
    }


    private fun stopToCoin() {
        val coinIndex = arrayOf(2, 7)
        initAnimator(coinIndex[Random.nextInt(coinIndex.size - 1)])
    }

    private fun stopToGift() {
        val coinIndex = arrayOf(1, 3, 5)
        initAnimator(coinIndex[Random.nextInt(coinIndex.size - 1)])
    }

}

fun dp2px(context: Context,dpValue: Float): Int {
    val scale: Float = context.getResources().getDisplayMetrics().density
    return (dpValue * scale + 0.5f).toInt()
}


interface LuckViewStop {
    fun stop()
}

data class LuckDrawBean(
    val rect: Rect?,
    val image: Bitmap,
)