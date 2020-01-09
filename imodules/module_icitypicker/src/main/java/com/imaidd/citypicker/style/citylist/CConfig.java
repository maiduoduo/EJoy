package com.imaidd.citypicker.style.citylist;

import com.imaidd.citypicker.style.citylist.bean.CityInfoBean;

public class CConfig {

    private static CityInfoBean sCityInfoBean;

    public static void setCity(CityInfoBean city) {
        sCityInfoBean = city;
    }

    public static CityInfoBean getCitySelected() {

        return sCityInfoBean;
    }
}
