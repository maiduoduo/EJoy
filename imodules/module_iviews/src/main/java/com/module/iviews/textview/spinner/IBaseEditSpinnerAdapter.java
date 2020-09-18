package com.module.iviews.textview.spinner;

import android.widget.BaseAdapter;

/**
 * IBaseEditSpinnerAdapter
 */
public abstract class IBaseEditSpinnerAdapter extends BaseAdapter {
    /**
     * editText输入监听
     *
     * @return
     */
    public abstract IEditSpinnerFilter getEditSpinnerFilter();

    /**
     * 获取需要填入editText的字符串
     * @param position
     * @return
     */
    public abstract String getItemString(int position);

}
