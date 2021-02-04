package com.ejoy.tool.ui.douyin.activity;


import com.ejoy.tool.R;
import com.ejoy.tool.ui.base.base_activity.IBaseActivity;
import com.ejoy.tool.ui.douyin.fragment.DouyinRecommendFragment;
import com.kongzue.baseframework.interfaces.DarkNavigationBarTheme;
import com.kongzue.baseframework.interfaces.DarkStatusBarTheme;
import com.kongzue.baseframework.interfaces.Layout;

/**
 * @ClassName:  DouyinPlayListActivity
 * @Author: maiduoduo
 * @BLOG: https://blog.csdn.net/Maiduoudo
 * @Date: 2021/2/2
 * @des: 视频全屏播放页
 */
@Layout(R.layout.activity_douyin_play_list)
@DarkStatusBarTheme(true)
@DarkNavigationBarTheme(false)
public class DouyinPlayListActivity extends IBaseActivity {
    public static int initPos;

    @Override
    protected boolean isRegistSatusbarFullScreenTransluent() {
        return true;
    }

    @Override
    public void initViews() {
        getSupportFragmentManager().beginTransaction().add(R.id.framelayout, new DouyinRecommendFragment()).commit();
    }

    @Override
    public void initDatas() {

    }

    @Override
    public void setEvents() {

    }
}
