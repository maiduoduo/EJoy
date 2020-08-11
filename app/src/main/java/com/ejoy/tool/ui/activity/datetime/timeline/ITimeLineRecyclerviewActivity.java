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

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ejoy.tool.R;
import com.ejoy.tool.common.bean.RecyclerviewTimeLineEntity;
import com.ejoy.tool.scaffold.utils.StatusBarTool;
import com.ejoy.tool.scaffold.view.PowerfulRecyclerView;
import com.ejoy.tool.ui.base.base_activity.IBaseActivity;
import com.ejoy.tool.ui.data.resource.GlobalDataProvider;
import com.google.gson.Gson;
import com.kongzue.baseframework.interfaces.DarkStatusBarTheme;
import com.kongzue.baseframework.interfaces.Layout;
import com.module.ires.bean.utils.EResUtils;
import com.module.ires.bean.utils.WidgetUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * @ClassName: ITimeLineRecyclerviewActivity
 * @Author: bsj-dingcl
 * @Email: dingchenglei@h4kit.com
 * @Date: 2020/07/20
 * @des: 时间线-RecyclerView
 */
@Layout(R.layout.activity_timeline_custom_recyclerview)
@DarkStatusBarTheme(true)
public class ITimeLineRecyclerviewActivity extends IBaseActivity {

    @BindView(R.id.title)
    FrameLayout title;
    @BindView(R.id.ivTopTtileIcon)
    ImageView ivTopTtileIcon;
    @BindView(R.id.tvPatientName)
    TextView tvPatientName;
    @BindView(R.id.tvFinishInfo)
    TextView tvFinishInfo;
    @BindView(R.id.mLineRecyclerView)
    PowerfulRecyclerView mLineRecyclerView;

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
        if (mTimeLineData == null) mTimeLineData = new ArrayList<>();
        else mTimeLineData.clear();
        WidgetUtils.initRecyclerView(mLineRecyclerView, mTimeLineAdapter =
                        new NursePatientinfoTodoTodayAdapter(R.layout.item_timeline_custom_rv, mTimeLineData, me),
                LinearLayoutManager.VERTICAL, 0, R.color.light_gray1, null);
    }

    @Override
    public void initDatas() {
        mTimeLineData.addAll(GlobalDataProvider.getTimeLineRvData(me));
        mTimeLineAdapter.setNewData(mTimeLineData);

        //已完成/总数
        int numFinish = 0;
        for (RecyclerviewTimeLineEntity n : mTimeLineData) {
            if (0 == n.getTotoStatus()){
                numFinish++;
            }
        }
        tvFinishInfo.setText("已完成 "+numFinish+"/"+mTimeLineData.size());


    }

    @Override
    public void setEvents() {

    }

    public void ivBack(View view) {
        finishActivity();
    }


    private class NursePatientinfoTodoTodayAdapter extends BaseQuickAdapter<RecyclerviewTimeLineEntity,BaseViewHolder> {
        private static final String TAG = "adapter";
        private Context mContext;
        private List<RecyclerviewTimeLineEntity> data;
        public NursePatientinfoTodoTodayAdapter(int layoutResId, List<RecyclerviewTimeLineEntity> data, Context context) {
            super(layoutResId, data);
            mContext = context;
            this.data = data;
        }

        @Override
        protected void convert(BaseViewHolder helper, RecyclerviewTimeLineEntity item) {
            int position = helper.getAdapterPosition();
            ImageView ivWaitDo = helper.getView(R.id.ivWaitDo);
            ImageView ivDoing = helper.getView(R.id.ivDoing);
            ImageView ivFinish = helper.getView(R.id.ivFinish);

            View lineGray = helper.getView(R.id.lineGray);
            View lineGreen = helper.getView(R.id.lineGreen);

            //设置分割线自适应
            TextView tvTitle = helper.getView(R.id.tvTitle);
            TextView tvTime = helper.getView(R.id.tvTime);
            RelativeLayout.LayoutParams layoutParams= (RelativeLayout.LayoutParams) lineGray.getLayoutParams();
            RelativeLayout.LayoutParams layoutParams1= (RelativeLayout.LayoutParams) lineGreen.getLayoutParams();
            layoutParams.addRule(RelativeLayout.ALIGN_BOTTOM,R.id.tvSpace);
            layoutParams1.addRule(RelativeLayout.ALIGN_BOTTOM,R.id.tvSpace);

            tvTitle.setText(item.getTotoTitle() == null ? "" : item.getTotoTitle());
            tvTime.setText(item.getTotoDate() == null ? "" : item.getTotoDate());

            if (position == 0){
                tvTitle.setTextColor(EResUtils.getColor(me,R.color.app_color_theme_2));
            }else {
                tvTitle.setTextColor(EResUtils.getColor(me,R.color.app_color_theme_10));
            }


            if (position == data.size()-1){
                lineGray.setVisibility(View.GONE);
                lineGreen.setVisibility(View.GONE);
            }else {
                lineGray.setVisibility(View.VISIBLE);
                lineGreen.setVisibility(View.VISIBLE);
            }

            /**
             * status:
             *       0:已完成
             *       1：近期完成
             *       2：正在进行
             */
            if (position == data.size()-1){
                lineGray.setVisibility(View.GONE);
                lineGreen.setVisibility(View.GONE);

            }else {
                if (2 == item.getTotoStatus()){//正在进行
                    lineGray.setVisibility(View.GONE);
                    lineGreen.setVisibility(View.VISIBLE);
                }else {//
                    lineGray.setVisibility(View.VISIBLE);
                    lineGreen.setVisibility(View.GONE);
                }
            }

            if (0 == item.getTotoStatus()) {//待办
                ivWaitDo.setVisibility(View.VISIBLE);
                ivDoing.setVisibility(View.GONE);
                ivFinish.setVisibility(View.GONE);
            }else if (1 == item.getTotoStatus()) {//近期完成
                ivWaitDo.setVisibility(View.GONE);
                ivDoing.setVisibility(View.VISIBLE);
                ivFinish.setVisibility(View.GONE);
            }else if (2 == item.getTotoStatus()) {//正在进行
                ivWaitDo.setVisibility(View.GONE);
                ivDoing.setVisibility(View.GONE);
                ivFinish.setVisibility(View.VISIBLE);
            }



        }
    }

}
