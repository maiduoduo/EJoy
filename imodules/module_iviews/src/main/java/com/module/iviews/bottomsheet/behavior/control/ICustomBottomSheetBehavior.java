package com.module.iviews.bottomsheet.behavior.control;

import android.support.annotation.NonNull;
import android.support.design.widget.CoordinatorLayout;
import android.view.View;

import java.util.List;


public interface ICustomBottomSheetBehavior<V extends View> {
    void setNestedScrollingChildRefList(List<View> nestedScrollingChildRefList);

    boolean onLayoutChild(CoordinatorLayout parent, V child, int layoutDirection);

    void onNestedScroll(
            @NonNull CoordinatorLayout coordinatorLayout,
            @NonNull V child,
            @NonNull View target,
            int dxConsumed,
            int dyConsumed,
            int dxUnconsumed,
            int dyUnconsumed,
            int type,
            @NonNull int[] consumed);
}
