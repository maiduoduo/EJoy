package com.ejoy.tool.ui.activity.more_detail.charts;

import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.ejoy.tool.R;
import com.ejoy.tool.common.bean.ISharesContentBean;
import com.ejoy.tool.ui.base.base_activity.IBaseActivity;
import com.ejoy.tool.ui.data.adapter.ISharesContentAdapter;
import com.ejoy.tool.ui.data.adapter.ISharesTabAdapter;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.kongzue.baseframework.interfaces.DarkStatusBarTheme;
import com.kongzue.baseframework.interfaces.Layout;
import com.kongzue.dialog.util.BlurView;
import com.module.ires.bean.utils.EJsonUtils;
import com.module.iviews.charts.ICustomizeMarqueeView;
import com.module.iviews.charts.ICustomizeScrollView;
import com.module.iviews.charts.adapter.IMarquessViewAdapter;
import com.module.iviews.charts.bean.IMessageBean;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;


/**
 * @ClassName: IChartsSharesActivity
 * @Author: maiduoduo
 * @BLOG: https://blog.csdn.net/Maiduoudo
 * @Date: 2021/2/21
 * @des: 股票类图表
 */
@Layout(R.layout.activity_charts_shares)
@DarkStatusBarTheme(true)
public class IChartsSharesActivity extends IBaseActivity {

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
    })
    public void eventClick(View view) {
        switch (view.getId()) {
            case R.id.btn_back:
                finish();
                break;
            case R.id.tvSharesDoubleList:
                jump(IChartsSharesDoubleListActivity.class);
                break;

        }
    }


}
