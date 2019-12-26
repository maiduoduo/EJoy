package com.ejoy.tool.scaffold.utils;

import android.app.Activity;
import android.app.Service;
import android.os.Vibrator;
import android.widget.Toast;

/**
 * 手机震动工具类
 */
public class VibratorUtil {
    private static Vibrator vibrator = null ;
    private static Activity mContext;
    public VibratorUtil(Activity activity) {
        mContext = activity;
    }

    public VibratorUtil getInstance(){
        if (vibrator == null) {
            synchronized (VibratorUtil.class){
                if (vibrator == null){
                    vibrator = (Vibrator) mContext.getSystemService(Service.VIBRATOR_SERVICE);
                }
            }
        }
        return this;
    }

    /**
     * final Activity activity  ：调用该方法的Activity实例
     * long milliseconds ：震动的时长，单位是毫秒
     */
    public VibratorUtil vibrate(long milliseconds) {
//        Vibrator vib = (Vibrator) activity.getSystemService(Service.VIBRATOR_SERVICE);
        if (checkHasVibrate()) {
            vibrator.vibrate(milliseconds);
        }else {
            Toast.makeText(mContext,"不支持震动",Toast.LENGTH_SHORT).show();
        }
        return this;
    }
    /**
     * final Activity activity  ：调用该方法的Activity实例
     * long milliseconds ：震动的时长，单位是毫秒
     * long[] pattern  ：自定义震动模式 。数组中数字的含义依次是[静止时长，震动时长，静止时长，震动时长。。。]时长的单位是毫秒
     * boolean isRepeat ： 是否反复震动，如果是true，反复震动，如果是false，只震动一次
     */
    public void vibrate(long[] pattern, boolean isRepeat) {
//        Vibrator vib = (Vibrator) activity.getSystemService(Service.VIBRATOR_SERVICE);
        if (checkHasVibrate()) {
            vibrator.vibrate(pattern, isRepeat ? 1 : -1);
        }else {
            Toast.makeText(mContext,"不支持震动",Toast.LENGTH_SHORT).show();
        }
    }

    public  void cancelVibrate(){
        if (vibrator != null){
            vibrator.cancel();
        }
    }

    private boolean checkHasVibrate(){
        if (vibrator != null){
            return vibrator.hasVibrator();
        }else {
            return false;
        }
    }

}
