package com.ejoy.tool.ui.activity.more_detail.keyboard;

import android.graphics.Color;
import android.view.View;

import com.ejoy.tool.R;
import com.ejoy.tool.ui.activity.more_detail.nomal_view.IDragFillBlankViewActivity;
import com.ejoy.tool.ui.base.base_activity.IBaseActivity;
import com.kongzue.baseframework.interfaces.DarkStatusBarTheme;
import com.kongzue.baseframework.interfaces.Layout;
import com.kongzue.dialog.util.BlurView;

import butterknife.BindView;
import butterknife.OnClick;


/**
 * @ClassName: IKeyboardHomeActivity
 * @Author: maiduoduo
 * @BLOG: https://blog.csdn.net/Maiduoudo
 * @Date: 2020/12/25
 * @des: 常规自定义View集合
 */
@Layout(R.layout.activity_keyboard_home)
@DarkStatusBarTheme(true)
public class IKeyboardHomeActivity extends IBaseActivity {

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
            , R.id.llKeyBoard1
            , R.id.llSafeKeyboard
    })
    public void eventClick(View view) {
        switch (view.getId()) {
            case R.id.btn_back:
                finish();
                break;
            case R.id.llKeyBoard1:
                jump(IKeyboardMultiTypeActivity.class);
                break;
            case R.id.llSafeKeyboard:
                jump(ISafeKeyboardActivity.class);
                break;

        }
    }

}


