package com.module.iviews.popup.weibo;

import android.graphics.drawable.Drawable;

/**
 * CN:      PopMenuItem
 * Author： DINGCL
 * Date:   2016-5-8
 * Des:    TODO:PopMenuItem
 */
public class PopMenuItem {

    private String title;
    private Drawable drawable;

    public PopMenuItem(String title, Drawable drawable) {
        this.title = title;
        this.drawable = drawable;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Drawable getDrawable() {
        return drawable;
    }

    public void setDrawable(Drawable drawable) {
        this.drawable = drawable;
    }
}
