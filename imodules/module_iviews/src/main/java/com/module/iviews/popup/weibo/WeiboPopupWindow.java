package com.module.iviews.popup.weibo;

import android.animation.Animator;
import android.animation.Animator.AnimatorListener;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.os.Handler;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationSet;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import com.module.iviews.R;
import java.util.ArrayList;
import java.util.List;

/**
 * 仿微博点击“+”弹窗PopupWindow
 */
public class WeiboPopupWindow extends PopupWindow implements OnClickListener {

    private String TAG = WeiboPopupWindow.class.getSimpleName();
    Activity mContext;
    private int mWidth;
    private int mHeight;
    private int statusBarHeight;
    private Bitmap mBitmap = null;
    private Bitmap overlay = null;

    private Handler mHandler = new Handler();

    public WeiboPopupWindow(Activity context) {
        mContext = context;
    }

    public void init() {
        Rect frame = new Rect();
        mContext.getWindow().getDecorView().getWindowVisibleDisplayFrame(frame);
        statusBarHeight = frame.top;
        DisplayMetrics metrics = new DisplayMetrics();
        mContext.getWindowManager().getDefaultDisplay()
                .getMetrics(metrics);
        mWidth = metrics.widthPixels;
        mHeight = metrics.heightPixels;

        setWidth(mWidth);
        setHeight(mHeight);
    }

    private Bitmap blur(int blurradius) {
        if (null != overlay) {
            return overlay;
        }
        long startMs = System.currentTimeMillis();

        View view = mContext.getWindow().getDecorView();
        view.setDrawingCacheEnabled(true);
        view.buildDrawingCache(true);
        mBitmap = view.getDrawingCache();

//        float scaleFactor = 8;
//        float radius = 10;
        float scaleFactor = 8;
        float radius = blurradius;
        int width = mBitmap.getWidth();
        int height = mBitmap.getHeight();

        overlay = Bitmap.createBitmap((int) (width / scaleFactor), (int) (height / scaleFactor), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(overlay);
        canvas.scale(1 / scaleFactor, 1 / scaleFactor);
        Paint paint = new Paint();
        paint.setFlags(Paint.FILTER_BITMAP_FLAG);
        canvas.drawBitmap(mBitmap, 0, 0, paint);

        overlay = FastBlur.doBlur(overlay, (int) radius, true);
        Log.i(TAG, "blur time is:" + (System.currentTimeMillis() - startMs));
        return overlay;
    }

    private Animation showAnimation1(final View view, int fromY, int toY) {
        AnimationSet set = new AnimationSet(true);
        TranslateAnimation go = new TranslateAnimation(0, 0, fromY, toY);
        go.setDuration(300);
        TranslateAnimation go1 = new TranslateAnimation(0, 0, -10, 2);
        go1.setDuration(100);
        go1.setStartOffset(250);
        set.addAnimation(go1);
        set.addAnimation(go);

        set.setAnimationListener(new AnimationListener() {

            @Override
            public void onAnimationEnd(Animation animation) {
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }

            @Override
            public void onAnimationStart(Animation animation) {

            }

        });
        return set;
    }


    private List<LinearLayout> menuitemViews = new ArrayList<>();
    public void showWindow(View anchor, int bottomMargin) {
        menuitemViews.clear();
        final RelativeLayout layout = (RelativeLayout) LayoutInflater.from(mContext).inflate(R.layout.layout_weib_popupwindow, null);
        setContentView(layout);
        final ImageView close = layout.findViewById(R.id.popwindow_close);
        LinearLayout popWrite = layout.findViewById(R.id.popWrite);
        LinearLayout popAlbum = layout.findViewById(R.id.popAlbum);
        LinearLayout popCamara = layout.findViewById(R.id.popCamara);
        LinearLayout popSign = layout.findViewById(R.id.popSign);
        LinearLayout popComment = layout.findViewById(R.id.popComment);
        LinearLayout popMore = layout.findViewById(R.id.popMore);
        menuitemViews.add(popWrite);
        menuitemViews.add(popAlbum);
        menuitemViews.add(popWrite);
        menuitemViews.add(popCamara);
        menuitemViews.add(popSign);
        menuitemViews.add(popComment);
        menuitemViews.add(popMore);
        popWrite.setOnClickListener(this);
        popAlbum.setOnClickListener(this);
        popCamara.setOnClickListener(this);
        popSign.setOnClickListener(this);
        popComment.setOnClickListener(this);
        popMore.setOnClickListener(this);
        close.setVisibility(View.VISIBLE);
        close.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isShowing()) {
                    closeAnimation();
                    close.setVisibility(View.INVISIBLE);
                }
            }

        });

        showAnimation(layout);
        setBackgroundDrawable(new BitmapDrawable(mContext.getResources(), blur(10)));
        setOutsideTouchable(true);
        setFocusable(true);
        showAtLocation(anchor, Gravity.BOTTOM, 0, statusBarHeight);
    }

    private void showAnimation(ViewGroup layout) {
        for (int i = 0; i < layout.getChildCount(); i++) {
            final View child = layout.getChildAt(i);
            if (child.getId() == R.id.popwindow_close) {
                continue;
            }
            child.setVisibility(View.INVISIBLE);
            mHandler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    child.setVisibility(View.VISIBLE);
                    ValueAnimator fadeAnim = ObjectAnimator.ofFloat(child, "translationY", 600, 0);
                    fadeAnim.setDuration(300);
                    KickBackAnimator kickAnimator = new KickBackAnimator();
                    kickAnimator.setDuration(150);
                    fadeAnim.setEvaluator(kickAnimator);
                    fadeAnim.start();
                }
            }, i * 50);
        }

    }

    private void closeAnimation() {
        if (menuitemViews != null) {
            for (int i = 0; i < menuitemViews.size(); i++) {
                final LinearLayout mChildView = menuitemViews.get(i);
                mHandler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mChildView.setVisibility(View.VISIBLE);
                        ValueAnimator fadeAnim = ObjectAnimator.ofFloat(mChildView, "translationY", 0, 800);
                        fadeAnim.setDuration(400);
                        KickBackAnimator kickAnimator = new KickBackAnimator();
                        kickAnimator.setDuration(200);
                        fadeAnim.setEvaluator(kickAnimator);
                        fadeAnim.start();
                        fadeAnim.addListener(new AnimatorListener() {
                            @Override
                            public void onAnimationStart(Animator animation) {
                            }
                            @Override
                            public void onAnimationRepeat(Animator animation) {
                            }
                            @Override
                            public void onAnimationEnd(Animator animation) {
                                mChildView.setVisibility(View.INVISIBLE);
                                dismiss();
                            }
                            @Override
                            public void onAnimationCancel(Animator animation) {
                            }
                        });
                    }
                }, (menuitemViews.size() - i - 1) * 50);
            }
        }
    }


    public void destroy() {
        if (null != overlay) {
            overlay.recycle();
            overlay = null;
            System.gc();
        }
        if (null != mBitmap) {
            mBitmap.recycle();
            mBitmap = null;
            System.gc();
        }
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.popWrite) {
            closeAnimation();
        } else if (v.getId() == R.id.popAlbum) {
            closeAnimation();
        } else if (v.getId() == R.id.popCamara) {
            closeAnimation();
        } else if (v.getId() == R.id.popSign) {
            closeAnimation();
        } else if (v.getId() == R.id.popComment) {
            closeAnimation();
        } else if (v.getId() == R.id.popMore) {
            closeAnimation();
        }

    }
}
