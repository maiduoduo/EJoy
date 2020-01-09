package com.module.ires.bean;
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

import android.support.annotation.DrawableRes;

/**
 * CN:      CustomBottomSheetItemBean
 * Author： JSYL-DINGCL (dingcl@jsyl.com.cn)
 * Date:   2019/12/27
 * Des:    TODO:CustomBottomSheetItem实体
 *      （横向图片+功能文本）
 */
public class CustomBottomSheetItemBean {
    private @DrawableRes int cbsIco;
    private String cbsMenuText;
    private String cbsMenusubText;

    public CustomBottomSheetItemBean(int cbsIco, String cbsMenuText, String cbsMenusubText) {
        this.cbsIco = cbsIco;
        this.cbsMenuText = cbsMenuText;
        this.cbsMenusubText = cbsMenusubText;
    }

    public CustomBottomSheetItemBean(int cbsIco, String cbsMenuText) {
        this.cbsIco = cbsIco;
        this.cbsMenuText = cbsMenuText;
    }

    public String getCbsMenusubText() {
        return cbsMenusubText;
    }

    public void setCbsMenusubText(String cbsMenusubText) {
        this.cbsMenusubText = cbsMenusubText;
    }

    public int getCbsIco() {
        return cbsIco;
    }

    public void setCbsIco(int cbsIco) {
        this.cbsIco = cbsIco;
    }

    public String getCbsMenuText() {
        return cbsMenuText;
    }

    public void setCbsMenuText(String cbsMenuText) {
        this.cbsMenuText = cbsMenuText;
    }
}
