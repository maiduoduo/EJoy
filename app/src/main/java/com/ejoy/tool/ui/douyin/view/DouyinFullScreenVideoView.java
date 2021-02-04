package com.ejoy.tool.ui.douyin.view;

import android.content.Context;
import android.media.MediaMetadataRetriever;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.RelativeLayout;
import android.widget.VideoView;


/**
 * @ClassName:  DouyinFullScreenVideoView
 * @Author: maiduoduo
 * @BLOG: https://blog.csdn.net/Maiduoudo
 * @Date: 2021/2/2
 * @des: 全屏播放Videoview
 */
public class DouyinFullScreenVideoView extends VideoView {

//    //最终的视频资源宽度
//    private int mVideoWidth=480;
//    //最终视频资源高度
//    private int mVideoHeight=480;
//    //视频资源原始宽度
//    private int videoRealW=1;
//    //视频资源原始高度
//    private int videoRealH=1;
//
//    public DouyinFullScreenVideoView(Context context) {
//        super(context);
//        // TODO Auto-generated constructor stub
//    }
//
//    public DouyinFullScreenVideoView(Context context, AttributeSet attrs) {
//        super(context, attrs);
//        // TODO Auto-generated constructor stub
//    }
//
//    public DouyinFullScreenVideoView(Context context, AttributeSet attrs, int defStyle) {
//        super(context, attrs, defStyle);
//        // TODO Auto-generated constructor stub
//    }
//    @Override
//    public void setVideoPath(String path) {
//        super.setVideoPath(path);
//
//        MediaMetadataRetriever retr = new MediaMetadataRetriever();
//        retr.setDataSource(path);
//        String height = retr.extractMetadata(MediaMetadataRetriever.METADATA_KEY_VIDEO_HEIGHT); // 视频高度
//        String width = retr.extractMetadata(MediaMetadataRetriever.METADATA_KEY_VIDEO_WIDTH); // 视频宽度
//        try {
//            videoRealH=Integer.parseInt(height);
//            videoRealW=Integer.parseInt(width);
//        } catch (NumberFormatException e) {
//            Log.e("----->" + "VideoView", "setVideoPath:" + e.toString());
//        }
//    }
//
//    @Override
//    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
//        int width = getDefaultSize(0, widthMeasureSpec);
//        int height = getDefaultSize(0, heightMeasureSpec);
//        if(height>width){
//            //竖屏
//            if(videoRealH>videoRealW){
//                //如果视频资源是竖屏
//                //占满屏幕
//                mVideoHeight=height;
//                mVideoWidth=width;
//            }else {
//                //如果视频资源是横屏
//                //宽度占满，高度保存比例
//                mVideoWidth=width;
//                float r=videoRealH/(float)videoRealW;
//                mVideoHeight= (int) (mVideoWidth*r);
//            }
//        }else {
//            //横屏
//            if(videoRealH>videoRealW){
//                //如果视频资源是竖屏
//                //宽度占满，高度保存比例
//                mVideoHeight=height;
//                float r=videoRealW/(float)videoRealH;
//                mVideoWidth= (int) (mVideoHeight*r);
//            }else {
//                //如果视频资源是横屏
//                //占满屏幕
//                mVideoHeight=height;
//                mVideoWidth=width;
//            }
//        }
//        if(videoRealH==videoRealW&&videoRealH==1){
//            //没能获取到视频真实的宽高，自适应就可以了，什么也不用做
//            super.onMeasure(widthMeasureSpec,heightMeasureSpec);
//        }else {
//            setMeasuredDimension(mVideoWidth, mVideoHeight);
//        }
//    }
//    @Override
//    public boolean onTouchEvent(MotionEvent ev) {
//        //屏蔽触摸点击事件
//        return true;
//    }







    private int width = 0;
    private int height = 0;

    public DouyinFullScreenVideoView(Context context) {
        super(context);
    }

    public DouyinFullScreenVideoView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void setMeasure(int width, int height) {
        this.width = width;
        this.height = height;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        // 默认高度，为了自动获取到focus
        int width = MeasureSpec.getSize(widthMeasureSpec);
        int height = width;
        // 这个之前是默认的拉伸图像
        if (this.width > 0 && this.width < 900 && this.height > 0 && this.height < 900) {
//            width = this.width;
            width = getDefaultSize(0, widthMeasureSpec);
            height = this.height;
        }else if (this.width >=  1000&& this.height >= 1000) {
            width = getDefaultSize(0, widthMeasureSpec);
            height = getDefaultSize(0, heightMeasureSpec);
        }
        setMeasuredDimension(width, height);
    }
















//    public DouyinFullScreenVideoView(Context context) {
//        super(context);
//    }
//
//    public DouyinFullScreenVideoView(Context context, AttributeSet attrs) {
//        super(context, attrs);
//    }
//
//    public DouyinFullScreenVideoView(Context context, AttributeSet attrs, int defStyleAttr) {
//        super(context, attrs, defStyleAttr);
//    }
//
//    @Override
//    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
//        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
//        int width = getDefaultSize(0, widthMeasureSpec);
//        int height = getDefaultSize(0, heightMeasureSpec);
//        setMeasuredDimension(width, height);
//    }








//    public DouyinFullScreenVideoView(Context context) {
//        super(context);
//    }
//
//    public DouyinFullScreenVideoView(Context context, AttributeSet attrs) {
//        super(context, attrs);
//    }
//
//    public DouyinFullScreenVideoView(Context context, AttributeSet attrs, int defStyleAttr) {
//        super(context, attrs, defStyleAttr);
//    }
//
//    @Override
//    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
////        setMeasuredDimension(getDefaultSize(0, widthMeasureSpec), getDefaultSize(0, heightMeasureSpec));
//        setMeasuredDimension(getDefaultSize(0, widthMeasureSpec), getDefaultSize(0, heightMeasureSpec));
//    }
}
