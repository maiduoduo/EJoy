package com.module.ires.bean.utils;

import android.content.Context;
import android.util.TypedValue;

import com.module.ires.R;


/**
 * @ClassName:  EColorUtil
 * @Author: maiduoduo
 * @BLOG: https://blog.csdn.net/Maiduoudo
 * @Date: 2020/9/17
 * @des: EColorUtil
 */
public class EColorUtil {

    public static int getColorPrimary(Context context){
        TypedValue typedValue = new TypedValue();
        context.getTheme().resolveAttribute(R.attr.colorPrimary, typedValue, true);
        return typedValue.data;
    }

    public static int getBackgroundColor(Context context){
        TypedValue typedValue = new TypedValue();
        context.getTheme().resolveAttribute(R.attr.widgetBackground, typedValue, true);
        return typedValue.data;
    }
}
