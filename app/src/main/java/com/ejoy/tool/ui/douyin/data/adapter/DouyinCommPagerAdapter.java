package com.ejoy.tool.ui.douyin.data.adapter;

import android.os.Parcelable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.ArrayList;

/**
 * @ClassName:  DouyinCommPagerAdapter
 * @Author: maiduoduo
 * @BLOG: https://blog.csdn.net/Maiduoudo
 * @Date: 2021/2/2
 * @des: 公共viewPageradapter
 */
public class DouyinCommPagerAdapter extends FragmentStatePagerAdapter {

    private ArrayList<? extends Fragment> items;
    private String[] mTitles;

    public DouyinCommPagerAdapter(FragmentManager fm, ArrayList< ? extends Fragment> items, String[] mTitles) {
        super(fm);
        this.items = items;
        this.mTitles = mTitles;
    }

    @Override
    public int getCount() {
        return items.size()==0 ? 0 :items.size();
    }

    @Override
    public Fragment getItem(int position) {
        return items.get(position);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mTitles[position];
    }

    @Override
    public int getItemPosition(Object object) {
        return POSITION_NONE;

    }
    @Override
    public Parcelable saveState() {
        return null;
    }
}