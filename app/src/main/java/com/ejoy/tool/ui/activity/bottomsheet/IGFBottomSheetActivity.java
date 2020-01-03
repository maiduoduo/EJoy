package com.ejoy.tool.ui.activity.bottomsheet;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetDialog;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ejoy.tool.R;
import com.ejoy.tool.ui.base.base_activity.BaseActivity;
import com.ejoy.tool.ui.data.resource.GlobalDataProvider;
import com.ejoy.tool.ui.mvp.base.BasePresenter;
import com.module.ires.bean.CustomBottomSheetItemBean;
import com.module.ires.bean.utils.EDensityUtils;
import com.module.ires.bean.utils.WidgetUtils;
import com.module.iviews.popup.AdapterItem;
import com.module.iviews.popup.EUISimpleAdapter;
import com.module.iviews.popup.EUISimpleExpandableListAdapter;
import com.module.iviews.popup.EUISimpleExpandablePopup;
import com.module.iviews.popup.EUISimplePopup;
import com.module.iviews.popup.ExpandableItem;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * CN:      IBitmapMultiChoiceActivity
 * Author： JSYL-DINGCL (dingcl@jsyl.com.cn)
 * Date:   2019/12/26
 * Des:    官方BottomSheet
 */
public class IGFBottomSheetActivity extends BaseActivity implements View.OnClickListener {
    @BindView(R.id.ivTitleMore)
    ImageView mIvTitleMore;

    private EUISimplePopup mListPopup;
    private EUISimplePopup mMenuPopup;
    private EUISimpleExpandablePopup mExpandableListPopup;

    @Override
    protected void initRestore(@Nullable Bundle savedInstanceState) {
    }

    @Override
    protected int getContentViewId() {
        return R.layout.activity_ibottomsheet;
    }

    @Override
    protected boolean isRegistSatusbarFullScreenTransluent() {
        return true;
    }


    @Override
    protected void initView(View mRootView) {
        initListPopup();
        initMenuPopup();
        initExpandableListPopup();
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


    /**
     * 点击监听事件
     *
     * @param v
     */
    @OnClick({
            R.id.llBsList,
            R.id.llBsgrid,
            R.id.ivBsPubUrl,
            R.id.ivTitleMore,
            R.id.bt1,
            R.id.bt2,
            R.id.bt3,
    })
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.llBsList:
                showBottomSheetListDialog(true);
                iToast.showISimpleToast("BottomSheetDialog-底部列表");
                break;
            case R.id.llBsgrid:
                showBottomSheetListDialog(false);
                iToast.showISimpleToast("BottomSheetDialog-九宫格");
                break;
            case R.id.ivBsPubUrl:
                //跳转到官网
                String bsPublicUrl = "https://developer.android.google.cn/reference/com/google/android/material/bottomsheet/BottomSheetDialog";
                Intent intent = new Intent();
                intent.setAction("android.intent.action.VIEW");
                intent.setData(Uri.parse(bsPublicUrl));
                startActivity(intent);
                break;
            case R.id.ivTitleMore:
                //popup:更多样式
                mMenuPopup.showDown(v);
                break;
            case R.id.bt1:
                mListPopup.showDown(v);
                break;
            case R.id.bt2:
                mExpandableListPopup.clearExpandStatus();
                mExpandableListPopup.showDown(v);
                break;
            case R.id.bt3:
                mMenuPopup.showDown(v);
                break;
        }

    }

    /**
     * 展示BottomSheetDialog
     *
     * @param isList
     */
    private void showBottomSheetListDialog(boolean isList) {
        BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(_mActivity);
        View view = LayoutInflater.from(_mActivity).inflate(R.layout.ejoy_dialog_bottom_sheet, null);
        RecyclerView recyclerView = view.findViewById(R.id.recyclerView);
        if (isList) {
            initDialogList(recyclerView);
        } else {
            initDialogGrid(recyclerView);
        }
        bottomSheetDialog.setContentView(view);
        View parent = (View) view.getParent();
        //辅助设置圆角
        parent.setBackgroundColor(_mActivity.getResources().getColor(android.R.color.transparent));
        CoordinatorLayout.LayoutParams layoutParams = (CoordinatorLayout.LayoutParams) (parent)
                .getLayoutParams();
        parent.setLayoutParams(layoutParams);
        bottomSheetDialog.setCancelable(true);
        bottomSheetDialog.setCanceledOnTouchOutside(true);
        bottomSheetDialog.show();
    }


    private void initDialogList(RecyclerView recyclerView) {
        List<CustomBottomSheetItemBean> bsData = GlobalDataProvider.getBsData();
        for (int i = 0; i < 10; i++) {
            bsData.add(i, new CustomBottomSheetItemBean(i, "第" + i + "首推荐的歌曲", "依据你最近的听歌风格与听歌类型计算推荐的歌曲"));
        }
        LinearLayoutManager manager = new LinearLayoutManager(_mActivity);
        manager.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(new BaseQuickAdapter<CustomBottomSheetItemBean, BaseViewHolder>(R.layout.ejoy_adapter_item_simple_list, bsData) {
            @Override
            protected void convert(BaseViewHolder helper, CustomBottomSheetItemBean item) {
                helper.setText(R.id.tv_title, item.getCbsMenuText());
                helper.setText(R.id.tv_sub_title, item.getCbsMenusubText());
            }
        });
    }

    private void initDialogGrid(RecyclerView recyclerView) {
        List<CustomBottomSheetItemBean> bsData = GlobalDataProvider.getBsData();
        WidgetUtils.initGridRecyclerView(recyclerView, 3, EDensityUtils.dp2px(_mActivity, 2));
        recyclerView.setAdapter(new BaseQuickAdapter<CustomBottomSheetItemBean, BaseViewHolder>(R.layout.item_main_layout, bsData) {
            @Override
            protected void convert(BaseViewHolder helper, CustomBottomSheetItemBean item) {
                ImageView itemIcon = helper.getView(R.id.item_icon);
                helper.setText(R.id.item_name, item.getCbsMenuText());

                Glide.with(_mActivity).load(item.getCbsIco())
//                .bitmapTransform(new CropCircleTransformation(this))
                        .skipMemoryCache(true) // 不使用内存缓存
                        .diskCacheStrategy(DiskCacheStrategy.NONE) // 不使用磁盘缓存
                        .into(itemIcon);
            }
        });
    }


    private void initListPopup() {
        mListPopup = new EUISimplePopup(_mActivity, GlobalDataProvider.dpiItems)
                .create(EDensityUtils.dp2px(_mActivity, 170), new EUISimplePopup.OnPopupItemClickListener() {
                    @Override
                    public void onItemClick(EUISimpleAdapter adapter, AdapterItem item, int position) {
                        iToast.showIDefaultImgResToast(item.getTitle().toString());
                    }
                })
                .setHasDivider(true);
    }

    /**
     * 初始化popupwindow
     */
    private void initMenuPopup() {
        mMenuPopup = new EUISimplePopup(_mActivity, GlobalDataProvider.menuItems)
                .create(new EUISimplePopup.OnPopupItemClickListener() {
                    @Override
                    public void onItemClick(EUISimpleAdapter adapter, AdapterItem item, int position) {
                        iToast.showIDefaultImgResToast(item.getTitle().toString());
                    }
                });
        mMenuPopup.setPopupTopBottomMinMargin(-30);
        mMenuPopup.setPopupLeftRightMinMargin(-50);
        mMenuPopup.setPositionOffsetX(-30);
        mMenuPopup.setHasDivider(true);
    }

    private void initExpandableListPopup() {
        mExpandableListPopup = new EUISimpleExpandablePopup(_mActivity, GlobalDataProvider.expandableItems)
                .create(EDensityUtils.dp2px(_mActivity, 200), EDensityUtils.dp2px(_mActivity, 200))
                .setOnExpandableItemClickListener(false, new EUISimpleExpandablePopup.OnExpandableItemClickListener() {
                    @Override
                    public void onExpandableItemClick(EUISimpleExpandableListAdapter adapter, ExpandableItem group, int groupPosition, int childPosition) {
                        iToast.showIDefaultImgResToast(group.getChildItem(childPosition).getTitle()+"");
                    }
                });
        mExpandableListPopup.setPopupTopBottomMinMargin(50);
        mExpandableListPopup.setPopupLeftRightMinMargin(20);
    }


}
