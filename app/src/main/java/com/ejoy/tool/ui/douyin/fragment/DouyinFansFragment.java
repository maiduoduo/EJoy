package com.ejoy.tool.ui.douyin.fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.ejoy.tool.R;
import com.ejoy.tool.ui.base.base_fragment.BaseFragment;
import com.ejoy.tool.ui.douyin.data.adapter.DouyinFansAdapter;
import com.ejoy.tool.ui.douyin.data.constant.DouyinDataCreate;

import butterknife.BindView;


public class DouyinFansFragment extends BaseFragment {
    @BindView(R.id.recyclerview)
    RecyclerView recyclerView;
    private DouyinFansAdapter fansAdapter;



    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_douyin_fans;
    }

    @Override
    public void intBase() {

    }

    @Override
    public void initPresenter() {

    }

    @Override
    protected void initView(View rootView) {
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        fansAdapter = new DouyinFansAdapter(R.layout.item_douyin_fans,DouyinDataCreate.userList,mActivity);
        recyclerView.setAdapter(fansAdapter);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initListener() {

    }
}
