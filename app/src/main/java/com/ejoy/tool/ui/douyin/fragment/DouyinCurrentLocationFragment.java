package com.ejoy.tool.ui.douyin.fragment;

import android.os.CountDownTimer;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;

import com.ejoy.tool.R;
import com.ejoy.tool.ui.base.base_fragment.BaseFragment;
import com.ejoy.tool.ui.douyin.data.adapter.DouyinGridVideoAdapter;
import com.ejoy.tool.ui.douyin.data.constant.DouyinDataCreate;

import butterknife.BindView;

/**
 * @ClassName:  DouyinCurrentLocationFragment
 * @Author: maiduoduo
 * @BLOG: https://blog.csdn.net/Maiduoudo
 * @Date: 2021/2/2
 * @des: 附近的人fragment
 */
public class DouyinCurrentLocationFragment extends BaseFragment {
    @BindView(R.id.recyclerview)
    RecyclerView recyclerView;
    private DouyinGridVideoAdapter adapter;

    @BindView(R.id.refreshlayout)
    SwipeRefreshLayout refreshLayout;

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_douyin_current_location;
    }

    @Override
    public void intBase() {

    }

    @Override
    public void initPresenter() {

    }

    @Override
    protected void initView(View rootView) {
        new DouyinDataCreate().initData();

        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));

        adapter = new DouyinGridVideoAdapter(R.layout.item_douyin_gridvideo, DouyinDataCreate.datas,getActivity());
        recyclerView.setAdapter(adapter);
        refreshLayout.setColorSchemeResources(R.color.color_douyin_link);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initListener() {
        refreshLayout.setOnRefreshListener(() -> new CountDownTimer(1000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {

            }

            @Override
            public void onFinish() {
                refreshLayout.setRefreshing(false);
            }
        }.start());
    }


}
