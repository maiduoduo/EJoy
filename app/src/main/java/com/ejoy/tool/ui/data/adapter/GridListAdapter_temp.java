package com.ejoy.tool.ui.data.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.ejoy.tool.R;
import com.ejoy.tool.common.db.CitysBean;
import com.ejoy.tool.common.listener.InnerListener;
import com.ejoy.tool.ui.fragment.citylist.model.HotCity;

import java.util.List;

public class GridListAdapter_temp extends RecyclerView.Adapter<GridListAdapter_temp.GridViewHolder>{
    public static final int SPAN_COUNT = 3;

    private Context mContext;
    private List<HotCity> mData;
    private InnerListener mInnerListener;

    public GridListAdapter_temp(Context context, List<HotCity> data) {
        this.mContext = context;
        this.mData = data;
    }

    @Override
    public GridViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.cp_grid_item_layout, parent, false);
        return new GridViewHolder(view);
    }

    @Override
    public void onBindViewHolder(GridViewHolder holder, int position) {
        final int pos = holder.getAdapterPosition();
        final CitysBean data = mData.get(pos);
        holder.tvhotTip.setVisibility(View.GONE);
        if (data == null) return;
        //设置item宽高
        DisplayMetrics dm = mContext.getResources().getDisplayMetrics();
        int screenWidth = dm.widthPixels;
        TypedValue typedValue = new TypedValue();
        mContext.getTheme().resolveAttribute(R.attr.cpGridItemSpace, typedValue, true);
        int space = mContext.getResources().getDimensionPixelSize(R.dimen.cp_default_padding);
        int padding = mContext.getResources().getDimensionPixelSize(R.dimen.cp_default_padding);
        int indexBarWidth = mContext.getResources().getDimensionPixelSize(R.dimen.cp_index_bar_width);
        int itemWidth = (screenWidth - padding - space * (SPAN_COUNT - 1) - indexBarWidth) / SPAN_COUNT;
        ViewGroup.LayoutParams lp = holder.container.getLayoutParams();
        lp.width = itemWidth;
        lp.height = ViewGroup.LayoutParams.WRAP_CONTENT;
        holder.container.setLayoutParams(lp);

        holder.name.setText(data.getC_name());
        if ("北京".equals(data.getC_name().trim())){
            holder.tvhotTip.setVisibility(View.VISIBLE);
            holder.tvhotTip.setText("故宫");
        }
        if ("上海".equals(data.getC_name().trim())){
            holder.tvhotTip.setVisibility(View.VISIBLE);
            holder.tvhotTip.setText("东方明珠");
        }
        if ("哈尔滨".equals(data.getC_name().trim())){
            holder.tvhotTip.setVisibility(View.VISIBLE);
            holder.tvhotTip.setText("冰雕");
        }
        if ("苏州".equals(data.getC_name().trim())){
            holder.tvhotTip.setVisibility(View.VISIBLE);
            holder.tvhotTip.setText("园林");
        }
        if ("南京".equals(data.getC_name().trim())){
            holder.tvhotTip.setVisibility(View.VISIBLE);
            holder.tvhotTip.setText("中山陵");
        }
        if ("天津".equals(data.getC_name().trim())){
            holder.tvhotTip.setVisibility(View.VISIBLE);
            holder.tvhotTip.setText("天津之眼");
        }
        holder.container.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mInnerListener != null){
                    mInnerListener.dismiss(pos, data);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mData == null ? 0 : mData.size();
    }

    public static class GridViewHolder extends RecyclerView.ViewHolder{
        FrameLayout container;
        TextView name;
        TextView tvhotTip;

        public GridViewHolder(View itemView) {
            super(itemView);
            container = itemView.findViewById(R.id.cp_grid_item_layout);
            name = itemView.findViewById(R.id.cp_gird_item_name);
            tvhotTip = itemView.findViewById(R.id.tvhotTip);
        }
    }

    public void setInnerListener(InnerListener listener){
        this.mInnerListener = listener;
    }
}
