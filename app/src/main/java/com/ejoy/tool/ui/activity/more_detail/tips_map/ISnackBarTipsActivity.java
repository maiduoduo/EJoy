package com.ejoy.tool.ui.activity.more_detail.tips_map;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ejoy.tool.R;
import com.ejoy.tool.app.AppConstant;
import com.ejoy.tool.scaffold.view.PowerfulRecyclerView;
import com.ejoy.tool.ui.base.base_activity.IBaseActivity;
import com.ejoy.tool.ui.data.resource.GlobalDataProvider;
import com.kongzue.baseframework.interfaces.DarkStatusBarTheme;
import com.kongzue.baseframework.interfaces.Layout;
import com.kongzue.dialog.util.BlurView;
import com.module.ires.bean.bean.IToastTipGridBean;
import com.module.ires.bean.utils.EDensityUtils;
import com.module.iviews.tips.snackbar.SweetSnackbar;
import com.module.iviews.tips.toast.SweetToast;
import com.module.iviews.tips.util.TipsSnackbarUtils;
import com.module.iviews.utils.WidgetUtils;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;


/**
 * @ClassName: ISnackBarTipsActivity
 * @Author: maiduoduo
 * @BLOG: https://blog.csdn.net/Maiduoudo
 * @Date: 2021/04/01
 * @des: snackbar自定义扩展集合
 */
@Layout(R.layout.activity_snackbar_tip)
@DarkStatusBarTheme(true)
public class ISnackBarTipsActivity extends IBaseActivity {

    @BindView(R.id.blur)
    BlurView blur;
    @BindView(R.id.snackbarRecyclerview)
    PowerfulRecyclerView mSnackbarRecyclerview;
    @BindView(R.id.btnSnackbarTarget)
    Button mBtnSnackbarTarget;
    @BindView(R.id.box_table_child)
    LinearLayout boxTableChild;

    private Context mContext;
    private BaseQuickAdapter mAdapter;
    private int snackbarAnimIndex = 0;
    private int[][] snackbarAnims = new int[][]{
            {R.anim.anim_tip_scale_enter,R.anim.anim_tip_scale_exit},
            {R.anim.anim_tip_slide_in_left,R.anim.anim_tip_slide_out_left},
            {R.anim.anim_sweet_toast_enter_miui,R.anim.anim_sweet_toast_exit}
    };



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
        mContext = ISnackBarTipsActivity.this;
        blur.setOverlayColor(Color.argb(100, 235, 235, 235));
        blur.setRadius(me, 0, 0);
        setDarkNavigationBarTheme(false);
        initGrid();
    }


    @Override
    public void initDatas() {

    }


    @Override
    public void setEvents() {
        mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter baseQuickAdapter, View view, int position) {
                switch (position) {
                    case 0:
                        //自定义动画
                        TipsSnackbarUtils.Long(mBtnSnackbarTarget,"Snackbar自定义动画")
                                .anim(snackbarAnims[snackbarAnimIndex][0],snackbarAnims[snackbarAnimIndex][1])
                                .backColor(AppConstant.color_cn_zhuqing)
                                .show();
                        snackbarAnimIndex = (++snackbarAnimIndex)%(snackbarAnims.length);
                        break;
                    case 1:
                        //显示在顶部
                        TipsSnackbarUtils.Long(mBtnSnackbarTarget,"从顶部滑入界面")
                                .gravityFrameLayout(Gravity.TOP)
                                .anim(R.anim.anim_tip_push_down_in,R.anim.anim_tip_push_up_out)
                                .backColor(AppConstant.color_flyme_blue_sky)
                                .show();
                        break;
                    case 2:
                        //点击回调
                        TipsSnackbarUtils.Long(mBtnSnackbarTarget,"设置监听")
                                .gravityFrameLayout(Gravity.CENTER)
                                .anim(R.anim.anim_tip_slide_in_left,R.anim.anim_tip_slide_out_left)
                                .backColor(AppConstant.color_cn_dailan)
                                .setAction("按钮", new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        SweetToast.makeText(me,"点击了按钮!").showImmediate();
                                    }
                                }).setCallback(new SweetSnackbar.Callback() {
                                    @Override
                                    public void onDismissed(SweetSnackbar snackbar, @DismissEvent int event) {
                                        super.onDismissed(snackbar, event);
                                        try {
                                            SweetToast.makeText(me,"onDismissed!").showImmediate();
                                        }catch (Exception e){
                                            Log.e(_TAG,"e:"+e.getLocalizedMessage());
                                        }
                                    }
                                    @Override
                                    public void onShown(SweetSnackbar snackbar) {
                                        super.onShown(snackbar);
                                        try {
                                            SweetToast.makeText(me,"onShown!").showImmediate();
                                        }catch (Exception e){
                                            Log.e(_TAG,"e:"+e.getLocalizedMessage());
                                        }
                                    }
                                }).show();
                        break;
                    case 3:
                        //工具类
                        int total2 = 0;
                        int[] l3 = new int[2];
                        getWindow().findViewById(android.R.id.content).getLocationInWindow(l3);
                        total2 = l3[1];
                        TipsSnackbarUtils.Custom(mBtnSnackbarTarget,"同时设置多重属性",1000*5)
                                .leftAndRightDrawable(R.mipmap.icon_logo_wechat,R.mipmap.icon_arrow_right)
                                .backColor(AppConstant.color_cn_wuhei)
                                .radius(5,0, Color.TRANSPARENT)
                                .bellow(boxTableChild,total2,16,16)
                                .anim(R.anim.anim_tip_push_down_in,R.anim.anim_tip_push_up_out)
                                .show();

                        break;


                    default:
                        break;
                }
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
            default:
                break;

        }
    }


    private void initGrid() {
        List<IToastTipGridBean> toastGridListData = GlobalDataProvider.getSnackbarGridListData();
        WidgetUtils.initGridRecyclerView(mSnackbarRecyclerview, 2, EDensityUtils.dp2px(me, 2));
        mSnackbarRecyclerview.setAdapter(mAdapter = new BaseQuickAdapter<IToastTipGridBean, BaseViewHolder>(R.layout.item_toast_tip_grid, toastGridListData) {
            @Override
            protected void convert(BaseViewHolder helper, IToastTipGridBean item) {
                TextView itemLabel = helper.getView(R.id.item_label);
                TextView itemName = helper.getView(R.id.item_name);
                helper.setText(R.id.item_name, item.getGridTitle());
                helper.setText(R.id.item_EN, item.getGridEn());
                String labelText = item.getLabelText();
                if (labelText != null && getString(R.string.LABEL_HOT).equals(labelText)) {
                    itemName.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
                } else {
                    itemName.setTypeface(Typeface.defaultFromStyle(Typeface.NORMAL));
                }
                if (item.isHotLabel()) {
                    itemLabel.setVisibility(View.VISIBLE);
                    itemLabel.setText(item.getLabelText());
                } else {
                    itemLabel.setVisibility(View.GONE);
                    itemLabel.setText("");
                }

            }
        });
    }


}


