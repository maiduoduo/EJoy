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

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.Gravity;
import android.view.View;
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
                IPopupwindowUse popWindow = new IPopupwindowUse.PopupWindowBuilder(this)
                        .setView(R.layout.layout_pop_bottom)
                        .setFocusable(true)
                        .setOutsideTouchable(true)
                        .create();
                popWindow.showPopupWindow(mEtComment, FROM_BOTTOM);

                // 设置popupWindow的显示位置，此处是在手机屏幕底部且水平居中的位置
//                popupWindow.showAtLocation(SettingActivity.this.findViewById(R.id.setting), Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);
//                popupView.startAnimation(animation);
                break;

            default:
                break;

        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
