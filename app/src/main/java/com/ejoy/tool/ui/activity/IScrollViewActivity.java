package com.ejoy.tool.ui.activity;
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

import android.app.Activity;
import android.graphics.Color;
import android.view.View;
import android.widget.Button;

import com.ejoy.tool.R;
import com.ejoy.tool.common.helper.InfoDialog.IDialog;
import com.ejoy.tool.scaffold.utils.StatusBarTool;
import com.ejoy.tool.ui.base.base_activity.BaseActivity;
import com.ejoy.tool.ui.mvp.base.BasePresenter;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * CN:      ToastActivity
 * Author： JSYL-DINGCL (dingcl@jsyl.com.cn)
 * Date:   2019/11/18
 * Des:    ScrollView
 */
public class IScrollViewActivity extends BaseActivity {
    @BindView(R.id.btnScrollViewLong) Button btnScrollViewLong;
    @BindView(R.id.btnScrollViewShort) Button btnScrollViewShort;
    private IDialog iDialog;
    private Activity mActivity;
    private String[] mMsgArray = new String[]{
            "温馨提示\n工具箱2.0新版本对系统做了大量优化及增加新功能，请及时更新，避免版本差异造成数据、功能不同步。\n\n" +
                    "当ScrollView中显示内容量小的时候自适应高度不滚动，当ScrollView中显示内容量大的时候需要将其高度设置为屏幕高度的一半且可以滚动查看，\n" +
                    "由于ScrollView没有设置其最大高度的属性，所以自定义一个ScrollView来满足显示要求。\n\n" +
                    "自定义一个View继承ScrollView并重写其onMeasure方法，在此方法中设置控件最大高度不能超过屏幕高度的一半。\n\n" +
                    "由于ScrollView没有设置其最大高度的属性，所以自定义一个ScrollView来满足显示要求。\n\n" +
                    "自定义一个View继承ScrollView并重写其onMeasure方法，在此方法中设置控件最大高度不能超过屏幕高度的一半。\n\n" +
                    "由于ScrollView没有设置其最大高度的属性，所以自定义一个ScrollView来满足显示要求。\n\n" +
                    "自定义一个View继承ScrollView并重写其onMeasure方法，在此方法中设置控件最大高度不能超过屏幕高度的一半。\n\n",
            "当ScrollView中显示内容量小的时候自适应高度不滚动，当ScrollView中显示内容量大的时候需要将其高度设置为屏幕高度的一半且可以滚动查看\n"
    };

    @Override
    protected int getContentViewId() {
        return R.layout.activity_iscrollview;
    }

    @Override
    protected void initView(View mRootView) {
        mActivity = IScrollViewActivity.this;
        iDialog = new IDialog(mActivity).builder();
        //当自定义状态栏背景和标题栏的时候，加上这两句代码，避免重叠问题
        StatusBarTool.setRootViewFitsSystemWindows(this,true);
        StatusBarTool.setStatusBarColor(this,Color.parseColor("#3493d4"));
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


    private void showScrollViewDialog(boolean isLong) {
        iDialog.setGone().setTitle("IScrollView")
                .setMsg(isLong ? mMsgArray[0] + "" : mMsgArray[1] + "")
                .setNegativeButton("取消", null)
                .setPositiveButton("继续", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                    }
                }).show();
    }


    @OnClick({
            R.id.btnScrollViewLong
            , R.id.btnScrollViewShort
    })
    public void bindViewClick(View view) {
        switch (view.getId()) {
            case R.id.btnScrollViewLong://长篇内容
                showScrollViewDialog(true);
                break;
            case R.id.btnScrollViewShort://短篇内容
                showScrollViewDialog(false);
                break;
        }
    }

    @Override
    protected void initStatusbar() {
        super.initStatusbar();
    }

}
