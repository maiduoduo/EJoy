package com.ejoy.tool.ui.douyin.data.adapter;

import android.content.Context;
import android.content.Intent;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ejoy.tool.R;
import com.ejoy.tool.scaffold.utils.GlideUtils;
import com.ejoy.tool.ui.douyin.activity.DouyinPlayListActivity;
import com.module.ires.bean.bean.DouyinVideoBean;
import com.module.iviews.textview.IconFontTextView;

import java.util.List;

public class DouyinGridVideoAdapter extends BaseQuickAdapter<DouyinVideoBean, BaseViewHolder> {
    private static final String TAG = "adapter";
    private Context mContext;

    public DouyinGridVideoAdapter(int layoutResId, List<DouyinVideoBean> data, Context context) {
        super(layoutResId, data);
        mContext = context;
    }

    @Override
    protected void convert(BaseViewHolder helper, DouyinVideoBean item) {
        int position = helper.getAdapterPosition();
        ImageView ivCover = helper.getView(R.id.iv_cover);
        TextView tvContent = helper.getView(R.id.tv_content);
        IconFontTextView tvDistance = helper.getView(R.id.tv_distance);
        ImageView ivHead = helper.getView(R.id.iv_head);
        LinearLayout llGridView = helper.getView(R.id.llGridView);


//        ivCover.setBackgroundResource(item.getCoverRes());
        GlideUtils.showImage(mContext,item.getCoverUrl(),ivCover);
        tvContent.setText(item.getContent());
        tvDistance.setText(item.getDistance() + " km");
        GlideUtils.showImage(mContext,item.getUserBean().getHeadUrl(),ivHead);
//        ivHead.setImageResource(item.getUserBean().getHead());

        llGridView.setOnClickListener(v -> {
            DouyinPlayListActivity.initPos = position;
            mContext.startActivity(new Intent(mContext, DouyinPlayListActivity.class));
        });
    }

}
