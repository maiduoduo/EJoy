package com.module.iviews.radius.delegate;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Function:TextView及EditText代理类
 * Description:
 * 1、2018-5-25 11:27:05 新增代理解决泛型链式调用BUG
 */
public class RadiusTextViewDelegate extends RadiusTextDelegate<RadiusTextViewDelegate> {

    public RadiusTextViewDelegate(TextView view, Context context, AttributeSet attrs) {
        super(view, context, attrs);
    }

    @Override
    public void init() {
        super.init();
    }
}
