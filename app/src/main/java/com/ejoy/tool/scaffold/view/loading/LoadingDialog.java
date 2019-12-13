package com.ejoy.tool.scaffold.view.loading;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;

import com.ejoy.tool.R;

/**
 * 加载中动画
 */
public class LoadingDialog extends Dialog {
    private Context mContext;
    public View mRootView;
    ImageView mLoading;
    private AnimationDrawable mAnimationDrawable;

    public LoadingDialog(Context context) {
        super(context, R.style.Transparent_Dialog);
        init(context);
    }

    public void init(Context context) {
        mContext = context;
        mRootView = LayoutInflater.from(context).inflate(R.layout.dialog_loading_ball, null);
        setContentView(mRootView);
        initView();
        initAnimation();
    }

    public void initAnimation() {
        Window window = getWindow();
//        window.setWindowAnimations(R.style.Dialog_Scale_Animation);
        window.setGravity(Gravity.CENTER);
    }

    public void initView() {
        mLoading = mRootView.findViewById(R.id.loading);
        mAnimationDrawable = (AnimationDrawable) mLoading.getDrawable();
        mAnimationDrawable.start();
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (mAnimationDrawable != null && mAnimationDrawable.isRunning()) {
            mAnimationDrawable.stop();
        }
    }
}
