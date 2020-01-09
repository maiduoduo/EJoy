package com.ejoy.tool.ui.activity.popupwindow;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;

import com.ejoy.tool.R;
import com.ejoy.tool.ui.base.base_activity.BaseActivity;
import com.ejoy.tool.ui.mvp.base.BasePresenter;
import com.module.iviews.popup.bar.ECookieBar;
import com.module.iviews.popup.bar.ECookieBar1111;

import butterknife.OnClick;



/*
*
*
*   ,------.     ,--.
*   |  .---'     |  | ,---.,--. ,--.
*   |  `--, ,--. |  || .-. |\  '  /
*   |  `---.|  '-'  /' '-' ' \   '
*   `------' `-----'  `---'.-'  /
*                          `---'
*/

/**
 * CN:      ICookieBarActivity
 * Author： JSYL-DINGCL (dingcl@jsyl.com.cn)
 * Date:   2020/01/08
 * Des:   顶部和底部信息显示条
 */
public class ICookieBarActivity extends BaseActivity {

    @Override
    protected void initRestore(@Nullable Bundle savedInstanceState) {

    }

    @Override
    protected Object registSatusbarBgcolor() {
        return baseWhite;
    }

    @Override
    protected boolean isRegistSatusbarFontDark() {
        return true;
    }

    @Override
    protected int getContentViewId() {
        return R.layout.activity_icookiebar;
    }

    @Override
    protected void initView(View mRootView) {

    }

    @Override
    protected void initData() {

    }

    @Override
    protected void addListener() {

    }

    @Override
    public BasePresenter getPresenter() {
        return null;
    }

    @OnClick({
            R.id.btnCBTop,
            R.id.btnCBBottom,
            R.id.btnCBAnim,
            R.id.btnCBBottomAnimated,
            R.id.btnCBCustomView,
            R.id.cookiebarParent
    })
    public void bindViewClick(View view) {
        ECookieBar.dismiss();
        switch (view.getId()) {
            case R.id.btnCBTop:
                showCookiebarTop();
                break;
            case R.id.btnCBBottom:
                showCookiebarBottom();
                break;
            case R.id.btnCBAnim:
                showCookiebarAnim();
                break;
            case R.id.btnCBBottomAnimated:
                showCookiebarBottomAnim();
                break;
            case R.id.btnCBCustomView:
                showCookiebarCustomview();
                break;
            case R.id.cookiebarParent:
                ECookieBar.dismiss();
                break;
            default:
                break;
        }
    }


    /**
     * 顶部
     */
    private void showCookiebarTop() {
        ECookieBar.builder(_mActivity)
                .setTitle(R.string.cookie_title)
                .setTitleColor(R.color.material_yellow_600)
                .setMessage(R.string.cookie_message)
                .setIcon(R.drawable.ic_app_launcher)
                .setDuration(3000)
                .setLayoutGravity(Gravity.TOP)
                .show();
    }

    /**
     * 底部
     */
    private void showCookiebarBottom() {
        ECookieBar.builder(_mActivity)
                .setTitle(R.string.cookie_title)
                .setIcon(R.mipmap.ic_app_launcher)
                .setMessage(R.string.cookie_message)
                .setLayoutGravity(Gravity.BOTTOM)
                .setAction(R.string.cookie_action, new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        iToast.showISimpleToast("点击消失");
                    }
                })
                .show();
    }

    /**
     * 动画
     */
    private void showCookiebarAnim() {

        ECookieBar.builder(_mActivity)
                .setTitle(R.string.cookie_title)
                .setMessage(R.string.cookie_message)
                .setIcon(R.drawable.ic_app_launcher)
                .setMessageColor(R.color.white)
                .setBackgroundColor(R.color.material_teal_500)
                .setAnimationIn(R.anim.cookiebar_slide_in_from_right, R.anim.cookiebar_slide_in_from_right)
                .setAnimationOut(R.anim.cookiebar_slide_out_to_left, R.anim.cookiebar_slide_out_to_left)
                .setDuration(3000)
                .show();
    }

    /**
     * 底部动画
     */
    private void showCookiebarBottomAnim() {
        ECookieBar.builder(_mActivity)
                .setTitle(R.string.cookie_title)
                .setIcon(R.mipmap.ic_app_launcher)
                .setMessage(R.string.cookie_message)
                .setLayoutGravity(Gravity.BOTTOM)
                .setMessageColor(R.color.white)
                .setBackgroundColor(R.color.material_blue_500)
                .setAnimationIn(R.anim.cookiebar_slide_in_from_left, R.anim.cookiebar_slide_in_from_left)
                .setAnimationOut(R.anim.cookiebar_slide_out_to_right, R.anim.cookiebar_slide_out_to_right)
                .setDuration(3000)
                .setAction(R.string.cookie_action, new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        iToast.showISimpleToast("点击消失");
                    }
                })
                .show();
    }

    /**
     * 自定义
     */
    private void showCookiebarCustomview() {
       /* ECookieBar.builder(_mActivity)
                .setCustomView(R.layout.layout_cookie_customview)
                .setCookiePosition(Gravity.BOTTOM)
                .setTitle(R.string.cookie_title)
                .setMessage(R.string.cookie_message)
                .setEnableAutoDismiss(false)
//                .setAnimationIn(R.anim.cookiebar_slide_in_from_bottom,R.anim.cookiebar_slide_in_from_bottom)
//                .setAnimationOut(R.anim.cookiebar_slide_in_from_top,R.anim.cookiebar_slide_in_from_top)
                .setCustomViewInitializer(new ECookieBar1111.CustomViewInitializer() {
                    @Override
                    public void initView(View view) {
                        Button btnNew = view.findViewById(R.id.custom_cookie_btn_new);
                        Button btnOpen = view.findViewById(R.id.custom_cookie_btn_open);
                        Button btnSave = view.findViewById(R.id.custom_cookie_btn_save);
                        View.OnClickListener btnListener = new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                Button button = (Button) view;
                                button.setText(R.string.custom_click_result);
                            }
                        };

                        btnNew.setOnClickListener(btnListener);
                        btnOpen.setOnClickListener(btnListener);
                        btnSave.setOnClickListener(btnListener);
                    }
                })
                .setAction("轻点关闭", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ECookieBar1111.dismiss();
                    }
                })
                .show();*/
    }

}