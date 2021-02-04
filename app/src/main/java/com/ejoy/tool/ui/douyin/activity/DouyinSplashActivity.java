package com.ejoy.tool.ui.douyin.activity;

import android.content.Intent;
import android.os.CountDownTimer;

import com.ejoy.tool.R;
import com.ejoy.tool.ui.activity.more_detail.IpaletteImageSampleActivity;
import com.ejoy.tool.ui.base.base_activity.IBaseActivity;
import com.kongzue.baseframework.interfaces.DarkNavigationBarTheme;
import com.kongzue.baseframework.interfaces.DarkStatusBarTheme;
import com.kongzue.baseframework.interfaces.Layout;


/**
 * @ClassName:  DouyinSplashActivity
 * @Author: maiduoduo
 * @BLOG: https://blog.csdn.net/Maiduoudo
 * @Date: 2021/2/2
 * @des: App启动页面
 */
@Layout(R.layout.activity_douyin_splash)
@DarkStatusBarTheme(true)
@DarkNavigationBarTheme(false)
public class DouyinSplashActivity extends IBaseActivity {

    @Override
    protected boolean isRegistSatusbarFullScreenTransluent() {
        return true;
    }

    @Override
    protected boolean isRegistFullScreen() {
        return true;
    }

    @Override
    public void initViews() {
    }

    @Override
    public void initDatas() {
        new CountDownTimer(2000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {

            }

            @Override
            public void onFinish() {
                startActivity(new Intent(me, DouyinMainActivity.class));
                finish();
            }
        }.start();
    }

    @Override
    public void setEvents() {

    }




}
