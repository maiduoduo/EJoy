package com.ejoy.tool.ui.data.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.ejoy.tool.R;
import com.ejoy.tool.common.bean.ISharesContentBean;
import com.module.iviews.charts.ICustomizeScrollView;

import java.util.ArrayList;
import java.util.List;


/**
 * @ClassName:  IStockContentAdapter
 * @Author: maiduoduo
 * @BLOG: https://blog.csdn.net/Maiduoudo
 * @Date: 2021/2/22
 * @des: 股票双向锁头内容列表Adapter
 */
public class IStockContentAdapter extends RecyclerView.Adapter<IStockContentAdapter.ViewHolder> {
    /**
     * 保存列表ViewHolder集合
     */
    private List<ViewHolder> recyclerViewHolder = new ArrayList<>();
    /**
     * 记录item滑动的位置，用于RecyclerView上下滚动时更新所有列表
     */
    private int offestX;

    private OnTabScrollViewListener onTabScrollViewListener;

    private List<ISharesContentBean> stockBeans;

    private Context mContext;

    public IStockContentAdapter(Context mContext) {
        this.mContext = mContext;
    }

    public void setOnTabScrollViewListener(OnTabScrollViewListener onTabScrollViewListener) {
        this.onTabScrollViewListener = onTabScrollViewListener;
    }

    public void setStockBeans(List<ISharesContentBean> stockBeans) {
        this.stockBeans = stockBeans;
        notifyDataSetChanged();
    }

    public List<ViewHolder> getRecyclerViewHolder() {
        return recyclerViewHolder;
    }

    public int getOffestX() {
        return offestX;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_stock_content_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {
        holder.mStockName.setText(stockBeans.get(position).getStockName());
        holder.mStockRecyclerView.setLayoutManager(new LinearLayoutManager(mContext, RecyclerView.HORIZONTAL, false));
        holder.mStockRecyclerView.setNestedScrollingEnabled(false);

        // TODO：文本RecyclerView中具体信息的RecyclerView（RecyclerView嵌套）
        IStockContentItemAdapter stockItemAdapter = new IStockContentItemAdapter(mContext);
        holder.mStockRecyclerView.setAdapter(stockItemAdapter);
        stockItemAdapter.setDetailBeans(stockBeans.get(position).getDetail());

        if (!recyclerViewHolder.contains(holder)) {
            recyclerViewHolder.add(holder);
        }

        /**
         * 第一步：水平滑动item时，遍历所有ViewHolder，使得整个列表的HorizontalScrollView同步滚动
         */
        holder.mStockScrollView.setViewListener(new ICustomizeScrollView.OnScrollViewListener() {
            @Override
            public void onScroll(int l, int t, int oldl, int oldt) {
                for (ViewHolder viewHolder : recyclerViewHolder) {
                    if (viewHolder != holder) {
                        viewHolder.mStockScrollView.scrollTo(l, 0);
                    }
                }
                /**
                 * 第二步：水平滑动item时，接口回调到Tab栏的HorizontalScrollView，使得Tab栏跟随item滚动实时更新
                 */
                if (onTabScrollViewListener != null) {
                    onTabScrollViewListener.scrollTo(l, t);
                    offestX = l;
                }
            }
        });


    }

    @Override
    public int getItemCount() {
        return stockBeans.size() == 0 ? 0 : stockBeans.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView mStockName;
        public ICustomizeScrollView mStockScrollView;
        public RecyclerView mStockRecyclerView;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mStockName = itemView.findViewById(R.id.stockName);
            mStockScrollView = itemView.findViewById(R.id.stockScrollView);
            mStockRecyclerView = itemView.findViewById(R.id.stockRecyclerView);
        }
    }


    public interface OnTabScrollViewListener {
        void scrollTo(int l, int t);
    }
}
