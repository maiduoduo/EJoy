package com.ejoy.tool.app;



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

import com.facebook.drawee.backends.pipeline.Fresco;

/**
 * CN:      App
 * Author： JSYL-DINGCL (1144286501@qq.com)
 * Date:   2019/10/14
 * Des:    必须继承BaseApplication
 */
public class App extends BaseMApplication {

    private static App instance;

    @Override
    public void onCreate() {
        super.onCreate();
        init();
        //初始化Fresco
        Fresco.initialize(this);

    }

    private void init() {
        instance = this;
    }


    /**
     * 是否打印日志
     *
     * @return
     */
    @Override
    protected boolean isDebug() {
        return false;
    }

}
