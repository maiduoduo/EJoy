package com.ejoy.tool.ui.activity.bottomsheet;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ScrollView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ejoy.tool.R;
import com.ejoy.tool.scaffold.utils.StatusBarTool;
import com.ejoy.tool.scaffold.view.decorator.DividerItemDecoration;
import com.ejoy.tool.ui.base.base_activity.BaseActivity;
import com.ejoy.tool.ui.data.resource.GlobalDataProvider;
import com.ejoy.tool.ui.mvp.base.BasePresenter;
import com.module.ires.bean.CustomBottomSheetItemBean;
import com.module.iviews.bottomsheet.custom.ICustomBottomSheet;
import com.module.iviews.bottomsheet.custom.ICustomBottomSheetBehavior;
import com.module.iviews.bottomsheet.custom.scrollview.ObservableScrollView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import jp.wasabeef.glide.transformations.RoundedCornersTransformation;

/**
 * CN:      IBitmapMultiChoiceActivity
 * Author： JSYL-DINGCL (dingcl@jsyl.com.cn)
 * Date:   2019/12/26
 * Des:    自定义BottomSheet
 */
public class ICustomBottomSheetActivity extends BaseActivity implements View.OnClickListener {

    @BindView(R.id.ivBack)
    ImageView ivBack;
    @BindView(R.id.title)
    FrameLayout mTitle;
    @BindView(R.id.bottomSheet)
    ICustomBottomSheet mBottomSheet;
    @BindView(R.id.coordinatorLayout)
    CoordinatorLayout mCoordinatorLayout;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.ObservableScrollView)
    ObservableScrollView mObservableScrollView;
    @BindView(R.id.bgNestedScrollView)
    ScrollView bgNestedScrollView;
    @BindView(R.id.iv_long_img)
    ImageView ivPicture;
    @BindView(R.id.mFloatingActionButton)
    FloatingActionButton mFloatingActionButton;
    @BindView(R.id.ivShade)
    ImageView ivShade;
    @BindView(R.id.ivAlbum)
    ImageView ivAlbum;

    private ICustomBottomSheetBehavior bottomSheetBehavior;
    private int expandedScrimColor;
    private int collapsedScrimColor;
    private CoordinatorLayout.LayoutParams layoutParams;
    private Bundle savedInstanceState;
    private BaseQuickAdapter mAdapter;

    @Override
    protected void initRestore(@Nullable Bundle savedInstanceState) {
        this.savedInstanceState = savedInstanceState;
    }

    @Override
    protected int getContentViewId() {
        return R.layout.activity_custom_bottomsheet;
    }

    @Override
    protected void initView(View mRootView) {
        StatusBarTool.setTranslucentStatus(this);//透明状态栏
        //用来设置整体下移，状态栏沉浸
        StatusBarTool.setRootViewFitsSystemWindows(this, false);
        //黑色字体
        StatusBarTool.setStatusBarDarkTheme(this, false);
        expandedScrimColor = ContextCompat.getColor(this, R.color.black_1e);
        collapsedScrimColor = ContextCompat.getColor(this, R.color.black_1e);
        ivShade.setVisibility(View.GONE);
        recyclerView.setLayoutManager(new LinearLayoutManager(_mActivity));
        recyclerView.addItemDecoration(new DividerItemDecoration(_mActivity,R.color.light_gray3) );
        recyclerView.setAdapter(mAdapter = new BaseQuickAdapter<CustomBottomSheetItemBean, BaseViewHolder>(R.layout.item_icbs_icotext, bsList) {
            @Override
            protected void convert(BaseViewHolder helper, CustomBottomSheetItemBean item) {
                helper.setText(R.id.tvbsTitle,item.getCbsMenuText());
                ImageView ivBs = helper.getView(R.id.ivBs);
                Glide.with(_mActivity).load(item.getCbsIco())
                        .asBitmap()
                        .skipMemoryCache(true) // 不使用内存缓存
                        .diskCacheStrategy(DiskCacheStrategy.NONE) // 不使用磁盘缓存
                        .into(new SimpleTarget<Bitmap>() {
                            @Override
                            public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
                                ivBs.setImageBitmap(resource);
                            }
                        });
            }
        });
//        recyclerView.addItemDecoration(new ISpaceItemDecoration(getResources().getDimensionPixelSize(R.dimen.list_spacing)));
        layoutParams = (CoordinatorLayout.LayoutParams) mBottomSheet.getLayoutParams();
        bottomSheetBehavior = (ICustomBottomSheetBehavior) layoutParams.getBehavior();
        bottomSheetBehavior.setPeekHeight(0);
        assert bottomSheetBehavior != null;
        if (savedInstanceState == null) {
            bottomSheetBehavior.setState(ICustomBottomSheetBehavior.STATE_HIDDEN);
        }


        Glide.with(this).load(R.mipmap.img_bs_scrollpic)
                .asBitmap()
                .skipMemoryCache(true) // 不使用内存缓存
                .diskCacheStrategy(DiskCacheStrategy.NONE) // 不使用磁盘缓存
                .into(new SimpleTarget<Bitmap>() {
                    @Override
                    public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
                        ivPicture.setImageBitmap(resource);
                    }
                });

        Glide.with(this).load(R.mipmap.img_song_album)
//                .bitmapTransform(new CropCircleTransformation(this))
                .bitmapTransform(new RoundedCornersTransformation(this,20,0))
                .skipMemoryCache(true) // 不使用内存缓存
                .diskCacheStrategy(DiskCacheStrategy.NONE) // 不使用磁盘缓存
                .into(ivAlbum);
    }

    private List<CustomBottomSheetItemBean> bsList = new ArrayList<>();


    @Override
    protected void initData() {
        mAdapter.setNewData(GlobalDataProvider.getBsData());

    }

    private float posX,posY,curPosX,curPosY;
    private float bsposX,bsposY,bscurPosX,bscurPosY;
    private float rvposX,rvposY,rvcurPosX,rvcurPosY;
    @Override
    protected void addListener() {
        mFloatingActionButton.setOnClickListener(this);
        ivBack.setOnClickListener(this);
        bottomSheetBehavior.setBottomSheetCallback(new ICustomBottomSheetBehavior.BottomSheetCallback() {
            @Override
            public void onStateChanged(@NonNull View bottomSheet, int newState) {
                if (bottomSheetBehavior.getState() == ICustomBottomSheetBehavior.STATE_HIDDEN) {
                    ivShade.setVisibility(View.GONE);
                }else if (newState == ICustomBottomSheetBehavior.STATE_EXPANDED) {
                    mTitle.setBackgroundColor(expandedScrimColor);
                    ivShade.setVisibility(View.VISIBLE);
                } else {
                    mTitle.setBackgroundColor(collapsedScrimColor);
                    ivShade.setVisibility(View.GONE);
                }

            }

            @Override
            public void onSlide(@NonNull View bottomSheet, float slideOffset) {

            }
        });
        bgNestedScrollView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN://按下
                        posX = event.getX();
                        posY = event.getY();
                        break;
                    case MotionEvent.ACTION_MOVE://滑动
                        curPosX = event.getX();
                        curPosY = event.getY();
                        //背景界面滑动时，禁止底部自定义bottomSheet界面拉出
                        bottomSheetBehavior.setState(ICustomBottomSheetBehavior.STATE_COLLAPSED);
                        break;
                    case MotionEvent.ACTION_UP:
                        if ((curPosX - posX > 0) && (Math.abs(curPosX - posX) > 25)) {
                            Log.e(_TAG, "向左滑动");
                        } else if ((curPosX - posX < 0) && (Math.abs(curPosX - posX) > 25)) {
                            Log.e(_TAG, "向右滑动");
                        }
                        if ((curPosY - posY > 0) && (Math.abs(curPosY - posY) > 0)) {
                            Log.e(_TAG, "向下滑动");
                        } else if ((curPosY - posY < 0) && (Math.abs(curPosY - posY) > 25)) {
                            Log.e(_TAG, "向上滑动");
                        }
                        break;
                }
                return false;
            }
        });
        mBottomSheet.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN://按下
                        bsposX = event.getX();
                        bsposY = event.getY();
                        break;
                    case MotionEvent.ACTION_MOVE://滑动
                        bscurPosX = event.getX();
                        bscurPosY = event.getY();
                        //背景遮罩
                        ivShade.setVisibility(View.VISIBLE);
                        break;
                    case MotionEvent.ACTION_UP:
                        if ((bscurPosY - bsposY > 0) && (Math.abs(bscurPosY - bsposY) > 0)) {
                            Log.e(_TAG, "BS向下滑动");
                            ivShade.setVisibility(View.VISIBLE);
                        } else if ((bscurPosY - bsposY < 0) && (Math.abs(bscurPosY - bsposY) > 25)) {
                            Log.e(_TAG, "BS向上滑动");
                            ivShade.setVisibility(View.VISIBLE);
                        }
                        break;
                }
                return false;
            }
        });
        mObservableScrollView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN://按下
                        rvposX = event.getX();
                        rvposY = event.getY();
                        break;
                    case MotionEvent.ACTION_MOVE://滑动
                        rvcurPosX = event.getX();
                        rvcurPosY = event.getY();
                        //背景遮罩
                        ivShade.setVisibility(View.VISIBLE);
                        break;
                    case MotionEvent.ACTION_UP:
                        if ((rvcurPosY - rvposY > 0) && (Math.abs(rvcurPosY - rvposY) > 0)) {
                            Log.e(_TAG, "RV向下滑动");
                            ivShade.setVisibility(View.VISIBLE);
                        } else if ((rvcurPosY - rvposY < 0) && (Math.abs(rvcurPosY - rvposY) > 25)) {
                            Log.e(_TAG, "RV向上滑动");
                            ivShade.setVisibility(View.VISIBLE);
                        }
                        break;
                }
                return false;
            }
        });
        mObservableScrollView.setOnScrollStatusListener(new ObservableScrollView.OnScrollStatusListener() {
            @Override public void onScrollStop() {
                ivShade.setVisibility(View.VISIBLE);
            }

            @Override public void onScrolling() {
                ivShade.setVisibility(View.VISIBLE);
            }
        });

    }

    @Override
    public BasePresenter getPresenter() {
        return null;
    }


    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        if (bottomSheetBehavior.getState() == ICustomBottomSheetBehavior.STATE_HIDDEN) {
            ivShade.setVisibility(View.GONE);
        }else {
            ivShade.setVisibility(View.VISIBLE);
        }
    }

    /**
     * 点击监听事件
     *
     * @param v
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.mFloatingActionButton:
                ivShade.setVisibility(View.VISIBLE);
                bottomSheetBehavior.setState(ICustomBottomSheetBehavior.STATE_EXPANDED);
                break;
            case R.id.ivBack:
                finish();
                break;
        }

    }



}
