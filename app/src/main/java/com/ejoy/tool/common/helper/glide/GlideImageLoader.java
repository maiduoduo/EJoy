package com.ejoy.tool.common.helper.glide;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.ejoy.tool.R;
import com.youth.banner.loader.ImageLoader;

/**
 * CN:      BannerActivity
 * Author： JSYL-DINGCL (dingcl@jsyl.com.cn)
 * Date:   2017/9/21
 * Des:    Glide
 */
public class GlideImageLoader extends ImageLoader {
    @Override
    public void displayImage(Context context, Object path, ImageView imageView) {
        //具体方法内容自己去选择，次方法是为了减少banner过多的依赖第三方包，所以将这个权限开放给使用者去选择
        Glide.with(context.getApplicationContext()).load(path)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .placeholder(R.mipmap.place_image_default)
                .error(R.mipmap.place_image_default)
                .crossFade()
                .into(imageView);
    }

    @Override
    public ImageView createImageView(Context context) {
        return null;
        //圆角
//        return new CustomRoundAngleImageViewBanner(context);
    }
}