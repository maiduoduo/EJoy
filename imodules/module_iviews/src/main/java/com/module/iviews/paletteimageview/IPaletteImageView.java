package com.module.iviews.paletteimageview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Message;
import android.support.v7.graphics.Palette;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;

import com.module.iviews.R;

import java.lang.ref.WeakReference;

/**
 * 配色ImageView
 *
 * 可以解析图片中的主色调，**默认将主色调作为控件阴影的颜色
 * 可以自定义设置控件的阴影颜色
 * 可以控制控件四个角的圆角大小（如果控件设置成正方向，随着圆角半径增大，可以将控件变成圆形）
 * 可以控制控件的阴影半径大小
 * 可以分别控制阴影在x方向和y方向上的偏移量
 * 可以将图片中的颜色解析出六种主题颜色，每一种主题颜色都有相应的匹配背景、标题、正文的推荐颜色
 */
public class IPaletteImageView extends AppCompatImageView {

    private static final String TAG = IPaletteImageView.class.getName();
    private static final int MSG = 0x101;
    private static final int DEFAULT_PADDING = 40;
    private static final int DEFAULT_OFFSET = 20;
    private static final int DEFAULT_SHADOW_RADIUS = 20;
    private Paint mPaintShadow;
    private Paint mPaint;
    private int mRadius;
    private int mPadding;
    private Bitmap mBitmap;
    private int mImgId;
    private AsyncTask mAsyncTask;
    public int mMainColor = -1;
    private int mOffsetX = DEFAULT_OFFSET;
    private int mOffsetY = DEFAULT_OFFSET;
    private int mShadowRadius = DEFAULT_SHADOW_RADIUS;
    private Palette mPalette;
    private RectF mRectFShadow;
    private Bitmap mRealBitmap;
//    private int mOnMeasureHeightMode = -1;
    private int mOnMeasureHeightMode = MeasureSpec.UNSPECIFIED;
    public IPaletteImageView mInstance;
    private Bitmap mRoundBitmap;
    private RectF mRoundRectF;
    private PorterDuffXfermode mPorterDuffXfermode;
    private OnParseColorListener mListener;
    private Handler mHandler;

    public IPaletteImageView(Context context) {
        this(context, null);
    }

    public IPaletteImageView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public IPaletteImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }


    private void init(Context context, AttributeSet attrs) {
        this.mInstance = this;
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.IPaletteImageView);
        mRadius = a.getDimensionPixelSize(R.styleable.IPaletteImageView_paletteRadius, 0);
        mImgId = a.getResourceId(R.styleable.IPaletteImageView_paletteSrc, 0);
        mPadding = a.getDimensionPixelSize(R.styleable.IPaletteImageView_palettePadding, DEFAULT_PADDING);
        mOffsetX = a.getDimensionPixelSize(R.styleable.IPaletteImageView_paletteOffsetX,DEFAULT_OFFSET);
        mOffsetY = a.getDimensionPixelSize(R.styleable.IPaletteImageView_paletteOffsetY,DEFAULT_OFFSET);
        mShadowRadius = a.getDimensionPixelSize(R.styleable.IPaletteImageView_paletteShadowRadius,DEFAULT_SHADOW_RADIUS);
        a.recycle();

        setPadding(mPadding, mPadding, mPadding, mPadding);

        mPaintShadow = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaintShadow.setDither(true);
        setLayerType(LAYER_TYPE_SOFTWARE, null);
        setBackgroundColor(getResources().getColor(android.R.color.transparent));
        mPaint  = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setDither(true);
        mPorterDuffXfermode = new PorterDuffXfermode(PorterDuff.Mode.SRC_IN);
        mHandler = new MyHandler(this);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int width = MeasureSpec.getSize(widthMeasureSpec);
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int height = MeasureSpec.getSize(heightMeasureSpec);
        mOnMeasureHeightMode = MeasureSpec.getMode(heightMeasureSpec);
        if (mOnMeasureHeightMode == MeasureSpec.UNSPECIFIED) {
            if (mBitmap != null) {
                height = (int) ((width - mPadding * 2) * (mBitmap.getHeight() * 1.0f / mBitmap.getWidth())) + mPadding * 2;
            }
            if (mImgId != 0 && mRealBitmap != null) {
                height = mRealBitmap.getHeight() + mPadding * 2;
            }
        }
        if (mBitmap != null) {
            height = (int) ((width - mPadding * 2) * (mBitmap.getHeight() * 1.0f / mBitmap.getWidth())) + mPadding * 2;
        }
        setMeasuredDimension(width, height);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        zipBitmap(mImgId, mBitmap, mOnMeasureHeightMode);
        mRectFShadow = new RectF(mPadding, mPadding, getWidth() - mPadding, getHeight() - mPadding);
        mRoundRectF = new RectF(0, 0, getWidth() - mPadding * 2, getHeight() - mPadding * 2);
        mRoundBitmap = createRoundConerImage(mRealBitmap,mRadius);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        if (mRealBitmap != null ) {
            canvas.drawRoundRect(mRectFShadow, mRadius, mRadius, mPaintShadow);
            if (mRoundBitmap != null)canvas.drawBitmap(mRoundBitmap, mPadding, mPadding, null);
            if (mMainColor != -1) mAsyncTask.cancel(true);
        }
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        mHandler.removeCallbacksAndMessages(null);
    }

    public void setShadowColor(int color){
        this.mMainColor = color;
        mHandler.sendEmptyMessage(MSG);
    }

    public void setBitmap(Bitmap bitmap) {
        this.mBitmap = bitmap;
        zipBitmap(mImgId, mBitmap, mOnMeasureHeightMode);
    }

    public void setPaletteRadius(int raius) {
        this.mRadius = raius;
        mRoundBitmap = createRoundConerImage(mRealBitmap,mRadius);
        invalidate();
    }

    public void setPaletteShadowOffset(int offsetX, int offsetY) {
        if (offsetX >= mPadding) {
            this.mOffsetX = mPadding;
        } else {
            this.mOffsetX = offsetX;
        }
        if (offsetY > mPadding) {
            this.mOffsetX = mPadding;
        } else {
            this.mOffsetY = offsetY;
        }

        mHandler.sendEmptyMessage(MSG);
    }

    public void setPaletteShadowRadius(int radius) {
        this.mShadowRadius = radius;
        mHandler.sendEmptyMessage(MSG);
    }

    public void setOnParseColorListener(OnParseColorListener listener) {
        this.mListener = listener;
    }

    private void initShadow(Bitmap bitmap) {
        if (bitmap != null) {
            mAsyncTask = Palette.from(bitmap).generate(paletteAsyncListener);
        }
    }

    private Bitmap createRoundConerImage(Bitmap source, int radius) {
        if (source == null) return null;
        Bitmap target = Bitmap.createBitmap(getWidth() - mPadding * 2, getHeight() - mPadding * 2, Bitmap.Config.ARGB_4444);
        Canvas canvas = new Canvas(target);
        canvas.drawRoundRect(mRoundRectF, radius, radius, mPaint);
        mPaint.setXfermode(mPorterDuffXfermode);
        canvas.drawBitmap(source, 0, 0, mPaint);
        mPaint.setXfermode(null);
        return target;
    }

    private void zipBitmap(int imgId, Bitmap bitmap, int heightNode) {
        WeakReference<Matrix> weakMatrix = new WeakReference<Matrix>(new Matrix());
        if (weakMatrix.get() == null) return;
        Matrix matrix = weakMatrix.get();
        int reqWidth = getWidth() - mPadding - mPadding;
        int reqHeight = getHeight() - mPadding - mPadding;
        if (reqHeight <= 0 || reqWidth <= 0) return;
        int rawWidth = 0;
        int rawHeight = 0;
        if (imgId != 0 && bitmap == null) {
            WeakReference<BitmapFactory.Options> weakOptions = new WeakReference<BitmapFactory.Options>(new BitmapFactory.Options());
            if (weakOptions.get() == null) return;
            BitmapFactory.Options options = weakOptions.get();
            BitmapFactory.decodeResource(getResources(), imgId, options);
            options.inJustDecodeBounds = true;
            rawWidth = options.outWidth;
            rawHeight = options.outHeight;
            options.inSampleSize = calculateInSampleSize(rawWidth, rawHeight, getWidth() - mPadding * 2, getHeight() - mPadding * 2);
            options.inJustDecodeBounds = false;
            bitmap = BitmapFactory.decodeResource(getResources(), mImgId, options);
        } else if (imgId == 0 && bitmap != null) {
            rawWidth = bitmap.getWidth();
            rawHeight = bitmap.getHeight();
            float scale = rawHeight * 1.0f / rawWidth;
            mRealBitmap = Bitmap.createScaledBitmap(bitmap, reqWidth, (int) (reqWidth * scale), true);
            initShadow(mRealBitmap);
            return;
        }
        if (heightNode == 0) {
            float scale = rawHeight * 1.0f / rawWidth;
            mRealBitmap = Bitmap.createScaledBitmap(bitmap, reqWidth, (int) (reqWidth * scale), true);
        } else {
            int dx = 0;
            int dy = 0;
            int small = Math.min(rawHeight, rawWidth);
            int big = Math.max(reqWidth, reqHeight);
            float scale = big * 1.0f / small;
            matrix.setScale(scale, scale);
            if (rawHeight > rawWidth) {
                dy = (rawHeight - rawWidth) / 2;
            } else if (rawHeight < rawWidth) {
                dx = (rawWidth - rawHeight) / 2;
            }
            if (small <= 0) return;
            try {

                mRealBitmap = Bitmap.createBitmap(bitmap, dx, dy, small, small, matrix, true);
            }catch (IllegalArgumentException e){
                e.printStackTrace();
            }
        }
        initShadow(mRealBitmap);

    }

    private int calculateInSampleSize(int rawWidth, int rawHeight, int reqWidth, int reqHeight) {
        int inSampleSize = 1;
        if (rawHeight > reqHeight || rawWidth > reqWidth) {
            int halfHeight = rawHeight / 2;
            int halfWidth = rawWidth / 2;
            while ((halfHeight / inSampleSize) >= reqHeight && (halfWidth / inSampleSize) >= reqWidth) {
                inSampleSize *= 2;
            }
        }
        return inSampleSize;
    }

    private Palette.PaletteAsyncListener paletteAsyncListener = new Palette.PaletteAsyncListener() {
        @Override
        public void onGenerated(Palette palette) {
            if (palette != null) {
                mPalette = palette;
                mMainColor = palette.getDominantSwatch().getRgb();
                mHandler.sendEmptyMessage(MSG);
                if (mListener != null) mListener.onComplete(mInstance);
            } else {
                if (mListener != null) mListener.onFail();
            }
        }
    };

    public int[] getVibrantColor() {
        if (mPalette == null || mPalette.getVibrantSwatch() == null) return null;
        int[] arry = new int[3];
        arry[0] = mPalette.getVibrantSwatch().getTitleTextColor();
        arry[1] = mPalette.getVibrantSwatch().getBodyTextColor();
        arry[2] = mPalette.getVibrantSwatch().getRgb();
        return arry;
    }

    public int[] getDarkVibrantColor() {
        if (mPalette == null || mPalette.getDarkVibrantSwatch() == null) return null;
        int[] arry = new int[3];
        arry[0] = mPalette.getDarkVibrantSwatch().getTitleTextColor();
        arry[1] = mPalette.getDarkVibrantSwatch().getBodyTextColor();
        arry[2] = mPalette.getDarkVibrantSwatch().getRgb();
        return arry;
    }

    public int[] getLightVibrantColor() {
        if (mPalette == null || mPalette.getLightVibrantSwatch() == null) return null;
        int[] arry = new int[3];
        arry[0] = mPalette.getLightVibrantSwatch().getTitleTextColor();
        arry[1] = mPalette.getLightVibrantSwatch().getBodyTextColor();
        arry[2] = mPalette.getLightVibrantSwatch().getRgb();
        return arry;
    }

    public int[] getMutedColor() {
        if (mPalette == null || mPalette.getMutedSwatch() == null) return null;
        int[] arry = new int[3];
        arry[0] = mPalette.getMutedSwatch().getTitleTextColor();
        arry[1] = mPalette.getMutedSwatch().getBodyTextColor();
        arry[2] = mPalette.getMutedSwatch().getRgb();
        return arry;
    }

    public int[] getDarkMutedColor() {
        if (mPalette == null || mPalette.getDarkMutedSwatch() == null) return null;
        int[] arry = new int[3];
        arry[0] = mPalette.getDarkMutedSwatch().getTitleTextColor();
        arry[1] = mPalette.getDarkMutedSwatch().getBodyTextColor();
        arry[2] = mPalette.getDarkMutedSwatch().getRgb();
        return arry;
    }

    public int[] getLightMutedColor() {
        if (mPalette == null || mPalette.getLightMutedSwatch() == null) return null;
        int[] arry = new int[3];
        arry[0] = mPalette.getLightMutedSwatch().getTitleTextColor();
        arry[1] = mPalette.getLightMutedSwatch().getBodyTextColor();
        arry[2] = mPalette.getLightMutedSwatch().getRgb();
        return arry;
    }


    private static class MyHandler extends Handler {
        private final WeakReference<IPaletteImageView> mPaletteImageViewWeakReference;
        public MyHandler(IPaletteImageView paletteImageView){
            mPaletteImageViewWeakReference = new WeakReference<IPaletteImageView>(paletteImageView);
        }

        @Override
        public void handleMessage(Message msg) {
            if (mPaletteImageViewWeakReference.get() != null) {
                IPaletteImageView paletteImageView = mPaletteImageViewWeakReference.get();
                if (paletteImageView.mOffsetX < DEFAULT_OFFSET) paletteImageView.mOffsetX = DEFAULT_OFFSET;
                if (paletteImageView.mOffsetY < DEFAULT_OFFSET) paletteImageView.mOffsetY = DEFAULT_OFFSET;
                if (paletteImageView.mShadowRadius < DEFAULT_SHADOW_RADIUS) paletteImageView.mShadowRadius = DEFAULT_SHADOW_RADIUS;
                paletteImageView.mPaintShadow.setShadowLayer(paletteImageView.mShadowRadius, paletteImageView.mOffsetX,paletteImageView. mOffsetY, paletteImageView.mMainColor);
                paletteImageView.invalidate();
            }
        }
    }
}