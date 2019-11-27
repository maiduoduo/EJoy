package com.iduo.filterlib.listener;

import com.iduo.filterlib.FilterResultBean;

import java.util.List;


public interface OnSelectResultListener {

    void onSelectResult(FilterResultBean resultBean);

    void onSelectResultList(List<FilterResultBean> resultBeans);
}
