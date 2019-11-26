package com.ejoy.tool.ui.data.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.ejoy.tool.ui.fragment.floattab.ViewPagerFragmentFactory;


/**
 * Created by user on 2015/11/9.
 */
public class HomeFragmentViewPagerAdapter  extends FragmentPagerAdapter{
    private String[] tab_names=  new String[]{"私搭","热门","星榜"};
    public HomeFragmentViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment = ViewPagerFragmentFactory.createFragment(position);
        return fragment;
    }
    @Override
    public CharSequence getPageTitle(int position) {
        return tab_names[position];
    }
    @Override
    public int getCount() {
        return tab_names.length;
    }
}
