package com.module.iviews.popup.bean;


public class GalleryBean {
    private String mTitle;
    private boolean isChecked;
    private int imgThumb;

    public GalleryBean(String title) {
        mTitle = title;
    }

    public GalleryBean(String mTitle, boolean isChecked) {
        this.mTitle = mTitle;
        this.isChecked = isChecked;
    }

    public GalleryBean(String mTitle, boolean isChecked, int imgThumb) {
        this.mTitle = mTitle;
        this.isChecked = isChecked;
        this.imgThumb = imgThumb;
    }

    public String getTitle() {
        return mTitle;
    }

    public int getImgThumb() {
        return imgThumb;
    }

    public void setImgThumb(int imgThumb) {
        this.imgThumb = imgThumb;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public boolean isChecked() {
        return isChecked;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
    }
}
