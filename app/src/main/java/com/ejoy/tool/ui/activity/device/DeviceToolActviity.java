package com.ejoy.tool.ui.activity.device;
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

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ejoy.tool.R;
import com.ejoy.tool.app.App;
import com.ejoy.tool.common.service.RestartAppService;
import com.ejoy.tool.scaffold.utils.StatusBarTool;
import com.ejoy.tool.scaffold.utils.VibratorUtil;
import com.ejoy.tool.scaffold.view.widget.ExpandLayout;
import com.ejoy.tool.ui.base.base_activity.BaseActivity;
import com.ejoy.tool.ui.mvp.base.BasePresenter;
import com.module.ires.bean.utils.EDeviceUtils;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * CN:      DeviceToolActviity
 * Author： JSYL-DINGCL (dingcl@jsyl.com.cn)
 * Date:   2019/12/23
 * Des:    设备工具集
 */
public class DeviceToolActviity extends BaseActivity {
    @BindView(R.id.phonename)
    TextView phonename;
    @BindView(R.id.expandLayout)
    ExpandLayout mExpandLayout;
    @BindView(R.id.ivDeviceArraw)
    ImageView mIvDeviceArraw;
    @BindView(R.id.tvUniqueCode)
    TextView mTvUniqueCode;
    @BindView(R.id.rlRestartApp)
    RelativeLayout mRlRestartApp;

    private VibratorUtil instance;
    private Animation rotate;

    @Override
    protected void initRestore(@Nullable Bundle savedInstanceState) {
    }

    @Override
    protected int getContentViewId() {
        return R.layout.activity_devicetool;
    }

    @Override
    protected void initView(View mRootView) {
        StatusBarTool.setRootViewFitsSystemWindows(_mActivity, true);
        StatusBarTool.setStatusBarColor(this, Color.parseColor(defalutStatus1));
        instance = new VibratorUtil(this).getInstance();
        //初始化
        mExpandLayout.initExpand(false);
        //创建动画
        rotate = AnimationUtils.loadAnimation(_mActivity, R.anim.arrow_rotate);
        //设置为线性旋转
        rotate.setInterpolator(new LinearInterpolator());
        getUniqueCode();
    }

    /**
     * 获取唯一码
     */
    private void getUniqueCode() {
        String uniqueId = EDeviceUtils.getUniqueId(_mActivity);
        mTvUniqueCode.setText(uniqueId == null ? " " : uniqueId);

    }

    @Override
    protected void initData() {
        String s = EDeviceUtils.printSystemInfo(_mActivity);
        phonename.setText(s);

    }

    @Override
    protected void addListener() {

    }

    @Override
    public BasePresenter getPresenter() {
        return null;
    }


    @OnClick({
            R.id.rlVibrator
            , R.id.rlDeviceinfo
            , R.id.rlRestartApp
    })
    public void bindViewClick(View view) {
        switch (view.getId()) {
            case R.id.rlVibrator://手机震动
                /**
                 * 开始震动
                 */
//                instance.vibrate(new long[]{3000, 2000, 6000}, false);
                instance.vibrate(6000);
                break;
            case R.id.rlDeviceinfo:
                mExpandLayout.toggleExpand();
                rotate.setFillAfter(!rotate.getFillAfter());//每次都取相反值，使得可以不恢复原位的旋转
                mIvDeviceArraw.startAnimation(rotate);
                break;
            case R.id.rlRestartApp://重启App
                restartApp();
                break;
        }
    }

    /**
     * 重启App
     */
    private void restartApp() {
        //开启一个新的服务，用来重启本APP
        Intent intent = new Intent(_mActivity, RestartAppService.class);
        intent.putExtra("packageName", _mActivity.getPackageName());
        _mActivity.startService(intent);
        //关闭整个app
        App.getInstance().exit();
    }

    public void ivBack(View view) {
        finish();
    }

    @Override
    protected void onDestroy() {
        if (instance != null) instance.cancelVibrate();
        super.onDestroy();
    }
}
