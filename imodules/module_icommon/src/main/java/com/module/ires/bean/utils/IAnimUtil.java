package com.module.ires.bean.utils;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Interpolator;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;

/**
 * @function: 动画工具类
 * UpdateListener： 动画过程中通过添加此监听来回调数据
 * EndListener： 动画结束的时候通过此监听器来做一些处理
 */
public class IAnimUtil {

    private ValueAnimator valueAnimator;
    private UpdateListener updateListener;
    private EndListener endListener;
    private long duration;
    private float start;
    private float end;
    private Interpolator interpolator = new LinearInterpolator();


    public IAnimUtil() {
        // 默认动画时常1s
        duration = 1000;
        start = 0.0f;
        end = 1.0f;
        // 匀速的插值器
        interpolator = new LinearInterpolator();
    }


    public void setDuration(int timeLength) {
        duration = timeLength;
    }

    public void setValueAnimator(float start, float end, long duration) {
        this.start = start;
        this.end = end;
        this.duration = duration;
    }

    public void setInterpolator(Interpolator interpolator) {
        this.interpolator = interpolator;
    }

    public void startAnimator() {
        if (valueAnimator != null) {
            valueAnimator = null;
        }
        valueAnimator = ValueAnimator.ofFloat(start, end);
        valueAnimator.setDuration(duration);
        valueAnimator.setInterpolator(interpolator);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {

                if (updateListener == null) {
                    return;
                }

                float cur = (float) valueAnimator.getAnimatedValue();
                updateListener.progress(cur);
            }
        });
        valueAnimator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animator) {
            }

            @Override
            public void onAnimationEnd(Animator animator) {
                if (endListener == null) {
                    return;
                }
                endListener.endUpdate(animator);
            }

            @Override
            public void onAnimationCancel(Animator animator) {
            }

            @Override
            public void onAnimationRepeat(Animator animator) {
            }
        });
        valueAnimator.start();
    }

    public void addUpdateListener(UpdateListener updateListener) {
        this.updateListener = updateListener;
    }

    public void addEndListner(EndListener endListener) {
        this.endListener = endListener;
    }

    public interface EndListener {
        void endUpdate(Animator animator);
    }

    public interface UpdateListener {
        void progress(float progress);
    }



    /**
     * 以中心缩放动画
     *
     * @param from
     * @param to
     */
    public static ScaleAnimation scaleAnim(long time, float from, float to, long offsetTime) {
        ScaleAnimation scaleAnimation = new ScaleAnimation(from, to, from, to,
                Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        scaleAnimation.setStartOffset(offsetTime);
        scaleAnimation.setInterpolator(new DecelerateInterpolator());
        scaleAnimation.setDuration(time);
        return scaleAnimation;
    }

    /**
     * 旋转动画
     *
     * @param time
     */
    public static RotateAnimation rotateAnim(long time, int fromDegrees, float toDegrees) {
        RotateAnimation rotateAnimation = new RotateAnimation(fromDegrees, toDegrees,
                Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        rotateAnimation.setDuration(time);
        return rotateAnimation;
    }

    /**
     * 移动动画
     *
     * @param fromX
     * @param toX
     * @param fromY
     * @param toY
     */
    public static TranslateAnimation translationAnim(long time, float fromX, float toX, float fromY, float toY, long offsetTime) {
        TranslateAnimation anim = new TranslateAnimation(fromX, toX, fromY, toY);
        anim.setDuration(time);
        anim.setInterpolator(new DecelerateInterpolator());
        anim.setStartOffset(offsetTime);
        return anim;
    }

    /**
     * 透明度动画
     *
     * @param fromAlpha
     * @param toAlpha
     * @param duration
     */
    public static AlphaAnimation alphaAnim(float fromAlpha, float toAlpha, long duration, long offsetTime) {
        AlphaAnimation anim = new AlphaAnimation(fromAlpha, toAlpha);
        anim.setDuration(duration);
        anim.setStartOffset(offsetTime);
        return anim;
    }

}
