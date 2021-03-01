package com.ejoy.tool.ui.activity.more_detail.keyboard;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.ejoy.tool.R;
import com.ejoy.tool.ui.base.base_activity.IBaseActivity;
import com.kongzue.baseframework.interfaces.DarkStatusBarTheme;
import com.kongzue.baseframework.interfaces.Layout;
import com.kongzue.dialog.util.BlurView;
import com.module.iviews.keyboard.safe_keyboard.SafeKeyboard;

import butterknife.BindView;

/**
 * @ClassName: ISafeKeyboardScrollViewEditActivity
 * @Author: maiduoduo
 * @BLOG: https://blog.csdn.net/Maiduoudo
 * @Date: 2020/12/27
 * @des: 自定义安全键盘-ScrollViewEdit
 */
@Layout(R.layout.activity_safe_keyboard_scroll_edit)
@DarkStatusBarTheme(true)
public class ISafeKeyboardScrollViewEditActivity extends IBaseActivity {

    @BindView(R.id.blur)
    BlurView blur;

    @BindView(R.id.safeEdit)
    EditText safeEdit;
    @BindView(R.id.safeEdit2)
    EditText safeEdit2;
    @BindView(R.id.safeEdit8)
    EditText safeEdit8;
    @BindView(R.id.keyboardPlace)
    LinearLayout keyboardContainer;
    @BindView(R.id.scrollRoot)
    View rootView;
    @BindView(R.id.scrollViewScrollLayout)
    View scrollLayout;

    private SafeKeyboard safeKeyboard;


    @Override
    public void initViews() {
        blur.setOverlayColor(Color.argb(100, 235, 235, 235));
        blur.setRadius(me, 0, 0);
        setDarkNavigationBarTheme(false);

        safeKeyboard = new SafeKeyboard(getApplicationContext(), keyboardContainer,
                R.layout.layout_safe_keyboard_containor, R.id.safeKeyboardLetter, rootView, scrollLayout, false);
        safeKeyboard.putEditText(safeEdit);
        safeKeyboard.putEditText(safeEdit2);
        safeKeyboard.putEditText(safeEdit8);
    }

    @Override
    public void initDatas() {

    }

    @Override
    public void setEvents() {

    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
//        Log.i("ScrollLayoutSafeEdit8", safeEdit8.getLeft() + ", \t\t" + safeEdit8.getTop()
//                + ", \t\t" + safeEdit8.getRight() + ", \t\t" + safeEdit8.getBottom());
    }

    // 当点击返回键时, 如果软键盘正在显示, 则隐藏软键盘并是此次返回无效
    @Override
    public void onBackPressed() {
        if (safeKeyboard != null && safeKeyboard.stillNeedOptManually(false)) {
            safeKeyboard.hideKeyboard();
            return;
        }
        super.onBackPressed();
    }
}
