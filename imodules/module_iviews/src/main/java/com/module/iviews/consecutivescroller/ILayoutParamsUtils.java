package com.module.iviews.consecutivescroller;

import android.view.ViewGroup;


/**
 * @ClassName:  ILayoutParamsUtils
 * @Author: maiduoduo
 * @BLOG: https://blog.csdn.net/Maiduoudo
 * @Date: 2020/9/18
 * @des: ILayoutParamsUtils
 */
public class ILayoutParamsUtils {

    /**
     * 使子view的topMargin和bottomMargin属性无效
     *
     * @param params
     */
    public static void invalidTopAndBottomMargin(ViewGroup.MarginLayoutParams params){
        if (params != null) {
            params.topMargin = 0;
            params.bottomMargin = 0;
        }
    }
}
