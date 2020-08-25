package com.ejoy.tool.ui.activity.datetime.picker;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;

import com.ejoy.tool.R;
import com.ejoy.tool.scaffold.utils.StatusBarTool;
import com.ejoy.tool.ui.base.base_activity.BaseActivity;
import com.ejoy.tool.ui.mvp.base.BasePresenter;
import com.imaidd.citypicker.Interface.OnCityItemClickListener;
import com.imaidd.citypicker.bean.CityBean;
import com.imaidd.citypicker.bean.DistrictBean;
import com.imaidd.citypicker.bean.ProvinceBean;
import com.imaidd.citypicker.citywheel.CityConfig;
import com.imaidd.citypicker.style.citypickerview.CityPickerView;
import butterknife.BindView;

/**
 * CN:      ITimeDateOrActivity
 * Author： JSYL-DINGCL (dingcl@jsyl.com.cn)
 * Date:   2019/12/13
 * Des:    仿IOS城市滚轮选择器
 */
public class ICitypickerWheelActivity extends BaseActivity implements View.OnClickListener {
    @BindView(R.id.result_tv) TextView mResultTv;
    @BindView(R.id.pro_et) EditText mProEt;
    @BindView(R.id.city_et) EditText mCityEt;
    @BindView(R.id.area_et) EditText mAreaEt;
    @BindView(R.id.pro_visible_count_et) EditText mProVisibleCountEt;
    @BindView(R.id.pro_cyclic_ck) CheckBox mProCyclicCk;
    @BindView(R.id.city_cyclic_ck) CheckBox mCityCyclicCk;
    @BindView(R.id.area_cyclic_ck) CheckBox mAreaCyclicCk;
    @BindView(R.id.gat_ck) CheckBox mGATCk;
    @BindView(R.id.half_bg_ck) CheckBox mHalfBgCk;
    @BindView(R.id.reset_setting_tv) TextView mResetSettingTv;
    @BindView(R.id.submit_tv) TextView mSubmitTv;
    @BindView(R.id.one_tv) TextView mOneTv;
    @BindView(R.id.two_tv) TextView mTwoTv;
    @BindView(R.id.three_tv) TextView mThreeTv;


    private int visibleItems = 5;
    private boolean isProvinceCyclic = true;
    private boolean isCityCyclic = true;
    private boolean isDistrictCyclic = true;
    private boolean isShowBg = true;
    private boolean isShowGAT = true;
    private String defaultProvinceName = "江苏";
    private String defaultCityName = "南京";
    private String defaultDistrict = "栖霞区";
    public CityConfig.WheelType mWheelType = CityConfig.WheelType.PRO_CITY_DIS;
    CityPickerView mCityPickerView = new CityPickerView();


    @Override
    protected void initRestore(@Nullable Bundle savedInstanceState) {
    }

    @Override
    protected int getContentViewId() {
        return R.layout.activity_icitypicker_wheel;
    }

    @Override
    protected void initView(View mRootView) {
        StatusBarTool.setRootViewFitsSystemWindows(_mActivity,true);
        StatusBarTool.setStatusBarColor(_mActivity,Color.parseColor(defalutYellow));
        setWheelType(mWheelType);
        mCityPickerView.init(this);
    }

    @Override
    protected void initData() {

    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.submit_tv://选择城市
                wheel();
                break;
            case R.id.reset_setting_tv://重置属性
                reset();
                break;
            case R.id.one_tv://一级联动，只显示省份，不显示市和区
                mWheelType = CityConfig.WheelType.PRO;
                setWheelType(mWheelType);
                break;
            case R.id.two_tv://二级联动，只显示省份， 市，不显示区
                mWheelType = CityConfig.WheelType.PRO_CITY;
                setWheelType(mWheelType);
                break;
            case R.id.three_tv://三级联动，显示省份， 市和区
                mWheelType = CityConfig.WheelType.PRO_CITY_DIS;
                setWheelType(mWheelType);
                break;
            default:
                break;
        }
    }
    @Override
    protected void addListener() {
        mSubmitTv.setOnClickListener(this);
        mResetSettingTv.setOnClickListener(this);
        mOneTv.setOnClickListener(this);
        mTwoTv.setOnClickListener(this);
        mThreeTv.setOnClickListener(this);
        mProCyclicCk.setOnClickListener(this);
        mCityCyclicCk.setOnClickListener(this);
        mAreaCyclicCk.setOnClickListener(this);
        mHalfBgCk.setOnClickListener(this);
        mGATCk.setOnClickListener(this);

        //省份是否循环显示
        mProCyclicCk.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                isProvinceCyclic = isChecked;
            }
        });

        //市是否循环显示
        mCityCyclicCk.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                isCityCyclic = isChecked;
            }
        });

        //区是否循环显示
        mAreaCyclicCk.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                isDistrictCyclic = isChecked;
            }
        });

        //半透明背景显示
        mHalfBgCk.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                isShowBg = isChecked;
            }
        });

        //港澳台数据显示
        mGATCk.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                isShowGAT = isChecked;
            }
        });
    }

    @Override
    public BasePresenter getPresenter() {
        return null;
    }




    /**
     * 重置属性
     */
    private void reset() {
        visibleItems = 5;
        isProvinceCyclic = true;
        isCityCyclic = true;
        isDistrictCyclic = true;
        isShowBg = true;
        isShowGAT = true;

        defaultProvinceName = "江苏";
        defaultCityName = "南京";
        defaultDistrict = "栖霞区";
        mWheelType = CityConfig.WheelType.PRO_CITY_DIS;
        setWheelType(mWheelType);
        mProCyclicCk.setChecked(true);
        mCityCyclicCk.setChecked(true);
        mAreaCyclicCk.setChecked(true);
        mGATCk.setChecked(true);
        mProEt.setText("" + defaultProvinceName);
        mCityEt.setText("" + defaultCityName);
        mAreaEt.setText("" + defaultDistrict);
        mProVisibleCountEt.setText("" + visibleItems);
        mHalfBgCk.setChecked(isShowBg);
        mProCyclicCk.setChecked(isProvinceCyclic);
        mAreaCyclicCk.setChecked(isDistrictCyclic);
        mCityCyclicCk.setChecked(isCityCyclic);
        mGATCk.setChecked(isShowGAT);
        setWheelType(mWheelType);
    }

    /**
     * @param wheelType
     */
    private void setWheelType(CityConfig.WheelType wheelType) {
        if (wheelType == CityConfig.WheelType.PRO) {
            mOneTv.setBackgroundResource(R.drawable.city_wheeltype_selected);
            mTwoTv.setBackgroundResource(R.drawable.city_wheeltype_normal);
            mThreeTv.setBackgroundResource(R.drawable.city_wheeltype_normal);
            mOneTv.setTextColor(Color.parseColor("#ffffff"));
            mTwoTv.setTextColor(Color.parseColor("#333333"));
            mThreeTv.setTextColor(Color.parseColor("#333333"));
        } else if (wheelType == CityConfig.WheelType.PRO_CITY) {
            mOneTv.setBackgroundResource(R.drawable.city_wheeltype_normal);
            mTwoTv.setBackgroundResource(R.drawable.city_wheeltype_selected);
            mThreeTv.setBackgroundResource(R.drawable.city_wheeltype_normal);
            mOneTv.setTextColor(Color.parseColor("#333333"));
            mTwoTv.setTextColor(Color.parseColor("#ffffff"));
            mThreeTv.setTextColor(Color.parseColor("#333333"));
        } else {
            mOneTv.setBackgroundResource(R.drawable.city_wheeltype_normal);
            mTwoTv.setBackgroundResource(R.drawable.city_wheeltype_normal);
            mThreeTv.setBackgroundResource(R.drawable.city_wheeltype_selected);
            mOneTv.setTextColor(Color.parseColor("#333333"));
            mTwoTv.setTextColor(Color.parseColor("#333333"));
            mThreeTv.setTextColor(Color.parseColor("#ffffff"));
        }
    }

    /**
     * 弹出选择器
     */
    private void wheel() {
        defaultProvinceName = mProEt.getText().toString();
        defaultCityName = mCityEt.getText().toString();
        defaultDistrict = mAreaEt.getText().toString();
        visibleItems = (Integer.parseInt(mProVisibleCountEt.getText().toString()));
        CityConfig cityConfig = new CityConfig.Builder()
                .title("选择城市")
                .visibleItemsCount(visibleItems)
                .province(defaultProvinceName)
                .city(defaultCityName)
                .district(defaultDistrict)
                .provinceCyclic(isProvinceCyclic)
                .cityCyclic(isCityCyclic)
                .districtCyclic(isDistrictCyclic)
                .setCityWheelType(mWheelType)
                .setCustomItemLayout(R.layout.item_city_wheel)//自定义item的布局
                .setCustomItemTextViewId(R.id.item_city_name_tv)
                .setShowGAT(isShowGAT)
                .build();

        mCityPickerView.setConfig(cityConfig);
        mCityPickerView.setOnCityItemClickListener(new OnCityItemClickListener() {
            @Override
            public void onSelected(ProvinceBean province, CityBean city, DistrictBean district) {
                StringBuilder sb = new StringBuilder();
                sb.append("选择的结果：\n");
                if (province != null) {
                    sb.append(province.getName() + " " + province.getId() + "\n");
                }

                if (city != null) {
                    sb.append(city.getName() + " " + city.getId() + ("\n"));
                }

                if (district != null) {
                    sb.append(district.getName() + " " + district.getId() + ("\n"));
                }

                mResultTv.setText("" + sb.toString());

            }

            @Override
            public void onCancel() {
                iToast.showISimpleToast("已取消");
            }
        });
        mCityPickerView.showCityPicker();
    }

    public void ivback(View view) {
        finish();
    }

}
