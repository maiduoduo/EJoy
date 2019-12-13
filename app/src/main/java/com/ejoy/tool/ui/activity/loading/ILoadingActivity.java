package com.ejoy.tool.ui.activity.loading;

import android.graphics.Color;
import android.text.TextUtils;
import android.view.View;
import android.widget.NumberPicker;

import com.ejoy.tool.R;
import com.ejoy.tool.common.helper.InfoDialog.IDialog;
import com.ejoy.tool.scaffold.utils.StatusBarTool;
import com.ejoy.tool.scaffold.view.loading.LoadingDialog;
import com.ejoy.tool.ui.base.base_activity.BaseActivity;
import com.ejoy.tool.ui.mvp.base.BasePresenter;
import com.maple.msdialog.ActionSheetDialog;
import com.maple.msdialog.AlertDialog;
import com.maple.msdialog.AlertEditDialog;
import com.maple.msdialog.AlertNumberPickerDialog;

/**
 * CN:      ILoadingActivity
 * Author： JSYL-DINGCL (dingcl@jsyl.com.cn)
 * Date:   2019/12/13
 * Des:    加载框
 */
public class ILoadingActivity extends BaseActivity {
    private LoadingDialog mLoadingDialog;

    @Override
    protected int getContentViewId() {
        return R.layout.activity_loading;
    }

    @Override
    protected void initView(View mRootView) {
        StatusBarTool.setRootViewFitsSystemWindows(_mActivity,true);
        StatusBarTool.setStatusBarColor(_mActivity,Color.parseColor("#EE85C1"));
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





    public void iosBack(View view) {
        finish();
    }



    public void loadingStart(View view) {
        if (mLoadingDialog == null) {
            mLoadingDialog = new LoadingDialog(this);
        }
        mLoadingDialog.show();
    }

}
