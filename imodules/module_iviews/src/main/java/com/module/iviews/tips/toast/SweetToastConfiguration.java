package com.module.iviews.tips.toast;

import android.support.annotation.NonNull;
import android.view.WindowManager;

/**
 * SweetToast关联的属性集合类
 *
 */

public class SweetToastConfiguration {
    private static final String TAG1 = "SweetTip";
    private WindowManager.LayoutParams mParams = null;
    private long duration = 0;

    public WindowManager.LayoutParams getParams() {
        return mParams;
    }

    public void setParams(@NonNull WindowManager.LayoutParams mParams) {
        this.mParams = mParams;
    }

    public long getDuration() {
        return duration;
    }

    public void setDuration(long duration) {
        this.duration = duration;
    }
}