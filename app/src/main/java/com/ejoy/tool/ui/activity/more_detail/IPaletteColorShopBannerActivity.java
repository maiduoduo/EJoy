package com.ejoy.tool.ui.activity.more_detail;

import android.os.Handler;
import android.support.v4.graphics.ColorUtils;
import android.support.v4.view.ViewPager;
import android.util.Log;
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
import com.youth.banner.listener.OnBannerListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

import static com.ejoy.tool.scaffold.utils.DisplayHelper.DENSITY;


/**
 * @ClassName: IPaletteColorShopBannerActivity
 * @Author: maiduoduo
 * @BLOG: https://blog.csdn.net/Maiduoudo
 * @Date: 2021/3/1
 * @des: 仿喜马拉雅banner颜色渐变
 *       购物推荐banner 标题栏、状态栏、背景等颜色渐变
 */
@Layout(R.layout.activity_palette_color_shop_banner)
@DarkStatusBarTheme(true)
//@DarkNavigationBarTheme(true)
public class IPaletteColorShopBannerActivity extends IBaseActivity {

    // @BindView(R.id.backto)
    // ImageView backto;
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
        final int bannerHeight = Math.round(150 * DENSITY);//图片高度
        Log.e(_TAG, "initSceneryHotBanner height: " + bannerHeight);
        initBanner();
    }


    /**
     * 加载广告banner轮播图
     */
    /*private void initBanner() {
        List<Integer> images = new ArrayList<>();
//        images.add("https://lvchen.coding.net/p/tupianyun/git/raw/master/image8.jpg");
//        images.add("https://lvchen.coding.net/p/tupianyun/git/raw/master/image9.jpg");
//        images.add("https://lvchen.coding.net/p/tupianyun/git/raw/master/image11.jpg");
//        images.add("https://lvchen.coding.net/p/tupianyun/git/raw/master/image13.jpg");
//        images.add("https://lvchen.coding.net/p/tupianyun/git/raw/master/image16.jpg");
//        images.add("https://lvchen.coding.net/p/tupianyun/git/raw/master/image19.jpg");

        images.add(R.mipmap.banner_3_a);
        images.add(R.mipmap.banner_3_b);
        images.add(R.mipmap.banner_3_c);
        images.add(R.mipmap.banner_3_d);
        images.add(R.mipmap.banner_3_f);
        Utils.initBanner(me, mTopBanner, images, 0);
        mTopBanner.setOnBannerListener(new OnBannerListener() {
            @Override
            public void OnBannerClick(int position) {
                iToast.showISimpleToast("position" + position);
            }
        });
        mTopBanner.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {
//                Log.e("Banner", "onPageScrolled i: "+i);

            }

            @Override
            public void onPageSelected(int i) {
                Log.e("Banner", "onPageSelected i: " + i);
//                iToast.showISimpleToast("position...:" + i);
            }

            @Override
            public void onPageScrollStateChanged(int i) {
//                Log.e("Banner", "onPageScrollStateChanged i: "+i);

            }
        });
    }*/

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

//        mTopBanner.setImageLoader(imageLoader);
//        //设置图片集合
//        mTopBanner.setImages(bannerList);
//        //设置banner动画效果
//        // banner.setBannerAnimation(Transformer.DepthPage);
//        //设置轮播时间
//        mTopBanner.setDelayTime(3000);
//        mTopBanner.setOnBannerListener(new OnBannerListener() {
//            @Override
//            public void OnBannerClick(int position) {
//
//            }
//        });
        //banner设置方法全部调用完毕时最后调用
//        mTopBanner.start();

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
