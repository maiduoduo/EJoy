package com.ejoy.tool.ui.mvp.base;

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


import com.ejoy.tool.ui.base.base_view.BaseView;

/**
 * CN:      BasePresenter
 * Author： JSYL-DINGCL (dingcl@jsyl.com.cn)
 * Date:   2019/11/14
 * Des:    Presenter父类
 */
public class BasePresenter<V extends BaseView> {

    protected V mView;

    /**
     * 绑定view，一般在初始化中调用该方法
     */
    public void attachView(V baseView) {
        this.mView = baseView;
    }
    /**
     * 解绑View，一般在onDestroy中调用
     */
    public void detachView() {
        this.mView = null;
    }
    /**
     * 是否与View建立连接
     * 每次调用业务请求的时候都要出先调用方法检查是否与View建立连接
     */
    public boolean isViewAttached(){
        return mView != null;
    }
    /**
     * 获取连接的view
     */
    public V getView(){
        return mView;
    }






}
