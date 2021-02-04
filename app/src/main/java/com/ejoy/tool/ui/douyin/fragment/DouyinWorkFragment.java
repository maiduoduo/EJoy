package com.ejoy.tool.ui.douyin.fragment;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.ejoy.tool.R;
import com.ejoy.tool.ui.base.base_fragment.BaseFragment;
import com.ejoy.tool.ui.douyin.data.adapter.DouyinWorkAdapter;
import com.ejoy.tool.ui.douyin.data.constant.DouyinDataCreate;

import butterknife.BindView;


/**
 * @ClassName:  DouyinWorkFragment
 * @Author: maiduoduo
 * @BLOG: https://blog.csdn.net/Maiduoudo
 * @Date: 2021/2/2
 * @des: 个人作品fragment
 */
public class DouyinWorkFragment extends BaseFragment {
    @BindView(R.id.recyclerview)
    RecyclerView recyclerView;
    private DouyinWorkAdapter workAdapter;



    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_work;
    }

    @Override
    public void intBase() {

    }

    @Override
    public void initPresenter() {

    }

    @Override
    protected void initView(View rootView) {
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 3));
        workAdapter = new DouyinWorkAdapter(R.layout.item_douyin_work,DouyinDataCreate.datas,getActivity());
        recyclerView.setAdapter(workAdapter);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initListener() {

    }
}
