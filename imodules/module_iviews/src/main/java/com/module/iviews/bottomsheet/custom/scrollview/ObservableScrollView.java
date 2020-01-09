package com.module.iviews.bottomsheet.custom.scrollview;
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
import android.os.Handler;
import android.os.Message;
import android.support.v4.widget.NestedScrollView;
import android.util.AttributeSet;

/**
 * CN:      ObservableScrollView
 * Author： JSYL-DINGCL (dingcl@jsyl.com.cn)
 * Date:   2019/12/27
 * Des:    TODO:NestedScrollView及事件监听
 */
public class ObservableScrollView extends NestedScrollView {

    private OnScrollStatusListener onScrollStatusListener;

    public ObservableScrollView(Context context) {
        super(context);
    }

    public ObservableScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ObservableScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);
        if (onScrollStatusListener != null) {
            onScrollStatusListener.onScrolling();
            mHandler.removeCallbacksAndMessages(null);
            mHandler.sendEmptyMessageDelayed(0x01, 200);
        }
    }

    public void setOnScrollStatusListener(OnScrollStatusListener onScrollStatusListener) {
        this.onScrollStatusListener = onScrollStatusListener;
    }

    private Handler mHandler = new Handler() {

        @Override public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 0x01:
                    if (onScrollStatusListener != null) {
                        onScrollStatusListener.onScrollStop();
                    }
                    break;
            }
        }
    };

    @Override protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        mHandler.removeCallbacksAndMessages(null);
    }

    public interface OnScrollStatusListener {
        void onScrollStop();

        void onScrolling();
    }
}
