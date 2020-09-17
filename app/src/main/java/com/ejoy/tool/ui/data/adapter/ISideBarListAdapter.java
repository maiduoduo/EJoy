package com.ejoy.tool.ui.data.adapter;

import android.content.Context;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ejoy.tool.R;
import com.ejoy.tool.common.db.CitysBean;
import com.ejoy.tool.common.listener.InnerListener;
import com.ejoy.tool.scaffold.view.decorator.CpGridItemDecoration;
import com.ejoy.tool.ui.fragment.citylist.model.HotCity;
import com.ejoy.tool.ui.fragment.citylist.model.LocateState;
import com.ejoy.tool.ui.fragment.citylist.model.LocatedCity;

import java.util.List;



/**
 * @ClassName:  ISideBarListAdapter
 * @Author: maiduoduo
 * @BLOG: https://blog.csdn.net/Maiduoudo
 * @Date: 2020/9/17
 * @des: 城市列表选择界面数据适配
 */
public class ISideBarListAdapter extends RecyclerView.Adapter<ISideBarListAdapter.BaseViewHolder> {
    private static final int VIEW_TYPE_LOCATION = 10;
    private static final int VIEW_TYPE_HOT     = 11;
    private static final String TAG = "Fragment";

    private Context mContext;
    private List<CitysBean> mData;
    private OnInnerListener onInnerListener;
    private LinearLayoutManager mLayoutManager;
    private boolean stateChanged;
    private boolean autoLocate;

    public interface OnInnerListener {
        void selectSectionItem(int position, CitysBean data);
    }

    public ISideBarListAdapter(Context context, List<CitysBean> data) {
        this.mData = data;
        this.mContext = context;
    }


    public void setLayoutManager(LinearLayoutManager manager){
        this.mLayoutManager = manager;
    }

    public void updateData(List<CitysBean> data){
        this.mData = data;
        notifyDataSetChanged();
    }



    /**
     * 滚动RecyclerView到索引位置
     * @param index
     */
    public void scrollToSection(String index){
        if (mData == null || mData.isEmpty()) return;
        if (TextUtils.isEmpty(index)) return;
        int size = mData.size();
        for (int i = 0; i < size; i++) {
            if (TextUtils.equals(index.substring(0, 1), mData.get(i).getSection().substring(0, 1))){
                if (mLayoutManager != null){
                    mLayoutManager.scrollToPositionWithOffset(i, 0);
                    return;
                }
            }
        }
    }

    @NonNull
    @Override
    public ISideBarListAdapter.BaseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        switch (viewType){
            default:
                view = LayoutInflater.from(mContext).inflate(R.layout.item_sidebar_list_item_default_layout, parent, false);
                return new ISideBarListAdapter.DefaultViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull ISideBarListAdapter.BaseViewHolder holder, int position) {
        if (holder instanceof ISideBarListAdapter.DefaultViewHolder){
            ((ISideBarListAdapter.DefaultViewHolder) holder).nameTip.setVisibility(View.GONE);
            final int pos = holder.getAdapterPosition();
            final CitysBean data = mData.get(pos);
            if (data == null) return;
            ((ISideBarListAdapter.DefaultViewHolder)holder).name.setText(data.getC_name());
            ((ISideBarListAdapter.DefaultViewHolder) holder).llDefaultname.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (onInnerListener != null){
                        onInnerListener.selectSectionItem(pos, data);
                    }
                }
            });
            String tip = data.getC_tip();
            if (tip != null && !TextUtils.isEmpty(tip)){
                Log.e(TAG, "onBindView------2------tip----------: "+tip);
                ((ISideBarListAdapter.DefaultViewHolder) holder).nameTip.setVisibility(View.VISIBLE);
                ((ISideBarListAdapter.DefaultViewHolder) holder).nameTip.setText(tip+"");
            }else {
                ((ISideBarListAdapter.DefaultViewHolder) holder).nameTip.setVisibility(View.GONE);
            }

        }

    }

    @Override
    public int getItemCount() {
        return mData == null ? 0 : mData.size();
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0 && TextUtils.equals("定", mData.get(position).getSection().substring(0, 1)))
            return VIEW_TYPE_LOCATION;
        if (position == 1 && TextUtils.equals("热", mData.get(position).getSection().substring(0, 1)))
            return VIEW_TYPE_HOT;
        return super.getItemViewType(position);
    }

    public void setInnerListener(OnInnerListener listener){
        this.onInnerListener = listener;
    }

    static class BaseViewHolder extends RecyclerView.ViewHolder{
        BaseViewHolder(View itemView) {
            super(itemView);
        }
    }

    public static class DefaultViewHolder extends ISideBarListAdapter.BaseViewHolder {
        TextView name;
        TextView nameTip;
        LinearLayout llDefaultname;

        DefaultViewHolder(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.cp_list_item_name);
            nameTip = itemView.findViewById(R.id.cp_list_item_tip);
            llDefaultname = itemView.findViewById(R.id.llDefaultname);
        }
    }




}