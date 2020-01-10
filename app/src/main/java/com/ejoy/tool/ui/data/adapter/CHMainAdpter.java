package com.ejoy.tool.ui.data.adapter;


import android.content.Context;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ejoy.tool.R;
import com.ejoy.tool.common.bean.MainItemBean;

import java.util.List;

/**
 * CN:      CHMainAdpter
 * Author： JSYL-DINGCL (dingcl@jsyl.com.cn)
 * Date:   2019/11/21
 * Des:    主界面数据适配器
 */
public class CHMainAdpter extends BaseQuickAdapter<MainItemBean.ContentBean,BaseViewHolder> {
    private static final String TAG = "CHMainAdpter";
    private Context mContext;
    public CHMainAdpter(int layoutResId, List<MainItemBean.ContentBean> data, Context context) {
        super(layoutResId, data);
        mContext = context;
    }

    @Override
    protected void convert(BaseViewHolder helper, MainItemBean.ContentBean item) {
        int position = helper.getAdapterPosition();
        helper.setText(R.id.item_name, item.getItemTitle() == null ? "" : item.getItemTitle());
        helper.setText(R.id.item_des, item.getItemDes() == null ? "" : item.getItemDes());
//        helper.setImageResource(R.id.item_icon,item.getItemSrc());
    }
}

