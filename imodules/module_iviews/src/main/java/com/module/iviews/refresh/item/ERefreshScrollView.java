package com.module.iviews.refresh.item;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ScrollView;

import com.module.ires.bean.utils.log.ELog;

import java.util.ArrayList;
import java.util.List;

/**
 * ScrollView
 * 嵌套ScrollView
 */
public class ERefreshScrollView extends ScrollView {

    private List<OnScrollListener> mOnScrollListeners = new ArrayList<>();

    public ERefreshScrollView(Context context) {
        super(context);
    }

    public ERefreshScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ERefreshScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);
        if (ELog.isEnabled()) ELog.v("l=%d, t=%d, oldl=%d, oldt=%d", l, t, oldl, oldt);
        notifiOnScrollListener(l, t, oldl, oldt);
    }

    public boolean addOnScrollListener(OnScrollListener onScrollListener) {
        return mOnScrollListeners.add(onScrollListener);
    }

    public boolean removeOnScrollListener(OnScrollListener onScrollListener) {
        return mOnScrollListeners.remove(onScrollListener);
    }

    private void notifiOnScrollListener(int l, int t, int oldl, int oldt) {
        for (OnScrollListener scrollListener : mOnScrollListeners) {
            scrollListener.onScrollChanged(this, l, t, oldl, oldt);
        }
    }

    public interface OnScrollListener {
        void onScrollChanged(ScrollView scrollView, int l, int t, int oldl, int oldt);
    }
}
