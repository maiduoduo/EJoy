package com.ejoy.tool.ui.activity.iosdialog;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.NumberPicker;

import com.ejoy.tool.R;
import com.ejoy.tool.common.helper.InfoDialog.IDialog;
import com.ejoy.tool.scaffold.utils.StatusBarTool;
import com.ejoy.tool.scaffold.view.loading.LoadingDialog;
import com.ejoy.tool.ui.base.base_activity.BaseActivity;
import com.ejoy.tool.ui.mvp.base.BasePresenter;
import com.maple.msdialog.ActionSheetDialog;
import com.maple.msdialog.AlertDialog;
import com.maple.msdialog.AlertEditDialog;
import com.maple.msdialog.AlertNumberPickerDialog;

/**
 * CN:      IIosDialogActivity
 * Author： JSYL-DINGCL (dingcl@jsyl.com.cn)
 * Date:   2019/12/2
 * Des:    Custom ios Dialog
 */
public class IIosDialogActivity extends BaseActivity {
    public static final String DEF_BLUE = "#037BFF";
    public static final String DEF_RED = "#C1C1C1";
    private IDialog iDialog;
    private LoadingDialog mLoadingDialog;
    private String[] mMsgArray = new String[]{
            "E·享新版本对系统做了大量优化及增加新功能，请及时更新，避免版本差异造成数据、功能不同步。\n\n" +
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
    protected void initRestore(@Nullable Bundle savedInstanceState) {

    }

    @Override
    protected int getContentViewId() {
        return R.layout.activity_iosdialog;
    }

    @Override
    protected void initView(View mRootView) {
        iDialog = new IDialog(_mActivity).builder();
        StatusBarTool.setRootViewFitsSystemWindows(_mActivity,true);
        StatusBarTool.setStatusBarColor(_mActivity,Color.parseColor("#ffe6393f"));
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


    // -------------------------------- Action Sheet Dialog ----------------------------------------

    public void asMessage(View view) {
        new ActionSheetDialog(_mActivity)
                .setCancelable(false)
                .setCanceledOnTouchOutside(false)
                .setTitle("清空消息列表后，聊天记录依然保留，确定要清空消息列表？")
                .addSheetItem("清空消息列表", Color.parseColor(DEF_RED),
                        new ActionSheetDialog.OnSheetItemClickListener() {
                            @Override
                            public void onClick(int which) {
                                iToast.showISimpleImgResToast("清空列表",R.drawable.itoast_done_green_target);
                            }
                        })
                .setCancelText("取 消")
                .show();
    }

    public void asImage(View view) {
        new ActionSheetDialog(_mActivity)
                .setCancelable(false)
                .setCanceledOnTouchOutside(false)
                .addSheetItem("发送给好友", Color.parseColor(DEF_BLUE),
                        new ActionSheetDialog.OnSheetItemClickListener() {
                            @Override
                            public void onClick(int which) {

                            }
                        })
                .addSheetItem("转载到空间相册", Color.parseColor(DEF_BLUE),
                        new ActionSheetDialog.OnSheetItemClickListener() {
                            @Override
                            public void onClick(int which) {

                            }
                        })
                .addSheetItem("上传到群相册",
                        new ActionSheetDialog.OnSheetItemClickListener() {
                            @Override
                            public void onClick(int which) {

                            }
                        })
                .addSheetItem("保存到手机",
                        new ActionSheetDialog.OnSheetItemClickListener() {
                            @Override
                            public void onClick(int which) {

                            }
                        })
                .addSheetItem("收藏",
                        new ActionSheetDialog.OnSheetItemClickListener() {
                            @Override
                            public void onClick(int which) {

                            }
                        })
                .addSheetItem("查看聊天图片",
                        new ActionSheetDialog.OnSheetItemClickListener() {
                            @Override
                            public void onClick(int which) {

                            }
                        })
                .show();
    }

    public void asList(View view) {
        new ActionSheetDialog(_mActivity)
                .setTitle("请选择操作")
                .setCancelable(false)
                .setCanceledOnTouchOutside(false)
                .addSheetItem("条目一", Color.parseColor(DEF_RED), new ActionSheetDialog.OnSheetItemClickListener() {
                    @Override
                    public void onClick(int which) {
                        iToast.showISimpleImgResToast("item " + which,R.drawable.itoast_done_green_target);
                    }
                })
                .addSheetItem("条目二", new ActionSheetDialog.OnSheetItemClickListener() {
                    @Override
                    public void onClick(int which) {
                        iToast.showISimpleImgResToast("item " + which,R.drawable.itoast_done_green_target);
                    }
                })
                .addSheetItem("条目三", Color.BLUE, new ActionSheetDialog.OnSheetItemClickListener() {
                    @Override
                    public void onClick(int which) {
                        iToast.showISimpleImgResToast("item " + which,R.drawable.itoast_done_green_target);
                    }
                })
                .addSheetItem("条目四", Color.CYAN, new ActionSheetDialog.OnSheetItemClickListener() {
                    @Override
                    public void onClick(int which) {
                        iToast.showISimpleImgResToast("item " + which,R.drawable.itoast_done_green_target);
                    }
                })
                .addSheetItem("条目五", new ActionSheetDialog.OnSheetItemClickListener() {
                    @Override
                    public void onClick(int which) {
                        iToast.showISimpleImgResToast("item " + which,R.drawable.itoast_done_green_target);
                    }
                })
                .addSheetItem("条目六", new ActionSheetDialog.OnSheetItemClickListener() {
                    @Override
                    public void onClick(int which) {
                        iToast.showISimpleImgResToast("item " + which,R.drawable.itoast_done_green_target);
                    }
                })
                .addSheetItem("条目七", new ActionSheetDialog.OnSheetItemClickListener() {
                    @Override
                    public void onClick(int which) {
                        iToast.showISimpleImgResToast("item " + which,R.drawable.itoast_done_green_target);
                    }
                })
                .addSheetItem("条目八", new ActionSheetDialog.OnSheetItemClickListener() {
                    @Override
                    public void onClick(int which) {
                        iToast.showISimpleImgResToast("item " + which,R.drawable.itoast_done_green_target);
                    }
                })
                .addSheetItem("条目九", new ActionSheetDialog.OnSheetItemClickListener() {
                    @Override
                    public void onClick(int which) {
                        iToast.showISimpleImgResToast("item " + which,R.drawable.itoast_done_green_target);
                    }
                })
                .addSheetItem("条目十", new ActionSheetDialog.OnSheetItemClickListener() {
                    @Override
                    public void onClick(int which) {
                        iToast.showISimpleImgResToast("item " + which,R.drawable.itoast_done_green_target);
                    }
                }).show();
    }

    // ------------------------------------ Alert Dialog -------------------------------------------

    public void asNormal(View view) {
        new AlertDialog(_mActivity)
                .setCancelable(false)
                .setTitle("退出当前账号")
                .setMessage("再连续登陆15天，就可变身为QQ达人。退出QQ可能会使你现有记录归零，确定退出？")
                .setLeftButton("取消", null)
                .setRightButton("确认退出", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        iToast.showISimpleToast("退出");
                    }
                })
                .show();
    }

    public void asSimpleless(View view) {
        new AlertDialog(_mActivity)
                .setCancelable(true)
                .setScaleWidth(0.7)// 设置宽度，占屏幕宽度百分比
                .setMessage("你现在无法接收到新消息提醒。请到系统-设置-通知中开启消息提醒")
                .setRightButton("确定", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        iToast.showISimpleToast("好的");
                    }
                })
                .show();
    }

    // --------------------------------- Alert Edit Dialog -----------------------------------------

    public void astextInput(View view) {
        new AlertEditDialog(_mActivity)
                .setTitle("姓名")
                .setMessage("请输入您的真实姓名。")
                .setLeftButton("取消", null)
                .setRightButton("确定", new AlertEditDialog.EditTextCallListener() {
                    @Override
                    public void callBack(String str) {
                        iToast.showISimpleToast(""+str);
                    }
                })
                .show();
    }

    public void asSimplelessInput(View view) {
        new AlertEditDialog(_mActivity)
                .setMessage("起一个好听的名字吧")
                .setRightButton("确定", new AlertEditDialog.EditTextCallListener() {
                            @Override
                            public void callBack(String str) {
                                if (!TextUtils.isEmpty(str)) {
                                    iToast.showISimpleToast(""+str);
                                }
                            }
                        }
                )
                .show();
    }

    // --------------------------------- Number Picker Dialog -----------------------------------------


    String[] numbers;
    int index = 0;
    String defValue;

    public void asNumselect(View view) {
        numbers = new String[]{"1", "2", "3", "4", "5", "6", "7", "8", "9", "10"};
        defValue = numbers[index];
        new AlertNumberPickerDialog(_mActivity)
                .setTitle("数字选择")
                .setNumberValues(numbers, index, new NumberPicker.OnValueChangeListener() {
                    @Override
                    public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                        defValue = numbers[newVal];
                    }
                })
                .setLeftButton("取消", null)
                .setRightButton("确定", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        iToast.showISimpleToast(""+defValue);
                    }
                })
                .show();
    }


    public void asCityselect(View view) {
        numbers = new String[]{"北京", "上海", "天津", "杭州", "苏州", "深圳"};
        defValue = numbers[index];
        new AlertNumberPickerDialog(_mActivity)
                .setScaleWidth(0.8)
                .setCancelable(false)
                .setTitle("城市选择")
                .setNumberValues(numbers, index, new NumberPicker.OnValueChangeListener() {
                    @Override
                    public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                        defValue = numbers[newVal];
                    }
                })
                .setNumberValueSuffix("市")
                .setLeftButton("取消", null)
                .setRightButton("确定", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        iToast.showISimpleToast(""+defValue);
                    }
                })
                .show();

    }

    public void iosBack(View view) {
        finish();
    }

    // ----------------------------------- text short/long -------------------------------------------


    public void asshortText(View view) {
        showScrollViewDialog(false,"短文本");
    }

    public void asLongText(View view) {
        showScrollViewDialog(true,"长文本");
    }

    private void showScrollViewDialog(boolean isLong,String title) {
        iDialog.setGone().setTitle(title)
                .setMsg(isLong ? mMsgArray[0] + "" : mMsgArray[1] + "")
                .setNegativeButton("取消", null)
                .setPositiveButton("继续", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                    }
                }).show();
    }



}
