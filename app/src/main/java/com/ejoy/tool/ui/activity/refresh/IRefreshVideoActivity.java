package com.ejoy.tool.ui.activity.refresh;
//  ┏┓　　　┏┓
//┏┛┻━━━┛┻┓
//┃　　　　　　　┃
//┃　　　━　　　┃
//┃　┳┛　┗┳　┃
//┃　　　　　　　┃
//┃　　　┻　　　┃
//┃　　　　　　　┃
//┗━┓　　　┏━┛
//    ┃　　　┃                  神兽保佑
//    ┃　　　┃                  永无BUG！
//    ┃　　　┗━━━┓
//    ┃　　　　　　　┣┓
//    ┃　　　　　　　┏┛
//    ┗┓┓┏━┳┓┏┛
//      ┃┫┫　┃┫┫
//      ┗┻┛　┗┻┛

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ejoy.tool.R;
import com.ejoy.tool.scaffold.utils.StatusBarTool;
import com.ejoy.tool.scaffold.view.decorator.DashlineItemDivider;
import com.ejoy.tool.scaffold.view.refresh.SmartRefreshHeader;
import com.ejoy.tool.scaffold.view.refresh.SmartVideoHeader;
import com.ejoy.tool.ui.base.base_activity.BaseActivity;
import com.ejoy.tool.ui.mvp.base.BasePresenter;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

import static android.view.View.VISIBLE;

/**
 * CN:      IRefreshSmartActivity
 * Author： JSYL-DINGCL (dingcl@jsyl.com.cn)
 * Date:   2019/12/25
 * Des:    TODO:下拉刷新-视频列表头部
 */
public class IRefreshVideoActivity extends BaseActivity {
    @BindView(R.id.mVideoRefreshLayout)
    SmartRefreshLayout mVideoRefreshLayout;
    @BindView(R.id.mRecyclerView)
    RecyclerView mRecyclerView;

    private SmartVideoHeader mSmartVideoHeader;
    private static int refreshTime = 1000;
    private long delayMillis = 2000;
    private BaseQuickAdapter mAdapter;
    private List<String> videoList = new ArrayList<>();

    @Override
    protected void initRestore(@Nullable Bundle savedInstanceState) {

    }

    @Override
    protected int getContentViewId() {
        return R.layout.activity_irefresh_videoheader;
    }

    @Override
    protected void initView(View mRootView) {
        StatusBarTool.setRootViewFitsSystemWindows(this,true);
        StatusBarTool.setStatusBarColor(this,Color.parseColor(defalutStatus2));
        mSmartVideoHeader = new SmartVideoHeader(_mActivity);
        mVideoRefreshLayout.setRefreshHeader(mSmartVideoHeader);
        mVideoRefreshLayout.setEnableScrollContentWhenLoaded(true);
        mVideoRefreshLayout.setEnableFooterFollowWhenLoadFinished(true);
        mVideoRefreshLayout.setHeaderHeight(60);
        mVideoRefreshLayout.autoRefresh();
        mRecyclerView.setLayoutManager(new LinearLayoutManager(_mActivity));
        mRecyclerView.addItemDecoration(new DashlineItemDivider("#fff2f3f5"));
        mRecyclerView.setAdapter(mAdapter = new BaseQuickAdapter<String, BaseViewHolder>(R.layout.item_irefresh_video, videoList) {
            @Override
            protected void convert(BaseViewHolder helper, String item) {
                helper.setText(R.id.tvVideoTitle,item+"");
            }
        });
        mAdapter.openLoadAnimation(2);
    }

    @Override
    protected void initData() {
        getVideoData();
    }

    private void getVideoData() {
        videoList.clear();
        for (int i = 0; i < 15; i++) {
            i+=1;
            videoList.add(i+" : 好尴尬,按下音箱而已…");
        }
        TextView tvtitle = mSmartVideoHeader.getTvtitle();
        tvtitle.setVisibility(VISIBLE);
        tvtitle.setText("发现"+videoList.size()+"条精选视频");
        mAdapter.setNewData(videoList);
        mVideoRefreshLayout.finishRefresh();
    }

    @Override
    protected void addListener() {
        mVideoRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh ( @NonNull final RefreshLayout refreshLayout){
                refreshLayout.getLayout().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        new Handler().postDelayed(new Runnable(){
                            public void run(){
                                //execute the task
                                getVideoData();

                            }
                        },delayMillis);
                    }
                }, refreshTime);
            }
        });
    }

    @Override
    public BasePresenter getPresenter() {
        return null;
    }

    public void iBack(View view) {
        finish();
    }
}
