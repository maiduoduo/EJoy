package com.module.iviews.textview;

import android.content.Context;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;


/**
 * @ClassName:  DouyinMarqueeTextView
 * @Author: maiduoduo
 * @BLOG: https://blog.csdn.net/Maiduoudo
 * @Date: 2021/2/1
 * @des: 跑马灯textview
 */
public class DouyinMarqueeTextView extends AppCompatTextView {

    public DouyinMarqueeTextView(Context context) {
        super(context);
    }

    public DouyinMarqueeTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public DouyinMarqueeTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean isFocused(){
        return true;
    }

}
