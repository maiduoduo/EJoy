package com.ejoy.tool.common.bean;

/**
 * @ClassName: RecyclerviewTimeLineEntity
 * @Author: bsj-dingcl
 * @Email: dingchenglei@h4kit.com
 * @Date: 2020/07
 * @des: 时间线
 */
public class RecyclerviewTimeLineEntity {

    /**
     * totoDate : 13:40
     * totoTitle : 运送至常德
     * totoStatus : 2
     * mark :
     */

    private String totoDate;
    private String totoTitle;
    private int totoStatus;
    private String mark;

    public String getTotoDate() {
        return totoDate;
    }

    public void setTotoDate(String totoDate) {
        this.totoDate = totoDate;
    }

    public String getTotoTitle() {
        return totoTitle;
    }

    public void setTotoTitle(String totoTitle) {
        this.totoTitle = totoTitle;
    }

    public int getTotoStatus() {
        return totoStatus;
    }

    public void setTotoStatus(int totoStatus) {
        this.totoStatus = totoStatus;
    }

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }
}



