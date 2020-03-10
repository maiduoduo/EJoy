package com.ejoy.tool.ui.activity.more_detail;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.ejoy.tool.R;
import com.ejoy.tool.ui.base.base_activity.IBaseActivity;
import com.kongzue.baseframework.interfaces.Layout;
import com.module.iviews.common.toolbar.RipAnimDrawable;

import net.qiujuer.genius.ui.widget.Button;

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
 * CN:      ICommonButtonActivity
 * Author： JSYL-DINGCL (dingcl@jsyl.com.cn)
 * Date:   2020/3/6
 * Des:    TODO:通用Button
 */
@Layout(R.layout.activity_icommon_button)
public class ICommonButtonActivity extends IBaseActivity implements Toolbar.OnMenuItemClickListener {

    @BindView(R.id.Toolbar)
    Toolbar toolbarTitle;

    @Override
    protected boolean isRegistSatusbarFullScreenTransluent() {
        return true;
    }

    @Override
    public void initViews() {
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

    @OnClick({
            R.id.btn,
            R.id.btn1,
            R.id.btn2,
            R.id.btn3,
            R.id.btn4,
            R.id.btn5,
            R.id.btn6,
            R.id.btn7,
    })
    public void bindViewclick(View view){
        switch (view.getId()){
            case R.id.btn:
                break;
            case R.id.btn1:
                break;
            case R.id.btn2:
                break;
            case R.id.btn3:
                break;
            case R.id.btn4:
                break;
            case R.id.btn5:
                break;
            case R.id.btn6:
                break;
            case R.id.btn7:
                break;
        }
    }


    private void initToolbar() {
        RipAnimDrawable ripAnim = new RipAnimDrawable();
        ripAnim.setColor(getResources().getColor(R.color.cyan_600));
        ripAnim.setFluCount(0, 0, 0, 20);

        toolbarTitle.setBackgroundDrawable(ripAnim);
//        toolbarTitle.inflateMenu(R.menu.menu_detail);
        toolbarTitle.setOnMenuItemClickListener(this);
        toolbarTitle.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

}
