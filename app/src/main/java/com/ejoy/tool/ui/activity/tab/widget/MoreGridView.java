package com.ejoy.tool.ui.activity.tab.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.GridView;

/**
 * 九宫格
 */
public class MoreGridView extends GridView {
    public MoreGridView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MoreGridView(Context context) {
        super(context);
    }

    public MoreGridView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        int expandSpec = MeasureSpec.makeMeasureSpec(
                Integer.MAX_VALUE >> 2, MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, expandSpec);
    }
}