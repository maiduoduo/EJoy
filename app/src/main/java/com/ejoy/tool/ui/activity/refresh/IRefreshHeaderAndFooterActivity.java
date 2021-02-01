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

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.FrameLayout;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ejoy.tool.R;
import com.ejoy.tool.ui.base.base_activity.BaseActivity;
import com.ejoy.tool.ui.data.resource.GlobalDataProvider;
import com.ejoy.tool.ui.mvp.base.BasePresenter;
import com.imaiduoduo.bottomsheet.BottomSheet;
import com.module.ires.bean.bean.IBaseBean;
import com.module.iviews.refresh.RefreshLoadMoreLayout;
import com.module.iviews.utils.WidgetUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import jp.wasabeef.glide.transformations.RoundedCornersTransformation;

/**
 * CN:      IRefreshHeaderAndFooterActivity
 * Author： JSYL-DINGCL (dingcl@jsyl.com.cn)
 * Date:   2020/1/20
 * Des:    TODO:支持RecyclerView,View, ScrollView, ListView,
 * TODO:GridView下拉刷新,上拉加载更多,上拉自动加载更多.
 */
public class IRefreshHeaderAndFooterActivity extends BaseActivity implements RefreshLoadMoreLayout.CallBack {
    @BindView(R.id.title)
    FrameLayout title;
    @BindView(R.id.mRefreshLoadMoreLayout)
    RefreshLoadMoreLayout mRefreshLoadMoreLayout;
    @BindView(R.id.ivShowTip)
    AppCompatImageView mIvShowTip;
    private RecyclerView mRecyclerView;
    private List<IBaseBean> iDatas;
    private BaseQuickAdapter mAdapter;
    private Handler handler = new Handler();
    private boolean isGone = false;

    @Override
    protected void initRestore(@Nullable Bundle savedInstanceState) {

    }

    @Override
    protected Object registSatusbarBgcolor() {
        return defalutStatus2;
    }

    @Override
    protected int getContentViewId() {
        return R.layout.activity_irefresh_headerandfooter;
    }

    @Override
    protected void initView(View mRootView) {
        if (iDatas != null) iDatas.clear();
        else iDatas = new ArrayList<>();
        Intent intent = getIntent();
        if (intent != null){
            isGone = intent.getBooleanExtra("ISGONE", false);
        }
        initRefreshHeaderLayout();
    }

    private void initRefreshHeaderLayout() {
        /**
         * canRefresh 是否下拉刷新
         * canLoadMore 是否上拉加载更多
         * autoLoadMore 自动加载更多（默认不自动加载更多）
         * showLastRefreshTime 是否显示上次刷新时间（默认不显示）
         * multiTask 下拉刷新上拉加载更多可同时进行（默认下拉刷新和上拉加载更多不能同时进行）
         * goneHeaderMsg 是否隐藏文本提示（true:隐藏，false:显示）
         */
        mRefreshLoadMoreLayout.init( new RefreshLoadMoreLayout.Config(this).canRefresh(true)
                .canLoadMore(true)
                .autoLoadMore()
                .showLastRefreshTime(
                        IRefreshHeaderAndFooterActivity.class,
                        "HH:mm:ss")
                .goneHeaderMsg(isGone)
                .multiTask());
        initContent();
        //自动下拉刷新
        mRefreshLoadMoreLayout.startAutoRefresh();
    }

    private void initContent() {
        View contentView = mRefreshLoadMoreLayout.getContentView();
        if (contentView instanceof RecyclerView){
            mRecyclerView = (RecyclerView) contentView;
        }
        List<IBaseBean> iBaseBeans = GlobalDataProvider.headerfooterListData();
        WidgetUtils.initLineRecyclerView(_mActivity, mRecyclerView, mAdapter = new BaseQuickAdapter<IBaseBean, BaseViewHolder>(R.layout.layout_item_headerfooter, iBaseBeans) {
            @Override
            protected void convert(BaseViewHolder helper, IBaseBean item) {
                helper.setText(R.id.txt_title, item.title == null ? "" : item.title);
                helper.setText(R.id.txt_tiptitle, item.tipTile == null ? "" : item.tipTile);
                AppCompatImageView image = helper.getView(R.id.image);
                if (!TextUtils.isEmpty(item.urlIco)) {
                    image.setVisibility(View.VISIBLE);
                    Glide.with(_mActivity)
                            .load(item.urlIco == null ? "" : item.urlIco)
                            .bitmapTransform(new RoundedCornersTransformation(_mActivity, 10, 5))
                            .into(image);
                } else {
                    image.setVisibility(View.GONE);
                }

            }
        }, null, true, false);
    }

    @Override
    protected void initData() {
        iDatas.clear();
        iDatas.addAll(GlobalDataProvider.headerfooterListData());
        mAdapter.setNewData(iDatas);
    }

    @Override
    protected void addListener() {

    }

    @Override
    public BasePresenter getPresenter() {
        return null;
    }

    public void iBack(View view) {
        finish();
    }


    /**
     * 下拉刷新
     */
    @Override
    public void onRefresh() {
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                iDatas.clear();
                iDatas.addAll(GlobalDataProvider.headerfooterListData());
                mAdapter.setNewData(iDatas);
                mRefreshLoadMoreLayout.stopRefresh();
            }
        }, 2000);
    }

    /**
     * 上拉加载更多
     */
    @Override
    public void onLoadMore() {
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                mAdapter.addData(GlobalDataProvider.headerfooterListMoreData());
                mRefreshLoadMoreLayout.stopLoadMoreNoData(mAdapter.getItemCount() >= 50);//依然可以上拉，显示没有更多数据
            }
        }, 2000);
    }

    private BottomSheet mBottomSheet;

    @OnClick(R.id.ivShowTip)
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ivShowTip:
                mBottomSheet = new BottomSheet
                        .Builder(_mActivity)
//                        .icon(IBitmapUtils.getRoundedBitmap(_mActivity, R.mipmap.img_sylm))
//                        .title("To ".concat(sYName))
                        .sheet(R.menu.xml_refresh_a)
                        .listener(new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                callonClick("", which);
                            }
                        })
                        .build();
                mBottomSheet.show();
                break;
        }
    }

    private boolean goneMsg = false;
    private void callonClick(String name, int which) {
        switch (which) {
            case R.id.hideMsg:
                iToast.showISimpleToast("隐藏刷新文字");
                goneMsg = true;
                break;
            case R.id.showMsg:
                iToast.showISimpleToast("显示刷新文字");
                goneMsg = false;
                break;
        }
        Intent intent = new Intent(_mActivity,IRefreshHeaderAndFooterActivity.class);
        intent.putExtra("ISGONE",goneMsg);
        startActivity(intent);
        overridePendingTransition(R.anim.anim_fade_in,R.anim.anim_fade_out);
        finish();
    }

}
