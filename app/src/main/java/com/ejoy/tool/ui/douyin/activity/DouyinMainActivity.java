package com.ejoy.tool.ui.douyin.activity;

import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.widget.Toast;

import com.ejoy.tool.R;
import com.ejoy.tool.ui.base.base_activity.IBaseActivity;
import com.ejoy.tool.ui.douyin.bean.DouyinMainPageChangeEvent;
import com.ejoy.tool.ui.douyin.bean.DouyinPauseVideoEvent;
import com.ejoy.tool.ui.douyin.data.adapter.DouyinCommPagerAdapter;
import com.ejoy.tool.ui.douyin.fragment.DouyinMainFragment;
import com.ejoy.tool.ui.douyin.fragment.DouyinPersonalHomeFragment;
import com.ejoy.tool.ui.douyin.utils.RxBus;
import com.kongzue.baseframework.interfaces.DarkNavigationBarTheme;
import com.kongzue.baseframework.interfaces.DarkStatusBarTheme;
import com.kongzue.baseframework.interfaces.Layout;

import java.util.ArrayList;
import butterknife.BindView;
import rx.functions.Action1;


/**
 * @ClassName:  DouyinMainActivity
 * @Author: maiduoduo
 * @BLOG: https://blog.csdn.net/Maiduoudo
 * @Date: 2021/2/2
 * @des: 抖音主页面
 */
@Layout(R.layout.activity_douyin_main)
@DarkStatusBarTheme(true)
@DarkNavigationBarTheme(false)
public class DouyinMainActivity extends IBaseActivity {
    @BindView(R.id.viewpager)
    ViewPager viewPager;
    private DouyinCommPagerAdapter pagerAdapter;
    private ArrayList<Fragment> fragments = new ArrayList<>();
    public static int curMainPage;
    private DouyinMainFragment mainFragment = new DouyinMainFragment();
    private DouyinPersonalHomeFragment personalHomeFragment = new DouyinPersonalHomeFragment();
    /** 上次点击返回键时间 */
    private long lastTime;
    /** 连续按返回键退出时间 */
    private final int EXIT_TIME = 2000;

//    @Override
//    protected boolean isRegistSatusbarFullScreenTransluent() {
//        return true;
//    }


    @Override
    protected void setTranslucentStatus(boolean on) {
        super.setTranslucentStatus(on);
    }

    @Override
    protected Object registSatusbarBgcolor() {
        return "#000000";
    }

    @Override
    public void initViews() {
        fragments.add(mainFragment);
        fragments.add(personalHomeFragment);
        pagerAdapter = new DouyinCommPagerAdapter(getSupportFragmentManager(), fragments, new String[]{"",""});
        viewPager.setAdapter(pagerAdapter);

        //点击头像切换页面
        RxBus.getDefault().toObservable(DouyinMainPageChangeEvent.class)
                .subscribe((Action1<DouyinMainPageChangeEvent>) event -> {
                    if (viewPager != null) {
                        viewPager.setCurrentItem(event.getPage());
                    }
                });

    }

    @Override
    public void initDatas() {

    }

    @Override
    public void setEvents() {
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                curMainPage = position;

                if (position == 0) {
                    RxBus.getDefault().post(new DouyinPauseVideoEvent(true));
                } else if (position == 1){
                    RxBus.getDefault().post(new DouyinPauseVideoEvent(false));
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }



    @Override
    public void onBackPressed() {
        //双击返回退出App
        if (System.currentTimeMillis() - lastTime > EXIT_TIME) {
            if (viewPager.getCurrentItem() == 1) {
                viewPager.setCurrentItem(0);
            }else{
                Toast.makeText(getApplicationContext(), "再按一次退出", Toast.LENGTH_SHORT).show();
                lastTime = System.currentTimeMillis();
            }
        } else {
            super.onBackPressed();
        }
    }

}
