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
 * CN:      CpCitySelectListAdapter
 * Author： JSYL-DINGCL (dingcl@jsyl.com.cn)
 * Date:   2019/11/20
 *  城市列表选择界面数据适配
 */
public class CpCitySelectListAdapter  extends RecyclerView.Adapter<CpCitySelectListAdapter.BaseViewHolder> {
    private static final int VIEW_TYPE_LOCATION = 10;
    private static final int VIEW_TYPE_HOT     = 11;
    private static final String TAG = "Fragment";

    private Context mContext;
    private List<CitysBean> mData;
    private List<HotCity> mHotData;
    private int locateState;
    private InnerListener mInnerListener;
    private LinearLayoutManager mLayoutManager;
    private boolean stateChanged;
    private boolean autoLocate;

    public CpCitySelectListAdapter(Context context, List<CitysBean> data, List<HotCity> hotData, int state) {
        this.mData = data;
        this.mContext = context;
        this.mHotData = hotData;
        this.locateState = state;
    }

    public void autoLocate(boolean auto){
        autoLocate = auto;
    }

    public void setLayoutManager(LinearLayoutManager manager){
        this.mLayoutManager = manager;
    }

    public void updateData(List<CitysBean> data){
        this.mData = data;
        notifyDataSetChanged();
    }

    public void updateLocateState(LocatedCity location, int state){
        mData.remove(0);
        mData.add(0, location);
        stateChanged = !(locateState == state);
        locateState = state;
        refreshLocationItem();
    }

    public void refreshLocationItem(){
        //如果定位城市的item可见则进行刷新
        if (stateChanged && mLayoutManager.findFirstVisibleItemPosition() == 0) {
            stateChanged = false;
            notifyItemChanged(0);
        }
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
                    if (TextUtils.equals(index.substring(0, 1), "定")) {
                        //防止滚动时进行刷新
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                if (stateChanged) notifyItemChanged(0);
                            }
                        }, 1000);
                    }
                    return;
                }
            }
        }
    }

    @NonNull
    @Override
    public CpCitySelectListAdapter.BaseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        switch (viewType){
            case VIEW_TYPE_LOCATION:
                view = LayoutInflater.from(mContext).inflate(R.layout.cp_newlist_item_location_layout, parent, false);
                return new CpCitySelectListAdapter.LocationViewHolder(view);
            case VIEW_TYPE_HOT:
                view = LayoutInflater.from(mContext).inflate(R.layout.cp_list_item_hot_layout, parent, false);
                return new CpCitySelectListAdapter.HotViewHolder(view);
            default:
                view = LayoutInflater.from(mContext).inflate(R.layout.cp_list_item_default_layout, parent, false);
                return new CpCitySelectListAdapter.DefaultViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull CpCitySelectListAdapter.BaseViewHolder holder, int position) {
        if (holder instanceof CpCitySelectListAdapter.DefaultViewHolder){
            ((CpCitySelectListAdapter.DefaultViewHolder) holder).nameTip.setVisibility(View.GONE);
            final int pos = holder.getAdapterPosition();
            final CitysBean data = mData.get(pos);
            if (data == null) return;
            ((CpCitySelectListAdapter.DefaultViewHolder)holder).name.setText(data.getC_name());
            ((CpCitySelectListAdapter.DefaultViewHolder) holder).llDefaultname.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mInnerListener != null){
                        mInnerListener.dismiss(pos, data);
                    }
                }
            });
            String tip = data.getC_tip();
            if (tip != null && !TextUtils.isEmpty(tip)){
                Log.e(TAG, "onBindView------2------tip----------: "+tip);
                ((CpCitySelectListAdapter.DefaultViewHolder) holder).nameTip.setVisibility(View.VISIBLE);
                ((CpCitySelectListAdapter.DefaultViewHolder) holder).nameTip.setText(tip+"");
            }else {
                ((CpCitySelectListAdapter.DefaultViewHolder) holder).nameTip.setVisibility(View.GONE);
            }

        }
        //定位城市
        if (holder instanceof CpCitySelectListAdapter.LocationViewHolder){
            final int pos = holder.getAdapterPosition();
            final CitysBean data = mData.get(pos);
            if (data == null) return;
            //设置宽高
            DisplayMetrics dm = mContext.getResources().getDisplayMetrics();
            int screenWidth = dm.widthPixels;
            TypedValue typedValue = new TypedValue();
            mContext.getTheme().resolveAttribute(R.attr.cpGridItemSpace, typedValue, true);
            int space = mContext.getResources().getDimensionPixelSize(R.dimen.cp_index_bar_width1);
            int padding = mContext.getResources().getDimensionPixelSize(R.dimen.cp_default_padding);
            int indexBarWidth = mContext.getResources().getDimensionPixelSize(R.dimen.cp_index_bar_width);
            int itemWidth = (screenWidth - padding - space * (CpGridListAdpter.SPAN_COUNT - 1) - indexBarWidth) / CpGridListAdpter.SPAN_COUNT;
            ViewGroup.LayoutParams lp = ((CpCitySelectListAdapter.LocationViewHolder) holder).container.getLayoutParams();
            lp.width = itemWidth;
            lp.height = ViewGroup.LayoutParams.WRAP_CONTENT;
            ((CpCitySelectListAdapter.LocationViewHolder) holder).container.setLayoutParams(lp);
            ((CpCitySelectListAdapter.LocationViewHolder) holder).current.setTextSize(14);
            ((CpCitySelectListAdapter.LocationViewHolder) holder).current.setTextColor(ContextCompat.getColor(mContext,R.color.black_77));
            switch (locateState){
                case LocateState.LOCATING:
                    ((CpCitySelectListAdapter.LocationViewHolder) holder).current.setText(R.string.cp_locating);
                    break;
                case LocateState.SUCCESS:
                    ((CpCitySelectListAdapter.LocationViewHolder) holder).current.setText(data.getC_name());
                    break;
                case LocateState.FAILURE:
                    ((CpCitySelectListAdapter.LocationViewHolder) holder).current.setText(R.string.cp_locate_failed);
                    break;
            }
            ((CpCitySelectListAdapter.LocationViewHolder) holder).container.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (locateState == LocateState.SUCCESS) {
                        if (mInnerListener != null) {
                            mInnerListener.dismiss(pos, data);
                        }
                    } else if (locateState == LocateState.FAILURE){
                        locateState = LocateState.LOCATING;
                        notifyItemChanged(0);
                        if (mInnerListener != null){
                            mInnerListener.locate();
                        }
                    }
                }
            });
            //第一次弹窗，如果未定位则自动定位
            if (autoLocate && locateState == LocateState.LOCATING && mInnerListener != null){
                mInnerListener.locate();
                autoLocate = false;
            }
        }
        //热门城市
        if (holder instanceof CpCitySelectListAdapter.HotViewHolder){
            final int pos = holder.getAdapterPosition();
            final CitysBean data = mData.get(pos);
            if (data == null) return;
            CpGridListAdpter mAdapter = new CpGridListAdpter(R.layout.cp_grid_item_layout, mHotData,mContext);
            mAdapter.setInnerListener(mInnerListener);
            ((CpCitySelectListAdapter.HotViewHolder) holder).mRecyclerView.setAdapter(mAdapter);
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

    public void setInnerListener(InnerListener listener){
        this.mInnerListener = listener;
    }

    static class BaseViewHolder extends RecyclerView.ViewHolder{
        BaseViewHolder(View itemView) {
            super(itemView);
        }
    }

    public static class DefaultViewHolder extends CpCitySelectListAdapter.BaseViewHolder {
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

    public static class HotViewHolder extends CpCitySelectListAdapter.BaseViewHolder {
        RecyclerView mRecyclerView;

        HotViewHolder(View itemView) {
            super(itemView);
            mRecyclerView = itemView.findViewById(R.id.cp_hot_list);
            mRecyclerView.setHasFixedSize(true);
            mRecyclerView.setLayoutManager(new GridLayoutManager(itemView.getContext(),
                    CpGridListAdpter.SPAN_COUNT, LinearLayoutManager.VERTICAL, false));
            int space = itemView.getContext().getResources().getDimensionPixelSize(R.dimen.cp_grid_item_space);
            mRecyclerView.addItemDecoration(new CpGridItemDecoration(CpGridListAdpter.SPAN_COUNT,
                    space));
        }
    }

    public static class LocationViewHolder extends CpCitySelectListAdapter.BaseViewHolder {
        FrameLayout container;
        TextView current;

        LocationViewHolder(View itemView) {
            super(itemView);
            container = itemView.findViewById(R.id.cp_list_item_location_layout);
            current = itemView.findViewById(R.id.cp_list_item_location);
        }
    }
}