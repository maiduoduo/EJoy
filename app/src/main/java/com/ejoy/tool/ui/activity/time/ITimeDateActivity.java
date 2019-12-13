package com.ejoy.tool.ui.activity.time;

import android.graphics.Color;
import android.text.TextUtils;
import android.view.View;
import android.widget.NumberPicker;
import android.widget.Toast;

import com.ejoy.tool.R;
import com.ejoy.tool.common.helper.InfoDialog.IDialog;
import com.ejoy.tool.scaffold.utils.StatusBarTool;
import com.ejoy.tool.scaffold.view.loading.LoadingDialog;
import com.ejoy.tool.ui.activity.MainActivity;
import com.ejoy.tool.ui.base.base_activity.BaseActivity;
import com.ejoy.tool.ui.mvp.base.BasePresenter;
import com.imaiduoduo.datetime.DatePickDialog;
import com.maple.msdialog.ActionSheetDialog;
import com.maple.msdialog.AlertDialog;
import com.maple.msdialog.AlertEditDialog;
import com.maple.msdialog.AlertNumberPickerDialog;

import java.util.Date;

/**
 * CN:      ITimeDateActivity
 * Author： JSYL-DINGCL (dingcl@jsyl.com.cn)
 * Date:   2019/12/13
 * Des:    时间选择
 */
public class ITimeDateActivity extends BaseActivity {

    private DatePickDialog datePickDialog;
    private String selectedDate = "2019-06-02";
    private String selectedTime = "17:15";

    @Override
    protected int getContentViewId() {
        return R.layout.activity_time;
    }

    @Override
    protected void initView(View mRootView) {
        StatusBarTool.setRootViewFitsSystemWindows(_mActivity,true);
        StatusBarTool.setStatusBarColor(_mActivity,Color.parseColor("#ffffd800"));
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void addListener() {

    }

    @Override
    public BasePresenter getPresenter() {
        return null;
    }



    public void iosBack(View view) {
        finish();
    }


    /**
     * 日期选择
     * @param view
     */
    public void dateSelect(View view) {
        showInitDialog(DatePickDialog.MODE_DATE_ONLY);
        datePickDialog.setOnDatePickListener(new DatePickDialog.OnDatePickListener() {
            @Override
            public void onDatePick(String date, String time) {
                selectedDate = date;
                iToast.showISimpleToast("日期：" + selectedDate + " ");
                datePickDialog.cancel();
            }
        });
    }

    /**
     * 时间选择
     * @param view
     */
    public void timeSelect(View view) {
        showInitDialog(DatePickDialog.MODE_TIME_ONLY);
        datePickDialog.setOnDatePickListener(new DatePickDialog.OnDatePickListener() {
            @Override
            public void onDatePick(String date, String time) {
                selectedTime = time;
                iToast.showISimpleToast("时间：" + selectedTime + " ");
                datePickDialog.cancel();
            }
        });
    }

    /**
     * 日期时间选择
     * @param view
     */
    public void dateTimeSelect(View view) {
        showInitDialog(DatePickDialog.MODE_DATE_AND_TIME);
        datePickDialog.setOnDatePickListener(new DatePickDialog.OnDatePickListener() {
            @Override
            public void onDatePick(String date, String time) {
                selectedDate = date;
                selectedTime = time;
                Toast.makeText(_mActivity, "日期时间：" + selectedDate + " " + selectedTime, Toast.LENGTH_SHORT).show();
                datePickDialog.cancel();
            }
        });
    }
    public void showInitDialog(int toBeMode){
        datePickDialog = new DatePickDialog(this);
        datePickDialog.show();
        datePickDialog.changeMode(toBeMode);
        datePickDialog.setSelectedDate(new Date());
    }

    @Override
    protected void onDestroy() {
        if (datePickDialog != null && datePickDialog.isShowing())datePickDialog.cancel();
        super.onDestroy();
    }
}
