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

import com.chad.library.adapter.base.entity.MultiItemEntity;

import java.util.List;

/**
 * CN:      MainItemBean
 * Author： JSYL-DINGCL (dingcl@jsyl.com.cn)
 * Date:   2019/11/26
 * Des:    TODO:
 */
public class MainItemBean_temp implements MultiItemEntity {

    //类型---------->
    public static final int MAIN_TYPE_TITLE = 0;
    public static final int MAIN_TYPE_CONTENT = 1;

    public static int itemType;
    public static String itemTypeTitle;
    public static List<ContentBean> contentlist;

    @Override
    public int getItemType() {
        if (itemType == 0)return MAIN_TYPE_TITLE;
        if (itemType == 1)return MAIN_TYPE_CONTENT;
        else return MAIN_TYPE_TITLE;
    }

    public static class ContentBean{
        public String itemTitle;
        public int itemSrc;
        public String itemDes;
        public int clickCode;
        public  Class clazz;
        public  String flag;

        public ContentBean(String itemTitle, int itemSrc, String itemDes) {
            this.itemTitle = itemTitle;
            this.itemSrc = itemSrc;
            this.itemDes = itemDes;
        }

        public ContentBean(String itemTitle, String itemDes, int clickCode, Class clazz, String flag) {
            this.itemTitle = itemTitle;
            this.itemDes = itemDes;
            this.clickCode = clickCode;
            this.clazz = clazz;
            this.flag = flag;
        }

    }

    public MainItemBean_temp(int itemType, String itemTypeTitle) {
        this.itemType = itemType;
        this.itemTypeTitle = itemTypeTitle;
    }

    public MainItemBean_temp(int itemType, List<ContentBean> contentlsit) {
        this.itemType = itemType;
        this.contentlist = contentlsit;
    }
}
