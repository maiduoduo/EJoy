package com.ejoy.tool.ui.activity.more_detail;

import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.v4.widget.NestedScrollView;
import android.util.Log;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.ejoy.tool.R;
import com.ejoy.tool.scaffold.utils.StatusBarTool;
import com.ejoy.tool.ui.base.base_activity.IBaseActivity;
import com.kongzue.baseframework.interfaces.DarkStatusBarTheme;
import com.kongzue.baseframework.interfaces.Layout;
import com.module.ires.bean.utils.EViewUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

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
 * CN:      ITopBarColorChangeActivity
 * Author： JSYL-DINGCL (dingcl@jsyl.com.cn)
 * Date:   2020/3/2
 * Des:    TODO:顶部栏颜色渐变
 */
@Layout(R.layout.activity_itopbarcolorchange)
//@DarkStatusBarTheme(false)
public class ITopBarColorChangeActivity extends IBaseActivity {

    @BindView(R.id.flTopLayout)
    FrameLayout mFlTopLayout;
    @BindView(R.id.appBarLayout)
    AppBarLayout mAppBarLayout;
    @BindView(R.id.tvTopbarColorChangeValue)
    TextView mTvTopbarColorChangeValue;
    @BindView(R.id.n_scroll_view)
    NestedScrollView mNScrollView;

    @Override
    protected boolean isRegistSatusbarFullScreenTransluent() {
        return true;
    }

    @Override
    public void initViews() {
        setDarkNavigationBarTheme(false);
        setTranslucentStatus(true);
//        EViewUtils.setImmersionStateMode(this);
    }

    @Override
    public void initDatas() {

    }

    @Override
    public void setEvents() {
        mAppBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                float percent = Float.valueOf(Math.abs(verticalOffset)) / Float.valueOf(appBarLayout.getTotalScrollRange());
                //第一种
                int toolbarHeight = appBarLayout.getTotalScrollRange();
                int dy = Math.abs(verticalOffset);
                Log.e(_TAG, "onOffsetChanged [dy]: "+dy);
                if (dy <= toolbarHeight) {
                    Log.e(_TAG, "onOffsetChanged ----------[dy <= toolbarHeight] --------");
                    float scale = (float) dy / toolbarHeight;
                    float alpha = scale * 255;
                    mFlTopLayout.setBackgroundColor(Color.argb((int) alpha, 255, 255, 255));
                    mTvTopbarColorChangeValue.setText("setBackgroundColor(Color.argb((int) "+(int) alpha+", 255, 255, 255))\n"
                                                      +"mFLayout.setAlpha("+percent+")");
                    StatusBarTool.setStatusBarDarkTheme(me, false);
                }
                if (dy > 220){//这个补充值根据实际情况进行设定
                    Log.e(_TAG, "onOffsetChanged ----------[dy > 220] --------");
                    StatusBarTool.setStatusBarDarkTheme(me, true);
                }


                //第二种
                // mFLayout.setAlpha(percent);
            }
        });
    }

}
