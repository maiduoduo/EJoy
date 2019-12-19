package com.ejoy.tool.scaffold.view.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

import com.ejoy.tool.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 侧边导航
 */
public class SideIndexBar extends View{
    private static final String[] DEFAULT_INDEX_ITEMS = {"区县","定位", "热门", "A", "B", "C", "D", "E", "F", "G", "H",
            "J", "K", "L", "M", "N", "P", "Q", "R", "S", "T", "W", "X", "Y", "Z"};

    private List<String> mIndexItems;
    private float mItemHeight; //每个index的高度
    private int mTextSize;      //sp
    private int mTextTouchSize;      //sp
    private int mTextColor;
    private int mTextTouchedColor;
    private int mCurrentIndex = -1;

    private Paint mPaint;
    private Paint mTouchedPaint;

    private int mWidth;
    private int mHeight;
    private float mTopMargin;   //居中绘制，文字绘制起点和控件顶部的间隔

    private TextView mOverlayTextView;
    private OnIndexTouchedChangedListener mOnIndexChangedListener;

    private int navigationBarHeight;
    private Context _context;

    public void setNavigationBarHeight(int height){
        this.navigationBarHeight = height;
    }

    public SideIndexBar(Context context) {
        this(context, null);
        _context = context;
    }

    public SideIndexBar(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
        _context = context;
    }

    public SideIndexBar(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        _context = context;
        init(context);
    }

    private void init(Context context) {
        mIndexItems = new ArrayList<>();
        mIndexItems.addAll(Arrays.asList(DEFAULT_INDEX_ITEMS));

        TypedValue typedValue = new TypedValue();
        context.getTheme().resolveAttribute(R.attr.cpIndexBarTextSize, typedValue, true);
        mTextSize = context.getResources().getDimensionPixelSize(R.dimen.cp_slide_size);
        mTextTouchSize = context.getResources().getDimensionPixelSize(R.dimen.cp_slide_touch_size);

        context.getTheme().resolveAttribute(R.attr.cpIndexBarNormalTextColor, typedValue, true);
        mTextColor = context.getResources().getColor(R.color.black_66);

        context.getTheme().resolveAttribute(R.attr.cpIndexBarSelectedTextColor, typedValue, true);
        mTextTouchedColor = context.getResources().getColor(R.color.Orange2);

        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setTextSize(mTextSize);
        mPaint.setFakeBoldText(true);
        mPaint.setColor(mTextColor);

        mTouchedPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mTouchedPaint.setTextSize(mTextTouchSize);
        mTouchedPaint.setColor(mTextTouchedColor);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        String index;
        for (int i = 0; i < mIndexItems.size(); i++) {
            index = mIndexItems.get(i);
            Paint.FontMetrics fm = mPaint.getFontMetrics();
            canvas.drawText(index,
                    (mWidth - mPaint.measureText(index)) / 2,
                    mItemHeight / 2 + (fm.bottom-fm.top) / 2 - fm.bottom + mItemHeight * i + mTopMargin,
                    i == mCurrentIndex ? mTouchedPaint : mPaint);
        }
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mWidth = getWidth();
        if (Math.abs(h - oldh) == navigationBarHeight){
            //底部导航栏隐藏或显示
            mHeight = h;
        }else {
            //避免软键盘弹出时挤压
            mHeight = Math.max(getHeight(), oldh);
        }
        mItemHeight = mHeight / mIndexItems.size();
        mTopMargin = (mHeight - mItemHeight * mIndexItems.size()) / 2;
    }

    @Override
    public boolean performClick() {
        return super.performClick();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        performClick();
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
            case MotionEvent.ACTION_MOVE:
                float y = event.getY();
                int indexSize = mIndexItems.size();
                int touchedIndex = (int) (y / mItemHeight);
                if (touchedIndex < 0) {
                    touchedIndex = 0;
                }else if (touchedIndex >= indexSize) {
                    touchedIndex = indexSize - 1;
                }
                if (mOnIndexChangedListener != null && touchedIndex >= 0 && touchedIndex < indexSize){
                    if (touchedIndex != mCurrentIndex) {
                        mCurrentIndex = touchedIndex;
                        if (mOverlayTextView != null){
                            mOverlayTextView.setVisibility(VISIBLE);
                            mOverlayTextView.setText(mIndexItems.get(touchedIndex));
                            mOverlayTextView.setTextSize(25);
                            mOverlayTextView.setTextColor(ContextCompat.getColor(_context,R.color.Orange2));
                        }
                        mOnIndexChangedListener.onIndexChanged(mIndexItems.get(touchedIndex), touchedIndex);
                        invalidate();
                    }
                }
                break;
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_CANCEL:
                mCurrentIndex = -1;
                if (mOverlayTextView != null){
                    mOverlayTextView.setVisibility(GONE);
                }
                invalidate();
                break;
        }
        return true;
    }

    public SideIndexBar setOverlayTextView(TextView overlay){
        this.mOverlayTextView = overlay;
        mOverlayTextView.setBackgroundResource(R.drawable.shape_overlay);
        return this;
    }

    public SideIndexBar setOnIndexChangedListener(OnIndexTouchedChangedListener listener){
        this.mOnIndexChangedListener = listener;
        return this;
    }

    public interface OnIndexTouchedChangedListener{
        void onIndexChanged(String index, int position);
    }
}
