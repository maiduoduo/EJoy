package com.ejoy.tool.ui.activity.more_detail.keyboard;
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

import android.graphics.Color;
import android.support.v7.widget.AppCompatEditText;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.ejoy.tool.R;
import com.ejoy.tool.ui.base.base_activity.IBaseActivity;
import com.kongzue.baseframework.interfaces.DarkStatusBarTheme;
import com.kongzue.baseframework.interfaces.Layout;
import com.kongzue.dialog.util.BlurView;
import com.module.iviews.keyboard.safe_keyboard.SafeKeyboard;

import butterknife.BindView;
import butterknife.OnClick;


/**
 * @ClassName: ISafeKeyboardActivity
 * @Author: maiduoduo
 * @BLOG: https://blog.csdn.net/Maiduoudo
 * @Date: 2020/12/23
 * @des: 自定义安全键盘
 */
@Layout(R.layout.activity_safe_keyboard)
@DarkStatusBarTheme(true)
public class ISafeKeyboardActivity extends IBaseActivity {

    @BindView(R.id.blur)
    BlurView blur;

    @BindView(R.id.safeEditText)
    EditText safeEdit;
    @BindView(R.id.safeEditText2)
    EditText safeEdit2;
    @BindView(R.id.safeEditText3)
    EditText safeEdit3;
    @BindView(R.id.safeAppCompactEditText)
    AppCompatEditText appCompatEditText;
    @BindView(R.id.safeEditText4)
    EditText safeEdit4;
    @BindView(R.id.safeEditText5)
    EditText safeEdit5;

    @BindView(R.id.flSafeKBRoot)
    View rootView;
    @BindView(R.id.scroll_layout)
    View scrollLayout;
    @BindView(R.id.keyboardPlace)
    LinearLayout keyboardContainer;




    private SafeKeyboard safeKeyboard;


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


        safeKeyboard = new SafeKeyboard(getApplicationContext(), keyboardContainer,
                R.layout.layout_safe_keyboard_containor, R.id.safeKeyboardLetter, rootView, scrollLayout);
        safeKeyboard.putEditText(safeEdit);
        safeKeyboard.putEditText(safeEdit2);
        safeKeyboard.putEditText(safeEdit3);
        safeKeyboard.putEditText(safeEdit4);
        safeKeyboard.putEditText(safeEdit5);
        safeKeyboard.putEditText(appCompatEditText);
        safeKeyboard.putEditText2IdCardType(safeEdit3.getId(), safeEdit3);
//        safeKeyboard.setDelDrawable(this.getResources().getDrawable(R.drawable.icon_del));
//        safeKeyboard.setLowDrawable(this.getResources().getDrawable(R.drawable.icon_capital_default));
//        safeKeyboard.setUpDrawable(this.getResources().getDrawable(R.drawable.icon_capital_selected));


    }


    @Override
    public void initDatas() {
    }


    @Override
    public void setEvents() {
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


    // 当点击返回键时, 如果软键盘正在显示, 则隐藏软键盘并是此次返回无效
    @Override
    public void onBackPressed() {
        if (safeKeyboard.stillNeedOptManually(false)) {
            safeKeyboard.hideKeyboard();
            return;
        }
        super.onBackPressed();
    }

    @Override
    protected void onDestroy() {
        if (safeKeyboard != null) {
            safeKeyboard.release();
            safeKeyboard = null;
        }
        super.onDestroy();
    }

    public void onAlertDialogClick(View view) {
//        jump(DialogSafeKeyboardActivity.class);
    }

    public void onPopupWindowTestClick(View view) {
//        jump(DialogSafeKeyboardActivity.class);
    }

    public void onScrollEditTestClick(View view) {
//        jump(DialogSafeKeyboardActivity.class);
    }






}


