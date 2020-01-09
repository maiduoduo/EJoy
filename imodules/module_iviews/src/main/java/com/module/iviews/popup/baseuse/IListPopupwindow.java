package com.module.iviews.popup.baseuse;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Rect;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Handler;
import android.support.v4.content.ContextCompat;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.target.BitmapImageViewTarget;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.iduo.filterlib.DisplayHelper;
import com.module.ires.bean.utils.EDensityUtils;
import com.module.ires.bean.utils.EResUtils;
import com.module.ires.bean.view.EDividerItemDecoration;
import com.module.iviews.R;
import com.module.iviews.popup.bean.GalleryBean;

import java.util.ArrayList;
import java.util.List;

import jp.wasabeef.glide.transformations.CropCircleTransformation;
import jp.wasabeef.glide.transformations.RoundedCornersTransformation;

/**
 * CN:      ISimpleListPopupWindow
 * Author： JSYL-DINGCL (dingcl@jsyl.com.cn)
 * Date:   2020/1/6
 * Des:    TODO:选取相册弹窗：自定义由上方弹出的窗体
 *  *      TODO:PopupWindow哦
 *  *      TODO:优势是可让其居于某个特定的布局下面比较不错
 */
public class IListPopupwindow extends PopupWindow {
    private Context context;
    private View window;
    private Animation animationIn, animationOut;
    private boolean isDismiss = false;
    private LinearLayout llPopRoot;
    private BaseQuickAdapter<GalleryBean,BaseViewHolder> adapter;
    private List<GalleryBean> mList = new ArrayList<>();
    /* 用于修改上下箭头的 很牛皮啊!! 这里以后可以拿来用非常不错*/
    private TextView picture_title;
    private Drawable drawableUp, drawableDown;
    private RecyclerView recyclerView;
    private OnPopItemSelectedListener mOnPopItemSelectedListener;
    public IListPopupwindow(Context context, List<GalleryBean> mList) {
        this.context = context;
        this.mList = mList;
        window = LayoutInflater.from(context).inflate(R.layout.layout_pop_album, null);
        this.setContentView(window);
        this.setWidth(EDensityUtils.getScreenWidth(context));
        this.setHeight(EDensityUtils.getScreenHeight(context));
        this.setAnimationStyle(R.style.ListPopAnim);
        this.setFocusable(true);
        this.setOutsideTouchable(true);
        this.update();
        this.setBackgroundDrawable(new ColorDrawable(Color.argb(123, 0, 0, 0)));
        /* 获得图片*/
        drawableUp = ContextCompat.getDrawable(context, R.mipmap.eico_arrow_up);
        drawableDown = ContextCompat.getDrawable(context, R.mipmap.eico_arrow_down);
        animationIn = AnimationUtils.loadAnimation(context, R.anim.anim_photo_album_show);
        animationOut = AnimationUtils.loadAnimation(context, R.anim.anim_photo_album_dismiss);
        initView();
    }

    public void setPictureTitleView(TextView picture_title) {
        this.picture_title = picture_title;
    }

    private void initView() {
        llPopRoot = window.findViewById(R.id.llPopRoot);
        recyclerView = window.findViewById(R.id.rvList);
        /* 设置列表的高度 ----- */
        recyclerView.getLayoutParams().height = (int) (EDensityUtils.getScreenHeight(context) * 0.6);
        recyclerView.addItemDecoration(new EDividerItemDecoration(
                context, LinearLayoutManager.HORIZONTAL, 0, ContextCompat.getColor(context, R.color.transparent)));
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        adapter = new BaseQuickAdapter<GalleryBean, BaseViewHolder>(R.layout.item_album_pop,mList) {
            @Override
            protected void convert(BaseViewHolder helper, GalleryBean item) {
                helper.setText(R.id.tvTitleName,item.getTitle());

                helper.itemView.setSelected(item.isChecked());

                ImageView view = helper.getView(R.id.first_image);
                Glide.with(context)
                        .load(item.getImgThumb())
                        .bitmapTransform(new RoundedCornersTransformation(context,25,5))
                        .into(view);
            }
        };
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                for (GalleryBean bean:mList){
                    bean.setChecked(false);
                }
                mList.get(position).setChecked(true);
                adapter.notifyDataSetChanged();
                if (mOnPopItemSelectedListener != null){
                    mOnPopItemSelectedListener.popItemSelected(mList.get(position).getTitle());
                }
            }
        });


        recyclerView.setAdapter(adapter);
        llPopRoot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                IListPopupwindow.this.dismiss();
            }
        });
    }

    @Override
    public void showAsDropDown(View anchor) {
        try {
            if (Build.VERSION.SDK_INT >= 24) {
                Rect rect = new Rect();
                anchor.getGlobalVisibleRect(rect);
                if (EDensityUtils.isNavigationBarExist((Activity) context)){
                    int h = anchor.getResources().getDisplayMetrics().heightPixels - rect.bottom;
                    setHeight(h);
                }else {
                    //全面屏手机需要获取真实高度....
                    int h = DisplayHelper.getRealScreenSize(context)[1] - rect.bottom;
                    setHeight(h);
                }
            }
            super.showAsDropDown(anchor);
            isDismiss = false;
            llPopRoot.startAnimation(animationIn);
            EResUtils.modifyTextViewDrawable(picture_title, drawableUp, 2);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void dismiss() {
        if (isDismiss) {
            return;
        }
        EResUtils.modifyTextViewDrawable(picture_title, drawableDown, 2);
        isDismiss = true;
        llPopRoot.startAnimation(animationOut);
        dismiss();
        animationOut.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                isDismiss = false;
                if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.JELLY_BEAN) {
                    dismiss4Pop();
                } else {
                    IListPopupwindow.super.dismiss();
                }
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }

    /**
     * 在android4.1.1和4.1.2版本关闭PopWindow
     */
    private void dismiss4Pop() {
        new Handler().post(new Runnable() {
            @Override
            public void run() {
                IListPopupwindow.super.dismiss();
            }
        });
    }

    public interface OnPopItemSelectedListener{
        void popItemSelected(String title);
    }

    public void setOnItemSelectedListener(OnPopItemSelectedListener onItemSelectedListener){
        this.mOnPopItemSelectedListener = onItemSelectedListener;
    }
}
