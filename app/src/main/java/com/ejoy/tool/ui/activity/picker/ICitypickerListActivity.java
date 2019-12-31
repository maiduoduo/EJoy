package com.ejoy.tool.ui.activity.picker;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;

import com.ejoy.tool.R;
import com.ejoy.tool.common.db.CitysBean;
import com.ejoy.tool.common.listener.OnPickListener;
import com.ejoy.tool.scaffold.utils.StatusBarTool;
import com.ejoy.tool.ui.base.base_activity.BaseActivity;
import com.ejoy.tool.ui.fragment.citylist.CityPicker;
import com.ejoy.tool.ui.fragment.citylist.model.City;
import com.ejoy.tool.ui.fragment.citylist.model.HotCity;
import com.ejoy.tool.ui.fragment.citylist.model.LocateState;
import com.ejoy.tool.ui.fragment.citylist.model.LocatedCity;
import com.ejoy.tool.ui.mvp.base.BasePresenter;
import com.imaidd.citypicker.style.citylist.CityListSelectActivity;
import com.imaidd.citypicker.style.citylist.bean.CityInfoBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * CN:      ICitypickerListActivity
 * Author： JSYL-DINGCL (dingcl@jsyl.com.cn)
 * Date:   2019/12/15
 * Des:    仿美团城市列表选择
 */
public class ICitypickerListActivity extends BaseActivity implements CompoundButton.OnCheckedChangeListener {

    @BindView(R.id.result_tv) TextView mResultTv;
    private static final String KEY = "current_theme";
    @BindView(R.id.cb_hot)
    CheckBox hotCB;
    @BindView(R.id.cb_enable_anim)
    CheckBox enableCB;
    @BindView(R.id.cb_anim)
    CheckBox animCB;
    @BindView(R.id.btn_style)
    Button themeBtn;
    @BindView(R.id.selectCity)
    TextView selectCity;
    private List<HotCity> hotCities;
    private int anim;
    private int theme;
    private boolean enable;


    @Override
    protected void initRestore(@Nullable Bundle savedInstanceState) {
        if (savedInstanceState != null) {
            theme = savedInstanceState.getInt(KEY);
            setTheme(theme > 0 ? theme : R.style.DefaultCityPickerTheme);
        }
    }

    @Override
    protected boolean isRegistSatusbarFullScreenTransluent() {
        return true;
    }

    @Override
    protected int getContentViewId() {
        return R.layout.activity_icitypicker_list;
    }

    @Override
    protected void initView(View mRootView) {
        if (theme == R.style.DefaultCityPickerTheme){
            themeBtn.setText("默认主题");
        }else if (theme == R.style.CPCustomTheme){
            themeBtn.setText("自定义主题");
        }

        hotCB.setOnCheckedChangeListener(this);
        animCB.setOnCheckedChangeListener(this);
        enableCB.setOnCheckedChangeListener(this);

        themeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (themeBtn.getText().toString().startsWith("自定义")){
                    themeBtn.setText("默认主题");
                    theme = R.style.DefaultCityPickerTheme;
                }else if (themeBtn.getText().toString().startsWith("默认")){
                    themeBtn.setText("自定义主题");
                    theme = R.style.CPCustomTheme;
                }
                recreate();
            }
        });
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void addListener() {

    }

    @Override
    public BasePresenter getPresenter() {
        return null;
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CityListSelectActivity.CITY_SELECT_RESULT_FRAG) {
            if (resultCode == RESULT_OK) {
                if (data == null) {
                    return;
                }
                Bundle bundle = data.getExtras();
                CityInfoBean cityInfoBean = (CityInfoBean) bundle.getParcelable("cityinfo");
                if (null == cityInfoBean) {
                    return;
                }
                mResultTv.setText("城市： " + cityInfoBean.getName() + "  " + cityInfoBean.getId());
            }
        }
    }

    public void ivBack(View view) {
        finish();
    }

    /**
     * 选择城市列表
     *
     * @param view
     */
    public void selectCity(View view) {
//        Intent intent = new Intent(ICitypickerListActivity.this, CityListSelectActivity.class);
//        startActivityForResult(intent, CityListSelectActivity.CITY_SELECT_RESULT_FRAG);

        CityPicker.from(this)
                .enableAnimation(enable)
                .setAnimationStyle(anim)
                .setLocatedCity(null)
                .setHotCities(hotCities)
                .setOnPickListener(new OnPickListener() {
                    @Override
                    public void onPick(int position, City data) {
                        mResultTv.setText(String.format("当前城市：%s，%s", data.getName(), data.getCode()));
                        Toast.makeText(
                                getApplicationContext(),
                                String.format("点击的数据：%s，  %s", data.getName(), data.getCode()),
                                Toast.LENGTH_SHORT)
                                .show();
                    }

                    @Override
                    public void onPick(int position, CitysBean data) {
                        mResultTv.setText(String.format("当前城市：%s，  %s", data.getC_name(), data.getC_code()));
                        Toast.makeText(
                                getApplicationContext(),
                                String.format("点击的数据：%s，%s", data.getC_name(), data.getC_code()),
                                Toast.LENGTH_SHORT)
                                .show();
                    }

                    @Override
                    public void onCancel() {
                        Toast.makeText(getApplicationContext(), "取消选择", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onLocate() {
                        //开始定位，这里模拟一下定位
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                CityPicker.from(ICitypickerListActivity.this).locateComplete(new LocatedCity("南京", "江苏", "101190101", ""), LocateState.SUCCESS);
                            }
                        }, 2000);
                    }
                })
                .show();
    }


    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(KEY, theme);
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        switch (buttonView.getId()) {
            case R.id.cb_hot:
                if (isChecked) {
                    hotCities = new ArrayList<>();
                    hotCities.add(new HotCity("北京", "北京", "101010100", ""));
                    hotCities.add(new HotCity("上海", "上海", "101020100", ""));
                    hotCities.add(new HotCity("广州", "广东", "101280101", ""));
                    hotCities.add(new HotCity("哈尔滨", "黑龙江", "101050101", ""));
                    hotCities.add(new HotCity("深圳", "广东", "101280601", ""));
                    hotCities.add(new HotCity("杭州", "浙江", "101210101", ""));
                    hotCities.add(new HotCity("苏州", "江苏", "101190401", ""));
                    hotCities.add(new HotCity("天津", "天津", "101030100", ""));
                } else {
                    hotCities = null;
                }
                break;
            case R.id.cb_anim:
                anim = isChecked ? R.style.CustomAnim : R.style.DefaultCityPickerAnimation;
                break;
            case R.id.cb_enable_anim:
                enable = isChecked;
                break;
        }
    }
}
