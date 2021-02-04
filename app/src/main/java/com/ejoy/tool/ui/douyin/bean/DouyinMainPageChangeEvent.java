package com.ejoy.tool.ui.douyin.bean;

public class DouyinMainPageChangeEvent {
    private int page;

    public DouyinMainPageChangeEvent(int page) {
        this.page = page;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }
}
