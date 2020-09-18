package com.ejoy.tool.common.service;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;

/**
 * @ClassName: RestartAppService
 * @Author: bsj-dingcl
 * @Email: dingchenglei@h4kit.com
 * @Date: 2020/08
 * @des: 重启App的服务
 */
public class RestartAppService extends Service {
    //关闭应用后多久重新启动
    private static long stopDelayed = 5000;
    private Handler handler;
    private String packageName;
    public RestartAppService() {
        handler = new Handler();
    }


    @Override
    public int onStartCommand(final Intent intent, int flags, int startId) {
        packageName = intent.getStringExtra("packageName");
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent LaunchIntent = getPackageManager().getLaunchIntentForPackage(packageName);
                startActivity(LaunchIntent);
                RestartAppService.this.stopSelf();
            }
        }, stopDelayed);
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
