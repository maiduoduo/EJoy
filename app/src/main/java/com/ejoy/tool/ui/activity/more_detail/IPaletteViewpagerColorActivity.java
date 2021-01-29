package com.ejoy.tool.ui.activity.more_detail;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.ejoy.tool.R;
import com.ejoy.tool.ui.base.base_activity.IBaseActivity;
import com.ejoy.tool.ui.data.adapter.IPaletteViewpagerColorAdapter;
import com.kongzue.baseframework.interfaces.DarkStatusBarTheme;
import com.kongzue.baseframework.interfaces.Layout;
import com.kongzue.dialog.util.BlurView;

import butterknife.BindView;
import butterknife.OnClick;


/**
 * @ClassName:  IPaletteViewpagerColorActivity
 * @Author: maiduoduo
 * @BLOG: https://blog.csdn.net/Maiduoudo
 * @Date: 2020/10/15
 * @des: 提取图片主题色-ViewPager嵌套Fragment设置背景
 */
@Layout(R.layout.activity_paletteimage_viewpager)
@DarkStatusBarTheme(true)
public class IPaletteViewpagerColorActivity extends IBaseActivity {
    @BindView(R.id.blur) BlurView blur;
    @BindView(R.id.viewpager) ViewPager mViewPager;

    private IPaletteViewpagerColorAdapter mAdapter;

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
        mAdapter = new IPaletteViewpagerColorAdapter(getSupportFragmentManager());
        mViewPager.setAdapter(mAdapter);
    }

    @Override
    public void initDatas() {

    }

    @Override
    public void setEvents() {

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



}
