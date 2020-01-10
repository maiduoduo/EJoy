package com.ejoy.tool.ui.activity.popupwindow;
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

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.design.widget.CoordinatorLayout;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

import com.ejoy.tool.R;
import com.ejoy.tool.ui.base.base_activity.BaseActivity;
import com.ejoy.tool.ui.mvp.base.BasePresenter;
import com.module.iviews.popup.baseuse.IPopupwindowUse;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.module.iviews.popup.baseuse.IPopupwindowUse.LocationType.FROM_BOTTOM;

/**
 * CN:      IPopupwindowTopEditActivity
 * Author： JSYL-DINGCL (dingcl@jsyl.com.cn)
 * Date:   2020/1/6
 * Des:    TODO:自定义依附在输入法之上的Bottom弹窗
 */
public class IPopupwindowTopEditActivity extends BaseActivity {


    @BindView(R.id.et_comment)
    TextView mEtComment;

    @Override
    protected void initRestore(@Nullable Bundle savedInstanceState) {

    }

    @Override
    protected Object registSatusbarBgcolor() {
        return defalutStatus7;
    }

    @Override
    protected int getContentViewId() {
        return R.layout.activity_popup_topedit;
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
            R.id.ivBack,
            R.id.et_comment
    })
    public void bindViewClick(View view) {
        switch (view.getId()) {
            case R.id.ivBack:
                finish();
                break;
            case R.id.et_comment:
                showInputEditPop();

                // 设置popupWindow的显示位置，此处是在手机屏幕底部且水平居中的位置
//                popupWindow.showAtLocation(SettingActivity.this.findViewById(R.id.setting), Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);
//                popupView.startAnimation(animation);
                break;

            default:
                break;

        }
    }

    /**
     * 弹出依附在输入法之上的Bottom弹窗
     */
    private void showInputEditPop() {
        View mContentView = LayoutInflater.from(_mActivity).inflate(R.layout.layout_edit_pop, null);
        IPopupwindowUse popWindow = new IPopupwindowUse.PopupWindowBuilder(this)
                .autoOpenSoftInput(_mActivity,true)
                .setView(mContentView)
                .setFocusable(true)
                .setOutsideTouchable(true)
                .enableBackgroundDark(true)
                .create();
        View parent = (View) mContentView.getParent();
        //辅助设置圆角
        if (parent != null) {
            parent.setBackgroundColor(_mActivity.getResources().getColor(android.R.color.transparent));
            CoordinatorLayout.LayoutParams layoutParams = (CoordinatorLayout.LayoutParams) (parent)
                    .getLayoutParams();
            parent.setLayoutParams(layoutParams);
        }
        popWindow.showPopupWindow(mEtComment, FROM_BOTTOM);
    }
}
