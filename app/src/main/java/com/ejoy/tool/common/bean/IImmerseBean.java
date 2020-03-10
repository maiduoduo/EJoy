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

/**
 * CN:      IImmerseBean
 * Author： JSYL-DINGCL (dingcl@jsyl.com.cn)
 * Date:   2020/3/9
 * Des:    TODO:
 */
public class IImmerseBean implements MultiItemEntity {
    private String name;
    private int type;

    //类型---------->
    public static final int TYPE_HEADER = 0;
    public static final int TYPE_CONTENT = 1;

    @Override
    public int getItemType() {
        return type;
    }

    public IImmerseBean(String name, int type) {
        this.name = name;
        this.type = type;
    }
}
