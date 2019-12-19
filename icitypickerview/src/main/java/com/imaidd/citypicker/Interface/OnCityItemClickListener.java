package com.imaidd.citypicker.Interface;

import com.imaidd.citypicker.bean.CityBean;
import com.imaidd.citypicker.bean.DistrictBean;
import com.imaidd.citypicker.bean.ProvinceBean;

/**
 * 城市选择监听
 */
public abstract class OnCityItemClickListener {
    
    /**
     * 当选择省市区三级选择器时，需要覆盖此方法
     * @param province
     * @param city
     * @param district
     */
    public void onSelected(ProvinceBean province, CityBean city, DistrictBean district) {
        
    }
    
    /**
     * 取消
     */
    public void onCancel() {
        
    }
}
