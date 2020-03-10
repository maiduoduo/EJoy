package com.ejoy.tool.ui.activity.textview;
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
import android.os.Bundle;
import android.view.View;

import com.ejoy.tool.R;
import com.ejoy.tool.ui.base.base_activity.IBaseActivity;
import com.kongzue.baseframework.BaseActivity;
import com.kongzue.baseframework.interfaces.DarkStatusBarTheme;
import com.kongzue.baseframework.interfaces.Layout;
import com.kongzue.dialog.util.BlurView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * CN:      ITextViewActivity
 * Author： JSYL-DINGCL (dingcl@jsyl.com.cn)
 * Date:   2020/1/16
 * Des:    TODO:TextView有关组件
 */
@Layout(R.layout.activity_itextview)
@DarkStatusBarTheme(true)
public class ITextViewActivity extends IBaseActivity {


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
//        setDarkStatusBarTheme(false);
//        setNavigationBarBackgroundColor(EResUtils.getColor(me,R.color.red));
//        StatusBarTool.setStatusBarColor(me,EResUtils.getColor(me,R.color.green));
//        blur.setOverlayColor(Color.argb(200, 235, 235, 235));
//        blur.setOverlayColor(Color.argb(200, 20, 20, 20));

//        blur.setOverlayColor(Color.argb(200, 235, 235, 235));
//        blur.setOverlayColor(R.color.LGray3);
//        blur.setRadius(me, 0, 0);
    }

    @Override
    public void initDatas() {
        blur.setOverlayColor(Color.argb(200, 235, 235, 235));
        blur.setRadius(me, 0, 0);
    }

    @Override
    public void setEvents() {

    }

    @OnClick({
            R.id.btn_back
    })
    public void bindClick(View view){
        switch (view.getId()){
            case R.id.btn_back:
                finish();
                break;
        }
    }

}
