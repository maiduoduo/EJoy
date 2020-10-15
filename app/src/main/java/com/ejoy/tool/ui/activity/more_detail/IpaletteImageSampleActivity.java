package com.ejoy.tool.ui.activity.more_detail;

import android.graphics.Color;
import android.view.View;
import android.widget.SeekBar;

import com.ejoy.tool.R;
import com.ejoy.tool.ui.base.base_activity.IBaseActivity;
import com.kongzue.baseframework.interfaces.DarkStatusBarTheme;
import com.kongzue.baseframework.interfaces.Layout;
import com.kongzue.dialog.util.BlurView;
import com.module.iviews.paletteimageview.IPaletteImageView;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @ClassName:  IpaletteImageSampleActivity
 * @Author: maiduoduo
 * @BLOG: https://blog.csdn.net/Maiduoudo
 * @Date: 2020/10/15
 * @des: 提取背景色动态参数设置示例
 */
@Layout(R.layout.activity_paletteimage_sample)
@DarkStatusBarTheme(true)
public class IpaletteImageSampleActivity extends IBaseActivity implements SeekBar.OnSeekBarChangeListener {
    @BindView(R.id.blur) BlurView blur;
    @BindView(R.id.palette) IPaletteImageView paletteImageView;
    @BindView(R.id.seek1) SeekBar mSeek1;
    @BindView(R.id.seek2) SeekBar mSeek2;
    @BindView(R.id.seek3) SeekBar mSeek3;
    @BindView(R.id.seek4) SeekBar mSeek4;

    @Override
    protected boolean isRegistSatusbarFullScreenTransluent() {
        return true;
    }

    @Override
    protected boolean isRegistSatusbarFontDark() {
        return true;
    }


    @Override
    public void initViews() {
        blur.setOverlayColor(Color.argb(100, 235, 235, 235));
        blur.setRadius(me, 0, 0);
        setDarkNavigationBarTheme(false);

        paletteImageView.setShadowColor(getResources().getColor(R.color.brown_300));
    }

    @Override
    public void initDatas() {

    }

    @Override
    public void setEvents() {
        mSeek1.setOnSeekBarChangeListener(this);
        mSeek2.setOnSeekBarChangeListener(this);
        mSeek3.setOnSeekBarChangeListener(this);
        mSeek4.setOnSeekBarChangeListener(this);
    }



    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        show(seekBar,progress);
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {}
    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {}


    private void show(SeekBar seekBar, int progress) {
        switch (seekBar.getId()){
            case R.id.seek1:
                paletteImageView.setPaletteRadius(progress);
                break;
            case R.id.seek2:
                paletteImageView.setPaletteShadowRadius(progress);
                break;
            case R.id.seek3:
                paletteImageView.setPaletteShadowOffset(progress,0);
                break;
            case R.id.seek4:
                paletteImageView.setPaletteShadowOffset(0,progress);
                break;
        }
    }

    @OnClick({
            R.id.btn_back
    })
    public void eventClick(View view) {
        switch (view.getId()) {
            case R.id.btn_back:
                finish();
                break;
        }
    }

    @Override
    protected void onPause() {
//        activity_main.removeAllViews();
        super.onPause();
    }
}
