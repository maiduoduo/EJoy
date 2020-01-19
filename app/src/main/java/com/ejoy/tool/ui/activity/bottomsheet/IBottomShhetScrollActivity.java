package com.ejoy.tool.ui.activity.bottomsheet;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.widget.NestedScrollView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;

import com.ejoy.tool.R;
import com.ejoy.tool.ui.base.base_activity.BaseActivity;
import com.ejoy.tool.ui.mvp.base.BasePresenter;
import com.module.ires.bean.utils.EDensityUtils;
import com.module.ires.bean.utils.EResUtils;
import com.module.iviews.bottomsheet.behavior.control.BottomSheetBehavior;
import com.module.iviews.bottomsheet.behavior.control.BottomSheetBehaviorRecyclerManager;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * CN:      IBottomShhetScrollActivity
 * Author： JSYL-DINGCL (dingcl@jsyl.com.cn)
 * Date:   2019/1/14
 * Des:    TODO:BottomSheet ScrollView列表
 */
public class IBottomShhetScrollActivity extends BaseActivity {

    @BindView(R.id.parent_container)
    CoordinatorLayout mParent;
    @BindView(R.id.main_bottomsheet)
    RelativeLayout mBottomSheetView;
    @BindView(R.id.scrollContent)
    NestedScrollView mScrollContent;
    @BindView(R.id.llscrollContent)
    LinearLayout mLlscrollContent;
    private BottomSheetBehavior mBottomSheetBehavior;


    @Override
    protected boolean isRegistSatusbarFontDark() {
        return true;
    }

    @Override
    protected void initRestore(@Nullable Bundle savedInstanceState) {

    }

    @Override
    protected int getContentViewId() {
        return R.layout.activity_bottomsheet_scroll;
    }

    @Override
    protected void initView(View mRootView) {
        ViewGroup.LayoutParams params = mBottomSheetView.getLayoutParams();
        params.height = EDensityUtils.dp2px(_mActivity,500);
        mBottomSheetView.setLayoutParams(params);
        mParent.setDescendantFocusability(ViewGroup.FOCUS_BLOCK_DESCENDANTS);
        mBottomSheetBehavior = BottomSheetBehavior.from(mBottomSheetView);
        mBottomSheetBehavior.setPeekHeight(150);
        mBottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
        mBottomSheetBehavior.setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
            @Override
            public void onStateChanged(View bottomSheet, int newState) {
                //4:STATE_COLLAPSED
                Log.e(_TAG, "onSlide-------newState-----: " + newState);
            }

            @Override
            public void onSlide(View bottomSheet, float slideOffset) {
                Log.e(_TAG, "onSlide-----slideOffset-------: " + slideOffset);
                if (slideOffset > 0 && slideOffset <= 1) {
                    mParent.setBackgroundColor(EResUtils.getColor(_mActivity, R.color.black_33alpha));
                } else if (slideOffset == 0) {
                    mParent.setBackgroundColor(EResUtils.getColor(_mActivity, R.color.material_blue_200));
                }
            }
        });

        //helper to rule scrolls
        BottomSheetBehaviorRecyclerManager manager = new BottomSheetBehaviorRecyclerManager(mParent, mBottomSheetBehavior, mBottomSheetView);
        manager.addControl(mScrollContent);
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

    @OnClick({R.id.parent_container})
    public void bindviewclick(View view) {
        switch (view.getId()) {
            case R.id.parent_container:
                mBottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                break;
        }
    }

}
