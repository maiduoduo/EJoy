package com.ejoy.tool.common.helper.InfoDialog;

import android.app.Dialog;
import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;
import com.ejoy.tool.R;


/**
 * CN:      IDialog
 * Author： JSYL-DINGCL (1144286501@qq.com)
 * Date:   2019/8/10
 * Des:    仿IOS消息弹框
 */
public class IDialog {
    private Context context;
    private Dialog dialog;
    private LinearLayout lLayout_bg;
    private TextView txt_title;
    private TextView txt_msg;
    private Button btn_neg;
    private Button btn_pos;
    private ImageView img_line;
    private Display display;
    private boolean showTitle = false;
    private boolean showMsg = false;
    private boolean showPosBtn = false;
    private boolean showNegBtn = false;

    public IDialog(Context context) {
        this.context = context;
        WindowManager windowManager = (WindowManager) context
                .getSystemService(Context.WINDOW_SERVICE);
        display = windowManager.getDefaultDisplay();
    }

    public IDialog builder() {
        View view = LayoutInflater.from(context).inflate(
                R.layout.dialog_normal_ios, null);

        lLayout_bg = (LinearLayout) view.findViewById(R.id.lLayout_bg);
        txt_title = (TextView) view.findViewById(R.id.txt_title);
        txt_msg = (TextView) view.findViewById(R.id.txt_msg);
        btn_neg = (Button) view.findViewById(R.id.btn_neg);
        btn_pos = (Button) view.findViewById(R.id.btn_pos);
        img_line = (ImageView) view.findViewById(R.id.img_line);
        setGone();
        dialog = new Dialog(context, R.style.IIosAlertDialogStyle);
        dialog.setContentView(view);
        lLayout_bg.setLayoutParams(new FrameLayout.LayoutParams((int) (display
                .getWidth() * 0.85), LayoutParams.WRAP_CONTENT));

        return this;
    }

    /**
     * 恢复初始
     * @return
     */
    public IDialog setGone() {
        if (lLayout_bg != null) {
            txt_title.setVisibility(View.GONE);
            txt_msg.setVisibility(View.GONE);
            btn_neg.setVisibility(View.GONE);
            btn_pos.setVisibility(View.GONE);
            img_line.setVisibility(View.GONE);

        }
        showTitle = false;
        showMsg = false;
        showPosBtn = false;
        showNegBtn = false;
        return this;
    }

    /**
     * 设置title
     * @param title
     * @return
     */
    public IDialog setTitle(String title) {
        showTitle = true;
        if (TextUtils.isEmpty(title)) {
            txt_title.setText("提示");
        } else {
            txt_title.setText(title);
        }
        return this;
    }

    /**
     * 设置Message
     * @param msg
     * @return
     */
    public IDialog setMsg(String msg) {
        showMsg = true;
        if (TextUtils.isEmpty(msg)) {
            txt_msg.setText("");
        } else {
            txt_msg.setText(msg);
        }
        return this;
    }

    /**
     * 设置点击外部是否消失
     * @param cancel
     * @return
     */
    public IDialog setCancelable(boolean cancel) {
        dialog.setCancelable(cancel);
        return this;
    }

    /**
     * 右侧按钮
     *
     * @param text
     * @param listener
     * @return
     */
    public IDialog setPositiveButton(String text,
                                         final OnClickListener listener) {
        return setPositiveButton(text, -1, listener);
    }

    public IDialog setPositiveButton(String text, int color,
                                         final OnClickListener listener) {
        showPosBtn = true;
        if ("".equals(text)) {
            btn_pos.setText("");
        } else {
            btn_pos.setText(text);
        }
        if (color == -1) {
            color = R.color.action_sheet_blue;
        }
        btn_pos.setTextColor(ContextCompat.getColor(context, color));
        btn_pos.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null)
                    listener.onClick(v);
                dismiss();
            }
        });
        return this;
    }

    /**
     * 左侧按钮
     *
     * @param text
     * @param listener
     * @return
     */

    public IDialog setNegativeButton(String text,
                                         final OnClickListener listener) {

        return setNegativeButton(text, -1, listener);
    }

    public IDialog setNegativeButton(String text, int color,
                                         final OnClickListener listener) {
        showNegBtn = true;
        if ("".equals(text)) {
            btn_neg.setText("");
        } else {
            btn_neg.setText(text);
        }
        if (color == -1) {
            color = R.color.action_sheet_blue;
        }
        btn_neg.setTextColor(ContextCompat.getColor(context, color));

        btn_neg.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null)
                    listener.onClick(v);
                dismiss();
            }
        });
        return this;
    }

    /**
     * 设置显示
     */
    private void setLayout() {
        if (!showTitle && !showMsg) {
            txt_title.setText("");
            txt_title.setVisibility(View.VISIBLE);
        }

        if (showTitle) {
            txt_title.setVisibility(View.VISIBLE);
        }

        if (showMsg) {
            txt_msg.setVisibility(View.VISIBLE);
        }

        if (!showPosBtn && !showNegBtn) {
            btn_pos.setText("");
            btn_pos.setVisibility(View.VISIBLE);
            btn_pos.setBackgroundResource(R.drawable.selector_dialog_ios_right);
            btn_pos.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    dismiss();
                }
            });
        }

        if (showPosBtn && showNegBtn) {
            btn_pos.setVisibility(View.VISIBLE);
            btn_pos.setBackgroundResource(R.drawable.selector_dialog_ios_right);
            btn_neg.setVisibility(View.VISIBLE);
            btn_neg.setBackgroundResource(R.drawable.selector_dialog_ios_left);
            img_line.setVisibility(View.VISIBLE);
        }

        if (showPosBtn && !showNegBtn) {
            btn_pos.setVisibility(View.VISIBLE);
            btn_pos.setBackgroundResource(R.drawable.selector_dialog_ios_right);
        }

        if (!showPosBtn && showNegBtn) {
            btn_neg.setVisibility(View.VISIBLE);
            btn_neg.setBackgroundResource(R.drawable.selector_dialog_ios_left);
        }
    }

    public void show() {
        setLayout();
        dialog.show();
    }

    public boolean isShowing() {
        if (dialog != null) {
            if (dialog.isShowing())
                return true;
            else
                return false;
        }
        return false;
    }

    public void dismiss() {
        if (dialog!=null){
            dialog.dismiss();
        }

    }
}
