package com.ejoy.tool.floattab.View;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;


/**
 *
 *
 * @author Kevin
 */
public class FragmentViewPager extends ViewPager {

    public FragmentViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public FragmentViewPager(Context context) {
        super(context);
    }
//    @Override
//    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
//        int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2,
//                MeasureSpec.AT_MOST);
//        super.onMeasure(widthMeasureSpec, expandSpec);
//    }
}
