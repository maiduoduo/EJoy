package com.ejoy.tool.common.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.NetworkInfo.State;

import com.ejoy.tool.app.App;

public class NetChangedReceiver extends BroadcastReceiver {

	public static String lastGrpID = "";
	private static boolean networkdown = false;
	private static String mobileSubTypeName = "";
	private static String networkTypeName = "";
	private final String TAG = "NetChangedReceiver";
	private SharedPreferences mSharedPreferences;

	static {
		ConnectivityManager connMgr = (ConnectivityManager)App.getAppContext()
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo activeInfo = connMgr.getActiveNetworkInfo();
		if (activeInfo != null) {
			networkTypeName = activeInfo.getTypeName();
			if ("mobile".equalsIgnoreCase(networkTypeName)) {
				mobileSubTypeName = activeInfo.getSubtypeName();
			}
		}
	}

	@Override
	public void onReceive(Context context, Intent intent) {

		mSharedPreferences = context.getSharedPreferences("com.zed.xst", context.MODE_PRIVATE);
		StringBuilder builder = new StringBuilder("NetChangedReceiver#onReceive");
		String action = intent.getAction();
		if (action.equals("android.net.conn.CONNECTIVITY_CHANGE")) {
			builder.append(" android.net.conn.CONNECTIVITY_CHANGE");
			ConnectivityManager connMgr = (ConnectivityManager) context
					.getSystemService(Context.CONNECTIVITY_SERVICE);

			State mobileState = null;
			boolean isMobileAvalilable = false;
			boolean isMobileConn = false;
			boolean isMobile = false; //
			NetworkInfo mobileInfo = connMgr.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
			if (mobileInfo != null) {
				mobileState = mobileInfo.getState();
				isMobileAvalilable = mobileInfo.isAvailable();
				isMobileConn = mobileInfo.isConnected();
				isMobile = mobileInfo.isConnectedOrConnecting();
			}
			// isWifiAvalilable = true, isWifiConn = true, isWifi = true, when
			// wifi connected but can not go on the web
			State wifiState = null;
			boolean isWifiAvalilable = false;
			boolean isWifiConn = false;
			boolean isWifi = false;// wifi����
			NetworkInfo wifiInfo = connMgr.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
			if (wifiInfo != null) {
				wifiState = wifiInfo.getState();
				isWifiAvalilable = wifiInfo.isAvailable();
				isWifiConn = wifiInfo.isConnected();
				isWifi = wifiInfo.isConnectedOrConnecting();
			}
			// active network info
			NetworkInfo activeInfo = connMgr.getActiveNetworkInfo();
			builder.append(" NetWorkInfo" + "activeInfo:" + (activeInfo == null ? "null" : activeInfo.toString()));
			if (activeInfo != null) {
				String typeName = activeInfo.getTypeName();
				State activeState = activeInfo.getState();
				if (!networkTypeName.equals(typeName)) {

//					if (!IndexFragment.onChanged) {
//						if (mSharedPreferences != null) {
//							IndexFragment.onNetChanged(true);
//						}
//						Toast.makeText(context, "重新连接通讯服务器",Toast.LENGTH_SHORT).show();
//					}else {
//						IndexFragment.onNetChanged(false);
//					}
				} else {
					if ("mobile".equalsIgnoreCase(networkTypeName)) {
						if (!mobileSubTypeName.equalsIgnoreCase(activeInfo.getSubtypeName())) {
							if (!networkdown) {
								networkdown = true;
								mobileSubTypeName = activeInfo.getSubtypeName();
							}
						}
					}
					if (networkdown) {
						if ("mobile".equalsIgnoreCase(networkTypeName)) {
							mobileSubTypeName = activeInfo.getSubtypeName();
						}
//						if (!IndexFragment.onChanged) {
//							if (mSharedPreferences != null) {
//							}
//							Toast.makeText(context, "重新连接通讯服务器",Toast.LENGTH_SHORT).show();
//						}
					}
				}
				networkdown = false;
				networkTypeName = typeName;
				Intent broadcastIntent = new Intent();
				broadcastIntent.setAction("com.zed3.sipua_network_changed");
				broadcastIntent.putExtra("network_state", 1);
				context.sendBroadcast(broadcastIntent);
			} else {
				if (!networkdown) {
				}
				networkdown = true;
				Intent broadcastIntent = new Intent();
				broadcastIntent.setAction("com.zed3.sipua_network_changed");
				broadcastIntent.putExtra("network_state", 0);
				context.sendBroadcast(broadcastIntent);
			}
		}

	}

	private static NetChangedReceiver sReceiver;

	public synchronized static void registerSelf() {

		if (sReceiver == null) {
			// --------------------------
			IntentFilter infilter = new IntentFilter();
			infilter.addAction("android.net.wifi.WIFI_STATE_CHANGED");
			infilter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
			infilter.addAction(Intent.ACTION_SCREEN_ON);
			infilter.addAction(Intent.ACTION_SCREEN_OFF);

			NetChangedReceiver receiver = new NetChangedReceiver();
			App.getAppContext().registerReceiver(receiver, infilter);
			sReceiver = receiver;
		}

	}

	public synchronized static void unregisterSelf() {
		if (sReceiver != null) {
			try {
				App.getAppContext().unregisterReceiver(sReceiver);
			} catch (Exception e) {
			}
			sReceiver = null;
		}
	}



}
