package com.ejoy.tool.ui.fragment.citylist.model;


import com.ejoy.tool.common.db.CitysBean;

/**
 * 热门城市section
 */
public class HotCity extends CitysBean {
    public HotCity(String name, String province, String code,String tip) {
        super(null,name, "热门城市",code, province, "","",tip);
    }


//    public HotCity(String name, String province, String code,String tip) {
//        super(name, province, "热门城市", code,tip);
//    }
}
