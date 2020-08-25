package com.module.iviews.easynavigationbar.ui.weibo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.module.iviews.R;
import com.module.iviews.easynavigationbar.ui.NavigationBarWeiboActivity;

/**
 * NavigationBarWBFirstFragment
 */

public class NavigationBarWBFirstFragment extends android.support.v4.app.Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_navigationbar_wb_first, null);

        Button bt01 = view.findViewById(R.id.bt01);
        bt01.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (getActivity() instanceof NavigationBarWeiboActivity) {
                    ((NavigationBarWeiboActivity) getActivity()).getNavigationBar().setMsgPointCount(2, 109);
                    ((NavigationBarWeiboActivity) getActivity()).getNavigationBar().setMsgPointCount(0, 5);
                    ((NavigationBarWeiboActivity) getActivity()).getNavigationBar().setHintPoint(3, true);
                }
            }
        });


        Button bt02 = view.findViewById(R.id.bt02);
        bt02.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (getActivity() instanceof NavigationBarWeiboActivity) {
                    ((NavigationBarWeiboActivity) getActivity()).getNavigationBar().clearAllHintPoint();
                    ((NavigationBarWeiboActivity) getActivity()).getNavigationBar().clearAllMsgPoint();
                }

            }
        });

        Button bt03 = view.findViewById(R.id.bt03);
        bt03.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (getActivity() instanceof NavigationBarWeiboActivity) {
                    ((NavigationBarWeiboActivity) getActivity()).getNavigationBar().clearMsgPoint(0);
                }
            }
        });


        Button bt04 = view.findViewById(R.id.bt04);
        bt04.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (getActivity() instanceof NavigationBarWeiboActivity) {
                    ((NavigationBarWeiboActivity) getActivity()).getNavigationBar().clearHintPoint(3);

                }
            }
        });

        Button bt05 = view.findViewById(R.id.bt05);
        bt05.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (getActivity() instanceof NavigationBarWeiboActivity) {
                    ((NavigationBarWeiboActivity) getActivity()).getNavigationBar().selectTab(1, true);
                    ((NavigationBarWBSecondFragment) (((NavigationBarWeiboActivity) getActivity()).getNavigationBar().getAdapter().getItem(1))).showToast("嘻嘻哈哈嗝");
                }
            }
        });

        Button bt06 = view.findViewById(R.id.bt06);
        bt06.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (getActivity() instanceof NavigationBarWeiboActivity) {
                    ((NavigationBarWeiboActivity) getActivity()).changeStyle();
                }
            }
        });

        Button bt07 = view.findViewById(R.id.bt07);
        bt07.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (getActivity() instanceof NavigationBarWeiboActivity) {
                    ((NavigationBarWeiboActivity) getActivity()).getNavigationBar().updateNavigationText(1, true, "变了");
                    ((NavigationBarWeiboActivity) getActivity()).getNavigationBar().updateNavigationIcon(1, true, R.mipmap.eicon_navigation_add_image);
                }
            }
        });

        return view;
    }


}
