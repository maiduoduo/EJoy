package com.module.ires.bean.utils;
//  ┏┓　　　┏┓
//┏┛┻━━━┛┻┓
//┃　　　　　　　┃
//┃　　　━　　　┃
//┃　┳┛　┗┳　┃
//┃　　　　　　　┃
//┃　　　┻　　　┃
//┃　　　　　　　┃
//┗━┓　　　┏━┛
//    ┃　　　┃                  神兽保佑
//    ┃　　　┃                  永无BUG！
//    ┃　　　┗━━━┓
//    ┃　　　　　　　┣┓
//    ┃　　　　　　　┏┛
//    ┗┓┓┏━┳┓┏┛
//      ┃┫┫　┃┫┫
//      ┗┻┛　┗┻┛

import android.annotation.TargetApi;
import android.app.WallpaperManager;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.renderscript.Allocation;
import android.renderscript.Element;
import android.renderscript.RenderScript;
import android.renderscript.ScriptIntrinsicBlur;
import android.util.Log;
import android.view.View;

import static android.support.v4.view.ViewCompat.setBackground;

import android.graphics.Matrix;

/**
 * CN:      EBlurHelper
 * Author： JSYL-DINGCL (dingcl@jsyl.com.cn)
 * Date:   2020/1/13
 * Des:    TODO:背景高斯模糊工具类
 */
public class EBlurHelper {
    private int mImageId = -1;//背景图
    private View mContainer;//容器
    private Context mContext;//上下文
    private Float mRadius = 10f;//模糊程度

    public EBlurHelper setImageResourse(int id) {
        mImageId = id;
        return this;
    }

    public EBlurHelper setContainerView(View view) {
        mContainer = view;
        return this;
    }

    public EBlurHelper setRadius(Float radius) {
        mRadius = radius;
        return this;
    }

    public EBlurHelper setContext(Context context) {
        mContext = context;
        return this;
    }

    private void applyBlur() {
        //默认状态下是以桌面壁纸为背景

        Bitmap bitmap;
        if (mImageId == -1) {
            WallpaperManager wallpaperManager = WallpaperManager.getInstance(mContext);
            Drawable wallpaperDrawable = wallpaperManager.getDrawable();
            bitmap = ((BitmapDrawable) wallpaperDrawable).getBitmap();
        } else {
            bitmap = BitmapFactory.decodeResource(mContext.getResources(), mImageId);
        }
        blur(bitmap);
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    private void blur(Bitmap bkg) {
//        Bitmap bkg = bkg;
        long startMs = System.currentTimeMillis();
        Float radius;
        if (mRadius < 25f && mRadius > 0f) {
            radius = mRadius;
        } else {
            radius = 3f;
        }
        bkg = small(bkg);
        Bitmap bitmap = bkg.copy(bkg.getConfig(), true);
        RenderScript rs = RenderScript.create(mContext);
        Allocation input = Allocation.createFromBitmap(rs, bkg, Allocation.MipmapControl.MIPMAP_NONE,
                Allocation.USAGE_SCRIPT);
        Allocation output = Allocation.createTyped(rs, input.getType());
        ScriptIntrinsicBlur script = ScriptIntrinsicBlur.create(rs, Element.U8_4(rs));
        script.setRadius(radius);
        script.setInput(input);
        script.forEach(output);
        output.copyTo(bitmap);

        bitmap = big(bitmap);
        setBackground(mContainer, new BitmapDrawable(mContext.getResources(), bitmap));
        rs.destroy();
        Log.d("BlurHelper", "blur take away:" + (System.currentTimeMillis() - startMs) + "ms");
    }

    private Bitmap big(Bitmap bitmap) {
        Matrix matrix = new Matrix();
        matrix.postScale(2f, 2f);//长和宽放大缩小的比例
        return Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
    }

    private Bitmap small(Bitmap bitmap) {
        Matrix matrix = new Matrix();
        matrix.postScale(0.25f, 0.25f);//长和宽放大缩小的比例
        return Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
    }

    public void build() {
        if (mContext == null) {
            Log.e("GasBlurHelperConfig", "contextConfig is null!");
            return;
        }

        if (mContainer == null && mContainer instanceof View) {
            Log.e("GasBlurHelperConfig", "mContainerId is null!");
            return;
        }
        applyBlur();
    }
}
