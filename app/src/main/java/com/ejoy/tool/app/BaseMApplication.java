package com.ejoy.tool.app;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;

import com.ejoy.tool.R;
import com.ejoy.tool.app.bugly.BuglyHelper;
import com.ejoy.tool.ui.base.base_activity.IBaseActivity;
import com.kongzue.baseframework.BaseActivity;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;

import java.util.LinkedList;
import java.util.List;
import java.util.Stack;


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

    private static Stack<AppCompatActivity> activityStack;

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
        BuglyHelper.getInstance().initBuglyApp(this);
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

    /**
     * 添加Activity到堆栈
     */
    public void pushActivity(AppCompatActivity activity) {
        if (activityStack == null) {
            activityStack = new Stack<>();
        }
        activityStack.add(activity);
    }

    //遍历所有Activity并finish
    public void exit() {
        for(Activity activity:activityList) {
            activity.finish();
        }
        activityList.clear();
    }

    public void deleteActivity(AppCompatActivity activity) {
        if (activity != null) {
            activityStack.remove(activity);
        }
    }

    /**
     * 结束指定的Activity
     */
    public void killActivity(IBaseActivity activity) {
        if (activity != null) {
            activity.finishActivity();
            activityStack.remove(activity);
            activity = null;
        }
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
