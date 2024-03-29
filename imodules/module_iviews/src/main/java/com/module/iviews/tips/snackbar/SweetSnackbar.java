/*
 * Copyright (C) 2015 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.module.iviews.tips.snackbar;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.annotation.AnimRes;
import android.support.annotation.ColorInt;
import android.support.annotation.IntDef;
import android.support.annotation.IntRange;
import android.support.annotation.NonNull;
import android.support.annotation.StringRes;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.SwipeDismissBehavior;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.WindowInsetsCompat;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.accessibility.AccessibilityManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.module.iviews.R;
import com.module.iviews.tips.util.TipsAnimationUtils;
import com.module.iviews.tips.util.TipsThemeUtils;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * 基于Design包下的Snackbar修改而来,可自定义 入场动画 及 出场动画
 *      功能点:
 *          设置SweetSnackbar实例的 入场动画 及 离场动画:{@link #setAnimations(int, int)}
 *收藏自：
 * https://www.jianshu.com/p/26dfafc5410f
 */
public final class SweetSnackbar {
    private static final String TAG1 = "SweetTip";

    /**
     * Callback class for {@link SweetSnackbar} instances.
     *
     * @see SweetSnackbar#setCallback(Callback)
     */
    public static abstract class Callback {
        /** Indicates that the SweetSnackbar was dismissed via a swipe.*/
        public static final int DISMISS_EVENT_SWIPE = 0;
        /** Indicates that the SweetSnackbar was dismissed via an action click.*/
        public static final int DISMISS_EVENT_ACTION = 1;
        /** Indicates that the SweetSnackbar was dismissed via a timeout.*/
        public static final int DISMISS_EVENT_TIMEOUT = 2;
        /** Indicates that the SweetSnackbar was dismissed via a call to {@link #dismiss()}.*/
        public static final int DISMISS_EVENT_MANUAL = 3;
        /** Indicates that the SweetSnackbar was dismissed from a new SweetSnackbar being shown.*/
        public static final int DISMISS_EVENT_CONSECUTIVE = 4;

        /** @hide */
        @IntDef({DISMISS_EVENT_SWIPE, DISMISS_EVENT_ACTION, DISMISS_EVENT_TIMEOUT,
                DISMISS_EVENT_MANUAL, DISMISS_EVENT_CONSECUTIVE})
        @Retention(RetentionPolicy.SOURCE)
        public @interface DismissEvent {}

        /**
         * Called when the given {@link SweetSnackbar} has been dismissed, either through a time-out,
         * having been manually dismissed, or an action being clicked.
         *
         * @param snackbar The snackbar which has been dismissed.
         * @param event The event which caused the dismissal. One of either:
         *              {@link #DISMISS_EVENT_SWIPE}, {@link #DISMISS_EVENT_ACTION},
         *              {@link #DISMISS_EVENT_TIMEOUT}, {@link #DISMISS_EVENT_MANUAL} or
         *              {@link #DISMISS_EVENT_CONSECUTIVE}.
         *
         * @see SweetSnackbar#dismiss()
         */
        public void onDismissed(SweetSnackbar snackbar, @DismissEvent int event) {
            // empty
        }

        /**
         * Called when the given {@link SweetSnackbar} is visible.
         *
         * @param snackbar The snackbar which is now visible.
         * @see SweetSnackbar#show()
         */
        public void onShown(SweetSnackbar snackbar) {
            // empty
        }
    }

    /**
     * @hide
     */
    @IntDef({LENGTH_INDEFINITE, LENGTH_SHORT, LENGTH_LONG})
    @IntRange(from = 1)
    @Retention(RetentionPolicy.SOURCE)
    public @interface Duration {}

    /**
     * Show the SweetSnackbar indefinitely. This means that the SweetSnackbar will be displayed from the time
     * that is {@link #show() shown} until either it is dismissed, or another SweetSnackbar is shown.
     *
     * @see #setDuration
     */
    public static final int LENGTH_INDEFINITE = -2;

    /**
     * Show the SweetSnackbar for a short period of time.
     *
     * @see #setDuration
     */
    public static final int LENGTH_SHORT = -1;

    /**
     * Show the SweetSnackbar for a long period of time.
     *
     * @see #setDuration
     */
    public static final int LENGTH_LONG = 0;

    static final int ANIMATION_DURATION = 250;
    static final int ANIMATION_FADE_DURATION = 180;

    static final Handler sHandler;
    static final int MSG_SHOW = 0;
    static final int MSG_DISMISS = 1;

    static {
        sHandler = new Handler(Looper.getMainLooper(), new Handler.Callback() {
            @Override
            public boolean handleMessage(Message message) {
                switch (message.what) {
                    case MSG_SHOW:
                        ((SweetSnackbar) message.obj).showView();
                        return true;
                    case MSG_DISMISS:
                        ((SweetSnackbar) message.obj).hideView(message.arg1);
                        return true;
                }
                return false;
            }
        });
    }

    private final ViewGroup mTargetParent;
    private final Context mContext;
    final SnackbarLayout mView;
    private int mDuration;
    private Callback mCallback;

    private final AccessibilityManager mAccessibilityManager;

    private SweetSnackbar(ViewGroup parent) {
        mTargetParent = parent;
        mContext = parent.getContext();

        TipsThemeUtils.checkAppCompatTheme(mContext);

        LayoutInflater inflater = LayoutInflater.from(mContext);
        mView = (SnackbarLayout) inflater.inflate(R.layout.layout_sweet_snackbar, mTargetParent, false);

        mAccessibilityManager = (AccessibilityManager)
                mContext.getSystemService(Context.ACCESSIBILITY_SERVICE);
    }

    /**
     * Make a SweetSnackbar to display a message
     *
     * <p>Snackbar will try and find a parent view to hold SweetSnackbar's view from the value given
     * to {@code view}. SweetSnackbar will walk up the view tree trying to find a suitable parent,
     * which is defined as a {@link CoordinatorLayout} or the window decor's content view,
     * whichever comes first.
     *
     * <p>Having a {@link CoordinatorLayout} in your view hierarchy allows SweetSnackbar to enable
     * certain features, such as swipe-to-dismiss and automatically moving of widgets like
     * {@link FloatingActionButton}.
     *
     * @param view     The view to find a parent from.
     * @param text     The text to show.  Can be formatted text.
     * @param duration How long to display the message.  Either {@link #LENGTH_SHORT} or {@link
     *                 #LENGTH_LONG}
     */
    @NonNull
    public static SweetSnackbar make(@NonNull View view, @NonNull CharSequence text,
                                     @Duration int duration) {
        SweetSnackbar snackbar = new SweetSnackbar(findSuitableParent(view));
        snackbar.setText(text);
        snackbar.setDuration(duration);
        return snackbar;
    }

    /**
     * Make a SweetSnackbar to display a message.
     *
     * <p>Snackbar will try and find a parent view to hold SweetSnackbar's view from the value given
     * to {@code view}. SweetSnackbar will walk up the view tree trying to find a suitable parent,
     * which is defined as a {@link CoordinatorLayout} or the window decor's content view,
     * whichever comes first.
     *
     * <p>Having a {@link CoordinatorLayout} in your view hierarchy allows SweetSnackbar to enable
     * certain features, such as swipe-to-dismiss and automatically moving of widgets like
     * {@link FloatingActionButton}.
     *
     * @param view     The view to find a parent from.
     * @param resId    The resource id of the string resource to use. Can be formatted text.
     * @param duration How long to display the message.  Either {@link #LENGTH_SHORT} or {@link
     *                 #LENGTH_LONG}
     */
    @NonNull
    public static SweetSnackbar make(@NonNull View view, @StringRes int resId, @Duration int duration) {
        return make(view, view.getResources().getText(resId), duration);
    }

    private static ViewGroup findSuitableParent(View view) {
        ViewGroup fallback = null;
        do {
            if (view instanceof CoordinatorLayout) {
                // We've found a CoordinatorLayout, use it
                return (ViewGroup) view;
            } else if (view instanceof FrameLayout) {
                if (view.getId() == android.R.id.content) {
                    // If we've hit the decor content view, then we didn't find a CoL in the
                    // hierarchy, so use it.
                    return (ViewGroup) view;
                } else {
                    // It's not the content view but we'll use it as our fallback
                    fallback = (ViewGroup) view;
                }
            }

            if (view != null) {
                // Else, we will loop and crawl up the view hierarchy and try to find a parent
                final ViewParent parent = view.getParent();
                view = parent instanceof View ? (View) parent : null;
            }
        } while (view != null);

        // If we reach here then we didn't find a CoL or a suitable content view so we'll fallback
        return fallback;
    }

    /**
     * Set the action to be displayed in this {@link SweetSnackbar}.
     *
     * @param resId    String resource to display
     * @param listener callback to be invoked when the action is clicked
     */
    @NonNull
    public SweetSnackbar setAction(@StringRes int resId, View.OnClickListener listener) {
        return setAction(mContext.getText(resId), listener);
    }

    /**
     * Set the action to be displayed in this {@link SweetSnackbar}.
     *
     * @param text     Text to display
     * @param listener callback to be invoked when the action is clicked
     */
    @NonNull
    public SweetSnackbar setAction(CharSequence text, final View.OnClickListener listener) {
        final TextView tv = mView.getActionView();

        if (TextUtils.isEmpty(text) || listener == null) {
            tv.setVisibility(View.GONE);
            tv.setOnClickListener(null);
        } else {
            tv.setVisibility(View.VISIBLE);
            tv.setText(text);
            tv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onClick(view);
                    // Now dismiss the SweetSnackbar
                    dispatchDismiss(Callback.DISMISS_EVENT_ACTION);
                }
            });
        }
        return this;
    }

    /**
     * Sets the text color of the action specified in
     * {@link #setAction(CharSequence, View.OnClickListener)}.
     */
    @NonNull
    public SweetSnackbar setActionTextColor(ColorStateList colors) {
        final TextView tv = mView.getActionView();
        tv.setTextColor(colors);
        return this;
    }

    /**
     * Sets the text color of the action specified in
     * {@link #setAction(CharSequence, View.OnClickListener)}.
     */
    @NonNull
    public SweetSnackbar setActionTextColor(@ColorInt int color) {
        final TextView tv = mView.getActionView();
        tv.setTextColor(color);
        return this;
    }

    /**
     * Update the text in this {@link SweetSnackbar}.
     *
     * @param message The new text for the Toast.
     */
    @NonNull
    public SweetSnackbar setText(@NonNull CharSequence message) {
        final TextView tv = mView.getMessageView();
        tv.setText(message);
        return this;
    }

    /**
     * Update the text in this {@link SweetSnackbar}.
     *
     * @param resId The new text for the Toast.
     */
    @NonNull
    public SweetSnackbar setText(@StringRes int resId) {
        return setText(mContext.getText(resId));
    }

    /**
     * Set how long to show the view for.
     *
     * @param duration either be one of the predefined lengths:
     *                 {@link #LENGTH_SHORT}, {@link #LENGTH_LONG}, or a custom duration
     *                 in milliseconds.
     */
    @NonNull
    public SweetSnackbar setDuration(@Duration int duration) {
        mDuration = duration;
        return this;
    }

    /**
     * Return the duration.
     *
     * @see #setDuration
     */
    @Duration
    public int getDuration() {
        return mDuration;
    }

    /**
     * Returns the {@link SweetSnackbar}'s view.
     */
    @NonNull
    public View getView() {
        return mView;
    }

    /**
     * Show the {@link SweetSnackbar}.
     */
    public void show() {
        SweetSnackbarManager.getInstance().show(mDuration, mManagerCallback);
    }

    /**
     * Dismiss the {@link SweetSnackbar}.
     */
    public void dismiss() {
        dispatchDismiss(Callback.DISMISS_EVENT_MANUAL);
    }

    void dispatchDismiss(@Callback.DismissEvent int event) {
        SweetSnackbarManager.getInstance().dismiss(mManagerCallback, event);
    }

    /**
     * Set a callback to be called when this the visibility of this {@link SweetSnackbar} changes.
     */
    @NonNull
    public SweetSnackbar setCallback(Callback callback) {
        mCallback = callback;
        return this;
    }

    /**
     * Return whether this {@link SweetSnackbar} is currently being shown.
     */
    public boolean isShown() {
        return SweetSnackbarManager.getInstance().isCurrent(mManagerCallback);
    }

    /**
     * Returns whether this {@link SweetSnackbar} is currently being shown, or is queued to be
     * shown next.
     */
    public boolean isShownOrQueued() {
        return SweetSnackbarManager.getInstance().isCurrentOrNext(mManagerCallback);
    }

    final SweetSnackbarManager.Callback mManagerCallback = new SweetSnackbarManager.Callback() {
        @Override
        public void show() {
            sHandler.sendMessage(sHandler.obtainMessage(MSG_SHOW, SweetSnackbar.this));
        }

        @Override
        public void dismiss(int event) {
            sHandler.sendMessage(sHandler.obtainMessage(MSG_DISMISS, event, 0, SweetSnackbar.this));
        }
    };

    final void showView() {
        if (mView.getParent() == null) {
            final ViewGroup.LayoutParams lp = mView.getLayoutParams();

            if (lp instanceof CoordinatorLayout.LayoutParams) {
                // If our LayoutParams are from a CoordinatorLayout, we'll setup our Behavior
                final CoordinatorLayout.LayoutParams clp = (CoordinatorLayout.LayoutParams) lp;

                final Behavior behavior = new Behavior();
                behavior.setStartAlphaSwipeDistance(0.1f);
                behavior.setEndAlphaSwipeDistance(0.6f);
                behavior.setSwipeDirection(SwipeDismissBehavior.SWIPE_DIRECTION_START_TO_END);
                behavior.setListener(new SwipeDismissBehavior.OnDismissListener() {
                    @Override
                    public void onDismiss(View view) {
                        view.setVisibility(View.GONE);
                        dispatchDismiss(Callback.DISMISS_EVENT_SWIPE);
                    }

                    @Override
                    public void onDragStateChanged(int state) {
                        switch (state) {
                            case SwipeDismissBehavior.STATE_DRAGGING:
                            case SwipeDismissBehavior.STATE_SETTLING:
                                // If the view is being dragged or settling, cancel the timeout
                                SweetSnackbarManager.getInstance().cancelTimeout(mManagerCallback);
                                break;
                            case SwipeDismissBehavior.STATE_IDLE:
                                // If the view has been released and is idle, restore the timeout
                                SweetSnackbarManager.getInstance().restoreTimeout(mManagerCallback);
                                break;
                        }
                    }
                });
                clp.setBehavior(behavior);
                // Also set the inset edge so that views can dodge the snackbar correctly
                clp.insetEdge = Gravity.BOTTOM;
            }

            mTargetParent.addView(mView);
        }

        mView.setOnAttachStateChangeListener(new SnackbarLayout.OnAttachStateChangeListener() {
            @Override
            public void onViewAttachedToWindow(View v) {}

            @Override
            public void onViewDetachedFromWindow(View v) {
                if (isShownOrQueued()) {
                    // If we haven't already been dismissed then this event is coming from a
                    // non-user initiated action. Hence we need to make sure that we callback
                    // and keep our state up to date. We need to post the call since removeView()
                    // will call through to onDetachedFromWindow and thus overflow.
                    sHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            onViewHidden(Callback.DISMISS_EVENT_MANUAL);
                        }
                    });
                }
            }
        });

        if (ViewCompat.isLaidOut(mView)) {
            if (shouldAnimate()) {
                // If animations are enabled, animate it in
                animateViewIn();
            } else {
                // Else if anims are disabled just call back now
                onViewShown();
            }
        } else {
            // Otherwise, add one of our layout change listeners and show it in when laid out
            mView.setOnLayoutChangeListener(new SnackbarLayout.OnLayoutChangeListener() {
                @Override
                public void onLayoutChange(View view, int left, int top, int right, int bottom) {
                    mView.setOnLayoutChangeListener(null);

                    if (shouldAnimate()) {
                        // If animations are enabled, animate it in
                        animateViewIn();
                    } else {
                        // Else if anims are disabled just call back now
                        onViewShown();
                    }
                }
            });
        }
    }

    private int animateIn = -1;
    private int animateOut = -1;

    /**
     * 设置SweetSnackbar实例的 入场动画 及 离场动画
     * @param animateIn
     * @param animateOut
     * @return
     */
    public SweetSnackbar setAnimations(@AnimRes int animateIn, @AnimRes int animateOut){
        this.animateIn = animateIn;
        this.animateOut = animateOut;
        return this;
    }
    void animateViewIn() {
        Animation anim = null;
        if(animateIn != -1){
            anim = AnimationUtils.loadAnimation(mView.getContext(),animateIn);
        }else {
            anim = AnimationUtils.loadAnimation(mView.getContext(), android.support.design.R.anim.design_snackbar_in);
        }
        anim.setInterpolator(TipsAnimationUtils.FAST_OUT_SLOW_IN_INTERPOLATOR);
        anim.setDuration(ANIMATION_DURATION);
        anim.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationEnd(Animation animation) {
                onViewShown();
            }

            @Override
            public void onAnimationStart(Animation animation) {}

            @Override
            public void onAnimationRepeat(Animation animation) {}
        });
        mView.startAnimation(anim);
    }

    private void animateViewOut(final int event) {
        Animation anim = null;
        if(animateOut != -1){
            anim = AnimationUtils.loadAnimation(mView.getContext(),animateOut);
        }else {
            anim = AnimationUtils.loadAnimation(mView.getContext(), android.support.design.R.anim.design_snackbar_out);
        }
        anim.setInterpolator(TipsAnimationUtils.FAST_OUT_SLOW_IN_INTERPOLATOR);
        anim.setDuration(ANIMATION_DURATION);
        anim.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationEnd(Animation animation) {
                onViewHidden(event);
            }

            @Override
            public void onAnimationStart(Animation animation) {}

            @Override
            public void onAnimationRepeat(Animation animation) {}
        });
        mView.startAnimation(anim);
    }

    final void hideView(@Callback.DismissEvent final int event) {
        if (shouldAnimate() && mView.getVisibility() == View.VISIBLE) {
            animateViewOut(event);
        } else {
            // If anims are disabled or the view isn't visible, just call back now
            onViewHidden(event);
        }
    }

    void onViewShown() {
        SweetSnackbarManager.getInstance().onShown(mManagerCallback);
        if (mCallback != null) {
            mCallback.onShown(this);
        }
    }

    void onViewHidden(int event) {
        // First tell the SweetSnackbarManager that it has been dismissed
        SweetSnackbarManager.getInstance().onDismissed(mManagerCallback);
        // Now call the dismiss listener (if available)
        if (mCallback != null) {
            mCallback.onDismissed(this, event);
        }
        if (Build.VERSION.SDK_INT < 11) {
            // We need to hide the SweetSnackbar on pre-v11 since it uses an old style Animation.
            // ViewGroup has special handling in removeView() when getAnimation() != null in
            // that it waits. This then means that the calculated insets are wrong and the
            // any dodging views do not return. We workaround it by setting the view to gone while
            // ViewGroup actually gets around to removing it.
            mView.setVisibility(View.GONE);
        }
        // Lastly, hide and remove the view from the parent (if attached)
        final ViewParent parent = mView.getParent();
        if (parent instanceof ViewGroup) {
            ((ViewGroup) parent).removeView(mView);
        }
    }

    /**
     * Returns true if we should animate the SweetSnackbar view in/out.
     */
    boolean shouldAnimate() {
        return !mAccessibilityManager.isEnabled();
    }

    /**
     * @hide
     */
    public static class SnackbarLayout extends LinearLayout {
        private TextView mMessageView;
        private Button mActionView;

        private int mMaxWidth;
        private int mMaxInlineActionWidth;

        interface OnLayoutChangeListener {
            void onLayoutChange(View view, int left, int top, int right, int bottom);
        }

        interface OnAttachStateChangeListener {
            void onViewAttachedToWindow(View v);
            void onViewDetachedFromWindow(View v);
        }

        private OnLayoutChangeListener mOnLayoutChangeListener;
        private OnAttachStateChangeListener mOnAttachStateChangeListener;

        public SnackbarLayout(Context context) {
            this(context, null);
        }

        public SnackbarLayout(Context context, AttributeSet attrs) {
            super(context, attrs);
            TypedArray a = context.obtainStyledAttributes(attrs, android.support.design.R.styleable.SnackbarLayout);
            mMaxWidth = a.getDimensionPixelSize(android.support.design.R.styleable.SnackbarLayout_android_maxWidth, -1);
            mMaxInlineActionWidth = a.getDimensionPixelSize(
                    android.support.design.R.styleable.SnackbarLayout_maxActionInlineWidth, -1);
            if (a.hasValue(android.support.design.R.styleable.SnackbarLayout_elevation)) {
                ViewCompat.setElevation(this, a.getDimensionPixelSize(
                        android.support.design.R.styleable.SnackbarLayout_elevation, 0));
            }
            a.recycle();

            setClickable(true);

            // Now inflate our content. We need to do this manually rather than using an <include>
            // in the layout since older versions of the Android do not inflate includes with
            // the correct Context.
            LayoutInflater.from(context).inflate(R.layout.layout_sweet_snackbar_include, this);

            ViewCompat.setAccessibilityLiveRegion(this,
                    ViewCompat.ACCESSIBILITY_LIVE_REGION_POLITE);
            ViewCompat.setImportantForAccessibility(this,
                    ViewCompat.IMPORTANT_FOR_ACCESSIBILITY_YES);

            // Make sure that we fit system windows and have a listener to apply any insets
            ViewCompat.setFitsSystemWindows(this, true);
            ViewCompat.setOnApplyWindowInsetsListener(this,
                    new android.support.v4.view.OnApplyWindowInsetsListener() {
                @Override
                public WindowInsetsCompat onApplyWindowInsets(View v, WindowInsetsCompat insets) {
                    // Copy over the bottom inset as padding so that we're displayed above the
                    // navigation bar
                    v.setPadding(v.getPaddingLeft(), v.getPaddingTop(),
                            v.getPaddingRight(), insets.getSystemWindowInsetBottom());
                    return insets;
                }
            });
        }

        @Override
        protected void onFinishInflate() {
            super.onFinishInflate();
            mMessageView = (TextView) findViewById(android.support.design.R.id.snackbar_text);
            mActionView = (Button) findViewById(android.support.design.R.id.snackbar_action);
        }

        TextView getMessageView() {
            return mMessageView;
        }

        Button getActionView() {
            return mActionView;
        }

        @Override
        protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
            super.onMeasure(widthMeasureSpec, heightMeasureSpec);

            if (mMaxWidth > 0 && getMeasuredWidth() > mMaxWidth) {
                widthMeasureSpec = MeasureSpec.makeMeasureSpec(mMaxWidth, MeasureSpec.EXACTLY);
                super.onMeasure(widthMeasureSpec, heightMeasureSpec);
            }

            final int multiLineVPadding = getResources().getDimensionPixelSize(
                    android.support.design.R.dimen.design_snackbar_padding_vertical_2lines);
            final int singleLineVPadding = getResources().getDimensionPixelSize(
                    android.support.design.R.dimen.design_snackbar_padding_vertical);
            final boolean isMultiLine = mMessageView.getLayout().getLineCount() > 1;

            boolean remeasure = false;
            if (isMultiLine && mMaxInlineActionWidth > 0
                    && mActionView.getMeasuredWidth() > mMaxInlineActionWidth) {
                if (updateViewsWithinLayout(VERTICAL, multiLineVPadding,
                        multiLineVPadding - singleLineVPadding)) {
                    remeasure = true;
                }
            } else {
                final int messagePadding = isMultiLine ? multiLineVPadding : singleLineVPadding;
                if (updateViewsWithinLayout(HORIZONTAL, messagePadding, messagePadding)) {
                    remeasure = true;
                }
            }

            if (remeasure) {
                super.onMeasure(widthMeasureSpec, heightMeasureSpec);
            }
        }

        void animateChildrenIn(int delay, int duration) {
            ViewCompat.setAlpha(mMessageView, 0f);
            ViewCompat.animate(mMessageView).alpha(1f).setDuration(duration)
                    .setStartDelay(delay).start();

            if (mActionView.getVisibility() == VISIBLE) {
                ViewCompat.setAlpha(mActionView, 0f);
                ViewCompat.animate(mActionView).alpha(1f).setDuration(duration)
                        .setStartDelay(delay).start();
            }
        }

        void animateChildrenOut(int delay, int duration) {
            ViewCompat.setAlpha(mMessageView, 1f);
            ViewCompat.animate(mMessageView).alpha(0f).setDuration(duration)
                    .setStartDelay(delay).start();

            if (mActionView.getVisibility() == VISIBLE) {
                ViewCompat.setAlpha(mActionView, 1f);
                ViewCompat.animate(mActionView).alpha(0f).setDuration(duration)
                        .setStartDelay(delay).start();
            }
        }

        @Override
        protected void onLayout(boolean changed, int l, int t, int r, int b) {
            super.onLayout(changed, l, t, r, b);
            if (mOnLayoutChangeListener != null) {
                mOnLayoutChangeListener.onLayoutChange(this, l, t, r, b);
            }
        }

        @Override
        protected void onAttachedToWindow() {
            super.onAttachedToWindow();
            if (mOnAttachStateChangeListener != null) {
                mOnAttachStateChangeListener.onViewAttachedToWindow(this);
            }

            ViewCompat.requestApplyInsets(this);
        }

        @Override
        protected void onDetachedFromWindow() {
            super.onDetachedFromWindow();
            if (mOnAttachStateChangeListener != null) {
                mOnAttachStateChangeListener.onViewDetachedFromWindow(this);
            }
        }

        void setOnLayoutChangeListener(OnLayoutChangeListener onLayoutChangeListener) {
            mOnLayoutChangeListener = onLayoutChangeListener;
        }

        void setOnAttachStateChangeListener(OnAttachStateChangeListener listener) {
            mOnAttachStateChangeListener = listener;
        }

        private boolean updateViewsWithinLayout(final int orientation,
                final int messagePadTop, final int messagePadBottom) {
            boolean changed = false;
            if (orientation != getOrientation()) {
                setOrientation(orientation);
                changed = true;
            }
            if (mMessageView.getPaddingTop() != messagePadTop
                    || mMessageView.getPaddingBottom() != messagePadBottom) {
                updateTopBottomPadding(mMessageView, messagePadTop, messagePadBottom);
                changed = true;
            }
            return changed;
        }

        private static void updateTopBottomPadding(View view, int topPadding, int bottomPadding) {
            if (ViewCompat.isPaddingRelative(view)) {
                ViewCompat.setPaddingRelative(view,
                        ViewCompat.getPaddingStart(view), topPadding,
                        ViewCompat.getPaddingEnd(view), bottomPadding);
            } else {
                view.setPadding(view.getPaddingLeft(), topPadding,
                        view.getPaddingRight(), bottomPadding);
            }
        }
    }

    final class Behavior extends SwipeDismissBehavior<SnackbarLayout> {
        @Override
        public boolean canSwipeDismissView(View child) {
            return child instanceof SnackbarLayout;
        }

        @Override
        public boolean onInterceptTouchEvent(CoordinatorLayout parent, SnackbarLayout child,
                                             MotionEvent event) {
            // We want to make sure that we disable any SweetSnackbar timeouts if the user is
            // currently touching the SweetSnackbar. We restore the timeout when complete
            if (parent.isPointInChildBounds(child, (int) event.getX(), (int) event.getY())) {
                switch (event.getActionMasked()) {
                    case MotionEvent.ACTION_DOWN:
                        SweetSnackbarManager.getInstance().cancelTimeout(mManagerCallback);
                        break;
                    case MotionEvent.ACTION_UP:
                    case MotionEvent.ACTION_CANCEL:
                        SweetSnackbarManager.getInstance().restoreTimeout(mManagerCallback);
                        break;
                }
            }

            return super.onInterceptTouchEvent(parent, child, event);
        }
    }
}
