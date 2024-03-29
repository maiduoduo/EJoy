package com.ejoy.tool.ui.data.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.ejoy.tool.R;
import com.ejoy.tool.app.App;
import com.ejoy.tool.common.bean.ImageFileBean;
import com.ejoy.tool.scaffold.utils.GlideUtils;
import com.ejoy.tool.scaffold.utils.PairHelp;

import java.util.List;


public class PictureAdapter extends RecyclerView.Adapter<PictureAdapter.ViewHolder> {


    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    private List<ImageFileBean> mData;
    private Context mContext;

    private OnItemClickListener onItemClickListener;

    public PictureAdapter(Context context, List<ImageFileBean> data) {
        this.mContext = context;
        this.mData = data;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_picture_compress_view, parent, false));
    }



    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        final ImageFileBean imageFileBean = mData.get(position);
        holder.mTvSize.setText(imageFileBean.imageSize);

        if (imageFileBean.isImage) {
            PairHelp.setViewTransitionName(holder.mIvPicture);
            GlideUtils.showCompressImage(mContext, imageFileBean.imageFile, holder.mIvPicture);
        } else {
             GlideUtils.showImage(mContext, R.drawable.take_picture_pressed, holder.mIvPicture);
           // ContextCompat.getDrawable(mContext, R.drawable.selector_picture_image);
        }
        holder.mIvPicture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onItemClickListener != null) {
                    if (imageFileBean.isImage) {
                        onItemClickListener.onPictureItemClick(v, position);
                    } else {
                        onItemClickListener.onAddItemClick(v, position);
                    }
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mData == null ? 0 : mData.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView mIvPicture;
        private TextView mTvSize;

        ViewHolder(View itemView) {
            super(itemView);
            initView(itemView);
        }

        private void initView(View itemView) {
            mIvPicture = itemView.findViewById(R.id.iv_picture);
            DisplayMetrics metrics = App.getAppContext().getResources().getDisplayMetrics();
            ViewGroup.LayoutParams layoutParams = mIvPicture.getLayoutParams();
            layoutParams.width = metrics.widthPixels  / 3;
            layoutParams.height = metrics.widthPixels  / 3;
            mIvPicture.setLayoutParams(layoutParams);
            mTvSize = itemView.findViewById(R.id.tv_size);
        }
    }

    public interface OnItemClickListener {
        void onAddItemClick(View view, int position);

        void onPictureItemClick(View view, int position);

    }
}
