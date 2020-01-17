package com.ejoy.tool.ui.data.adapter;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ejoy.tool.R;
import com.ejoy.tool.common.bean.MainItemBean;
import com.ejoy.tool.scaffold.utils.IToast;
import com.ejoy.tool.ui.activity.compress.IBitmapMultiChoiceActivity;
import com.ejoy.tool.ui.activity.compress.IBitmapSingChoiceActivity;
import com.ejoy.tool.ui.activity.compress.IBitmapSystemSingleCompressActivity;
import com.google.gson.Gson;
import com.maple.msdialog.ActionSheetDialog;
import com.module.ires.bean.view.EGridDividerItemDecoration;

import java.util.List;


/**
 * 首页适配器
 *
 * @author dingcl
 * 数据绑定未进行详细的数据验证，再实际使用中不可取
 */
public class HomeMultipleRecycleAdapter extends BaseMultiItemQuickAdapter<MainItemBean, BaseViewHolder> {
    private String TAG = "HomeMultipleRecycleAdapter";
    private Activity context;
    private List<MainItemBean> mData;
    public IToast iToast;


    /**
     * Same as QuickAdapter#QuickAdapter(Context,int) but with
     * some initialization data.
     *
     * @param data A new list is created out of this one to avoid mutable list
     */
    public HomeMultipleRecycleAdapter(Activity context, List<MainItemBean> data) {
        super(data);
        this.context = context;
        //初始化自定义Toast
        iToast = new IToast().builder();
        addItemType(MainItemBean.MAIN_TYPE_TITLE, R.layout.layout_ihome_title);
        addItemType(MainItemBean.MAIN_TYPE_CONTENT, R.layout.layout_ihome_content);
        Log.e(TAG, "HomeMultipleRecycleAdapter555:  -----data----------\n"+new Gson().toJson(data));
    }


    @Override
    protected void convert(BaseViewHolder helper, MainItemBean item) {
        Log.e(TAG, "HomeMultipleRecycleAdapter:  -----getItemType----------" + item.getType() + "  " + item.getItemTypeTitle());
        int position = helper.getAdapterPosition();
        switch (helper.getItemViewType()) {
            case MainItemBean.MAIN_TYPE_TITLE:
                bindTitleData(helper, item);
                Log.e(TAG, "HomeMultipleRecycleAdapter333:  --bindTitleData-------------");
                break;
            case MainItemBean.MAIN_TYPE_CONTENT:
                bindContentListData(helper, item);
                Log.e(TAG, "HomeMultipleRecycleAdapter333:  -----bindContentListData----------");
                break;
            default:
                break;
        }
    }


    /**
     * 标题
     *
     * @param helper
     * @param item
     */
    private void bindTitleData(BaseViewHolder helper, MainItemBean item) {
        if (item != null) {
            helper.setText(R.id.homeTile, item.getItemTypeTitle() == null ? "" : item.getItemTypeTitle());
            Log.e(TAG, "HomeMultipleRecycleAdapter444:  -----bindTitleData----------");
        }
    }


    /**
     * 内容列表
     *
     * @param helper
     * @param item
     */
    private void bindContentListData(BaseViewHolder helper, MainItemBean item) {
        if (item != null) {
            Log.e(TAG, "HomeMultipleRecycleAdapter444:  -----bindContentListData----------");
            RecyclerView mRecyclerView = helper.getView(R.id.iRecyclerView);
            //Recyclerview
            GridLayoutManager gridLayoutManager = new GridLayoutManager(context, 3);
            mRecyclerView.setLayoutManager(gridLayoutManager);
            mRecyclerView.setHasFixedSize(true);
            mRecyclerView.setItemViewCacheSize(10);
            CHMainAdpter mCHMainAdpter = new CHMainAdpter(R.layout.item_main_layout, item.getContent(), context);
            mRecyclerView.setAdapter(mCHMainAdpter);
            // 打开动画效果
            mCHMainAdpter.openLoadAnimation();
//            IGridItemDecoration build = new IGridItemDecoration.Builder(context)
//                    .size(1)
//                    .color(R.color.Gray4)
//                    .margin(2, 2)
//                    .isExistHead(false)
//                    .showLastDivider(false)
//                    .showHeadDivider(false)
//                    .build();

//            mRecyclerView.addItemDecoration(build);
//            mRecyclerView.addItemDecoration(new GridDividerItemDecoration(1,EResUtils.getColor(context,R.color.Gray4)));
            mRecyclerView.addItemDecoration(new EGridDividerItemDecoration(context, 3, 1, R.color.LGray3));

            // 动画一直执行
            mCHMainAdpter.isFirstOnly(true);
            mCHMainAdpter.openLoadAnimation(BaseQuickAdapter.SCALEIN);
            mCHMainAdpter.setNotDoAnimationCount(10);
            mCHMainAdpter.setOnItemClickListener(new OnItemClickListener() {
                @Override
                public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                    MainItemBean.ContentBean contentBean = item.getContent().get(position);
                    String flag = contentBean.getFlag();
                    if (!TextUtils.isEmpty(contentBean.getClazz())) {
//                        showActivity(context, contentBean.clazz);
                        Class clazz = null;
                        try {
                            clazz = Class.forName(contentBean.getClazz());
                        } catch (ClassNotFoundException e) {
                            e.printStackTrace();
                        }
                        Intent intent = new Intent(context,clazz);
                        context.startActivity(intent);
                    } else {
                        if ("BITMAP".equals(flag)) {
                            showBotttomDialog();
                        }
                    }
                }
            });
        }

    }

    public void showActivity(Activity aty, Class clazz, int enterAnim, int exitAnim) {
        Intent i = new Intent(aty, clazz);
        aty.startActivity(i);
        aty.overridePendingTransition(enterAnim, exitAnim);
    }

    public void showActivity(Activity aty, Class clazz) {
        Intent i = new Intent(aty, clazz);
        aty.startActivity(i);
        aty.overridePendingTransition(R.anim.zoomin, R.anim.zoomout);
    }

    private void showBotttomDialog() {
        new ActionSheetDialog(context)
                .setCancelable(false)
                .setCanceledOnTouchOutside(false)
                .addSheetItem("单张图片压缩", Color.parseColor("#037BFF"),
                        new ActionSheetDialog.OnSheetItemClickListener() {
                            @Override
                            public void onClick(int which) {
                                showActivity(context, IBitmapSingChoiceActivity.class);
                            }
                        })
                .addSheetItem("批量图片压缩", Color.parseColor("#037BFF"),
                        new ActionSheetDialog.OnSheetItemClickListener() {
                            @Override
                            public void onClick(int which) {
                                showActivity(context, IBitmapMultiChoiceActivity.class);
                            }
                        })
                .addSheetItem("系统API图片压缩", Color.parseColor("#037BFF"),
                        new ActionSheetDialog.OnSheetItemClickListener() {
                            @Override
                            public void onClick(int which) {
                                showActivity(context, IBitmapSystemSingleCompressActivity.class);
                            }
                        })
                .show();
    }
}

