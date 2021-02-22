package com.module.iviews.charts.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.module.iviews.R;
import com.module.iviews.charts.ICustomizeMarqueeView;
import com.module.iviews.charts.bean.IMessageBean;

import java.util.List;

/**
 * @ClassName:  IMarquessViewAdapter
 * @Author: maiduoduo
 * @BLOG: https://blog.csdn.net/Maiduoudo
 * @Date: 2021/2/22
 * @des: 自定义跑马灯View数据adapter
 */
public class IMarquessViewAdapter {

    private OnDataChangedListener mOnDataChangedListener;
    private List<IMessageBean> messageBeans;
    private Context mContext;

    public IMarquessViewAdapter(Context mContext) {
        this.mContext = mContext;
    }

    public void setMessageBeans(List<IMessageBean> messageBeans) {
        this.messageBeans = messageBeans;
    }

    /**
     * 自定义跑马灯的Item布局
     *
     * @param parent
     * @return
     */
    public View onCreateView(ICustomizeMarqueeView parent) {
        return LayoutInflater.from(parent.getContext()).inflate(R.layout.item_shares_marqueeview, null);
    }

    /**
     * 更新数据
     * @param view
     * @param position
     */
    public void onBindView(View view, int position) {
        TextView timeTextView = view.findViewById(R.id.time);
        TextView stockNameTextView = view.findViewById(R.id.stockName);
        TextView typeTextView = view.findViewById(R.id.type);
        TextView messageTextView = view.findViewById(R.id.message);
        timeTextView.setText(messageBeans.get(position).getTime());
        stockNameTextView.setText(messageBeans.get(position).getStockName());
        typeTextView.setText(messageBeans.get(position).getType().equals("1") ? "主力吃货" : "主力出货");
        typeTextView.setTextColor(messageBeans.get(position).getType().equals("1") ?
                mContext.getResources().getColor(R.color.red_300) : mContext.getResources().getColor(R.color.green_300));
        messageTextView.setTextColor(messageBeans.get(position).getType().equals("1") ?
                mContext.getResources().getColor(R.color.red_300) : mContext.getResources().getColor(R.color.green_300));
        messageTextView.setText(messageBeans.get(position).getMessage());

    }

    public void setOnDataChangedListener(OnDataChangedListener onDataChangedListener) {
        mOnDataChangedListener = onDataChangedListener;
    }

    public int getItemCount() {
        return messageBeans == null ? 0 : messageBeans.size();
    }

    public void notifyDataChanged() {
        if (mOnDataChangedListener != null) {
            mOnDataChangedListener.onChanged();
        }
    }

    public interface OnDataChangedListener {
        void onChanged();
    }
}
