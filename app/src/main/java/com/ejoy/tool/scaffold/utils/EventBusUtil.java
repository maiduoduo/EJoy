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


import com.ejoy.tool.common.event.SuccessEvent;

import org.greenrobot.eventbus.EventBus;

/**
 * CN:      EventBusUtil
 * Author： JSYL-DINGCL (dingcl@jsyl.com.cn)
 * Date:   2019/11/15
 * Des:    EventBus工具类
 */
public class EventBusUtil {

    public static void register(Object subscriber) {
        EventBus.getDefault().register(subscriber);
    }

    public static void unregister(Object subscriber) {
        EventBus.getDefault().unregister(subscriber);
    }

    public static void sendEvent(Object event) {
        EventBus.getDefault().post(event);
    }

    public static void sendSuccessEvent() {
        sendSuccessEvent(0);
    }
    public static void sendSuccessEvent(int type) {
        sendEvent(new SuccessEvent(type));
    }
    public static void sendStickyEvent(Object event) {
        EventBus.getDefault().postSticky(event);
    }

    public static void removeStickyEvent(Object stickyEvent){
        EventBus.getDefault().removeStickyEvent(stickyEvent);
    }
}
