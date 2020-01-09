package com.module.iviews.view.widget;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.module.iviews.R;


/**
 * 发布按钮弹出菜单
 */
public class IPublishDialog extends Dialog {
    private RelativeLayout mPublishMainRlmian;
    private LinearLayout mPublishDialogFabu;
    private LinearLayout mPublishDialogHuishou;
    private LinearLayout mPublishDialogPinggu;
    private LinearLayout mPublishDialogLlBt;
    private ImageView mPublishDialogIvMenu;
    public static Dialog dialog;


    private Handler mHandler;
    private Context mContext;

    public IPublishDialog(Context context) {
        this(context, R.style.IPublishdialog_style);
        //这里需要注意使用了一个自定义的Style代码会在下面给出

    }

    public IPublishDialog(Context context, int themeResId) {
        super(context, themeResId);
        this.mContext=context;
        init();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //全屏
        WindowManager.LayoutParams params = getWindow().getAttributes();
        params.height = ViewGroup.LayoutParams.MATCH_PARENT;
        params.width = ViewGroup.LayoutParams.MATCH_PARENT;
        getWindow().setAttributes(params);
        dialog=this;

    }

    /**
     * 初始化
     */
    private void init() {
        setContentView(R.layout.layout_dialog_publish);
        mPublishMainRlmian = findViewById(R.id.publish_main_rlmian);//主布局
        mPublishDialogFabu = findViewById(R.id.Publish_dialog_fabu);//发布
        mPublishDialogHuishou = findViewById(R.id.publish_dialog_huishou);//官方回收
        mPublishDialogPinggu = findViewById(R.id.publish_dialog_pinggu);//评估
        mPublishDialogLlBt = findViewById(R.id.publish_dialog_llBt);
        mPublishDialogIvMenu = findViewById(R.id.publish_dialog_ivMenu);//退出按钮x

        mHandler=new Handler();

        mPublishDialogLlBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                outDia();
                //点击底部按钮区域退出dialog
            }
        });

        mPublishMainRlmian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                outDia();
                //设置点击非控件区域也将退出dialog
            }
        });

    }

    @Override
    public void show() {
        super.show();
        goinDia();
    }
    /**
     * 进入dialog
     */
    private void goinDia() {
        mPublishDialogFabu.setVisibility(View.INVISIBLE);
        mPublishDialogHuishou.setVisibility(View.INVISIBLE);
        mPublishDialogPinggu.setVisibility(View.INVISIBLE);
        //首先把发布回收评估三个控件设置为不可见
        mPublishMainRlmian.setAnimation(AnimationUtils.loadAnimation(mContext,R.anim.main_go_in));
        //然后设置主布局的动画
        mPublishDialogIvMenu.setAnimation(AnimationUtils.loadAnimation(mContext,R.anim.main_rotate_right));
        //这里设置底部退出按钮的动画 这里是用了一个rotate动画
        mPublishDialogFabu.setVisibility(View.VISIBLE);
        //底部按钮动画执行过之后把发布设置为可见
        mPublishDialogFabu.setAnimation(AnimationUtils.loadAnimation(mContext,R.anim.mian_shoot_in));
        //然后让他执行mian_shoot_in动画这个动画里定义的是平移动画
        //在这里设置之后如果你同时设置其他两个评估和回收动画着这三个动画会同时从屏幕的底部向上平移
        //而我们想实现的效果是挨个向上平移这里 使用到了定时器handler开启一个线程定时100毫秒启动这个线程
        // 这样就可以达到挨个向上平移的效果
        // mHandler.postDelayed开启一个定时任务
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                mPublishDialogHuishou.setVisibility(View.VISIBLE);
                mPublishDialogHuishou.setAnimation(AnimationUtils.loadAnimation(mContext,R.anim.mian_shoot_in));
            }
        },100);

        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                mPublishDialogPinggu.setVisibility(View.VISIBLE);
                mPublishDialogPinggu.setAnimation(AnimationUtils.loadAnimation(mContext,R.anim.mian_shoot_in));


            }
        },200);
        //这里需要设置成两百不然会出现和评估同时向上滑动
    }

    /**
     * 退出Dialog
     */
    public void outDia(){
        mPublishMainRlmian.setAnimation(AnimationUtils.loadAnimation(mContext,R.anim.main_go_out));

        mPublishDialogIvMenu.setAnimation(AnimationUtils.loadAnimation(mContext,R.anim.main_rotate_left));
        //设置退出按钮从右向左旋转
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
               dismiss();
            }
        },500);
        //这里设置了一个定时500毫秒的定时器来执行dismiss();来关闭Dialog 我们需要在500毫秒的时间内完成对控件动画的设置
        mPublishDialogFabu.setAnimation(AnimationUtils.loadAnimation(mContext,R.anim.mian_shoot_out));
        //然后设置发布从上向下平移动画
        mPublishDialogFabu.setVisibility(View.INVISIBLE);
        //将其设置为不可见
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                mPublishDialogHuishou.setAnimation(AnimationUtils.loadAnimation(mContext,R.anim.mian_shoot_out));
                mPublishDialogHuishou.setVisibility(View.INVISIBLE);
            }
        },100);
        //同理使用定时器将评估和回向下平移 这里需要注意的是评估和回收的定时器时间的设置不能大于关闭Dialog的定时时间
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                mPublishDialogPinggu.setAnimation(AnimationUtils.loadAnimation(mContext,R.anim.mian_shoot_out));
                mPublishDialogPinggu.setVisibility(View.INVISIBLE);
            }
        },150);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(isShowing()){
            outDia();
            //这里重写了onKeyDown方法捕获了back键的执行事件 点击back将退出Dialog
            return true;
        }else {
            return super.onKeyDown(keyCode, event);
        }

    }
//这三个方法设置了三个控件的点击事件并返回一个PublishDialog 这里需要一个OnClickListener的参数


    public IPublishDialog setFabuClickListener(View.OnClickListener clickListener){
        mPublishDialogFabu.setOnClickListener(clickListener);
        return this;

    }
    public IPublishDialog setHuishouClickListener(View.OnClickListener clickListener){
        mPublishDialogHuishou.setOnClickListener(clickListener);
        return this;

    }
    public IPublishDialog setPingguClickListener(View.OnClickListener clickListener){
        mPublishDialogPinggu.setOnClickListener(clickListener);
        return this;

    }

}
