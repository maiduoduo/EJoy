package com.ejoy.tool.ui.fragment;

import android.app.Dialog;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;

import com.module.iviews.simplemultidialog.ISimpleMultiAlertDialog;

/**
 * @Author: AriesHoo on 2019/4/23 14:57
 * @E-Mail: AriesHoo@126.com
 * @Function: DialogFragment使用示例
 * @Description:
 */
public class AlertDialogFragment extends DialogFragment {

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        ISimpleMultiAlertDialog dialog = new ISimpleMultiAlertDialog.DividerQQBuilder(getContext())
                .setTitle("退出群聊")
                .setTitleTextColor(Color.BLACK)
                .setMessage("你将退出  四川移动爱分享抢流量(XXXXXXXX) 退群通知仅群管理员可见。")
                .setMessageTextColor(Color.BLACK)
                .setNegativeButton("取消", null)
                .setNegativeButtonTextColor(Color.BLACK)
                .setPositiveButton("退出", null)
                .setPositiveButtonTextColor(Color.BLACK)
                .setNeutralButton("中性", null)
                .setNeutralButtonTextColor(Color.BLACK)
                .create();
        return dialog;
    }
}
