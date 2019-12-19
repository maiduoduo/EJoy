package com.ejoy.tool.ui.base.base_fragment;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;

/*
*
*    ,------.     ,--.
*    |  .---'     |  | ,---.,--. ,--.
*    |  `--, ,--. |  || .-. |\  '  /
*    |  `---.|  '-'  /' '-' ' \   '
*    `------' `-----'  `---'.-'  /
*                           `---'
*/


/**
 * CN:      BaseActivity
 * Author： JSYL-DINGCL (dingcl@jsyl.com.cn)
 * Date:   2019/11/15
 * Des:    Fragment基类
 */
public abstract class BaseFragment extends Fragment {
    protected View rootView;
    public static String TAG = "";
    protected Activity mActivity;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mActivity = (Activity) context;
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        intBase();
        if (rootView == null)
            rootView = inflater.inflate(getLayoutResource(), container, false);
        ButterKnife.bind(this, rootView);
        TAG = getActivity().getClass().getSimpleName();
        initPresenter();
        initView(rootView);
        initData();
        initListener();
        return rootView;
    }

    //获取布局文件
    protected abstract int getLayoutResource();

    //简单页面无需mvp就不用管此方法即可,完美兼容各种实际场景的变通
    public abstract void intBase();

    public abstract void initPresenter();

    //初始化view
    protected abstract void initView(View rootView);

    protected abstract void initData();

    protected abstract void initListener();



    /**
     * 通过Class跳转界面
     **/
    public void startActivity(Class<?> cls) {
        startActivity(cls, null);
    }

    /**
     * 通过Class跳转界面
     **/
    public void startActivityForResult(Class<?> cls, int requestCode) {
        startActivityForResult(cls, null, requestCode);
    }

    /**
     * 含有Bundle通过Class跳转界面
     **/
    public void startActivityForResult(Class<?> cls, Bundle bundle,
                                       int requestCode) {
        Intent intent = new Intent();
        intent.setClass(getActivity(), cls);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        startActivityForResult(intent, requestCode);
    }

    /**
     * 含有Bundle通过Class跳转界面
     **/
    public void startActivity(Class<?> cls, Bundle bundle) {
        Intent intent = new Intent();
        intent.setClass(getActivity(), cls);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        startActivity(intent);
    }



    /**
     * 开启加载进度条
     *
     * @param msg
     */
    public void startProgressDialog(String msg) {
    }

    /**
     * 停止加载进度条
     */
    public void stopProgressDialog() {
    }


    /**
     * 短暂显示Toast提示(来自String)
     **/
    public void showShortToast(String text) {
    }

    /**
     * 短暂显示Toast提示(id)
     **/
    public void showShortToast(int resId) {
    }

    /**
     * 长时间显示Toast提示(来自res)
     **/
    public void showLongToast(int resId) {
    }

    /**
     * 长时间显示Toast提示(来自String)
     **/
    public void showLongToast(String text) {
    }


    public void showToastWithImg(String text, int res) {
    }

    /**
     * 网络访问错误提醒
     */
    public void showNetErrorTip() {
    }

    public void showNetErrorTip(String error) {
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }


}
