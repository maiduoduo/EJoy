package com.ejoy.tool.ui.activity.more_detail;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import com.ejoy.tool.R;
import com.ejoy.tool.ui.base.base_activity.IBaseActivity;
import com.kongzue.baseframework.interfaces.Layout;
import com.kongzue.dialog.util.BlurView;
import com.module.iviews.common.blur.BlurredView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

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

/**
 * CN:      ICommonBaseBlurViewActivity
 * Author： JSYL-DINGCL (dingcl@jsyl.com.cn)
 * Date:   2020/3/5
 * Des:    TODO:Android的动态模糊图像视图.-基本使用
 */
@Layout(R.layout.activity_base_blurview)
public class ICommonBaseBlurViewActivity extends IBaseActivity {

    @BindView(R.id.blur)
    BlurView blur;

    /**
     * Blurredview
     */
    @BindView(R.id.activity_main_blurredview)
    BlurredView mBlurredView;

    /**
     * 进度条SeekBar
     */
    @BindView(R.id.activity_main_seekbar)
    SeekBar mSeekbar;

    /**
     * 显示进度的文字
     */
    @BindView(R.id.activity_main_progress_tv)
    TextView mProgressTv;



    @Override
    protected boolean isRegistSatusbarFullScreenTransluent() {
        return true;
    }

    @Override
    public void initViews() {
        // 可以在代码中使用setBlurredImg()方法指定需要模糊的图片
        mBlurredView.setBlurredImg(getResources().getDrawable(R.mipmap.img_bg_picture));
    }

    @Override
    public void initDatas() {
        blur.setOverlayColor(Color.argb(200, 235, 235, 235));
        blur.setRadius(me, 0, 0);
    }

    /**
     * 处理seekbar滑动事件
     */
    @Override
    public void setEvents() {
        mSeekbar.setMax(100);
        mSeekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                mBlurredView.setBlurredLevel(progress);
                mProgressTv.setText(String.valueOf(progress));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    @OnClick({
            R.id.box_table_child
    })
    public void bindClick(View view) {
        switch (view.getId()) {
            case R.id.box_table_child:
                finish();
                break;
        }
    }

}
