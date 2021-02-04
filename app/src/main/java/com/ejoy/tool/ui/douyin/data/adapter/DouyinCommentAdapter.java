package com.ejoy.tool.ui.douyin.data.adapter;

import android.content.Context;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ejoy.tool.R;
import com.ejoy.tool.scaffold.utils.GlideUtils;
import com.ejoy.tool.ui.douyin.bean.DouyinCommentBean;
import com.module.ires.bean.utils.EDensityUtils;
import com.module.iviews.image.DouyinCircleImageView;

import java.util.List;


public class DouyinCommentAdapter extends BaseQuickAdapter<DouyinCommentBean,BaseViewHolder> {
    private static final String TAG = "adapter";
    private Context mContext;
    public DouyinCommentAdapter(int layoutResId, List<DouyinCommentBean> data, Context context) {
        super(layoutResId, data);
        mContext = context;
    }

    @Override
    protected void convert(BaseViewHolder helper, DouyinCommentBean item) {
        int position = helper.getAdapterPosition();
        DouyinCircleImageView ivHead = helper.getView(R.id.iv_head);
        TextView tvNickname = helper.getView(R.id.tv_nickname);
        TextView tvContent = helper.getView(R.id.tv_content);
        TextView tvLikecount = helper.getView(R.id.tv_likecount);

        GlideUtils.showImage(mContext,item.getUserBean().getHeadUrl(),ivHead);
        tvNickname.setText(item.getUserBean().getNickName());
        tvContent.setText(item.getContent());
        tvLikecount.setText(EDensityUtils.numberFilter(item.getLikeCount()));
    }



}
