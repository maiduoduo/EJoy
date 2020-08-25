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

import com.ejoy.tool.R;
import com.ejoy.tool.scaffold.utils.StatusBarTool;
import com.ejoy.tool.ui.base.base_activity.IBaseActivity;
import com.kongzue.baseframework.interfaces.DarkStatusBarTheme;
import com.kongzue.baseframework.interfaces.Layout;


/**
 * @ClassName: ITimeLineActivity
 * @Author: bsj-dingcl
 * @Email: dingchenglei@h4kit.com
 * @Date: 2020/07/20
 * @des: 时间线
 */
@Layout(R.layout.activity_timeline)
@DarkStatusBarTheme(true)
public class ITimeLineActivity extends IBaseActivity {

    @Override
    protected boolean isRegistSatusbarFullScreenTransluent() {
        return true;
    }

    @Override
    public void initViews() {
        StatusBarTool.setRootViewFitsSystemWindows(me,true);
        StatusBarTool.setStatusBarColor(me,Color.parseColor("#ff529aff"));
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

    /**
     * 日历+时间线
     * @param view
     */
    public void calendarTimeLine(View view) {
        jump(ITimeLineCalendarActivity.class);
    }

    /**
     * 自定义时间线（RecyclerView）
     * @param view
     */
    public void timeLIneRecyclerView(View view) {
        jump(ITimeLineRecyclerviewActivity.class);
    }

    /**
     * 仿「美柚」官网大事记时间线
     * @param view
     */
    public void myouTimeLIneRecyclerView(View view) {
    }
}
