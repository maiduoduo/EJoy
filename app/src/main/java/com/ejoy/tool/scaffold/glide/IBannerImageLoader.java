package com.ejoy.tool.scaffold.glide;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.PixelFormat;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.support.v7.graphics.Palette;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.GlideDrawableImageViewTarget;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.target.Target;
import com.ejoy.tool.R;
import com.ejoy.tool.common.bean.IPaletteColorInfo;
import com.youth.banner.loader.ImageLoader;

import java.util.List;



/**
 * @ClassName:  IBannerImageLoader
 * @Author: maiduoduo
 * @BLOG: https://blog.csdn.net/Maiduoudo
 * @Date: 2017/12/18
 * @des: 统一的加载banner图片(各种类型的， 热门banner，视频banner，直播间广告banner,商城banner.....)
 */
public class IBannerImageLoader extends ImageLoader {
    private Context context;
    private List<IPaletteColorInfo> colorList;
    private Palette palette;

    public IBannerImageLoader(List<IPaletteColorInfo> colorList) {
        this.colorList = colorList;
    }

    //具体方法内容自己去选择，次方法是为了减少banner过多的依赖第三方包，所以将这个权限开放给使用者去选择
    @Override
    public void displayImage(Context context, final Object imgObj, ImageView imageView) {
        this.context = context;
        if (imgObj != null) {
            imageView.setPadding(30, 0, 30, 0);
            Glide.with(context)
                    .load(imgObj.toString())
//                    .load(R.drawable.bimg_banner_image8)
                    .diskCacheStrategy(DiskCacheStrategy.NONE)
                    .override(Target.SIZE_ORIGINAL, Target.SIZE_ORIGINAL)
                    .bitmapTransform(new IGlideRoundImage(context,20))
                    .into(new GlideDrawableImageViewTarget(imageView) {
                        @Override
                        public void onLoadStarted(Drawable placeholder) {
                            super.onLoadStarted(placeholder);
                            Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.img_default_placeload);
//                            Bitmap bitmap = drawableToBitmap(resource);
                            setColorList(bitmap, imgObj.toString());
                        }

                        @Override
                        public void onResourceReady(GlideDrawable resource, GlideAnimation<? super GlideDrawable> animation) {
                            super.onResourceReady(resource, animation);
                            Bitmap bitmap = drawableToBitmap(resource);
                            setColorList(bitmap, imgObj.toString());


                        }
                    });
        }
    }


    private void setColorList(Bitmap bitmap, String imgUrl) {
        if (colorList == null) {
            return;
        }
        palette = Palette.from(bitmap).generate();
        for (int i = 0; i < colorList.size(); i++) {
            if (colorList.get(i).getImgUrl().equals(imgUrl)) {// imgUrl作为识别标志
                if (palette.getVibrantSwatch() != null) {
                    colorList.get(i).setVibrantColor(palette.getVibrantSwatch().getRgb());
                }
                if (palette.getDarkVibrantSwatch() != null) {
                    colorList.get(i).setVibrantDarkColor(palette.getDarkVibrantSwatch().getRgb());
                }
                if (palette.getLightVibrantSwatch() != null) {
                    colorList.get(i).setVibrantLightColor(palette.getLightVibrantSwatch().getRgb());
                }
                if (palette.getMutedSwatch() != null) {
                    colorList.get(i).setMutedColor(palette.getMutedSwatch().getRgb());
                }
                if (palette.getDarkMutedSwatch() != null) {
                    colorList.get(i).setMutedDarkColor(palette.getDarkMutedSwatch().getRgb());
                }
                if (palette.getLightVibrantSwatch() != null) {
                    colorList.get(i).setMutedLightColor(palette.getLightVibrantSwatch().getRgb());
                }
            }
        }
    }


    /**
     * Vibrant （有活力）
     * Vibrant dark（有活力 暗色）
     * Vibrant light（有活力 亮色）
     * Muted （柔和）
     * Muted dark（柔和 暗色）
     * Muted light（柔和 亮色）
     */

    public int getVibrantColor(int position) {
        return colorList.get(position).getVibrantColor();
    }

    public int getVibrantDarkColor(int position) {
        return colorList.get(position).getVibrantDarkColor();
    }

    public int getVibrantLightColor(int position) {
        return colorList.get(position).getVibrantLightColor();
    }

    public int getMutedColor(int position) {
        return colorList.get(position).getMutedColor();
    }

    public int getMutedDarkColor(int position) {
        return colorList.get(position).getMutedDarkColor();
    }

    public int getMutedLightColor(int position) {
        return colorList.get(position).getMutedLightColor();
    }


    /**
     * Drawable转换成一个Bitmap
     *
     * @param drawable drawable对象
     * @return
     */
    public static final Bitmap drawableToBitmap(Drawable drawable) {
        Bitmap bitmap = Bitmap.createBitmap( drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight(),
                drawable.getOpacity() != PixelFormat.OPAQUE ? Bitmap.Config.ARGB_8888 : Bitmap.Config.RGB_565);
        Canvas canvas = new Canvas(bitmap);
        drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
        drawable.draw(canvas);
        return bitmap;
    }
}
