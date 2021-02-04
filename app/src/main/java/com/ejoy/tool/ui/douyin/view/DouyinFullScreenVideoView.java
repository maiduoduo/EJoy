package com.ejoy.tool.ui.douyin.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.VideoView;


/**
 * @ClassName:  DouyinFullScreenVideoView
 * @Author: maiduoduo
 * @BLOG: https://blog.csdn.net/Maiduoudo
 * @Date: 2021/2/2
 * @des: 全屏播放Videoview
 */
public class DouyinFullScreenVideoView extends VideoView {

    public DouyinFullScreenVideoView(Context context) {
        super(context);
    }

    public DouyinFullScreenVideoView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public DouyinFullScreenVideoView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        setMeasuredDimension(getDefaultSize(0, widthMeasureSpec), getDefaultSize(0, heightMeasureSpec));
    }
}
