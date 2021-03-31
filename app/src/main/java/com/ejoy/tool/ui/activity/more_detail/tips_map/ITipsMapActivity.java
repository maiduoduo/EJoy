package com.ejoy.tool.ui.activity.more_detail.tips_map;

import android.graphics.Color;
import android.view.View;
import android.widget.TextView;

import com.ejoy.tool.R;
import com.ejoy.tool.ui.base.base_activity.IBaseActivity;
import com.kongzue.baseframework.interfaces.DarkStatusBarTheme;
import com.kongzue.baseframework.interfaces.Layout;
import com.kongzue.dialog.util.BlurView;
import com.module.ires.bean.utils.EFileUtils;

import java.text.SimpleDateFormat;
import java.util.Date;

import butterknife.BindView;
import butterknife.OnClick;


/**
 * @ClassName: ITipsMapActivity
 * @Author: maiduoduo
 * @BLOG: https://blog.csdn.net/Maiduoudo
 * @Date: 2021/03/31
 * @des: 提示类控件自定义扩展集合
 */
@Layout(R.layout.activity_tips_map)
@DarkStatusBarTheme(true)
public class ITipsMapActivity extends IBaseActivity {

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
            ,R.id.llToastTipExtend
            ,R.id.llSnackbarTipExtend
    })
    public void eventClick(View view) {
        switch (view.getId()) {
            case R.id.btn_back:
                finish();
                break;
            case R.id.llToastTipExtend:
                //Toast扩展
                jump(IToastTipsActivity.class);
                break;

            case R.id.llSnackbarTipExtend:
                //snackbar扩展
                jump(IToastTipsActivity.class);
                break;
            default:
                break;

        }
    }


}


