package com.module.iviews.popup.baseuse;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Handler;
import android.support.annotation.RequiresApi;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.PopupWindow;

import com.iduo.filterlib.util.KeyboardUtils;

/**
 * CN:      IPopupwindowUse
 * Author： JSYL-DINGCL (dingcl@jsyl.com.cn)
 * Date:   2020/1/6
 * Des:    TODO:自定义PopWindow类，封装了PopWindow的一些常用属性，用Builder模式支持链式调用
 */
public class IPopupwindowUse implements PopupWindow.OnDismissListener{
    private static final String TAG = IPopupwindowUse.class.getSimpleName();
    private static final float DEFAULT_ALPHA = 0.7f;
    private Context mContext;
    private int mWidth;
    private int mHeight;
    private boolean mIsFocusable = true;
    private boolean mIsOutside = true;
    private int mResLayoutId = -1;
    private View mContentView;
    private PopupWindow mPopupWindow;
    private int mAnimationStyle = -1;

    private boolean mClippEnable = true;//default is true
    private boolean mIgnoreCheekPress = false;
    private int mInputMode = -1;
    private PopupWindow.OnDismissListener mOnDismissListener;
    private int mSoftInputMode = -1;
    private boolean mTouchable = true;//default is ture
    private View.OnTouchListener mOnTouchListener;

    private Window mWindow;//当前Activity 的窗口
    /**
     * 弹出PopWindow 背景是否变暗，默认不会变暗。
     */
    private boolean mIsBackgroundDark = false;

    private float mBackgroundDrakValue = 0;// 背景变暗的值，0 - 1
    /**
     * 设置是否允许点击 PopupWindow之外的地方，关闭PopupWindow
     */
    private boolean enableOutsideTouchDisMiss = true;// 默认点击pop之外的地方可以关闭

    private int mOffsetX = 0;
    private int mOffsetY = 0;
    private int mGravity = Gravity.BOTTOM;

    private IPopupwindowUse(Context context){
        mContext = context;
    }

    public int getWidth() {
        return mWidth;
    }

    public int getHeight() {
        return mHeight;
    }

    /**
     *
     * @param anchor
     * @param xOff
     * @param yOff
     * @return
     */
    public IPopupwindowUse showAsDropDown(View anchor, int xOff, int yOff){
        if(mPopupWindow!=null){
            mPopupWindow.showAsDropDown(anchor,xOff,yOff);
        }
        return this;
    }

    public IPopupwindowUse showAsDropDown(View anchor){
        if(mPopupWindow!=null){
            mPopupWindow.showAsDropDown(anchor);
        }
        return this;
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public IPopupwindowUse showAsDropDown(View anchor, int xOff, int yOff, int gravity){
        if(mPopupWindow!=null){
            mPopupWindow.showAsDropDown(anchor,xOff,yOff,gravity);
        }
        return this;
    }


    /**
     * 相对于父控件的位置（通过设置Gravity.CENTER，下方Gravity.BOTTOM等 ），可以设置具体位置坐标
     * @param parent 父控件
     * @param gravity
     * @param x the popup's x location offset
     * @param y the popup's y location offset
     * @return
     */
    public IPopupwindowUse showAtLocation(View parent, int gravity, int x, int y){
        if(mPopupWindow!=null){
            mPopupWindow.showAtLocation(parent,gravity,x,y);
        }
        return this;
    }


    public IPopupwindowUse showPopupWindow(View v, LocationType type) {
        int[] locations = new int[2];
        v.getLocationOnScreen(locations);
        int left = locations[0];
        int top  =  locations[1];
        mPopupWindow.getContentView().measure(
                View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED),
                View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED));
        int popupWidth = mPopupWindow.getContentView().getMeasuredWidth();
        int popupHeight = mPopupWindow.getContentView().getMeasuredHeight();
        switch (type) {
            case TOP_LEFT:
                mPopupWindow.showAtLocation(v,Gravity.NO_GRAVITY,left - popupWidth + mOffsetX,top - popupHeight + mOffsetY);
                break;
            case TOP_CENTER:
                int offsetX = (v.getWidth() - popupWidth) / 2;
                mPopupWindow.showAtLocation(v,Gravity.NO_GRAVITY,left + offsetX + mOffsetX,top - popupHeight + mOffsetY);
                break;
            case TOP_RIGHT:
                mPopupWindow.showAtLocation(v,Gravity.NO_GRAVITY,left + v.getWidth() + mOffsetX,top - popupHeight + mOffsetY);
                break;

            case BOTTOM_LEFT:
                mPopupWindow.showAsDropDown(v, -popupWidth + mOffsetX,mOffsetY);
                break;
            case BOTTOM_CENTER:
                int offsetX1 = (v.getWidth() - popupWidth) / 2;
                mPopupWindow.showAsDropDown(v,offsetX1 + mOffsetX,mOffsetY);
                break;
            case BOTTOM_RIGHT:
                mPopupWindow.showAsDropDown(v, v.getWidth() + mOffsetX,mOffsetY);
                break;

            case LEFT_TOP:
                mPopupWindow.showAtLocation(v, Gravity.NO_GRAVITY, left - popupWidth + mOffsetX, top - popupHeight + mOffsetY);
                break;
            case LEFT_BOTTOM:
                mPopupWindow.showAtLocation(v, Gravity.NO_GRAVITY, left - popupWidth + mOffsetX, top + v.getHeight() + mOffsetY);
                break;
            case LEFT_CENTER:
                int offsetY = (v.getHeight() - popupHeight) / 2;
                mPopupWindow.showAtLocation(v, Gravity.NO_GRAVITY,left - popupWidth + mOffsetX,top + offsetY + mOffsetY);
                break;

            case RIGHT_TOP:
                mPopupWindow.showAtLocation(v, Gravity.NO_GRAVITY, left + v.getWidth() + mOffsetX,top - popupHeight + mOffsetY);
                break;
            case RIGHT_BOTTOM:
                mPopupWindow.showAtLocation(v, Gravity.NO_GRAVITY, left + v.getWidth() + mOffsetX,top + v.getHeight() + mOffsetY);
                break;
            case RIGHT_CENTER:
                int offsetY1 = (v.getHeight() - popupHeight) / 2;
                mPopupWindow.showAtLocation(v, Gravity.NO_GRAVITY,left + v.getWidth() + mOffsetX,top + offsetY1 + mOffsetY);
                break;
            case FROM_BOTTOM:
                mPopupWindow.showAtLocation(v,mGravity,mOffsetX,mOffsetY);
                break;
        }
        return this;
    }

    /**
     * 添加一些属性设置
     * @param popupWindow
     */
    private void apply(PopupWindow popupWindow){
        popupWindow.setClippingEnabled(mClippEnable);
        if(mIgnoreCheekPress){
            popupWindow.setIgnoreCheekPress();
        }
        if(mInputMode!=-1){
            popupWindow.setInputMethodMode(mInputMode);
        }
        if(mSoftInputMode!=-1){
            popupWindow.setSoftInputMode(mSoftInputMode);
        }
        if(mOnDismissListener!=null){
            popupWindow.setOnDismissListener(mOnDismissListener);
        }
        if(mOnTouchListener!=null){
            popupWindow.setTouchInterceptor(mOnTouchListener);
        }
        popupWindow.setTouchable(mTouchable);



    }

    public IPopupwindowUse setOffsetX(int offsetX) {
        this.mOffsetX = offsetX;
        return this;
    }

    public IPopupwindowUse setOffsetY(int offsetY) {
        this.mOffsetY = offsetY;
        return this;
    }

    public IPopupwindowUse setGravity(int gravity) {
        this.mGravity = gravity;
        return this;
    }

    private PopupWindow build(){

        if(mContentView == null){
            mContentView = LayoutInflater.from(mContext).inflate(mResLayoutId,null);
        }

        // 获取当前Activity的window
        Activity activity = (Activity) mContentView.getContext();
        if(activity!=null && mIsBackgroundDark){
            //如果设置的值在0 - 1的范围内，则用设置的值，否则用默认值
            final  float alpha = (mBackgroundDrakValue > 0 && mBackgroundDrakValue < 1) ? mBackgroundDrakValue : DEFAULT_ALPHA;
            mWindow = activity.getWindow();
            WindowManager.LayoutParams params = mWindow.getAttributes();
            params.alpha = alpha;
            mWindow.addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
            mWindow.setAttributes(params);
        }


        if(mWidth != 0 && mHeight!=0 ){
            mPopupWindow = new PopupWindow(mContentView,mWidth,mHeight);
        }else{
            mPopupWindow = new PopupWindow(mContentView, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        }
        if(mAnimationStyle!=-1){
            mPopupWindow.setAnimationStyle(mAnimationStyle);
        }

        apply(mPopupWindow);//设置一些属性

        if(mWidth == 0 || mHeight == 0){
            mPopupWindow.getContentView().measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED);
            //如果外面没有设置宽高的情况下，计算宽高并赋值
            mWidth = mPopupWindow.getContentView().getMeasuredWidth();
            mHeight = mPopupWindow.getContentView().getMeasuredHeight();
        }

        // 添加dissmiss 监听
        mPopupWindow.setOnDismissListener(this);

        //fix 设置  setOutsideTouchable（false）点击外部取消的bug.
        // 判断是否点击PopupWindow之外的地方关闭 popWindow
        if(!enableOutsideTouchDisMiss){
            //注意这三个属性必须同时设置，不然不能disMiss，以下三行代码在Android 4.4 上是可以，然后在Android 6.0以上，下面的三行代码就不起作用了，就得用下面的方法
            mPopupWindow.setFocusable(true);
            mPopupWindow.setOutsideTouchable(false);
            mPopupWindow.setBackgroundDrawable(null);
            //注意下面这三个是contentView 不是PopupWindow
            mPopupWindow.getContentView().setFocusable(true);
            mPopupWindow.getContentView().setFocusableInTouchMode(true);
            mPopupWindow.getContentView().setOnKeyListener(new View.OnKeyListener() {
                @Override
                public boolean onKey(View v, int keyCode, KeyEvent event) {
                    if (keyCode == KeyEvent.KEYCODE_BACK) {
                        mPopupWindow.dismiss();
                        return true;
                    }
                    return false;
                }
            });
            //在Android 6.0以上 ，只能通过拦截事件来解决
            mPopupWindow.setTouchInterceptor(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {

                    final int x = (int) event.getX();
                    final int y = (int) event.getY();

                    if ((event.getAction() == MotionEvent.ACTION_DOWN)
                            && ((x < 0) || (x >= mWidth) || (y < 0) || (y >= mHeight))) {
                        Log.e(TAG,"out side ");
                        Log.e(TAG,"width:"+mPopupWindow.getWidth()+"height:"+mPopupWindow.getHeight()+" x:"+x+" y  :"+y);
                        return true;
                    } else if (event.getAction() == MotionEvent.ACTION_OUTSIDE) {
                        Log.e(TAG,"out side ...");
                        return true;
                    }
                    return false;
                }
            });
        }else{
            mPopupWindow.setFocusable(mIsFocusable);
            mPopupWindow.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            mPopupWindow.setOutsideTouchable(mIsOutside);
        }
        // update
        mPopupWindow.update();

        return mPopupWindow;
    }
    public View getContentView() {
        return mContentView;
    }


    @Override
    public void onDismiss() {
        dissmiss();
    }

    /**
     * 关闭popWindow
     */
    public void dissmiss(){

        if(mOnDismissListener!=null){
            mOnDismissListener.onDismiss();
        }

        //如果设置了背景变暗，那么在dissmiss的时候需要还原
        if(mWindow!=null){
            WindowManager.LayoutParams params = mWindow.getAttributes();
            params.alpha = 1.0f;
            mWindow.setAttributes(params);
        }
        if(mPopupWindow!=null && mPopupWindow.isShowing()){
            mPopupWindow.dismiss();
        }
    }

    public PopupWindow getPopupWindow() {
        return mPopupWindow;
    }

    public static class PopupWindowBuilder{
        private IPopupwindowUse mCustomPopWindow;
        private int offsetX;

        public PopupWindowBuilder(Context context){
            mCustomPopWindow = new IPopupwindowUse(context);
        }
        public PopupWindowBuilder size(int width,int height){
            mCustomPopWindow.mWidth = width;
            mCustomPopWindow.mHeight = height;
            return this;
        }


        public PopupWindowBuilder setFocusable(boolean focusable){
            mCustomPopWindow.mIsFocusable = focusable;
            return this;
        }



        public PopupWindowBuilder setView(int resLayoutId){
            mCustomPopWindow.mResLayoutId = resLayoutId;
            mCustomPopWindow.mContentView = null;
            return this;
        }

        public PopupWindowBuilder setView(View view){
            mCustomPopWindow.mContentView = view;
            mCustomPopWindow.mResLayoutId = -1;
            return this;
        }

        public PopupWindowBuilder setOutsideTouchable(boolean outsideTouchable){
            mCustomPopWindow.mIsOutside = outsideTouchable;
            return this;
        }

        /**
         * 设置弹窗动画
         * @param animationStyle
         * @return
         */
        public PopupWindowBuilder setAnimationStyle(int animationStyle){
            mCustomPopWindow.mAnimationStyle = animationStyle;
            return this;
        }


        public PopupWindowBuilder setClippingEnable(boolean enable){
            mCustomPopWindow.mClippEnable =enable;
            return this;
        }


        public PopupWindowBuilder setIgnoreCheekPress(boolean ignoreCheekPress){
            mCustomPopWindow.mIgnoreCheekPress = ignoreCheekPress;
            return this;
        }

        public PopupWindowBuilder setInputMethodMode(int mode){
            mCustomPopWindow.mInputMode = mode;
            return this;
        }

        public PopupWindowBuilder setOnDissmissListener(PopupWindow.OnDismissListener onDissmissListener){
            mCustomPopWindow.mOnDismissListener = onDissmissListener;
            return this;
        }


        public PopupWindowBuilder setSoftInputMode(int softInputMode){
            mCustomPopWindow.mSoftInputMode = softInputMode;
            return this;
        }


        public PopupWindowBuilder setTouchable(boolean touchable){
            mCustomPopWindow.mTouchable = touchable;
            return this;
        }

        public PopupWindowBuilder setTouchIntercepter(View.OnTouchListener touchIntercepter){
            mCustomPopWindow.mOnTouchListener = touchIntercepter;
            return this;
        }

        /**
         * 设置背景变暗是否可用
         * @param isDark
         * @return
         */
        public PopupWindowBuilder enableBackgroundDark(boolean isDark){
            mCustomPopWindow.mIsBackgroundDark = isDark;
            return this;
        }

        /**
         * 设置背景变暗的值
         * @param darkValue
         * @return
         */
        public PopupWindowBuilder setBgDarkAlpha(float darkValue){
            mCustomPopWindow.mBackgroundDrakValue = darkValue;
            return this;
        }

        /**
         * 设置是否允许点击 PopupWindow之外的地方，关闭PopupWindow
         * @param disMiss
         * @return
         */
        public PopupWindowBuilder enableOutsideTouchableDissmiss(boolean disMiss){
            mCustomPopWindow.enableOutsideTouchDisMiss = disMiss;
            return this;
        }

        private ShowSoftInputTask showSoftInputTask;
        public PopupWindowBuilder showSoftInput2(View focusView, boolean autoOpenSoftInput){
            if (autoOpenSoftInput) {
                if (showSoftInputTask == null) {
                    showSoftInputTask = new ShowSoftInputTask(focusView);
                } else {
                    focusView.removeCallbacks(showSoftInputTask);
                }
                focusView.postDelayed(showSoftInputTask, 10);
            }
            return this;
        }

        public PopupWindowBuilder autoOpenSoftInput(final Activity context, boolean autoOpenSoftInput){
            if (autoOpenSoftInput) {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        KeyboardUtils.showInputMethod(context);
                    }
                }, 100);
            }else {
                KeyboardUtils.hideSoftInput(context);
            }
            return this;
        }


        public IPopupwindowUse create(){
            //构建PopWindow
            mCustomPopWindow.build();
            return mCustomPopWindow;
        }




    }

    public enum LocationType {
        TOP_LEFT,
        TOP_RIGHT,
        TOP_CENTER,

        BOTTOM_LEFT,
        BOTTOM_RIGHT,
        BOTTOM_CENTER,

        RIGHT_TOP,
        RIGHT_BOTTOM,
        RIGHT_CENTER,

        LEFT_TOP,
        LEFT_BOTTOM,
        LEFT_CENTER,

        FROM_BOTTOM,
    }

    static class ShowSoftInputTask implements Runnable {
        View focusView;
        boolean isDone = false;

        public ShowSoftInputTask(View focusView) {
            this.focusView = focusView;
        }

        @Override
        public void run() {
            if (focusView != null && !isDone) {
                isDone = true;
                KeyboardUtils.showSoftInput(focusView);
            }
        }
    }

}
