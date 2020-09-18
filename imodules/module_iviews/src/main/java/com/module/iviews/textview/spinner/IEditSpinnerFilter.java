package com.module.iviews.textview.spinner;

/**
 * IEditSpinnerFilter
 *
 * @author WrBug
 * @since 2017/2/25
 */
public interface IEditSpinnerFilter {
    /**
     * editText输入监听
     * @param keyword
     * @return
     */
    boolean onFilter(String keyword);
}
