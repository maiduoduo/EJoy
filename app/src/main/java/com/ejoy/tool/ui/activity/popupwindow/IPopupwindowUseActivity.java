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

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.ejoy.tool.R;
import com.ejoy.tool.ui.activity.MainActivity;
import com.ejoy.tool.ui.base.base_activity.BaseActivity;
import com.ejoy.tool.ui.data.resource.GlobalDataProvider;
import com.ejoy.tool.ui.mvp.base.BasePresenter;
import com.module.ires.bean.utils.EDensityUtils;
import com.module.ires.bean.utils.EResUtils;
import com.module.iviews.popup.baseuse.ICommonPopupWindow;
import com.module.iviews.popup.baseuse.IPopupwindowUse;
import com.module.iviews.popup.baseuse.ISimpleListPopupWindow;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

import static com.module.iviews.popup.baseuse.ICommonPopupWindow.LocationType.TOP_LEFT;
import static com.module.iviews.popup.baseuse.IPopupwindowUse.LocationType.BOTTOM_CENTER;
import static com.module.iviews.popup.baseuse.IPopupwindowUse.LocationType.TOP_CENTER;

import static com.module.iviews.popup.baseuse.ICommonPopupWindow.LocationType.BOTTOM_LEFT;
import static com.module.iviews.popup.baseuse.ICommonPopupWindow.LocationType.BOTTOM_RIGHT;
import static com.module.iviews.popup.baseuse.ICommonPopupWindow.LocationType.FROM_BOTTOM;
import static com.module.iviews.popup.baseuse.ICommonPopupWindow.LocationType.LEFT_BOTTOM;
import static com.module.iviews.popup.baseuse.ICommonPopupWindow.LocationType.LEFT_CENTER;
import static com.module.iviews.popup.baseuse.ICommonPopupWindow.LocationType.LEFT_TOP;
import static com.module.iviews.popup.baseuse.ICommonPopupWindow.LocationType.RIGHT_BOTTOM;
import static com.module.iviews.popup.baseuse.ICommonPopupWindow.LocationType.RIGHT_CENTER;
import static com.module.iviews.popup.baseuse.ICommonPopupWindow.LocationType.RIGHT_TOP;
import static com.module.iviews.popup.baseuse.ICommonPopupWindow.LocationType.TOP_RIGHT;

/**
 * CN:      IPopupwindowUseActivity
 * Author： JSYL-DINGCL (dingcl@jsyl.com.cn)
 * Date:   2020/1/6
 * Des:    TODO:Popupwindow基础封装
 */
public class IPopupwindowUseActivity extends BaseActivity {

    @BindView(R.id.bottomShow)
    LinearLayout mBottomShow;
    @BindView(R.id.topShow)
    LinearLayout mTopShow;
    @BindView(R.id.listShow)
    LinearLayout mListShow;
    @BindView(R.id.ScreenListshow)
    LinearLayout mScreenListshow;
    @BindView(R.id.BgChangeshow)
    LinearLayout mBgChangeshow;
    @BindView(R.id.Animateshow)
    LinearLayout mAnimateshow;
    @BindView(R.id.Dismissshow)
    LinearLayout mDismissshow;
    @BindView(R.id.title)
    FrameLayout mTitleBar;
    @BindView(R.id.btnAnchorPop)
    Button mBtnAnchorPop;

    private List<String> mData;
    private IPopupwindowUse mIPopupwindowUse;

    @Override
    protected void initRestore(@Nullable Bundle savedInstanceState) {

    }

    @Override
    protected Object registSatusbarBgcolor() {
        return baseWhite;
    }

    @Override
    protected boolean isRegistSatusbarFontDark() {
        return true;
    }

    @Override
    protected int getContentViewId() {
        return R.layout.activity_popupbaseuse;
    }

    @Override
    protected void initView(View mRootView) {

    }

    @Override
    protected void initData() {

    }

    @Override
    protected void addListener() {

    }


    @OnClick({
            R.id.bottomShow,
            R.id.topShow,
            R.id.listShow,
            R.id.ScreenListshow,
            R.id.BgChangeshow,
            R.id.Animateshow,
            R.id.Dismissshow,
            R.id.btnAnchorPop
    })
    public void bindViewClick(View view) {
        switch (view.getId()) {
            case R.id.bottomShow://底部
                showPopBottom(BOTTOM_CENTER);
                break;
            case R.id.topShow://顶部
                showPopTop(TOP_CENTER);
                break;
            case R.id.listShow://菜单
                showListMenuPop();
                break;
            case R.id.ScreenListshow://列表
                showListPopUpWindow();
                break;
            case R.id.BgChangeshow://背景变暗
                showPopTopWithDarkBg();
                break;
            case R.id.Animateshow://动画
                useInAndOutAnim();
                break;
            case R.id.Dismissshow://点击popupwindow外部不消失
                touchOutsideDontDisMiss();
                break;
            case R.id.btnAnchorPop://不同位置弹窗
                showAnchorPop();
                break;
            default:
                break;

        }
    }

    /**
     * 不同方向弹窗
     */
    private void showAnchorPop() {
        View contentView = LayoutInflater.from(this).inflate(R.layout.layout_popbase_listanchor, null);
        //处理popWindow 显示内容
        handleLogic(contentView);
        //创建并显示popWindow
        mIPopupwindowUse = new IPopupwindowUse.PopupWindowBuilder(this)
                .setView(contentView)
                .enableBackgroundDark(true) //弹出popWindow时，背景是否变暗
                .setBgDarkAlpha(0.8f) // 控制亮度
                .size(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
                .create()
                .showAsDropDown(mTitleBar, ((int) (EDensityUtils.getScreenWidth(_mActivity) * 0.5 - 150)), -15);
    }

    /**
     * ListPop
     */
    private void showListMenuPop() {
        View contentView = LayoutInflater.from(this).inflate(R.layout.layout_popbase_listmenu, null);
        //处理popWindow 显示内容
        handleLogic(contentView);
        //创建并显示popWindow
        mIPopupwindowUse = new IPopupwindowUse.PopupWindowBuilder(this)
                .setView(contentView)
                .create()
                .showAsDropDown(mListShow, ((int) (EDensityUtils.getScreenWidth(_mActivity) * 0.5 - 150)), -15);
    }

    ISimpleListPopupWindow listpopupWindow;

    private void showListPopUpWindow() {
        listpopupWindow = new ISimpleListPopupWindow
                .Builder(_mActivity)
                .seListNewData(mData = GlobalDataProvider.addListPopStringData() == null ? new ArrayList<>() : GlobalDataProvider.addListPopStringData())
                .setAnchorView(mScreenListshow)
                .setModal(true)
                .build()
                .addOnPopListItemListener(new ISimpleListPopupWindow.IOnPopListItemListener() {
                    @Override
                    public void OnItemClick(AdapterView<?> parent, View view, int position, long id) {
                        iToast.showISimpleToast(mData.get(position) + "");
                        listpopupWindow.dismiss();
                    }
                });
        listpopupWindow.setHorizontalOffset(EDensityUtils.dp2px(_mActivity, 150));
        listpopupWindow.setDropDownGravity(Gravity.RIGHT);//设置下拉列表的对齐方式。Gravity.START表示与参照控件左侧对齐，Gravity.END表示与参照控件右侧对齐。
        listpopupWindow.showPopup(true);
    }


    @Override
    public BasePresenter getPresenter() {
        return null;
    }

    public void ivBack(View view) {
        finish();
    }

    /**
     * 底部
     *
     * @param bottomCenter
     */
    private void showPopBottom(IPopupwindowUse.LocationType bottomCenter) {
        IPopupwindowUse popWindow = new IPopupwindowUse.PopupWindowBuilder(this)
                .setView(R.layout.layout_pop_bottom)
                .setFocusable(true)
                .setOutsideTouchable(true)
                .create();
        popWindow.showPopupWindow(mBottomShow, bottomCenter);

    }

    /**
     * 底部
     */
    private void showPopTop(IPopupwindowUse.LocationType topCenter) {
//        ListPopupWindow
        IPopupwindowUse popWindow = new IPopupwindowUse.PopupWindowBuilder(this)
                .setView(R.layout.layout_pop_top)
                .create();
        popWindow.showPopupWindow(mBottomShow, topCenter);
    }

    /**
     * 显示PopupWindow 同时背景变暗
     */
    private void showPopTopWithDarkBg() {
        View contentView = LayoutInflater.from(this).inflate(R.layout.layout_popbase_listmenu, null);
        LinearLayout llCancel = contentView.findViewById(R.id.llCancel);
        llCancel.setVisibility(View.GONE);
        //处理popWindow 显示内容
        handleLogic(contentView);
        //创建并显示popWindow
        mIPopupwindowUse = new IPopupwindowUse.PopupWindowBuilder(this)
                .setView(contentView)
                .enableBackgroundDark(true) //弹出popWindow时，背景是否变暗
                .setBgDarkAlpha(0.7f) // 控制亮度
                .setOnDissmissListener(new PopupWindow.OnDismissListener() {
                    @Override
                    public void onDismiss() {
                        Log.e("TAG", "onDismiss");
                    }
                })
                .create()
                .showAsDropDown(mBgChangeshow, ((int) (EDensityUtils.getScreenWidth(_mActivity) * 0.5 - 150)), -15);
    }

    /**
     * 动画
     */
    private void useInAndOutAnim() {
        View contentView = LayoutInflater.from(this).inflate(R.layout.layout_popbase_listmenu, null);
        //处理popWindow 显示内容
        handleLogic(contentView);
        mIPopupwindowUse = new IPopupwindowUse.PopupWindowBuilder(this)
                .setView(contentView)
                .setFocusable(true)
                .setOutsideTouchable(true)
                .setAnimationStyle(R.style.CustomPopWindowStyle)
                .create()
                .showAsDropDown(mAnimateshow, ((int) (EDensityUtils.getScreenWidth(_mActivity) * 0.5 - 150)), -15);
    }

    /**
     * 点击 PopupWindow 之外的地方不消失
     */
    private void touchOutsideDontDisMiss() {
        View view = LayoutInflater.from(this).inflate(R.layout.layout_popbase_listmenu, null);
        mIPopupwindowUse = new IPopupwindowUse.PopupWindowBuilder(this)
                .setView(view)
                .enableOutsideTouchableDissmiss(false)// 设置点击PopupWindow之外的地方，popWindow不关闭，如果不设置这个属性或者为true，则关闭
                .create();
        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mIPopupwindowUse.dissmiss();
            }
        };
        LinearLayout llCancel = view.findViewById(R.id.llCancel);
        llCancel.setVisibility(View.VISIBLE);
        llCancel.setOnClickListener(listener);
        view.setOnClickListener(listener);
        mIPopupwindowUse.showAsDropDown(mDismissshow, ((int) (EDensityUtils.getScreenWidth(_mActivity) * 0.5 - 150)), -15);
    }

    /**
     * 处理弹出显示内容、点击事件等逻辑
     *
     * @param contentView
     */
    private void handleLogic(View contentView) {
        List<String> list = GlobalDataProvider.addListPopStringData();
        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mIPopupwindowUse != null) {
                    mIPopupwindowUse.dissmiss();
                }
                String showContent = "";
                switch (v.getId()) {
                    case R.id.menu1:
                        showPopupWindow(TOP_LEFT);
                        break;
                    case R.id.menu2:
                        showPopupWindow(TOP_RIGHT);
                        break;
                    case R.id.menu3:
                        showPopupWindow(com.module.iviews.popup.baseuse.ICommonPopupWindow.LocationType.TOP_CENTER);
                        break;
                    case R.id.menu4:
                        showPopupWindow(BOTTOM_LEFT);
                        break;
                    case R.id.menu5:
                        showPopupWindow(BOTTOM_RIGHT);
                        break;
                    case R.id.menu6:
                        showPopupWindow(com.module.iviews.popup.baseuse.ICommonPopupWindow.LocationType.BOTTOM_CENTER);
                        break;
                    case R.id.menu7:
                        showPopupWindow(LEFT_TOP);
                        break;
                    case R.id.menu8:
                        showPopupWindow(LEFT_BOTTOM);
                        break;
                    case R.id.menu9:
                        showPopupWindow(LEFT_CENTER);
                        break;
                    case R.id.menu10:
                        showPopupWindow(RIGHT_TOP);
                        break;
                    case R.id.menu11:
                        showPopupWindow(RIGHT_BOTTOM);
                        break;
                    case R.id.menu12:
                        showPopupWindow(RIGHT_CENTER);
                        break;
                    case R.id.menu13:
                        showPopupWindow(FROM_BOTTOM);
                        break;
                    case R.id.rootView:
//                        showPopupWindow(TOP_LEFT);
                        break;
                }
            }
        };
        contentView.findViewById(R.id.menu1).setOnClickListener(listener);
        contentView.findViewById(R.id.menu2).setOnClickListener(listener);
        contentView.findViewById(R.id.menu3).setOnClickListener(listener);
        contentView.findViewById(R.id.menu4).setOnClickListener(listener);
        contentView.findViewById(R.id.menu5).setOnClickListener(listener);
        contentView.findViewById(R.id.menu6).setOnClickListener(listener);
        contentView.findViewById(R.id.menu7).setOnClickListener(listener);
        contentView.findViewById(R.id.menu8).setOnClickListener(listener);
        contentView.findViewById(R.id.menu9).setOnClickListener(listener);
        contentView.findViewById(R.id.menu10).setOnClickListener(listener);
        contentView.findViewById(R.id.menu11).setOnClickListener(listener);
        contentView.findViewById(R.id.menu12).setOnClickListener(listener);
        contentView.findViewById(R.id.menu13).setOnClickListener(listener);
        contentView.findViewById(R.id.rootView).setOnClickListener(listener);
    }


    private void showPopupWindow(ICommonPopupWindow.LocationType type) {
        ICommonPopupWindow popupWindow = new ICommonPopupWindow
                .Builder(this)
                .setLayoutId(R.layout.layout_pop_anchor)
                .setBackgroundDrawable(new ColorDrawable(Color.GRAY))
                .setAnimationStyle(R.style.PopupWindowScaleTheme)
                .setOutsideTouchable(true)
                .setTouchable(true)
                .setOnDismissListener(new PopupWindow.OnDismissListener() {
                    @Override
                    public void onDismiss() {
                    }
                })
                .build();
        popupWindow.showPopupWindow(mBtnAnchorPop, type);
    }

}
