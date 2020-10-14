package com.module.iviews.custombanner;


import android.content.Context;
import android.content.res.TypedArray;
import android.database.DataSetObserver;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.GradientDrawable;
import android.os.Handler;
import android.os.Message;
import android.os.StrictMode;
import android.support.v7.graphics.Palette;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.widget.Adapter;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.module.iviews.R;
import com.module.iviews.paletteimageview.IPaletteImageView;
import com.module.iviews.paletteimageview.OnParseColorListener;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

/**
 * @ClassName: IBannerView
 * @Author: bsj-dingcl
 * @Email: dingchenglei@h4kit.com
 * @Date: 2020/
 * @des:
 */
public class IBannerView extends FrameLayout implements IOnAnimationViewListener {


    private Adapter adapter;
    private int current_position = 0;

    private float mStartX, mStartY, mEndX, mEndY, mMoveX, mMoveY;
    int indicator_size = 5, indicator_space = 10;
    private float percent = 0;
    private View current_view = null, front_view = null, next_view = null;
    private IAnimationView animationView;

    private LayoutParams mLayoutParams;

    private Boolean isSlide = false;
    private int second = 0;
    private AdapterDataSetObserver dataSetObserver;

    private OnPickColorListener onPickColorListener;

    public void setOnPickColorListener(OnPickColorListener onPickColorListener1) {
        this.onPickColorListener = onPickColorListener1;
    }


    public interface OnPickColorListener {
        void onColorArray(int[] colors);
    }

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == 1) {
                second += 1;
                if (adapter != null && adapter.getCount() > 0 && second > 2) {

                    createAnimationView();
                    if (!animationView.isAnimationRunning() && !isSlide) {
                        switchAniamtionBitmap(-1);
                        if (!isAnimationViewVisible()) {
                            setAnimationViewVisible(true);
                        }
                        animationView.startAnimation(-1);
                    }
                    second = 0;
                }
            }
            super.handleMessage(msg);
        }
    };

    Timer timer = new Timer();
    TimerTask timerTask = new TimerTask() {
        @Override
        public void run() {
            handler.sendEmptyMessage(1);
        }
    };

    public IBannerView(Context context) {
        this(context, null);
    }

    public IBannerView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public IBannerView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }

    public int dip2px(float dpValue) {
        final float scale = getContext().getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    private void init(AttributeSet attrs) {
        mLayoutParams = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
        TypedArray ta = getContext().obtainStyledAttributes(attrs, R.styleable.IBannerView);
        indicator_size = ta.getInt(R.styleable.IBannerView_indicator_size, dip2px(2));
        indicator_space = ta.getInt(R.styleable.IBannerView_indicator_space, dip2px(15));
        ta.recycle();

        timer.schedule(timerTask, 1000, 1000);
    }

//    public void setAdapter(Adapter adapter) {
//        if (adapter != null && dataSetObserver != null) {
//            adapter.unregisterDataSetObserver(dataSetObserver);
//        }
//        this.adapter = adapter;
//        dataSetObserver = new AdapterDataSetObserver();
//        adapter.registerDataSetObserver(dataSetObserver);
//        updateView();
//    }

    private List<Integer> urlLocallist = new ArrayList();
    private List<String> urlNetList = new ArrayList();
    private boolean isNet = false;

    /**
     * 数据设置
     *
     * @param adapter
     * @param pics1 数据源数据
     * @param isNetImg 数据源是否是网络图片  true:网络图片  false:本地图片
     * @param flBannerRoot 要设置背景的控件   更多控件传入请自行拓展：  View... views
     */
    private View flBanner;

    public void setAdapter(Adapter adapter, List pics1, boolean isNetImg, View flBannerRoot) {
        urlLocallist.clear();
        urlNetList.clear();
        if (isNetImg) {
            isNet = true;
            urlNetList = pics1;
        } else {
            isNet = false;
            urlLocallist = pics1;
        }
        flBanner = flBannerRoot;
        if (adapter != null && dataSetObserver != null) {
            adapter.unregisterDataSetObserver(dataSetObserver);
        }
        this.adapter = adapter;
        dataSetObserver = new AdapterDataSetObserver();
        adapter.registerDataSetObserver(dataSetObserver);
        updateView();
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (getWidth() <= 0 || getHeight() <= 0) {
            return false;
        }

        if (adapter == null || adapter.getCount() == 0) {
            return true;
        }

        //当动画组件动画执行中，则忽略touch事件
        if (animationView != null && animationView.isAnimationRunning()) {
            return true;
        }
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mStartX = event.getX();
                mStartY = event.getY();

                break;
            case MotionEvent.ACTION_MOVE:
                mMoveX = event.getX() - mStartX;
                createAnimationView();
                isSlide = true;
                percent = mMoveX / getWidth();

                if (percent < -1) {
                    percent = -1;
                } else if (percent > 1) {
                    percent = 1;
                }
                if (!isAnimationViewVisible()) {
                    setAnimationViewVisible(true);
                }
                switchAniamtionBitmap(percent);
                animationView.setAnimationPercent(percent);
                break;
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_CANCEL:
            case MotionEvent.ACTION_OUTSIDE:
                float toPercent = 0;
                if (percent > 0.1f) {
                    toPercent = 1;
                } else if (percent < -0.1f) {
                    toPercent = -1;
                }
                animationView.startAnimation(toPercent);
                break;

        }

        return true;
    }

    private void createAnimationView() {
        if (animationView == null) {
            animationView = new IAnimationView(getContext());
            animationView.setOnAnimationViewListener(this);
        }
    }

    protected boolean isAnimationViewVisible() {
        return animationView != null && ((View) animationView).getParent() != null;
    }

    private void switchAniamtionBitmap(float percent) {
        //当第一次滑动或改变方向的时候重新装载图片

        if (animationView.direction == 0 || animationView.direction * percent < 0) {
            Bitmap frontBitmap = getViewBitmap(current_view);
            Bitmap backBitmap = percent > 0 ? getViewBitmap(front_view) : getViewBitmap(next_view);
            initAniamtionView(frontBitmap, backBitmap);
        }
    }

    /*
     *  获取view截图
     */
    protected Bitmap getViewBitmap(View view) {
        view.setDrawingCacheEnabled(true);
        view.buildDrawingCache();
        return view.getDrawingCache();
    }

    private void initAniamtionView(Bitmap frontBitmap, Bitmap backBitmap) {
        animationView.setBitmap(frontBitmap, backBitmap);
    }


    @Override
    protected void dispatchDraw(Canvas canvas) {
        super.dispatchDraw(canvas);
        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setStrokeWidth(1.0f);
        paint.setColor(Color.parseColor("#EF5362"));
        paint.setStyle(Paint.Style.STROKE);
        int count = adapter.getCount();

        int x_space = (getWidth() - (count - 1) * indicator_space) / 2;

        for (int i = 0; i < count; i++) {
            if (current_position == i) {
                paint.setStyle(Paint.Style.FILL_AND_STROKE);
            } else {
                paint.setStyle(Paint.Style.STROKE);
            }

//            canvas.drawCircle(x_space + i * indicator_space, getHeight() - indicator_space, indicator_size, paint);

            //使用画笔在画布上画矩形
//            canvas.drawRect(10,10,20,20,paint);
            canvas.drawRect(x_space + i * indicator_space,getHeight() - indicator_space,x_space + i * indicator_space+20,getHeight() - indicator_space+10,paint);
        }

    }

    protected void setAnimationViewVisible(boolean visible) {
        if (animationView == null) {
            return;
        }
        if (visible) {
            addView((View) animationView, mLayoutParams);
        } else {
            removeView((View) animationView);
            animationView.direction = 0;
        }
    }

    //TODO:-----------更新界面--------------------------
    private void updateView() {
        removeAllViews();
        setAnimationViewVisible(false);
        if (adapter.getCount() > 0) {
            if (current_position >= adapter.getCount()) {
                current_position = 0;
            }
            if (current_position < 0) {
                current_position = adapter.getCount() - 1;
            }
            Log.e("Banner", "updateView current_position):" + current_position);
            current_view = adapter.getView(current_position, current_view, null);
            front_view = adapter.getView(getPosition(current_position - 1), front_view, null);
            next_view = adapter.getView(getPosition(current_position + 1), next_view, null);
            addView(front_view);
            addView(current_view);
            addView(next_view);

            front_view.setVisibility(INVISIBLE);
            this.current_view.setVisibility(VISIBLE);
            next_view.setVisibility(INVISIBLE);

            // 用来提取颜色的Bitmap
            //提取颜色
            changeTopBgColor(current_position);
        }
    }


    /**
     * 根据Palette提取的颜色
     * &可以修改tab和toolbar以及状态栏的颜色
     * &可以修改背景色或者字体颜色等
     */
    private void changeTopBgColor(int position) {
        // 用来提取颜色的Bitmap
        if (isNet){
            if (urlNetList != null && urlNetList.size() > 0) {
                Bitmap bitmap = GetNetBitmap((String) urlNetList.get(position));
                getPaletteColor(bitmap);
            }
        }else {
            if (urlLocallist != null && urlLocallist.size() > 0) {
                Bitmap bitmap = BitmapFactory.decodeResource(getResources(), urlLocallist.get(position));
                getPaletteColor(bitmap);
            }
        }

    }

    private int[] colors = new int[3];
    private void getPaletteColor(Bitmap bitmap) {
        // Palette的部分
        if (bitmap != null) {
            Palette.Builder builder = Palette.from(bitmap);
            builder.generate(new Palette.PaletteAsyncListener() {
                @Override
                public void onGenerated(Palette palette) {
                    //获取到充满活力的这种色调
                    Palette.Swatch vibrant = palette.getVibrantSwatch();
                    //根据调色板Palette获取到图片中的颜色设置到toolbar和tab中背景，标题等，使整个UI界面颜色统一
//                    flBanner.setBackgroundColor(vibrant.getRgb());
//                    flBanner.setBackgroundColor(colorBurn(vibrant.getRgb()));
//                    if (android.os.Build.VERSION.SDK_INT >= 21) {
//                        Window window = getWindow();
//                        window.setStatusBarColor(colorBurn(vibrant.getRgb()));
//                        window.setNavigationBarColor(colorBurn(vibrant.getRgb()));
//                    }


                    //获取到充满活力的这种色调
//                    Palette.Swatch s = palette.getVibrantSwatch();       //获取到充满活力的这种色调
//                    Palette.Swatch s = palette.getDarkVibrantSwatch();    //获取充满活力的黑
//                    Palette.Swatch s = palette.getLightVibrantSwatch();   //获取充满活力的亮
//                    Palette.Swatch s = palette.getMutedSwatch();           //获取柔和的色调
//                    Palette.Swatch s = palette.getDarkMutedSwatch();      //获取柔和的黑
//                    Palette.Swatch s = palette.getLightMutedSwatch();    //获取柔和的亮
//                    //独特的一种
//                    //返回从调色板中占主导地位的样本。
//                    Palette.Swatch s = palette.getDominantSwatch();

                    Palette.Swatch vibrantSwatch = palette.getVibrantSwatch();
//                    List<Palette.Swatch> swatches = palette.getSwatches();
                    if (vibrantSwatch != null) {
                        Log.e("Banner", "===vibrantSwatch=1=" + vibrantSwatch.getRgb());
//                        flBanner.setBackgroundColor(colorBurn(vibrantSwatch.getRgb()));
                        colors[0] = vibrantSwatch.getRgb();
                    }
                    //获取充满活力的黑
                    Palette.Swatch darkVibrantSwatch = palette.getDarkVibrantSwatch();
                    if (darkVibrantSwatch != null) {
                        Log.e("Banner", "===darkVibrantSwatch=2=" + darkVibrantSwatch.getRgb());
                    }
                    //获取充满活力的亮
                    Palette.Swatch lightVibrantSwatch = palette.getLightVibrantSwatch();
                    if (lightVibrantSwatch != null) {
                        Log.e("Banner", "===lightVibrantSwatch=3=" + lightVibrantSwatch.getRgb());
//                        flBanner.setBackgroundColor(colorBurn(vibrantSwatch.getRgb()));
                        colors[1] = lightVibrantSwatch.getRgb();
                    }
                    //获取柔和的色调
                    Palette.Swatch mutedSwatch = palette.getMutedSwatch();
                    if (mutedSwatch != null) {
                        Log.e("Banner", "===mutedSwatch=4=" + mutedSwatch.getRgb());
//                        flBanner.setBackgroundColor(mutedSwatch.getRgb());
//                        flBanner.setBackgroundColor(colorBurn(mutedSwatch.getRgb()));
                    }
                    //获取柔和的黑
                    Palette.Swatch darkMutedSwatch = palette.getDarkMutedSwatch();
                    if (darkMutedSwatch != null) {
                        Log.e("Banner", "===darkMutedSwatch=5=" + darkMutedSwatch.getRgb());
                    }
                    //获取柔和的亮
                    Palette.Swatch lightMutedSwatch = palette.getLightMutedSwatch();
                    if (lightMutedSwatch != null) {
                        Log.e("Banner", "===lightMutedSwatch=6=" + lightMutedSwatch.getRgb());
//                        flBanner.setBackgroundColor(lightMutedSwatch.getRgb());
                        colors[2] = lightMutedSwatch.getRgb();
                    }


//                    GradientDrawable drawable = new GradientDrawable();
                    //设置圆角大小
//                    drawable.setCornerRadius(5);
                    //设置边缘线的宽以及颜色
//                    drawable.setStroke(1, Color.parseColor(#FF00FF));
                    //设置shape背景色
//                    drawable.setColor(Color.parseColor("#FFFFFF"));
//                    drawable.
                    //设置到TextView中
//                    tv_type.setBackgroundDrawable(drawable);


//                    int colors[] = { 0xff255779 , 0xff3e7492, 0xffa6c0cd };//分别为开始颜色，中间颜色，结束颜色
                    GradientDrawable gradientDrawable = new GradientDrawable(GradientDrawable.Orientation.TOP_BOTTOM, colors);
                    gradientDrawable.setCornerRadius(5);
                    flBanner.setBackgroundDrawable(gradientDrawable);


                }
            });
        }
    }


    /**
     * 颜色加深处理
     *
     * @param RGBValues RGB的值，由alpha（透明度）、red（红）、green（绿）、blue（蓝）构成，
     *                  Android中我们一般使用它的16进制，
     *                  例如："#FFAABBCC",最左边到最右每两个字母就是代表alpha（透明度）、
     *                  red（红）、green（绿）、blue（蓝）。每种颜色值占一个字节(8位)，值域0~255
     *                  所以下面使用移位的方法可以得到每种颜色的值，然后每种颜色值减小一下，在合成RGB颜色，颜色就会看起来深一些了
     * @return
     */
    private int colorBurn(int RGBValues) {
        int alpha = RGBValues >> 24;
        int red = RGBValues >> 16 & 0xFF;
        int green = RGBValues >> 8 & 0xFF;
        int blue = RGBValues & 0xFF;
        red = (int) Math.floor(red * (1 - 0.1));
        green = (int) Math.floor(green * (1 - 0.1));
        blue = (int) Math.floor(blue * (1 - 0.1));
        return Color.rgb(red, green, blue);
    }

    //修改透明度
    public static Bitmap getImageToChange(Bitmap mBitmap) {
//        Log.d(TAG,"with="+mBitmap.getWidth()+"--height="+mBitmap.getHeight());
        Bitmap createBitmap = Bitmap.createBitmap(mBitmap.getWidth(), mBitmap.getHeight(), Bitmap.Config.ARGB_4444);
        int mWidth = mBitmap.getWidth();
        int mHeight = mBitmap.getHeight();
        for (int i = 0; i < mHeight; i++) {
            for (int j = 0; j < mWidth; j++) {
                int color = mBitmap.getPixel(j, i);
                int g = Color.green(color);
                int r = Color.red(color);
                int b = Color.blue(color);
                int a = Color.alpha(color);

                float index = i * 1.0f / mHeight;
                if (index > 0.5f) {
                    float temp = i - mHeight / 2.0f;
                    a = 255 - (int) (temp / 375 * 255);
                }
                color = Color.argb(a, r, g, b);
                createBitmap.setPixel(j, i, color);
            }
        }
        return createBitmap;
    }


    /**
     * 将网络图片转化成Bitmap
     *
     * @param url
     * @return
     */
    public static Bitmap GetNetBitmap(String url) {
        // Android 4.0 之后不能在主线程中请求HTTP请求


        Bitmap bitmap1 = null;
        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
            try {
                URL urlData = new URL(url);
                InputStream is = urlData.openStream();
                bitmap1 = BitmapFactory.decodeStream(is);
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
        }

        return bitmap1;

//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                Bitmap bitmap1;
//                try {
//                    URL urlData = new URL(url);
//                    InputStream is = urlData.openStream();
//                    Bitmap bitmap1 = BitmapFactory.decodeStream(is);
//                    is.close();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//                return bitmap1;
//            }
//        }).start();
    }

    private void initListener(IPaletteImageView mPaletteImageView) {
        mPaletteImageView.setOnParseColorListener(new OnParseColorListener() {
            @Override
            public void onComplete(IPaletteImageView paletteImageView) {
                int[] vibrant = paletteImageView.getVibrantColor();
                int[] vibrantDark = paletteImageView.getDarkVibrantColor();
                int[] vibrantLight = paletteImageView.getLightVibrantColor();
                int[] muted = paletteImageView.getMutedColor();
                int[] mutedDark = paletteImageView.getDarkMutedColor();
                int[] mutedLight = paletteImageView.getLightMutedColor();
                List<int[]> list = new ArrayList<int[]>();
                list.clear();
                list.add(vibrant);
                list.add(vibrantDark);
                list.add(vibrantLight);
                list.add(muted);
                list.add(mutedDark);
                list.add(mutedLight);
                for (int i = 0; i < list.size(); i++) {
                    int[] arry = list.get(i);
                    if (arry == null) list.remove(arry);
                }
                int[] arry = list.get(new Random().nextInt(list.size() - 1));
                if (arry != null) {
                    Log.e("Banner", "---updateView arry != null-----length-----:" + arry.length);
                    if (onPickColorListener != null) {
                        onPickColorListener.onColorArray(arry);
                    }
//                    title.setTextColor(arry[1]);
//                    content.setTextColor(arry[0]);
//                    mLinearLayout.setBackgroundColor(arry[2]);
                }
            }

            @Override
            public void onFail() {

            }
        });
    }

    private int getPosition(int position) {
        if (position >= adapter.getCount()) {
            position = 0;
        }
        if (position < 0) {
            position = adapter.getCount() - 1;
        }
        return position;
    }

    @Override
    public void pageChanged(float toPercent) {
        Log.e("leafly", String.valueOf(percent));
        if (toPercent == 1) {
            current_position -= 1;
        } else if (toPercent == -1) {
            current_position += 1;
        }
        updateView();
        isSlide = false;
        second = 0;
        percent = 0;
    }


    public class AdapterDataSetObserver extends DataSetObserver {
        @Override
        public void onChanged() {
            super.onChanged();
            updateView();
        }

        @Override
        public void onInvalidated() {
            super.onInvalidated();
        }
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        timer.cancel();
    }

}

