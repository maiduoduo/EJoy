package com.ejoy.tool.ui.douyin.data.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ejoy.tool.R;
import com.ejoy.tool.scaffold.utils.GlideUtils;
import com.module.ires.bean.bean.DouyinVideoBean;
import com.module.iviews.image.DouyinCircleImageView;

import java.util.List;


public class DouyinFansAdapter extends BaseQuickAdapter<DouyinVideoBean.UserBean,BaseViewHolder> {
    private static final String TAG = "adapter";
    private Context mContext;
    public DouyinFansAdapter(int layoutResId, List<DouyinVideoBean.UserBean> data, Context context) {
        super(layoutResId, data);
        mContext = context;
    }

    @Override
    protected void convert(BaseViewHolder helper, DouyinVideoBean.UserBean item) {
        int position = helper.getAdapterPosition();
        DouyinCircleImageView ivHead = helper.getView(R.id.iv_head);
        TextView tvNickname = helper.getView(R.id.tv_nickname);
        TextView tvFocus = helper.getView(R.id.tv_focus);

        GlideUtils.showImage(mContext,item.getHeadUrl(),ivHead);
        tvNickname.setText(item.getNickName());
        tvFocus.setText(item.isFocused() ? "已关注" : "关注");

        tvFocus.setOnClickListener(v -> {
            if (!item.isFocused()) {
                tvFocus.setText("已关注");
                tvFocus.setBackgroundResource(R.drawable.shape_round_halfwhite);
            } else {
                tvFocus.setText("关注");
                tvFocus.setBackgroundResource(R.drawable.shape_round_red);
            }

            item.setFocused(!item.isFocused());
        });
    }



}
