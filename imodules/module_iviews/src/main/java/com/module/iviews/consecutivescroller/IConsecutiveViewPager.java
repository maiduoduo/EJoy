package com.module.iviews.consecutivescroller;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import java.util.ArrayList;
import java.util.List;



/**
 * @ClassName:  IConsecutiveViewPager
 * @Author: maiduoduo
 * @BLOG: https://blog.csdn.net/Maiduoudo
 * @Date: 2020/9/18
 * @des:  IConsecutiveViewPager
 */
public class IConsecutiveViewPager extends ViewPager implements IConsecutiveScroller {

    private int mAdjustHeight;

    public IConsecutiveViewPager(@NonNull Context context) {
        super(context);
    }

    public IConsecutiveViewPager(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        if (isConsecutiveParent() && mAdjustHeight > 0) {
            int height = getDefaultSize(0, heightMeasureSpec) - mAdjustHeight;
            super.onMeasure(widthMeasureSpec,
                    MeasureSpec.makeMeasureSpec(height, MeasureSpec.getMode(heightMeasureSpec)));
        } else {
            super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        }
    }

    @Override
    public void addView(View child, int index, ViewGroup.LayoutParams params) {
        super.addView(child, index, params);

        // 去掉子View的滚动条。选择在这里做这个操作，而不是在onFinishInflate方法中完成，是为了兼顾用代码add子View的情况

        if (IScrollUtils.isConsecutiveScrollerChild(this)) {
            disableChildScroll(child);

            if (child instanceof ViewGroup) {
                ((ViewGroup) child).setClipToPadding(false);
            }
        }
    }

    /**
     * 禁用子view的一下滑动相关的属性
     *
     * @param child
     */
    private void disableChildScroll(View child) {
        child.setVerticalScrollBarEnabled(false);
        child.setHorizontalScrollBarEnabled(false);
        child.setOverScrollMode(OVER_SCROLL_NEVER);
        ViewCompat.setNestedScrollingEnabled(child, false);
    }

    private boolean isConsecutiveParent() {
        ViewParent parent = getParent();
        if (parent instanceof IConsecutiveScrollerLayout) {
            IConsecutiveScrollerLayout layout = (IConsecutiveScrollerLayout) parent;
            return layout.indexOfChild(this) == layout.getChildCount() - 1;
        }
        return false;
    }

    public int getAdjustHeight() {
        return mAdjustHeight;
    }

    public void setAdjustHeight(int adjustHeight) {
        if (mAdjustHeight != adjustHeight) {
            mAdjustHeight = adjustHeight;
            requestLayout();
        }
    }

    /**
     * 返回当前需要滑动的view。
     *
     * @return
     */
    @Override
    public View getCurrentScrollerView() {
        int count = getChildCount();
        for (int i = 0; i < count; i++) {
            View view = getChildAt(i);
            if (view.getX() == getScrollX() + getPaddingLeft()) {
                return view;
            }
        }
        return this;
    }

    /**
     * 返回全部需要滑动的下级view
     *
     * @return
     */
    @Override
    public List<View> getScrolledViews() {
        List<View> views = new ArrayList<>();
        int count = getChildCount();
        if (count > 0) {
            for (int i = 0; i < count; i++) {
                views.add(getChildAt(i));
            }
        }
        return views;
    }
}
