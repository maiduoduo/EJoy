package com.ejoy.tool.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.ejoy.tool.R;
import com.ejoy.tool.common.bean.MainItemBean;
import com.ejoy.tool.scaffold.view.PowerfulRecyclerView;
import com.ejoy.tool.scaffold.view.decorator.GridItemDecoration;
import com.ejoy.tool.ui.base.base_activity.BaseActivity;
import com.ejoy.tool.ui.data.adapter.CHMainAdpter;
import com.ejoy.tool.ui.data.resource.GlobalDataProvider;
import com.ejoy.tool.ui.mvp.base.BasePresenter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * CN:      MainActivity
 * Author： JSYL-DINGCL (dingcl@jsyl.com.cn)
 * Date:   2019/10/10
 * Des:    主界面
 */
public class MainActivity_1126 extends BaseActivity implements BaseQuickAdapter.OnItemClickListener {


    @BindView(R.id.mRecyclerView)
    PowerfulRecyclerView mRecyclerView;

    private String localImgUrl = "";
    private CHMainAdpter mCHMainAdpter;
    private List<MainItemBean> mData;

    @Override
    protected void initRestore(@Nullable Bundle savedInstanceState) {

    }

    @Override
    protected int getContentViewId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView(View mRootView) {
        if (mData == null) mData = new ArrayList<>();
        else mData.clear();
        initRecyclerView();
        addData();
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void addListener() {

    }


    @Override
    public BasePresenter getPresenter() {
        return null;
    }


    private void initRecyclerView() {
        GridLayoutManager linearLayoutManager = new GridLayoutManager(_mActivity, 4);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setItemViewCacheSize(10);
        mCHMainAdpter = new CHMainAdpter(R.layout.item_main_layout, mData, _mActivity);
        mCHMainAdpter.setOnItemClickListener(this);
        mRecyclerView.setAdapter(mCHMainAdpter);
        // 打开动画效果
        mCHMainAdpter.openLoadAnimation();
        GridItemDecoration divider = new GridItemDecoration.Builder(_mActivity)
                .setHorizontalSpan(R.dimen.common_vew_column_padding)
                .setVerticalSpan(R.dimen.common_vew_raw_padding)
                .setColorResource(R.color.LGray1)
                .setShowLastLine(true)
                .build();
        mRecyclerView.addItemDecoration(divider);

        // 动画一直执行
        mCHMainAdpter.isFirstOnly(true);
        mCHMainAdpter.openLoadAnimation(BaseQuickAdapter.SCALEIN);
        mCHMainAdpter.setNotDoAnimationCount(10);
    }

    private void addData() {
        List<MainItemBean> mainItemData = GlobalDataProvider.getMainItemData();
        mData.addAll(mainItemData);
        mCHMainAdpter.setNewData(mData);
    }


    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        switch (position) {
            case 0://Toast
                startActivity(new Intent(this, ToastActivity.class));
                break;
            case 1://仿IOS Dialog
                break;
            case 2://ScrollView
                startActivity(new Intent(this, IScrollViewActivity.class));
                break;
            case 3://FloatDragButton
//                startActivity(new Intent(this, HomeActivity.class));
                break;
        }
    }

   /* @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);


    }*/


   /* @OnClick({
            R.id.cvToast
            , R.id.cvScrollView
            , R.id.cvFloatTab
    })
    public void bindViewClick(View view) {
        switch (view.getId()) {
            case R.id.cvToast://Toast
                startActivity(new Intent(this, ToastActivity.class));
                break;
            case R.id.cvScrollView://Toast
                startActivity(new Intent(this, IScrollViewActivity.class));
                break;
            case R.id.cvFloatTab://Toast
                startActivity(new Intent(this, HomeActivity.class));
                break;
        }
    }*/


}
