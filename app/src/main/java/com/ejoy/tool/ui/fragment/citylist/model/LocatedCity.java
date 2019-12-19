package com.ejoy.tool.ui.fragment.citylist.model;


import com.ejoy.tool.common.db.CitysBean;

public class LocatedCity extends CitysBean {
    public LocatedCity(String c_name, String c_code,String c_province, String c_tip) {
        super(null,c_name, "定位城市/最近访问",c_code,c_province, "", "",c_tip);
    }


//    public LocatedCity(String name, String province, String code,String tip) {
//        super(name, province, "定位城市/最近访问", code,tip);
//    }
}
