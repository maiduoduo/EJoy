package com.imaidd.citypicker.Interface;

import com.imaidd.citypicker.bean.CustomCityData;

/**
 * 自定义城市选择监听
 */

public abstract class OnCustomCityPickerItemClickListener {

    /**
     * 当选择省市区三级选择器时，需要覆盖此方法
     *
     * @param province
     * @param city
     * @param district
     */
    public void onSelected(CustomCityData province, CustomCityData city, CustomCityData district) {

    }

    /**
     * 取消
     */
    public void onCancel() {

    }
}
