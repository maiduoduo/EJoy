package com.ejoy.tool.ui.activity.more_detail;
//  ┏┓　　　┏┓
//┏┛┻━━━┛┻┓
//┃　　　　　　　┃
//┃　　　━　　　┃
//┃　┳┛　┗┳　┃
//┃　　　　　　　┃
//┃　　　┻　　　┃
//┃　　　　　　　┃
//┗━┓　　　┏━┛
//    ┃　　　┃                  神兽保佑
//    ┃　　　┃                  永无BUG！
//    ┃　　　┗━━━┓
//    ┃　　　　　　　┣┓
//    ┃　　　　　　　┏┛
//    ┗┓┓┏━┳┓┏┛
//      ┃┫┫　┃┫┫
//      ┗┻┛　┗┻┛

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.ejoy.tool.R;
import com.ejoy.tool.ui.base.base_activity.IBaseActivity;
import com.flyco.tablayout.SlidingTabLayout;
import com.kongzue.baseframework.interfaces.DarkStatusBarTheme;
import com.kongzue.baseframework.interfaces.Layout;
import com.module.ires.bean.utils.EDensityUtils;
import com.module.iviews.behavior.mimusicbehavior.fragment.SongFragment;
import com.module.iviews.behavior.mimusicbehavior.fragment.TabFragment;

import java.lang.reflect.Field;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.OnClick;


/**
 * @ClassName:  IMiMusicBehaviorActivity
 * @Author: maiduoduo
 * @BLOG: https://blog.csdn.net/Maiduoudo
 * @Date: 2020/9/16
 * @des: 自定义Behavior实现小米音乐歌手详情页
 */
@Layout(R.layout.activity_iui_mimusic_behavior)
@DarkStatusBarTheme(true)
//@DarkNavigationBarTheme(true)
public class IMiMusicBehaviorActivity extends IBaseActivity {

    @BindView(R.id.stl)
    SlidingTabLayout mSl;
    @BindView(R.id.vp)
    ViewPager mViewPager;


    private final String[] mTitles = {
            "热门", "专辑", "视屏","资讯"
    };
    private ArrayList<Fragment> mFragments = new ArrayList<>();
    private MiMusicFragmentAdapter mFragmentAdapter;

    @Override
    protected boolean isRegistSatusbarFullScreenTransluent() {
        return true;
    }

    @Override
    protected boolean isRegistSatusbarFontDark() {
        return true;
    }


    @Override
    public void initViews() {
        setDarkNavigationBarTheme(false);

    }

    @Override
    public void initDatas() {
        mFragments.add(new SongFragment());
        mFragments.add(TabFragment.newInstance("我是专辑页面"));
        mFragments.add(TabFragment.newInstance("我是视屏页面"));
        mFragments.add(TabFragment.newInstance("我是资讯页面"));
        mFragmentAdapter = new MiMusicFragmentAdapter(getSupportFragmentManager());
    }


    @Override
    public void setEvents() {

        mViewPager.setAdapter(mFragmentAdapter);
        mSl.setViewPager(mViewPager, mTitles);

        //反射修改最少滑动距离
        try {
            Field mTouchSlop = ViewPager.class.getDeclaredField("mTouchSlop");
            mTouchSlop.setAccessible(true);
            mTouchSlop.setInt(mViewPager, EDensityUtils.dp2px(me,50));
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
        mViewPager.setOffscreenPageLimit(mFragments.size());

    }


    @OnClick({
    })
    public void eventClick(View view) {
        switch (view.getId()) {

        }
    }




    private class MiMusicFragmentAdapter extends FragmentPagerAdapter {

        MiMusicFragmentAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int i) {
            return mFragments.get(i);
        }

        @Override
        public int getCount() {
            return mFragments.size();
        }
    }

}
