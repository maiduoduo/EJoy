package com.ejoy.tool.ui.data.adapter;

import android.content.Context;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ejoy.tool.R;

import java.util.List;

/**
 * @ClassName:  ISharesTabAdapter
 * @Author: maiduoduo
 * @BLOG: https://blog.csdn.net/Maiduoudo
 * @Date: 2021/2/22
 * @des: 股票双向锁头列表TAB adapter
 */
public class ISharesTabAdapter extends BaseQuickAdapter<String,BaseViewHolder> {
    private static final String TAG = "ISLTabRecyclerViewAdapter";
    private Context context;
    private List<String> tabData;
    public ISharesTabAdapter(int layoutResId, List<String> data, Context context) {
        super(layoutResId, data);
        this.context = context;
        this.tabData = data;
        notifyDataSetChanged();
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {
        int position = helper.getAdapterPosition();
        TextView mTabView = helper.getView(R.id.tabView);
        mTabView.setText(item);
    }





}




