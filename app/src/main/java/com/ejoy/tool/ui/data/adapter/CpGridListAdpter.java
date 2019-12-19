package com.ejoy.tool.ui.data.adapter;


import android.content.Context;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ejoy.tool.R;
import com.ejoy.tool.common.listener.InnerListener;
import com.ejoy.tool.ui.fragment.citylist.model.HotCity;

import java.util.List;

/**
 * CN:      CpGridListAdpter
 * Author： JSYL-DINGCL (dingcl@jsyl.com.cn)
 * Date:   2019/11/18
 *  热门城市的九宫格数据
 */
public class CpGridListAdpter extends BaseQuickAdapter<HotCity,BaseViewHolder> {
    public static final int SPAN_COUNT = 3;
    private Context mContext;
    private List<HotCity> mData;
    private InnerListener mInnerListener;

    public CpGridListAdpter(int layoutResId, List<HotCity> data, Context context) {
        super(layoutResId, data);
        mContext = context;
        this.mData = data;
    }

    @Override
    protected void convert(BaseViewHolder holder, HotCity item) {
        final int pos = holder.getAdapterPosition();
        TextView tvhotTip = holder.getView(R.id.tvhotTip);
        FrameLayout flContainer = holder.getView(R.id.cp_grid_item_layout);
        TextView tvName = holder.getView(R.id.cp_gird_item_name);
        tvhotTip.setVisibility(View.GONE);
        //设置item宽高
        DisplayMetrics dm = mContext.getResources().getDisplayMetrics();
        int screenWidth = dm.widthPixels;
        TypedValue typedValue = new TypedValue();
        mContext.getTheme().resolveAttribute(R.attr.cpGridItemSpace, typedValue, true);
        int space = mContext.getResources().getDimensionPixelSize(R.dimen.cp_default_padding);
        int padding = mContext.getResources().getDimensionPixelSize(R.dimen.cp_default_padding);
        int indexBarWidth = mContext.getResources().getDimensionPixelSize(R.dimen.cp_index_bar_width);
        int itemWidth = (screenWidth - padding - space * (SPAN_COUNT - 1) - indexBarWidth) / SPAN_COUNT;
        ViewGroup.LayoutParams lp = flContainer.getLayoutParams();
        lp.width = itemWidth;
        lp.height = ViewGroup.LayoutParams.WRAP_CONTENT;
        flContainer.setLayoutParams(lp);

        tvName.setText(item.getC_name());
        if ("北京".equals(item.getC_name().trim())){
            tvhotTip.setVisibility(View.VISIBLE);
            tvhotTip.setText("故宫");
        }
        if ("上海".equals(item.getC_name().trim())){
            tvhotTip.setVisibility(View.VISIBLE);
            tvhotTip.setText("东方明珠");
        }
        if ("哈尔滨".equals(item.getC_name().trim())){
            tvhotTip.setVisibility(View.VISIBLE);
            tvhotTip.setText("冰雕");
        }
        if ("苏州".equals(item.getC_name().trim())){
            tvhotTip.setVisibility(View.VISIBLE);
            tvhotTip.setText("园林");
        }
        if ("南京".equals(item.getC_name().trim())){
            tvhotTip.setVisibility(View.VISIBLE);
            tvhotTip.setText("中山陵");
        }
        if ("天津".equals(item.getC_name().trim())){
            tvhotTip.setVisibility(View.VISIBLE);
            tvhotTip.setText("天津之眼");
        }
        flContainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mInnerListener != null){
                    mInnerListener.dismiss(pos, item);
                }
            }
        });
    }


    public void setInnerListener(InnerListener listener){
        this.mInnerListener = listener;
    }
}

