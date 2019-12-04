package com.ejoy.tool.scaffold.utils;

import android.content.Context;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.ejoy.tool.R;

import java.io.File;


public class GlideUtils {


    public static void showImage(@NonNull Context context, @NonNull String url, @NonNull ImageView imageView){
        //具体方法内容自己去选择，次方法是为了减少banner过多的依赖第三方包，所以将这个权限开放给使用者去选择
        Glide.with(context.getApplicationContext()).load(url)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .placeholder(R.mipmap.place_image_default)
                .error(R.mipmap.place_image_default)
                .crossFade()
                .fitCenter()
                .into(imageView);


    }
    public static void showImage(@NonNull Context context, @NonNull String url, @NonNull ImageView imageView, @DrawableRes int errorRes, @DrawableRes int placeholderRes){
        Glide.with(context.getApplicationContext()).load(url)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .placeholder(placeholderRes)
                .error(errorRes)
                .crossFade()
                .centerCrop()
                .into(imageView);


    }
    public static void showImage(@NonNull Context context, @NonNull File file, @NonNull ImageView imageView, @DrawableRes int errorRes, @DrawableRes int placeholderRes){
        Glide.with(context.getApplicationContext()).load(file)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .placeholder(placeholderRes)
                .error(errorRes)
                .crossFade()
                .centerCrop()
                .into(imageView);


    }
    public static void showImage(@NonNull Context context, @DrawableRes int drawableRes, @NonNull ImageView imageView){

        Glide.with(context.getApplicationContext()).load(drawableRes)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .placeholder(R.mipmap.place_image_default)
                .error(R.mipmap.place_image_default)
                .crossFade()
                .centerCrop()
                .into(imageView);
    }


    public static void showCompressImage(@NonNull Context context, @NonNull File file, @NonNull ImageView imageView){
        //具体方法内容自己去选择，次方法是为了减少banner过多的依赖第三方包，所以将这个权限开放给使用者去选择
        Glide.with(context.getApplicationContext()).load(file)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .placeholder(R.drawable.take_picture_pressed)
                .error(R.drawable.take_picture_pressed)
                .crossFade()
                .centerCrop()
                .into(imageView);


    }
}
