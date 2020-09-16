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
import com.jaeger.library.StatusBarUtil;
import com.kongzue.baseframework.interfaces.DarkStatusBarTheme;
import com.kongzue.baseframework.interfaces.Layout;
import com.kongzue.dialog.util.BlurView;
import com.module.ires.bean.utils.EAppUtil;
import com.module.ires.bean.utils.EDensityUtils;
import com.module.ires.bean.utils.WidgetUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;



/**
 * @ClassName:  IViewsActivity
 * @Author: maiduoduo
 * @BLOG: https://blog.csdn.net/Maiduoudo
 * @Date: 2020/9/16
 * @des: 全组件界面
 */
@Layout(R.layout.activity_iviews)
@DarkStatusBarTheme(true)
//@DarkNavigationBarTheme(true)
public class IViewsActivity extends IBaseActivity {

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
        mTextView.setBackgroundColor(Color.WHITE);
        if (mTextView != null) {
            mTextView.setText(Html.fromHtml(
                    "是否全面屏:" + StatusBarTool.isStatusBar(me.getWindow())
                            + "<br>屏幕纵横比:" + EDensityUtils.getScreenDpi(me)
                            + "<br>是否开启全面屏手势:根据判断系统是否开启虚拟导航栏(如华为可手动开关该方法不是完全正确的):" + false
                            + "<br>是否有导航栏:" + EDensityUtils.isNavigationBarExist(me)
                            + "<br>导航栏高度:" + EDensityUtils.getStatusHeight(me)
                            + "<br>是否隐藏状态栏:" + StatusBarTool.isStatusBar(me.getWindow())
                            + "<br>是否隐藏导航栏:" +false));
        }
        mContentAdapter.addHeaderView(mTextView);
        mTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StatusBarTool.setFullScreen(me, !mIsFull);
                mIsFull = !mIsFull;
            }
        });
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
        widgetEntities.add(new WidgetEntity(R.drawable.bicon_soso_red,"TabLayout", "快速创建Tab控件包括CommonTabLayout、SlidingTabLayout及SegmentTabLayout三种类型Tab", ISeekBarAndCheckBoxActivity.class));
        widgetEntities.add(new WidgetEntity(R.drawable.bicon_soso_blue,"StatusViewHelper", "支持顶部自定义标题栏沉浸效果帮助类。", ISeekBarAndCheckBoxActivity.class));
        widgetEntities.add(new WidgetEntity(R.drawable.bicon_today_todo_circle,"KeyboardHelper", "解决软键盘遮住底部输入框问题帮助类。", ISeekBarAndCheckBoxActivity.class));
        widgetEntities.add(new WidgetEntity(R.drawable.bicon_soso_red,"TitleBarView", "一款支持沉浸状态栏效果设置的ToolBar控件:支持xml设置是否沉浸、主标题及副标题、左边文字及icon、右边文字及icon、下划线;支持添加左边、中间及右边view方便扩展。", ISeekBarAndCheckBoxActivity.class));
        widgetEntities.add(new WidgetEntity(R.drawable.bicon_today_todo_circle,"UIAlertDialog", "一款自定义Alert效果控件:属性命名及调用方式同Android原生AlertDialog,增加样式背景及文本相关属性自定义自定义。", ISeekBarAndCheckBoxActivity.class));
        widgetEntities.add(new WidgetEntity(R.drawable.bicon_soso_blue,"UIActionSheetDialog", "一款底部弹框控件:支持List模式(iOS、微信及QQ样式)和Grid模式", ISeekBarAndCheckBoxActivity.class));
        widgetEntities.add(new WidgetEntity(R.drawable.bicon_soso_red,"UIProgressDialog", "一款仿微博、微信、MD loading控件:扩展背景及loading 样式设置。", ISeekBarAndCheckBoxActivity.class));
        widgetEntities.add(new WidgetEntity(R.drawable.bicon_soso_red,"Behavior", "自定义Behavior实现音乐详情页。", ISeekBarAndCheckBoxActivity.class));
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


//            helper.itemView.setPadding(helper.itemView.getPaddingLeft(), helper.itemView.getPaddingTop()
//                    , helper.itemView.getPaddingBottom(), position == getData().size() - 1 ? NavigationBarUtil.getNavigationBarHeight((Activity) mContext) : 0);
            Log.i("position", "position:" + position);




        }
    }

}
