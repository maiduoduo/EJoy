package com.ejoy.tool.ui.douyin.bean;

import com.module.ires.bean.bean.DouyinVideoBean;

public class DouyinCommentBean {
    private String content;
    private DouyinVideoBean.UserBean userBean;
    private int likeCount;
    private boolean isLiked;

    public String getContent() {
        return content == null ? "" : content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public DouyinVideoBean.UserBean getUserBean() {
        return userBean;
    }

    public void setUserBean(DouyinVideoBean.UserBean userBean) {
        this.userBean = userBean;
    }

    public int getLikeCount() {
        return likeCount;
    }

    public void setLikeCount(int likeCount) {
        this.likeCount = likeCount;
    }

    public boolean isLiked() {
        return isLiked;
    }

    public void setLiked(boolean liked) {
        isLiked = liked;
    }
}
