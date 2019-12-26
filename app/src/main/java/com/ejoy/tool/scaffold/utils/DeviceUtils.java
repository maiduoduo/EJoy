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

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.SystemClock;
import android.provider.Settings;
import android.telephony.TelephonyManager;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;

/**
 * CN:      DeviceUtils
 * Author： JSYL-DINGCL (dingcl@jsyl.com.cn)
 * Date:   2019/12/23
 * Des:    TODO:手机信息 & MAC地址 & 开机时间 & ip地址 &手机的IMEI、IMSI等号码
 */

public class DeviceUtils {
    /**
     * MAC地址
     *
     * @return
     */
    public static String getMacAddress() {
         /*获取mac地址有一点需要注意的就是android 6.0版本后，以下注释方法不再适用，
         不管任何手机都会返回"02:00:00:00:00:00"这个默认的mac地址，
         这是googel官方为了加强权限管理而禁用了getSYstemService(Context.WIFI_SERVICE)方法来获得mac地址。*/
        //        String macAddress= "";
//        WifiManager wifiManager = (WifiManager) MyApp.getContext().getSystemService(Context.WIFI_SERVICE);
//        WifiInfo wifiInfo = wifiManager.getConnectionInfo();
//        macAddress = wifiInfo.getMacAddress();
//        return macAddress;

        String macAddress = null;
        StringBuffer buf = new StringBuffer();
        NetworkInterface networkInterface = null;
        try {
            networkInterface = NetworkInterface.getByName("eth1");
            if (networkInterface == null) {
                networkInterface = NetworkInterface.getByName("wlan0");
            }
            if (networkInterface == null) {
                return "02:00:00:00:00:02";
            }
            byte[] addr = networkInterface.getHardwareAddress();
            for (byte b : addr) {
                buf.append(String.format("%02X:", b));
            }
            if (buf.length() > 0) {
                buf.deleteCharAt(buf.length() - 1);
            }
            macAddress = buf.toString();
        } catch (SocketException e) {
            e.printStackTrace();
            return "02:00:00:00:00:02";
        }
        return macAddress;
    }

    /**
     * 获得IP地址，分为两种情况，一是wifi下，二是移动网络下，得到的ip地址是不一样的
     * <uses-permission android:name="android.permission.ACCESS_NETWORK_STATE" />
     */
    public static String getIPAddress(Context context) {
        NetworkInfo info = ((ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE)).getActiveNetworkInfo();
        if (info != null && info.isConnected()) {
            if (info.getType() == ConnectivityManager.TYPE_MOBILE) {//当前使用2G/3G/4G网络
                try {
                    for (Enumeration<NetworkInterface> en = NetworkInterface.getNetworkInterfaces(); en.hasMoreElements(); ) {
                        NetworkInterface intf = en.nextElement();
                        for (Enumeration<InetAddress> enumIpAddr = intf.getInetAddresses(); enumIpAddr.hasMoreElements(); ) {
                            InetAddress inetAddress = enumIpAddr.nextElement();
                            if (!inetAddress.isLoopbackAddress() && inetAddress instanceof Inet4Address) {
                                return inetAddress.getHostAddress();
                            }
                        }
                    }
                } catch (SocketException e) {
                    e.printStackTrace();
                }

            } else if (info.getType() == ConnectivityManager.TYPE_WIFI) {//当前使用无线网络
                WifiManager wifiManager = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
                WifiInfo wifiInfo = wifiManager.getConnectionInfo();
                //调用方法将int转换为地址字符串
                String ipAddress = intIP2StringIP(wifiInfo.getIpAddress());//得到IPV4地址
                return ipAddress;
            }
        } else {
            //当前无网络连接,请在设置中打开网络
        }
        return null;
    }

    /**
     * 将得到的int类型的IP转换为String类型
     *
     * @param ip
     * @return
     */
    private static String intIP2StringIP(int ip) {
        return (ip & 0xFF) + "." +
                ((ip >> 8) & 0xFF) + "." +
                ((ip >> 16) & 0xFF) + "." +
                (ip >> 24 & 0xFF);
    }

    /**
     * 获取 ANDROID_ID
     */
    public static String getAndroidId(Context context) {
        String androidId = Settings.Secure.getString(context.getContentResolver(), Settings.Secure.ANDROID_ID);
        return androidId;
    }

    /**
     * 获取 开机时间
     */
    public static String getBootTimeString() {
        long ut = SystemClock.elapsedRealtime() / 1000;
        int h = (int) ((ut / 3600));
        int m = (int) ((ut / 60) % 60);
        return h + ":" + m;
    }

    /**
     * 手机信息
     * 需要 <uses-permission android:name="android.permission.READ_PHONE_STATE"/>
     *
     * @return
     */
    public static String printSystemInfo(Context context) {
        Date date = new Date(System.currentTimeMillis());
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String time = dateFormat.format(date);
        StringBuilder sb = new StringBuilder();
        sb.append("_______  系统信息  ").append(time).append(" ______________");
        sb.append("\nID                 :").append(Build.ID); // Either  a changelist number, or a label like "M4-rc20".
        sb.append("\nBRAND              :").append(Build.BRAND); //品牌名 如 Xiaomi
        sb.append("\nMODEL              :").append(Build.MODEL); //手机型号
        sb.append("\nRELEASE            :").append(Build.VERSION.RELEASE); //frimware版本(系统版本) 如：2.1-update1
        sb.append("\nSDK                :").append(Build.VERSION.SDK); //sdk版本号

        sb.append("\n\n_______ OTHER _______");
        sb.append("\n基板名(BOARD)              :").append(Build.BOARD); //基板名 如 MSM8974
        sb.append("\n生产厂商(PRODUCT)           :").append(Build.PRODUCT); //The name of the overall product.
        sb.append("\n品牌型号名(DEVICE)             :").append(Build.DEVICE); //品牌型号名，如小米4对应cancro
        sb.append("\n系统信息(FINGERPRINT)        :").append(Build.FINGERPRINT); //包含制造商，设备名，系统版本等诸多信息 如  Xiaomi/cancro_wc_lte/cancro:6.0.1/MMB29M/V8.1.3.0.MXDCNDI:user/release-keys
        sb.append("\n(HOST)               :").append(Build.HOST); // 如 c3-miui-ota-bd43
        sb.append("\n(TAGS)              :").append(Build.TAGS); //Comma-separated tags describing the build, like "unsigned,debug".
        sb.append("\n(TYPE)               :").append(Build.TYPE); //The type of build, like "user" or "eng".
        sb.append("\n(TIME)               :").append(Build.TIME); //当前时间，毫秒值
        sb.append("\n(INCREMENTAL)        :").append(Build.VERSION.INCREMENTAL);

        sb.append("\n\n_______ CUPCAKE-3 _______");
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.CUPCAKE) {
            sb.append("\n(DISPLAY)            :").append(Build.DISPLAY); // 如 MMB29M
        }

        sb.append("\n\n_______ DONUT-4 _______");
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.DONUT) {
            sb.append("\nSDK_INT            :").append(Build.VERSION.SDK_INT);
            sb.append("\nMANUFACTURER       :").append(Build.MANUFACTURER); // The manufacturer of the product/hardware. 如 Xiaomi
            sb.append("\nBOOTLOADER         :").append(Build.BOOTLOADER); //The system bootloader version number. 如
            sb.append("\nCPU_ABI            :").append(Build.CPU_ABI); // 如 armeabi-v7a
            sb.append("\nCPU_ABI2           :").append(Build.CPU_ABI2); // 如 armeabi
            sb.append("\nHARDWARE           :").append(Build.HARDWARE); // The name of the hardware (from the kernel command line or /proc). 如 qcom
            sb.append("\nUNKNOWN            :").append(Build.UNKNOWN); // Value used for when a build property is unknown.
            sb.append("\nCODENAME           :").append(Build.VERSION.CODENAME);
        }

        sb.append("\n\n_______ GINGERBREAD-9 _______");
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.GINGERBREAD) {
            sb.append("\nSERIAL             :").append(Build.SERIAL); // A hardware serial number, if available. 如 abcdefgh
        }

        sb.append("\nIMEI             :").append(getPhoneIMEI(context));
        sb.append("\nIMSI             :").append(getPhoneIMSI(context));
        sb.append("\n开机时间             :").append(getBootTimeString());
        sb.append("\nANDROID_ID             :").append(getAndroidId(context));
        sb.append("\nMAC地址             :").append(getMacAddress());
        return sb.toString();
    }

    /**
     * 获取手机的IMEI号码
     * 使用TelephonyManager时需要 <uses-permission android:name="READ_PHONE_STATE" />
     */
    public static String getPhoneIMEI(Context context) {
        TelephonyManager mTm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        String imei = mTm.getDeviceId();
        return imei;
    }

    /**
     * 获取手机的imsi号码
     * 使用TelephonyManager时需要 <uses-permission android:name="READ_PHONE_STATE" />
     */
    public static String getPhoneIMSI(Context context) {
        TelephonyManager mTm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        String imsi = mTm.getSubscriberId();
        return imsi;
    }

    /**
     * 获取手机号码
     * 使用TelephonyManager时需要 <uses-permission android:name="READ_PHONE_STATE" />
     */
    public static String getPhoneNumer(Context context) {
        TelephonyManager mTm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        String numer = mTm.getLine1Number(); // 手机号码，有的可得，有的不可得
        return numer;
    }

}




