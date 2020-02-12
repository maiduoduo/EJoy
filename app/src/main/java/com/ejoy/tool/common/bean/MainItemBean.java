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
public class MainItemBean implements MultiItemEntity {

    //类型---------->
    public static final int MAIN_TYPE_TITLE = 0;
    public static final int MAIN_TYPE_CONTENT = 1;
    public static final int MAIN_TYPE_More = 2;
    /**
     * itemType : 0
     * itemTypeTitle : 弹窗
     * Content : [{"clazz":"com.ejoy.tool.ui.activity.ToastActivity","clickCode":0,"flag":"","itemDes":"吐司，支持图标，文本时长等","itemTitle":"Toast"},{"clazz":"com.ejoy.tool.ui.activity.iosdialog.IDialogActivity","clickCode":1,"flag":"","itemDes":"系统对话框解决方案，仿IOS样式","itemTitle":"Dialog"},{"clazz":"com.ejoy.tool.ui.activity.popupwindow.IPopupwindowActivity","clickCode":2,"flag":"","itemDes":"Popupwindow、ECookieBar、SnackBar等","itemTitle":"Popupwindow"},{"clazz":"com.ejoy.tool.ui.activity.bottomsheet.IBottomSheetActivity","clickCode":3,"flag":"","itemDes":"自定义BottomSheetDialog及官方示例","itemTitle":"BottomSheetDialog"},{"clazz":"com.ejoy.tool.ui.activity.picker.ITimeDateOrActivity","clickCode":4,"flag":"","itemDes":"日期选择器，省市区级联选择","itemTitle":"日期等选择器"}]
     */

    private int type;
    private String itemTypeTitle;
    private List<ContentBean> Content;


    @Override
    public int getItemType() {
        return type;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getItemTypeTitle() {
        return itemTypeTitle;
    }

    public void setItemTypeTitle(String itemTypeTitle) {
        this.itemTypeTitle = itemTypeTitle;
    }

    public List<ContentBean> getContent() {
        return Content;
    }

    public void setContent(List<ContentBean> Content) {
        this.Content = Content;
    }


    public static class ContentBean {
        /**
         * clazz : com.ejoy.tool.ui.activity.ToastActivity
         * clickCode : 0
         * flag :
         * itemDes : 吐司，支持图标，文本时长等
         * itemTitle : Toast
         */

        private String clazz;
        private int clickCode;
        private String flag;
        private String itemDes;
        private String itemTitle;

        public String getClazz() {
            return clazz;
        }

        public void setClazz(String clazz) {
            this.clazz = clazz;
        }

        public int getClickCode() {
            return clickCode;
        }

        public void setClickCode(int clickCode) {
            this.clickCode = clickCode;
        }

        public String getFlag() {
            return flag;
        }

        public void setFlag(String flag) {
            this.flag = flag;
        }

        public String getItemDes() {
            return itemDes;
        }

        public void setItemDes(String itemDes) {
            this.itemDes = itemDes;
        }

        public String getItemTitle() {
            return itemTitle;
        }

        public void setItemTitle(String itemTitle) {
            this.itemTitle = itemTitle;
        }
    }
}
