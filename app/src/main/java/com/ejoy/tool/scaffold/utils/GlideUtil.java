//package com.ejoy.tool.scaffold.utils;
//
//import android.content.Context;
//import android.text.TextUtils;
//import android.widget.ImageView;
//
//import com.bumptech.glide.Glide;
//import com.bumptech.glide.load.engine.DiskCacheStrategy;
//import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
//import com.bumptech.glide.request.RequestOptions;
//import com.ejoy.tool.R;
//
//
///**
// * CN:      GlideUtil
// * Author： JSYL-DINGCL (dingcl@jsyl.com.cn)
// * Date:   2019/10/10
// * Des:    Glide工具类
// *              Glide版本：4.4.0
// */
//public class GlideUtil {
//
//    public static void load(Context context,
//                            String url,
//                            ImageView imageView) {
//        RequestOptions options = new RequestOptions()
//                .placeholder(R.drawable.pa_shape)
//                .centerCrop()
//                .diskCacheStrategy(DiskCacheStrategy.ALL);//让Glide既缓存全尺寸图片，下次在任何ImageView中加载图片的时候，全尺寸的图片将从缓存中取出，重新调整大小，然后缓存
//
//        load(context,url,imageView,options);
//
//    }
//
//    public static void load(Context context,
//                            String url,
//                            ImageView imageView,
//                            RequestOptions options) {
//
//        if(TextUtils.isEmpty(url)) return;
//        if(null == context) return;
//        if(null == imageView) return;
//
//        Glide.with(context)
//                .load(url)
//                .transition(new DrawableTransitionOptions().crossFade())// 渐入渐出效果
//                .apply(options)
//                .into(imageView);
//    }
//}
