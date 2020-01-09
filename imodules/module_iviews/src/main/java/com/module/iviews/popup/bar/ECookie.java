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

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.module.ires.bean.utils.EDensityUtils;
import com.module.ires.bean.utils.EThemeUtils;
import com.module.iviews.R;

/**
 * CN:      ECookie
 * Author： JSYL-DINGCL (dingcl@jsyl.com.cn)
 * Date:   2020/1/8
 * Des:    TODO:
 */
final class ECookie extends LinearLayout {

    /**
     * 默认持续时间（ms）
     */
    public static final long DEFAULT_COOKIE_DURATION = 2000;

    private Animation mSlideInAnimation;
    private Animation mSlideOutAnimation;

    private LinearLayout mLayoutCookie;
    private TextView mTvTitle;
    private TextView mTvMessage;
    private ImageView mIvIcon;
    private TextView mBtnAction;
    private ImageView mBtnActionWithIcon;
    private long mDuration = DEFAULT_COOKIE_DURATION;
    private int mGravity = Gravity.BOTTOM;

    private int animationInTop;
    private int animationInBottom;
    private int animationOutTop;
    private int animationOutBottom;

    public ECookie(@NonNull final Context context) {
        this(context, null);
    }

    public ECookie(@NonNull final Context context, @Nullable final AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ECookie(@NonNull final Context context, @Nullable final AttributeSet attrs,
                  final int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initViews(context);
    }

    public int getLayoutGravity() {
        return mGravity;
    }

    private void initViews(Context context) {
        inflate(getContext(), R.layout.layout_eui_cookie, this);

        mLayoutCookie = findViewById(R.id.cookie);
        mTvTitle = findViewById(R.id.tv_title);
        mTvMessage = findViewById(R.id.tv_message);
        mIvIcon = findViewById(R.id.iv_icon);
        mBtnAction = findViewById(R.id.btn_action);
        mBtnActionWithIcon = findViewById(R.id.btn_action_with_icon);
        initDefaultStyle(context);
    }

    /**
     * Init the default text color or background color. You can change the default style by set the
     * Theme's attributes.
     *
     * <pre>
     *  <style name="AppTheme" parent="Theme.AppCompat.Light.DarkActionBar">
     *          <item name="cookieTitleColor">@color/default_title_color</item>
     *          <item name="cookieMessageColor">@color/default_message_color</item>
     *          <item name="cookieActionColor">@color/default_action_color</item>
     *          <item name="cookieBackgroundColor">@color/default_bg_color</item>
     *  </style>
     * </pre>
     */
    private void initDefaultStyle(Context context) {
        //Custom the default style of a cookie
        int titleColor = EThemeUtils.resolveColor(context, R.attr.cookieTitleColor, Color.WHITE);
        int messageColor = EThemeUtils.resolveColor(context, R.attr.cookieMessageColor, Color.WHITE);
        int actionColor = EThemeUtils.resolveColor(context, R.attr.cookieActionColor, Color.WHITE);
        int backgroundColor = EThemeUtils.resolveColor(context, R.attr.cookieBackgroundColor,
                ContextCompat.getColor(context, R.color.cookie_bar_default_bg_color));

        mTvTitle.setTextColor(titleColor);
        mTvMessage.setTextColor(messageColor);
        mBtnAction.setTextColor(actionColor);
        mLayoutCookie.setBackgroundColor(backgroundColor);
    }

    public void setParams(final ECookieBar.Params params) {
        if (params != null) {
            mDuration = params.duration;
            mGravity = params.layoutGravity;

            animationInTop = params.animationInTop;
            animationInBottom = params.animationInBottom;
            animationOutTop = params.animationOutTop;
            animationOutBottom = params.animationOutBottom;

            //Icon
            if (params.iconResId != 0) {
                mIvIcon.setVisibility(VISIBLE);
                mIvIcon.setBackgroundResource(params.iconResId);
            }

            //Title
            if (!TextUtils.isEmpty(params.title)) {
                mTvTitle.setVisibility(VISIBLE);
                mTvTitle.setText(params.title);
                if (params.titleColor != 0) {
                    mTvTitle.setTextColor(ContextCompat.getColor(getContext(), params.titleColor));
                }
            }

            //Message
            if (!TextUtils.isEmpty(params.message)) {
                mTvMessage.setVisibility(VISIBLE);
                mTvMessage.setText(params.message);
                if (params.messageColor != 0) {
                    mTvMessage.setTextColor(ContextCompat.getColor(getContext(), params.messageColor));
                }

                if (TextUtils.isEmpty(params.title)) {
                    LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) mTvMessage
                            .getLayoutParams();
                    layoutParams.topMargin = 0;
                }
            }

            //Action
            if ((!TextUtils.isEmpty(params.action) || params.actionIcon != 0)
                    && params.onActionClickListener != null) {
                mBtnAction.setVisibility(VISIBLE);
                mBtnAction.setText(params.action);
                mBtnAction.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        params.onActionClickListener.onClick(view);
                        dismiss();
                    }
                });

                //Action Color
                if (params.actionColor != 0) {
                    mBtnAction.setTextColor(ContextCompat.getColor(getContext(), params.actionColor));
                }
            }

            if (params.actionIcon != 0 && params.onActionClickListener != null) {
                mBtnAction.setVisibility(GONE);
                mBtnActionWithIcon.setVisibility(VISIBLE);
                mBtnActionWithIcon.setBackgroundResource(params.actionIcon);
                mBtnActionWithIcon.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        params.onActionClickListener.onClick(view);
                        dismiss();
                    }
                });
            }

            //Background
            if (params.backgroundColor != 0) {
                mLayoutCookie
                        .setBackgroundColor(ContextCompat.getColor(getContext(), params.backgroundColor));
            }

            int padding = EThemeUtils.resolveDimension(getContext(), R.attr.eui_config_content_spacing_horizontal);
            if (mGravity == Gravity.BOTTOM) {
//                mLayoutCookie.setPadding(padding, padding, padding, padding);
                mLayoutCookie.setPadding(EDensityUtils.dp2px(getContext(),16), EDensityUtils.dp2px(getContext(),16), EDensityUtils.dp2px(getContext(),16), EDensityUtils.dp2px(getContext(),16));
            }

            createInAnim();
            createOutAnim();
        }
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        if (mGravity == Gravity.TOP) {
            super.onLayout(changed, l, 0, r, mLayoutCookie.getMeasuredHeight());
        } else {
            super.onLayout(changed, l, t, r, b);
        }
    }

    private void createInAnim() {
//        mSlideInAnimation = AnimationUtils.loadAnimation(getContext(),
//                mGravity == Gravity.BOTTOM ? R.anim.cookiebar_slide_in_from_bottom : R.anim.cookiebar_slide_in_from_top);
        int animationResId = mGravity == Gravity.BOTTOM ? animationInBottom : animationInTop;
        Animation mSlideInAnimation = AnimationUtils.loadAnimation(getContext(), animationResId);


        mSlideInAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                if (mDuration < 0) {
                    return;
                }
                postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        dismiss();
                    }
                }, mDuration);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        setAnimation(mSlideInAnimation);
    }

    private void createOutAnim() {
//        mSlideOutAnimation = AnimationUtils.loadAnimation(getContext(),
//                mGravity == Gravity.BOTTOM ? R.anim.cookiebar_slide_out_to_bottom : R.anim.cookiebar_slide_out_to_top);

        int animationResId = mGravity == Gravity.BOTTOM ? animationOutBottom : animationOutTop;
        mSlideOutAnimation = AnimationUtils.loadAnimation(getContext(), animationResId);
        mSlideOutAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }

    /**
     * 消失
     */
    public void dismiss() {
        mSlideOutAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(final Animation animation) {
            }

            @Override
            public void onAnimationEnd(final Animation animation) {
                destroy();
            }

            @Override
            public void onAnimationRepeat(final Animation animation) {
            }
        });
        startAnimation(mSlideOutAnimation);
    }

    /**
     * 销毁
     */
    private void destroy() {
        postDelayed(new Runnable() {
            @Override
            public void run() {
                ViewParent parent = getParent();
                if (parent != null) {
                    ECookie.this.clearAnimation();
                    ((ViewGroup) parent).removeView(ECookie.this);
                }
            }
        }, 200);
    }

}

