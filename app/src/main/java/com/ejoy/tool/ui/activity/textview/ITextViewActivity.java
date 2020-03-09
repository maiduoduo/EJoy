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

import com.ejoy.tool.R;
import com.kongzue.baseframework.BaseActivity;
import com.kongzue.baseframework.interfaces.DarkStatusBarTheme;
import com.kongzue.baseframework.interfaces.Layout;
import com.kongzue.dialog.util.BlurView;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * CN:      ITextViewActivity
 * Author： JSYL-DINGCL (dingcl@jsyl.com.cn)
 * Date:   2020/1/16
 * Des:    TODO:TextView有关组件
 */
@Layout(R.layout.activity_itextview)
@DarkStatusBarTheme(true)
public class ITextViewActivity extends BaseActivity {


    @BindView(R.id.blur)
    BlurView blur;

    @Override
    public void initViews() {
        ButterKnife.bind(this);
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

}
