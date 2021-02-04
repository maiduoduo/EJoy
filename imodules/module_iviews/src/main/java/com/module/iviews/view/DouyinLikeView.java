package com.module.iviews.view;

import android.content.Context;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.widget.ImageView;
import android.widget.RelativeLayout;


import com.module.ires.bean.utils.IAnimUtil;
import com.module.iviews.R;

import java.util.Random;


/**
 * @ClassName:  DouyinLikeView
 * @Author: maiduoduo
 * @BLOG: https://blog.csdn.net/Maiduoudo
 * @Date: 2021/2/2
 * @des: 点赞动画view
 */
public class DouyinLikeView extends RelativeLayout {
    private GestureDetector gestureDetector;
    /** 图片大小 */
    private int likeViewSize = 330;
    private int[] angles = new int[]{-30, 0, 30};
    /** 单击是否有点赞效果 */
    private boolean canSingleTabShow = false;
    private OnPlayPauseListener onPlayPauseListener;
    private OnLikeListener onLikeListener;

    public DouyinLikeView(Context context) {
        super(context);
        init();
    }

    public DouyinLikeView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        gestureDetector = new GestureDetector(new GestureDetector.SimpleOnGestureListener() {
            @Override
            public boolean onDoubleTap(MotionEvent e) {
                addLikeView(e);
                onLikeListener.onLikeListener();
                return true;
            }

            @Override
            public boolean onSingleTapConfirmed(MotionEvent e) {
                if (onPlayPauseListener != null) {
                    onPlayPauseListener.onPlayOrPause();
                }
                return true;
            }
        });

        setOnTouchListener((v, event) -> {
            gestureDetector.onTouchEvent(event);
            return true;
        });
    }

    private void addLikeView(MotionEvent e) {
        ImageView imageView = new ImageView(getContext());
        imageView.setImageResource(R.mipmap.eicon_like);

        addView(imageView);

        LayoutParams layoutParams = new LayoutParams(likeViewSize, likeViewSize);
        layoutParams.leftMargin = (int) e.getX() - likeViewSize / 2;
        layoutParams.topMargin = (int) e.getY() - likeViewSize;
        imageView.setLayoutParams(layoutParams);

        playAnim(imageView);
    }

    private void playAnim(View view) {
        AnimationSet animationSet = new AnimationSet(true);
        int degrees = angles[new Random().nextInt(3)];
        animationSet.addAnimation(IAnimUtil.rotateAnim(0, 0, degrees));
        animationSet.addAnimation(IAnimUtil.scaleAnim(100, 2f, 1f, 0));
        animationSet.addAnimation(IAnimUtil.alphaAnim(0, 1, 100, 0));
        animationSet.addAnimation(IAnimUtil.scaleAnim(500, 1f, 1.8f, 300));
        animationSet.addAnimation(IAnimUtil.alphaAnim(1f, 0, 500, 300));
        animationSet.addAnimation(IAnimUtil.translationAnim(500, 0, 0, 0, -400, 300));

        animationSet.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                new Handler().post(() -> removeView(view));
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        view.startAnimation(animationSet);
    }

    public interface OnPlayPauseListener {
        void onPlayOrPause();
    }

    /**
     * 设置单机播放暂停事件
     * @param onPlayPauseListener
     */
    public void setOnPlayPauseListener(OnPlayPauseListener onPlayPauseListener) {
        this.onPlayPauseListener = onPlayPauseListener;
    }

    public interface OnLikeListener {
        void onLikeListener();
    }

    /**
     * 设置双击点赞事件
     * @param onLikeListener
     */
    public void setOnLikeListener(OnLikeListener onLikeListener) {
        this.onLikeListener = onLikeListener;
    }
}
