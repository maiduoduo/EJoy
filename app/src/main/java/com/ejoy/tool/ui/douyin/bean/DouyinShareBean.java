package com.ejoy.tool.ui.douyin.bean;

public class DouyinShareBean {
    private int iconRes;
    private String text;
    private int bgRes;

    public DouyinShareBean(int iconRes, String text, int bgRes) {
        this.iconRes = iconRes;
        this.text = text;
        this.bgRes = bgRes;
    }

    public int getIconRes() {
        return iconRes;
    }

    public void setIconRes(int iconRes) {
        this.iconRes = iconRes;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getBgRes() {
        return bgRes;
    }

    public void setBgRes(int bgRes) {
        this.bgRes = bgRes;
    }
}
