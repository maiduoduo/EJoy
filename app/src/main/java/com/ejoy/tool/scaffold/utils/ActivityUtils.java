package com.ejoy.tool.scaffold.utils;

import android.app.ActivityManager;
import android.app.ActivityManager.RunningTaskInfo;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.util.Log;

import java.util.List;

public class ActivityUtils {

	
	/**
	 * 
	 * 判断某个activity是否存在	 
	 */
	public static boolean isExsitMianActivity(Class cls, Context context) {
		Intent intent = new Intent(context, cls);
		ComponentName cmpName = intent.resolveActivity(context.getPackageManager());
		boolean flag = false;
		if (cmpName != null) { // 说明系统中存在这个activity
			ActivityManager am = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
			List<RunningTaskInfo> taskInfoList = am.getRunningTasks(10);
			for (RunningTaskInfo taskInfo : taskInfoList) {
				if (taskInfo.baseActivity.equals(cmpName)) { // 说明它已经启动了
					flag = true;
					break; // 跳出循环，优化效率
				}
			}
		}
		return flag;
	}

	/**
	 * 判断某个Activity 界面是否在前台
	 * @param context
	 * @param className 某个界面名称
	 * @return
	 */
	public static boolean  isForeground(Context context, String className) {
		if (context == null || TextUtils.isEmpty(className)) {
			return false;
		}

		ActivityManager am = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
		List<RunningTaskInfo> list = am.getRunningTasks(1);
		if (list != null && list.size() > 0) {
			ComponentName cpn = list.get(0).topActivity;
			Log.e("activitylist","当前前台的Activity className:"+cpn.getClassName());
			Log.e("activitylist","当前前台的Activity :"+className.equals(cpn.getClassName()));
			if (className.equals(cpn.getClassName())) {
				return true;
			}
		}

		return false;

	}
	
}
