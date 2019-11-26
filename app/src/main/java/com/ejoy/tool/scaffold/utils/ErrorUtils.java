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

import android.app.ActivityManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v4.content.ContextCompat;
import android.telephony.TelephonyManager;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


/**
 * CN:      ErrorUtils
 * Author： JSYL-DINGCL (dingcl@jsyl.com.cn)
 * Date:   2019/10/28
 * Des:    TODO:
 */
public class ErrorUtils {
    public ErrorUtils() {
    }

    public static int dip2px(Context var0, float var1) {
        float var2 = var0.getResources().getDisplayMetrics().density;
        return (int)(var1 * var2 + 0.5F);
    }

    public static int px2dip(Context var0, float var1) {
        float var2 = var0.getResources().getDisplayMetrics().density;
        return (int)(var1 / var2 + 0.5F);
    }

    public static String getErrorMessage(int var0) {
        String var1 = "";
        switch(var0) {
            case 0:
                var1 = "您的网络不给力！";
                break;
            case 404:
                var1 = "找不到指定内容！";
                break;
            case 405:
                var1 = "方法不允许！";
                break;
            case 500:
                var1 = "网络繁忙！";
                break;
            case 502:
                var1 = "错误的网关！";
                break;
            case 504:
                var1 = "网关超时！";
                break;
            case 600:
                var1 = "未能查询到您的订单！";
                break;
            case 700:
                var1 = "未能查询到您所查询的内容！";
                break;
            default:
                var1 = "未知的错误！错误码：" + var0;
        }

        return var1;
    }

    public static String getString(JSONObject var0, String var1) {
        String var2 = "";

        try {
            String var3 = var0.getString(var1);
            if (var3 != null) {
                var2 = var0.getString(var1);
            }
        } catch (JSONException var4) {
            var4.printStackTrace();
        }

        return var2;
    }

    public static List<String> removeRepeatString(List<String> var0) {
        ArrayList var1 = new ArrayList();

        for(int var2 = 0; var2 < var0.size(); ++var2) {
            if (!var1.contains(var0.get(var2))) {
                var1.add(var0.get(var2));
            }
        }

        return var1;
    }

    public static String[] list2array(List<String> var0) {
        String[] var1 = new String[var0.size()];
        int var2 = 0;

        for(int var3 = var0.size(); var2 < var3; ++var2) {
            var1[var2] = (String)var0.get(var2);
        }

        return var1;
    }

    public static boolean isExsitMianActivity(Class var0, Context var1) {
        Intent var2 = new Intent(var1, var0);
        ComponentName var3 = var2.resolveActivity(var1.getPackageManager());
        boolean var4 = false;
        if (var3 != null) {
//            ActivityManager var5 = (ActivityManager)var1.getSystemService("activity");
            ActivityManager var5 = (ActivityManager)var1.getSystemService(Context.ACTIVITY_SERVICE);
            List var6 = var5.getRunningTasks(10);
            Iterator var7 = var6.iterator();

            while(var7.hasNext()) {
                ActivityManager.RunningTaskInfo var8 = (ActivityManager.RunningTaskInfo)var7.next();
                if (var8.baseActivity.equals(var3)) {
                    var4 = true;
                    break;
                }
            }
        }

        return var4;
    }

    public static boolean isNetworkAvailable(Context var0) {
//        ConnectivityManager var1 = (ConnectivityManager)var0.getSystemService("connectivity");
        ConnectivityManager var1 = (ConnectivityManager)var0.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (var1 != null) {
            NetworkInfo var2 = var1.getActiveNetworkInfo();
            if (var2 != null && var2.isConnected() && var2.getState() == NetworkInfo.State.CONNECTED) {
                return true;
            }
        }

        return false;
    }

    public static String getDeviceIMEI(Context var0) {
        String var1 = "";
        if (ContextCompat.checkSelfPermission(var0, "android.permission.READ_PHONE_STATE") == 0) {
            TelephonyManager var2 = (TelephonyManager)var0.getSystemService(Context.TELEPHONY_SERVICE);
            var1 = var2.getDeviceId();
        }

        return var1;
    }
}
