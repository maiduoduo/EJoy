package com.ejoy.tool.ui.data.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import com.ejoy.tool.R;

import java.util.List;
import java.util.Random;


/**
 * @ClassName:  ISharesContentItemAdapter
 * @Author: maiduoduo
 * @BLOG: https://blog.csdn.net/Maiduoudo
 * @Date: 2021/2/22
 * @des: 股票双向锁头内容列表ITEM Adapter
 */
public class ISharesContentItemAdapter extends RecyclerView.Adapter<ISharesContentItemAdapter.ItemViewHolder> {

    private List<String> detailBeans;

    private Context mContext;

    public ISharesContentItemAdapter(Context mContext) {
        this.mContext = mContext;
    }


    public void setDetailBeans(List<String> detailBeans) {
        this.detailBeans = detailBeans;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(mContext).inflate(R.layout.item_shares_content_detail, parent, false);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
        holder.mTabView.setText(detailBeans.get(position));
        // 模拟文本显示颜色
        int random = new Random().nextInt(3);
        if (random == 1) {
            holder.mTabView.setTextColor(mContext.getResources().getColor(R.color.tabTextColor));// 灰色
        } else if (random == 2) {
            holder.mTabView.setTextColor(mContext.getResources().getColor(R.color.green_300));// 绿色
        } else {
            holder.mTabView.setTextColor(mContext.getResources().getColor(R.color.red_300));// 红色
        }
    }

    @Override
    public int getItemCount() {
        return detailBeans.size();
    }

    class ItemViewHolder extends RecyclerView.ViewHolder {

        TextView mTabView;

        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);
            mTabView = itemView.findViewById(R.id.tabView);
        }
    }
}




