package com.ejoy.tool.ui.activity.iosdialog;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.NumberPicker;
import android.widget.Toast;

import com.ejoy.tool.R;
import com.ejoy.tool.common.helper.InfoDialog.IDialog;
import com.ejoy.tool.scaffold.view.loading.LoadingDialog;
import com.ejoy.tool.ui.activity.MainActivity;
import com.ejoy.tool.ui.base.base_activity.BaseActivity;
import com.ejoy.tool.ui.mvp.base.BasePresenter;
import com.maple.msdialog.ActionSheetDialog;
import com.maple.msdialog.AlertDialog;
import com.maple.msdialog.AlertEditDialog;
import com.maple.msdialog.AlertNumberPickerDialog;
import com.module.iviews.view.widget.IPublishDialog;

import butterknife.OnClick;

/**
 * CN:      IIosDialogActivity
 * Author： JSYL-DINGCL (dingcl@jsyl.com.cn)
 * Date:   2019/12/2
 * Des:    Custom Dialog
 */
public class IDialogActivity extends BaseActivity {

    private IPublishDialog mPublishDialog;

    @Override
    protected void initRestore(@Nullable Bundle savedInstanceState) {

    }

    @Override
    protected boolean isRegistSatusbarFullScreenTransluent() {
        return true;
    }

    @Override
    protected int getContentViewId() {
        return R.layout.activity_idialog_packall;
    }


    @Override
    protected void initView(View mRootView) { 
        if (mPublishDialog==null) {
            mPublishDialog = new IPublishDialog(_mActivity);
        }
        
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void addListener() {
        mPublishDialog.setFabuClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iToast.showISimpleToast("发布");
                mPublishDialog.outDia();
            }
        });
        mPublishDialog.setHuishouClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iToast.showISimpleToast("回收");
                mPublishDialog.outDia();

            }
        });
        mPublishDialog.setPingguClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iToast.showISimpleToast("评估");
                mPublishDialog.outDia();

            }
        });
    }

    @OnClick({
            R.id.EIosDialog,
            R.id.EDialogMenu,
            R.id.iBack
    })
    public void bindViewclick(View view) {
        switch (view.getId()) {
            case R.id.EIosDialog:
                showActivity(_mActivity, IIosDialogActivity.class, R.anim.anim_fade_in, R.anim.anim_fade_out);
                break;
            case R.id.EDialogMenu:
                mPublishDialog.show();
                break;
            case R.id.iBack:
                finish();
                break;
        }
    }

    @Override
    public BasePresenter getPresenter() {
        return null;
    }
}
