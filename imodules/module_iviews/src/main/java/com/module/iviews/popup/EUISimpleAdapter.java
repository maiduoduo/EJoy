package com.module.iviews.popup;

import android.content.Context;
import android.view.Gravity;
import android.view.View;

import com.module.ires.bean.utils.EDensityUtils;
import com.module.iviews.R;

import java.util.ArrayList;
import java.util.List;

/**
 * 简单的适配器
 */
public class EUISimpleAdapter extends BaseListAdapter<AdapterItem, ViewHolder> {

    private int mPaddingLeftPx;

    public EUISimpleAdapter(Context context) {
        super(context);
    }

    public EUISimpleAdapter(Context context, List<AdapterItem> data) {
        super(context, data);
    }

    public EUISimpleAdapter(Context context, AdapterItem[] data) {
        super(context, data);
    }

    @Override
    protected ViewHolder newViewHolder(View convertView) {
        ViewHolder holder = new ViewHolder();
        holder.mLLContentView = convertView.findViewById(R.id.ll_content);
        holder.mTvTitle = convertView.findViewById(R.id.tv_title);
        holder.mIvIcon = convertView.findViewById(R.id.iv_icon);

        if (mPaddingLeftPx != 0) {
            holder.mLLContentView.setPadding(mPaddingLeftPx, 0, 0, 0);
            holder.mLLContentView.setGravity(Gravity.CENTER_VERTICAL);
        } else {
            holder.mLLContentView.setGravity(Gravity.CENTER);
        }
        return holder;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.eui_adapter_listview_simple_item;
    }

    @Override
    protected void convert(ViewHolder holder, AdapterItem item, int position) {
        holder.mTvTitle.setText(item.getTitle());
        if (item.getIcon() != null) {
            holder.mIvIcon.setVisibility(View.VISIBLE);
            holder.mIvIcon.setImageDrawable(item.getIcon());
        } else {
            holder.mIvIcon.setVisibility(View.GONE);
        }

    }

    public EUISimpleAdapter setPaddingLeftPx(int paddingLeftPx) {
        mPaddingLeftPx = paddingLeftPx;
        return this;
    }

    public EUISimpleAdapter setPaddingLeftDp(Context context,int paddingLeftDp) {
        mPaddingLeftPx = EDensityUtils.dp2px(context,paddingLeftDp);
        return this;
    }

    /**
     * 创建简单的适配器【不含图标】
     *
     * @param context
     * @param data
     * @return
     */
    public static EUISimpleAdapter create(Context context, String[] data) {
        if (data != null && data.length > 0) {
            List<AdapterItem> lists = new ArrayList<>();
            for (int i = 0; i < data.length; i++) {
                lists.add(new AdapterItem(data[i]));
            }
            return new EUISimpleAdapter(context, lists);
        } else {
            return new EUISimpleAdapter(context);
        }
    }

    /**
     * 创建简单的适配器【不含图标】
     *
     * @param context
     * @param data
     * @return
     */
    public static EUISimpleAdapter create(Context context, List<String> data) {
        if (data != null && data.size() > 0) {
            List<AdapterItem> lists = new ArrayList<>();
            for (int i = 0; i < data.size(); i++) {
                lists.add(new AdapterItem(data.get(i)));
            }
            return new EUISimpleAdapter(context, lists);
        } else {
            return new EUISimpleAdapter(context);
        }
    }

}
