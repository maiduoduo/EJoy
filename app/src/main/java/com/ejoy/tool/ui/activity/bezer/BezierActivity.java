package com.ejoy.tool.ui.activity.bezer;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.ejoy.tool.R;
import com.ejoy.tool.scaffold.utils.StatusBarTool;
import com.ejoy.tool.scaffold.view.BezierView;
import com.ejoy.tool.scaffold.view.MyDrawQuad;

/*
*
*
,------.     ,--.
|  .---'     |  | ,---.,--. ,--.
|  `--, ,--. |  || .-. |\  '  /
|  `---.|  '-'  /' '-' ' \   '
`------' `-----'  `---'.-'  /
                       `---'
*/

/**
 * CN:      BezierActivity
 * Author： JSYL-DINGCL (dingcl@jsyl.com.cn)
 * Date:   2019/10/10
 * Des:    贝塞尔曲线
 */
public class BezierActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        BezierView view = new BezierView(this);
        setContentView(R.layout.activity_bezer);
//        BezierView myDrawQuad = findViewById(R.id.MyDrawQuad);
//        myDrawQuad.init(this);
//        MyDrawQuad myDrawQuad = new MyDrawQuad(this);
        loadBlurAndSetStatusBar();
    }

  /*  @Override
    protected int getContentViewId() {
        return R.layout.activity_bezer;
    }

    @Override
    protected void initView(View mRootView) {
        loadBlurAndSetStatusBar();
        BezerView view = new BezerView(this);
    }

    @Override
    protected boolean isSetStatusBarBg() {
        return false;
    }




    @Override
    public BasePresenter getPresenter() {
        return null;
    }



*/



    /**
     * 设置毛玻璃效果和沉浸状态栏
     */
    private void loadBlurAndSetStatusBar() {
        //用来设置整体下移，状态栏沉浸
//        StatusBarTool.setRootViewFitsSystemWindows(this, false);
////        StatusBarUtil.setTranslucent(MainActivity.this, 0);
//        StatusBarTool.setTranslucentStatus(MainActivity.this);//透明状态栏
        //黑色字体
        StatusBarTool.setStatusBarDarkTheme(BezierActivity.this, false);
        //黑色字体
//        StatusBarTool.setStatusBarDarkTheme(MainActivity.this, true);
        //设置白色字体，其他背景
//        StatusBarTool.setStatusBarColor(MainActivity.this, Color.parseColor("#58C087"));//设置背景颜色

    }




}
