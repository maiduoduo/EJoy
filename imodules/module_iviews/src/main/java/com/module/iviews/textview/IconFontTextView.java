package com.module.iviews.textview;

import android.content.Context;
import android.graphics.Typeface;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;


/**
 * @ClassName:  IconFontTextView
 * @Author: maiduoduo
 * @BLOG: https://blog.csdn.net/Maiduoudo
 * @Date: 2021/2/2
 * @des: 用于加载iconfont的TextView
 */
public class IconFontTextView extends AppCompatTextView {
    /** 所有IconFontTextView公用typeface */
    private static Typeface typeface;

    public IconFontTextView(Context context) {
        super(context);
    }

    public IconFontTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    {
        typeface = Typeface.createFromAsset(getContext().getAssets(), "iconfont.ttf");
        setTypeface(typeface);
    }

}
