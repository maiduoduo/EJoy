package com.module.ires.bean.utils;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Environment;
import android.util.Log;

import com.google.gson.Gson;

import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.lang.reflect.Field;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @ClassName: LogCrashHandler
 * @Author: bsj-dingcl
 * @Email: dingchenglei@h4kit.com
 * @Date: 2020/
 * @des: 停止运行崩溃异常信息保存和上传
 * UncaughtException处理类,当程序发生Uncaught异常的时候,有该类来接管程序,并记录发送错误报告
 */
public class ILogCrashHandler implements Thread.UncaughtExceptionHandler {
    String time;
    // 系统默认的UncaughtException处理类
    private Thread.UncaughtExceptionHandler mDefaultHandler;
    // CrashHandler实例
    private static ILogCrashHandler INSTANCE = new ILogCrashHandler();
    // 程序的Context对象
    private Context mContext;
    // 用来存储设备信息和异常信息
    private Map<String, String> infos = new LinkedHashMap<String, String>();
    // 用于格式化日期,作为日志文件名的一部分
    private DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
    /**
     * 程序崩溃报告根目录
     **/
    private String path = Environment.getExternalStorageDirectory().getPath() + "/"+ "EJOY"+"/AppCrash" +File.separator;
    /**
     * 程序崩溃报告上传服务器地址
     **/
    private String setUrl = "";
    @SuppressWarnings("unused")
    private Gson gson = new Gson();

    /**
     * 保证只有一个CrashHandler实例
     */
    private ILogCrashHandler() {
    }

    /**
     * 获取CrashHandler实例 ,单例模式
     */
    public static ILogCrashHandler getInstance() {
        return INSTANCE;
    }

    /**
     * 初始化
     *
     * @param context
     */
    public void init(Context context) {
        mContext = context;
        // 获取系统默认的UncaughtException处理器
        mDefaultHandler = Thread.getDefaultUncaughtExceptionHandler();
        // 设置该CrashHandler为程序的默认处理器
        Thread.setDefaultUncaughtExceptionHandler(this);
    }

    /**
     * 当UncaughtException发生时会转入该函数来处理
     */
    @Override
    public void uncaughtException(Thread thread, Throwable ex) {
        // if (!handleException(ex) && mDefaultHandler != null) {
        // // 如果用户没有处理则让系统默认的异常处理器来处理
        // mDefaultHandler.uncaughtException(thread, ex);
        // }
        if (!handleException(ex)) {
            if (mDefaultHandler != null) {
                mDefaultHandler.uncaughtException(thread, ex);
            }
        } else {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
            }
            // 退出程序
            android.os.Process.killProcess(android.os.Process.myPid());
            System.exit(1);
        }
    }

    /**
     * 自定义错误处理,收集错误信息 发送错误报告等操作均在此完成.
     *
     * @param ex
     * @return true:如果处理了该异常信息;否则返回false.
     */
    private boolean handleException(Throwable ex) {
        if (ex == null) {
            return true;
        }
//		// 使用Toast来显示异常信息
//		new Thread() {
//			@Override
//			public void run() {
//				Looper.prepare();
//				Toast.makeText(mContext, "很抱歉,程序出现异常,即将退出……", Toast.LENGTH_LONG)
//						.show();
//				Looper.loop();
//			}
//		}.start();
        // 收集设备参数信息
        collectDeviceInfo(mContext);
        // 保存日志文件到设备
        saveCrashInfo2File(ex);
        // 保存日志文件到服务器
        setToServer();
        return false;
    }

    private void setToServer() {

        HashMap<String, Object> params = new HashMap<String, Object>();
        File file = new File(path + File.separator + time + ".log");
        params.put(time, file);
//		OkHttpUtil.getInstance().upLoadFile(setUrl, params, new OkHttpUtil.DataCallBack() {
//			@Override
//			public void requestSuccess(String result) throws Exception {
//			}
//
//			@Override
//			public void requestFailure(Request request, IOException e) {
//			}
//
//			@Override
//			public void requestProgress(long current, long total) {
//			}
//		});
    }

    /**
     * 收集设备参数信息
     *
     * @param ctx
     */
    public void collectDeviceInfo(Context ctx) {
        try {
            PackageManager pm = ctx.getPackageManager();
            PackageInfo pi = pm.getPackageInfo(
                    ctx.getPackageName(), 0);
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// HH:mm:ss
//                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");// HH:mm:ss
            //获取当前时间
            Date date = new Date(System.currentTimeMillis());
            String time = simpleDateFormat.format(date);
            if (pi != null) {
                infos = new LinkedHashMap<String, String>();
                int labelRes = pi.applicationInfo.labelRes;
                infos.put("appName", ctx.getResources().getString(labelRes));//软件名称
                infos.put("appVersionName", pi.versionName);//软件版本名称
                infos.put("appVersionCode", pi.versionCode + "");//软件版本号
                infos.put("crashTime", time+ "");//崩溃时间5
//				String crashGps =  ApplicationData.crashGps;//当前设备所在经纬度
//                infos.put("appGps",crashGps);
            }
            Field[] fields = Build.class.getDeclaredFields();
            for (Field field : fields) {
                try {
                    field.setAccessible(true);
                    infos.put(field.getName(), field.get(null).toString());
                } catch (Exception e) {
                }
            }

        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

    }

    /**
     * 保存错误信息到文件中
     *
     * @param ex
     * @return 返回文件名称, 便于将文件传送到服务器
     */
    private String saveCrashInfo2File(Throwable ex) {

        StringBuffer sb = new StringBuffer();
        for (Map.Entry<String, String> entry : infos.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();
            sb.append(key + "=" + value + "\n");
        }
        Writer writer = new StringWriter();
        PrintWriter printWriter = new PrintWriter(writer);
        ex.printStackTrace(printWriter);
        Throwable cause = ex.getCause();
        while (cause != null) {
            cause.printStackTrace(printWriter);
            cause = cause.getCause();
        }
        printWriter.close();
        String result = writer.toString();
        sb.append(result);
        try {
//			long timestamp = System.currentTimeMillis();
            time = formatter.format(new Date());
            String fileName = time + ".log";
            if (Environment.getExternalStorageState().equals(
                    Environment.MEDIA_MOUNTED)) {
                File dir = new File(path);
                if (!dir.exists()) {
                    dir.mkdirs();
                }
                //将崩溃日志写入文件中
                FileOutputStream fos = new FileOutputStream(path + fileName);
                fos.write(sb.toString().getBytes());
                fos.close();
            }

            return fileName;
        } catch (Exception e) {
            Log.e("LogCrashHandler", e.getMessage());
        }
        return null;
    }

}
