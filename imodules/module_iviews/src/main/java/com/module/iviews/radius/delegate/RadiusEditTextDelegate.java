package com.module.iviews.radius.delegate;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.widget.EditText;

import com.module.iviews.R;


/**
 * @Function:
 * @Description:
 */
public class RadiusEditTextDelegate extends RadiusTextDelegate<RadiusEditTextDelegate> {

    private boolean mSelectionEndEnable;
    private boolean mSelectionEndOnceEnable;

    public RadiusEditTextDelegate(EditText view, Context context, AttributeSet attrs) {
        super(view, context, attrs);
    }

    @Override
    protected void initAttributes(Context context, AttributeSet attrs) {
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.RadiusEditText);

        mSelectionEndEnable = typedArray.getBoolean(R.styleable.RadiusEditText_rv_selectionEndEnable, true);
        mSelectionEndOnceEnable = typedArray.getBoolean(R.styleable.RadiusEditText_rv_selectionEndOnceEnable, false);
        typedArray.recycle();
        super.initAttributes(context, attrs);
    }

    @Override
    public void init() {
        super.init();
    }

    public boolean isSelectionEndEnable() {
        return mSelectionEndEnable;
    }

    /**
     * 是否调用setText后光标默认移动至结尾处
     *
     * @param selectionEndEnable
     * @return
     */
    public RadiusEditTextDelegate setSelectionEndEnable(boolean selectionEndEnable) {
        mSelectionEndEnable = selectionEndEnable;
        return this;
    }

    public boolean isSelectionEndOnceEnable() {
        return mSelectionEndOnceEnable;
    }

    /**
     * 是否调用setText后光标位置移动结尾效果只执行一次setSelectionEndEnable(true)后该方法方有意义
     *
     * @param selectionEndOnceEnable
     * @return
     */
    public RadiusEditTextDelegate setSelectionEndOnceEnable(boolean selectionEndOnceEnable) {
        mSelectionEndOnceEnable = selectionEndOnceEnable;
        return this;
    }

}
