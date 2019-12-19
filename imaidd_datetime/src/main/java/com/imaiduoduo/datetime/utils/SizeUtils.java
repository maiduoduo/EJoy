package com.imaiduoduo.datetime.utils;

import android.content.Context;
import android.util.TypedValue;

public class SizeUtils {
    public static int dp2px(Context context, int dp) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp
                , context.getResources().getDisplayMetrics());
    }

    public static int sp2px(Context context, int dp) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, dp
                , context.getResources().getDisplayMetrics());
    }
}
