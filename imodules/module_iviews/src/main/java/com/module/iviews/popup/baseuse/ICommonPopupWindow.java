package com.module.iviews.popup.baseuse;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.PopupWindow;
import android.widget.PopupWindow.OnDismissListener;

/**
 * 通用PopupWindow
 */
public class ICommonPopupWindow {

    private Context mContext;
    private PopupWindow mPopupWindow;
    private Drawable mBackgroundDrawable;
    private OnDismissListener mOnDismissListener;
    private boolean mOutsideTouchable;
    private int mAnimationStyle;
    private int mWidth;
    private View mView;
    private int mLayoutId;
    private int mHeight;
    private int mOffsetX;
    private int mOffsetY;
    private int mGravity;
    private boolean mTouchable;

    private ICommonPopupWindow() {}

    public void showPopupWindow(View v, LocationType type) {
        if (mView!=null){
            mPopupWindow.setContentView(mView);
        }else if (mLayoutId != -1){
            View contentView = LayoutInflater.from(mContext).inflate(mLayoutId, null);
            mPopupWindow.setContentView(contentView);
        }
        mPopupWindow.setWidth(mWidth);
        mPopupWindow.setHeight(mHeight);
        mPopupWindow.setBackgroundDrawable(mBackgroundDrawable);
        mPopupWindow.setOutsideTouchable(mOutsideTouchable);
        mPopupWindow.setOnDismissListener(mOnDismissListener);
        mPopupWindow.setAnimationStyle(mAnimationStyle);
        mPopupWindow.setTouchable(mTouchable);

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
    }

    public void dismiss(){
        if (mPopupWindow!=null){
            mPopupWindow.dismiss();
        }
    }

    public static class Builder {
        private Context context;
        private View contentView;
        private int layoutId;
        private PopupWindow popupWindow;
        private boolean outsideTouchable;
        private Drawable backgroundDrawable;
        private OnDismissListener onDismissListener;
        private int animationStyle;
        private int width;
        private int height;
        private int offsetX;
        private int offsetY;
        private int gravity;
        private boolean touchable;

        public Builder(Context context) {
            this.context = context;
            this.popupWindow = new PopupWindow(context);
            this.outsideTouchable = true;
            this.touchable = true;
            this.backgroundDrawable = new ColorDrawable(Color.TRANSPARENT);
            this.width  = WindowManager.LayoutParams.WRAP_CONTENT;
            this.height = WindowManager.LayoutParams.WRAP_CONTENT;
            this.gravity = Gravity.CENTER;
            this.layoutId = -1;
            this.offsetX = 0;
            this.offsetY = 0;
        }

        public Builder setContentView(View contentView) {
            this.contentView = contentView;
            return this;
        }

        public Builder setLayoutId(int layoutId) {
            this.layoutId = layoutId;
            return this;
        }

        public Builder setBackgroundDrawable(Drawable backgroundDrawable) {
            this.backgroundDrawable = backgroundDrawable;
            return this;
        }

        public Builder setWidth(int width) {
            this.width = width;
            return this;
        }

        public Builder setOutsideTouchable(boolean outsideTouchable) {
            this.outsideTouchable = outsideTouchable;
            return this;
        }

        public Builder setHeight(int height) {
            this.height = height;
            return this;
        }

        public Builder setOffsetX(int offsetX) {
            this.offsetX = offsetX;
            return this;
        }

        public Builder setOffsetY(int offsetY) {
            this.offsetY = offsetY;
            return this;
        }

        public Builder setGravity(int gravity) {
            this.gravity = gravity;
            return this;
        }

        public Builder setOnDismissListener(OnDismissListener onDismissListener) {
            this.onDismissListener = onDismissListener;
            return this;
        }

        public Builder setAnimationStyle(int animationStyle) {
            this.animationStyle = animationStyle;
            return this;
        }

        public Builder setTouchable(boolean touchable) {
            this.touchable = touchable;
            return this;
        }

        public ICommonPopupWindow build() {
            ICommonPopupWindow popupWindow = new ICommonPopupWindow();
            setPopupWindowConfig(popupWindow);
            return popupWindow;
        }

        private void setPopupWindowConfig(ICommonPopupWindow window) {
            if (contentView != null && layoutId != -1){
                throw new IException("setContentView and setLayoutId can't be used together.", "0");
            }else if (contentView == null && layoutId == -1){
                throw new IException("contentView or layoutId can't be null.", "1");
            }

            if (context == null) {
                throw new IException("context can't be null.", "2");
            } else {
                window.mContext = this.context;
            }

            window.mWidth  = this.width;
            window.mHeight = this.height;
            window.mView = this.contentView;
            window.mLayoutId = layoutId;
            window.mPopupWindow = this.popupWindow;
            window.mOutsideTouchable   = this.outsideTouchable;
            window.mBackgroundDrawable = this.backgroundDrawable;
            window.mOnDismissListener  = this.onDismissListener;
            window.mAnimationStyle = this.animationStyle;
            window.mTouchable = this.touchable;
            window.mOffsetX = this.offsetX;
            window.mOffsetY = this.offsetY;
            window.mGravity = this.gravity;
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

    static class IException extends RuntimeException{
        private String errorCode;
        public IException(String errorCode) {
            this.errorCode = errorCode;
        }
        public IException(String message, String errorCode) {
            super(message);
            this.errorCode = errorCode;
        }
        public String getErrorCode() {
            return errorCode;
        }
        public void setErrorCode(String errorCode) {
            this.errorCode = errorCode;
        }
        @Override
        public String toString() {
            return super.toString()+",MException{" +
                    "errorCode='" + errorCode + '\'' +
                    '}';
        }
    }
}
