package com.ejoy.tool.ui.douyin.fragment;


import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.androidkun.xtablayout.XTabLayout;
import com.ejoy.tool.R;
import com.ejoy.tool.ui.base.base_fragment.BaseFragment;
import com.ejoy.tool.ui.douyin.bean.DouyinPauseVideoEvent;
import com.ejoy.tool.ui.douyin.data.adapter.DouyinCommPagerAdapter;
import com.ejoy.tool.ui.douyin.utils.RxBus;

import java.util.ArrayList;

import butterknife.BindView;



/**
 * @ClassName:  DouyinMainFragment
 * @Author: maiduoduo
 * @BLOG: https://blog.csdn.net/Maiduoudo
 * @Date: 2021/2/2
 * @des: 主页fragment
 */
public class DouyinMainFragment extends BaseFragment {
    private DouyinCurrentLocationFragment currentLocationFragment;
    private DouyinRecommendFragment recommendFragment;
    @BindView(R.id.viewpager)
    ViewPager viewPager;
    @BindView(R.id.tab_title)
    XTabLayout tabTitle;
    @BindView(R.id.tab_mainmenu)
    XTabLayout tabMainMenu;
    private ArrayList<Fragment> fragments = new ArrayList<>();
    private DouyinCommPagerAdapter pagerAdapter;
    /** 默认显示第一页推荐页 */
    public static int curPage = 1;

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_douyin_main;
    }

    @Override
    public void intBase() {

    }

    @Override
    public void initPresenter() {

    }

    @Override
    protected void initView(View rootView) {
        setFragments();
        setMainMenu();
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initListener() {

    }



    private void setFragments() {
        currentLocationFragment = new DouyinCurrentLocationFragment();
        recommendFragment = new DouyinRecommendFragment();
        fragments.add(currentLocationFragment);
        fragments.add(recommendFragment);

        tabTitle.addTab(tabTitle.newTab().setText("杭州"));
        tabTitle.addTab(tabTitle.newTab().setText("推荐"));

        pagerAdapter = new DouyinCommPagerAdapter(getChildFragmentManager(), fragments, new String[] {"杭州", "推荐"});
        viewPager.setAdapter(pagerAdapter);
        tabTitle.setupWithViewPager(viewPager);

        tabTitle.getTabAt(1).select();

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                curPage = position;
                if (position == 1) {
                    //继续播放
                    RxBus.getDefault().post(new DouyinPauseVideoEvent(true));
                } else {
                    //切换到其他页面，需要暂停视频
                    RxBus.getDefault().post(new DouyinPauseVideoEvent(false));
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private void setMainMenu() {
        tabMainMenu.addTab(tabMainMenu.newTab().setText("首页"));
        tabMainMenu.addTab(tabMainMenu.newTab().setText("好友"));
        tabMainMenu.addTab(tabMainMenu.newTab().setText(""));
        tabMainMenu.addTab(tabMainMenu.newTab().setText("消息"));
        tabMainMenu.addTab(tabMainMenu.newTab().setText("我"));
    }

}
