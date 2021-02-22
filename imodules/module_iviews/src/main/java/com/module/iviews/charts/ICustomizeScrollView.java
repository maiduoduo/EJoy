package com.module.iviews.charts;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.HorizontalScrollView;

/**
 * @ClassName:  ICustomizeScrollView
 * @Author: maiduoduo
 * @BLOG: https://blog.csdn.net/Maiduoudo
 * @Date: 2021/2/22
 * @des: 自定义横向滚动
 */
public class ICustomizeScrollView extends HorizontalScrollView {

    private OnScrollViewListener viewListener;

    public interface OnScrollViewListener {
        void onScroll(int l, int t, int oldl, int oldt);
    }

    public void setViewListener(OnScrollViewListener viewListener) {
        this.viewListener = viewListener;
    }

    public ICustomizeScrollView(Context context) {
        this(context,null);
    }

    public ICustomizeScrollView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public ICustomizeScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);

        if (viewListener != null) {
            viewListener.onScroll(l, t, oldl, oldt);
        }
    }

}
