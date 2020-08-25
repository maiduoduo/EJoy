package com.ejoy.tool.ui.activity.datetime.picker;
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

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ejoy.tool.R;
import com.ejoy.tool.ui.base.base_activity.IBaseActivity;
import com.imaiduoduo.datetime.fulepicker.DateUtil;
import com.imaiduoduo.datetime.fulepicker.LoopListener;
import com.imaiduoduo.datetime.fulepicker.LoopView;
import com.kongzue.baseframework.interfaces.DarkStatusBarTheme;
import com.kongzue.baseframework.interfaces.Layout;

import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;


/**
 * @ClassName: IDateTimeSelectDialogLessActivity
 * @Author: bsj-dingcl
 * @Email: dingchenglei@h4kit.com
 * @Date: 2020/07/20
 * @des: 日期选择-无弹窗
 */
@Layout(R.layout.activity_date_choose_dialogless)
@DarkStatusBarTheme(true)
public class IDateTimeSelectDialogLessActivity extends IBaseActivity {

    //日期选择器
    @BindView(R.id.mStartLoopYear) LoopView mStartloopYear;
    @BindView(R.id.mStartLoopMonth) LoopView mStartloopMonth;
    @BindView(R.id.mStartLoopDay) LoopView mStartloopDay;
    @BindView(R.id.tvStartDate) TextView tvStartDate;
    @BindView(R.id.tvEndDate) TextView tvEndDate;
    @BindView(R.id.tvStartLine) TextView tvStartLine;
    @BindView(R.id.tvEndLine) TextView tvEndLine;
    @BindView(R.id.llStartDateText) LinearLayout llStartDateText;
    @BindView(R.id.llEndDateText) LinearLayout llEndDateText;
    @BindView(R.id.flDateStart) FrameLayout flDateStart;

    private Integer startSelectYear, startSelectMonth, startSelectDay
            ,startMaxYear, startMaxMonth, startMaxDay
            ,startMinYear, startMinMonth, startMinDay;
    private static int MIN_YEAR = 2000;
    private static int MAX_YEAR = 2020;
    private String currentDate = "";
    private boolean isClickStart = true;

    @Override
    protected boolean isRegistSatusbarFullScreenTransluent() {
        return false;
    }

    @Override
    public void initViews() {
        setDarkNavigationBarTheme(false);
        currentDate = DateUtil.getCurrentDate();
        initDatePicker();
    }

    private void initDatePicker() {
        //切割当前日期
        String s = currentDate.split("\\-")[0];
        MAX_YEAR = Integer.parseInt(s)+1;
        //日期选择器初始化
        showStartDate(DateUtil.getDateForString(currentDate));
        initDateView();
        tvStartDate.setText(currentDate);
        tvEndDate.setText(currentDate);
        tvStartDate.post(new Runnable() {
            @Override
            public void run() {
                tvStartDate.performClick();
            }
        });
    }

    @Override
    public void initDatas() {

    }

    @Override
    public void setEvents() {
        //日期监听
        mStartloopYear.setListener(startMaxDaySyncListener);
        mStartloopMonth.setListener(startMaxDaySyncListener);
        mStartloopDay.setListener(startDayLoopListener);
        llStartDateText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isClickStart = true;
                flDateStart.setVisibility(View.GONE);
                flDateStart.setVisibility(View.VISIBLE);
                tvStartLine.setVisibility(View.VISIBLE);
                tvEndLine.setVisibility(View.GONE);

                initDateView();
                showStartDate(DateUtil.getDateForString(DateUtil.getCurrentDate()));

            }
        });


        llEndDateText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isClickStart = false;
                flDateStart.setVisibility(View.GONE);
                flDateStart.setVisibility(View.VISIBLE);
                tvStartLine.setVisibility(View.GONE);
                tvEndLine.setVisibility(View.VISIBLE);
                initDateView();
                showStartDate(DateUtil.getDateForString(DateUtil.getCurrentDate()));
            }
        });
    }





    @OnClick({
            R.id.tvSelectsure
    })
    public void bindViewClick(View view){
        switch (view.getId()){
            case R.id.tvSelectsure:
                int[] currDateValues = getCurrDateValues(mStartloopYear,mStartloopMonth,mStartloopDay);
                if (isClickStart){
                    tvStartDate.setText(String.format("%d-%s-%s", currDateValues[0], currDateValues[1] > 9 ? currDateValues[1] : ("0" + currDateValues[1]), currDateValues[2] > 9 ? currDateValues[2] : ("0" + currDateValues[2])));
                }else {
                    tvEndDate.setText(String.format("%d-%s-%s", currDateValues[0], currDateValues[1] > 9 ? currDateValues[1] : ("0" + currDateValues[1]), currDateValues[2] > 9 ? currDateValues[2] : ("0" + currDateValues[2])));
                }
                break;
        }
    }


    /**
     * 初始化日期容器控件
     */
    private void initDateView() {
        Calendar c = Calendar.getInstance();
        mStartloopYear.setArrayList(d(MIN_YEAR, MAX_YEAR - MIN_YEAR + 1));
        mStartloopMonth.setArrayList(d(1, 12));
        mStartloopDay.setArrayList(d(1, 30));

        if (startSelectDay != null) {
            mStartloopDay.setCurrentItem(startSelectDay);
        } else {
            mStartloopDay.setCurrentItem(c.get(Calendar.DATE));
        }
        mStartloopDay.setNotLoop();

        if (startSelectYear != null) {
            mStartloopYear.setCurrentItem(startSelectYear - MIN_YEAR + 1);
        } else {
            mStartloopYear.setCurrentItem(MAX_YEAR);
        }
        mStartloopYear.setNotLoop();


        if (startSelectMonth != null) {
            mStartloopMonth.setCurrentItem(startSelectMonth);
        } else {
            mStartloopMonth.setCurrentItem(c.get(Calendar.MONTH));
        }
        mStartloopMonth.setNotLoop();
    }

    /**
     * 显示开始日期
     *
     * @param date
     */
    private void showStartDate(List<Integer> date) {
        setStartSelectYear(date.get(0) - 1);
        setStartSelectMonth(date.get(1) - 1);
        setStartSelectDay(date.get(2) - 1);

        setStartMaxYear(DateUtil.getYear());
        setStartMaxMonth(DateUtil.getDateForString(DateUtil.getToday()).get(1));
        setStartMaxDay(DateUtil.getDateForString(DateUtil.getToday()).get(2));
    }

    public void setStartSelectYear(int year) {
        this.startSelectYear = year;
    }

    public void setStartSelectMonth(int month) {
        this.startSelectMonth = month;
    }

    public void setStartSelectDay(int day) {
        this.startSelectDay = day;
    }

    public void setStartMaxYear(int year) {
        startMaxYear = year;
    }

    public void setStartMaxMonth(int month) {
        startMaxMonth = month;
    }

    public void setStartMaxDay(int day) {
        startMaxDay = day;
    }


    /**
     * 将数字传化为集合，并且补充0
     *
     * @param startNum 数字起点
     * @param count    数字个数
     * @return
     */
    private static List<String> d(int startNum, int count) {
        String[] values = new String[count];
        for (int i = startNum; i < startNum + count; i++) {
            String tempValue = (i < 10 ? "0" : "") + i;
            values[i - startNum] = tempValue;
        }
        return Arrays.asList(values);
    }

    /**
     * 获取当前选择的日期
     *
     * @return int[]数组形式返回。例[1990,6,15]
     */
    private final int[] getCurrDateValues(LoopView loopYear,LoopView loopMonth,LoopView loopDay) {
        int currYear = Integer.parseInt(loopYear.getCurrentItemValue());
        int currMonth = Integer.parseInt(loopMonth.getCurrentItemValue());
        int currDay = Integer.parseInt(loopDay.getCurrentItemValue());
        return new int[]{currYear, currMonth, currDay};
    }


    /**
     * 日期`年月`滑轮监听
     */
    final LoopListener startMaxDaySyncListener = new LoopListener() {
        @Override
        public void onItemSelect(int item) {
            try {
                Calendar c = Calendar.getInstance();
                boolean needFixed = true;
                if (startMinYear != null) {
                    if (Integer.parseInt(mStartloopYear.getCurrentItemValue()) == startMinYear) {
                        if (startMinMonth != null) {
                            if (Integer.parseInt(mStartloopMonth.getCurrentItemValue()) < startMinMonth) {
                                mStartloopMonth.setCurrentItem(startMinMonth - 1);
                                Log.e(_TAG, "-----1-------:" + (startMinMonth - 1));
                            }
                        }
                    } else if (Integer.parseInt(mStartloopYear.getCurrentItemValue()) < startMinYear) {
                        mStartloopYear.setCurrentItem(startMinYear - MIN_YEAR);
                        Log.e(_TAG, "-----2-------:" + (startMinYear - MIN_YEAR));
                    }
                }


                //开始日期
                if (startMaxYear != null) {
                    if (Integer.parseInt(mStartloopYear.getCurrentItemValue()) == startMaxYear) {
                        if (startMaxMonth != null) {
                            if (Integer.parseInt(mStartloopMonth.getCurrentItemValue()) > startMaxMonth) {
                                mStartloopMonth.setCurrentItem(startMaxMonth - 1);
                            }
                        }
                    } else if (Integer.parseInt(mStartloopYear.getCurrentItemValue()) > startMaxYear) {
                        mStartloopYear.setCurrentItem(startMaxYear - MIN_YEAR);
                    }
                }


                c.set(Integer.parseInt(mStartloopYear.getCurrentItemValue()), Integer.parseInt(mStartloopMonth.getCurrentItemValue()) - 1, 1);
                c.roll(Calendar.DATE, false);

                if (needFixed) {
                    int maxDayOfMonth = c.get(Calendar.DATE);
                    int fixedCurr = mStartloopDay.getCurrentItem();
                    mStartloopDay.setArrayList(d(1, maxDayOfMonth));
                    // 修正被选中的日期最大值
                    if (fixedCurr > maxDayOfMonth) fixedCurr = maxDayOfMonth - 1;
                    mStartloopDay.setCurrentItem(fixedCurr);
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    };


    /**
     * 日期`日`滑轮监听
     */
    final LoopListener startDayLoopListener = new LoopListener() {
        @Override
        public void onItemSelect(int item) {
            try {
                if (startMinYear != null && startMinMonth != null && startMinDay != null
                        && Integer.parseInt(mStartloopYear.getCurrentItemValue()) == startMinYear
                        && Integer.parseInt(mStartloopMonth.getCurrentItemValue()) == startMinMonth
                        && Integer.parseInt(mStartloopDay.getCurrentItemValue()) < startMinDay
                        ) {
                    mStartloopDay.setCurrentItem(startMinDay - 1);
                }

                if (startMaxYear != null && startMaxMonth != null && startMaxDay != null
                        && Integer.parseInt(mStartloopYear.getCurrentItemValue()) == startMaxYear
                        && Integer.parseInt(mStartloopMonth.getCurrentItemValue()) == startMaxMonth
                        && Integer.parseInt(mStartloopDay.getCurrentItemValue()) > startMaxDay
                        ) {
                    mStartloopDay.setCurrentItem(startMaxDay - 1);
                }
            }catch (Exception e){
                e.printStackTrace();
            }

        }
    };





    public void ivBack(View view) {
        finishActivity();
    }
}
