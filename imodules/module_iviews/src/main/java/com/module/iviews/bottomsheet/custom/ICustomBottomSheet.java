package com.module.iviews.bottomsheet.custom;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetDialogFragment;
import android.support.v4.widget.NestedScrollView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import com.module.iviews.R;


/**
  * Des  :   TODO: 自定义NestedScrollView
 */
public class ICustomBottomSheet extends NestedScrollView {
    public ICustomBottomSheet(Context context) {
        super(context);
        init(context, null);
    }


    public ICustomBottomSheet(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public ICustomBottomSheet(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }


    private void init(Context context, @Nullable AttributeSet attrs) {
        LayoutInflater.from(context).inflate(R.layout.layout_expand_custom_bottomsheet, this);
        if (attrs != null) {
            TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.ICustomBottomSheet);
            //find our header container and inflate header if required
            ViewGroup headerContainer = findViewById(R.id.headerContainer);
            int headerLayoutReferenceID = a.getResourceId(R.styleable.ICustomBottomSheet_cbs_header_layout, 0);
            if (headerLayoutReferenceID != 0) {
                LayoutInflater.from(context).inflate(headerLayoutReferenceID, headerContainer);
            }
            a.recycle();
        }
    }
}
