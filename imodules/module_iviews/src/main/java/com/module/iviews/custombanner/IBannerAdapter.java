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


//    String[] pic_urls = new String[]{
//            "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1591008936948&di=b0f06aea0614b7e40bd677208023cecf&imgtype=0&src=http%3A%2F%2Fc.hiphotos.baidu.com%2Fzhidao%2Fpic%2Fitem%2Fd000baa1cd11728bcdde8185ccfcc3cec2fd2ca1.jpg",
//            "https://ss1.bdstatic.com/70cFuXSh_Q1YnxGkpoWK1HF6hhy/it/u=2112376104,3802761195&fm=26&gp=0.jpg",
//            "https://ss1.bdstatic.com/70cFvXSh_Q1YnxGkpoWK1HF6hhy/it/u=1760283799,1689150510&fm=26&gp=0.jpg",
//    };
    private List<String> imageRemoteUrl;
    private List<Integer> imageLocalSrc;

    private Context context;

    public IBannerAdapter(Context context) {
        this.context = context;
    }

    public void setPicNetUrls(List<String> pic_urls) {
        imageRemoteUrl = new ArrayList<>();
        this.imageRemoteUrl = pic_urls;
//        if (pic_urls != null && pic_urls.size() > 0) {
//        }else {
//            imageRemoteUrl.clear();
//            imageRemoteUrl.add("https://timgsa.baidu.com/bimg_banner_palette_a?image&quality=80&size=b9999_10000&sec=1591008936948&di=b0f06aea0614b7e40bd677208023cecf&imgtype=0&src=http%3A%2F%2Fc.hiphotos.baidu.com%2Fzhidao%2Fpic%2Fitem%2Fd000baa1cd11728bcdde8185ccfcc3cec2fd2ca1.jpg")
//            imageRemoteUrl.add("https://ss1.bdstatic.com/70cFuXSh_Q1YnxGkpoWK1HF6hhy/it/u=2112376104,3802761195&fm=26&gp=0.jpg");
//            imageRemoteUrl.add("https://ss1.bdstatic.com/70cFvXSh_Q1YnxGkpoWK1HF6hhy/it/u=1760283799,1689150510&fm=26&gp=0.jpg");
//        }
    }

    public void setPicLocals(List<Integer> pic_urls) {
        imageLocalSrc = new ArrayList<>();
        this.imageLocalSrc = pic_urls;
//        if (pic_urls != null && pic_urls.size() > 0) {
//        }else {
//            imageLocalSrc.clear();
//            imageLocalSrc.add(R.drawable.bimg_banner_palette_a);
//            imageLocalSrc.add(R.drawable.bimg_banner_palette_b);
//            imageLocalSrc.add(R.drawable.bimg_banner_palette_c);
//            imageLocalSrc.add(R.drawable.bimg_banner_palette_d);
//            imageLocalSrc.add(R.drawable.bimg_banner_palette_e);
//            imageLocalSrc.add(R.drawable.bimg_banner_palette_f);
//            imageLocalSrc.add(R.drawable.bimg_banner_palette_g);
//            imageLocalSrc.add(R.drawable.bimg_banner_palette_h);
//        }
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
            Glide.with(getContext()).load(imageRemoteUrl.get(position)).into(viewHolder.mPaletteImageView);
        }else if (imageLocalSrc != null && imageLocalSrc.size() > 0) {
            Glide.with(getContext()).load(imageLocalSrc.get(position)).into(viewHolder.mPaletteImageView);
        }else {
            Glide.with(getContext()).load(R.drawable.bimg_banner_palette_b).into(viewHolder.mPaletteImageView);
        }


        return view;
    }

    public class ViewHolder {
//        public ImageView imageView;
        public ImageView mPaletteImageView;
    }
}

