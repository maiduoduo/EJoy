package com.ejoy.tool.ui.activity.tab;

import android.graphics.Color;
import android.view.View;

import com.ejoy.tool.R;
import com.ejoy.tool.scaffold.utils.StatusBarTool;
import com.ejoy.tool.ui.base.base_activity.IBaseActivity;
import com.kongzue.baseframework.interfaces.DarkStatusBarTheme;
import com.kongzue.baseframework.interfaces.Layout;
import com.module.iviews.easynavigationbar.ui.NavigationBarAddAsFragmentActivity;
import com.module.iviews.easynavigationbar.ui.NavigationBarAddViewActivity;
import com.module.iviews.easynavigationbar.ui.NavigationBarAllActivity;
import com.module.iviews.easynavigationbar.ui.NavigationBarNormalActivity;
import com.module.iviews.easynavigationbar.ui.NavigationBarOnlyNavigationActivity;
import com.module.iviews.easynavigationbar.ui.NavigationBarSetUpViewPagerActivity;
import com.module.iviews.easynavigationbar.ui.NavigationBarWeiboActivity;

/**
 * @ClassName: INavigationBarActivity
 * @Author: bsj-dingcl
 * @Email: dingchenglei@h4kit.com
 * @Date: 2020/08/22
 * @des: 底部导航栏
 *    整理自：https://github.com/Vincent7Wong/EasyNavigation
 */
@Layout(R.layout.activity_navigationbar)
@DarkStatusBarTheme(true)
public class INavigationBarActivity extends IBaseActivity {

    @Override
    protected boolean isRegistSatusbarFullScreenTransluent() {
        return true;
    }

    @Override
    public void initViews() {
        StatusBarTool.setRootViewFitsSystemWindows(me, true);
        StatusBarTool.setStatusBarColor(me, Color.parseColor(baseThemeColor));
    }

    @Override
    public void initDatas() {
    }

    @Override
    public void setEvents() {



    }


    public void NoAddNavigationBar(View view) {
        jump(NavigationBarNormalActivity.class);
    }


    public void AddAsFragment(View view) {
        jump(NavigationBarAddAsFragmentActivity.class);
    }

    public void AddView(View view) {
        jump(NavigationBarAddViewActivity.class);
    }

    public void OnWeibo(View view) {
        jump(NavigationBarWeiboActivity.class);
    }

    public void OnlyNavigation(View view) {
        jump(NavigationBarOnlyNavigationActivity.class);
    }

    public void SetUpViewPagerActivity(View view) {
        jump(NavigationBarSetUpViewPagerActivity.class);
    }

    public void AllActivity(View view) {
        jump(NavigationBarAllActivity.class);
    }
}
