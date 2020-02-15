package com.ejoy.tool.scaffold.glide;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.ejoy.tool.scaffold.view.IRoundImageView;
import com.youth.banner.loader.ImageLoader;


public class IGlideImageLoader extends ImageLoader {
    //图片圆角弧度
    private float radius;

    public IGlideImageLoader(float radius){
        this.radius = radius;
    }

    @Override
    public void displayImage(Context context, Object path, ImageView imageView) {
        //Glide 加载图片简单用法
        Glide
                .with(context)
                .load(path)
                .centerCrop()
                .into(imageView);
    }
    /**
     * 自定义圆角类
     * @param context
     * @return
     */
    @Override
    public ImageView createImageView(Context context) {
        IRoundImageView roundImageView = new IRoundImageView(context);
        roundImageView.setImageViewRadius(context,radius);
        return roundImageView;

    }
}
