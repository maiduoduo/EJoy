package com.ejoy.tool.ui.activity.iosdialog;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ejoy.tool.R;
import com.ejoy.tool.scaffold.utils.IBitmapUtils;
import com.ejoy.tool.ui.base.base_activity.BaseActivity;
import com.ejoy.tool.ui.data.resource.GlobalDataProvider;
import com.ejoy.tool.ui.mvp.base.BasePresenter;
import com.imaiduoduo.bottomsheet.BottomSheet;
import com.imaiduoduo.bottomsheet.BottomSheetHelper;
import com.module.ires.bean.utils.WidgetUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * CN:      IDialogBottomsheetActivity
 * Author： JSYL-DINGCL (dingcl@jsyl.com.cn)
 * Date:   2019/12/15
 * Des:    类于Bottomsheet  Dialog
 */
public class IDialogBottomsheetActivity extends BaseActivity {

    @BindView(R.id.mTitleBar)
    FrameLayout mTitleBar;
    @BindView(R.id.mRecyclerView)
    RecyclerView mRecyclerView;
    @BindView(R.id.ivBACK)
    ImageView mIvBACK;
    private BaseQuickAdapter mAdapter;
    private List<String> mData;
    private BottomSheet mBottomSheet;
    private int action;
    private String sYName = "石原里美";

    @Override
    protected void initRestore(@Nullable Bundle savedInstanceState) {

    }


    @Override
    protected int getContentViewId() {
        return R.layout.activity_dialog_likebottomsheet;
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
    protected void initView(View mRootView) {
        action = getIntent().getFlags();
        if (mData != null) mData.clear();
        else mData = new ArrayList<>();
        WidgetUtils.initRecyclerView(mRecyclerView, 1, R.color.LGray3);
        mRecyclerView.setAdapter(mAdapter = new BaseQuickAdapter<String, BaseViewHolder>(android.R.layout.simple_list_item_1, mData) {
            @Override
            protected void convert(BaseViewHolder helper, String item) {
                helper.setText(android.R.id.text1, item == null ? "" : item);
            }
        });
    }


    @Override
    protected void initData() {
        mAdapter.setNewData(mData = GlobalDataProvider.likeBottomsheetData());
    }

    @Override
    protected void addListener() {
        mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                showDialog(position);

            }
        });
        mIvBACK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    @Override
    public BasePresenter getPresenter() {
        return null;
    }

    @SuppressWarnings("deprecation")
    @Nullable
    @Override
    protected Dialog onCreateDialog(final int position, Bundle args) {
        switch (position) {
            case 0:
                mBottomSheet = new BottomSheet
                        .Builder(_mActivity)
                        .icon(IBitmapUtils.getRoundedBitmap(_mActivity, R.mipmap.img_sylm))
                        .title("To ".concat(sYName))
                        .sheet(R.menu.xml_list)
                        .listener(new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                callonClick(sYName, which);
                            }
                        })
                        .build();
                break;
            case 1:
                mBottomSheet = new BottomSheet.Builder(_mActivity)
                        .sheet(R.menu.noicon)
                        .listener(new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                callonClick(sYName, which);
                            }
                        }).build();
                break;

            case 2:
                mBottomSheet = new BottomSheet.Builder(_mActivity)
                        .darkTheme()
                        .title("To ".concat(sYName)+"B")
                        .sheet(R.menu.xml_list)
                        .listener(new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                callonClick(sYName, which);
                            }
                        }).build();
                break;
            case 3://Grid
                mBottomSheet = new BottomSheet.Builder(_mActivity)
                        .sheet(R.menu.xml_list1)
                        .listener(new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                callonClick(sYName, which);
                            }
                        }).grid().build();
                break;
            case 4://style
                mBottomSheet = new BottomSheet.Builder(_mActivity, com.imaiduoduo.bottomsheet.R.style.BottomSheet_StyleDialog)
                        .title("To".concat(sYName)+"H")
                        .sheet(R.menu.xml_list)
                        .listener(new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                callonClick(sYName, which);
                            }
                        }).build();
                break;
            case 5://Style for Theme
                mBottomSheet = new BottomSheet.Builder(_mActivity)
                        .title("To ".concat(sYName)+"C")
                        .sheet(R.menu.longlist)
                        .listener(new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                callonClick(sYName, which);
                            }
                        }).limit(R.integer.bs_initial_list_row).build();
                break;
            case 6://share Action
                mBottomSheet = getShareActions("Hello ".concat(sYName)+"D")
                        .title("Share To ".concat(sYName)+"D")
                        .limit(R.integer.no_limit)
                        .build();
                break;
            case 7://Full
                mBottomSheet = getShareActions("Hello ".concat(sYName)+"E")
                        .title("Share To ".concat(sYName)+"E")
                        .build();
                break;
            case 8://Menu Manipulate
                mBottomSheet = new BottomSheet.Builder(_mActivity)
                        .icon(IBitmapUtils.getRoundedBitmap(_mActivity, R.mipmap.img_sylm))
                        .title("To ".concat(sYName)+"F")
                        .sheet(R.menu.xml_list)
                        .listener(new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                callonClick(sYName, which);
                            }
                        }).build();
                final Menu menu = mBottomSheet.getMenu();
                menu.getItem(0).setTitle("MenuClickListener");
                menu.getItem(0).setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        callonClick("You can set OnMenuItemClickListener for each item", R.id.share);
                        return true;
                    }
                });
                menu.getItem(1).setVisible(false);
                menu.getItem(2).setEnabled(false);
                menu.add(Menu.NONE, 23, Menu.NONE, "Fresh meal!");
                menu.findItem(23).setIcon(R.mipmap.icon_logo_qzone);
                menu.findItem(23).setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        callonClick(sYName, 0);
                        return true;
                    }
                });
                menu.setGroupVisible(android.R.id.empty, false);
                break;
            case 9://HeaderLayout
                mBottomSheet = new BottomSheet
                        .Builder(this, R.style.BottomSheet_CustomizedDialog)
                        .icon(R.mipmap.img_sylm_longpic)
                        .grid().title("To ".concat(sYName)+"G")
                        .sheet(R.menu.xml_list)
                        .listener(new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                callonClick(sYName, which);
                            }
                        }).build();
                mBottomSheet.setOnShowListener(new DialogInterface.OnShowListener() {
                    @Override
                    public void onShow(DialogInterface dialog) {
                        iToast.showISimpleToast("I'm showing");
                    }
                });
                mBottomSheet.setOnDismissListener(new DialogInterface.OnDismissListener() {
                    @Override
                    public void onDismiss(DialogInterface dialog) {
                        iToast.showISimpleToast("I'm dismissing");
                    }
                });
                break;
        }
        return mBottomSheet;
    }

    private void callonClick(String name, int which) {
        switch (which) {
            case R.id.share:
                iToast.showISimpleToast("Share to " + name);
                break;
            case R.id.upload:
                iToast.showISimpleToast("Upload for " + name);
                break;
            case R.id.call:
                iToast.showISimpleToast("Call to " + name);
                break;
            case R.id.help:
                iToast.showISimpleToast("Help me!");
                break;
        }
    }

    private BottomSheet.Builder getShareActions(String text) {
        final Intent shareIntent = new Intent(Intent.ACTION_SEND);
        shareIntent.setType("text/plain");
        shareIntent.putExtra(Intent.EXTRA_TEXT, text);

        return BottomSheetHelper.shareAction(this, shareIntent);
    }


}
