package com.ejoy.tool.ui.activity.more_detail.charts;

import android.graphics.Color;
import android.view.View;

import com.ejoy.tool.R;
import com.ejoy.tool.ui.base.base_activity.IBaseActivity;
import com.kongzue.baseframework.interfaces.DarkStatusBarTheme;
import com.kongzue.baseframework.interfaces.Layout;
import com.kongzue.dialog.util.BlurView;
import com.module.iviews.charts.ITempControlDialView;

import butterknife.BindView;
import butterknife.OnClick;


/**
 * @ClassName: IChartsDialTempControlActivity
 * @Author: maiduoduo
 * @BLOG: https://blog.csdn.net/Maiduoudo
 * @Date: 2020/12/15
 * @des: 温度控制表盘图
 */
@Layout(R.layout.activity_charts_dial_temp_control)
@DarkStatusBarTheme(true)
public class IChartsDialTempControlActivity extends IBaseActivity {

    @BindView(R.id.blur)
    BlurView blur;
    @BindView(R.id.temp_control)
    ITempControlDialView tempControl;



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
        blur.setOverlayColor(Color.argb(200, 235, 235, 235));
        blur.setRadius(me, 0, 0);
        setDarkNavigationBarTheme(false);

        // 设置三格代表温度1度
        tempControl.setAngleRate(1);
        tempControl.setTemp(16, 37, 20);
        //设置旋钮是否可旋转
        tempControl.setCanRotate(true);



    }


    @Override
    public void initDatas() {
    }

    @Override
    public void setEvents() {
        tempControl.setOnTempChangeListener(new ITempControlDialView.OnTempChangeListener() {
            @Override
            public void change(int temp) {
                iToast.showISimpleToast( temp + "°");
            }
        });

        tempControl.setOnClickListener(new ITempControlDialView.OnClickListener() {
            @Override
            public void onClick(int temp) {
                iToast.showISimpleToast( temp + "°");
            }
        });
    }

    @OnClick({
            R.id.btn_back
    })
    public void eventClick(View view) {
        switch (view.getId()) {
            case R.id.btn_back:
                finish();
                break;

        }
    }


}
