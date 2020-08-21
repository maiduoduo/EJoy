package com.ejoy.tool.ui.activity.datetime.timeline;
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

import android.graphics.Color;
import android.view.View;
import android.widget.FrameLayout;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.ejoy.tool.R;
import com.ejoy.tool.common.bean.RecyclerviewTimeLineEntity;
import com.ejoy.tool.scaffold.utils.StatusBarTool;
import com.ejoy.tool.ui.base.base_activity.IBaseActivity;
import com.kongzue.baseframework.interfaces.DarkStatusBarTheme;
import com.kongzue.baseframework.interfaces.Layout;

import java.util.List;

import butterknife.BindView;


/**
 * @ClassName: ITimeLineCalendarActivity
 * @Author: bsj-dingcl
 * @Email: dingchenglei@h4kit.com
 * @Date: 2020/07/20
 * @des: 时间线+日历
 */
@Layout(R.layout.activity_timeline_calendar)
@DarkStatusBarTheme(true)
public class ITimeLineCalendarActivity extends IBaseActivity {

    @BindView(R.id.title)
    FrameLayout title;

    private BaseQuickAdapter mTimeLineAdapter;
    private List<RecyclerviewTimeLineEntity> mTimeLineData;

    @Override
    protected boolean isRegistSatusbarFullScreenTransluent() {
        return true;
    }

    @Override
    public void initViews() {
        StatusBarTool.setRootViewFitsSystemWindows(me, true);
        StatusBarTool.setStatusBarColor(me, Color.parseColor(baseThemeColor));
    }

    @Override
    public void initDatas() {
    }

    @Override
    public void setEvents() {

    }

    public void ivBack(View view) {
        finishActivity();
    }



}
