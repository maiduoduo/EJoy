package com.imaiduoduo.bottomsheet;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.view.ViewCompat;
import android.support.v4.widget.ViewDragHelper;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;

/**
 * Project: gradle
 */
public class ClosableSlidingLayout extends FrameLayout {

    private static final int INVALID_POINTER = -1;
    private final float MINVEL;
    View mTarget;
    boolean swipeable = true;
    private ViewDragHelper mDragHelper;
    private SlideListener mListener;
    private int height;
    private int top;
    private int mActivePointerId;
    private boolean mIsBeingDragged;
    private float mInitialMotionY;
    private boolean collapsible = false;
    private float yDiff;

    public ClosableSlidingLayout(Context context) {
        this(context, null);
    }

    public ClosableSlidingLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    public ClosableSlidingLayout(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        mDragHelper = ViewDragHelper.create(this, 0.8f, new ViewDragCallback());
        MINVEL = getResources().getDisplayMetrics().density * 400;
    }

    @Override
    public boolean onInterceptTouchEvent(@NonNull MotionEvent event) {
        final int action = event.getActionMasked();

        if (!isEnabled() || canChildScrollUp()) {
            // Fail fast if we're not in a state where a swipe is possible
            return false;
        }

        if (action == MotionEvent.ACTION_CANCEL || action == MotionEvent.ACTION_UP) {
            mActivePointerId = INVALID_POINTER;
            mIsBeingDragged = false;
            if (collapsible && -yDiff > mDragHelper.getTouchSlop()) {
                expand(mDragHelper.getCapturedView(), 0);
            }
            mDragHelper.cancel();
            return false;
        }

        switch (action) {
            case MotionEvent.ACTION_DOWN:
                height = getChildAt(0).getHeight();
                top = getChildAt(0).getTop();
                mActivePointerId = event.getPointerId(0);
                mIsBeingDragged = false;
                final float initialMotionY = getMotionEventY(event, mActivePointerId);
                if (initialMotionY == -1) {
                    return false;
                }
                mInitialMotionY = initialMotionY;
                yDiff = 0;
                break;
            case MotionEvent.ACTION_MOVE:
                if (mActivePointerId == INVALID_POINTER) {
                    return false;
                }
                final float y = getMotionEventY(event, mActivePointerId);
                if (y == -1) {
                    return false;
                }
                yDiff = y - mInitialMotionY;
                if (swipeable && yDiff > mDragHelper.getTouchSlop() && !mIsBeingDragged) {
                    mIsBeingDragged = true;
                    mDragHelper.captureChildView(getChildAt(0), 0);
                }
                break;
        }
        mDragHelper.shouldInterceptTouchEvent(event);
        return mIsBeingDragged;
    }

    @Override
    public void requestDisallowInterceptTouchEvent(boolean b) {
        // Nope.
    }

    /**
     * @return Whether it is possible for the child view of this layout to
     * scroll up. Override this if the child view is a custom view.
     */
    private boolean canChildScrollUp() {
        return mTarget.canScrollVertically(-1);
    }

    private float getMotionEventY(MotionEvent ev, int activePointerId) {
        final int index = ev.findPointerIndex(activePointerId);
        if (index < 0) {
            return -1;
        }
        return ev.getY(index);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        if (!isEnabled() || canChildScrollUp()) {
            return super.onTouchEvent(ev);
        }

        try {
            if (swipeable)
                mDragHelper.processTouchEvent(ev);
        } catch (Exception ignored) {
        }
        return true;
    }

    @Override
    public void computeScroll() {
        if (mDragHelper.continueSettling(true)) {
            ViewCompat.postInvalidateOnAnimation(this);
        }
    }

    void setSlideListener(SlideListener listener) {
        mListener = listener;
    }

    void setCollapsible(boolean collapsible) {
        this.collapsible = collapsible;
    }

    private void expand(View releasedChild, float yvel) {
        if (mListener != null) {
            mListener.onOpened();
        }
    }

    private void dismiss(View view, float yvel) {
        mDragHelper.smoothSlideViewTo(view, 0, top + height);
        ViewCompat.postInvalidateOnAnimation(ClosableSlidingLayout.this);
    }

    /**
     * set listener
     */
    interface SlideListener {
        void onClosed();

        void onOpened();
    }

    /**
     * Callback
     */
    private class ViewDragCallback extends ViewDragHelper.Callback {


        @Override
        public boolean tryCaptureView(View child, int pointerId) {
            return true;
        }

        @Override
        public void onViewReleased(View releasedChild, float xvel, float yvel) {
            if (yvel > MINVEL) {
                dismiss(releasedChild, yvel);
            } else {
                if (releasedChild.getTop() >= top + height / 2) {
                    dismiss(releasedChild, yvel);
                } else {
                    mDragHelper.smoothSlideViewTo(releasedChild, 0, top);
                    ViewCompat.postInvalidateOnAnimation(ClosableSlidingLayout.this);
                }
            }
        }

        @Override
        public void onViewPositionChanged(View changedView, int left, int top, int dx, int dy) {
            if (height - top < 1 && mListener != null) {
                mDragHelper.cancel();
                mListener.onClosed();
                mDragHelper.smoothSlideViewTo(changedView, 0, top);
            }
        }

        @Override
        public int clampViewPositionVertical(View child, int top, int dy) {
            return Math.max(top, ClosableSlidingLayout.this.top);
        }
    }

}