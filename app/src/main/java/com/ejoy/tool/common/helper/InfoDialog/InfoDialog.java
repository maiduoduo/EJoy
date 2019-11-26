package com.ejoy.tool.common.helper.InfoDialog;


import android.app.Dialog;
import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.ejoy.tool.R;

/**
 * CN:      InfoDialog
 * Author： JSYL-DINGCL (1144286501@qq.com)
 * Date:   2019/9/26
 * Des:    提示弹窗
 */
public class InfoDialog extends Dialog {

    private InfoDialog(Context context, int themeResId) {
        super(context, themeResId);
    }

    public static class Builder {

        private View mLayout;

//        private ImageView mIcon;
//        private TextView mTitle;
//        private TextView mMessage;
//        private Button mButton;
        private TextView mTitle;
        private TextView mMessage;
        private TextView mButton;
        private ImageView mCloseButton1;
        private TextView mCloseButton;
//        private TextView mCloseBottomBtn;
        //版本
        private TextView mTvCurrentVersionName;
        private TextView mTvNewVersionName;

        private View.OnClickListener mButtonClickListener;
        private View.OnClickListener mButtonCancelClickListener;

        private InfoDialog mDialog;

        public Builder(Context context, @LayoutRes int dialogLayout) {
            mDialog = new InfoDialog(context, R.style.IIosAlertDialogStyle);
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            //加载布局文件
            mLayout = inflater.inflate(dialogLayout, null, false);
            //添加布局文件到 Dialog
            mDialog.addContentView(mLayout, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            mTitle = (TextView) mLayout.findViewById(R.id.permission_title);
            mMessage = (TextView) mLayout.findViewById(R.id.tv_permission_desc);
            mButton = (TextView) mLayout.findViewById(R.id.permission_btn);
            mCloseButton1 = (ImageView) mLayout.findViewById(R.id.permission_close1);
            mCloseButton = (TextView) mLayout.findViewById(R.id.permission_close);
        }

        /**
         * 设置 Dialog 标题
         */
        public Builder setTitle(@NonNull String title) {
            mTitle.setText(title);
            mTitle.setVisibility(View.VISIBLE);
            return this;
        }


        /**
         * 设置 Message
         */
        public Builder setMessage(@NonNull String message) {
            mMessage.setText(message);
            return this;
        }

        /**
         * 设置按钮文字和监听
         */
        public Builder setButton(@NonNull String text, View.OnClickListener listener) {
            mButton.setText(text);
            mButtonClickListener = listener;
            return this;
        }

        public Builder setCancleButton(View.OnClickListener listener) {
            mButtonCancelClickListener = listener;
            return this;
        }

        public InfoDialog create() {
            mButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mDialog.dismiss();
                    mButtonClickListener.onClick(v);
                }
            });
            mCloseButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mDialog.dismiss();
                    mButtonCancelClickListener.onClick(v);
                }
            });
            mDialog.setContentView(mLayout);
            mDialog.setCancelable(true);                //用户可以点击后退键关闭 Dialog
            mDialog.setCanceledOnTouchOutside(false);   //用户不可以点击外部来关闭 Dialog
            return mDialog;
        }
    }
}
