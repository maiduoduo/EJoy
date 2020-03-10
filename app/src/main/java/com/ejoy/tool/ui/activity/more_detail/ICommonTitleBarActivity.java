package com.ejoy.tool.ui.activity.more_detail;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

import com.ejoy.tool.R;
import com.ejoy.tool.ui.base.base_activity.IBaseActivity;
import com.kongzue.baseframework.interfaces.Layout;
import com.module.iviews.common.toolbar.RipAnimDrawable;

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
 * CN:      ICommonTitleBarActivity
 * Author： JSYL-DINGCL (dingcl@jsyl.com.cn)
 * Date:   2020/3/6
 * Des:    TODO:通用标题栏
 */
@Layout(R.layout.activity_icommon_titlebar)
public class ICommonTitleBarActivity extends IBaseActivity implements Toolbar.OnMenuItemClickListener {

    @BindView(R.id.ToolbarTitle) Toolbar toolbarTitle;

    @Override
    protected boolean isRegistSatusbarFullScreenTransluent() {
        return true;
    }

    @Override
    public void initViews() {
        setTranslucentStatus(true);
        // ToolBar
        initToolbar();
    }


    @Override
    public void initDatas() {

    }

    @Override
    public void setEvents() {

    }


    @Override
    public boolean onMenuItemClick(MenuItem item) {
        int id = item.getItemId();
        return true;
    }


    private void initToolbar() {
        RipAnimDrawable ripAnim = new RipAnimDrawable();
        ripAnim.setColor(getResources().getColor(R.color.cyan_600));
        ripAnim.setFluCount(0, 0, 0, 36);

        toolbarTitle.setBackgroundDrawable(ripAnim);
        toolbarTitle.inflateMenu(R.menu.menu_detail);
        toolbarTitle.setOnMenuItemClickListener(this);
        toolbarTitle.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
