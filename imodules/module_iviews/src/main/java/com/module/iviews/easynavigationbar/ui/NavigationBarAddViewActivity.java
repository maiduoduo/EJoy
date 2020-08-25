package com.module.iviews.easynavigationbar.ui;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;

import com.module.iviews.R;
import com.module.iviews.easynavigationbar.ui.normal.AFragment;
import com.module.iviews.easynavigationbar.ui.normal.BFragment;
import com.module.iviews.easynavigationbar.ui.normal.CFragment;
import com.module.iviews.easynavigationbar.ui.normal.DFragment;
import com.next.easynavigation.view.EasyNavigationBar;

import java.util.ArrayList;
import java.util.List;

public class NavigationBarAddViewActivity extends AppCompatActivity {

    private EasyNavigationBar navigationBar;

    private String[] tabText =  {"聊天", "通讯录", "发现", "我的"};
    //未选中icon
    private int[] normalIcon = {R.mipmap.eicon_navigation_index, R.mipmap.eicon_navigation_find, R.mipmap.eicon_navigation_message,R.mipmap.eicon_navigation_me};
    //选中时icon
    private int[] selectIcon = {R.mipmap.eicon_navigation_index1, R.mipmap.eicon_navigation_find1, R.mipmap.eicon_navigation_message1, R.mipmap.eicon_navigation_me1};

    private List<Fragment> fragments = new ArrayList<>();
    private Handler mHandler = new Handler();
    private boolean flag = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_view);

        navigationBar = findViewById(R.id.navigationBar);

        fragments.add(new AFragment());
        fragments.add(new BFragment());
        fragments.add(new CFragment());
        fragments.add(new DFragment());


        View view = LayoutInflater.from(this).inflate(R.layout.layout_custom_add_view, null);

        navigationBar.titleItems(tabText)
                .normalIconItems(normalIcon)
                .selectIconItems(selectIcon)
                .fragmentList(fragments)
                .canScroll(true)
                .mode(EasyNavigationBar.NavigationMode.MODE_ADD_VIEW)
                .addCustomView(view)
                .fragmentManager(getSupportFragmentManager())
                .setOnTabClickListener(new EasyNavigationBar.OnTabClickListener() {
                    @Override
                    public boolean onTabSelectEvent(View view, int position) {
                        return false;
                    }

                    @Override
                    public boolean onTabReSelectEvent(View view, int position) {
                        return false;
                    }

                })
                .setOnCenterTabClickListener(new EasyNavigationBar.OnCenterTabSelectListener() {
                    @Override
                    public boolean onCenterTabSelectEvent(View view) {
                        mHandler.post(new Runnable() {
                            @Override
                            public void run() {
                                //＋ 旋转动画
                                if (flag) {
                                    navigationBar.getCustomAddView().animate().rotation(180).setDuration(400);
                                } else {
                                    navigationBar.getCustomAddView().animate().rotation(0).setDuration(400);
                                }
                                flag = !flag;
                            }
                        });
                        return false;
                    }
                })
                .build();

    }

    public EasyNavigationBar getNavigationBar() {
        return navigationBar;
    }

}
