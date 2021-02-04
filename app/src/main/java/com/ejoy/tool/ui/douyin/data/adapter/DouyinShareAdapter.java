package com.ejoy.tool.ui.douyin.data.adapter;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ejoy.tool.R;
import com.ejoy.tool.ui.douyin.bean.DouyinShareBean;

import java.util.List;

public class DouyinShareAdapter extends BaseQuickAdapter<DouyinShareBean, BaseViewHolder> {
        private static final String TAG = "adapter";
        private Context mContext;

    public DouyinShareAdapter(int layoutResId, List<DouyinShareBean> data, Context context) {
            super(layoutResId, data);
            mContext = context;
        }

        @Override
        protected void convert(BaseViewHolder helper, DouyinShareBean item) {
            int position = helper.getAdapterPosition();
            TextView tvIcon = helper.getView(R.id.tv_icon);
            TextView tvText = helper.getView(R.id.tv_text);
            View viewBg = helper.getView(R.id.view_bg);


//            GlideUtils.showImage(mContext,item.getIconRes(), ivIcon);
            tvIcon.setText(item.getIconRes());
            tvText.setText(item.getText());
            viewBg.setBackgroundResource(item.getBgRes());
        }



}
