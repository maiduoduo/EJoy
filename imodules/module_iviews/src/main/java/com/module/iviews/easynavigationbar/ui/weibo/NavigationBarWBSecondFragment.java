package com.module.iviews.easynavigationbar.ui.weibo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.module.iviews.R;


/**
 * NavigationBarWBSecondFragment
 */

public class NavigationBarWBSecondFragment extends android.support.v4.app.Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_navigationbar_b, container,false);
        return view;
    }

    //提示消息
    public void showToast(String str) {
        Log.e(getClass().getName(),"hhhhhhhh");
    }
}
