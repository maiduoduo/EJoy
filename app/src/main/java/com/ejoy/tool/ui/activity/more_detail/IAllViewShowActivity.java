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

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Build;
import android.os.Handler;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.NestedScrollView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.ejoy.tool.R;
import com.ejoy.tool.scaffold.utils.ActivityUtils;
import com.ejoy.tool.scaffold.utils.StatusBarTool;
import com.ejoy.tool.scaffold.utils.Utils;
import com.ejoy.tool.ui.activity.IArcLayoutActivity;
import com.ejoy.tool.ui.activity.IScrollViewActivity;
import com.ejoy.tool.ui.activity.bezer.BezierActivity;
import com.ejoy.tool.ui.activity.compress.IBitmapMultiChoiceActivity;
import com.ejoy.tool.ui.activity.compress.IBitmapSingChoiceActivity;
import com.ejoy.tool.ui.activity.compress.IBitmapSystemSingleCompressActivity;
import com.ejoy.tool.ui.activity.device.DeviceToolActviity;
import com.ejoy.tool.ui.activity.refresh.IRefreshActivity;
import com.ejoy.tool.ui.activity.seekbar.ISeekBarAndCheckBoxActivity;
import com.ejoy.tool.ui.activity.tab.INavigationBarActivity;
import com.ejoy.tool.ui.activity.tab.ITabScrollActivity;
import com.ejoy.tool.ui.activity.textview.ITextViewActivity;
import com.ejoy.tool.ui.base.base_activity.IBaseActivity;
import com.kongzue.baseframework.interfaces.DarkStatusBarTheme;
import com.kongzue.baseframework.interfaces.Layout;
import com.maple.msdialog.ActionSheetDialog;
import com.module.ires.bean.utils.EDensityUtils;
import com.module.ires.bean.view.EEditTextSearch;
import com.module.iviews.view.IObserverScrollView;
import com.tencent.map.geolocation.TencentLocation;
import com.tencent.map.geolocation.TencentLocationListener;
import com.tencent.map.geolocation.TencentLocationManager;
import com.tencent.map.geolocation.TencentLocationRequest;
import com.tencent.map.geolocation.TencentPoi;
import com.youth.banner.Banner;
import com.youth.banner.listener.OnBannerListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

import static com.ejoy.tool.scaffold.utils.DisplayHelper.DENSITY;


/**
 * CN:      IAllViewShowActivity
 * Author： JSYL-DINGCL (dingcl@jsyl.com.cn)
 * Date:   2020/1/22
 * Des:    TODO:全组件界面
 */

@Layout(R.layout.activity_ishowall)
@DarkStatusBarTheme(true)
//@DarkNavigationBarTheme(true)
public class IAllViewShowActivity extends IBaseActivity implements TencentLocationListener {

    @BindView(R.id.topTabLine)
    LinearLayout topTabLine;
    @BindView(R.id.banner)
    Banner mBanner;
    @BindView(R.id.clickScrollView)
    LinearLayout clickScrollView;
    @BindView(R.id.clickDeviceInfo)
    LinearLayout clickDeviceInfo;
    @BindView(R.id.re_imagesoft)
    RelativeLayout reImagesoft;
    @BindView(R.id.re_refreshStyle)
    RelativeLayout reRefreshStyle;
    @BindView(R.id.reCommonTitlebar)
    RelativeLayout reCommonTitlebar;
    @BindView(R.id.re_common_textview)
    RelativeLayout reCommonTextview;
    @BindView(R.id.re_version_upgrade)
    RelativeLayout reVersionUpgrade;
    @BindView(R.id.MS)
    TextView MS;
    @BindView(R.id.clickDetail)
    LinearLayout clickDetail;
    @BindView(R.id.re_card_1)
    LinearLayout reCard1;
    @BindView(R.id.clickFloatDragButton)
    LinearLayout clickFloatDragButton;
    @BindView(R.id.clickArcLayout)
    LinearLayout clickArcLayout;
    @BindView(R.id.re_card_2)
    LinearLayout reCard2;
    @BindView(R.id.re_tour)
    RelativeLayout reTour;
    @BindView(R.id.reCameraFace)
    RelativeLayout reCameraFace;
    @BindView(R.id.re_card_3)
    LinearLayout reCard3;
    @BindView(R.id.visa)
    TextView visa;
    @BindView(R.id.currency)
    TextView currency;
    @BindView(R.id.guide)
    TextView guide;
    @BindView(R.id.li_1)
    LinearLayout li1;
    @BindView(R.id.air_ticket)
    TextView airTicket;
    @BindView(R.id.gift)
    TextView gift;
    @BindView(R.id.bank_card)
    TextView bankCard;
    @BindView(R.id.cooperation)
    TextView cooperation;
    @BindView(R.id.more)
    TextView more;
    @BindView(R.id.li_2)
    LinearLayout li2;
    @BindView(R.id.scenery_hot_text)
    TextView sceneryHotText;
    @BindView(R.id.scenery_hot_more)
    TextView sceneryHotMore;
    @BindView(R.id.banner_scenery_hot)
    Banner banner;
    @BindView(R.id.scrollView)
    IObserverScrollView scrollView;
    @BindView(R.id.location_text)
    TextView locationCity;
    @BindView(R.id.search)
    EEditTextSearch editTextSearch;
    @BindView(R.id.message_icon)
    ImageView messageIcon;
    @BindView(R.id.re_search)
    RelativeLayout mSearchBar;
    @BindView(R.id.backto)
    ImageView backto;
    @BindView(R.id.tvScrollALPHAInfo)
    TextView tvScrollALPHAInfo;


    private TencentLocationManager tencentLocationManager;
    // 用于记录定位参数, 以显示到 UI
    private String mRequestParams;
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
        editTextSearch.setFocusable(false);
        StatusBarTool.setStatusBarDarkTheme(me, false);
        locationHandler.sendEmptyMessage(1);
        final int bannerHeight = Math.round(150 * DENSITY);//图片高度
        Log.e(_TAG, "initSceneryHotBanner height: " + bannerHeight);
        initBanner();
        initSceneryHotBanner();
    }


    /**
     * 加载广告banner轮播图
     */
    private void initBanner() {
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
        Utils.initBanner(me, mBanner, images, 0);
        mBanner.setOnBannerListener(new OnBannerListener() {
            @Override
            public void OnBannerClick(int position) {
                iToast.showISimpleToast("position" + position);
            }
        });
        mBanner.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {
//                Log.e("Banner", "onPageScrolled i: "+i);

            }

            @Override
            public void onPageSelected(int i) {
                Log.e("Banner", "onPageSelected i: "+i);
//                iToast.showISimpleToast("position...:" + i);
            }

            @Override
            public void onPageScrollStateChanged(int i) {
//                Log.e("Banner", "onPageScrollStateChanged i: "+i);

            }
        });
    }

    /**
     * 加载热门风景轮播图
     */
    private void initSceneryHotBanner() {
        List<String> images = new ArrayList<>();
        images.add("https://lvchen.coding.net/p/tupianyun/git/raw/master/image20.jpg");
        images.add("https://lvchen.coding.net/p/tupianyun/git/raw/master/image25.jpg");
        images.add("https://lvchen.coding.net/p/tupianyun/git/raw/master/image26.jpg");
        images.add("https://lvchen.coding.net/p/tupianyun/git/raw/master/image27.jpg");
        images.add("https://lvchen.coding.net/p/tupianyun/git/raw/master/image30.jpg");
        Utils.initBanner(me, banner, images, 10);
        banner.setOnBannerListener(new OnBannerListener() {
            @Override
            public void OnBannerClick(int position) {
                Toast.makeText(me, "position" + position, Toast.LENGTH_SHORT).show();
            }
        });
//        if (y <= 0) {   //设置标题的背景颜色
//            toolbar.setBackgroundColor(Color.argb((int) 0, 239, 86, 112));
//        } else if (y > 0 && y <= height) { //滑动距离小于banner图的高度时，设置背景和字体颜色颜色透明度渐变
//            float scale = (float) y / height;
//            float alpha = (255 * scale);
//            toolbar.setBackgroundColor(Color.argb((int) alpha, 239, 86, 112));
//        } else {    //滑动到banner下面设置普通颜色
//            toolbar.setBackgroundColor(Color.argb((int) 255, 239, 86, 112));
//        }
    }

    @Override
    public void initDatas() {
    }

    @Override
    public void setEvents() {
        editTextSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent(me, SearchActivity.class);
//                startActivity(intent);
            }
        });

        // 获取顶部图片高度后，设置滚动监听
        ViewTreeObserver vto = banner.getViewTreeObserver();
        vto.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                banner.getViewTreeObserver().removeGlobalOnLayoutListener(
                        this);
                final float imageHeight = banner.getHeight();
                Log.e(_TAG, "initListeners height: " + imageHeight);
                scrollView.setScrollViewListener(new IObserverScrollView.ScrollViewListener() {
                    @Override
                    public void onScrollChanged(NestedScrollView scrollView, int x, int y, int oldx, int oldy) {
                        // TODO Auto-generated method stub
                        Log.e(_TAG, "initListeners y: " + y);
                        float percent = Float.valueOf(Math.abs(y)) / Float.valueOf(EDensityUtils.dp2px(me, 180));
                        if (y <= 0) {
                            //设置文字背景颜色，白色
                            mSearchBar.setBackgroundColor(Color.argb((int) 0, 255, 255, 255));//AGB由相关工具获得，或者美工提供
                            //设置文字颜色，黑色
                            editTextSearch.setBackgroundResource(R.drawable.search_edit_round);
                            backto.setImageResource(R.mipmap.pick_ico_back_white_90px);
                            messageIcon.setImageResource(R.drawable.ico_message_white_32px);
                            locationCity.setTextColor(Color.parseColor("#FFFFFF"));
                            StatusBarTool.setTranslucentStatus(me);
                            StatusBarTool.setStatusBarDarkTheme(me, false);
                        } else if (y > 0 && y <= imageHeight) {
                            float scale = (float) y / imageHeight;
                            float alpha = (255 * scale);
                            // 只是layout背景透明(仿知乎滑动效果)白色透明
                            mSearchBar.setBackgroundColor(Color.argb((int) alpha, 255, 255, 255));
                            //设置文字颜色，黑色，加透明度
                            backto.setImageResource(R.mipmap.img_back_black_48px);
                            messageIcon.setImageResource(R.drawable.ico_message_black_32px);
                            locationCity.setTextColor(Color.argb((int) alpha, 0, 0, 0));
                            editTextSearch.setBackgroundResource(R.drawable.search_edit_round_dark);
                            StatusBarTool.setStatusBarColor(me, Color.parseColor("#FFFFFF"));
                            StatusBarTool.setStatusBarDarkTheme(me, true);

                            tvScrollALPHAInfo.setText("Topbar-setBackgroundColor(Color.argb((int) " + (int) alpha + ", 255, 255, 255))\n" + "mSearchBar.setAlpha(" + percent + ")");
                        } else {
                            //白色不透明
                            mSearchBar.setBackgroundColor(Color.argb((int) 255, 255, 255, 255));
                            //设置文字颜色:黑色
                            locationCity.setTextColor(Color.argb((int) 255, 0, 0, 0));
                        }
                    }
                });
            }
        });
    }


    /**
     * 自动定位当前位置
     */
    @SuppressLint("HandlerLeak")
    private Handler locationHandler = new Handler() {
        @Override
        public void handleMessage(android.os.Message msg) {
            if (Build.VERSION.SDK_INT >= 23) {
                String[] permissions = {
                        Manifest.permission.ACCESS_COARSE_LOCATION,
                        Manifest.permission.READ_PHONE_STATE,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE
                };

                if (me.checkSelfPermission(permissions[0]) != PackageManager.PERMISSION_GRANTED) {
                    requestPermissions(permissions, 0);
                }
            }
            autoGetLocation();
        }


    };

    /**
     * 初始化tencentLocationManager
     */
    public void autoGetLocation() {
        tencentLocationManager = TencentLocationManager.getInstance(me);
        startLocation();
    }

    public void onLocationChanged(TencentLocation tencentLocation, int error, String s) {
        if (error == TencentLocation.ERROR_OK) {
            // 定位成功
            String location = toString(tencentLocation, mLevel);
            stopLocation();
        }
    }

    public void onStatusUpdate(String s, int i, String s1) {

    }

    /**
     * 开始定位
     */
    private void startLocation() {
        TencentLocationRequest request = TencentLocationRequest.create();
        request.setInterval(5000);
        request.setRequestLevel(mLevel);
        tencentLocationManager.requestLocationUpdates(request, this);
    }

    /**
     * 停止定位
     */
    private void stopLocation() {
        if (tencentLocationManager != null) {
            tencentLocationManager.removeUpdates(this);
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        stopLocation();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case 0:
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED
                        && grantResults[1] == PackageManager.PERMISSION_GRANTED) {
                    // 权限被用户同意。
                    autoGetLocation();
                } else {

                }
                break;
        }
    }

    // ===== util method
    private String toString(TencentLocation location, int level) {
        StringBuilder sb = new StringBuilder();

        sb.append("latitude=").append(location.getLatitude()).append(",");
        sb.append("longitude=").append(location.getLongitude()).append(",");
        sb.append("altitude=").append(location.getAltitude()).append(",");
        sb.append("accuracy=").append(location.getAccuracy()).append(",");

        switch (level) {
            case TencentLocationRequest.REQUEST_LEVEL_GEO:
                break;

            case TencentLocationRequest.REQUEST_LEVEL_NAME:
                sb.append("name=").append(location.getName()).append(",");
                sb.append("address=").append(location.getAddress()).append(",");
                break;

            case TencentLocationRequest.REQUEST_LEVEL_ADMIN_AREA:
            case TencentLocationRequest.REQUEST_LEVEL_POI:
            case 7:
                sb.append("nation=").append(location.getNation()).append(",");
                sb.append("province=").append(location.getProvince()).append(",");
                sb.append("city=").append(location.getCity()).append(",");
                sb.append("district=").append(location.getDistrict()).append(",");
                sb.append("town=").append(location.getTown()).append(",");
                sb.append("village=").append(location.getVillage()).append(",");
                sb.append("street=").append(location.getStreet()).append(",");
                sb.append("streetNo=").append(location.getStreetNo()).append(",");

                //此处动态设置用户当前所处城市：
                if (!TextUtils.isEmpty(location.getDistrict()) && !location.getDistrict().equals("Unknown") && location.getDistrict() != null) {
                    locationCity.setText(location.getDistrict());
                } else {
                    locationCity.setText("北京");
                }
                if (level == TencentLocationRequest.REQUEST_LEVEL_POI) {
                    List<TencentPoi> poiList = location.getPoiList();
                    int size = poiList.size();
                    for (int i = 0, limit = 3; i < limit && i < size; i++) {
                        sb.append("\n");
                        sb.append("poi[" + i + "]=")
                                .append(toString(poiList.get(i))).append(",");
                    }
                }

                break;

            default:
                break;
        }

        return sb.toString();
    }

    private static String toString(TencentPoi poi) {
        StringBuilder sb = new StringBuilder();
        sb.append("name=").append(poi.getName()).append(",");
        sb.append("address=").append(poi.getAddress()).append(",");
        sb.append("catalog=").append(poi.getCatalog()).append(",");
        sb.append("distance=").append(poi.getDistance()).append(",");
        sb.append("latitude=").append(poi.getLatitude()).append(",");
        sb.append("longitude=").append(poi.getLongitude()).append(",");
        return sb.toString();
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }


    @OnClick({
            R.id.clickScrollView,
            R.id.clickDetail,
            R.id.clickFloatDragButton,
            R.id.clickArcLayout,
            R.id.clickDeviceInfo,
            R.id.re_imagesoft,
            R.id.re_refreshStyle,
            R.id.backto,
            R.id.re_topbar,
            R.id.re_common_textview,
            R.id.reCommonTitlebar,
            R.id.re_button,
            R.id.re_blurview,
            R.id.reSeekBar,
            R.id.tabAnchorPoint,
            R.id.navigationBar,
    })
    public void bindViewclick(View view) {
        switch (view.getId()) {
            case R.id.clickScrollView://ScrollView
                jump(IScrollViewActivity.class);
                break;
            case R.id.clickDetail:
                break;
            case R.id.clickFloatDragButton:
                jump(BezierActivity.class);
                break;
            case R.id.reCommonTitlebar:
                jump(ICommonTitleBarActivity.class);
                break;
            case R.id.re_common_textview:
                jump(ITextViewActivity.class);
                jumpAnim(R.anim.push_bottom_in, R.anim.push_bottom_out);
                break;
            case R.id.re_version_upgrade:
                break;
            case R.id.clickArcLayout://ArcLayout
                jump(IArcLayoutActivity.class);
                break;
            case R.id.clickDeviceInfo://设备信息
                ActivityUtils.getInstance().showActivity(this, DeviceToolActviity.class);
                break;
            case R.id.re_imagesoft://图片处理
                showBotttomDialog();
                break;
            case R.id.re_refreshStyle://下拉刷新
                jump(IRefreshActivity.class);
                break;
            case R.id.re_topbar://顶部栏颜色渐变
                jump(ITopBarColorChangeActivity.class);
                break;
            case R.id.re_button://Button
                jump(ICommonButtonActivity.class);
                break;
            case R.id.re_blurview://Button
                jump(ICommonBlurViewActivity.class);
                break;
            case R.id.backto://返回
                finishActivity();
                break;
            case R.id.reSeekBar://SeekBar
                jump(ISeekBarAndCheckBoxActivity.class);
                break;
            case R.id.tabAnchorPoint://tab锚点定位
                jump(ITabScrollActivity.class);
                break;
            case R.id.navigationBar://底部导航栏
                jump(INavigationBarActivity.class);
                break;
            default:
                break;
        }
    }

    private void showBotttomDialog() {
        new ActionSheetDialog(me)
                .setCancelable(false)
                .setCanceledOnTouchOutside(false)
                .addSheetItem("单张图片压缩", Color.parseColor("#037BFF"),
                        new ActionSheetDialog.OnSheetItemClickListener() {
                            @Override
                            public void onClick(int which) {
                                jump(IBitmapSingChoiceActivity.class);
                            }
                        })
                .addSheetItem("批量图片压缩", Color.parseColor("#037BFF"),
                        new ActionSheetDialog.OnSheetItemClickListener() {
                            @Override
                            public void onClick(int which) {
                                jump(IBitmapMultiChoiceActivity.class);
                            }
                        })
                .addSheetItem("系统API图片压缩", Color.parseColor("#037BFF"),
                        new ActionSheetDialog.OnSheetItemClickListener() {
                            @Override
                            public void onClick(int which) {
                                jump(IBitmapSystemSingleCompressActivity.class);
                            }
                        })
                .show();
    }
}
