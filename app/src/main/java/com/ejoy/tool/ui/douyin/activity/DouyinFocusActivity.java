package com.ejoy.tool.ui.douyin.activity;

import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import com.androidkun.xtablayout.XTabLayout;
import com.ejoy.tool.R;
import com.ejoy.tool.ui.base.base_activity.IBaseActivity;
import com.ejoy.tool.ui.douyin.data.adapter.DouyinCommPagerAdapter;
import com.ejoy.tool.ui.douyin.fragment.DouyinFansFragment;
import com.kongzue.baseframework.interfaces.DarkStatusBarTheme;
import com.kongzue.baseframework.interfaces.Layout;

import java.util.ArrayList;
import butterknife.BindView;


/**
 * @ClassName:  DouyinFocusActivity
 * @Author: maiduoduo
 * @BLOG: https://blog.csdn.net/Maiduoudo
 * @Date: 2021/2/4
 * @des: 粉丝关注人页面
 */
@Layout(R.layout.activity_douyin_focus)
@DarkStatusBarTheme(true)
public class DouyinFocusActivity extends IBaseActivity {
    @BindView(R.id.tablayout)
    XTabLayout tabLayout;
    @BindView(R.id.viewpager)
    ViewPager viewPager;
    private ArrayList<Fragment> fragments = new ArrayList<>();
    private DouyinCommPagerAdapter pagerAdapter;
    private String[] titles = new String[] {"关注 15", "粉丝 1820", "推荐关注"};



    @Override
    public void initViews() {
        for (int i=0;i<titles.length;i++) {
            fragments.add(new DouyinFansFragment());
            tabLayout.addTab(tabLayout.newTab().setText(titles[i]));
        }

        pagerAdapter = new DouyinCommPagerAdapter(getSupportFragmentManager(), fragments, titles);
        viewPager.setAdapter(pagerAdapter);
        tabLayout.setupWithViewPager(viewPager);
    }

    @Override
    public void initDatas() {

    }

    @Override
    public void setEvents() {

    }
}
