package com.ejoy.tool.ui.activity;

import android.annotation.TargetApi;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.BottomSheetDialog;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.ejoy.tool.R;
import com.ejoy.tool.common.bean.MainItemBean;
import com.ejoy.tool.common.db.CitysBean;
import com.ejoy.tool.common.db.engine.CitiesDaoHelper;
import com.ejoy.tool.common.helper.dialog.ExitDialog;
import com.ejoy.tool.scaffold.utils.ActivityUtils;
import com.ejoy.tool.scaffold.utils.FileUtils;
import com.ejoy.tool.scaffold.utils.StatusBarTool;
import com.ejoy.tool.scaffold.view.PowerfulRecyclerView;
import com.ejoy.tool.ui.activity.bezer.BezierActivity;
import com.ejoy.tool.ui.activity.bottomsheet.IBottomSheetActivity;
import com.ejoy.tool.ui.activity.compress.IBitmapMultiChoiceActivity;
import com.ejoy.tool.ui.activity.compress.IBitmapSingChoiceActivity;
import com.ejoy.tool.ui.activity.compress.IBitmapSystemSingleCompressActivity;
import com.ejoy.tool.ui.activity.device.DeviceToolActviity;
import com.ejoy.tool.ui.activity.iosdialog.IDialogActivity;
import com.ejoy.tool.ui.activity.loading.ILoadingActivity;
import com.ejoy.tool.ui.activity.datetime.picker.ITimeDateOrActivity;
import com.ejoy.tool.ui.activity.popupwindow.IPopupwindowActivity;
import com.ejoy.tool.ui.activity.refresh.IRefreshActivity;
import com.ejoy.tool.ui.base.base_activity.BaseActivity;
import com.ejoy.tool.ui.data.adapter.HomeMultipleRecycleAdapter;
import com.ejoy.tool.ui.mvp.base.BasePresenter;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.maple.msdialog.ActionSheetDialog;
import com.module.ires.bean.utils.EJsonUtils;
import com.module.iviews.utils.WidgetUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import jp.wasabeef.glide.transformations.BlurTransformation;
import jp.wasabeef.glide.transformations.CropCircleTransformation;

/*
*
*
,------.     ,--.
|  .---'     |  | ,---.,--. ,--.
|  `--, ,--. |  || .-. |\  '  /
|  `---.|  '-'  /' '-' ' \   '
`------' `-----'  `---'.-'  /
                       `---'
*/

/**
 * CN:      MainActivity
 * Author： JSYL-DINGCL (dingcl@jsyl.com.cn)
 * Date:   2019/10/10
 * Des:    主界面
 */
public class MainActivity_0122 extends BaseActivity implements BaseQuickAdapter.OnItemClickListener {

    @BindView(R.id.mRecyclerView)
    PowerfulRecyclerView mRecyclerView;
    @BindView(R.id.head_layout)
    LinearLayout headLayout;
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.app_bar_layout)
    AppBarLayout appBarLayout;
    @BindView(R.id.root_layout)
    CoordinatorLayout rootLayout;
    @BindView(R.id.collapsing_toolbar_layout)
    CollapsingToolbarLayout mCollapsingToolbarLayout;
    @BindView(R.id.head_iv)
    ImageView mHeaderIv;
    public static final int SPAN_COUNT = 4;
    private String localImgUrl = "";
    private HomeMultipleRecycleAdapter mMainAdpter;
    private View headerView;
    private List<MainItemBean> mData;
    private BottomSheetDialog bottomSheetDialog;
    private ExitDialog mExitDialog;

    @Override
    protected void initRestore(@Nullable Bundle savedInstanceState) {
    }

    @Override
    protected int getContentViewId() {
        return R.layout.activity_main;
    }

    @Override
    protected boolean isRegistSatusbarFullScreenTransluent() {
        return true;
    }
    @Override
    protected void initView(View mRootView) {
        if (mData == null) mData = new ArrayList<>();
        else mData.clear();
        //检测更新
//        BuglyHelper.getInstance().doCheckUpgrade(this);
        initTopHeader();
        initRecyclerView();
        addData();
    }

    @Override
    protected void initData() {
        List<CitysBean> citysBeans = CitiesDaoHelper.queryAll();
    }

    @Override
    protected void addListener() {

    }

    private void initTopHeader() {
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        //使用CollapsingToolbarLayout必须把title设置到CollapsingToolbarLayout上，设置到Toolbar上则不会显示
        appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if (verticalOffset <= -headLayout.getHeight() / 2) {
                    mCollapsingToolbarLayout.setTitle("E·享");
                } else {
                    mCollapsingToolbarLayout.setTitle(" ");
                }
            }
        });

        loadBlurAndSetStatusBar();
        Glide.with(this).load(R.mipmap.img_f).bitmapTransform(new CropCircleTransformation(this))
                .into(mHeaderIv);


    }


    @Override
    public BasePresenter getPresenter() {
        return null;
    }

    private void initRecyclerView() {
        WidgetUtils.initRecyclerView(_mActivity,mRecyclerView,mMainAdpter = new HomeMultipleRecycleAdapter(_mActivity, mData),
                headerView = getLayoutInflater().inflate(R.layout.layout_main_header, null));
        headerView.findViewById(R.id.mainRvheader).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iToast.showIDefaultImgResToast("快速预览");
                String clazzStr = "com.ejoy.tool.ui.activity.ToastActivity";
                Class clazz = null;
                try {
                    clazz = Class.forName(clazzStr);
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
                Intent intent = new Intent(MainActivity_0122.this,clazz);
                startActivity(intent);
            }
        });
    }

    private void addData() {
        String jsonStr = EJsonUtils.getJson(this,"main_data.json");
        Gson gson = new Gson();
        List<MainItemBean> mainItemDataList = gson.fromJson(jsonStr,new TypeToken<List<MainItemBean>>() {}.getType());
        mData.addAll(mainItemDataList);
        mMainAdpter.setNewData(mData);
    }


    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        switch (position) {
            case 0://Toast
                startActivity(new Intent(this, ToastActivity.class));
                break;
            case 1://Dialog
                showActivity(this, IDialogActivity.class,R.anim.push_bottom_in,R.anim.push_bottom_out);
                break;
            case 2://Loading
                startActivity(new Intent(this, ILoadingActivity.class));
                break;
            case 3://ScrollView
                startActivity(new Intent(this, IScrollViewActivity.class));
                break;
            case 4://FloatDragButton
                startActivity(new Intent(this, BezierActivity.class));
                break;
            case 6://
                break;
            case 7://Popupwindow
                startActivity(new Intent(this, IPopupwindowActivity.class));
                break;
            case 8://ArcLayout
                startActivity(new Intent(this, IArcLayoutActivity.class));
                break;
            case 9://设备信息
                ActivityUtils.getInstance().showActivity(this, DeviceToolActviity.class);
                break;
            case 10://图片处理
                showBotttomDialog();
                break;
            case 11://BottomSheet
                startActivity(new Intent(this, IBottomSheetActivity.class));
                break;
            case 12://时间
                startActivity(new Intent(this, ITimeDateOrActivity.class));
                break;
            case 13://下拉刷新
                startActivity(new Intent(this, IRefreshActivity.class));
                break;
            default:
                break;
        }
    }

    private void showBotttomDialog() {
        new ActionSheetDialog(_mActivity)
                .setCancelable(false)
                .setCanceledOnTouchOutside(false)
                .addSheetItem("单张图片压缩", Color.parseColor("#037BFF"),
                        new ActionSheetDialog.OnSheetItemClickListener() {
                            @Override
                            public void onClick(int which) {
                                startActivity(new Intent(MainActivity_0122.this, IBitmapSingChoiceActivity.class));
                            }
                        })
                .addSheetItem("批量图片压缩", Color.parseColor("#037BFF"),
                        new ActionSheetDialog.OnSheetItemClickListener() {
                            @Override
                            public void onClick(int which) {
                                startActivity(new Intent(MainActivity_0122.this, IBitmapMultiChoiceActivity.class));
                            }
                        })
                .addSheetItem("系统API图片压缩", Color.parseColor("#037BFF"),
                        new ActionSheetDialog.OnSheetItemClickListener() {
                            @Override
                            public void onClick(int which) {
                                startActivity(new Intent(MainActivity_0122.this, IBitmapSystemSingleCompressActivity.class));
                            }
                        })
                .show();
    }

   /* private void showBottomSheet() {
        bottomSheetDialog = new BottomSheetDialog(this);
        //创建recyclerView
        View view = getLayoutInflater().inflate(R.layout.layout_parent_problem_local, null);
        PowerfulRecyclerView recyclerView = (PowerfulRecyclerView) view.findViewById(R.id.mRecyclerView);
        ImageView ivOffClose = view.findViewById(R.id.ivOffClose);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        ProblemReportLocalDataListAdapter recyclerAdapter = new ProblemReportLocalDataListAdapter(R.layout.item_problem_offline,problemReportCacheBeans,this);
        recyclerView.setAdapter(recyclerAdapter);

        ivOffClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (bottomSheetDialog != null && bottomSheetDialog.isShowing()){
                    bottomSheetDialog.dismiss();
                }
            }
        });
    }*/


    /**
     * 设置毛玻璃效果和沉浸状态栏
     */
    private void loadBlurAndSetStatusBar() {
        //用来设置整体下移，状态栏沉浸
//        StatusBarTool.setRootViewFitsSystemWindows(this, false);
////        StatusBarUtil.setTranslucent(MainActivity.this, 0);
//        StatusBarTool.setTranslucentStatus(MainActivity.this);//透明状态栏
        //黑色字体
        StatusBarTool.setStatusBarDarkTheme(MainActivity_0122.this, false);
        //黑色字体
//        StatusBarTool.setStatusBarDarkTheme(MainActivity.this, true);
        //设置白色字体，其他背景
//        StatusBarTool.setStatusBarColor(MainActivity.this, Color.parseColor("#58C087"));//设置背景颜色
        Glide.with(this).load(R.mipmap.image_top_main_bg)
                .centerCrop()
                .bitmapTransform(new BlurTransformation(this, 5))
                .into(new SimpleTarget<GlideDrawable>() {
                    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
                    @Override
                    public void onResourceReady(GlideDrawable resource, GlideAnimation<? super GlideDrawable> glideAnimation) {
                        headLayout.setBackground(resource);
//                        rootLayout.setBackground(resource);
                    }
                });

        Glide.with(this).load(R.mipmap.image_top_main_bg).bitmapTransform(new BlurTransformation(this, 10))
                .into(new SimpleTarget<GlideDrawable>() {
                    @Override
                    public void onResourceReady(GlideDrawable resource, GlideAnimation<? super
                            GlideDrawable> glideAnimation) {
                        mCollapsingToolbarLayout.setContentScrim(resource);
                    }
                });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        String msg = "";
        switch (item.getItemId()) {
            case R.id.userCenter:
                msg += "关于";
                showActivity(_mActivity,AboutMeActivity.class);
                break;
            case R.id.action_settings:
                msg += "设置";
                break;
        }
        if (!TextUtils.isEmpty(msg)) {
            iToast.showISimpleToast(msg);
        }
        return super.onOptionsItemSelected(item);
    }


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


    @Override
    public void onBackPressed() {
        if (mExitDialog == null) {
            mExitDialog = new ExitDialog(this);
        }
        if (!mExitDialog.isShowing()) mExitDialog.show();
        mExitDialog.setOnExitDialogClickListener(new ExitDialog.OnExitDialogClickListener() {
            @Override
            public void onConfirmListener(boolean isChecked) {
                if (isChecked) {
                    FileUtils.deleteAllFile(FileUtils.getFileDirectorHead(MainActivity_0122.this.getApplicationContext()));
                }
                MainActivity_0122.super.onBackPressed();
            }
        });
    }

    @Override
    protected void onDestroy() {
        if (mExitDialog != null && !mExitDialog.isShowing()) mExitDialog.cancel();
        super.onDestroy();
    }
}
