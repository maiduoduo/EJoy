package com.ejoy.tool.ui.activity.more_detail.tips_map;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

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
import com.module.ires.bean.utils.EResUtils;
import com.module.iviews.tips.toast.SweetToast;
import com.module.iviews.tips.util.TipsScreenUtil;
import com.module.iviews.utils.WidgetUtils;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;


/**
 * @ClassName: IToastTipsActivity
 * @Author: maiduoduo
 * @BLOG: https://blog.csdn.net/Maiduoudo
 * @Date: 2021/03/31
 * @des: toast自定义扩展集合
 */
@Layout(R.layout.activity_toast_tip)
@DarkStatusBarTheme(true)
public class IToastTipsActivity extends IBaseActivity {

    @BindView(R.id.blur)
    BlurView blur;
    @BindView(R.id.toastRecyclerview)
    PowerfulRecyclerView mToastRecyclerview;
    @BindView(R.id.btnToastTarget)
    Button mBtnToastTarget;

    private Context mContext;
    private BaseQuickAdapter mAdapter;
    private int colorIndex = 0;
    private String[] colorTags = new String[]{
            "Custom BackgroundColor :信息",
            "Custom BackgroundColor :确认",
            "Custom BackgroundColor :警告",
            "Custom BackgroundColor :危险",
            "Custom BackgroundColor :竹青",
            "Custom BackgroundColor :紫檀",
            "Custom BackgroundColor :蓝灰",
            "Custom BackgroundColor :水绿",
            "Custom BackgroundColor :胭脂",
            "Custom BackgroundColor :翡翠",
            "Custom BackgroundColor :天蓝",
            "Custom BackgroundColor :鲜红"
    };

    private int[] colorValues = new int[]{
            AppConstant.color_info,
            AppConstant.color_confirm,
            AppConstant.color_warning,
            AppConstant.color_danger,
            AppConstant.color_cn_zhuqing,
            AppConstant.color_cn_zitan,
            AppConstant.color_cn_lanhui,
            AppConstant.color_cn_shuilv,
            AppConstant.color_cn_yanzhi,
            AppConstant.color_cn_feicui,
            AppConstant.color_flyme_blue_sky,
            AppConstant.color_flyme_red_bright
    };

    private int durationIndex = 0;
    private String[] durationTags = new String[]{
            "Toast.LENGTH_SHORT",
            "Toast.LENGTH_LONG",
            "Custom Duration :5 seconds"
    };
    private int[] durationValues = new int[]{
            (int) SweetToast.SHORT_DELAY,
            (int) SweetToast.LONG_DELAY,
            1000 * 5
    };


    private int animIndex = 0;
    private String[] animTags = new String[]{
            "Custom Animation 1",
            "Custom Animation 2",
            "Custom Animation 3",
            "Custom Animation 4"
    };
    private SweetToast.SweetToastWindowAnimations[] animValues = new SweetToast.SweetToastWindowAnimations[]{
            SweetToast.SweetToastWindowAnimations.AnimationDialog,
            SweetToast.SweetToastWindowAnimations.AnimationTranslucent,
            SweetToast.SweetToastWindowAnimations.AnimationToast,
            SweetToast.SweetToastWindowAnimations.AnimationActivity
    };
    private int[][] animationValues = new int[][]{
            {R.anim.anim_sweet_toast_enter, R.anim.anim_sweet_toast_exit},
            {R.anim.anim_tip_scale_enter, R.anim.anim_tip_scale_exit},
            {R.anim.anim_tip_slide_in_left, R.anim.anim_tip_slide_out_left},
            {R.anim.anim_tip_scale_enter2, R.anim.anim_tip_scale_exit2}
    };
    private int positionIndex = 0;
    private String[] positionTags = new String[]{
            "Custom Display position :leftTop",
            "Custom Display position :rightTop",
            "Custom Display position :leftBottom",
            "Custom Display position :rightBottom",
            "Custom Display position :topCenter",
            "Custom Display position :bottomCenter",
            "Custom Display position :leftCenter",
            "Custom Display position :rightCenter",
            "Custom Display position :center",
            "Custom Display position :layoutAbove",
            "Custom Display position :layoutBellow"
    };
    private int previousIndex = 0;
    private String[] previousTags = new String[]{
            "Display content in the previous instance :1 seconds",
            "Display content in the previous instance :2 seconds",
            "Display content in the previous instance :3 seconds",
    };
    private int[] previousValues = new int[]{
            1000 * 1,
            1000 * 2,
            1000 * 3
    };
    private int sizeIndex = 0;
    private String[] sizeTags = new String[]{
            "Custom minSize : 200 * 200",
            "Custom minSize : 400 * 400",
            "Custom minSize : 600 * 600"
    };
    private int[] sizeValues = new int[]{
            200,
            400,
            600
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
        mContext = IToastTipsActivity.this;
        blur.setOverlayColor(Color.argb(100, 235, 235, 235));
        blur.setRadius(me, 0, 0);
        setDarkNavigationBarTheme(false);
        initToastGrid();
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
                        //常规Toast
                        Toast.makeText(mContext, "System Toast : Toast.LENGTH_SHORT", Toast.LENGTH_SHORT).show();
                        break;
                    case 1:
                        //设置背景资源
                        try {
                            SweetToast.makeText(mContext, "Background Resource", Toast.LENGTH_SHORT).backgroundResource(R.drawable.shape_toast_tip_drawable1).showImmediate();
                        } catch (Exception e) {
                            Log.e(_TAG, "e:" + e.getLocalizedMessage());
                        }
                        break;
                    case 2:
                        //设置背景颜色
                        for (int i = 0; i < colorTags.length; i++) {
                            SweetToast.makeText(mContext, colorTags[colorIndex], 1200).backgroundColor(colorValues[colorIndex]).show();
                            colorIndex = (colorIndex + 1) % colorTags.length;
                        }
                        break;
                    case 3:
                        //优化View风格
                        View customView = LayoutInflater.from(mContext).inflate(R.layout.layout_toast_tip_customview, null);
                        SweetToast.makeText(customView, 5000).center().showImmediate();
//                        iToast.showISimpleToast();
                        break;
                    case 4:
                        //新增View
                        ImageView iv = new ImageView(mContext);
                        iv.setImageResource(R.mipmap.ichat_send_chatgroup);
//                      SweetToast.makeText(context,"Add View").addView(iv,0).showImmediate();
                        SweetToast.makeText(me, "(Add View)消息到来").addView(iv, 0).center().showImmediate();
                        break;
                    case 5:
                        //展示时长
                        for (int i = 0; i < durationTags.length; i++) {
                            SweetToast.makeText(me, durationTags[durationIndex], durationValues[durationIndex]).backgroundColor(AppConstant.color_cn_tuoyan).show();
                            durationIndex = (durationIndex + 1) % durationTags.length;
                        }
                        break;
                    case 6:
                        //展示动画
                        SweetToast.makeText(me, animTags[animIndex], Toast.LENGTH_SHORT).setWindowAnimations(animValues[animIndex]).backgroundColor(colorValues[animIndex]).show();
                        animIndex = (animIndex + 1) % animTags.length;
                        break;
                    case 7:
                        //进场退场动画
                        SweetToast.makeText(me, animTags[animIndex], Toast.LENGTH_SHORT).setAnimations(animationValues[animIndex][0], animationValues[animIndex][1]).backgroundColor(colorValues[animIndex]).show();
                        animIndex = (++animIndex) % (animTags.length);
                        break;
                    case 8:
                        //进场位置
                        //如果不是全屏,则计算状态栏的高度
                        int statusHeight = TipsScreenUtil.getStatusHeight(me);
                        SweetToast.makeText(me, positionTags[0], 1200).leftTop().backgroundColor(AppConstant.color_cn_tuoyan).show();
                        SweetToast.makeText(me, positionTags[1], 1200).rightTop().backgroundColor(AppConstant.color_cn_tuoyan).show();
                        SweetToast.makeText(me, positionTags[2], 1200).leftBottom().backgroundColor(AppConstant.color_cn_tuoyan).show();
                        SweetToast.makeText(me, positionTags[3], 1200).rightBottom().backgroundColor(AppConstant.color_cn_tuoyan).show();
                        SweetToast.makeText(me, positionTags[4], 1200).topCenter().backgroundColor(AppConstant.color_cn_tuoyan).show();
                        SweetToast.makeText(me, positionTags[5], 1200).bottomCenter().backgroundColor(AppConstant.color_cn_tuoyan).show();
                        SweetToast.makeText(me, positionTags[6], 1200).leftCenter().backgroundColor(AppConstant.color_cn_tuoyan).show();
                        SweetToast.makeText(me, positionTags[7], 1200).rightCenter().backgroundColor(AppConstant.color_cn_tuoyan).show();
                        SweetToast.makeText(me, positionTags[8], 1200).center().backgroundColor(AppConstant.color_cn_tuoyan).show();
                        SweetToast.makeText(me, positionTags[9], 1200).layoutAbove(mBtnToastTarget, statusHeight).backgroundColor(AppConstant.color_cn_tuoyan).show();
                        SweetToast.makeText(me, positionTags[10], 1200).layoutBellow(mBtnToastTarget, statusHeight).backgroundColor(AppConstant.color_cn_tuoyan).show();
                        break;
                    case 9:
                        //展示上一个实例中的内容
                        SweetToast.makeText(me,previousTags[previousIndex],previousValues[previousIndex]).backgroundColor(AppConstant.color_cn_tuoyan).showByPrevious();
                        previousIndex = (previousIndex + 1)%previousTags.length;
                        break;
                    case 10:
                        //无延迟显示
                        SweetToast.makeText(me,"ShowImmediate",Toast.LENGTH_SHORT).backgroundColor(AppConstant.color_cn_tuoyan).showImmediate();
                        break;
                    case 11:
                        //自定宽高
                        for(int i=0;i<sizeTags.length;i++){
                            SweetToast.makeText(me,sizeTags[sizeIndex],Toast.LENGTH_SHORT).backgroundColor(AppConstant.color_cn_tuoyan).minSize(sizeValues[sizeIndex],sizeValues[sizeIndex]).show();
                            sizeIndex = (sizeIndex + 1)%sizeTags.length;
                        }
                        break;
                    case 12:
                        //最大高度
                        SweetToast.makeText(me,EResUtils.getString(me,R.string.str_toast_tip_sample),Toast.LENGTH_LONG).showImmediate();
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


    private void initToastGrid() {
        List<IToastTipGridBean> toastGridListData = GlobalDataProvider.getToastGridListData();
        WidgetUtils.initGridRecyclerView(mToastRecyclerview, 2, EDensityUtils.dp2px(me, 2));
        mToastRecyclerview.setAdapter(mAdapter = new BaseQuickAdapter<IToastTipGridBean, BaseViewHolder>(R.layout.item_toast_tip_grid, toastGridListData) {
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


