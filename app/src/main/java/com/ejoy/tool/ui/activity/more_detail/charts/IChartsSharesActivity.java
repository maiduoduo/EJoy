package com.ejoy.tool.ui.activity.more_detail.charts;

import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.ejoy.tool.R;
import com.ejoy.tool.common.bean.ISharesContentBean;
import com.ejoy.tool.ui.base.base_activity.IBaseActivity;
import com.ejoy.tool.ui.data.adapter.ISharesContentAdapter;
import com.ejoy.tool.ui.data.adapter.ISharesTabAdapter;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.kongzue.baseframework.interfaces.DarkStatusBarTheme;
import com.kongzue.baseframework.interfaces.Layout;
import com.kongzue.dialog.util.BlurView;
import com.module.ires.bean.utils.EJsonUtils;
import com.module.iviews.charts.ICustomizeMarqueeView;
import com.module.iviews.charts.ICustomizeScrollView;
import com.module.iviews.charts.adapter.IMarquessViewAdapter;
import com.module.iviews.charts.bean.IMessageBean;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;


/**
 * @ClassName: IChartsSharesActivity
 * @Author: maiduoduo
 * @BLOG: https://blog.csdn.net/Maiduoudo
 * @Date: 2021/2/21
 * @des: 股票、证券列表联动效果
 */
@Layout(R.layout.activity_charts_shares)
@DarkStatusBarTheme(true)
public class IChartsSharesActivity extends IBaseActivity implements ISharesContentAdapter.OnTabScrollViewListener{

    @BindView(R.id.blur)
    BlurView blur;
    /**
     * Tab栏RecyclerView
     */
    @BindView(R.id.headRecyclerView)
    RecyclerView mHeadRecyclerView;
    /**
     * 列表RecyclerView
     */
    @BindView(R.id.contentRecyclerView)
    RecyclerView mContentRecyclerView;
    /**
     * Tab栏ScrollView
     */
    @BindView(R.id.headScrollView)
    ICustomizeScrollView headHorizontalScrollView;
    /**
     * 跑马灯View
     */
    @BindView(R.id.marqueeView)
    ICustomizeMarqueeView mMarqueeView;


    /**
     * 跑马灯Adapter
     */
    private IMarquessViewAdapter marquessViewAdapter;
    /**
     * Tab栏Adapter
     */
    private ISharesTabAdapter mTabAdapter;
    /**
     * 列表Adapter
     */
    private ISharesContentAdapter mSharesContentAdapter;
    /**
     * Tab栏标题
     */
    String values[] = {"最新", "涨幅", "涨跌", "换手", "成交额", "量比", "振幅"};
    /**
     * RecyclerView数据集合
     */
    private List<ISharesContentBean> sharesContentBeans = new ArrayList<>();
    /**
     * 跑马灯数据集合
     */
    private List<IMessageBean> messageBeans = new ArrayList<>();


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
        blur.setOverlayColor(Color.argb(200, 235, 235, 235));
        blur.setRadius(me, 0, 0);
        setDarkNavigationBarTheme(false);


        // TODO:Tab栏RecycleView
        // 设置RecyclerView水平显示
        mHeadRecyclerView.setLayoutManager(
                new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false));
        mTabAdapter = new ISharesTabAdapter(R.layout.item_shares_tab,Arrays.asList(values),me);
        // 设置ListView禁止滑动，这样使得ScrollView滑动更流畅
        mHeadRecyclerView.setNestedScrollingEnabled(false);
        mHeadRecyclerView.setAdapter(mTabAdapter);

        // TODO:文中列表RecyclerView
        mContentRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mSharesContentAdapter = new ISharesContentAdapter(this);
        mContentRecyclerView.setAdapter(mSharesContentAdapter);
        mSharesContentAdapter.setOnTabScrollViewListener(this);
        initStockData();

        // TODO：跑马灯
        marquessViewAdapter = new IMarquessViewAdapter(this);
        mMarqueeView.setItemCount(2);
        mMarqueeView.setSingleLine(false);
        mMarqueeView.setAdapter(marquessViewAdapter);
        marquessViewAdapter.setMessageBeans(messageBeans);
        initMarquessData();

    }


    @Override
    public void initDatas() {
    }

    @Override
    public void setEvents() {
        /**
         * 第三步：Tab栏HorizontalScrollView水平滚动时，遍历所有RecyclerView列表，并使其跟随滚动
         */
        headHorizontalScrollView.setViewListener(new ICustomizeScrollView.OnScrollViewListener() {
            @Override
            public void onScroll(int l, int t, int oldl, int oldt) {
                List<ISharesContentAdapter.ViewHolder> viewHolders = mSharesContentAdapter.getRecyclerViewHolder();
                for (ISharesContentAdapter.ViewHolder viewHolder : viewHolders) {
                    viewHolder.mStockScrollView.scrollTo(l, 0);
                }
            }
        });

        /**
         * 第四步：RecyclerView垂直滑动时，遍历更新所有item中HorizontalScrollView的滚动位置，否则会出现item位置未发生变化状态
         */
        mContentRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                List<ISharesContentAdapter.ViewHolder> viewHolders = mSharesContentAdapter.getRecyclerViewHolder();
                for (ISharesContentAdapter.ViewHolder viewHolder : viewHolders) {
                    viewHolder.mStockScrollView.scrollTo(mSharesContentAdapter.getOffestX(), 0);
                }
            }
        });
    }

    @OnClick({
            R.id.btn_back
    })
    public void eventClick(View view) {
        switch (view.getId()) {
            case R.id.btn_back:
                finish();
                break;

        }
    }

    /**
     * 初始化Tab数据
     */
    void initTabData() {
        mTabAdapter.setNewData(Arrays.asList(values));
    }

    /**
     * 初始化列表数据
     */
    void initStockData() {
        String jsonStr = EJsonUtils.getJson(this,"asset_shares_content.json");
        Gson gson = new Gson();
        sharesContentBeans = gson.fromJson(jsonStr,new TypeToken<List<ISharesContentBean>>() {}.getType());
        mSharesContentAdapter.setStockBeans(sharesContentBeans);
    }


    /**
     * 初始化跑马灯数据
     */
    private void initMarquessData() {
        String jsonStr = EJsonUtils.getJson(this,"asset_shares_message.json");
        Gson gson = new Gson();
        List<IMessageBean> sharesMessageBeans = gson.fromJson(jsonStr,new TypeToken<List<IMessageBean>>() {}.getType());

        messageBeans.addAll(sharesMessageBeans);
        marquessViewAdapter.notifyDataChanged();
    }


    @Override
    public void scrollTo(int l, int t) {
        if (headHorizontalScrollView != null) {
            headHorizontalScrollView.scrollTo(l, 0);
        }
    }
}
