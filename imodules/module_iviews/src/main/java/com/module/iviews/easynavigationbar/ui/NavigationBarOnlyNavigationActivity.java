package com.module.iviews.easynavigationbar.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.module.iviews.R;
import com.module.iviews.easynavigationbar.ui.normal.AFragment;
import com.module.iviews.easynavigationbar.ui.normal.BFragment;
import com.module.iviews.easynavigationbar.ui.normal.CFragment;
import com.module.iviews.easynavigationbar.ui.normal.DFragment;
import com.next.easynavigation.view.EasyNavigationBar;

import java.util.ArrayList;
import java.util.List;

public class NavigationBarOnlyNavigationActivity extends AppCompatActivity {

    private EasyNavigationBar navigationBar;

    private String[] tabText =  {"聊天", "通讯录", "发现", "我的"};
    //未选中icon
    private int[] normalIcon = {R.mipmap.eicon_navigation_index, R.mipmap.eicon_navigation_find, R.mipmap.eicon_navigation_message, R.mipmap.eicon_navigation_me};
    //选中时icon
    private int[] selectIcon = {R.mipmap.eicon_navigation_index1, R.mipmap.eicon_navigation_find1, R.mipmap.eicon_navigation_message1, R.mipmap.eicon_navigation_me1};

    private List<Fragment> fragments = new ArrayList<>();

    private TextView tv_content;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_only_navigation);

        navigationBar = findViewById(R.id.navigationBar);
        tv_content = findViewById(R.id.tv_content);

        fragments.add(new AFragment());
        fragments.add(new BFragment());
        fragments.add(new CFragment());
        fragments.add(new DFragment());

        navigationBar.titleItems(tabText)
                .normalIconItems(normalIcon)
                .selectIconItems(selectIcon)
                .build();

        navigationBar.setOnTabClickListener(new EasyNavigationBar.OnTabClickListener() {

            @Override
            public boolean onTabSelectEvent(View view, int position) {
                tv_content.setText("您点击了第"+position+"个Tab，这里面没有Fragment的、只是单纯的点击");
                return false;
            }

            @Override
            public boolean onTabReSelectEvent(View view, int position) {
                Toast.makeText(NavigationBarOnlyNavigationActivity.this,"重复点击",Toast.LENGTH_SHORT).show();
                return false;
            }

        });
    }


}
