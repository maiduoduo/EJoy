package com.ejoy.tool.ui.activity.more_detail.charts;

import android.graphics.Color;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.ejoy.tool.R;
import com.ejoy.tool.ui.base.base_activity.IBaseActivity;
import com.kongzue.baseframework.interfaces.DarkStatusBarTheme;
import com.kongzue.baseframework.interfaces.Layout;
import com.kongzue.dialog.util.BlurView;
import com.module.iviews.charts.IThermometerView;

import java.util.Random;

import butterknife.BindView;
import butterknife.OnClick;


/**
 * @ClassName: IChartsActivity
 * @Author: maiduoduo
 * @BLOG: https://blog.csdn.net/Maiduoudo
 * @Date: 2021/2/20
 * @des: 温度计/体温计
 */
@Layout(R.layout.activity_charts_thermometer)
@DarkStatusBarTheme(true)
public class IChartsThermometerActivity extends IBaseActivity {

    @BindView(R.id.blur)
    BlurView blur;
    @BindView(R.id.tv_thermometer)
    IThermometerView thermometerTv;


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
    }


    @Override
    public void initDatas() {
    }

    @Override
    public void setEvents() {

    }

    @OnClick({
            R.id.btn_back
            , R.id.btn_anim
            , R.id.btn_operate
    })
    public void eventClick(View view) {
        switch (view.getId()) {
            case R.id.btn_back:
                finish();
                break;
            case R.id.btn_anim:
                thermometerTv.setValueAndStartAnim(getRandomValue());
                break;
            case R.id.btn_operate:
                thermometerTv.setCurValue(getRandomValue());
                break;

        }
    }


    private float getRandomValue(){
        float value = new Random().nextFloat() * 7 + 35;
        Log.e(_TAG, "current value: " + value);
        return value;
    }


}
