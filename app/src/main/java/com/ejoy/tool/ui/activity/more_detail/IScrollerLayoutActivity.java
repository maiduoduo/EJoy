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

import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.text.Html;
import android.util.TypedValue;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ejoy.tool.R;
import com.ejoy.tool.common.bean.WidgetEntity;
import com.ejoy.tool.scaffold.view.PowerfulRecyclerView;
import com.ejoy.tool.ui.activity.more_detail.scrollerlayout.ISLConsecutiveActivity;
import com.ejoy.tool.ui.activity.more_detail.scrollerlayout.ISLPermanentStickyActivity;
import com.ejoy.tool.ui.activity.more_detail.scrollerlayout.ISLSampleActivity;
import com.ejoy.tool.ui.activity.more_detail.scrollerlayout.ISLSinkStickyActivity;
import com.ejoy.tool.ui.activity.more_detail.scrollerlayout.ISLStickyActivity;
import com.ejoy.tool.ui.base.base_activity.IBaseActivity;
import com.kongzue.baseframework.interfaces.DarkStatusBarTheme;
import com.kongzue.baseframework.interfaces.Layout;
import com.kongzue.dialog.util.BlurView;
import com.module.ires.bean.utils.EResUtils;
import com.module.ires.bean.utils.WidgetUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;


/**
 * @ClassName:  IScrollerLayoutActivity
 * @Author: maiduoduo
 * @BLOG: https://blog.csdn.net/Maiduoudo
 * @Date: 2020/9/18
 * @des:  支持多个滑动布局(RecyclerView、WebView、ScrollView等)和lView等)和
 *        普通控件(TextView、ImageView、LinearLayou、自定义View等)持续连贯滑动的容器,
 *        它使所有的子View像一个整体一样连续顺畅滑动。
 *        并且支持布局吸顶功能。
 */
@Layout(R.layout.activity_iui_scrollerlayout)
@DarkStatusBarTheme(true)
//@DarkNavigationBarTheme(true)
public class IScrollerLayoutActivity extends IBaseActivity {

    @BindView(R.id.recyclerView)
    PowerfulRecyclerView mRecyclerView;
    @BindView(R.id.blur)
    BlurView blur;

    private BaseQuickAdapter mContentAdapter;
    private List<WidgetEntity> contentList;
    private TextView mTextView;

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
        blur.setOverlayColor(Color.argb(200, 235, 235, 235));
        blur.setRadius(me, 0, 0);
        setDarkNavigationBarTheme(false);
        if (contentList != null)contentList.clear();
        else contentList = new ArrayList<>();
        initRecyclerView();

    }


    private void initRecyclerView() {
        WidgetUtils.initRecyclerView(mRecyclerView, mContentAdapter =
                        new BaseQuickAdapter<WidgetEntity, BaseViewHolder>(R.layout.item_views_list_simple, contentList) {
                            @Override
                            protected void convert(BaseViewHolder helper, WidgetEntity item) {
                                int position = helper.getAdapterPosition() - getHeaderLayoutCount();
                                helper.setText(R.id.tv_titleWidget, item.title);
                                ((ImageView)helper.getView(R.id.ivIcon)).setImageResource(item.icon);
                            }
                        },
                LinearLayoutManager.VERTICAL, 1, R.color.base_bg, null);
    }

    @Override
    public void initDatas() {
        addHeader();
        contentList.clear();
        contentList.addAll(getContentList());
        mContentAdapter.setNewData(contentList);
    }


    @Override
    public void setEvents() {
        mContentAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                WidgetEntity item = (WidgetEntity) adapter.getItem(position);
                if (item.activity != null) {
                    jump(item.activity);
                }
            }
        });
    }


    @OnClick({
            R.id.btn_back
    })
    public void eventClick(View view){
         switch (view.getId()){
                case R.id.btn_back:
                    finish();
                    break;

           }
     }


    private void addHeader() {
        int padding = getResources().getDimensionPixelSize(R.dimen.dp_margin);
        mTextView = new TextView(me);
        mTextView.setTextColor(ContextCompat.getColor(me, R.color.colorTextBlack));
        mTextView.setPadding(padding, padding, padding, padding);
        mTextView.setLineSpacing(1.5f, 1f);
        mTextView.setTextSize(TypedValue.COMPLEX_UNIT_PX, getResources().getDimensionPixelSize(R.dimen.dp_text_size_main));
//        mTextView.setBackgroundColor(Color.WHITE);
        mTextView.setBackgroundColor(EResUtils.getColor(me,R.color.base_bg));
        if (mTextView != null) {
            mTextView.setText(Html.fromHtml(
                    "ConsecutiveScrollerLayout:"
                            + "<br>支持多个滑动布局(RecyclerView、WebView、ScrollView等)和lView等)"
                            + "<br>支持普通控件(TextView、ImageView、LinearLayou、自定义View等)持续连贯滑动的容器"
                            + "<br>使所有的子View像一个整体一样连续顺畅滑动"
                            + "<br>并且支持布局吸顶功能。")
            );
        }
        mContentAdapter.addHeaderView(mTextView);
    }


    private List<WidgetEntity> getContentList() {
        List<WidgetEntity> widgetEntities = new ArrayList<>();
        widgetEntities.add(new WidgetEntity(R.drawable.bicon_front_a,"基础示例", "", ISLSampleActivity.class));
        widgetEntities.add(new WidgetEntity(R.drawable.bicon_front_b,"布局吸顶", "", ISLStickyActivity.class));
        widgetEntities.add(new WidgetEntity(R.drawable.bicon_front_c,"布局吸顶常驻", "", ISLPermanentStickyActivity.class));
        widgetEntities.add(new WidgetEntity(R.drawable.bicon_front_d,"吸顶下沉模式", "", ISLSinkStickyActivity.class));
        widgetEntities.add(new WidgetEntity(R.drawable.bicon_front_e,"局部滑动", "", ISLConsecutiveActivity.class));
        widgetEntities.add(new WidgetEntity(R.drawable.bicon_front_f,"支持ViewPager", "", null));
        widgetEntities.add(new WidgetEntity(R.drawable.bicon_front_g,"包裹Fragment", "", null));
        return widgetEntities;
    }


}
