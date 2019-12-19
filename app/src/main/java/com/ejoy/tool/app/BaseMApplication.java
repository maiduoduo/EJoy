package com.ejoy.tool.app;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import com.ejoy.tool.R;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;

import java.util.LinkedList;
import java.util.List;



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
 * CN:      BaseCPApplication
 * Author： JSYL-DINGCL (1144286501@qq.com)
 * Date:   2019/10/14
 * Des:    Application基类进行dex分包
 */
public class BaseMApplication extends Application {


    /**
     * 上下文
     */
    protected static BaseMApplication appContext;
    /**
     * 主线程Handler
     */
    protected static Handler handler;
    /**
     * 主线程Handler
     */
    private static Handler mHandler;
    /**
     * 主线程
     */
    private static Thread mMainThread;

    /**
     * 主线程id
     */
    private static long mMainThreadId;
    /**
     * 循环队列
     */
    private static Looper mMainLooper;
    private static int mainTid;


    private List<Activity> activityList = new LinkedList<>();
    private static BaseMApplication instance;

    //单例模式中获取唯一的MyApplication实例
    public static BaseMApplication getInstance2() {
        if(null == instance) {
            instance = new BaseMApplication();
        }
        return instance;
    }


    public static BaseMApplication getInstance(){
        return appContext;
    }
    public static Context getAppContext() {
        return appContext;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        appContext = this;
        initLogger();
        mainTid = android.os.Process.myTid();
        handler=new Handler();
        initImageLoader(getApplicationContext());
    }

    protected void initLogger() {
    }

    protected boolean isDebug(){
        return true;
    }

    protected String setLoggerName(){
        return getString(R.string.app_name);
    }



    //添加Activity到容器中
    public void addActivity(Activity activity)  {
        activityList.add(activity);
    }

    //遍历所有Activity并finish
    public void exit() {
        for(Activity activity:activityList) {
            activity.finish();
        }
        activityList.clear();
    }


    private void initImageLoader(Context applicationContext) {
        ImageLoaderConfiguration.Builder config = new ImageLoaderConfiguration.Builder(applicationContext);
        config.threadPriority(Thread.NORM_PRIORITY - 2);
        config.denyCacheImageMultipleSizesInMemory();
        config.diskCacheFileNameGenerator(new Md5FileNameGenerator());
        config.diskCacheSize(50 * 1024 * 1024); // 50 MiB
        config.tasksProcessingOrder(QueueProcessingType.LIFO);
        config.writeDebugLogs(); // Remove for release app

        // Initialize ImageLoader with configuration.
        ImageLoader.getInstance().init(config.build());

    }

    public static int getMainTid() {
        return mainTid;
    }
    public static Handler getHandler() {
        return handler;
    }






}
