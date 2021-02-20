package com.ejoy.tool.ui.activity.more_detail.scrollerlayout;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.ejoy.tool.R;
import com.ejoy.tool.ui.base.base_fragment.BaseFragment;
import com.ejoy.tool.ui.data.adapter.ISLTabRecyclerViewAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * @ClassName:  ISLTabFragment
 * @Author: maiduoduo
 * @BLOG: https://blog.csdn.net/Maiduoudo
 * @Date: 2021/2/19
 * @des: ISLTabFragment
 */
public class ISLTabFragment extends BaseFragment {

    @BindView(R.id.recyclerView1)
    RecyclerView recyclerView1;
    @BindView(R.id.recyclerView2)
    RecyclerView recyclerView2;

    private List<String> tabList;

    @Override
    protected int getLayoutResource(){
        return R.layout.fragment_sl_tab;
    }

    @Override
    public void intBase() {

    }

    @Override
    public void initPresenter() {

    }

    @Override
    protected void initView(View rootView) {
        if (tabList != null) tabList.clear();
        else tabList = new ArrayList<>();

        recyclerView1.setLayoutManager(new LinearLayoutManager(getActivity()));
        ISLTabRecyclerViewAdapter adapter1 = new ISLTabRecyclerViewAdapter(R.layout.item_adapter_recycler_sl_tab, tabList, mActivity);
        recyclerView1.setAdapter(adapter1);
        recyclerView2.setLayoutManager(new LinearLayoutManager(getActivity()));
        ISLTabRecyclerViewAdapter adapter2 = new ISLTabRecyclerViewAdapter(R.layout.item_adapter_recycler_sl_tab, tabList, mActivity);
        recyclerView2.setAdapter(adapter2);

    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initListener() {

    }

}
