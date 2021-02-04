package com.ejoy.tool.ui.douyin.data.adapter;

import android.content.Context;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ejoy.tool.R;
import com.ejoy.tool.scaffold.utils.GlideUtils;
import com.module.ires.bean.bean.DouyinVideoBean;

import java.util.List;

public class DouyinPrivateLetterAdapter extends BaseQuickAdapter<DouyinVideoBean.UserBean, BaseViewHolder> {
    private static final String TAG = "adapter";
    private Context mContext;

    public DouyinPrivateLetterAdapter(int layoutResId, List<DouyinVideoBean.UserBean> data, Context context) {
        super(layoutResId, data);
        mContext = context;
    }

    @Override
    protected void convert(BaseViewHolder helper, DouyinVideoBean.UserBean item) {
        int position = helper.getAdapterPosition();
        ImageView ivHead = helper.getView(R.id.iv_head);
        TextView tvName = helper.getView(R.id.tv_nickname);

        GlideUtils.showImage(mContext, item.getHeadUrl(), ivHead);
        tvName.setText(item.getNickName());
    }



}
