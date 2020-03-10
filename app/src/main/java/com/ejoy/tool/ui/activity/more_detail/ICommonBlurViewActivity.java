package com.ejoy.tool.ui.activity.more_detail;

import android.graphics.Color;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.widget.Toolbar;
import android.text.method.LinkMovementMethod;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.ejoy.tool.R;
import com.ejoy.tool.ui.base.base_activity.IBaseActivity;
import com.kongzue.baseframework.interfaces.DarkStatusBarTheme;
import com.kongzue.baseframework.interfaces.Layout;
import com.kongzue.dialog.util.BlurView;
import com.module.iviews.common.toolbar.RipAnimDrawable;
import com.module.iviews.popup.bar.ECookieBar;
import com.module.iviews.popup.bar.ECookieBarCustom;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

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

/**
 * CN:      ICommonBlurViewActivity
 * Author： JSYL-DINGCL (dingcl@jsyl.com.cn)
 * Date:   2020/3/6
 * Des:    TODO:Android的动态模糊图像视图.此库基于RenderScript。
 * TODO:在此处可以找到有关RenderScript的更多详细信息：RenderScript
 */
@Layout(R.layout.activity_icommon_blurview)
public class ICommonBlurViewActivity extends IBaseActivity {

    @BindView(R.id.blur)
    BlurView blur;
    @BindView(R.id.btn_intro)
    ImageView btnIntro;


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
            R.id.baseBlur,
            R.id.baseImmerseBlur,
            R.id.btn_back,
            R.id.btn_intro,
    })
    public void bindClick(View view) {
        switch (view.getId()) {
            case R.id.baseBlur:
                jump(ICommonBaseBlurViewActivity.class);
                break;
            case R.id.baseImmerseBlur:
                jump(IImerseBlurViewActivity.class);
                break;
            case R.id.btn_back:
                finish();
                break;
            case R.id.btn_intro:
                showCookiebarCustomview();
                break;
        }
    }


    /**
     * 自定义
     */
    private void showCookiebarCustomview() {
        ECookieBarCustom.builder(me)
                .setCustomView(R.layout.layout_cookie_customview_blur_intro)
                .setCookiePosition(Gravity.TOP)
                .setBackgroundColor(R.color.black_55_33)
//                .setTitle(R.string.cookie_title)
//                .setMessage(R.string.cookie_message)
                .setEnableAutoDismiss(false)
                .setAnimationIn(R.anim.cookiebar_slide_in_from_top,R.anim.cookiebar_slide_in_from_top)
                .setAnimationOut(R.anim.cookiebar_slide_in_from_top,R.anim.cookiebar_slide_in_from_top)
                .setCustomViewInitializer(new ECookieBarCustom.CustomViewInitializer() {
                    @Override
                    public void initView(View view) {
                        ImageView ivCancel = view.findViewById(R.id.ivCancel);
                        ConstraintLayout rootIntro = view.findViewById(R.id.rootIntro);
                        TextView showBlogLink = view.findViewById(R.id.showBlogLink);
                        View.OnClickListener btnListener = new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                switch (view.getId()){
                                    case R.id.ivCancel:
                                    case R.id.rootIntro:
                                        ECookieBarCustom.dismiss();
                                        break;
                                }
                            }
                        };
//
                        ivCancel.setOnClickListener(btnListener);
                        rootIntro.setOnClickListener(btnListener);
                        //这一句很重要，否则ClickableSpan内的onClick方法将无法触发！！
                        showBlogLink.setMovementMethod(LinkMovementMethod.getInstance());
                    }
                })
                .setAction("轻点关闭", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ECookieBarCustom.dismiss();
                    }
                })
                .show();
    }

}
