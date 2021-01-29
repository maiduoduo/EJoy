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
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.ejoy.tool.R;
import com.ejoy.tool.ui.base.base_activity.IBaseActivity;
import com.ejoy.tool.ui.data.resource.GlobalDataProvider;
import com.kongzue.baseframework.interfaces.DarkStatusBarTheme;
import com.kongzue.baseframework.interfaces.Layout;
import com.kongzue.dialog.util.BlurView;
import com.module.iviews.custombanner.IBannerAdapter;
import com.module.iviews.custombanner.IBannerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;


/**
 * @ClassName: IPaletteImageActivity
 * @Author: maiduoduo
 * @BLOG: https://blog.csdn.net/Maiduoudo
 * @Date: 2020/10/13
 * @des: 轮播广告条，滚动定位到图片取色并设置标题栏背景
 */
@Layout(R.layout.activity_iui_paletteimage)
@DarkStatusBarTheme(true)
//@DarkNavigationBarTheme(true)
public class IPaletteImageActivity extends IBaseActivity {

    @BindView(R.id.blur)
    BlurView blur;
    @BindView(R.id.ibannerView)
    IBannerView mBannerView;
    @BindView(R.id.box_table)
    RelativeLayout mTitleBox;
    @BindView(R.id.flBannerRoot)
    FrameLayout flBannerRoot;
    @BindView(R.id.llBannerRoot)
    LinearLayout llBannerRoot;
    private List<Integer> bannerList;
    private IBannerAdapter bannerAdapter;


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
        blur.setOverlayColor(Color.argb(100, 235, 235, 235));
        blur.setRadius(me, 0, 0);
        setDarkNavigationBarTheme(false);

        bannerAdapter = new IBannerAdapter(me);
        //--------------------本地图片示例--------START.---------------------------------------------
        bannerAdapter.setPicLocals(GlobalDataProvider.getBannerListLocal());
        mBannerView.setAdapter(bannerAdapter, GlobalDataProvider.getBannerListLocal(), false, flBannerRoot);
        //--------------------本地图片示例--------END.---------------------------------------------


        //--------------------网络图片示例--------START.---------------------------------------------
//        bannerAdapter.setPicNetUrls(GlobalDataProvider.getBannerRemoteList());
//        mBannerView.setAdapter(bannerAdapter,GlobalDataProvider.getBannerRemoteList(),true,flBannerRoot);
        //--------------------网络图片示例--------END.---------------------------------------------


    }


    @Override
    public void initDatas() {
        if (bannerList != null) bannerList.clear();
        else bannerList = new ArrayList<>();

    }


    @Override
    public void setEvents() {
    }


    @OnClick({
            R.id.btn_back
            , R.id.llColorActivi
            , R.id.llColorViewpager
            , R.id.llColorList
    })
    public void eventClick(View view) {
        switch (view.getId()) {
            case R.id.btn_back:
                finish();
                break;
            case R.id.llColorActivi:
                jump(IpaletteImageSampleActivity.class);
                break;
            case R.id.llColorViewpager:
                jump(IPaletteViewpagerColorActivity.class);
                break;
            case R.id.llColorList:
                jump(IPaletteListColorActivity.class);
                break;

        }
    }

}


