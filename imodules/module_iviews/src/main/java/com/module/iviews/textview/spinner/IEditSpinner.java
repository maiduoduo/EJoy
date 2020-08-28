package com.module.iviews.textview.spinner;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.support.annotation.ColorInt;
import android.support.annotation.DrawableRes;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListPopupWindow;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;

import com.module.iviews.R;

import java.util.List;

/**
 * 支持输入模糊查询的spinner
 */
public class IEditSpinner extends RelativeLayout implements View.OnClickListener, AdapterView.OnItemClickListener, TextWatcher {
    private EditText editText;
    private ImageView mRightIv;
    private View mRightImageTopView;
    private Context mContext;
    private ListPopupWindow popupWindow;
    IBaseEditSpinnerAdapter adapter;
    private long popupWindowHideTime;
    private Animation mAnimation;
    private Animation mResetAnimation;
    private AdapterView.OnItemClickListener mOnItemClickListener;
    private int maxLine = 1;
    private boolean isClickItem = false;

    public IEditSpinner(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mContext = context;
        initView(attrs);
        initAnimation();
    }


    public void setItemData(List<String> data) {
        adapter = new ISimpleAdapter(mContext, data);
        setAdapter(adapter);
    }

    public int getListNum(){
        return adapter.getCount();
    }

    public void setOnItemClickListener(AdapterView.OnItemClickListener listener) {
        this.mOnItemClickListener = listener;
    }

    public void setText(String text) {
        editText.setText(text);
    }

    public void setTextColor(@ColorInt int color) {
        editText.setTextColor(color);
    }

    public String getText() {
        return editText.getText().toString();
    }

    public void setHint(String hint) {
        editText.setHint(hint);
    }

    public void setRightImageDrawable(Drawable drawable) {
        mRightIv.setImageDrawable(drawable);
    }

    public void setRightImageResource(@DrawableRes int res) {
        mRightIv.setImageResource(res);
    }

    public void setAdapter(IBaseEditSpinnerAdapter adapter) {
        this.adapter = adapter;
        setBaseAdapter(this.adapter);
    }

    public void setMaxLine(int maxLine) {
        this.maxLine = maxLine;
        editText.setMaxLines(this.maxLine);
    }

    private void initAnimation() {
//        mAnimation = new RotateAnimation(0, -90, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        mAnimation = new RotateAnimation(0, -180, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        mAnimation.setDuration(300);
        mAnimation.setFillAfter(true);
//        mResetAnimation = new RotateAnimation(-90, 0, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        mResetAnimation = new RotateAnimation(-180, 0, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        mResetAnimation.setDuration(300);
        mResetAnimation.setFillAfter(true);
    }

    private void initView(AttributeSet attrs) {
        LayoutInflater.from(mContext).inflate(R.layout.layout_edit_spinner, this);
        editText = (EditText) findViewById(R.id.edit_sipnner_edit);
        mRightIv = (ImageView) findViewById(R.id.edit_spinner_expand);
        mRightImageTopView = findViewById(R.id.edit_spinner_expand_above);
        mRightImageTopView.setOnClickListener(this);
        mRightImageTopView.setClickable(false);
        mRightIv.setOnClickListener(this);
//        mRightIv.setRotation(90);
//        mRightIv.setRotation(360);
        editText.addTextChangedListener(this);
        TypedArray tArray = mContext.obtainStyledAttributes(attrs,
                R.styleable.IEditSpinner);
        editText.setHint(tArray.getString(R.styleable.IEditSpinner_hint));
        int imageId = tArray.getResourceId(R.styleable.IEditSpinner_rightImage, 0);
        if (imageId != 0) {
            mRightIv.setImageResource(imageId);
        }
        int bg = tArray.getResourceId(R.styleable.IEditSpinner_Background, 0);
        if (bg != 0) {
            editText.setBackgroundResource(bg);
        }
        maxLine = tArray.getInt(R.styleable.IEditSpinner_maxLine, 1);
        editText.setMaxLines(maxLine);
        tArray.recycle();
    }

    private final void setBaseAdapter(BaseAdapter adapter) {
        if (popupWindow == null) {
            initPopupWindow();
        }
        popupWindow.setAdapter(adapter);
    }

    private void initPopupWindow() {
        popupWindow = new ListPopupWindow(mContext) {

            @Override
            public void show() {
                super.show();
                mRightImageTopView.setClickable(true);
                mRightIv.startAnimation(mAnimation);
            }

            @Override
            public void dismiss() {
                super.dismiss();
            }

        };
        popupWindow.setOnItemClickListener(this);
        popupWindow.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
        popupWindow.setPromptPosition(ListPopupWindow.POSITION_PROMPT_BELOW);
        popupWindow.setWidth(ViewGroup.LayoutParams.WRAP_CONTENT);
        popupWindow.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        popupWindow.setAnchorView(editText);
        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                popupWindowHideTime = System.currentTimeMillis();
                mRightIv.startAnimation(mResetAnimation);
                if (!isClickItem){
                    editText.setText("");
                }
                isClickItem = false;
            }
        });
    }


    @Override
    public final void onClick(View v) {
        togglePopupWindow();
    }

    private void togglePopupWindow() {
        if (System.currentTimeMillis() - popupWindowHideTime > 200) {
            if (adapter == null || popupWindow == null) {
                return;
            }
            showFilterData("");
        }
    }

    @Override
    public final void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        editText.setText(((IBaseEditSpinnerAdapter) parent.getAdapter()).getItemString(position));
        mRightImageTopView.setClickable(false);
        isClickItem = true;
        popupWindow.dismiss();
        if (mOnItemClickListener != null) {
            mOnItemClickListener.onItemClick(parent, view, position, id);
        }
    }

    @Override
    public final void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public final void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public final void afterTextChanged(Editable s) {
        String key = s.toString();
        editText.setSelection(key.length());


        if (!TextUtils.isEmpty(key)) {
            showFilterData(key);
        } else {
            popupWindow.dismiss();
        }
    }

    private void showFilterData(String key) {
        isClickItem = false;
        if (popupWindow == null || adapter == null || adapter.getEditSpinnerFilter() == null) {
            if (popupWindow != null) {
                popupWindow.dismiss();
            }
            return;
        }
        if (adapter.getEditSpinnerFilter().onFilter(key)) {
            popupWindow.dismiss();
            editText.setText("");
        } else {
            popupWindow.show();
        }

    }
}
