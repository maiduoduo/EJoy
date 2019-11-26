package com.ejoy.tool.scaffold.utils;
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


import com.ejoy.tool.R;

/**
 * CN:      IToastImageType
 * Author： JSYL-DINGCL (dingcl@jsyl.com.cn)
 * Date:   2019/11/1
 * Des:    Toast图片类型
 */
public enum IToastImageType {
    DEFAULT,//默认
    SUCCESS,//成功
    FAIL,//失败
    WARN,//警告
    ERROR,//错误
    INFO,//普通信息
    YES,//是
    NO;//否

    public static int getCommonImageResID(IToastImageType type) {
        switch (type) {
            case FAIL:
                return R.drawable.itoast_ico_fail;
            case WARN:
                return R.drawable.itoast_ico_fail;
            case SUCCESS:
            case YES:
                return R.drawable.itoast_ico_done;
            case INFO:
                return R.drawable.itoast_ico_info;
            case NO:
            case ERROR:
                return R.drawable.itoast_ico_error;
            default:
                return R.drawable.itoast_ico_info;

        }
    }
}
