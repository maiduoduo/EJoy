package com.ejoy.tool.ui.douyin.bean;


import com.module.ires.bean.bean.DouyinVideoBean;

/**
 * @ClassName:  DouyinCurUserBean
 * @Author: maiduoduo
 * @BLOG: https://blog.csdn.net/Maiduoudo
 * @Date: 2021/2/2
 * @des: 当前播放视频的作者Userbean切换
 */
public class DouyinCurUserBean {
    private DouyinVideoBean.UserBean userBean;

    public DouyinCurUserBean(DouyinVideoBean.UserBean userBean) {
        this.userBean = userBean;
    }

    public DouyinVideoBean.UserBean getUserBean() {
        return userBean;
    }

    public void setUserBean(DouyinVideoBean.UserBean userBean) {
        this.userBean = userBean;
    }
}
