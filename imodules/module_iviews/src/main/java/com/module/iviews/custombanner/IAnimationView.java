package com.module.iviews.custombanner;


import android.animation.Animator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.DecelerateInterpolator;



/**
 * @ClassName: IAnimationView
 * @Author: bsj-dingcl
 * @Email: dingchenglei@h4kit.com
 * @Date: 2020/
 * @des:
 */
public class IAnimationView extends View {


    public float direction = 0;
    public float percent;

    Paint paint = new Paint();

    private ValueAnimator mAnimator;

    private IOnAnimationViewListener onAnimationViewListener;

    private Bitmap frontBitmap = null, backBitmap = null;

    public IAnimationView(Context context) {
        this(context, null);
    }

    public IAnimationView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public IAnimationView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setColor(Color.parseColor("#29cf79"));
        paint.setStrokeWidth(4f);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeCap(Paint.Cap.ROUND);
    }

    public void setOnAnimationViewListener(IOnAnimationViewListener onAnimationViewListener) {
        this.onAnimationViewListener = onAnimationViewListener;
    }

    public void setBitmap(Bitmap frontBitmap, Bitmap backBitmap) {
        this.frontBitmap = frontBitmap;
        this.backBitmap = backBitmap;
    }

    public void setAnimationPercent(float percent) {
        this.percent = percent;
        if (percent > 0) {
            direction = 1;
        } else if (percent < 0) {
            direction = -1;
        }

        postInvalidate();
    }

    public boolean isAnimationRunning() {
        if (mAnimator == null) {
            return false;
        }
        return mAnimator.isRunning();
    }

    public void startAnimation(float toPercent) {
        if (mAnimator != null && mAnimator.isRunning()) {
            return;
        }

        mAnimator = ValueAnimator.ofFloat(percent, toPercent);
        mAnimator.setDuration((long) (Math.abs(toPercent - percent) * 300));
        mAnimator.setInterpolator(new DecelerateInterpolator());
        mAnimator.start();
        OnAnimationListener onAnimationListener = new OnAnimationListener(toPercent);
        mAnimator.addUpdateListener(onAnimationListener);
        mAnimator.addListener(onAnimationListener);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int width = getWidth();
        //canvas.drawCircle(width * percent,50,10,paint);
        if (frontBitmap == null || backBitmap == null) {
            return;
        }


        canvas.drawBitmap(frontBitmap, width * percent, 0, paint);
        if (percent != 0) {
            canvas.drawBitmap(backBitmap, width * (percent > 0 ? percent - 1 : percent + 1), 0, paint);
        }


        //canvas.drawCircle(100,100,50,paint);
    }

    class OnAnimationListener implements ValueAnimator.AnimatorUpdateListener, Animator.AnimatorListener {
        private float toPercent;

        public OnAnimationListener(float toPercent) {
            this.toPercent = toPercent;
        }

        @Override
        public void onAnimationStart(Animator animation) {

        }

        @Override
        public void onAnimationEnd(Animator animation) {
            percent = 0;
            if (onAnimationViewListener == null) {
                return;
            }
            onAnimationViewListener.pageChanged(toPercent);
        }

        @Override
        public void onAnimationCancel(Animator animation) {
            percent = 0;
        }

        @Override
        public void onAnimationRepeat(Animator animation) {

        }

        @Override
        public void onAnimationUpdate(ValueAnimator animation) {
            setAnimationPercent((float) animation.getAnimatedValue());
        }
    }
}

