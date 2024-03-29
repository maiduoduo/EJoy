package com.ejoy.tool.scaffold.utils;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.ActivityManager.RunningTaskInfo;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;

import com.ejoy.tool.R;

import java.io.Serializable;
import java.util.List;

public class ActivityUtils {

	private static class Holder {
		private static final ActivityUtils instance = new ActivityUtils();
	}

	private ActivityUtils() {

	}

	public static final ActivityUtils getInstance() {
		return Holder.instance;
	}


	
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




	public void showActivity(Activity aty, Class clazz) {
		Intent i = new Intent(aty, clazz);
		aty.startActivity(i);
		aty.overridePendingTransition(R.anim.zoomin, R.anim.zoomout);
	}

	public void showActivity(Activity aty, Class clazz, String key, Serializable serialize) {
		Intent i = new Intent(aty, clazz);
		i.putExtra(key, serialize);
		aty.startActivity(i);
		aty.overridePendingTransition(R.anim.zoomin, R.anim.zoomout);
	}

	public void showActivity(Activity aty, Class clazz, Bundle bundle) {
		Intent i = new Intent(aty, clazz);
		i.putExtras(bundle);
		aty.startActivity(i);
		aty.overridePendingTransition(R.anim.zoomin, R.anim.zoomout);
	}

	public void skipActivity(Activity aty, Class clazz) {
		Intent i = new Intent(aty, clazz);
		aty.startActivity(i);
		aty.overridePendingTransition(R.anim.zoomin, R.anim.zoomout);
		aty.finish();
	}

	public void skipActivity(Activity aty, Class clazz, Bundle bundle) {
		Intent i = new Intent(aty, clazz);
		i.putExtras(bundle);
		aty.startActivity(i);
		aty.overridePendingTransition(R.anim.zoomin, R.anim.zoomout);
		aty.finish();
	}

	public void closeSelf(Activity aty) {
		aty.finish();
	}

	public void showActivityForResult(Activity aty, int requestCode, Class clazz) {
		Intent i = new Intent(aty, clazz);
		aty.startActivityForResult(i, requestCode);
	}

	public void showActivity(Activity aty, int flags, Class clazz) {
		Intent i = new Intent(aty, clazz);
		i.setFlags(flags);
		aty.startActivity(i);
		aty.overridePendingTransition(R.anim.zoomin, R.anim.zoomout);
	}

	public void skipActivity(Activity aty, int flags, Class clazz) {
		Intent i = new Intent(aty, clazz);
		i.setFlags(flags);
		aty.startActivity(i);
		aty.overridePendingTransition(R.anim.zoomin, R.anim.zoomout);
		aty.finish();
	}

	public void skipActivity(Activity aty, Bundle bundle, int flags, Class clazz) {
		Intent i = new Intent(aty, clazz);
		i.putExtras(bundle);
		i.setFlags(flags);
		aty.startActivity(i);
		aty.overridePendingTransition(R.anim.zoomin, R.anim.zoomout);
		aty.finish();
	}

	public void showActivity(Activity aty, int flags, Bundle bundle, Class clazz) {
		Intent i = new Intent(aty, clazz);
		i.putExtras(bundle);
		i.setFlags(flags);
		aty.startActivity(i);
		aty.overridePendingTransition(R.anim.zoomin, R.anim.zoomout);
	}

	public void showActivity(Context aty, int flags, Bundle bundle, Class clazz) {
		Intent i = new Intent(aty, clazz);
		i.putExtras(bundle);
		i.setFlags(flags);
		aty.startActivity(i);

	}

	public void showActivity(Context aty, int flags, Class clazz) {
		Intent i = new Intent(aty, clazz);
		i.setFlags(flags);
		aty.startActivity(i);
	}

	public void showActivityAnima(Activity aty, Class clazz, Bundle bundle, int inAnima, int outAnima) {
		Intent i = new Intent(aty, clazz);
		i.putExtras(bundle);
		aty.startActivity(i);
		aty.overridePendingTransition(inAnima, outAnima);
	}

	public void showActivityAnima(Activity aty, Class clazz, int flags, int inAnima, int outAnima) {
		Intent i = new Intent(aty, clazz);
		i.setFlags(flags);
		aty.startActivity(i);
		aty.overridePendingTransition(inAnima, outAnima);
	}

	public void skipActivityAnima(Activity aty, Class clazz, Bundle bundle, int inAnima, int outAnima) {
		Intent i = new Intent(aty, clazz);
		i.putExtras(bundle);
		aty.startActivity(i);
		aty.overridePendingTransition(inAnima, outAnima);
		aty.finish();
	}

	public void showActivityAnima(Activity aty, Class clazz, int inAnima, int outAnima) {
		Intent i = new Intent(aty, clazz);
		aty.startActivity(i);
		aty.overridePendingTransition(inAnima, outAnima);
	}

	public void showActivityAnima(Activity aty, Class clazz, int flags, Bundle bundle, int inAnima, int outAnima) {
		Intent i = new Intent(aty, clazz);
		i.putExtras(bundle);
		i.setFlags(flags);
		aty.startActivity(i);
		aty.overridePendingTransition(inAnima, outAnima);
	}

	public void showActivity(Context aty, Intent intent) {
		aty.startActivity(intent);
	}
	
}
