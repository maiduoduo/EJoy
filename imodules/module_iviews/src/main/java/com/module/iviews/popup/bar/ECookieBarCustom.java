package com.module.iviews.popup.bar;
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

import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.app.Activity;
import android.support.annotation.AnimRes;
import android.support.annotation.AnimatorRes;
import android.support.annotation.ColorRes;
import android.support.annotation.DrawableRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.StringRes;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;

import com.module.iviews.R;


import java.lang.ref.WeakReference;

import static com.module.iviews.popup.bar.ECookieCustom.DEFAULT_COOKIE_DURATION;

/**
 * CN:      ECookieBarCustom
 * Author： JSYL-DINGCL (dingcl@jsyl.com.cn)
 * Date:   2020/1/8
 * Des:    TODO:顶部和底部信息显示条
 *
 * <p>
 * <pre>
 *  new CookieBar
 *      .Builder(MainActivity.this)
 *      .setTitle("TITLE")
 *      .setMessage("MESSAGE")
 *      .setAction("ACTION", new OnActionClickListener() {})
 *      .show();
 *  </pre>
 * <p>
 */
public class ECookieBarCustom {
    private static ECookieCustom mCookieView;
    private WeakReference<Activity> mActivityWeakRef;
//    public static final int TOP = Gravity.TOP;
//    public static final int BOTTOM = Gravity.BOTTOM;

    private ECookieBarCustom() {
    }

    private ECookieBarCustom(Activity activity, Params params) {
        mActivityWeakRef = new WeakReference<>(activity);
        if (params == null) {
            // since params is null, this CookieBar object can only be used to dismiss
            // existing cookies
            dismiss();
            return;
        }
        mCookieView = new ECookieCustom(activity,params);
        mCookieView.setParams(params);
    }

    /**
     * 显示
     */
    public void show() {
        if (mCookieView != null && getActivity() != null) {
            final ViewGroup decorView = (ViewGroup) getActivity().getWindow().getDecorView();
            final ViewGroup content = decorView.findViewById(android.R.id.content);
            if (mCookieView.getParent() == null) {
                if (mCookieView.getLayoutGravity() == Gravity.BOTTOM) {
                    content.addView(mCookieView);
                } else {
                    decorView.addView(mCookieView);
                }
            }
        }
    }


    /**
     * 消失
     */
    public static void dismiss() {
        if (mCookieView != null) {
            mCookieView.dismiss();
        }
    }

    /**
     * 获取 Activity
     *
     * @return
     */
    public Activity getActivity() {
        if (mActivityWeakRef != null && mActivityWeakRef.get() != null) {
            return mActivityWeakRef.get();
        } else {
            return null;
        }
    }

    /**
     * 获取构建者
     *
     * @param activity
     * @return
     */
    public static Builder builder(Activity activity) {
        return new Builder(activity);
    }

    public static class Builder {
        private Params params = new Params();

        public Activity context;

        /**
         * Create a builder for an cookie.
         */
        public Builder(Activity activity) {
            this.context = activity;
        }

        public Builder setIcon(@DrawableRes int iconResId) {
            params.iconResId = iconResId;
            return this;
        }

        public Builder setTitle(String title) {
            params.title = title;
            return this;
        }

        public Builder setTitle(@StringRes int resId) {
            params.title = context.getString(resId);
            return this;
        }

        public Builder setMessage(String message) {
            params.message = message;
            return this;
        }

        public Builder setMessage(@StringRes int resId) {
            params.message = context.getString(resId);
            return this;
        }

        public Builder setDuration(long duration) {
            params.duration = duration;
            return this;
        }

        public Builder setTitleColor(@ColorRes int titleColor) {
            params.titleColor = titleColor;
            return this;
        }

        public Builder setMessageColor(@ColorRes int messageColor) {
            params.messageColor = messageColor;
            return this;
        }

        public Builder setBackgroundColor(@ColorRes int backgroundColor) {
            params.backgroundColor = backgroundColor;
            return this;
        }

        public Builder setActionColor(@ColorRes int actionColor) {
            params.actionColor = actionColor;
            return this;
        }

        public Builder setAction(String action, View.OnClickListener onActionClickListener) {
            params.action = action;
            params.onActionClickListener = onActionClickListener;
            return this;
        }

        public Builder setAction(@StringRes int resId, View.OnClickListener onActionClickListener) {
            params.action = context.getString(resId);
            params.onActionClickListener = onActionClickListener;
            return this;
        }

        public Builder setActionWithIcon(@DrawableRes int resId,
                                         View.OnClickListener onActionClickListener) {
            params.actionIcon = resId;
            params.onActionClickListener = onActionClickListener;
            return this;
        }

        public Builder setCookiePosition(int layoutGravity) {
            params.layoutGravity = layoutGravity;
            return this;
        }

        public ECookieBarCustom create() {
            return new ECookieBarCustom(context, params);
        }

        public ECookieBarCustom show() {
            final ECookieBarCustom cookie = create();
            cookie.show();
            return cookie;
        }

        public ECookieBarCustom.Builder setAnimationIn(@AnimRes int topAnimation, @AnimRes int bottomAnimation) {
            params.animationInTop = topAnimation;
            params.animationInBottom = bottomAnimation;
            return this;
        }

        public ECookieBarCustom.Builder setAnimationOut(@AnimRes int topAnimation, @AnimRes int bottomAnimation) {
            params.animationOutTop = topAnimation;
            params.animationOutBottom = bottomAnimation;
            return this;
        }

        public ECookieBarCustom.Builder setIconAnimation(@AnimatorRes int iconAnimation) {
            params.iconAnimator = (AnimatorSet) AnimatorInflater.loadAnimator(context, iconAnimation);
            return this;
        }

        public ECookieBarCustom.Builder setCustomView(@LayoutRes int customView) {
            params.customViewResource = customView;
            return this;
        }

        public ECookieBarCustom.Builder setCustomViewInitializer(CustomViewInitializer viewInitializer) {
            params.viewInitializer = viewInitializer;
            return this;
        }

        public ECookieBarCustom.Builder setEnableAutoDismiss(boolean enableAutoDismiss) {
            params.enableAutoDismiss = enableAutoDismiss;
            return this;
        }
    }

    final static class Params {

        public int animationInTop = R.anim.anim_cookiebar_slide_in_from_top;
        public int animationInBottom = R.anim.anim_cookiebar_slide_in_from_bottom;
        public int animationOutTop = R.anim.anim_cookiebar_slide_out_to_top;
        public int animationOutBottom = R.anim.anim_cookiebar_slide_out_to_bottom;
        public AnimatorSet iconAnimator;
        public int customViewResource;
        public CustomViewInitializer viewInitializer;
        public boolean enableAutoDismiss = true;
        /**
         * 布局对齐方式
         */
        public int layoutGravity;

        /**
         * 标题
         */
        public String title;

        /**
         * 文字信息
         */
        public String message;

        /**
         * 按钮文字
         */
        public String action;

        /**
         * 按钮图片资源
         */
        public int actionIcon;

        /**
         * 按钮点击时间
         */
        public View.OnClickListener onActionClickListener;

        /**
         * 左侧图标资源
         */
        public int iconResId;

        /**
         * 背景颜色
         */
        public int backgroundColor;

        /**
         * 标题文字颜色
         */
        public int titleColor;

        /**
         * 提示信息文字颜色
         */
        public int messageColor;

        /**
         * 按钮文字颜色
         */
        public int actionColor;

        /**
         * 显示持续时间
         */
        public long duration = DEFAULT_COOKIE_DURATION;



    }

    public interface CustomViewInitializer {
        void initView(View view);
    }
}
