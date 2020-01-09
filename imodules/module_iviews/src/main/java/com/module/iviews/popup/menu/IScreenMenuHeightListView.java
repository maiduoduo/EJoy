package com.module.iviews.popup.menu;

import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.Display;
import android.widget.ListView;

public class IScreenMenuHeightListView extends ListView {

    private Context mContext;

    public IScreenMenuHeightListView(Context context) {
        this(context, null);
    }

    public IScreenMenuHeightListView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public IScreenMenuHeightListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        mContext = context;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        try {
            Display display = ((Activity) mContext).getWindowManager().getDefaultDisplay();
            DisplayMetrics d = new DisplayMetrics();
            display.getMetrics(d);
            heightMeasureSpec = MeasureSpec.makeMeasureSpec((int) ((d.heightPixels * 0.8) - 200), MeasureSpec.AT_MOST);
        } catch (Exception e) {
            e.printStackTrace();
        }
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }
}
