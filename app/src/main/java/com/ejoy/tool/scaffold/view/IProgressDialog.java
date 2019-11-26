package com.ejoy.tool.scaffold.view;

import android.app.ProgressDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ejoy.tool.R;

/**
  * Author： JSYL_Dingcl
  * Des  :   加载弹窗
 */
public class IProgressDialog {

	private static ProgressDialog dialog;

	public static void showSuccinctProgress(Context context, String message,boolean canCancel) {

		// 创建ProgressDialog对象
		dialog = new ProgressDialog(context, R.style.ILoadingProgress);
		// false 设置点击外边距不可取消,true 可取消
		dialog.setCanceledOnTouchOutside(canCancel);
		// 设置点击back键不可取消
		dialog.setCancelable(true);
		// 加载自定义的ProgressDialog
		RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT,
				LayoutParams.WRAP_CONTENT);
		View view = LayoutInflater.from(context).inflate(R.layout.layout_progress_loading, null);
		ImageView mProgressIcon = (ImageView) view.findViewById(R.id.progress_icon);
		// 设置样式
		mProgressIcon.setImageResource(R.drawable.icon_progress);
		TextView mProgressMessage = (TextView) view.findViewById(R.id.progress_message);
		// 设置内容
		mProgressMessage.setText(message);
		new AnimationUtils();
		// 设置动画
		Animation jumpAnimation = AnimationUtils.loadAnimation(context, R.anim.anim_progress_loading);
		mProgressIcon.startAnimation(jumpAnimation);
		// 显示
		dialog.show();
		// 必须先显示，在设置自定义的View
		dialog.setContentView(view, params);

	}
	
	/**
	 * @return true即现实中，false为不在显示
	 */
	public static boolean isShowing() {
		if (dialog != null && dialog.isShowing()) {
			return true;
		}
		return false;
	}

	/**
	 * 取消Dialog
	 */
	public static void dismiss() {
		if (isShowing()) {
			dialog.dismiss();
		}

	}
	
}
