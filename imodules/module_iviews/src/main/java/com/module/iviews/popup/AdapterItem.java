package com.module.iviews.popup;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.renderscript.Allocation;

/**
 * 简易的适配项
 */
public class AdapterItem {

    /**
     * 标题内容
     */
    private CharSequence mTitle;
    /**
     * 图标
     */
    private Drawable mIcon;

    public static AdapterItem of(CharSequence title) {
        return new AdapterItem(title);
    }

    public static AdapterItem[] arrayof(CharSequence[] title) {
        AdapterItem[] array = new AdapterItem[title.length];
        for (int i = 0; i < array.length; i++) {
            array[i] = new AdapterItem(title[i]);
        }
        return array;
    }

    public AdapterItem(CharSequence title) {
        mTitle = title;
    }

    public AdapterItem(CharSequence title, Drawable icon) {
        mTitle = title;
        mIcon = icon;
    }


    public AdapterItem(Context context, int titleId, int drawableId) {
        mTitle = context.getResources().getText(titleId);
        mIcon = context.getResources().getDrawable(drawableId);
    }

    public AdapterItem(Context context, CharSequence title, int drawableId) {
        mTitle = title;
        mIcon = context.getResources().getDrawable(drawableId);
    }

    public CharSequence getTitle() {
        return mTitle;
    }

    public AdapterItem setTitle(CharSequence title) {
        mTitle = title;
        return this;
    }

    public Drawable getIcon() {
        return mIcon;
    }

    public AdapterItem setIcon(Drawable icon) {
        mIcon = icon;
        return this;
    }

    @Override
    public String toString() {
        return mTitle.toString();
    }
}
