package com.ejoy.tool.ui.activity.refresh;
//  ┏┓　　　┏┓
//┏┛┻━━━┛┻┓
//┃　　　　　　　┃
//┃　　　━　　　┃
//┃　┳┛　┗┳　┃
//┃　　　　　　　┃
//┃　　　┻　　　┃
//┃　　　　　　　┃
//┗━┓　　　┏━┛
//    ┃　　　┃                  神兽保佑
//    ┃　　　┃                  永无BUG！
//    ┃　　　┗━━━┓
//    ┃　　　　　　　┣┓
//    ┃　　　　　　　┏┛
//    ┗┓┓┏━┳┓┏┛
//      ┃┫┫　┃┫┫
//      ┗┻┛　┗┻┛

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.ejoy.tool.R;
import com.ejoy.tool.scaffold.utils.StatusBarTool;
import com.ejoy.tool.ui.base.base_activity.BaseActivity;
import com.ejoy.tool.ui.mvp.base.BasePresenter;

/**
 * CN:      IRefreshActivity
 * Author： JSYL-DINGCL (dingcl@jsyl.com.cn)
 * Date:   2019/12/25
 * Des:    TODO:下拉刷新
 */
public class IRefreshActivity extends BaseActivity {
    @Override
    protected void initRestore(@Nullable Bundle savedInstanceState) {

    }

    @Override
    protected int getContentViewId() {
        return R.layout.activity_refresh;
    }

    @Override
    protected void initView(View mRootView) {
        StatusBarTool.setRootViewFitsSystemWindows(this,true);
        StatusBarTool.setStatusBarColor(this,Color.parseColor(defalutStatus2));
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

    public void iosBack(View view) {
        finish();
    }

    /**
     * 基于SmartRefreshLayout实现自由定制头部
     * @param view
     */
    public void smartRefresh(View view) {
        showActivity(this,IRefreshSmartActivity.class);
    }

    /**
      * Author： JSYL_Dingcl
      * Des  :   TODO: 视频下拉刷新示范
     */
    public void videoRefresh(View view) {
        showActivity(this,IRefreshVideoActivity.class);
    }

    /**
     * Author： JSYL_Dingcl
     * Des  :   TODO: 下拉刷新、上拉更多控件示范
     */
    public void pulldownView(View view) {
        showActivity(this,IRefreshHeaderAndFooterActivity.class);
    }
}
