package com.ejoy.tool.ui.activity.more_detail.nomal_view;

import android.graphics.Color;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.ejoy.tool.R;
import com.ejoy.tool.ui.activity.more_detail.IPaletteListColorActivity;
import com.ejoy.tool.ui.activity.more_detail.IPaletteViewpagerColorActivity;
import com.ejoy.tool.ui.activity.more_detail.IpaletteImageSampleActivity;
import com.ejoy.tool.ui.base.base_activity.IBaseActivity;
import com.ejoy.tool.ui.data.resource.GlobalDataProvider;
import com.kongzue.baseframework.interfaces.DarkStatusBarTheme;
import com.kongzue.baseframework.interfaces.Layout;
import com.kongzue.dialog.util.BlurView;
import com.module.iviews.custombanner.IBannerAdapter;
import com.module.iviews.custombanner.IBannerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;


/**
 * @ClassName: INormalCustomViewActivity
 * @Author: maiduoduo
 * @BLOG: https://blog.csdn.net/Maiduoudo
 * @Date: 2020/12/18
 * @des: 常规自定义View集合
 */
@Layout(R.layout.activity_iui_normal_custom_view)
@DarkStatusBarTheme(true)
public class INormalCustomViewActivity extends IBaseActivity {

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
        blur.setOverlayColor(Color.argb(100, 235, 235, 235));
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
            , R.id.llDragFillBlankActivi
    })
    public void eventClick(View view) {
        switch (view.getId()) {
            case R.id.btn_back:
                finish();
                break;
            case R.id.llDragFillBlankActivi:
                jump(IDragFillBlankViewActivity.class);
                break;

        }
    }

}


