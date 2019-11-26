package com.ejoy.tool.scaffold.utils;

/**
 * CN:      ObjectUtil
 * Author： JSYL-DINGCL (dingcl@jsyl.com.cn)
 * Date:   2019/11/img_g
 * Des:    ObjectUtil
 */
public class ObjectUtil {
    public static<T> T requireNonNull(T object,String message){
        if(object == null){
            throw new NullPointerException(message);
        }
        return object;
    }
}
