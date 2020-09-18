package com.ejoy.tool.ui.activity.more_detail;
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
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.text.Html;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ejoy.tool.R;
import com.ejoy.tool.common.bean.WidgetEntity;
import com.ejoy.tool.scaffold.utils.StatusBarTool;
import com.ejoy.tool.scaffold.view.PowerfulRecyclerView;
import com.ejoy.tool.ui.activity.seekbar.ISeekBarAndCheckBoxActivity;
import com.ejoy.tool.ui.base.base_activity.IBaseActivity;
import com.kongzue.baseframework.interfaces.DarkStatusBarTheme;
import com.kongzue.baseframework.interfaces.Layout;
import com.kongzue.dialog.util.BlurView;
import com.module.ires.bean.utils.EDensityUtils;
import com.module.ires.bean.utils.EResUtils;
import com.module.ires.bean.utils.WidgetUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;


/**
 * @ClassName:  IUIBehaviorActivity
 * @Author: maiduoduo
 * @BLOG: https://blog.csdn.net/Maiduoudo
 * @Date: 2020/9/16
 * @des: UI Behavior
 */
@Layout(R.layout.activity_iui_behavior)
@DarkStatusBarTheme(true)
//@DarkNavigationBarTheme(true)
public class IUIBehaviorActivity extends IBaseActivity {

    @BindView(R.id.blur)
    BlurView blur;
    @BindView(R.id.rv_content)
    PowerfulRecyclerView mRvContent;

    private BaseQuickAdapter mContentAdapter;
    private List<WidgetEntity> contentList;
    private TextView mTextView;
    private boolean mIsFull = false;

    @Override
    protected boolean isRegistSatusbarFullScreenTransluent() {
        return true;
    }

    @Override
    protected boolean isRegistSatusbarFontDark() {
        return true;
    }


    @Override
    public void initViews() {
        if (contentList != null) contentList.clear();
        else contentList = new ArrayList<>();
        blur.setOverlayColor(Color.argb(200, 235, 235, 235));
        blur.setRadius(me, 0, 0);
        setDarkNavigationBarTheme(false);

        WidgetUtils.initRecyclerView(mRvContent, mContentAdapter =
                        new ContentAdapter(R.layout.item_views_list_widget, contentList, me),
                LinearLayoutManager.VERTICAL, 1, R.color.base_bg, null);
    }

    @Override
    public void initDatas() {
        addHeader();
        contentList.clear();
        contentList.addAll(getContentList());
        mContentAdapter.setNewData(contentList);
    }

    private void addHeader() {
        int padding = getResources().getDimensionPixelSize(R.dimen.dp_margin);
        mTextView = new TextView(me);
        mTextView.setTextColor(ContextCompat.getColor(me, R.color.colorTextBlack));
        mTextView.setPadding(padding, padding, padding, padding);
        mTextView.setLineSpacing(1.5f, 1f);
        mTextView.setTextSize(TypedValue.COMPLEX_UNIT_PX, getResources().getDimensionPixelSize(R.dimen.dp_text_size_main));
        mTextView.setBackgroundColor(EResUtils.getColor(me,R.color.base_bg));
        if (mTextView != null) {
            mTextView.setText(Html.fromHtml(
                    "<h5>官网的解释：用来协调CoordinatorLayout的Child Views之间的交互行为</h5>"
                            + "Behavior是CoordinatorLayout的一个抽象内部类，"
                            + "<br>在自定义Behavior的时候，我们需要关心2种机制:"
                            + "<br>1.dependent机制;"
                            + "<br>2.Nested机制"));
        }
        mContentAdapter.addHeaderView(mTextView);

    }

    @Override
    public void setEvents() {

        mContentAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                WidgetEntity item = (WidgetEntity) adapter.getItem(position);
                jump(item.activity);
            }
        });

    }


    @OnClick({
            R.id.btn_back
    })
    public void eventClick(View view) {
        switch (view.getId()) {
            case R.id.btn_back:
                finish();
                break;

        }
    }


    private List<WidgetEntity> getContentList() {
        List<WidgetEntity> widgetEntities = new ArrayList<>();
        widgetEntities.add(new WidgetEntity(R.mipmap.bicon_behavior_a,"小米音乐歌手详情页", "自定义Behavior实现小米音乐歌手详情页", IMiMusicBehaviorActivity.class));
        widgetEntities.add(new WidgetEntity(R.drawable.bicon_soso_blue,"标题栏伸缩效果", "（1）头像移动和缩放动画 （2）文字的透明度动画  （3）背景的透明动画。", IPercentageBehaviorActivity.class));
        return widgetEntities;
    }


    private class ContentAdapter extends BaseQuickAdapter<WidgetEntity, BaseViewHolder> {
        private static final String TAG = "adapter";
        private Context mContext;
        private List<WidgetEntity> data;

        public ContentAdapter(int layoutResId, List<WidgetEntity> data, Context context) {
            super(layoutResId, data);
            mContext = context;
            this.data = data;
        }

        @Override
        protected void convert(BaseViewHolder helper, WidgetEntity item) {
            int position = helper.getAdapterPosition() - getHeaderLayoutCount();
            helper.setText(R.id.tv_titleWidget, item.title)
                    .setText(R.id.tv_contentWidget, item.content);
            ((ImageView)helper.getView(R.id.ivIcon)).setImageResource(item.icon);
        }
    }

}
