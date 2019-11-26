package com.ejoy.tool.common.bean;
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
 * CN:      MainItemBean
 * Author： JSYL-DINGCL (dingcl@jsyl.com.cn)
 * Date:   2019/11/26
 * Des:    TODO:
 */
public class MainItemBean {
    private String itemTitle;
    private int itemSrc;

    public MainItemBean(String itemTitle, int itemSrc) {
        this.itemTitle = itemTitle;
        this.itemSrc = itemSrc;
    }

    public String getItemTitle() {
        return itemTitle;
    }

    public void setItemTitle(String itemTitle) {
        this.itemTitle = itemTitle;
    }

    public int getItemSrc() {
        return itemSrc;
    }

    public void setItemSrc(int itemSrc) {
        this.itemSrc = itemSrc;
    }
}
