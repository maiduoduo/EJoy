package com.module.ires.bean.utils;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.AnimRes;
import android.support.annotation.ArrayRes;
import android.support.annotation.ColorInt;
import android.support.annotation.ColorRes;
import android.support.annotation.DimenRes;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.annotation.StyleableRes;
import android.support.v4.content.ContextCompat;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v7.content.res.AppCompatResources;
import android.util.TypedValue;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

/**
 * 获取res中的资源
 */
public final class EResUtils {
//    private static Context mContext;

//    public EResUtils(Context context) {
//        this.mContext = context;
//    }

    private EResUtils() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }

    /**
     * 获取resources对象
     *
     * @return
     */
    public static Resources getResources(Context context) {
        return context.getResources();
    }

    /**
     * 获取字符串
     *
     * @param resId
     * @return
     */
    public static String getString(Context context,@StringRes int resId) {
        return getResources(context).getString(resId);
    }


    /**
     * 获取资源图片【和主体有关】
     *
     * @param resId
     * @return
     */
    public static Drawable getDrawable(Context context, @DrawableRes int resId) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            return context.getDrawable(resId);
        }
        return AppCompatResources.getDrawable(context, resId);

//        Drawable drawable = null;
//        try {
//            drawable = mContext.getResources().getDrawable(res);
//        } catch (Exception e) {
//
//        }
//        return drawable;
    }

    /**
     * 获取svg资源图片
     *
     * @param context
     * @param resId
     * @return
     */
    public static Drawable getVectorDrawable(Context context, @DrawableRes int resId) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            return context.getDrawable(resId);
        }
        return AppCompatResources.getDrawable(context, resId);
    }

    /**
     * 获取Drawable属性（兼容VectorDrawable）
     *
     * @param context
     * @param typedArray
     * @param index
     * @return
     */
    public static Drawable getDrawableAttrRes(Context context, TypedArray typedArray, @StyleableRes int index) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            return typedArray.getDrawable(index);
        } else {
            int resourceId = typedArray.getResourceId(index, -1);
            if (resourceId != -1) {
                return AppCompatResources.getDrawable(context, resourceId);
            }
        }
        return null;
    }

    /**
     * 获取dimes值，返回的是精确的值
     *
     * @param resId
     * @return
     */
    public static float getDimens(Context context,@DimenRes int resId) {
        return getResources(context).getDimension(resId);
    }

    /**
     * 获取Color值
     *
     * @param resId
     * @return
     */
    public static int getColor(Context context,@ColorRes int resId) {
        return getResources(context).getColor(resId);
    }

    /**
     * 获取ColorStateList值
     *
     * @param resId
     * @return
     */
    public static ColorStateList getColors(Context context,@ColorRes int resId) {
        return getResources(context).getColorStateList(resId);
    }

    /**
     * 获取dimes值，返回的是【去余取整】的值
     *
     * @param resId
     * @return
     */
    public static int getDimensionPixelOffset(Context context,@DimenRes int resId) {
        return getResources(context).getDimensionPixelOffset(resId);
    }

    /**
     * 获取dimes值，返回的是【4舍5入】的值
     *
     * @param resId
     * @return
     */
    public static int getDimensionPixelSize(Context context,@DimenRes int resId) {
        int result = 0;
        try {
            result = getResources(context).getDimensionPixelSize(resId);
        } catch (Exception e) {
        }
        return result;
    }

    /**
     * 获取字符串的数组
     *
     * @param resId
     * @return
     */
    public static String[] getStringArray(Context context,@ArrayRes int resId) {
        String[] result = new String[0];
        try {
            result = getResources(context).getStringArray(resId);
        } catch (Exception e) {
        }
        return result;
    }

    /**
     * 获取Drawable的数组
     *
     * @param context
     * @param resId
     * @return
     */
    public static Drawable[] getDrawableArray(Context context, @ArrayRes int resId) {
        TypedArray ta = getResources(context).obtainTypedArray(resId);
        Drawable[] icons = new Drawable[ta.length()];
        for (int i = 0; i < ta.length(); i++) {
            int id = ta.getResourceId(i, 0);
            if (id != 0) {
                icons[i] = ContextCompat.getDrawable(context, id);
            }
        }
        ta.recycle();
        return icons;
    }

    /**
     * 获取数字的数组
     *
     * @param resId
     * @return
     */
    public static int[] getIntArray(Context context,@ArrayRes int resId) {
        return getResources(context).getIntArray(resId);
    }

    /**
     * 获取动画
     *
     * @param resId
     * @return
     */
    public static Animation getAnim(Context context,@AnimRes int resId) {
        return AnimationUtils.loadAnimation(context, resId);
    }

    /**
     * Check if layout direction is RTL
     *
     * @return {@code true} if the layout direction is right-to-left
     */
    public static boolean isRtl(Context context) {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1 &&
                getResources(context).getConfiguration().getLayoutDirection() == View.LAYOUT_DIRECTION_RTL;
    }

    /**
     * Darkens a color by a given factor.
     *
     * @param color  the color to darken
     * @param factor The factor to darken the color.
     * @return darker version of specified color.
     */
    public static int darker(int color, float factor) {
        return Color.argb(Color.alpha(color), Math.max((int) (Color.red(color) * factor), 0),
                Math.max((int) (Color.green(color) * factor), 0),
                Math.max((int) (Color.blue(color) * factor), 0));
    }

    /**
     * Lightens a color by a given factor.
     *
     * @param color  The color to lighten
     * @param factor The factor to lighten the color. 0 will make the color unchanged. 1 will make the
     *               color white.
     * @return lighter version of the specified color.
     */
    public static int lighter(int color, float factor) {
        int red = (int) ((Color.red(color) * (1 - factor) / 255 + factor) * 255);
        int green = (int) ((Color.green(color) * (1 - factor) / 255 + factor) * 255);
        int blue = (int) ((Color.blue(color) * (1 - factor) / 255 + factor) * 255);
        return Color.argb(Color.alpha(color), red, green, blue);
    }


    public static void modifyTextViewDrawable(TextView v, Drawable drawable, int index) {
        drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
        //index 0:左 1：上 2：右 3：下
        if (index == 0) {
            v.setCompoundDrawables(drawable, null, null, null);
        } else if (index == 1) {
            v.setCompoundDrawables(null, drawable, null, null);
        } else if (index == 2) {
            v.setCompoundDrawables(null, null, drawable, null);
        } else {
            v.setCompoundDrawables(null, null, null, drawable);
        }
    }


    //---------------------------Resource-----------------------------------------------------------------
    public static CharSequence getText(Context mContext, int res) {
        CharSequence txt = null;
        try {
            txt = mContext.getText(res);
        } catch (Exception e) {

        }
        return txt;
    }

    public CharSequence[] getTextArray(Context mContext,int res) {
        CharSequence[] result = new CharSequence[0];
        try {
            result = mContext.getResources().getTextArray(res);
        } catch (Exception e) {
        }
        return result;
    }

    public Drawable getResDrawable(Context mContext,int res) {
        Drawable drawable = null;
        try {
            drawable = mContext.getResources().getDrawable(res);
        } catch (Exception e) {

        }
        return drawable;
    }


    public static ColorStateList getColorStateList(Context mContext, int res) {
        ColorStateList color = null;
        try {
            color = mContext.getResources().getColorStateList(res);
        } catch (Exception e) {

        }
        return color;
    }

    public static float getDimension(Context mContext, int res) {
        float result = 0;
        try {
            result = mContext.getResources().getDimension(res);
        } catch (Exception e) {
        }
        return result;
    }



    public static int getAttrColor(Context mContext, int attrRes) {
        int result = 0;
        try {
            TypedValue typedValue = new TypedValue();
            mContext.getTheme().resolveAttribute(attrRes, typedValue, true);
            result = typedValue.data;
        } catch (Exception e) {

        }
        return result;
    }

    public float getAttrFloat(Context mContext,int attrRes) {
        return getAttrFloat(mContext,attrRes, 1.0f);
    }

    public float getAttrFloat(Context mContext,int attrRes, float def) {
        float result = def;
        try {
            TypedValue typedValue = new TypedValue();
            mContext.getTheme().resolveAttribute(attrRes, typedValue, true);
            result = typedValue.getFloat();
        } catch (Exception e) {

        }
        return result == 0 ? def : result;
    }


    //==================================Drawable设置相关工具类=======================================================
    //Description:
    //1、2018-11-16 16:36:09 修改newDrawable返回
    //2、2019-4-22 17:00:49 新增{@link #setTintDrawable(Drawable, int)}{@link #setTintDrawable(Drawable, ColorStateList)}用于动态设置Drawable 颜色



    /**
     * 设置drawable宽高
     *
     * @param drawable
     * @param width
     * @param height
     */
    public static void setDrawableWidthHeight(Drawable drawable, int width, int height) {
        try {
            if (drawable != null) {
                drawable.setBounds(0, 0,
                        width >= 0 ? width : drawable.getIntrinsicWidth(),
                        height >= 0 ? height : drawable.getIntrinsicHeight());
            }
        } catch (Exception e) {
        }
    }

    /**
     * 复制当前drawable
     *
     * @param drawable
     * @return
     */
    public static Drawable getNewDrawable(Drawable drawable) {
        if (drawable == null) {
            return null;
        }
        return drawable.getConstantState().newDrawable();
    }


    /**
     * 给一个Drawable变换线框颜色
     *
     * @param drawable 需要变换颜色的drawable
     * @param color    需要变换的颜色
     * @return
     */
    public static Drawable setTintDrawable(Drawable drawable, @ColorInt int color) {
        if (drawable != null) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                try {
                    DrawableCompat.setTint(drawable, color);
                } catch (Exception e) {
                    drawable.setColorFilter(color, PorterDuff.Mode.SRC_ATOP);
                }

            } else {
                drawable.setColorFilter(color, PorterDuff.Mode.SRC_ATOP);
            }
        }
        return drawable;
    }

    public static Drawable setTintDrawable(Drawable drawable, @Nullable ColorStateList tint) {
        if (drawable != null && tint != null) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                try {
                    DrawableCompat.setTintList(drawable, tint);
                } catch (Exception e) {
                    drawable.setColorFilter(tint.getDefaultColor(), PorterDuff.Mode.SRC_ATOP);
                }
            } else {
                drawable.setColorFilter(tint.getDefaultColor(), PorterDuff.Mode.SRC_ATOP);
            }
        }
        return drawable;
    }

    public static Drawable setTintMode(Drawable drawable, @NonNull PorterDuff.Mode tintMode, int color) {
        if (drawable != null && tintMode != null) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                try {
                    DrawableCompat.setTintMode(drawable, tintMode);
                } catch (Exception e) {
                    drawable.setColorFilter(color, tintMode);
                }
            } else {
                drawable.setColorFilter(color, tintMode);
            }
        }
        return drawable;
    }


}
