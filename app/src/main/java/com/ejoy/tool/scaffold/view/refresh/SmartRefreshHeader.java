package com.ejoy.tool.scaffold.view.refresh;

import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ejoy.tool.R;
import com.scwang.smartrefresh.layout.api.RefreshHeader;
import com.scwang.smartrefresh.layout.api.RefreshKernel;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.constant.RefreshState;
import com.scwang.smartrefresh.layout.constant.SpinnerStyle;

/**
 * SmartRefreshLayout 的自定义下拉刷新 Header
 */
public class SmartRefreshHeader extends LinearLayout implements RefreshHeader {
    private ImageView mImage;
    private AnimationDrawable pullDownAnim;
    private AnimationDrawable refreshingAnim;
    private TextView tv_refresh_header;
    private boolean hasSetPullDownAnim = false;

    public SmartRefreshHeader(Context context) {
        this(context, null, 0);
    }

    public SmartRefreshHeader(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SmartRefreshHeader(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        View view = View.inflate(context, R.layout.layout_smart_refreshheader, this);
        mImage = (ImageView) view.findViewById(R.id.header_iv);
        tv_refresh_header = (TextView) view.findViewById(R.id.header_tv);
    }

    @NonNull
    @Override
    public View getView() {
        return this;
    }

    @Override
    public SpinnerStyle getSpinnerStyle() {
        return SpinnerStyle.Translate;
    }

    @Override
    public void onStartAnimator(RefreshLayout layout, int height, int extendHeight) {

    }

    /**
     * 状态改变时调用。
     * @param refreshLayout
     * @param oldState
     * @param newState
     */
    @Override
    public void onStateChanged(RefreshLayout refreshLayout, RefreshState oldState, RefreshState newState) {
        switch (newState) {
            case PullDownToRefresh: //下拉刷新开始。正在下拉还没松手时调用
                //每次重新下拉时，将图片资源重置
                mImage.setImageResource(R.mipmap.smart_image);
                tv_refresh_header.setText("下拉可以刷新");
                break;
            case Refreshing: //正在刷新。只调用一次
                //状态切换为正在刷新状态时
                mImage.setImageResource(R.drawable.anim_header_smart_refresh);
                refreshingAnim = (AnimationDrawable) mImage.getDrawable();
                tv_refresh_header.setText("正在刷新...");
                refreshingAnim.start();
                break;
            case ReleaseToRefresh:

                break;
        }
    }

    /**
     * 下拉过程中不断调用此方法。
     */
    @Override
    public void onPullingDown(float percent, int offset, int headerHeight, int extendHeight) {
        // 下拉的百分比小于100%时，不断调用 setScale 方法改变图片大小
        if (percent < 1) {
            mImage.setScaleX(percent);
            mImage.setScaleY(percent);

            //是否执行过翻跟头动画的标记
            if (hasSetPullDownAnim) {
                hasSetPullDownAnim = false;
            }
        }

        //当下拉的高度达到Header高度100%时，开始加载
        if (percent >= 1.0) {
            if (!hasSetPullDownAnim) {
                tv_refresh_header.setText("松开立即刷新...");
                mImage.setImageResource(R.mipmap.smart_image2);
            }
        }
    }

    /**
     * 动画结束后调用
     */
    @Override
    public int onFinish(RefreshLayout layout, boolean success) {
        // 结束动画
        if (pullDownAnim != null && pullDownAnim.isRunning()) {
            pullDownAnim.stop();
        }
        if (refreshingAnim != null && refreshingAnim.isRunning()) {
            refreshingAnim.stop();
        }
        //重置状态
        hasSetPullDownAnim = false;
        return 0;
    }

    @Override
    public void onReleasing(float percent, int offset, int headerHeight, int extendHeight) {

    }

    @Override
    public void onRefreshReleased(RefreshLayout layout, int headerHeight, int extendHeight) {

    }

    @Override
    public void setPrimaryColors(int... colors) {

    }

    @Override
    public void onInitialized(RefreshKernel kernel, int height, int extendHeight) {

    }

    @Override
    public void onHorizontalDrag(float percentX, int offsetX, int offsetMax) {

    }

    @Override
    public boolean isSupportHorizontalDrag() {
        return false;
    }


}
