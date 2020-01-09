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
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.module.ires.bean.utils.EThemeUtils;
import com.module.iviews.R;

/**
 * CN:      ECookie
 * Author： JSYL-DINGCL (dingcl@jsyl.com.cn)
 * Date:   2020/1/8
 * Des:    TODO:顶部和底部信息消息显示条
 */
public class ECookie11 extends LinearLayout implements View.OnTouchListener {

    /**
     * 默认持续时间（ms）
     */
    public static final long DEFAULT_COOKIE_DURATION = 2000;

    private Animation mSlideInAnimation;
    private Animation mSlideOutAnimation;

    private ViewGroup mLayoutCookie;
    private TextView mTvTitle;
    private TextView mTvMessage;
    private ImageView mIvIcon;
    private TextView mBtnAction;
    private ImageView mBtnActionWithIcon;
    private long mDuration = DEFAULT_COOKIE_DURATION;
    private int mGravity;

    private int animationInTop;
    private int animationOutTop;
    private int animationInBottom;
    private int animationOutBottom;

    private boolean isAutoDismissEnabled;


    public ECookie11(@NonNull final Context context, @Nullable final AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ECookie11(@NonNull final Context context, @Nullable final AttributeSet attrs,
                     final int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public ECookie11(@NonNull final Context context, ECookieBar1111.Params params) {
        super(context);
        initViews(params.customViewResource, params.viewInitializer);

    }

    public int getLayoutGravity() {
        return mGravity;
    }

    private void initViews(@LayoutRes int rootView, ECookieBar1111.CustomViewInitializer viewInitializer) {
//        inflate(getContext(), R.layout.layout_eui_cookie, this);
        if (rootView != 0) {
            inflate(getContext(), rootView, this);
            if (viewInitializer != null) {
                viewInitializer.initView(getChildAt(0));
            }
        } else {
            inflate(getContext(), R.layout.layout_eui_cookie, this);
        }

        if (getChildAt(0).getLayoutParams() instanceof LinearLayout.LayoutParams) {
            LinearLayout.LayoutParams lp = (LayoutParams) (getChildAt(0).getLayoutParams());
            lp.gravity = Gravity.TOP;
        }

        mLayoutCookie = findViewById(R.id.cookie);
        mTvTitle = findViewById(R.id.tv_title);
        mTvMessage = findViewById(R.id.tv_message);
        mIvIcon = findViewById(R.id.iv_icon);
        mBtnAction = findViewById(R.id.btn_action);
        mBtnActionWithIcon = findViewById(R.id.btn_action_with_icon);
        initDefaultStyle(getContext());
        if(rootView == 0) {
            validateLayoutIntegrity();
            initDefaultStyle(getContext());
        }
        mLayoutCookie.setOnTouchListener(this);
    }

    private void validateLayoutIntegrity() {
        if (mLayoutCookie == null || mTvTitle == null || mTvMessage == null ||
                mIvIcon == null || mBtnAction == null) {

            throw new RuntimeException("Your custom cookie view is missing one of the default required views");
        }
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
                ContextCompat.getColor(context, R.color.notificationError));

        mTvTitle.setTextColor(titleColor);
        mTvMessage.setTextColor(messageColor);
        mBtnAction.setTextColor(actionColor);
        mLayoutCookie.setBackgroundColor(backgroundColor);
    }

    public void setParams(final ECookieBar1111.Params params) {
        if (params != null) {
            animationInTop = params.animationInTop;
            animationInBottom = params.animationInBottom;
            animationOutTop = params.animationOutTop;
            animationOutBottom = params.animationOutBottom;

            mDuration = params.duration;
            mGravity = params.layoutGravity;

            isAutoDismissEnabled = params.enableAutoDismiss;

            //Icon
            if (params.iconResId != 0) {
                mIvIcon.setVisibility(VISIBLE);
                mIvIcon.setBackgroundResource(params.iconResId);
            }

            //Icon Animate
            if (params.iconResId != 0 && mIvIcon != null) {
                mIvIcon.setVisibility(VISIBLE);
                mIvIcon.setBackgroundResource(params.iconResId);
                if (params.iconAnimator != null) {
                    params.iconAnimator.setTarget(mIvIcon);
                    params.iconAnimator.start();
                }
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
                mLayoutCookie.setPadding(padding, padding, padding, padding);
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
        mSlideInAnimation = AnimationUtils.loadAnimation(getContext(), mGravity == Gravity.BOTTOM ? R.anim.cookiebar_slide_in_from_bottom : R.anim.cookiebar_slide_in_from_top);
//        int animationResId = mGravity == Gravity.BOTTOM ? animationInBottom : animationInTop;
//        mSlideInAnimation = AnimationUtils.loadAnimation(getContext(), animationResId);
        mSlideInAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                if (!isAutoDismissEnabled) {
                    return;
                }
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
//        int animationResId = mGravity == Gravity.BOTTOM ? animationOutBottom : animationOutTop;
//        mSlideOutAnimation = AnimationUtils.loadAnimation(getContext(), animationResId);

        mSlideOutAnimation = AnimationUtils.loadAnimation(getContext(),
                mGravity == Gravity.BOTTOM ? R.anim.cookiebar_slide_out_to_bottom : R.anim.cookiebar_slide_out_to_top);
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
        setVisibility(View.GONE);
        removeFromParent();
    }

    private void removeFromParent() {
        postDelayed(new Runnable() {
            @Override
            public void run() {
                ViewParent parent = getParent();
                if (parent != null) {
                    ECookie11.this.clearAnimation();
                    ((ViewGroup) parent).removeView(ECookie11.this);
                }
            }
        }, 200);
    }


    @Override
    public boolean onTouch(View view, MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                return true;
            case MotionEvent.ACTION_UP:
                return true;

            case MotionEvent.ACTION_MOVE:
                return true;
            default:
                return false;
        }
    }
}

