package com.imaiduoduo.datetime.languages;

import java.util.Locale;

/**
 * 中文的默认实现类
 * 如果你想实现更多的语言请参考Language{@link DPLManager}
 */
public class CN extends DPLManager {

    public CN(Locale locale) {
        mLocale = locale;
    }

    @Override
    public String[] titleMonth() {
        return new String[]{"一月", "二月", "三月", "四月", "五月", "六月", "七月", "八月", "九月", "十月", "十一月", "十二月"};
    }

    @Override
    public String titleEnsure() {
        return "确定";
    }

    @Override
    public String titleBC() {
        return "公元前";
    }

    @Override
    public String[] titleWeek() {
        return new String[]{"日", "一", "二", "三", "四", "五", "六"};
    }

    @Override
    public String getDateFormatStr() {
        return "yyyy年M月d日";
    }

}
