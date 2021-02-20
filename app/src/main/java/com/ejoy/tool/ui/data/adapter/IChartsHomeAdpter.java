package com.ejoy.tool.ui.data.adapter;


import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ejoy.tool.R;
import com.ejoy.tool.common.bean.IChartsHomeBean;
import com.ejoy.tool.common.bean.MainItemBean;
import com.module.ires.bean.utils.EResUtils;

import java.util.List;


/**
 * @ClassName:  IChartsHomeAdpter
 * @Author: maiduoduo
 * @BLOG: https://blog.csdn.net/Maiduoudo
 * @Date: 2021/2/20
 * @des: 各种图表Home界面数据填充
 */
public class IChartsHomeAdpter extends BaseQuickAdapter<IChartsHomeBean,BaseViewHolder> {
    private static final String TAG = "IAdpter";
    private Context mContext;
    public IChartsHomeAdpter(int layoutResId, List<IChartsHomeBean> data, Context context) {
        super(layoutResId, data);
        mContext = context;
    }

    @Override
    protected void convert(BaseViewHolder helper, IChartsHomeBean item) {
        int position = helper.getAdapterPosition();
        TextView itemName = helper.getView(R.id.item_name);
        TextView itemDes = helper.getView(R.id.item_des);
        itemName.setText(item.getItemTitle() == null ? "" : item.getItemTitle());
        itemDes.setText(item.getItemDes() == null ? "" : item.getItemDes());
        itemName.setTextColor(EResUtils.getColor(mContext,R.color.white));
    }
}

