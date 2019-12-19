/*
 * Copyright (c) 2017. CMRI PRIVATE LIMITED. All rights reserved
 * Created by WangBo on 17-6-23 上午11:48
 *
 * Last modified 17-6-23 上午11:48
 */

package com.ejoy.tool.common.db;

import android.text.TextUtils;

import com.chad.library.adapter.base.entity.MultiItemEntity;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Entity mapped to table "NOTE".
 */
@Entity(
        nameInDb = "cities",
        createInDb = false
)
public class CitysBean implements MultiItemEntity {
    @Id
    @Property(nameInDb = "id")
    private Long id;
    @Property(nameInDb = "c_name")
    private String c_name;
    @Property(nameInDb = "c_pinyin")
    private String c_pinyin;
    @Property(nameInDb = "c_code")
    private String c_code;
    @Property(nameInDb = "c_province")
    private String c_province;
    @Property(nameInDb = "c_area")
    private String c_area;
    @Property(nameInDb = "c_area_code")
    private String c_area_code;
    @Property(nameInDb = "c_tip")
    private String c_tip;
@Generated(hash = 1401655982)
public CitysBean(Long id, String c_name, String c_pinyin, String c_code,
        String c_province, String c_area, String c_area_code, String c_tip) {
    this.id = id;
    this.c_name = c_name;
    this.c_pinyin = c_pinyin;
    this.c_code = c_code;
    this.c_province = c_province;
    this.c_area = c_area;
    this.c_area_code = c_area_code;
    this.c_tip = c_tip;
}
@Generated(hash = 1870946605)
public CitysBean() {
}
public Long getId() {
    return this.id;
}
public void setId(Long id) {
    this.id = id;
}
public String getC_name() {
    return this.c_name;
}
public void setC_name(String c_name) {
    this.c_name = c_name;
}
public String getC_pinyin() {
    return this.c_pinyin;
}
public void setC_pinyin(String c_pinyin) {
    this.c_pinyin = c_pinyin;
}
public String getC_code() {
    return this.c_code;
}
public void setC_code(String c_code) {
    this.c_code = c_code;
}
public String getC_province() {
    return this.c_province;
}
public void setC_province(String c_province) {
    this.c_province = c_province;
}
public String getC_area() {
    return this.c_area;
}
public void setC_area(String c_area) {
    this.c_area = c_area;
}
public String getC_area_code() {
    return this.c_area_code;
}
public void setC_area_code(String c_area_code) {
    this.c_area_code = c_area_code;
}
public String getC_tip() {
    return this.c_tip;
}
public void setC_tip(String c_tip) {
    this.c_tip = c_tip;
}











    /***
     * 获取悬浮栏文本，（#、定位、热门 需要特殊处理）
     * @return
     */
    public String getSection(){
        if (TextUtils.isEmpty(c_pinyin)) {
            return "#";
        } else {
            String c = c_pinyin.substring(0, 1);
            Pattern p = Pattern.compile("[a-zA-Z]");
            Matcher m = p.matcher(c);
            if (m.matches()) {
                return c.toUpperCase();
            }
            //在添加定位和热门数据时设置的section就是‘定’、’热‘开头
            else if (TextUtils.equals(c, "定") || TextUtils.equals(c, "热"))
                return c_pinyin;
            else
                return "#";
        }
    }


    @Override
    public int getItemType() {
        return 0;
    }
}
