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

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import com.ejoy.tool.R;
import com.ejoy.tool.ui.base.base_activity.BaseActivity;
import com.ejoy.tool.ui.data.resource.GlobalDataProvider;
import com.ejoy.tool.ui.mvp.base.BasePresenter;
import com.module.ires.bean.utils.EDensityUtils;
import com.module.iviews.popup.AdapterItem;
import com.module.iviews.popup.EUISimpleAdapter;
import com.module.iviews.popup.EUISimplePopup;
import com.module.iviews.popup.qq.QQPopupWindow;
import com.module.iviews.popup.weibo.WeiboPopupWindow;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.annotations.Nullable;

/**
 * CN:      IPopupwindowActivity
 * Author： JSYL-DINGCL (dingcl@jsyl.com.cn)
 * Date:   2020/1/2
 * Des:    TODO:Popupwindow使用集合及仿Popupwindow
 */
public class IPopupwindowActivity extends BaseActivity{
    private QQPopupWindow mQQPopupWindow;
    private List<String> mPopData;
    private EUISimplePopup mListPopup;
    private WeiboPopupWindow mWeiboPopupWindow;

    @Override
    protected void initRestore(@Nullable Bundle savedInstanceState) {
    }

    @Override
    protected int getContentViewId() {
        return R.layout.activity_popupwindow_all;
    }

    @Override
    protected Object registSatusbarBgcolor() {
        return baseTransparent;
    }

    @Override
    protected void initView(View mRootView) {
        initPop();
        initListPopup();
    }

    private void initPop() {
        mQQPopupWindow = new QQPopupWindow(this).builder();
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

    /**
     * 筛选菜单
     * @param view
     */
    public void PWWbFilter(View view) {
        showActivity(this,IPopupwindowFilterActivity.class);
    }

    /**
     * 仿微博弹簧式样弹窗
     * @param view
     */
    public void PWWbDialog(View view) {
        mListPopup.showDown(view);
    }
    /**
     * 仿QQ的右上角选项弹窗
     * @param view
     */
    public void qqPopup(View view) {
        showPop(view,false);

    }

    /**
     * 仿QQ的右上角选项弹窗
     * @param view
     */
    public void qqPopupshow(View view) {
        showPop(view,true);
    }


    private void showPop(View view,boolean ishsowLine) {
        //弹出选项弹窗
        mQQPopupWindow
                //TODO:-必须在showIt()；方法之前调用，否则不生效-
                .showLine(ishsowLine)
                //列表数据
                .showIt(mPopData = GlobalDataProvider.qqPopItems() == null ? new ArrayList<>() : GlobalDataProvider.qqPopItems())
                .showAtBottom(view)
                //必须加上背景透明度切换
                .toggleBright()
                .setIQQPopupItemListener(new QQPopupWindow.IQQPopupItemListener() {
                    @Override
                    public void onPopItemClick(View view, int position) {
//                        iToast.showISimpleToast( position+" : "+mPopData.get(position));
                    }
                });
    }


    public void ivBack(View view) {
        finish();
    }

    private void initListPopup() {
        mListPopup = new EUISimplePopup(_mActivity, GlobalDataProvider.wbDialogItems)
                .create(EDensityUtils.dp2px(_mActivity, 170), new EUISimplePopup.OnPopupItemClickListener() {
                    @Override
                    public void onItemClick(EUISimpleAdapter adapter, AdapterItem item, int position) {
                        iToast.showIDefaultImgResToast(item.getTitle().toString());
                        switch (position){
                            case 0:
                                View view = adapter.getView(position, null, null);
                                showWeibPopupWindow(view);
                                break;
                            case 1:
                                showActivity(_mActivity,IPopupWindowPopmenuActivity.class,R.anim.push_bottom_in, R.anim.push_bottom_out);
                                break;
                        }
                    }
                })
                .setHasDivider(true);
    }

    private void showWeibPopupWindow(View view) {
        if (null == mWeiboPopupWindow) {
            mWeiboPopupWindow = new WeiboPopupWindow(this);
            mWeiboPopupWindow.init();
        }
        mWeiboPopupWindow.showWindow(view,100);
    }


}
