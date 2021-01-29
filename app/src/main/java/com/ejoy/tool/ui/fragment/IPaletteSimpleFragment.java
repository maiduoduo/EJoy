package com.ejoy.tool.ui.fragment;

import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.ejoy.tool.R;
import com.module.iviews.paletteimageview.IPaletteImageView;


/**
 * @ClassName:  IPaletteSimpleFragment
 * @Author: maiduoduo
 * @BLOG: https://blog.csdn.net/Maiduoudo
 * @Date: 2020/10/15
 * @des: 取图片主题色-ViewPager嵌套的Fragment
 */
public class IPaletteSimpleFragment extends Fragment {
    private static final String IMG_ID = "img_id";
    public IPaletteImageView paletteImageView;
    private LinearLayout mContainer;


    public static IPaletteSimpleFragment newInstance(int imgId){
        IPaletteSimpleFragment fragment = new IPaletteSimpleFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(IMG_ID,imgId);
        fragment.setArguments(bundle);
        return fragment;
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.item_palettecolor_fragment_one,container,false);
        mContainer  = (LinearLayout) view.findViewById(R.id.container);
        paletteImageView = (IPaletteImageView) view.findViewById(R.id.palette);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (getArguments() != null){
            int imgId = getArguments().getInt(IMG_ID);
            paletteImageView.setBitmap(BitmapFactory.decodeResource(getResources(),imgId));
        }
    }

    @Override
    public void onPause() {
        mContainer.removeAllViews();
        super.onPause();
    }
}
