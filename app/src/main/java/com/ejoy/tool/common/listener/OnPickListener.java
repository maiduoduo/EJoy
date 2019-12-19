package com.ejoy.tool.common.listener;


import com.ejoy.tool.common.db.CitysBean;
import com.ejoy.tool.ui.fragment.citylist.model.City;

public interface OnPickListener {
//    void onPick(int position, CitysBean data);
    void onPick(int position, City data);
    void onPick(int position, CitysBean data);
    void onLocate();
    void onCancel();
}
