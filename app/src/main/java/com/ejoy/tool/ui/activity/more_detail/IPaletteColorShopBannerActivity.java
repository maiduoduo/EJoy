package com.ejoy.tool.ui.activity.more_detail;

import android.os.Handler;
import android.support.v4.graphics.ColorUtils;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.ejoy.tool.R;
import com.ejoy.tool.common.bean.IPaletteColorInfo;
import com.ejoy.tool.scaffold.glide.IBannerImageLoader;
import com.ejoy.tool.scaffold.utils.StatusBarTool;
import com.ejoy.tool.scaffold.utils.Utils;
import com.ejoy.tool.ui.base.base_activity.IBaseActivity;
import com.ejoy.tool.ui.data.resource.GlobalDataProvider;
import com.kongzue.baseframework.interfaces.DarkStatusBarTheme;
import com.kongzue.baseframework.interfaces.Layout;
import com.tencent.map.geolocation.TencentLocationRequest;
import com.youth.banner.Banner;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;


/**
 * @ClassName: IPaletteColorShopBannerActivity
 * @Author: maiduoduo
 * @BLOG: https://blog.csdn.net/Maiduoudo
 * @Date: 2021/3/1
 * @des: 仿喜马拉雅banner颜色渐变
 *     TODO: 购物推荐banner 标题栏、状态栏、背景等颜色渐变________
 *
 */
@Layout(R.layout.activity_palette_color_shop_banner)
@DarkStatusBarTheme(true)
//@DarkNavigationBarTheme(true)
public class IPaletteColorShopBannerActivity extends IBaseActivity {

    @BindView(R.id.iv_banner_head_bg)
    ImageView ivBannerHeadBg;
    @BindView(R.id.rlPaletteShopRoot)
    RelativeLayout rlPaletteShopRoot;
    @BindView(R.id.topBanner)
    Banner mTopBanner;


    private List<String> bannerList = new ArrayList<>();
    private List<IPaletteColorInfo> colorList = new ArrayList<>();
    private IBannerImageLoader imageLoader;
    private int count;
    private boolean isInit = true;


    private static final int[] LEVELS = new int[]{
            TencentLocationRequest.REQUEST_LEVEL_GEO,
            TencentLocationRequest.REQUEST_LEVEL_NAME,
            TencentLocationRequest.REQUEST_LEVEL_ADMIN_AREA,
            TencentLocationRequest.REQUEST_LEVEL_POI};
    private static final int DEFAULT = 2;

    private int mIndex = DEFAULT;
    private int mLevel = LEVELS[DEFAULT];


    @Override
    protected boolean isRegistSatusbarFullScreenTransluent() {
        return true;
    }

    @Override
    public void initViews() {
//        blur.setOverlayColor(Color.argb(200, 235, 235, 235));
//        blur.setRadius(me, 0, 0);
        setDarkNavigationBarTheme(false);
        StatusBarTool.setStatusBarDarkTheme(me, false);
        initBanner();
    }



    /**
     * 初始化banner
     */
    private void initBanner() {
        List<String> bannerList = GlobalDataProvider.paletteColorShopBannerData();
        count = bannerList.size();
        colorList.clear();
        for (int i = 0; i <= count + 1; i++) {
            IPaletteColorInfo info = new IPaletteColorInfo();
            if (i == 0) {
                info.setImgUrl(bannerList.get(count - 1));
            } else if (i == count + 1) {
                info.setImgUrl(bannerList.get(0));
            } else {
                info.setImgUrl(bannerList.get(i - 1));
            }
            colorList.add(info);
        }

        imageLoader = new IBannerImageLoader(colorList);
        Utils.initBanner(me, mTopBanner, bannerList, imageLoader,10);
    }


    @Override
    public void initDatas() {
    }

    @Override
    public void setEvents() {

        mTopBanner.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                if (positionOffset > 1) {//会出现极个别大于1的数据
                    return;
                }
                //修正position，解决两头颜色错乱，来自Banner控件源码
                if (position == 0) {
                    position = count;
                }
                if (position > count) {
                    position = 1;
                }
                int pos = (position + 1) % count;//很关键

//                int vibrantColor = ColorUtils.blendARGB(imageLoader.getVibrantColor(pos), imageLoader.getVibrantColor(pos + 1), positionOffset);
                int vibrantColor = ColorUtils.blendARGB(imageLoader.getVibrantDarkColor(pos), imageLoader.getVibrantDarkColor(pos + 1), positionOffset);
                int VibrantLightColor = ColorUtils.blendARGB(imageLoader.getVibrantColor(pos), imageLoader.getVibrantLightColor(pos + 1), positionOffset);
               if (ivBannerHeadBg != null) {
                   ivBannerHeadBg.setBackgroundColor(vibrantColor);
//                rlPaletteShopRoot.setBackgroundColor(VibrantLightColor);
               }
               StatusBarTool.setStatusBarColor(me, vibrantColor);
            }

            @Override
            public void onPageSelected(final int position) {
                if(isInit){// 第一次,延时加载才能拿到颜色
                    isInit = false;
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            int vibrantColor = imageLoader.getVibrantDarkColor(1);
//                            int vibrantColor = imageLoader.getVibrantColor(1);
                            int vibrantLightColor = imageLoader.getVibrantLightColor(1);
                            if (ivBannerHeadBg != null) {
                                ivBannerHeadBg.setBackgroundColor(vibrantColor);
                            }
//                            rlPaletteShopRoot.setBackgroundColor(vibrantLightColor);
                            StatusBarTool.setStatusBarColor(me, vibrantColor);
                        }
                    }, 200);

                }
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });
    }


    @Override
    public void onStop() {
        super.onStop();
    }


    @OnClick({
//            R.id.backto,
    })
    public void bindViewclick(View view) {
        switch (view.getId()) {
//            case R.id.backto://返回
//                finishActivity();
//                break;
            default:
                break;
        }
    }

}
