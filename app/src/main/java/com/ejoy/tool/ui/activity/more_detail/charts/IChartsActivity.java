package com.ejoy.tool.ui.activity.more_detail.charts;

import android.content.Intent;
import android.graphics.Color;
import android.os.CountDownTimer;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.ejoy.tool.R;
import com.ejoy.tool.common.bean.IChartsHomeBean;
import com.ejoy.tool.common.bean.MainItemBean;
import com.ejoy.tool.ui.activity.more_detail.IPaletteImageActivity;
import com.ejoy.tool.ui.base.base_activity.IBaseActivity;
import com.ejoy.tool.ui.data.adapter.CHMainAdpter;
import com.ejoy.tool.ui.data.adapter.IChartsHomeAdpter;
import com.ejoy.tool.ui.douyin.activity.DouyinMainActivity;
import com.ejoy.tool.ui.douyin.activity.DouyinSplashActivity;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.kongzue.baseframework.interfaces.DarkNavigationBarTheme;
import com.kongzue.baseframework.interfaces.DarkStatusBarTheme;
import com.kongzue.baseframework.interfaces.Layout;
import com.kongzue.dialog.util.BlurView;
import com.module.ires.bean.utils.EJsonUtils;
import com.module.ires.bean.view.EGridDividerItemDecoration;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;


/**
 * @ClassName: IChartsActivity
 * @Author: maiduoduo
 * @BLOG: https://blog.csdn.net/Maiduoudo
 * @Date: 2021/2/20
 * @des: 各类图表
 */
@Layout(R.layout.activity_charts)
@DarkStatusBarTheme(true)
public class IChartsActivity extends IBaseActivity {

    @BindView(R.id.iRecyclerView)
    RecyclerView mRecyclerView;
    @BindView(R.id.blur)
    BlurView blur;

    private List<IChartsHomeBean> mCharts;
    private IChartsHomeAdpter iChartsHomeAdpter;

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
        mCharts = (List<IChartsHomeBean>) initPublicArrayList(mCharts);
        loadGrid();
    }


    @Override
    public void initDatas() {
        addData();
    }

    @Override
    public void setEvents() {

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

    private void loadGrid() {
        //Recyclerview
        GridLayoutManager gridLayoutManager = new GridLayoutManager(me, 3);
        mRecyclerView.setLayoutManager(gridLayoutManager);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setItemViewCacheSize(10);
        iChartsHomeAdpter = new IChartsHomeAdpter(R.layout.item_main_layout, mCharts, me);
        mRecyclerView.setAdapter(iChartsHomeAdpter);
        // 打开动画效果
        iChartsHomeAdpter.openLoadAnimation();
//            IGridItemDecoration build = new IGridItemDecoration.Builder(context)
//                    .size(1)
//                    .color(R.color.Gray4)
//                    .margin(2, 2)
//                    .isExistHead(false)
//                    .showLastDivider(false)
//                    .showHeadDivider(false)
//                    .build();

//            mRecyclerView.addItemDecoration(build);
//            mRecyclerView.addItemDecoration(new GridDividerItemDecoration(1,EResUtils.getColor(context,R.color.Gray4)));
        mRecyclerView.addItemDecoration(new EGridDividerItemDecoration(me, 3, 1, R.color.light_gray1));

        // 动画一直执行
        iChartsHomeAdpter.isFirstOnly(true);
        iChartsHomeAdpter.openLoadAnimation(BaseQuickAdapter.SCALEIN);
        iChartsHomeAdpter.setNotDoAnimationCount(10);
        iChartsHomeAdpter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                IChartsHomeBean iChartsHomeBean = mCharts.get(position);
                String flag = iChartsHomeBean.getFlag();
                String clazz1 = iChartsHomeBean.getClazz();
                Log.e(_TAG, "-----------[charts home]onItemClick  clazz: " + clazz1);
                if (!TextUtils.isEmpty(iChartsHomeBean.getClazz())) {
                    Class clazz = null;
                    try {
                        clazz = Class.forName(iChartsHomeBean.getClazz());
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    }
                    Intent intent = new Intent(me, clazz);
                    startActivity(intent);
                } else {
                    iToast.showIImgToast("无跳转");
                }
            }
        });
    }

    /**
     * 主页Grid数据
     */
    private void addData() {
        String jsonStr = EJsonUtils.getJson(this, "asset_charts_home.json");
        Gson gson = new Gson();
        List<IChartsHomeBean> mainItemDataList = gson.fromJson(jsonStr, new TypeToken<List<IChartsHomeBean>>() {
        }.getType());
        mCharts.addAll(mainItemDataList);
        iChartsHomeAdpter.setNewData(mCharts);
    }


}
