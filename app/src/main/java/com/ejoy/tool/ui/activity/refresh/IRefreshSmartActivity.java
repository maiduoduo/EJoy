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
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ejoy.tool.R;
import com.ejoy.tool.scaffold.utils.StatusBarTool;
import com.ejoy.tool.scaffold.view.decorator.DashlineItemDivider;
import com.ejoy.tool.scaffold.view.refresh.SmartRefreshHeader;
import com.ejoy.tool.ui.base.base_activity.BaseActivity;
import com.ejoy.tool.ui.mvp.base.BasePresenter;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * CN:      IRefreshSmartActivity
 * Author： JSYL-DINGCL (dingcl@jsyl.com.cn)
 * Date:   2019/12/25
 * Des:    TODO:下拉刷新-SmartRefresh头部
 */
public class IRefreshSmartActivity extends BaseActivity {
    @BindView(R.id.msmartRefreshLayout)
    SmartRefreshLayout mSmartRefreshLayout;
    @BindView(R.id.mRecyclerView)
    RecyclerView mRecyclerView;

    private BaseQuickAdapter mAdapter;
    private static int refreshTime = 1000;
    private long delayMillis = 1000;
    private List<String> normalList = new ArrayList<>();

    @Override
    protected void initRestore(@Nullable Bundle savedInstanceState) {

    }

    @Override
    protected int getContentViewId() {
        return R.layout.activity_irefresh_smartheader;
    }

    @Override
    protected void initView(View mRootView) {
        StatusBarTool.setRootViewFitsSystemWindows(this,true);
        StatusBarTool.setStatusBarColor(this,Color.parseColor(defalutStatus2));
        mSmartRefreshLayout.setRefreshHeader(new SmartRefreshHeader(this));
        mSmartRefreshLayout.setEnableScrollContentWhenLoaded(true);
        mSmartRefreshLayout.setEnableFooterFollowWhenLoadFinished(true);
        mSmartRefreshLayout.autoRefresh();
        mRecyclerView.setLayoutManager(new LinearLayoutManager(_mActivity));
        mRecyclerView.addItemDecoration(new DashlineItemDivider("#cccccc"));
        mRecyclerView.setAdapter(mAdapter = new BaseQuickAdapter<String, BaseViewHolder>(R.layout.item_irefresh_normal_girl, normalList) {
            @Override
            protected void convert(BaseViewHolder helper, String item) {
                helper.setText(R.id.tvNormalTitle,item+"");
            }
        });

        //代码实现动画效果
        LayoutAnimationController controller = new LayoutAnimationController(AnimationUtils.loadAnimation(this, R.anim.animation_item));
        //也可以通过此方法获得
        //LayoutAnimationController controller = AnimationUtils.loadLayoutAnimation(this, R.anim.animation_recyclerview);
        controller.setOrder(LayoutAnimationController.ORDER_NORMAL);
        controller.setDelay(0.2f);//前面还没结束，后面已经开始，实现连续
        mRecyclerView.setLayoutAnimation(controller);
        mRecyclerView.startLayoutAnimation();//貌似不加这句动画也会自动实现
    }

    @Override
    protected void initData() {
        getVideoData();
    }

    private void getVideoData() {
        normalList.clear();
        normalList.add("好尴尬,按下音箱而已…");
        normalList.add("你信不信下个红绿灯他们在那里等着要锤死你");
        normalList.add("知道外国人为什么这么少了吧");
        normalList.add("鸣笛归鸣笛，关键人家不刹车啊");
        normalList.add("非洲两辆摩托车发生事故，伤亡总计25人。");
        normalList.add("内涵段子，冷笑话、搞笑图片、搞笑视频以及搞笑的神最右等等、也可以在这里了解到最新发生的实时事件。");
        normalList.add("我就是让你看10遍，你都做不出来，信不信");
        mAdapter.setNewData(normalList);
        if (mSmartRefreshLayout != null) mSmartRefreshLayout.finishRefresh();
    }

    @Override
    protected void addListener() {
        mSmartRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
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
