package com.ejoy.tool.ui.activity.more_detail.scrollerlayout;


import android.graphics.Color;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.TextView;

import com.ejoy.tool.R;
import com.ejoy.tool.ui.base.base_activity.IBaseActivity;
import com.ejoy.tool.ui.data.adapter.ISLTabPagerAdapter;
import com.kongzue.baseframework.interfaces.DarkStatusBarTheme;
import com.kongzue.baseframework.interfaces.Layout;
import com.kongzue.dialog.util.BlurView;
import com.module.iviews.consecutivescroller.IConsecutiveScrollerLayout;
import com.module.iviews.consecutivescroller.IConsecutiveViewPager;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;


/**
 * @ClassName: ISLFragmentActivity
 * @Author: maiduoduo
 * @BLOG: https://blog.csdn.net/Maiduoudo
 * @Date: 2021/2/20
 * @des: ScrollerLayout 包裹Fragment
 */
@Layout(R.layout.activity_scrollerlayout_fragment)
@DarkStatusBarTheme(true)
public class ISLFragmentActivity extends IBaseActivity {


    @BindView(R.id.blur)
    BlurView blur;


    @Override
    protected boolean isRegistSatusbarFullScreenTransluent() {
        return true;
    }

    @Override
    protected boolean isRegistSatusbarFontDark() {
        return true;
    }

    @Override
    public void initViews() {
        blur.setOverlayColor(Color.argb(10, 235, 235, 235));
        blur.setRadius(me, 0, 0);
        setDarkNavigationBarTheme(false);

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();   // 开启一个事务
        transaction.replace(R.id.fragment_container, new ISLTabFragment());
        transaction.commit();
    }


    @Override
    public void initDatas() {
    }

    @Override
    public void setEvents() {
    }

    @OnClick({
            R.id.btn_back
    })
    public void bindEvent(View view){
        switch (view.getId()){
            case R.id.btn_back:
                finishActivity();
                break;
        }
    }


}
