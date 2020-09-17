package com.ejoy.tool.ui.activity.more_detail;
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
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.ejoy.tool.R;
import com.ejoy.tool.common.db.CitysBean;
import com.ejoy.tool.common.db.engine.CitiesDaoHelper;
import com.ejoy.tool.scaffold.utils.IToastImageType;
import com.ejoy.tool.scaffold.view.PowerfulRecyclerView;
import com.ejoy.tool.scaffold.view.decorator.DividerItemDecoration;
import com.ejoy.tool.scaffold.view.decorator.SectionItemDecoration;
import com.ejoy.tool.ui.base.base_activity.IBaseActivity;
import com.ejoy.tool.ui.data.adapter.CpCitySelectListAdapter;
import com.ejoy.tool.ui.data.adapter.ISideBarListAdapter;
import com.ejoy.tool.ui.fragment.citylist.model.HotCity;
import com.google.gson.Gson;
import com.kongzue.baseframework.interfaces.DarkStatusBarTheme;
import com.kongzue.baseframework.interfaces.Layout;
import com.kongzue.dialog.util.BlurView;
import com.module.iviews.slidebar.IWaveSideBarView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;


/**
 * @ClassName: ISideBarActivity
 * @Author: maiduoduo
 * @BLOG: https://blog.csdn.net/Maiduoudo
 * @Date: 2020/9/17
 * @des: 字母侧边栏，快速导航
 */
@Layout(R.layout.activity_iui_slidebar)
@DarkStatusBarTheme(true)
//@DarkNavigationBarTheme(true)
public class ISideBarActivity extends IBaseActivity {

    @BindView(R.id.recyclerView)
    PowerfulRecyclerView mRecyclerView;
    @BindView(R.id.blur)
    BlurView blur;
    @BindView(R.id.iwaveSideBarView)
    IWaveSideBarView iwaveSideBarView;
    private LinearLayoutManager mLayoutManager;
    private ISideBarListAdapter mAdapter;

    private List<CitysBean> mAllCities;

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
        initDBData();
        initRecyclerView();

    }


    /**
     * 初始化数据库数据
     */
    private List<CitysBean> citysBeanList;
    private void initDBData() {
        if (citysBeanList != null)citysBeanList.clear();
        else citysBeanList = new ArrayList<>();
        if (mAllCities != null)mAllCities.clear();
        else mAllCities = new ArrayList<>();
        List<CitysBean> citysBeans = CitiesDaoHelper.queryAll();
        citysBeanList.addAll(citysBeans);
        Log.e(_TAG, "sidebar initDBData------------->: \n"+new Gson().toJson(citysBeans));
        mAllCities.addAll(citysBeanList);
    }

    private void initRecyclerView() {
        mLayoutManager = new LinearLayoutManager(me, LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.addItemDecoration(new SectionItemDecoration(me, mAllCities), 0);
        mRecyclerView.addItemDecoration(new DividerItemDecoration(me, R.color.black_11), 1);
        mAdapter = new ISideBarListAdapter(me, mAllCities);
        mAdapter.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            //用来标记是否正在向最后一个滑动
            boolean isSlidingToLast = false;

            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                //确保定位城市能正常刷新
                LinearLayoutManager manager = (LinearLayoutManager) recyclerView.getLayoutManager();
                // 当不滚动时
                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                    //获取最后一个完全显示的ItemPosition
                    int lastVisibleItem = manager.findLastCompletelyVisibleItemPosition();
                    int totalItemCount = manager.getItemCount();

                    // 判断是否滚动到底部，并且是向右滚动
                    if (lastVisibleItem == (totalItemCount - 1) && isSlidingToLast) {
                        //加载更多功能的代码
                    }
                }

            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                //dx用来判断横向滑动方向，dy用来判断纵向滑动方向
                if (dx > 0) {
                    //大于0表示正在向右滚动
                    isSlidingToLast = true;
                } else {
                    //小于等于0表示停止或向左滚动
                    isSlidingToLast = false;
                }
            }
        });
    }

    @Override
    public void initDatas() {
    }


    @Override
    public void setEvents() {
        iwaveSideBarView.setOnTouchLetterChangeListener(new IWaveSideBarView.OnTouchLetterChangeListener() {
            @Override
            public void onLetterChange(String letter) {
                //滚动RecyclerView到索引位置
                mAdapter.scrollToSection(letter);
            }
        });

        mAdapter.setInnerListener(new ISideBarListAdapter.OnInnerListener() {
            @Override
            public void selectSectionItem(int position, CitysBean data) {
                iToast.showIImgToast("选中："+mAllCities.get(position).getC_name(),0,IToastImageType.SUCCESS);
            }
        });

    }


    @OnClick({
            R.id.btn_back
    })
    public void eventClick(View view){
         switch (view.getId()){
                case R.id.btn_back:
                    finish();
                    break;

           }
     }


}
