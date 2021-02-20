package com.ejoy.tool.ui.activity.more_detail.scrollerlayout;


import android.graphics.Color;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
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
 * @ClassName: ISLViewpagerActivity
 * @Author: maiduoduo
 * @BLOG: https://blog.csdn.net/Maiduoudo
 * @Date: 2021/2/19
 * @des: ScrollerLayout 支持ViewPager
 */
@Layout(R.layout.activity_scrollerlayout_viewpager)
@DarkStatusBarTheme(true)
public class ISLViewpagerActivity extends IBaseActivity {

    @BindView(R.id.textHead)
    TextView textHead;
    @BindView(R.id.textHead1)
    TextView textHead1;

    @BindView(R.id.blur)
    BlurView blur;
    @BindView(R.id.scrollerLayout1)
    IConsecutiveScrollerLayout scrollerLayout;
    @BindView(R.id.iConsecutiveViewPager)
    IConsecutiveViewPager iConsecutiveViewPager;
    @BindView(R.id.iConsecutivetabLayout)
    TabLayout iConsecutivetabLayout;

    private ISLTabPagerAdapter mAdapter;

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

        textHead.setText("子view通过实现IConsecutiveScroller接口，可以使ConsecutiveScrollerLayout能正确地处理子view的下级view的滑动事件。\n" +
                "下面的例子中，通过自定义ViewPager，实现IConsecutiveScroller接口，ConsecutiveScrollerLayout能正确的处理ViewPager里" +
                "的子布局。如果ViewPager的内容是可以垂直滑动的，请使用ConsecutiveScrollerLayout或者RecyclerView等可滑动布局作为它内容的根布局。\n" +
                "下面的列子中使用ViewPager承载多个Fragment，Fragment的根布局为ConsecutiveScrollerLayout。");
        textHead1.setText("子view通过实现IConsecutiveScroller接口，可以使ConsecutiveScrollerLayout能正确地处理子view的下级view的滑动事件。\n" +
                "下面的例子中，通过自定义ViewPager，实现IConsecutiveScroller接口，ConsecutiveScrollerLayout能正确的处理ViewPager里" +
                "的子布局。如果ViewPager的内容是可以垂直滑动的，请使用ConsecutiveScrollerLayout或者RecyclerView等可滑动布局作为它内容的根布局。\n" +
                "下面的列子中使用ViewPager承载多个Fragment，Fragment的根布局为ConsecutiveScrollerLayout。");

        List<String> tabs = getTabs();
        mAdapter = new ISLTabPagerAdapter(getSupportFragmentManager(), tabs, getFragments(tabs));
        iConsecutiveViewPager.setAdapter(mAdapter);
        iConsecutivetabLayout.setupWithViewPager(iConsecutiveViewPager);

        iConsecutivetabLayout.post(new Runnable() {
            @Override
            public void run() {
                iConsecutiveViewPager.setAdjustHeight(iConsecutivetabLayout.getHeight());
            }
        });


        // 设置吸顶到顶部的距离
//        scrollerLayout.setStickyOffset(50);
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


    private List<String> getTabs() {
        List<String> tabs = new ArrayList<>();
        tabs.add("首页");
        tabs.add("推荐");
        tabs.add("搞笑");
        tabs.add("旅行");
        tabs.add("美食");
        tabs.add("自制");
        tabs.add("直播");
        tabs.add("美女");
        return tabs;
    }

    private List<Fragment> getFragments(List<String> tabs) {
        List<Fragment> fragmentList = new ArrayList<>();
        if (tabs != null && tabs.size() > 0) {
            for (int i = 0; i < tabs.size(); i++) {
                fragmentList.add(new ISLTabFragment());
            }
        }
        return fragmentList;
    }
}
