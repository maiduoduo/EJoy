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

import android.app.Activity;
import android.app.ActivityManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Environment;
import android.os.Looper;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.Log;
import java.io.File;
import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;

/**
 * CN:      Tools
 * Author： JSYL-DINGCL (dingcl@jsyl.com.cn)
 * Date:   2019/11/img_a
 * Des:    TODO:
 */
public class Tools {
    public static boolean isInBg = true;

    public Tools() {
    }

    public static boolean isInMainThread() {
        return Looper.myLooper() == Looper.getMainLooper();
    }

    public static String getRandomCharNum(int length) {
        String val = "";
        Random random = new Random();

        for(int i = 0; i < length; ++i) {
            String charOrNum = random.nextInt(2) % 2 == 0 ? "char" : "num";
            if ("char".equalsIgnoreCase(charOrNum)) {
                int choice = random.nextInt(2) % 2 == 0 ? 65 : 97;
                val = val + (char)(choice + random.nextInt(26));
            } else if ("num".equalsIgnoreCase(charOrNum)) {
                val = val + String.valueOf(random.nextInt(10));
            }
        }

        return val;
    }

    public static boolean isRunBackGroud(Context context) {
        ActivityManager am = (ActivityManager)context.getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningTaskInfo> tasks = am.getRunningTasks(1);
        if (!tasks.isEmpty()) {
            ComponentName topActivity = ((ActivityManager.RunningTaskInfo)tasks.get(0)).topActivity;
            if (!topActivity.getPackageName().equals(context.getPackageName())) {
                return true;
            }
        }

        return false;
    }

    public static String getVersionName(Context ctx) {
        PackageInfo packageInfo = null;

        try {
            packageInfo = ctx.getPackageManager().getPackageInfo(ctx.getPackageName(), 0);
        } catch (PackageManager.NameNotFoundException var3) {
            var3.printStackTrace();
        }

        return packageInfo == null ? "" : packageInfo.versionName;
    }

    public static double calculateTotal(double db) {
        double x = db / 1024.0D / 1024.0D;
        x = (double)Math.round(x * 100.0D) / 100.0D;
        return x;
    }

    public static double calculatePercent(double a, double b) {
        double x = a / b;
        x = (double)Math.round(x * 100.0D) / 100.0D;
        return x;
    }



    public static boolean isConnect(Context context) {
        try {
            ConnectivityManager connectivity = (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
            if (connectivity != null) {
                NetworkInfo info = connectivity.getActiveNetworkInfo();
                if (info != null && info.isConnected() && info.getState() == NetworkInfo.State.CONNECTED) {
                    return true;
                }
            }
        } catch (Exception var3) {
            Log.v("error", var3.toString());
        }

        return false;
    }


    public static byte[] shortArray2ByteArray(short[] shorts) {
        byte[] bytes = new byte[shorts.length * 2];
        ByteBuffer.wrap(bytes).order(ByteOrder.LITTLE_ENDIAN).asShortBuffer().put(shorts);
        return bytes;
    }

    public static short[] byteArray2ShortArray(byte[] bytes) {
        short[] shorts = new short[bytes.length / 2];
        ByteBuffer.wrap(bytes).order(ByteOrder.LITTLE_ENDIAN).asShortBuffer().get(shorts);
        return shorts;
    }

    public static int[] getWidthHeight(Activity ctx) {
        int[] res = new int[2];
        DisplayMetrics dm = new DisplayMetrics();
        ctx.getWindowManager().getDefaultDisplay().getMetrics(dm);
        int screenWidth = dm.widthPixels;
        int screenHeigh = dm.heightPixels;
        res[0] = screenWidth;
        res[1] = screenHeigh;
        return res;
    }

    public static double getWidthHeightRate(Activity ctx) {
        int[] res = getWidthHeight(ctx);
        return (double)res[0] * 1.0D / ((double)res[1] * 1.0D);
    }

    public static void writeFileToSD(String str) {
        if (!TextUtils.isEmpty(str)) {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date curDate = new Date(System.currentTimeMillis());
            str = formatter.format(curDate) + ": " + str;
            String sdStatus = Environment.getExternalStorageState();
            if (!sdStatus.equals("mounted")) {
                Log.d("TestFile", "SD card is not avaiable/writeable right now.");
            } else {
                try {
                    String pathName = Environment.getExternalStorageDirectory().getPath() + "/videoTest/";
                    String fileName = Build.MODEL + ".txt";
                    File path = new File(pathName);
                    File file = new File(pathName + fileName);
                    if (!path.exists()) {
                        Log.d("TestFile", "Create the path:" + pathName);
                        path.mkdir();
                    }

                    if (!file.exists()) {
                        Log.d("TestFile", "Create the file:" + fileName);
                        file.createNewFile();
                    }

                    FileOutputStream stream = new FileOutputStream(file, true);
                    byte[] buf = str.getBytes();
                    stream.write(buf);
                    stream.close();
                } catch (Exception var10) {
                    Log.e("TestFile", "Error on writeFilToSD.");
                    var10.printStackTrace();
                }

            }
        }
    }

}
