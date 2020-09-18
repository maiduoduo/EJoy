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
 * @ClassName:  ISLStickyActivity
 * @Author: maiduoduo
 * @BLOG: https://blog.csdn.net/Maiduoudo
 * @Date: 2020/9/18
 * @des:  ScrollerLayout 布局吸顶
 */
@Layout(R.layout.activity_scrollerlayout_sticky)
@DarkStatusBarTheme(true)
public class ISLStickyActivity extends IBaseActivity {

    @BindView(R.id.scrollerLayout1)
    IConsecutiveScrollerLayout scrollerLayout;
    @BindView(R.id.blur)
    BlurView blur;

    private List<String> strList;
    private List<String> str1List;
    private List<String> str2List;
    private BaseQuickAdapter mSLSampleRecyclerViewAdapter1;
    private BaseQuickAdapter mSLSampleRecyclerViewAdapter2;
    private BaseQuickAdapter mSLSampleRecyclerViewAdapter3;

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
        if (str2List != null) str2List.clear();
        else str2List = new ArrayList<>();
        WebView webView = findViewById(R.id.webView);
        webView.loadUrl("https://github.com/maiduoduo");

        webView.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                super.onProgressChanged(view, newProgress);
                // 在webView加载的过程中，用户滚动了webView内容，可能会使webView的显示与scrollerLayout断层，
                // 需要让scrollerLayout重新检查一下所有View的显示位置
                if (scrollerLayout != null) {
                    scrollerLayout.checkLayoutChange();
                }
            }
        });

        initList();



        // 设置吸顶到顶部的距离
//        scrollerLayout.setStickyOffset(50);
    }


    @Override
    public void initDatas() {
        getRecyclerViewData(0);
        getRecyclerViewData(1);
        getRecyclerViewData(2);
    }

    private void getRecyclerViewData(int i) {
        strList.clear();
        for (int j = 0; j < 30; j++) {
            if (0 == i) strList.add("这是滑动列表的第"+i+"个的第");
            if (1 == i) str1List.add("这是滑动列表的第"+i+"个的第");
            if (2 == i) str2List.add("这是滑动列表的第"+i+"个的第");
        }
    }

    @Override
    public void setEvents() {
        // 监听吸顶view
        scrollerLayout.setOnStickyChangeListener(new IConsecutiveScrollerLayout.OnStickyChangeListener() {
            @Override
            public void onStickyChange(@Nullable View oldStickyView, @Nullable View newStickyView) {
                Log.e(_TAG,"-----监听吸顶view------:"+oldStickyView + "--newStickyView--" + newStickyView);
            }
        });
    }


    /**
     * 初始化列表
     */
    private void initList() {
        RecyclerView recyclerView1 = findViewById(R.id.recyclerView1);
        recyclerView1.setLayoutManager(new LinearLayoutManager(this));
//        SLSampleRecyclerViewAdapter adapter1 = new SLSampleRecyclerViewAdapter(this,"RecyclerView1-");
        recyclerView1.setAdapter(mSLSampleRecyclerViewAdapter1 = new BaseQuickAdapter<String, BaseViewHolder>(android.R.layout.simple_list_item_1, strList) {
            @Override
            protected void convert(BaseViewHolder helper, String item) {
                int adapterPosition = helper.getAdapterPosition();
                helper.setText(android.R.id.text1,item+adapterPosition+"条");
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

        RecyclerView recyclerView3 = findViewById(R.id.recyclerView3);
        recyclerView3.setLayoutManager(new LinearLayoutManager(this));
        recyclerView2.setAdapter(mSLSampleRecyclerViewAdapter3 = new BaseQuickAdapter<String, BaseViewHolder>(android.R.layout.simple_list_item_1, str2List) {
            @Override
            protected void convert(BaseViewHolder helper, String item) {
                int adapterPosition = helper.getAdapterPosition();
                helper.setText(android.R.id.text1,item+adapterPosition+"条");
            }
        });
        recyclerView3.setAdapter(mSLSampleRecyclerViewAdapter3);
    }
}
