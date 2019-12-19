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

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.ejoy.tool.R;
import com.ejoy.tool.scaffold.utils.StatusBarTool;
import com.ejoy.tool.ui.activity.MainActivity;
import com.ejoy.tool.ui.base.base_activity.BaseActivity;
import com.ejoy.tool.ui.base.base_webview.BaseWebView;
import com.ejoy.tool.ui.mvp.base.BasePresenter;
import com.google.gson.Gson;
import com.iduo.filterlib.FilterInfoBean;
import com.iduo.filterlib.FilterResultBean;
import com.iduo.filterlib.FilterTabConfig;
import com.iduo.filterlib.FilterTabView;
import com.iduo.filterlib.listener.OnSelectResultListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * CN:      IPopupwindowFilterActivity
 * Author： JSYL-DINGCL (dingcl@jsyl.com.cn)
 * Date:   2019/11/27
 * Des:    PopupwindowFilter
 */
public class IPopupwindowFilterActivity extends BaseActivity implements OnSelectResultListener {
    @BindView(R.id.webview) BaseWebView mWebview;
    @BindView(R.id.ftb_filter) FilterTabView ftb_filter;

    @Override
    protected void initRestore(@Nullable Bundle savedInstanceState) {

    }

    @Override
    protected int getContentViewId() {
        return R.layout.activity_ipopupwindow_filter;
    }

    @Override
    protected void initView(View mRootView) {
        StatusBarTool.setTranslucentStatus(this);//透明状态栏
        //用来设置整体下移，状态栏沉浸
        StatusBarTool.setRootViewFitsSystemWindows(this, false);
        //黑色字体
        StatusBarTool.setStatusBarDarkTheme(this, false);
//        StatusBarTool.setStatusBarColor(this, Color.parseColor("#58C087"));//设置背景颜色
        loadFilter();
        loadListener();
        loadWebView();
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void addListener() {

    }

    private void loadWebView() {
        mWebview.loadUrl("file:///android_asset/PopupwindowFilter.html");

    }


    private void loadFilter() {
        String jsonStr = getJson(this,"demo_data.json");
        FilterEntity filterEntity = JsonToObject(jsonStr, FilterEntity.class);

        ftb_filter.removeViews();
        FilterInfoBean bean1 = new FilterInfoBean("区域", FilterTabConfig.FILTER_TYPE_AREA, filterEntity.getArea());
        FilterInfoBean bean2 = new FilterInfoBean("总价", FilterTabConfig.FILTER_TYPE_PRICE, filterEntity.getPrice());
        FilterInfoBean bean3 = new FilterInfoBean("户型", FilterTabConfig.FILTER_TYPE_SINGLE_SELECT, filterEntity.getHouseType());
        FilterInfoBean bean4 = new FilterInfoBean("筛选", FilterTabConfig.FILTER_TYPE_MUL_SELECT, filterEntity.getMulSelect());
        FilterInfoBean bean5 = new FilterInfoBean("几室", FilterTabConfig.FILTER_TYPE_SINGLE_GIRD, filterEntity.getHouseType());


        ftb_filter.addFilterItem(bean1.getTabName(), bean1.getFilterData(), bean1.getPopupType(), 0);
        ftb_filter.addFilterItem(bean2.getTabName(), bean2.getFilterData(), bean2.getPopupType(), 1);
        ftb_filter.addFilterItem(bean3.getTabName(), bean3.getFilterData(), bean3.getPopupType(), 2);
        ftb_filter.addFilterItem(bean4.getTabName(), bean4.getFilterData(), bean4.getPopupType(), 3);
        ftb_filter.addFilterItem(bean5.getTabName(), bean5.getFilterData(), bean5.getPopupType(), 4);
    }

    @Override
    public BasePresenter getPresenter() {
        return null;
    }

    private void loadListener() {
        ftb_filter.setOnSelectResultListener(this);
    }


    @OnClick({
    })
    public void bindViewClick(View view) {
        switch (view.getId()) {
        }
    }

    @Override
    protected void initStatusbar() {
        super.initStatusbar();
    }

    /**
     * 获取assets下的json文件
     *
     * @param context
     * @param fileName
     * @return
     */
    public static String getJson(Context context, String fileName) {
        //将json数据变成字符串
        StringBuilder stringBuilder = new StringBuilder();
        try {
            //获取assets资源管理器
            AssetManager assetManager = context.getAssets();
            //通过管理器打开文件并读取
            BufferedReader bf = new BufferedReader(new InputStreamReader(
                    assetManager.open(fileName), "utf-8"));
            String line;
            while ((line = bf.readLine()) != null) {
                stringBuilder.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return stringBuilder.toString();
    }

    /**
     * 将字符串转换为 对象
     *
     * @param json
     * @param type
     * @return
     */
    public static <T> T JsonToObject(String json, Class<T> type) {
        try {
            Gson gson = new Gson();
            return gson.fromJson(json, type);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }


    @Override
    public void onSelectResult(FilterResultBean resultBean) {
        String message="";
        if(resultBean.getPopupType() == 3) {
            List<FilterResultBean.MulTypeBean> list = resultBean.getSelectList();
            for (int i = 0; i < list.size(); i++) {
                FilterResultBean.MulTypeBean bean = list.get(i);
                if (i == (list.size() - 1)) {
                    message = message + bean.getItemName();
                } else {
                    message = message + bean.getItemName() + ",";
                }
            }
        } else {
            message =resultBean.getItemId()+":"+resultBean.getName();
        }

        Toast.makeText(this,message,Toast.LENGTH_LONG).show();
    }

    @Override
    public void onSelectResultList(List<FilterResultBean> resultBeans) {
        String message="";
        List<FilterResultBean> list = resultBeans;
        for (int i = 0; i < list.size(); i++) {
            FilterResultBean bean = list.get(i);
            if (i == (list.size() - 1)) {
                message = message + bean.getName();
            } else {
                message = message + bean.getName() + ",";
            }
        }
        Toast.makeText(this,message,Toast.LENGTH_LONG).show();
    }
}
