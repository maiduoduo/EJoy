package com.module.iviews.custombanner;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.module.iviews.R;
import com.module.iviews.paletteimageview.IPaletteImageView;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName: IBannerAdapter
 * @Author: bsj-dingcl
 * @Email: dingchenglei@h4kit.com
 * @Date: 2020/
 * @des:
 */
public class IBannerAdapter extends BaseAdapter {


    private List<String> imageRemoteUrl;
    private List<Integer> imageLocalSrc;

    private Context context;

    public IBannerAdapter(Context context) {
        this.context = context;
    }

    public void setPicNetUrls(List<String> pic_urls) {
        imageRemoteUrl = new ArrayList<>();
        this.imageRemoteUrl = pic_urls;
    }

    public void setPicLocals(List<Integer> pic_urls) {
        imageLocalSrc = new ArrayList<>();
        this.imageLocalSrc = pic_urls;
    }

    public Context getContext() {
        return context;
    }

    @Override
    public int getCount() {
        if (imageRemoteUrl != null && imageRemoteUrl.size() > 0) {
            return imageRemoteUrl.size();
        }else if (imageLocalSrc != null && imageLocalSrc.size() > 0) {
            return imageLocalSrc.size();
        }else {
            return 0;
        }
    }

    @Override
    public Object getItem(int position) {
        if (imageRemoteUrl != null && imageRemoteUrl.size() > 0) {
            return imageRemoteUrl.get(position);
        }else if (imageLocalSrc != null && imageLocalSrc.size() > 0) {
            return imageLocalSrc.get(position);
        }else {
            return new ArrayList<>();
        }
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view;
        ViewHolder viewHolder;
        if (convertView == null) {
            view = LayoutInflater.from(context).inflate(R.layout.layout_banner_item, parent, false);
            viewHolder = new ViewHolder();
//            viewHolder.imageView = view.findViewById(R.id.iv_picture);
            viewHolder.mPaletteImageView = view.findViewById(R.id.paletteImageView);
            view.setTag(viewHolder);
        } else {
            view = convertView;
            viewHolder = (ViewHolder) view.getTag();
        }



        if (imageRemoteUrl != null && imageRemoteUrl.size() > 0) {
            Glide.with(view.getContext()).load(imageRemoteUrl.get(position)).into(viewHolder.mPaletteImageView);
        }else if (imageLocalSrc != null && imageLocalSrc.size() > 0) {
            Glide.with(view.getContext()).load(imageLocalSrc.get(position)).into(viewHolder.mPaletteImageView);
        }else {
            Glide.with(view.getContext()).load(R.drawable.bimg_banner_palette_b).into(viewHolder.mPaletteImageView);
        }


        return view;
    }

    public class ViewHolder {
//        public ImageView imageView;
        public ImageView mPaletteImageView;
    }
}

