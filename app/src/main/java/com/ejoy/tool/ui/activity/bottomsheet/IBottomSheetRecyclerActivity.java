package com.ejoy.tool.ui.activity.bottomsheet;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ejoy.tool.R;
import com.ejoy.tool.ui.base.base_activity.BaseActivity;
import com.ejoy.tool.ui.data.resource.GlobalDataProvider;
import com.ejoy.tool.ui.mvp.base.BasePresenter;
import com.module.ires.bean.CustomBottomSheetItemBean;
import com.module.ires.bean.bean.TempModel;
import com.module.ires.bean.utils.EResUtils;
import com.module.iviews.bottomsheet.behavior.control.BottomSheetBehavior;
import com.module.iviews.bottomsheet.behavior.control.BottomSheetBehaviorRecyclerManager;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * CN:      IBottomSheetRecyclerActivity
 * Author： JSYL-DINGCL (dingcl@jsyl.com.cn)
 * Date:   2019/1/14
 * Des:    TODO:BottomSheet Recyclerview列表
 */
public class IBottomSheetRecyclerActivity extends BaseActivity {

    @BindView(R.id.parent_container)
    CoordinatorLayout mParent;
    @BindView(R.id.main_bottomsheet)
    RelativeLayout mBottomSheetView;
    @BindView(R.id.btm_recyclerview_left)
    RecyclerView mBottomSheetRecyclerLeft;
    @BindView(R.id.btm_recyclerview_right)
    RecyclerView mBottomSheetRecyclerRight;
    @BindView(R.id.main_container)
    RelativeLayout mMainContainer;



    private BottomSheetBehavior<View> mBottomSheetBehavior;
    private LinearLayoutManager mLayoutManagerRight;
    private LinearLayoutManager mLayoutManagerLeft;
    private BaseQuickAdapter mAdapterLeft;
    private BaseQuickAdapter mAdapterRight;
    private List<CustomBottomSheetItemBean> modelsLeft = new ArrayList<>();
    private List<CustomBottomSheetItemBean> modelsRight = new ArrayList<>();

    @Override
    protected Object registSatusbarBgcolor() {
        return baseTransparent;
    }

    @Override
    protected boolean isRegistSatusbarFontDark() {
        return true;
    }

    @Override
    protected void initRestore(@Nullable Bundle savedInstanceState) {

    }

    @Override
    protected int getContentViewId() {
        return R.layout.activity_bottomsheet_recyclerview;
    }

    @Override
    protected void initView(View mRootView) {
        mParent.setDescendantFocusability(ViewGroup.FOCUS_BLOCK_DESCENDANTS);
        mLayoutManagerLeft = new LinearLayoutManager(this);
        mBottomSheetRecyclerLeft.setLayoutManager(mLayoutManagerLeft);
        mLayoutManagerRight = new LinearLayoutManager(this);
        mBottomSheetRecyclerRight.setLayoutManager(mLayoutManagerRight);
        mBottomSheetBehavior = BottomSheetBehavior.from(mBottomSheetView);
        mBottomSheetBehavior.setPeekHeight(150);
        mBottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
        mBottomSheetBehavior.setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
            @Override
            public void onStateChanged(View bottomSheet, int newState) {
                //4:STATE_COLLAPSED
                Log.e(_TAG, "onSlide-------newState-----: "+newState);
            }

            @Override
            public void onSlide(View bottomSheet, float slideOffset) {
                Log.e(_TAG, "onSlide-----slideOffset-------: "+slideOffset);
                if (slideOffset > 0 && slideOffset <=1){
                    mMainContainer.setBackgroundColor(EResUtils.getColor(_mActivity,R.color.alpha_10_black));
                }else if (slideOffset == 0){
                    mMainContainer.setBackgroundColor(EResUtils.getColor(_mActivity,R.color.transparent));
                }
            }
        });

        mBottomSheetRecyclerLeft.setAdapter(mAdapterLeft = new BaseQuickAdapter<CustomBottomSheetItemBean, BaseViewHolder>(R.layout.item_bs_recyclerview,modelsLeft) {
            @Override
            protected void convert(BaseViewHolder helper, CustomBottomSheetItemBean item) {
                helper.setText(R.id.list_text,item.getCbsMenuText());
                helper.setText(R.id.list_subtext,item.getCbsMenusubText());
                Glide.with(_mActivity)
                        .load(item.getCbsIco())
                        .into((ImageView) helper.getView(R.id.itemico));
            }
        });
        mAdapterLeft.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                CustomBottomSheetItemBean tempModel = modelsLeft.get(position);
                iToast.showISimpleToast("Left Clicked ".concat(tempModel.getCbsMenuText()));
            }
        });


        mBottomSheetRecyclerRight.setAdapter(mAdapterRight = new BaseQuickAdapter<CustomBottomSheetItemBean, BaseViewHolder>(R.layout.item_bs_recyclerview,modelsRight) {
            @Override
            protected void convert(BaseViewHolder helper, CustomBottomSheetItemBean item) {
                ((ImageView) helper.getView(R.id.itemico)).setVisibility(View.GONE);
                helper.setText(R.id.list_text,item.getCbsMenuText());
                helper.setText(R.id.list_subtext,item.getCbsMenusubText());
            }
        });
        mAdapterRight.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                CustomBottomSheetItemBean tempModel = modelsRight.get(position);
                iToast.showISimpleToast("Right Clicked ".concat(tempModel.getCbsMenuText()));
            }
        });

        getListData();
        //helper to rule scrolls
        BottomSheetBehaviorRecyclerManager manager = new BottomSheetBehaviorRecyclerManager(mParent, mBottomSheetBehavior, mBottomSheetView);
        manager.addControl(mBottomSheetRecyclerLeft);
        manager.addControl(mBottomSheetRecyclerRight);
        manager.create();
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void addListener() {

    }

    @Override
    public BasePresenter getPresenter() {
        return null;
    }


    private void getListData() {
        modelsLeft.clear();modelsRight.clear();
        modelsLeft.addAll(GlobalDataProvider.getBsData());
        for (int i = 0; i < 20; i++) {
            modelsRight.add(new CustomBottomSheetItemBean(R.mipmap.ico_zan, "第"+i+"首收藏的歌曲", i+".好听就多听，倾听作者的心声，融入每一丝音符"));
        }
        mAdapterLeft.setNewData(modelsLeft);
        mAdapterRight.setNewData(modelsRight);
    }
}
