package com.module.iviews.popup.qq.entity;

import java.util.List;


/**
 * CN:      FilterEntity
 * Author： JSYL-DINGCL (dingcl@jsyl.com.cn)
 * Date:   2019/11/27
 * Des:    筛选数据
 */
public class FilterEntity {


    /**
     * 区域
     */
    private List<FilterAreaEntity> area;
    /**
     * 总价
     */
    private List<FilterSelectedEntity> price;
    /**
     * 户型
     */
    private List<FilterSelectedEntity> houseType;
    /**
     * 筛选
     */
    private List<FilterMulSelectEntity> mulSelect;

    public List<FilterAreaEntity> getArea() {
        return area;
    }

    public void setArea(List<FilterAreaEntity> area) {
        this.area = area;
    }

    public List<FilterSelectedEntity> getPrice() {
        return price;
    }

    public void setPrice(List<FilterSelectedEntity> price) {
        this.price = price;
    }

    public List<FilterSelectedEntity> getHouseType() {
        return houseType;
    }

    public void setHouseType(List<FilterSelectedEntity> houseType) {
        this.houseType = houseType;
    }

    public List<FilterMulSelectEntity> getMulSelect() {
        return mulSelect;
    }

    public void setMulSelect(List<FilterMulSelectEntity> mulSelect) {
        this.mulSelect = mulSelect;
    }
}
