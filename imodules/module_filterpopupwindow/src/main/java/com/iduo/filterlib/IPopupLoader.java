package com.iduo.filterlib;

import android.content.Context;
import android.widget.PopupWindow;

import com.iduo.filterlib.listener.OnFilterToViewListener;

import java.util.List;


public interface IPopupLoader {

    PopupWindow getPopupEntity(Context context, List data, int filterType, int position, OnFilterToViewListener onFilterToViewListener, FilterTabView view);
}
