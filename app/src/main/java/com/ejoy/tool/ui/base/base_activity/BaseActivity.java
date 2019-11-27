package com.ejoy.tool.ui.base.base_activity;
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

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.ejoy.tool.R;
import com.ejoy.tool.scaffold.utils.EventBusUtil;
//import com.ejoy.tool.scaffold.utils.GlideUtil;
import com.ejoy.tool.scaffold.utils.IToast;
import com.ejoy.tool.scaffold.utils.IToastImageType;
import com.ejoy.tool.scaffold.utils.StatusBarTool;
import com.ejoy.tool.scaffold.view.IProgressDialog;
import com.ejoy.tool.ui.activity.MainActivity;
import com.ejoy.tool.ui.base.base_view.BaseView;
import com.ejoy.tool.ui.mvp.base.BasePresenter;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * CN:      BaseActivity
 * Author： JSYL-DINGCL (dingcl@jsyl.com.cn)
 * Date:   2019/11/14
 * Des:    Activity父类
 */
public abstract class BaseActivity extends AppCompatActivity implements BaseView {

    protected final String TAG = this.getClass().getSimpleName();
    public  Activity _mActivity;
    public IToast iToast;


    protected abstract int getContentViewId();

    protected abstract void initView(View mRootView);

    /**
     * 获取Presenter实例，子类实现
     */
    public abstract BasePresenter getPresenter();

    public View getView(int layoutId) {
        return getLayoutInflater().inflate(layoutId, null);
    }


    /**
     * 是否注册事件分发
     *
     * @return true绑定EventBus事件分发，默认不绑定，子类需要绑定的话复写此方法返回true.
     */
    protected boolean isRegisterEventBus() {
        return false;
    }
    protected boolean isSetStatusBarBg() {
        return true;
    }





    private Unbinder unbinder;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initStatusbar();
        View mRootView = getView(getContentViewId());
        _mActivity = this;
        setContentView(mRootView);
        unbinder = ButterKnife.bind(this);
        //初始化自定义Toast
        iToast = new IToast().builder();
        if (getPresenter() != null) {
            getPresenter().attachView(this);
        }
        if (isRegisterEventBus()) {
            EventBusUtil.register(this);
        }
        initView(mRootView);

    }

    protected void initStatusbar() {
        //沉浸式代码配置
        //当FitsSystemWindows设置 true 时，会在屏幕最上方预留出状态栏高度的 padding
        StatusBarTool.setRootViewFitsSystemWindows(this, true);
        //设置状态栏透明
        StatusBarTool.setTranslucentStatus(this);
        //一般的手机的状态栏文字和图标都是白色的, 可如果你的应用也是纯白色的, 或导致状态栏文字看不清
        //所以如果你是这种情况,请使用以下代码, 设置状态使用深色文字图标风格, 否则你可以选择性注释掉这个if内容
        if (!StatusBarTool.setStatusBarDarkTheme(this, true)) {
            //如果不支持设置深色风格 为了兼容总不能让状态栏白白的看不清, 于是设置一个状态栏颜色为半透明,
            //这样半透明+白=灰, 状态栏的文字能看得清
//            StatusBarTool.setStatusBarColor(this, 0x55000000);
            StatusBarTool.setStatusBarColor(this, 0x00000000);
        }


        //用来设置整体下移，状态栏沉浸
        StatusBarTool.setRootViewFitsSystemWindows(this, false);
//        StatusBarUtil.setTranslucent(MainActivity.this, 0);
        StatusBarTool.setTranslucentStatus(this);//透明状态栏
        //黑色字体
//        StatusBarTool.setStatusBarDarkTheme(this, false);
        //黑色字体
//        StatusBarTool.setStatusBarDarkTheme(MainActivity.this, true);
        //设置白色字体，其他背景
//        StatusBarTool.setStatusBarColor(this, Color.parseColor("#58C087"));//设置背景颜色

//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) { //系统版本大于19
//            setTranslucentStatus(true);
//        }
//        if (isSetStatusBarBg()) {
//            SystemBarTintManager tintManager = new SystemBarTintManager(this);
//            tintManager.setStatusBarTintEnabled(true);
//            tintManager.setStatusBarTintResource(R.drawable.shape_statusbar);
//
//            //去除灰色遮罩
//            //Android5.0以上
//            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//                Window window = getWindow();
//                window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
//                window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
//                        | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
//                window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
//                window.setStatusBarColor(Color.TRANSPARENT);
//            }else if(Build.VERSION.SDK_INT>= Build.VERSION_CODES.KITKAT){//Android4.4以上,5.0以下
//                getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
//            }
//        }
    }


    @TargetApi(19)
    public void setTranslucentStatus(boolean on) {
        Window win = getWindow();
        WindowManager.LayoutParams winParams = win.getAttributes();
        final int bits = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
        if (on) {
            winParams.flags |= bits;        // a|=b的意思就是把a和b按位或然后赋值给a   按位或的意思就是先把a和b都换成2进制，然后用或操作，相当于a=a|b
        } else {
            winParams.flags &= ~bits;        //&是位运算里面，与运算  a&=b相当于 a = a&b  ~非运算符
        }
        win.setAttributes(winParams);
    }


    @Override
    public void showLoadingDialog(String msg) {
        IProgressDialog.showSuccinctProgress(_mActivity, "加载中，请稍后...", true);
    }


    @Override
    public void hideLoadingDialog() {
        if (IProgressDialog.isShowing()) {
            IProgressDialog.dismiss();
        }
    }





    @Override
    public void showToast(String msg) {
        Toast.makeText(this,msg+"",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showSuccessTips(String msg) {
        iToast.showIImgToast(msg+"", IToastImageType.SUCCESS);
    }

    @Override
    public void showFailTips(String msg) {
        iToast.showIImgToast(msg+"", IToastImageType.FAIL);
    }


    public Drawable getDrawablee(int id) {
        return ContextCompat.getDrawable(this, id);
    }

    public String getStringg(int id) {
        return getResources().getString(id);
    }

    public int getColorr(int id) {
        return ContextCompat.getColor(this, id);
    }

    public String getEditTextString(EditText editText) {
        String str = editText.getText().toString();
        return str;
    }

    public String getTextViewString(TextView textView) {
        String str = textView.getText().toString().trim();
        return str;
    }

    /**
     * 隐藏View
     * @param views
     */
    public void goneView(View... views) {
        for (View view : views) {
            if (null != view && view.getVisibility() != View.GONE)
                view.setVisibility(View.GONE);
        }
    }

    /**
     * 展示View
     * @param views
     */
    public void showView(View... views) {
        for (View view : views) {
            if (view.getVisibility() != View.VISIBLE)
                view.setVisibility(View.VISIBLE);
        }
    }

    /**
     * 开启Activity并关闭当前
     * @param c
     * @param finish
     */
    public void startActivityFinishCurrent(Class c,boolean finish) {
        startActivity(new Intent(this, c));
        if(finish) finish();
    }

    /**
     * 开启Activity
     * @param c
     */
    public void startActivity(Class c) {
        startActivityFinishCurrent(c,false);
    }

    public boolean isNotNullAndEmpty(String content, String tips) {
        if (null != content && !content.isEmpty())
            return true;
        else {
            if (null != tips)
                showFailTips(tips + "不能为空");
            return false;
        }
    }

    protected void loadImage(String url, ImageView imageView) {
//        GlideUtil.load(this, url, imageView);
    }

    public void showLog(String str) {
        Log.e(TAG, str);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (getPresenter() != null) getPresenter().detachView();
        if (isRegisterEventBus()) EventBusUtil.unregister(this);
        try { unbinder.unbind(); } catch (Exception e) { }
    }
}
