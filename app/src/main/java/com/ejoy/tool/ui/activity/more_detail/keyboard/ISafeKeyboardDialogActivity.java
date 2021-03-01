package com.ejoy.tool.ui.activity.more_detail.keyboard;

import android.graphics.Color;
import android.view.View;
import android.widget.CheckBox;

import com.ejoy.tool.R;
import com.ejoy.tool.ui.base.base_activity.IBaseActivity;
import com.kongzue.baseframework.interfaces.DarkStatusBarTheme;
import com.kongzue.baseframework.interfaces.Layout;
import com.kongzue.dialog.util.BlurView;

import butterknife.BindView;

/**
 * @ClassName:  ISafeKeyboardDialogActivity
 * @Author: maiduoduo
 * @BLOG: https://blog.csdn.net/Maiduoudo
 * @Date: 2021/3/1
 * @des: 安全键盘-对话框演示
 */

@Layout(R.layout.activity_safe_keyboard_dialog)
@DarkStatusBarTheme(true)
public class ISafeKeyboardDialogActivity extends IBaseActivity implements ISafeKeyboardDialogFragment.onDialogResult {

    @BindView(R.id.blur)
    BlurView blur;
    @BindView(R.id.use_safe_key)
    CheckBox useSafe;



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

    public void onAlertDialogClick(View view) {
        ISafeKeyboardDialogFragment fragment = ISafeKeyboardDialogFragment.newInstance(useSafe.isChecked());
        fragment.setOnDialogResult(this);
        fragment.show(getSupportFragmentManager(), "SafeKeyboardDialogFragment");
    }

    @Override
    public void onDialogResults(Object t) {
        // TODO... 获得处理结果, 在这里添加你的业务代码
    }
}
