package com.ejoy.tool.ui.activity.datetime.picker;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.ejoy.tool.R;
import com.ejoy.tool.scaffold.utils.StatusBarTool;
import com.ejoy.tool.ui.base.base_activity.BaseActivity;
import com.ejoy.tool.ui.mvp.base.BasePresenter;
import com.imaidd.citypicker.Interface.OnCityItemClickListener;
import com.imaidd.citypicker.bean.CityBean;
import com.imaidd.citypicker.bean.DistrictBean;
import com.imaidd.citypicker.bean.ProvinceBean;
import com.imaidd.citypicker.style.cityjd.JDCityConfig;
import com.imaidd.citypicker.style.cityjd.JDCityPicker;

import butterknife.BindView;


/**
 * CN:      ICitypickerJDActivity
 * Author： JSYL-DINGCL (dingcl@jsyl.com.cn)
 * Date:   2019/12/20
 * Des:    仿京东地址选择器
 */
public class ICitypickerJDActivity extends BaseActivity {

    @BindView(R.id.jd_btn) Button jdBtn;
    @BindView(R.id.result_tv) TextView resultV;
    @BindView(R.id.two_tv) TextView mTwoTv;
    @BindView(R.id.three_tv) TextView mThreeTv;
    @BindView(R.id.tvAddress) TextView mTvAddress;

    JDCityPicker cityPicker;
    public JDCityConfig.ShowType mWheelType = JDCityConfig.ShowType.PRO_CITY;
    private JDCityConfig jdCityConfig = new JDCityConfig.Builder().build();

    @Override
    protected void initRestore(@Nullable Bundle savedInstanceState) {

    }

    @Override
    protected int getContentViewId() {
        return R.layout.activity_icitypicker_jd;
    }

    @Override
    protected void initView(View mRootView) {
        StatusBarTool.setRootViewFitsSystemWindows(_mActivity,true);
        StatusBarTool.setStatusBarColor(_mActivity,Color.parseColor(defalutBlue));
        jdCityConfig.setShowType(mWheelType);
    }

    @Override
    protected void initData() {
        cityPicker = new JDCityPicker();
        //初始化数据
        cityPicker.init(this);
        //设置JD选择器样式位只显示省份和城市两级
        cityPicker.setConfig(jdCityConfig);
    }

    @Override
    protected void addListener() {
        //二级联动，只显示省份， 市，不显示区
        mTwoTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mWheelType = JDCityConfig.ShowType.PRO_CITY;
                setWheelType(mWheelType);
                jdCityConfig.setShowType(mWheelType);
            }
        });

        //三级联动，显示省份， 市和区
        mThreeTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mWheelType = JDCityConfig.ShowType.PRO_CITY_DIS;
                setWheelType(mWheelType);
                jdCityConfig.setShowType(mWheelType);
            }
        });
        jdBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showJD();
            }
        });
        cityPicker.setOnCityItemClickListener(new OnCityItemClickListener() {
            @Override
            public void onSelected(ProvinceBean province, CityBean city, DistrictBean district) {

                String proData = null;
                String proData1 = "";
                if (province != null) {
                    proData = "name:  " + province.getName() + "   id:  " + province.getId();
                    proData1 = province.getName() ;
                }

                String cityData = null;
                String cityData1 = "";
                if (city != null) {
                    cityData = "name:  " + city.getName() + "   id:  " + city.getId();
                    cityData1 = city.getName();
                }


                String districtData = null;
                String districtData1 = "";
                if (district != null) {
                    districtData = "name:  " + district.getName() + "   id:  " + district.getId();
                    districtData1 = district.getName();
                }


                if (mWheelType == JDCityConfig.ShowType.PRO_CITY_DIS) {
                    resultV.setText(String.format("城市选择结果：%s --,%s,%s",proData,cityData,districtData));
                    mTvAddress.setText(String.format("收货地址：%s,%s",proData1+cityData1+districtData1+"","xxx街道xxx大厦xxx幢C401-5"));
                } else {
                    resultV.setText(String.format("城市选择结果：%s --,%s",proData,cityData));
                    mTvAddress.setText(String.format("收货地址：%s,%s",proData1+cityData1+"","xxx街道xxx大厦xxx幢C401-5"));
                }
            }

            @Override
            public void onCancel() {
            }
        });
    }

    @Override
    public BasePresenter getPresenter() {
        return null;
    }



    /**
     * @param wheelType
     */
    private void setWheelType(JDCityConfig.ShowType wheelType) {
        if (wheelType == JDCityConfig.ShowType.PRO_CITY) {
            mTwoTv.setBackgroundResource(R.drawable.shape_city_jd_left_selected);
            mThreeTv.setBackgroundResource(R.drawable.shape_city_jd_right_normal);
            mTwoTv.setTextColor(Color.parseColor("#ffffff"));
            mThreeTv.setTextColor(Color.parseColor("#333333"));
        } else {
            mTwoTv.setBackgroundResource(R.drawable.shape_city_jd_left_normal);
            mThreeTv.setBackgroundResource(R.drawable.shape_city_jd_right_selected);
            mTwoTv.setTextColor(Color.parseColor("#333333"));
            mThreeTv.setTextColor(Color.parseColor("#ffffff"));
        }
    }


    private void showJD() {
        cityPicker.showCityPicker();
    }

    public void ivback(View view) {
        finish();
    }
}
