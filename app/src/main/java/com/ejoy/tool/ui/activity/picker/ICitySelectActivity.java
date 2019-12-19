package com.ejoy.tool.ui.activity.picker;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Toast;

import com.ejoy.tool.R;
import com.ejoy.tool.scaffold.utils.ActivityUtils;
import com.ejoy.tool.scaffold.utils.StatusBarTool;
import com.ejoy.tool.ui.activity.MainActivity;
import com.ejoy.tool.ui.base.base_activity.BaseActivity;
import com.ejoy.tool.ui.mvp.base.BasePresenter;
import com.imaiduoduo.datetime.DatePickDialog;

import java.util.Date;

/**
 * CN:      ITimeDateOrActivity
 * Author： JSYL-DINGCL (dingcl@jsyl.com.cn)
 * Date:   2019/12/13
 * Des:    城市选择器
 */
public class ICitySelectActivity extends BaseActivity {


    @Override
    protected void initRestore(@Nullable Bundle savedInstanceState) {

    }

    @Override
    protected int getContentViewId() {
        return R.layout.activity_icity_picker;
    }

    @Override
    protected void initView(View mRootView) {
        StatusBarTool.setRootViewFitsSystemWindows(_mActivity,true);
        StatusBarTool.setStatusBarColor(_mActivity,Color.parseColor("#ffffd800"));
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



    public void ivBack(View view) {
        finish();
    }



    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    /**
     * 城市列表
     * @param view
     */
    public void citySelectList(View view) {
        showActivity(ICitySelectActivity.this, ICitypickerListActivity.class);

    }

    public void FIosSelect(View view) {
        ActivityUtils.getInstance().showActivity(_mActivity, ICitypickerWheelActivity.class);
    }

    /**
     * 三级列表
     * @param view
     */
    public void ThreeLevelSelect(View view) {
        ActivityUtils.getInstance().showActivity(_mActivity, ICitypickerThreeListActivity.class);
    }

    /**
     * 仿京东选择地址
     * @param view
     */
    public void onJDAddressSelect(View view) {
    }

    /**
     * 自定义数据源
     * @param view
     */
    public void customRes(View view) {
    }
}
