package com.module.iviews.easynavigationbar.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;

import com.module.iviews.R;
import com.module.iviews.easynavigationbar.ui.normal.AFragment;
import com.module.iviews.easynavigationbar.ui.normal.BFragment;
import com.module.iviews.easynavigationbar.ui.normal.CFragment;
import com.module.iviews.easynavigationbar.ui.normal.DFragment;
import com.next.easynavigation.view.EasyNavigationBar;

import java.util.ArrayList;
import java.util.List;

public class NavigationBarNormalActivity extends AppCompatActivity {

    private EasyNavigationBar navigationBar;

    private String[] tabText =  {"聊天", "通讯录", "发现", "我的"};
    //未选中icon
    private int[] normalIcon = {R.mipmap.eicon_navigation_index, R.mipmap.eicon_navigation_find, R.mipmap.eicon_navigation_message, R.mipmap.eicon_navigation_me};
    //选中时icon
    private int[] selectIcon = {R.mipmap.eicon_navigation_index1, R.mipmap.eicon_navigation_find1, R.mipmap.eicon_navigation_message1, R.mipmap.eicon_navigation_me1};

    private List<Fragment> fragments = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_noadd);

        navigationBar = findViewById(R.id.navigationBar);

        fragments.add(new AFragment());
        fragments.add(new BFragment());
        fragments.add(new CFragment());
        fragments.add(new DFragment());

        navigationBar.titleItems(tabText)
                .normalIconItems(normalIcon)
                .selectIconItems(selectIcon)
                .fragmentList(fragments)
                .fragmentManager(getSupportFragmentManager())
                .canScroll(true)
                .build();


    }

    public EasyNavigationBar getNavigationBar() {
        return navigationBar;
    }

}
