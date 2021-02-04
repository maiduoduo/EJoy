package com.ejoy.tool.ui.douyin.data.adapter;

import android.content.Context;
import android.content.Intent;
import android.widget.ImageView;
import android.widget.RelativeLayout;


import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ejoy.tool.R;
import com.ejoy.tool.scaffold.utils.GlideUtils;
import com.ejoy.tool.ui.douyin.activity.DouyinPlayListActivity;
import com.module.ires.bean.bean.DouyinVideoBean;
import com.module.ires.bean.utils.EDensityUtils;

import java.util.List;


public class DouyinWorkAdapter extends BaseQuickAdapter<DouyinVideoBean,BaseViewHolder> {
    private static final String TAG = "adapter";
    private Context mContext;
    public DouyinWorkAdapter(int layoutResId, List<DouyinVideoBean> data, Context context) {
        super(layoutResId, data);
        mContext = context;
    }

    @Override
    protected void convert(BaseViewHolder helper, DouyinVideoBean item) {
        int position = helper.getAdapterPosition();
        ImageView ivCover = helper.getView(R.id.iv_cover);
        RelativeLayout rlItemWork = helper.getView(R.id.rlItemWork);


//        ivCover.setImageResource(item.getCoverRes());
        GlideUtils.showImage(mContext,item.getCoverUrl(),ivCover);
        helper.setText(R.id.tv_likecount,EDensityUtils.numberFilter(item.getLikeCount()));

        rlItemWork.setOnClickListener(v -> {
            DouyinPlayListActivity.initPos = position;
            mContext.startActivity(new Intent(mContext, DouyinPlayListActivity.class));
        });
    }



}
