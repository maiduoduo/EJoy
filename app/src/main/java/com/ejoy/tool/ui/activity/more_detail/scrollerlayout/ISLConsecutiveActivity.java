package com.ejoy.tool.ui.activity.more_detail.scrollerlayout;


import android.graphics.Color;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ejoy.tool.R;
import com.ejoy.tool.ui.base.base_activity.IBaseActivity;
import com.kongzue.baseframework.interfaces.DarkStatusBarTheme;
import com.kongzue.baseframework.interfaces.Layout;
import com.kongzue.dialog.util.BlurView;
import com.module.iviews.consecutivescroller.IConsecutiveScrollerLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;


/**
 * @ClassName:  ISLConsecutiveActivity
 * @Author: maiduoduo
 * @BLOG: https://blog.csdn.net/Maiduoudo
 * @Date: 2020/9/18
 * @des:  ScrollerLayout 局部滑动
 */
@Layout(R.layout.activity_scrollerlayout_consecutive)
@DarkStatusBarTheme(true)
public class ISLConsecutiveActivity extends IBaseActivity {

    @BindView(R.id.blur)
    BlurView blur;

    private List<String> strList;
    private List<String> str1List;
    private BaseQuickAdapter mSLSampleRecyclerViewAdapter1;
    private BaseQuickAdapter mSLSampleRecyclerViewAdapter2;

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
        blur.setOverlayColor(Color.argb(50, 235, 235, 235));
        blur.setRadius(me, 0, 0);
        setDarkNavigationBarTheme(false);
        if (strList != null) strList.clear();
        else strList = new ArrayList<>();
        if (str1List != null) str1List.clear();
        else str1List = new ArrayList<>();
        initList();
    }


    @Override
    public void initDatas() {
        getRecyclerViewData();
    }

    private void getRecyclerViewData() {
        strList.clear();
        str1List.clear();
        for (int j = 0; j < 30; j++) {
             strList.add("这是滑动列表的第"+0+"个的第");
        }
        for (int j = 0; j < 30; j++) {
            str1List.add("这是滑动列表的第"+1+"个的第");
        }
        mSLSampleRecyclerViewAdapter1.setNewData(strList);
        mSLSampleRecyclerViewAdapter1.setNewData(str1List);
    }

    @Override
    public void setEvents() {
    }


    /**
     * 初始化列表
     */
    private void initList() {
        RecyclerView recyclerView1 = findViewById(R.id.recyclerView1);
        recyclerView1.setLayoutManager(new LinearLayoutManager(this));
        recyclerView1.setAdapter(mSLSampleRecyclerViewAdapter1 = new BaseQuickAdapter<String, BaseViewHolder>(android.R.layout.simple_list_item_1, strList) {
            @Override
            protected void convert(BaseViewHolder helper, String item) {
                int adapterPosition = helper.getAdapterPosition();
                helper.setText(android.R.id.text1,item+adapterPosition+"条");
                Log.e(TAG, "convert: "+ item+adapterPosition+"条");
            }
        });
        recyclerView1.setAdapter(mSLSampleRecyclerViewAdapter1);


        RecyclerView recyclerView2 = findViewById(R.id.recyclerView2);
        recyclerView2.setLayoutManager(new LinearLayoutManager(this));
        recyclerView2.setAdapter(mSLSampleRecyclerViewAdapter2 = new BaseQuickAdapter<String, BaseViewHolder>(android.R.layout.simple_list_item_1, str1List) {
            @Override
            protected void convert(BaseViewHolder helper, String item) {
                int adapterPosition = helper.getAdapterPosition();
                helper.setText(android.R.id.text1,item+adapterPosition+"条");
            }
        });
        recyclerView2.setAdapter(mSLSampleRecyclerViewAdapter2);

    }
}
