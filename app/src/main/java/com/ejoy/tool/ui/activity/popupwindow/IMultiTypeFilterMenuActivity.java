package com.ejoy.tool.ui.activity.popupwindow;
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

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ejoy.tool.R;
import com.ejoy.tool.common.bean.MultiFilterMenuModel;
import com.ejoy.tool.scaffold.view.decorator.DividerItemDecoration;
import com.ejoy.tool.ui.base.base_activity.IBaseActivity;
import com.imaiduoduo.datetime.DatePickDialog;
import com.kongzue.baseframework.interfaces.DarkStatusBarTheme;
import com.kongzue.baseframework.interfaces.Layout;
import com.module.iviews.popup.multimenu.MultiFilterMenuPopWindow;
import com.module.iviews.popup.multimenu.MultiFiltrateBean;
import com.module.iviews.slide.SwipeItemLayout;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import butterknife.BindView;


/**
 * @ClassName: IMultiTypeFilterMenuActivity
 * @Author: bsj-dingcl
 * @Email: dingchenglei@h4kit.com
 * @Date: 2020/07/18
 * @des: 多类型筛选菜单-基于Popupwindow
 */
@Layout(R.layout.activity_multitype_filtermenu)
@DarkStatusBarTheme(true)
public class IMultiTypeFilterMenuActivity extends IBaseActivity implements View.OnClickListener, RadioGroup.OnCheckedChangeListener, BaseQuickAdapter.OnItemClickListener {

    @BindView(R.id.recyclerview)
    RecyclerView mRecyclerView;
    @BindView(R.id.btn_search3)
    Button mBtnSearch;
    @BindView(R.id.llFilter)
    LinearLayout mLlFilter;
    @BindView(R.id.rgnReceive)
    RadioGroup rgnReceive;
    @BindView(R.id.rbnAll)
    RadioButton rbnAll;
    @BindView(R.id.rbnReceived)
    RadioButton rbnReceived;
    @BindView(R.id.rbnUnReceived)
    RadioButton rbnUnReceived;
    String[] filterStatus;
    private BaseQuickAdapter mAdapter;
    private MultiFilterMenuPopWindow flowPopWindow;
    private DatePickDialog datePickDialog;
    private String selectedDate = "2019-06-02";
    private String selectedTime = "17:15";
    private static int[] mImages = new int[]{
            R.mipmap.banner_a,
            R.mipmap.banner_b,
            R.mipmap.banner_g,
            R.mipmap.banner_d,
            R.mipmap.banner_e,
            R.mipmap.banner_f,
    };

    @Override
    protected boolean isRegistSatusbarFullScreenTransluent() {
        return false;
    }

    @Override
    public void initViews() {
        setDarkNavigationBarTheme(false);
        filterStatus = getResources().getStringArray(R.array.multi_filter_status);
        initMultiFilterParam();
        setAdapter();
    }

    @Override
    public void initDatas() {

    }

    @Override
    public void setEvents() {
        mBtnSearch.setOnClickListener(this);
        mLlFilter.setOnClickListener(this);
        rgnReceive.setOnCheckedChangeListener(this);
    }


    /**
     * 初始化筛选数据
     */
    private List<MultiFiltrateBean> dictList;
    private void initMultiFilterParam() {
        dictList = new ArrayList<>();
        MultiFiltrateBean fb3 = new MultiFiltrateBean();
        fb3.setTypeName("状态");
        List<MultiFiltrateBean.Children> childrenList3 = new ArrayList<>();
        for (int x = 0; x < filterStatus.length; x++) {
            MultiFiltrateBean.Children cd = new MultiFiltrateBean.Children();
            cd.setValue(filterStatus[x]);
            childrenList3.add(cd);
        }
        fb3.setChildren(childrenList3);
        dictList.add(fb3);
    }


    private List<MultiFilterMenuModel> loseAndFoundList;
    private void setAdapter() {
        loseAndFoundList = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            if (i == 1){
                MultiFilterMenuModel loseAndFoundModel = new MultiFilterMenuModel(
                        "皮包 "+i,
                        "2018-08-09 10:2"+i,
                        "G1568   "+i,
                        i+"00000000里面有现金若干,内有重要物品，外表灰黄色，有两个背带，",
                        "12306",
                        0
                );
                loseAndFoundList.add(loseAndFoundModel);
            }else if (i == 2){
                MultiFilterMenuModel loseAndFoundModel = new MultiFilterMenuModel(
                        "皮包 "+i,
                        "2018-08-09 10:2"+i,
                        "G1568   "+i,
                        i+"00000000里面有现金若干,内有重要物品，外表灰黄色，有两个背带，一串金属挂件，请及时排查找寻有线索立即通知。00000000里面有现金若干,内有重要物品，外表灰黄色，有两个背带，一串金属挂件，请及时排查找寻有线索立即通知。00000000里面有现金若干,内有重要物品，外表灰黄色，有两个背带，一串金属挂件，请及时排查找寻有线索立即通知。，",
                        "12306",
                        0
                );
                loseAndFoundList.add(loseAndFoundModel);
            }else {
                MultiFilterMenuModel loseAndFoundModel = new MultiFilterMenuModel(
                        "皮包 "+i,
                        "2018-08-09 10:2"+i,
                        "G1568   "+i,
                        i+"00000000里面有现金若干,内有重要物品，外表灰黄色，有两个背带，一串金属挂件，请及时排查找寻有线索立即通知。",
//                        i+"00000000里面有现金若干,内有重",
                        "本站"+i,
                        1
                );
                loseAndFoundList.add(loseAndFoundModel);
            }
        }
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.addItemDecoration(new DividerItemDecoration(me,R.color.light_gray1));
        mRecyclerView.addOnItemTouchListener(new SwipeItemLayout.OnSwipeItemTouchListener(this));
        mRecyclerView.setAdapter(mAdapter = new BaseQuickAdapter<MultiFilterMenuModel, BaseViewHolder>(R.layout.item_multifilter_list,loseAndFoundList) {
            @Override
            protected void convert(BaseViewHolder helper, MultiFilterMenuModel item) {
                final int adapterPosition = helper.getAdapterPosition();
                helper.setText(R.id.tvName,item.getName());
                helper.setText(R.id.tvLoseDate,item.getDate());
                helper.setText(R.id.tvLostPosition,"遗失位置： "+item.getPosition()+"车厢");
                helper.setText(R.id.tvLostDetail,item.getDescription());
                helper.setText(R.id.tvStationPosition,item.getStationposition());
                int status = item.getStatus();
                if (status == 0){
                    helper.setText(R.id.lostStatus,"待接收");
                    ((TextView)helper.getView(R.id.lostStatus)).setTextColor(getResources().getColor(R.color.red));
                }else if (status == 1){
                    helper.setText(R.id.lostStatus,"已接收");
                    ((TextView)helper.getView(R.id.lostStatus)).setTextColor(getResources().getColor(R.color.material_indigo_A200));
                }

                Random random = new Random();
                int i = random.nextInt(6);
                int mImage = mImages[i];
                ((ImageView)helper.getView(R.id.ivLoseImg)).setImageResource(mImage);
                ((View)helper.getView(R.id.main)).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(v.getContext(), "点击了位置为：" + adapterPosition, Toast.LENGTH_SHORT).show();
                    }
                });
                ((View)helper.getView(R.id.main)).setOnLongClickListener(new View.OnLongClickListener() {
                    @Override
                    public boolean onLongClick(View v) {
                        Toast.makeText(v.getContext(), "长按了位置为：" + adapterPosition, Toast.LENGTH_SHORT).show();
                        return false;
                    }
                });

                /*((View)helper.getView(R.id.delete)).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        loseAndFoundList.remove(adapterPosition);
                        notifyItemRemoved(adapterPosition);
                        Toast.makeText(v.getContext(), "删除：" + adapterPosition, Toast.LENGTH_SHORT).show();
                        SwipeItemLayout.closeAllItems(mRecyclerView);
                    }
                });*/
                if (adapterPosition == 1){
                    ((Button)helper.getView(R.id.btnClaim)).setVisibility(View.GONE);
                    ((View) helper.getView(R.id.btnReceive)).setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            iToast.showIImgToast("标为未读：" + adapterPosition);
                            SwipeItemLayout.closeAllItems(mRecyclerView);
                        }
                    });
                }else if (adapterPosition == 2){
                    ((LinearLayout)helper.getView(R.id.llSlideMenu)).setVisibility(View.GONE);
                }else {
                    ((View) helper.getView(R.id.btnClaim)).setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            iToast.showIImgToast("删除：" + adapterPosition);
                            SwipeItemLayout.closeAllItems(mRecyclerView);
                        }
                    });
                    ((View) helper.getView(R.id.btnReceive)).setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            iToast.showIImgToast("标为未读：" + adapterPosition);
                            SwipeItemLayout.closeAllItems(mRecyclerView);
                        }
                    });
                }
            }
        });
        mAdapter.setOnItemClickListener(this);

    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.llFilter){
            //筛选弹窗
            showFilterMenu();
        }
    }


    /**
     * 内容筛选
     */
    private int typeSelectTime;
    private TextView startTextView;
    private TextView endTextView;
    private void showFilterMenu() {
        flowPopWindow = new MultiFilterMenuPopWindow(me, dictList);
//        flowPopWindow.showAsDropDown(toolBar);
        flowPopWindow.showAsDropDown(mLlFilter);
        flowPopWindow.setOnConfirmClickListener(new MultiFilterMenuPopWindow.OnConfirmClickListener() {
            @Override
            public void onConfirmClick() {
                StringBuilder sb = new StringBuilder();
                String startTextViewResult = startTextView.getText().toString().trim();
                String endTextViewResult = endTextView.getText().toString().trim();
                sb.append(startTextViewResult +endTextViewResult);
                for (MultiFiltrateBean fb : dictList) {
                    List<MultiFiltrateBean.Children> cdList = fb.getChildren();
                    for (int x = 0; x < cdList.size(); x++) {
                        MultiFiltrateBean.Children children = cdList.get(x);
                        if (children.isSelected())
//                            sb.append(fb.getTypeName() + ":" + children.getValue() + "；");
                            sb.append(fb.getTypeName() + " : "+children.getValue());
                    }
                }
                if (!TextUtils.isEmpty(sb.toString())) {
                    iToast.showISimpleToast(""+ sb.toString());
                }else {
                    iToast.showISimpleToast("您还未选择内容");
                }
            }
        });
        startTextView = (TextView)flowPopWindow.getStartTextView();
        endTextView = (TextView)flowPopWindow.getEndTextView();
        if (startTextView instanceof TextView){
            ((TextView)startTextView).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    typeSelectTime = 1;//遗失时间从
                    dateTimeSelect(typeSelectTime);
                }
            });
        }
        if (endTextView instanceof TextView){
            ((TextView)endTextView).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    typeSelectTime = 2;//遗失时间至
                    dateTimeSelect(typeSelectTime);
                }
            });
        }
    }


    /**
     * 日期时间选择
     * @param dateType
     */
    public void dateTimeSelect(int dateType) {
        showInitDialog(DatePickDialog.MODE_DATE_AND_TIME);
        datePickDialog.setOnDatePickListener(new DatePickDialog.OnDatePickListener() {
            @Override
            public void onDatePick(String date, String time) {
                selectedDate = date;
                selectedTime = time;
                if (dateType == 2) {
                    endTextView.setText(selectedDate + " " + selectedTime);
                }else if (dateType == 1){
                    startTextView.setText(selectedDate + " " + selectedTime);
                }
                iToast.showISimpleToast("日期时间：" + selectedDate + " " + selectedTime);
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

    /**
     * 全部、已接收、待接收的监听
     * @param group
     * @param checkedId
     */
    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        if (checkedId == R.id.rbnAll){//全部
            iToast.showIImgToast("全部");
            rbnAll.setChecked(true);
            rbnAll.setTextColor(getResources().getColor(R.color.white));
            rbnAll.setBackgroundResource(R.drawable.shape_rantage_filter_click);
            rbnReceived.setChecked(false);
            rbnReceived.setTextColor(getResources().getColor(R.color.black));
            rbnReceived.setBackgroundResource(R.color.white);
            rbnUnReceived.setChecked(false);
            rbnUnReceived.setTextColor(getResources().getColor(R.color.black));
            rbnUnReceived.setBackgroundResource(R.color.white);
        }else  if (checkedId == R.id.rbnReceived){//已接收
            iToast.showIImgToast("已接收");
            rbnReceived.setChecked(true);
            rbnReceived.setTextColor(getResources().getColor(R.color.white));
            rbnReceived.setBackgroundResource(R.drawable.shape_rantage_filter_click);
            rbnAll.setChecked(false);
            rbnAll.setTextColor(getResources().getColor(R.color.black));
            rbnAll.setBackgroundResource(R.color.white);
            rbnUnReceived.setChecked(false);
            rbnUnReceived.setTextColor(getResources().getColor(R.color.black));
            rbnUnReceived.setBackgroundResource(R.color.white);
        }else  if (checkedId == R.id.rbnUnReceived){//待接收
            iToast.showIImgToast("待接收");
            rbnUnReceived.setChecked(true);
            rbnUnReceived.setTextColor(getResources().getColor(R.color.white));
            rbnUnReceived.setBackgroundResource(R.drawable.shape_rantage_filter_click);
            rbnAll.setChecked(false);
            rbnAll.setTextColor(getResources().getColor(R.color.black));
            rbnAll.setBackgroundResource(R.color.white);
            rbnReceived.setChecked(false);
            rbnReceived.setTextColor(getResources().getColor(R.color.black));
            rbnReceived.setBackgroundResource(R.color.white);
        }
    }


    /**
     * 条目点击监听
     * @param adapter
     * @param view
     * @param position
     */
    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        iToast.showIImgToast("第"+position+"条");
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }

    public void ivBack(View view) {
        finishActivity();
    }
}
