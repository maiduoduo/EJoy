package com.module.iviews.popup.qq.entity;
import com.iduo.filterlib.base.BaseFilterBean;

import java.util.List;


/**
 * CN:      FilterEntity
 * Author： JSYL-DINGCL (dingcl@jsyl.com.cn)
 * Date:   2019/11/27
 * Des:    筛选多选Entity
 */
public class FilterMulSelectEntity extends BaseFilterBean {

    /**
     * 分类名称
     */
    private String sortname;
    /**
     * 分类Key
     */
    private String sortkey;
    /**
     * 分类数据
     */
    private List<FilterSelectedEntity> sortdata;

    private int isCan;

    public int getIsCan() {
        return isCan;
    }

    public void setIsCan(int isCan) {
        this.isCan = isCan;
    }

    public String getSortname() {
        return sortname;
    }

    public void setSortname(String sortname) {
        this.sortname = sortname;
    }

    public String getSortkey() {
        return sortkey;
    }

    public void setSortkey(String sortkey) {
        this.sortkey = sortkey;
    }

    public List<FilterSelectedEntity> getSortdata() {
        return sortdata;
    }

    public void setSortdata(List<FilterSelectedEntity> sortdata) {
        this.sortdata = sortdata;
    }

    @Override
    public String getItemName() {
        return null;
    }

    @Override
    public int getId() {
        return 0;
    }

    @Override
    public int getSelecteStatus() {
        return 0;
    }

    @Override
    public void setSelecteStatus(int status) {

    }

    @Override
    public String getSortTitle() {
        return sortname;
    }

    @Override
    public List getChildList() {
        return sortdata;
    }

    @Override
    public String getSortKey() {
        return sortkey;
    }

    @Override
    public boolean isCanMulSelect() {
        return isCan == 1;
    }
}
