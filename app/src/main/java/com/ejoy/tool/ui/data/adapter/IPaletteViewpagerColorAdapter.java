package com.ejoy.tool.ui.data.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.ejoy.tool.ui.data.resource.GlobalDataProvider;
import com.ejoy.tool.ui.fragment.IPaletteSimpleFragment;

/**
 * @ClassName:  IPaletteViewpagerColorAdapter
 * @Author: maiduoduo
 * @BLOG: https://blog.csdn.net/Maiduoudo
 * @Date: 2020/10/15
 * @des: 提取图片主题色-ViewPager适配器
 */
public class IPaletteViewpagerColorAdapter extends FragmentStatePagerAdapter {
    public IPaletteViewpagerColorAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        IPaletteSimpleFragment fragment = IPaletteSimpleFragment.newInstance(GlobalDataProvider.mHeros[position]);
        return fragment;
    }
    @Override
    public int getCount() {
        return GlobalDataProvider.mHeros.length;
    }

}
