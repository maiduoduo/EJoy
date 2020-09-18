package com.ejoy.tool.common.bean;

import android.app.Activity;


/**
 * @ClassName:  WidgetEntity
 * @Author: maiduoduo
 * @BLOG: https://blog.csdn.net/Maiduoudo
 * @Date: 2020/9/16
 * @des:  条目实体
 */

public class WidgetEntity {

    public int icon;
    public String title;
    public String content;
    public Class<? extends Activity> activity;

    public WidgetEntity(String title, String content, Class<? extends Activity> activity) {
        this.title = title;
        this.content = content;
        this.activity = activity;
    }

    public WidgetEntity(int icon,String title, String content, Class<? extends Activity> activity) {
        this.icon = icon;
        this.title = title;
        this.content = content;
        this.activity = activity;
    }
}
