package com.ejoy.tool.scaffold.utils;
//  ┏┓　　　┏┓
//┏┛┻━━━┛┻┓
//┃　　　　　　　┃
//┃　　　━　　　┃
//┃　┳┛　┗┳　┃
//┃　　　　　　　┃
//┃　　　┻　　　┃
//┃　　　　　　　┃
//┗━┓　　　┏━┛
//    ┃　　　┃                  神兽保佑
//    ┃　　　┃                  永无BUG！
//    ┃　　　┗━━━┓
//    ┃　　　　　　　┣┓
//    ┃　　　　　　　┏┛
//    ┗┓┓┏━┳┓┏┛
//      ┃┫┫　┃┫┫
//      ┗┻┛　┗┻┛

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.DrawableRes;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.ejoy.tool.R;
import com.ejoy.tool.app.App;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;


/**
 * CN:      IToast
 * Author： JSYL-DINGCL (dingcl@jsyl.com.cn)
 * Date:   2019/11/img_g
 * Des:    Toast工具
 */
public class IToast {
    /** 之前显示的内容 */
    private static String oldMsg;
    /** Toast对象 */
    private static Toast mToast = null ;
    private static Toast mWXToast = null ;
    private static Toast mNormalToast = null ;
    /** 第一次时间 */
    private static long oneTime = 0 ;
    /** 第二次时间 */
    private static long twoTime = 0 ;
    private static Context mContext;

    /** Place the object in the center of its container in both the vertical
     *  and horizontal axis, not changing its size. */
    public static int TOAST_GRAVITY_FLAG = 100;
    public static final int TOAST_Gravity_CENTER = Gravity.CENTER;
    public static final int TOAST_Gravity_TOP = Gravity.TOP;
    public static final int TOAST_Gravity_BOTTOM = Gravity.BOTTOM;
    public static final int TOAST_Gravity_LEFT = Gravity.LEFT;
    public static final int TOAST_Gravity_RIGHT = Gravity.RIGHT;
    public static final int TOAST_Gravity_NO_GRAVITY = Gravity.NO_GRAVITY;
    public static final int TOAST_DURATION_SHORT = Toast.LENGTH_SHORT;
    public static final int TOAST_DURATION_LONG = Toast.LENGTH_LONG;

    public static final String TOAST_SIMPLE_TEXT_ICO = "1";
    public static final String TOAST_SIMPLE_TEXT_ONLY = "2";
    public static final String TOAST_SIMPLE_ICO_ONLY = "3";

    //仿微信消息弹窗显示与隐藏
    private final int SHOW = 1;
    private final int HIDE = 0;

//    private boolean isShowDefault = true;
    private TextView mTipText;
    private ImageView mTipImageView;
    private View mView;


    /**
     * 在使用自定义IToast时要先初始化
     * 初始化示例：IToast itoast = new Itoast().builder();
     * 调用示例（成功）： iToast.doTipText("发送成功")
     *                         .doDuration(0)   //时长
     *                         .doGravity(0)    //位置
     *                         .showISuccessToast() //成功的弹窗
     *                         .showIt();   //显示
     */
    public IToast() {
        mContext = App.getAppContext();
    }

    public IToast builder() {
        if (mToast == null){
            synchronized (IToast.class){
                if (mToast == null){
                    mToast = new Toast(App.getAppContext());
                }
            }
        }

        if (mWXToast == null){
            synchronized (IToast.class){
                if (mWXToast == null){
                    mWXToast = new Toast(App.getAppContext());
                }
            }
        }
        return this;
    }







    //------------------------原始Toast-------------------------
    /**
     * 显示Toast
     *   根据显示时间不重复创建实例
     * @param message
     */
    public static void showNoRepeatToast(String message){
        if(mNormalToast == null){
            mNormalToast = Toast.makeText(mContext, message, Toast.LENGTH_SHORT);
            mNormalToast.show() ;
            oneTime = System.currentTimeMillis() ;
        }else{
            twoTime = System.currentTimeMillis() ;
            if(message.equals(oldMsg)){
                if(twoTime - oneTime > Toast.LENGTH_SHORT){
                    mNormalToast.show() ;
                }
            }else{
                oldMsg = message ;
                mNormalToast.setText(message) ;
                mNormalToast.show() ;
            }
        }
        oneTime = twoTime ;
    }



    //------------↓-----------自定义仿ios提示Toast---------------↓---------------
    /**
     * 带图标的Toast
     *      默认图标
     *      默认时长
     * @param text 提示内容
     * @return
     */
    public static void showIImgToast(String text) {
        showIImgToast(text, TOAST_DURATION_SHORT);
    }


    /**
     * 带图标的Toast
     *      默认图标
     *      指定时长
     * @param text 提示内容
     * @param duration 显示时长，对应 Toast.LEHGTH_SHORT和Toast.LENGTH_LONG
     * @return
     */
    public static void showIImgToast(String text, int duration) {
        showIImgToast(text, duration, IToastImageType.DEFAULT);
    }


    /**
     * 带图标的Toast
     *      指定图标
     *      默认时长
     * @param text 提示内容
     * @param type 图片显示类型{@link IToastImageType}
     * @return
     */
    public static void showIImgToast(String text,IToastImageType type) {
        int imageViewResID = IToastImageType.getCommonImageResID(type);
        showIImgToast(text, TOAST_DURATION_SHORT, imageViewResID);
    }

    /**
     * 带图标的Toast
     *      指定图标
     *      指定时长
     * @param text 提示内容
     * @param type 图片显示类型{@link IToastImageType}
     * @return
     */
    public static void showIImgToast(String text, int duration, IToastImageType type) {
        int imageViewResID = IToastImageType.getCommonImageResID(type);
        showIImgToast(text, duration, imageViewResID);
    }

    /**
     * 带图标的Toast
     *      指定自己引入的图标
     *      指定时长
     * @param text 提示内容
     * @param duration 显示时长，对应 Toast.LEHGTH_SHORT和Toast.LENGTH_LONG
     * @param imageViewResID 图片资源ID
     * @return
     */
    public static void showIImgToast(String text, int duration, @DrawableRes int imageViewResID) {
        ObjectUtil.requireNonNull(App.getAppContext(),"context is null");
        if (text == null) {
            text = "提示信息为空";
        }
        LogUtils.e("--------------------imageViewResID----------------------:"+imageViewResID);
        LayoutInflater inflater = (LayoutInflater) App.getAppContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View mView = inflater.inflate(R.layout.layout_itoast, null);
        TextView mTextView = mView.findViewById(R.id.toast_msg);
        ImageView mImageView = mView.findViewById(R.id.toast_iv);
        mImageView.setImageResource(imageViewResID);
        mTextView.setText(text);
        mToast.setGravity(Gravity.CENTER,0,0);
        mToast.setDuration(duration == 0 ? TOAST_DURATION_SHORT : TOAST_DURATION_LONG);
        mToast.setView(mView);
        mToast.show();
    }

    //------------↑-----------自定义仿ios提示Toast------[END]---------↑---------------




    //============↓=======自定义提示Toast------[简单布局]==============↓===============

    /**
     * 简洁自定义Toast
     * @param text 提示文字
     */
    public static void showISimpleToast(String text) {
        showISimpleToast(text,TOAST_DURATION_SHORT);
    }

    /**
     * 简洁自定义Toast
     * @param text 提示文字
     * @param duration 指定时长
     */
    public static void showISimpleToast(String text,int duration) {
        showISimpleToast(text,duration,IToastImageType.DEFAULT);
    }

    /**
     * 简洁自定义Toast
     * @param text 提示文字
     * @param duration 指定时长
     * @param type 图片显示类型{@link IToastImageType}
     */
    public static void showISimpleToast(String text,int duration,IToastImageType type) {
        int imageViewResID = IToastImageType.getCommonImageResID(type);
        showISimpleToast(text,duration,imageViewResID);
    }

    /**
     * 简洁自定义Toast
     * @param text 提示文字
     * @param duration 指定时长
     * @param imageViewResID 指定图标
     *
     */
    public static void showISimpleToast(String text,int duration,@DrawableRes int imageViewResID,int gravity) {
        showISimpleToast(text,duration,imageViewResID,TOAST_SIMPLE_TEXT_ICO,gravity);
    }
    public static void showISimpleToast(String text,int duration,@DrawableRes int imageViewResID) {
        showISimpleToast(text,duration,imageViewResID,TOAST_SIMPLE_TEXT_ICO,TOAST_Gravity_CENTER);
    }

    /**
     * 简洁自定义Toast
     * @param text 提示文字
     * @param duration 指定时长
     * @param showTextIco 指定显示类型：
     *                     public static final String TOAST_SIMPLE_TEXT_ICO = "1";
     *                     public static final String TOAST_SIMPLE_TEXT_ONLY = "2";
     *                     public static final String TOAST_SIMPLE_ICO_ONLY = "3";
     *
     */
    public static void showISimpleToast(String text,int duration,String showTextIco,int gravity) {
        int imageViewResID = IToastImageType.getCommonImageResID(IToastImageType.DEFAULT);
        showISimpleToast(text,duration,imageViewResID,showTextIco,gravity);
    }

    public static void showISimpleToast(String text,int duration,String showTextIco) {
        int imageViewResID = IToastImageType.getCommonImageResID(IToastImageType.DEFAULT);
        showISimpleToast(text,duration,imageViewResID,showTextIco,TOAST_Gravity_CENTER);
    }


    /**
     * 简洁自定义Toast
     *     指定显示类型
     *     指定显示图标
     * @param text
     * @param imageViewResID
     * @param showTextIco
     */
    public static void showISimpleImgResToast(String text,@DrawableRes int imageViewResID,String showTextIco) {
        showISimpleToast(text,TOAST_DURATION_SHORT,imageViewResID,showTextIco,TOAST_Gravity_CENTER);
    }

    /**
     * 简洁自定义Toast
     *     默认显示类型
     *     指定显示图标
     * @param text
     * @param imageViewResID 指定显示图标
     */
    public static void showISimpleImgResToast(String text,@DrawableRes int imageViewResID) {
        showISimpleToast(text,TOAST_DURATION_SHORT,imageViewResID,TOAST_SIMPLE_TEXT_ICO,TOAST_Gravity_CENTER);
    }



    /**
     * 自定义Toast展示文字+左图标
     *      指定自己引入的图标
     *      指定时长
     * @param text 提示内容
     * @param duration 显示时长，对应 Toast.LEHGTH_SHORT和Toast.LENGTH_LONG
     * @param imageViewResID 图片资源ID
     * @param showTextIco 展示类型
     *                    0：图标+文字
     *                    1：图标（无文本）
     *                    2：文本（无图标）
     * @return
     */
    public static void showISimpleToast(String text, int duration, @DrawableRes int imageViewResID,String showTextIco,int Gravity) {
        ObjectUtil.requireNonNull(App.getAppContext(),"context is null");
        if (text == null) {
            text = "提示信息为空";
        }
        LayoutInflater inflater = (LayoutInflater) App.getAppContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View mView = inflater.inflate(R.layout.layout_itoast_simple, null);
        TextView mTextView = mView.findViewById(R.id.toast_msg_text);
        ImageView mImageView = mView.findViewById(R.id.toast_iv_left);
        mImageView.setVisibility(View.GONE);
        mTextView.setVisibility(View.GONE);
        if (TOAST_SIMPLE_TEXT_ICO.equals(showTextIco)) {
            mImageView.setVisibility(View.VISIBLE);
            mTextView.setVisibility(View.VISIBLE);
            mImageView.setImageResource(imageViewResID);
            mTextView.setText(text);
        }else if (TOAST_SIMPLE_TEXT_ONLY.equals(showTextIco)) {
            mImageView.setVisibility(View.GONE);
            mTextView.setVisibility(View.VISIBLE);
            mTextView.setText(text);
        }else if (TOAST_SIMPLE_ICO_ONLY.equals(showTextIco)) {
            mImageView.setVisibility(View.VISIBLE);
            mTextView.setVisibility(View.GONE);
            mImageView.setImageResource(imageViewResID);
        }else {
            mImageView.setVisibility(View.VISIBLE);
            mTextView.setVisibility(View.VISIBLE);
            mTextView.setText("提示文本");
            mImageView.setImageResource(R.drawable.itoast_ico_done_green);
        }

        if (TOAST_Gravity_LEFT == Gravity || TOAST_Gravity_RIGHT== Gravity) mToast.setGravity(Gravity,60,0);
        else if (TOAST_Gravity_TOP == Gravity || TOAST_Gravity_BOTTOM == Gravity) mToast.setGravity(Gravity,0,60);
        else if (TOAST_Gravity_CENTER== Gravity) mToast.setGravity(Gravity,0,0);
        mToast.setDuration(duration == 0 ? TOAST_DURATION_SHORT : TOAST_DURATION_LONG);
        mToast.setView(mView);
        mToast.show();
    }


    //------------↑-----------自定义提示Toast------[END]---------↑---------------



    //------------↓-----------悬浮（仿微信通知到来/新消息）Toast---------------↓----

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case SHOW:
                    handler.sendEmptyMessageDelayed(HIDE, durationTime);
                    show();
                    break;
                case HIDE:
                    hide();
                    break;
            }
        }
    };

    private Object mTN;
    private Method mShow;
    private Method mHide;
    private Field mViewFeild;
    private long durationTime = 3*1000;
    private Activity mWActivity;
    public void showWXFloatToast(Activity activity,String title, String content, Map<String,String> params) {
        mWActivity = activity;
        if (activity == null) {
            return;
        }
//        LayoutInflater inflater = mActivity.getLayoutInflater();//调用Activity的getLayoutInflater()
        LayoutInflater inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View mView = inflater.inflate(R.layout.layout_push_toast, null);
        LinearLayout llPushContent = (LinearLayout) mView.findViewById(R.id.ll_push_content);
        TextView tvTitle = (TextView) mView.findViewById(R.id.tv_title);
        TextView tvContent = (TextView) mView.findViewById(R.id.tv_content);
        LinearLayout llContent = (LinearLayout) mView.findViewById(R.id.ll_push_content);
        //模糊处理

        tvTitle.setText(title);
        tvContent.setText(content);
        mWXToast.setView(mView);
        mWXToast.setDuration(Toast.LENGTH_LONG);
        mWXToast.setGravity(Gravity.TOP, 0, 70);
        reflectEnableClick();
        reflectToast();
        //TODO:如果需要在操作界面进行回调，请自行加入回调---
        llPushContent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showISimpleToast("点击了消息到来");
                handler.sendEmptyMessage(HIDE);
            }
        });
        if(mShow != null && mHide != null){
            handler.sendEmptyMessage(SHOW);
        }else{
            mWXToast.show();
        }
    }

    private void rootBlur(final LinearLayout llContent) {
        llContent.getViewTreeObserver().addOnPreDrawListener(
                new ViewTreeObserver.OnPreDrawListener() {
                    @Override
                    public boolean onPreDraw() {
                        applyBlur(llContent);
                        return true;
                    }
                });
    }

    private void applyBlur(LinearLayout llContent) {
        View view = mWActivity.getWindow().getDecorView();
        view.setDrawingCacheEnabled(true);
        view.buildDrawingCache(true);
        /**
         * 获取当前窗口快照，相当于截屏
         */
        Bitmap bmp1 = view.getDrawingCache();
//        final Bitmap bmp1 = BitmapFactory.decodeResource(getResources(), R.drawable.bg);
        int height = getOtherHeight();
        /**
         * 除去状态栏和标题栏
         */
        Bitmap bmp2 = Bitmap.createBitmap(bmp1, 0, height,bmp1.getWidth(), bmp1.getHeight() - height);
        blur(bmp2, llContent);
    }

    @SuppressLint("NewApi")
    private void blur(Bitmap bkg, View view) {
        long startMs = System.currentTimeMillis();
        float scaleFactor = 8;//图片缩放比例；
        float radius = 5;//模糊程度

        Bitmap overlay = Bitmap.createBitmap(
                (int) (view.getMeasuredWidth() / scaleFactor),
                (int) (view.getMeasuredHeight() / scaleFactor),
                Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(overlay);
        canvas.translate(-view.getLeft() / scaleFactor, -view.getTop()/ scaleFactor);
        canvas.scale(1 / scaleFactor, 1 / scaleFactor);
        Paint paint = new Paint();
        paint.setFlags(Paint.FILTER_BITMAP_FLAG);
        canvas.drawBitmap(bkg, 10, 0, paint);


        overlay = FastBlur.doBlur(overlay, (int) radius, true);
        view.setBackground(new BitmapDrawable(mWActivity.getResources(), overlay));
        view.setBackgroundResource(R.drawable.bg9);
//        view.setBackgroundResource(R.color.white);
        /**
         * 打印高斯模糊处理时间，如果时间大约16ms，用户就能感到到卡顿，时间越长卡顿越明显，如果对模糊完图片要求不高，可是将scaleFactor设置大一些。
         */
        Log.i("jerome", "blur time:" + (System.currentTimeMillis() - startMs));
    }


    /**
     * 获取系统状态栏和软件标题栏，部分软件没有标题栏，看自己软件的配置；
     * @return
     */
    private int getOtherHeight() {
        Rect frame = new Rect();
        mWActivity.getWindow().getDecorView().getWindowVisibleDisplayFrame(frame);
        int statusBarHeight = frame.top;
        int contentTop = mWActivity.getWindow().findViewById(Window.ID_ANDROID_CONTENT).getTop();
        int titleBarHeight = contentTop - statusBarHeight;
        return statusBarHeight + titleBarHeight;
    }


    private void reflectEnableClick() {
        try {
            Object mTN;
            mTN = getField(mWXToast, "mTN");
            if (mTN != null) {
                Object mParams = getField(mTN, "mParams");
                if (mParams != null
                        && mParams instanceof WindowManager.LayoutParams) {
                    WindowManager.LayoutParams params = (WindowManager.LayoutParams) mParams;
                    //显示与隐藏动画
//                    params.windowAnimations = R.style.ClickToast;
                    //Toast可点击
                    params.flags = WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON
                            | WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE;
                    //设置viewgroup宽高
                    params.width = WindowManager.LayoutParams.MATCH_PARENT; //设置Toast宽度为屏幕宽度
                    params.height = WindowManager.LayoutParams.WRAP_CONTENT; //设置高度
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * 反射字段
     *
     * @param object    要反射的对象
     * @param fieldName 要反射的字段名称
     */
    private static Object getField(Object object, String fieldName)
            throws NoSuchFieldException, IllegalAccessException {
        Field field = object.getClass().getDeclaredField(fieldName);
        if (field != null) {
            field.setAccessible(true);
            return field.get(object);
        }
        return null;
    }


    public void reflectToast() {
        Field field = null;
        try {
            field = mWXToast.getClass().getDeclaredField("mTN");
            field.setAccessible(true);
            mTN = field.get(mWXToast);
            mShow = mTN.getClass().getDeclaredMethod("show");
            mHide = mTN.getClass().getDeclaredMethod("hide");
            mViewFeild = mTN.getClass().getDeclaredField("mNextView");
            mViewFeild.setAccessible(true);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e1) {
            e1.printStackTrace();
        }
    }

    private void show() {
        try {
            //android4.0以上就要以下处理
            if (Build.VERSION.SDK_INT > 14) {
                Field mNextViewField = mTN.getClass().getDeclaredField("mNextView");
                mNextViewField.setAccessible(true);
                LayoutInflater inflate = (LayoutInflater) App.getAppContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                View v = mWXToast.getView();
                mNextViewField.set(mTN, v);
                Method method = mTN.getClass().getDeclaredMethod("show",  new Class[0]);
                method.invoke(mTN, new Object[]{});
            }
            mShow.invoke(mTN, new Object[]{});
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void hide() {
        try {
            mHide.invoke(mTN, new Object[]{});
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }catch (NullPointerException ex){
            ex.printStackTrace();
        }
    }

    //------------↑-----------悬浮（仿微信通知到来/新消息）Toast------[END]---------↑---------------
}
