package com.ejoy.tool.ui.base.base_view;

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
 * CN:      BaseView
 * Author： JSYL-DINGCL (dingcl@jsyl.com.cn)
 * Date:   2019/11/14
 * Des:    View父类
 */
public interface BaseView {

    /**
     * 显示加载弹窗
     *
     * @param msg
     */
    void showLoadingDialog(String msg);
    /**
     * 关闭加载弹窗
     *
     */
    void hideLoadingDialog();

    /**
     * 显示提示
     *
     * @param msg
     */
    void showToast(String msg);

    void showSuccessTips(String msg);

    void showFailTips(String msg);


}
