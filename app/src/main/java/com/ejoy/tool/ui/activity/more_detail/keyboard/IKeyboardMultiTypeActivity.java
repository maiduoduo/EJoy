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
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.ejoy.tool.R;
import com.ejoy.tool.ui.base.base_activity.IBaseActivity;
import com.kongzue.baseframework.interfaces.DarkStatusBarTheme;
import com.kongzue.baseframework.interfaces.Layout;
import com.kongzue.dialog.util.BlurView;
import com.module.iviews.keyboard.KingKeyboard;

import butterknife.BindView;
import butterknife.OnClick;


/**
 * @ClassName: IKeyboardMultiTypeActivity
 * @Author: maiduoduo
 * @BLOG: https://blog.csdn.net/Maiduoudo
 * @Date: 2020/12/23
 * @des: 自定义键盘
 * 内置了满足各种场景的键盘需求：包括但不限于混合、字母、数字、电话、身份证、车牌号等可输入场景
 */
@Layout(R.layout.activity_keyboard_multi_type)
@DarkStatusBarTheme(true)
public class IKeyboardMultiTypeActivity extends IBaseActivity {

    @BindView(R.id.blur)
    BlurView blur;
    @BindView(R.id.et_system_input)
    EditText et;
    @BindView(R.id.et1)
    EditText et1;
    @BindView(R.id.et2)
    EditText et2;
    @BindView(R.id.et3)
    EditText et3;
    @BindView(R.id.et4)
    EditText et4;
    @BindView(R.id.et5)
    EditText et5;
    @BindView(R.id.et6)
    EditText et6;
    @BindView(R.id.et7)
    EditText et7;
    @BindView(R.id.et8)
    EditText et8;
    @BindView(R.id.et9)
    EditText et9;
    @BindView(R.id.et10)
    EditText et10;
    @BindView(R.id.et11)
    EditText et11;
    @BindView(R.id.et12)
    EditText et12;
    @BindView(R.id.et13)
    EditText et13;
    @BindView(R.id.btn)
    Button btn;
    @BindView(R.id.rootview)
    LinearLayout rootview;
    @BindView(R.id.keyboardParent)
    LinearLayout keyboardParent;


    private KingKeyboard kingKeyboard;
    private boolean isVibrationEffectEnabled;


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

        //初始化KingKeyboard
        kingKeyboard = new KingKeyboard(this, keyboardParent);
        //然后将EditText注册到KingKeyboard即可
        kingKeyboard.register(et1, KingKeyboard.KeyboardType.NORMAL);
        kingKeyboard.register(et2, KingKeyboard.KeyboardType.LETTER);
        kingKeyboard.register(et3, KingKeyboard.KeyboardType.LOWERCASE_LETTER_ONLY);
        kingKeyboard.register(et4, KingKeyboard.KeyboardType.UPPERCASE_LETTER_ONLY);
        kingKeyboard.register(et5, KingKeyboard.KeyboardType.LETTER_NUMBER);
        kingKeyboard.register(et6, KingKeyboard.KeyboardType.NUMBER);
        kingKeyboard.register(et7, KingKeyboard.KeyboardType.NUMBER_DECIMAL);
        kingKeyboard.register(et8, KingKeyboard.KeyboardType.PHONE);
        kingKeyboard.register(et9, KingKeyboard.KeyboardType.ID_CARD);
        kingKeyboard.register(et10, KingKeyboard.KeyboardType.LICENSE_PLATE);
        kingKeyboard.register(et11, KingKeyboard.KeyboardType.LICENSE_PLATE_PROVINCE);

    }


    @Override
    public void initDatas() {
        /*
         * 如果目前所支持的键盘满足不了您的需求，您也可以自定义键盘，KingKeyboard对外提供自定义键盘类型。
         * 自定义步骤也非常简单，只需自定义键盘的xml布局，然后将EditText注册到对应的自定义键盘类型即可
         *
         * 1. 自定义键盘Custom，自定义方法setKeyboardCustom，键盘类型为{@link KeyboardType#CUSTOM}
         * 2. 自定义键盘CustomModeChange，自定义方法setKeyboardCustomModeChange，键盘类型为{@link KeyboardType#CUSTOM_MODE_CHANGE}
         * 3. 自定义键盘CustomMore，自定义方法setKeyboardCustomMore，键盘类型为{@link KeyboardType#CUSTOM_MORE}
         *
         * xmlLayoutResId 键盘布局的资源文件，其中包含键盘布局和键值码等相关信息
         */
        kingKeyboard.setKeyboardCustom(R.xml.keyboard_custom);
//        kingKeyboard.setKeyboardCustomModeChange(xmlLayoutResId)
//        kingKeyboard.setKeyboardCustomMore(xmlLayoutResId)
        kingKeyboard.register(et12,KingKeyboard.KeyboardType.CUSTOM);

        isVibrationEffectEnabled = kingKeyboard.isVibrationEffectEnabled();

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btn.setText(isVibrationEffectEnabled? "Enabled":"Disabled");
                isVibrationEffectEnabled = !isVibrationEffectEnabled;
                kingKeyboard.setVibrationEffectEnabled(isVibrationEffectEnabled);
            }
        });
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


    /**
     * 在Activity或Fragment的生命周期中调用对应的方法
     */
    @Override
    protected void onResume() {
        super.onResume();
        kingKeyboard.onResume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        kingKeyboard.onDestroy();
    }


}


