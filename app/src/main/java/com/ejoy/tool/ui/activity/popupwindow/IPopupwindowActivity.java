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
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.text.method.LinkMovementMethod;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.animation.OvershootInterpolator;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.ejoy.tool.R;
import com.ejoy.tool.ui.base.base_activity.BaseActivity;
import com.ejoy.tool.ui.data.resource.GlobalDataProvider;
import com.ejoy.tool.ui.mvp.base.BasePresenter;
import com.google.gson.Gson;
import com.module.ires.bean.utils.EBlurHelper;
import com.module.ires.bean.utils.EDensityUtils;
import com.module.ires.bean.utils.ETextviewUtils;
import com.module.ires.bean.utils.WidgetUtils;
import com.module.iviews.popup.AdapterItem;
import com.module.iviews.popup.EUISimpleAdapter;
import com.module.iviews.popup.EUISimpleExpandableListAdapter;
import com.module.iviews.popup.EUISimpleExpandablePopup;
import com.module.iviews.popup.EUISimplePopup;
import com.module.iviews.popup.ExpandableItem;
import com.module.iviews.popup.baseuse.IListPopupwindow;
import com.module.iviews.popup.baseuse.IPopupwindowUse;
import com.module.iviews.popup.bean.GalleryBean;
import com.module.iviews.popup.blurPop.BlurPopupWindow;
import com.module.iviews.popup.menu.IScreenMenuPopWindow;
import com.module.iviews.popup.menu.bean.FiltrateBean;
import com.module.iviews.popup.qq.IQQPopupWindow;
import com.module.iviews.popup.weibo.AlertDesignViewDialog;
import com.module.iviews.popup.weibo.ImageInfoBean;
import com.module.iviews.popup.weibo.WeiboPopupWindow;
import com.module.iviews.popup.weibo.adpter.ImgListAdapter;
import com.module.iviews.view.widget.IExpandableLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.annotations.Nullable;

import static com.module.iviews.popup.baseuse.IPopupwindowUse.LocationType.BOTTOM_CENTER;

/**
 * CN:      IPopupwindowActivity
 * Author： JSYL-DINGCL (dingcl@jsyl.com.cn)
 * Date:   2020/1/2
 * Des:    TODO:Popupwindow使用集合及仿Popupwindow
 */
public class IPopupwindowActivity extends BaseActivity implements IListPopupwindow.OnPopItemSelectedListener {
    @BindView(R.id.qqPopupshow)
    ImageButton mIbnqqPopupshow;
    @BindView(R.id.multiMenuShow)
    TextView mMultiMenuShow;
    @BindView(R.id.title)
    FrameLayout mTitle;
    @BindView(R.id.Ppictitle)
    TextView mPpictitle;
    @BindView(R.id.expandable_layout)
    IExpandableLayout mExpandableLayout;
    @BindView(R.id.iv_indicator)
    ImageView mIvIndicator;
    @BindView(R.id.pop_root)
    ConstraintLayout mPopRoot;
    @BindView(R.id.tvWarn)
    TextView mTvWarn;
    @BindView(R.id.tvWarn2)
    TextView mTvWarn2;
    private IQQPopupWindow mQQPopupWindow;
    private List<String> mPopData;
    private WeiboPopupWindow mWeiboPopupWindow;
    public EUISimplePopup mListPopup;
    private EUISimplePopup mMenuPopup;
    private EUISimpleExpandablePopup mExpandableListPopup;
    private IScreenMenuPopWindow screenPopWindow;
    //选取相册弹窗
    private IListPopupwindow iListPopupwindow;
    private List<GalleryBean> mGalleryList;
    private ArrayList<ImageInfoBean> mSelectImgList = new ArrayList<ImageInfoBean>();

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
        blurGlobal();
        setExpand();
        initPop();
        initListPopup();
        initMenuPopup();
        initExpandableListPopup();
    }

    private void blurGlobal() {
        //高斯模糊背景
        new EBlurHelper().setContainerView(mPopRoot)
                .setImageResourse(R.drawable.img_bg5)
                .setRadius(20f)
                .setContext(_mActivity)
                .build();
    }

    private void setExpand() {
        mExpandableLayout.setInterpolator(new OvershootInterpolator());
        mExpandableLayout.setOnExpansionChangedListener(new IExpandableLayout.OnExpansionChangedListener() {
            @Override
            public void onExpansionChanged(float expansion, int state) {
                if (mIvIndicator != null) {
                    mIvIndicator.setRotation(expansion * 90);
                }
            }
        });
        mExpandableLayout.setExpanded(false, false);
    }

    private void initPop() {
        mQQPopupWindow = new IQQPopupWindow(this).builder();
    }

    @Override
    protected void initData() {
        //这一句很重要，否则ClickableSpan内的onClick方法将无法触发！！
        mTvWarn.setMovementMethod(LinkMovementMethod.getInstance());
        mTvWarn2.setMovementMethod(LinkMovementMethod.getInstance());
    }

    @Override
    protected void addListener() {

    }

    @Override
    public BasePresenter getPresenter() {
        return null;
    }


    @OnClick({
            R.id.qqPopupshow,
            R.id.qqPopupshowText,
            R.id.tvCustomPWFilter,
            R.id.popBaseUse,
            R.id.tvWb,
            R.id.Epop_normal,
            R.id.Epop_extends,
            R.id.Epop_menu,
            R.id.multiMenuShow,
            R.id.Ppictitle,
            R.id.ECookieBar,
            R.id.albumListPop,
            R.id.popCustomEdit,
            R.id.rlPopupRoot,
            R.id.albumTitle,
            R.id.wbalbumList,
            R.id.popTipMenu,
            R.id.EBlurPop
    })
    public void bindViewClick(View view) {
        switch (view.getId()) {
            case R.id.qqPopupshow://仿QQ的右上角选项弹窗
                showPop(view, true);
                break;
            case R.id.qqPopupshowText://仿QQ的右上角选项弹窗
                showPop(mIbnqqPopupshow, false);
                break;
            case R.id.tvCustomPWFilter://Popupwindow筛选菜单
                showActivity(this, IPopupwindowFilterActivity.class);
                break;
            case R.id.tvWb://Popupwindow仿微博弹簧弹窗
                mListPopup.showDown(view);
                break;
            case R.id.multiMenuShow://多类型筛选弹框
                showMultiMenuShow(BOTTOM_CENTER);
                break;
            case R.id.popBaseUse://Popupwindow基础封装
                showActivity(_mActivity, IPopupwindowUseActivity.class);
                break;
            case R.id.popCustomEdit://自定义依附在输入法之上的Bottom弹窗
                showActivity(_mActivity, IPopupwindowTopEditActivity.class);
                break;
            case R.id.Epop_normal://普通选择框
                mListPopup.showDown(view);
                break;
            case R.id.Epop_extends://可伸缩选择框
                mExpandableListPopup.clearExpandStatus();
                mExpandableListPopup.showDown(view);
                break;
            case R.id.Epop_menu://弹出菜单
                mMenuPopup.showDown(view);
                break;
            case R.id.Ppictitle://相机胶卷弹窗
                showAlbumListPopup();
                break;
            case R.id.albumListPop://相机胶卷弹窗
                showAlbumListPopup();
                break;
            case R.id.ECookieBar://CookieBar2
                showActivity(_mActivity, ICookieBarActivity.class);
                break;
            case R.id.albumTitle:
                showAlbumListPopup();
                break;
            case R.id.wbalbumList:
                Intent intent = new Intent(_mActivity, IWBAlbumActivity.class);
                intent.putParcelableArrayListExtra("selectedImglist", mSelectImgList);
                startActivityForResult(intent, 0);
                break;
            case R.id.popTipMenu:
                showActivity(_mActivity, IPopupwindowTipMenuActivity.class);
                break;
            case R.id.EBlurPop://背景Blur化的弹窗
                new BlurPopupWindow.Builder(_mActivity).setContentView(R.layout.layout_dialog_like_blur)
                        .bindClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Toast.makeText(v.getContext(), "Click Button", Toast.LENGTH_SHORT).show();
                            }
                        }, R.id.dialog_like_bt)
                        .setGravity(Gravity.CENTER)
                        .setScaleRatio(0.2f)
                        .setBlurRadius(10)
                        .setTintColor(0x30000000)
                        .setDismissOnClickBack(true)
                        .build()
                        .show();
                break;
            case R.id.rlPopupRoot:
                if (mExpandableLayout.isExpanded()) {
                    mExpandableLayout.collapse();
                } else {
                    mExpandableLayout.expand();
                }
                break;
            default:
                break;
        }
    }

    /**
     * 相册列表选择弹窗
     */
    private void showAlbumListPopup() {
        iListPopupwindow = new IListPopupwindow(_mActivity, mGalleryList = GlobalDataProvider.getAlbumInfo() == null ? new ArrayList<>()
                : GlobalDataProvider.getAlbumInfo());
        iListPopupwindow.setPictureTitleView(mPpictitle);
        iListPopupwindow.setOnItemSelectedListener(this);
        if (iListPopupwindow.isShowing()) {
            iListPopupwindow.dismiss();
        } else {
            iListPopupwindow.showAsDropDown(mPpictitle);
            mPpictitle.setText(mGalleryList.get(0).getTitle());
        }
    }


    private void showPop(View topview, boolean ishsowLine) {
        //弹出选项弹窗
        mQQPopupWindow
                //TODO:-必须在showIt()；方法之前调用，否则不生效-
                .showLine(ishsowLine)
                //列表数据
                .showIt(mPopData = GlobalDataProvider.qqPopItems() == null ? new ArrayList<>() : GlobalDataProvider.qqPopItems())
                .showAtBottom(topview)
                //必须加上背景透明度切换
                .toggleBright()
                .setIQQPopupItemListener(new IQQPopupWindow.IQQPopupItemListener() {
                    @Override
                    public void onPopItemClick(View view, int position) {
                        switch (position) {
                            case 0://气泡提示
                                break;
                            case 1://EasyPopup
                                break;
                            case 2://EUIPopup
                                break;
                            default:
                                break;
                        }
                        iToast.showISimpleToast(position + " : " + mPopData.get(position));
                    }
                });
    }


    public void ivBack(View view) {
        finish();
    }

    @Override
    protected String[] fixPopData() {
        return GlobalDataProvider.wbDialogItems;
    }

    /**
     * 普通选择框
     */
    private void initListPopup() {
        mListPopup = new EUISimplePopup(_mActivity, GlobalDataProvider.wbDialogItems);
        mListPopup.create(EDensityUtils.dp2px(_mActivity, 170), new EUISimplePopup.OnPopupItemClickListener() {
            @Override
            public void onItemClick(EUISimpleAdapter adapter, AdapterItem item, int position) {
                iToast.showIDefaultImgResToast(item.getTitle().toString());
                switch (position) {
                    case 0:
                        View view = adapter.getView(position, null, null);
                        showWeibPopupWindow(view);
                        break;
                    case 1:
                        showActivity(_mActivity, IPopupWindowPopmenuActivity.class, R.anim.push_bottom_in, R.anim.push_bottom_out);
                        break;
                    default:
                        break;
                }
                mListPopup.dismiss();
            }
        })
                .setHasDivider(true);
    }

    private void showWeibPopupWindow(View view) {
        if (null == mWeiboPopupWindow) {
            mWeiboPopupWindow = new WeiboPopupWindow(this);
            mWeiboPopupWindow.init();
        }
        mWeiboPopupWindow.showWindow(view, 100);
    }


    /**
     * 可伸缩选择框
     */
    private void initMenuPopup() {
        mMenuPopup = new EUISimplePopup(_mActivity, GlobalDataProvider.menuItems)
                .create(new EUISimplePopup.OnPopupItemClickListener() {
                    @Override
                    public void onItemClick(EUISimpleAdapter adapter, AdapterItem item, int position) {
                        iToast.showIDefaultImgResToast(item.getTitle().toString());
                        mMenuPopup.dismiss();
                    }
                })
                .setHasDivider(true);
//        mMenuPopup.setPopupTopBottomMinMargin(-30);
//        mMenuPopup.setPopupLeftRightMinMargin(-50);
//        mMenuPopup.setPositionOffsetX(-30);
    }

    /**
     * 弹出菜单
     */
    private void initExpandableListPopup() {
        mExpandableListPopup = new EUISimpleExpandablePopup(_mActivity, GlobalDataProvider.expandableItems)
                .create(EDensityUtils.dp2px(_mActivity, 200), EDensityUtils.dp2px(_mActivity, 200))
                .setOnExpandableItemClickListener(false, new EUISimpleExpandablePopup.OnExpandableItemClickListener() {
                    @Override
                    public void onExpandableItemClick(EUISimpleExpandableListAdapter adapter, ExpandableItem group, int groupPosition, int childPosition) {
                        iToast.showIDefaultImgResToast(group.getChildItem(childPosition).getTitle() + "");
                        mExpandableListPopup.dismiss();
                    }
                })
                .setHasDivider(false);
    }


    /**
     * 多类型筛选选择器
     *
     * @param bottomCenter
     */
    private void showMultiMenuShow(IPopupwindowUse.LocationType bottomCenter) {
        IPopupwindowUse popWindow = new IPopupwindowUse.PopupWindowBuilder(this)
                .setView(R.layout.layout_pop_bottom)
                .setFocusable(true)
                .setOutsideTouchable(true)
                .create();
        popWindow.showPopupWindow(mMultiMenuShow, bottomCenter);
        View contentView = popWindow.getContentView();
        TextView tvSub_a = contentView.findViewById(R.id.tvSub_a);
        TextView tvSub_b = contentView.findViewById(R.id.tvSub_b);
        tvSub_a.setText("单选模式");
        tvSub_b.setText("多选模式");
        tvSub_a.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showMultiChoosePop(true);
                popWindow.dissmiss();
            }
        });

        tvSub_b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showMultiChoosePop(false);
                popWindow.dissmiss();
            }
        });

    }

    /**
     * 多类型筛选弹框
     *
     * @param setSingle
     */
    private void showMultiChoosePop(boolean setSingle) {
        List<FiltrateBean> multiMenuData = GlobalDataProvider.getMultiMenuData();
        screenPopWindow = new IScreenMenuPopWindow(_mActivity, multiMenuData);
        //默认单选，因为共用的一个bean，这里调用reset重置下数据
        if (setSingle) {
            screenPopWindow.reset().build();
        } else {
            //设置多选，因为共用的一个bean，这里调用reset重置下数据
            screenPopWindow.setSingle(false).reset().build();
        }
        screenPopWindow.setAnimationStyle(R.style.PopMenuAnim);
        screenPopWindow.showAsDropDown(mTitle);
        screenPopWindow.setOnConfirmClickListener(new IScreenMenuPopWindow.OnConfirmClickListener() {
            @Override
            public void onConfirmClick(List<String> sectionAList, List<String> sectionBList) {
                if (sectionAList != null && sectionAList.size() > 0 && sectionBList != null && sectionBList.size() > 0) {
                    StringBuilder str = new StringBuilder();
                    for (String a : sectionAList) {
                        str.append(a).append(" ");
                    }
                    for (String b : sectionBList) {
                        str.append(b).append(" ");
                    }
                    iToast.showISimpleToast("" + str.toString());
                    screenPopWindow.dismiss();
                } else {
                    iToast.showISimpleToast("请各选择至少一项");
                }
            }
        });
    }

    @Override
    public void popItemSelected(String title) {
        mPpictitle.setText(title);
        iListPopupwindow.dismiss();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {
            case 0:
                if (data != null) {
                    mSelectImgList = data.getParcelableArrayListExtra("selectImgList");
                    initImgList();
                }
                break;
        }
    }

    AlertDesignViewDialog viewDialog;

    private void initImgList() {
        viewDialog = new AlertDesignViewDialog(_mActivity)
                .build(mSelectImgList)
                .setCancelable(true)
                .setScaleWidth(0.8)// 设置宽度，占屏幕宽度百分比
                .addOnAddPicClickListener(new AlertDesignViewDialog.OnAddPicClickListener() {
                    @Override
                    public void onAddPic() {
                        viewDialog.cancelIt();
                        Intent intent = new Intent(_mActivity, IWBAlbumActivity.class);
                        intent.putParcelableArrayListExtra("selectedImglist", mSelectImgList);
                        startActivityForResult(intent, 0);
                    }
                })
                .setRightButton("确定", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        iToast.showISimpleToast("退出");
                    }
                })
                .show();
    }
}
