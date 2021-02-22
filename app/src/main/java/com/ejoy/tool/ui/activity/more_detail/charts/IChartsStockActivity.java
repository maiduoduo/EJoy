package com.ejoy.tool.ui.activity.more_detail.charts;

import android.graphics.Color;
import android.view.View;

import com.ejoy.tool.R;
import com.ejoy.tool.ui.base.base_activity.IBaseActivity;
import com.kongzue.baseframework.interfaces.DarkStatusBarTheme;
import com.kongzue.baseframework.interfaces.Layout;
import com.kongzue.dialog.util.BlurView;

import butterknife.BindView;
import butterknife.OnClick;


/**
 * @ClassName: IChartsStockActivity
 * @Author: maiduoduo
 * @BLOG: https://blog.csdn.net/Maiduoudo
 * @Date: 2021/2/21
 * @des: 股票类图表
 */
@Layout(R.layout.activity_charts_stock)
@DarkStatusBarTheme(true)
public class IChartsStockActivity extends IBaseActivity {

    @BindView(R.id.blur)
    BlurView blur;


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
            , R.id.tvSharesDoubleList
            , R.id.tvCandleCharts
    })
    public void eventClick(View view) {
        switch (view.getId()) {
            case R.id.btn_back:
                finish();
                break;
            case R.id.tvSharesDoubleList:
                jump(IChartsStockDoubleListActivity.class);
                break;
            case R.id.tvCandleCharts:
                jump(IChartsStockCandleActivity.class);
                break;

        }
    }


}
