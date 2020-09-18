package com.module.iviews.easynavigationbar.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.module.iviews.R;
import com.module.iviews.easynavigationbar.ui.normal.AFragment;
import com.module.iviews.easynavigationbar.ui.normal.BFragment;
import com.module.iviews.easynavigationbar.ui.normal.CFragment;
import com.module.iviews.easynavigationbar.ui.normal.DFragment;
import com.next.easynavigation.adapter.ViewPagerAdapter;
import com.next.easynavigation.view.EasyNavigationBar;

import java.util.ArrayList;
import java.util.List;

public class NavigationBarSetUpViewPagerActivity extends AppCompatActivity {

    private EasyNavigationBar navigationBar;

    private String[] tabText = {"首页", "发现", "消息", "我的"};
    //未选中icon
    private int[] normalIcon = {R.mipmap.eicon_navigation_index, R.mipmap.eicon_navigation_find, R.mipmap.eicon_navigation_message, R.mipmap.eicon_navigation_me};
    //选中时icon
    private int[] selectIcon = {R.mipmap.eicon_navigation_index1, R.mipmap.eicon_navigation_find1, R.mipmap.eicon_navigation_message1, R.mipmap.eicon_navigation_me1};

    private List<Fragment> fragments = new ArrayList<>();

    private ViewPager vp_setup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setup_viewpager);

        navigationBar = findViewById(R.id.navigationBar);
        vp_setup = findViewById(R.id.vp_setup);

        fragments.add(new AFragment());
        fragments.add(new BFragment());
        fragments.add(new CFragment());
        fragments.add(new DFragment());

        vp_setup.setAdapter(new ViewPagerAdapter(getSupportFragmentManager(), fragments));

        vp_setup.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                Log.e("xxx", "切换了第" + position + "个页面");
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        navigationBar.titleItems(tabText)
                .normalIconItems(normalIcon)
                .selectIconItems(selectIcon)
                .setupWithViewPager(vp_setup)
                .build();

    }


}
