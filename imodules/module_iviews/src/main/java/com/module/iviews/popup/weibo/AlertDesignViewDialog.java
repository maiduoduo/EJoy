package com.module.iviews.popup.weibo;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Typeface;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.maple.msdialog.BaseDialog;
import com.module.ires.bean.utils.WidgetUtils;
import com.module.iviews.R;
import com.module.iviews.popup.weibo.adpter.ImgListAdapter;

import java.util.ArrayList;


/**
 * CN:      AlertDesignViewDialog
 * Author： JSYL-DINGCL (dingcl@jsyl.com.cn)
 * Date:   2020/1/13
 * Des:    TODO:微博相册选择
 *      自定义布局Dialog [ 标题 + 自定义布局 + 左按钮 + 右按钮 ]
 */
public class AlertDesignViewDialog extends BaseDialog {
    private OnAddPicClickListener onCallViewBackListener;
    private ArrayList<ImageInfoBean> mSelectList = new ArrayList<ImageInfoBean>();
    private Button rightButton;
    private boolean showRightBtn = false;

    public AlertDesignViewDialog(final Context context) {
        super(context);
    }


    public AlertDesignViewDialog build(final ArrayList<ImageInfoBean> mSelectImgList) {
        rootView = LayoutInflater.from(mContext).inflate(R.layout.view_gridlist_dialog, null);
        RecyclerView ImgList = rootView.findViewById(R.id.mGRecyclerView);
        rightButton = rootView.findViewById(R.id.bt_right);
        WidgetUtils.initGridRecyclerView(ImgList,3,0);
        ImgListAdapter imgListAdapter = new ImgListAdapter(mSelectImgList, mContext);
        imgListAdapter.setOnFooterViewClickListener(new ImgListAdapter.OnFooterViewClickListener() {
            @Override
            public void OnFooterViewClick() {
                if (onCallViewBackListener != null){
                    onCallViewBackListener.onAddPic();
                }
            }
        });
        ImgList.setAdapter(imgListAdapter);

//        // set Dialog style
        dialog = new Dialog(mContext, R.style.ActionSheetDialogStyle);
        dialog.setContentView(rootView);
        return this;
    }

    public AlertDesignViewDialog setCancelable(boolean cancel) {
        dialog.setCancelable(cancel);
        return this;
    }

    public AlertDesignViewDialog setRightButton(String text, final View.OnClickListener listener) {
        int color = ContextCompat.getColor(mContext, com.maple.msdialog.R.color.action_sheet_blue);
        return setRightButton(text, color, 16, false, listener);
    }

    public AlertDesignViewDialog setRightButton(String text, int color, int size, boolean isBold, final View.OnClickListener listener) {
        showRightBtn = true;
        if (TextUtils.isEmpty(text)) {
            rightButton.setText(com.maple.msdialog.R.string.ok);
        } else {
            rightButton.setText(text);
        }
        if (color != -1) {
            rightButton.setTextColor(color);
        }
        if (size > 0) {
            rightButton.setTextSize(size);
        }
        if (isBold) {
            rightButton.setTypeface(rightButton.getTypeface(), Typeface.BOLD);
        }
        rightButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.onClick(v);
                }
                dialog.dismiss();
            }
        });
        return this;
    }

    @Override
    public AlertDesignViewDialog setScaleWidth(double scWidth) {
        return (AlertDesignViewDialog) super.setScaleWidth(scWidth);
    }

    public AlertDesignViewDialog show() {
        dialog.show();
        return this;
    }

    public void cancelIt() {
        if (dialog != null && dialog.isShowing())dialog.cancel();
    }

    public AlertDesignViewDialog addOnAddPicClickListener(OnAddPicClickListener listener){
        this.onCallViewBackListener = listener;
        return this;
    }

    public interface OnAddPicClickListener{
        void onAddPic();
    }

}
