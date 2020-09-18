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

import android.view.View;

import com.ejoy.tool.R;
import com.ejoy.tool.ui.base.base_activity.IBaseActivity;
import com.kongzue.baseframework.interfaces.DarkStatusBarTheme;
import com.kongzue.baseframework.interfaces.Layout;

import butterknife.OnClick;


/**
 * @ClassName:  IPercentageBehaviorActivity
 * @Author: maiduoduo
 * @BLOG: https://blog.csdn.net/Maiduoudo
 * @Date: 2020/9/17
 * @des: 自定义百分比Behavior实现标题栏渐变伸缩位移效果
 */
@Layout(R.layout.activity_iui_pecentage_behavior)
@DarkStatusBarTheme(true)
//@DarkNavigationBarTheme(true)
public class IPercentageBehaviorActivity extends IBaseActivity {

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
    }


    @Override
    public void setEvents() {


    }


    @OnClick({
    })
    public void eventClick(View view) {
        switch (view.getId()) {

        }
    }





}
