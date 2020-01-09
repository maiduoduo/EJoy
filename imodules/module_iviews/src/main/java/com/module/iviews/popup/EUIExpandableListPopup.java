package com.module.iviews.popup;
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

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.webkit.WebView;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.FrameLayout;

import com.module.ires.bean.utils.EDeviceUtils;
import com.module.ires.bean.utils.EDensityUtils;
import com.module.ires.bean.utils.EThemeUtils;
import com.module.iviews.R;

/**
 * CN:      EUIExpandableListPopup
 * Author： JSYL-DINGCL (dingcl@jsyl.com.cn)
 * Date:   2019/12/31
 * Des:    TODO:继承自 {@link EUIPopup}，在 {@link EUIPopup} 的基础上，支持显示一个可伸缩的列表。
 */
public class EUIExpandableListPopup <T extends EUIExpandableListPopup> extends EUIPopup {

    protected ExpandableListView mExpandableListView;
    protected ExpandableListAdapter mAdapter;
    private boolean mHasDivider;

    public EUIExpandableListPopup(Context context) {
        super(context);
    }

    /**
     * 构造方法.
     *
     * @param context   Context
     * @param direction
     */
    public EUIExpandableListPopup(Context context, int direction, ExpandableListAdapter adapter) {
        super(context, direction);
        mAdapter = adapter;
    }

    public EUIExpandableListPopup(Context context, ExpandableListAdapter adapter) {
        super(context);
        mAdapter = adapter;
    }

    /**
     * 创建弹窗
     *
     * @param width                弹窗的宽度
     * @param maxHeight            弹窗最大的高度
     * @param onChildClickListener 子列表点击的监听
     * @return
     */
    public T create(int width, int maxHeight, ExpandableListView.OnChildClickListener onChildClickListener) {
        create(width, maxHeight);
        mExpandableListView.setOnChildClickListener(onChildClickListener);
        return (T) this;
    }

    /**
     * 创建弹窗
     *
     * @param width 弹窗的宽度
     * @return
     */
    public T create(int width) {
        return create(width, 0);
    }

    /**
     * 创建弹窗
     *
     * @param width     弹窗的宽度
     * @param maxHeight 弹窗最大的高度
     * @return
     */
    protected T create(int width, int maxHeight) {
        int margin = EDensityUtils.dp2px(getContext(), 5);
        if (maxHeight != 0) {
            mExpandableListView = new EUIWrapContentExpandableListView(getContext(), maxHeight);
            FrameLayout.LayoutParams lp = new FrameLayout.LayoutParams(width, maxHeight);
            lp.setMargins(0, margin, 0, margin);
            mExpandableListView.setLayoutParams(lp);
        } else {
            mExpandableListView = new EUIWrapContentExpandableListView(getContext());
            FrameLayout.LayoutParams lp = new FrameLayout.LayoutParams(width, FrameLayout.LayoutParams.WRAP_CONTENT);
            lp.setMargins(0, margin, 0, margin);
            mExpandableListView.setLayoutParams(lp);
        }
        mExpandableListView.setPadding(margin, 0, margin, 0);
        mExpandableListView.setAdapter(mAdapter);
        mExpandableListView.setGroupIndicator(null);
        mExpandableListView.setVerticalScrollBarEnabled(false);
        mExpandableListView.setOverScrollMode(WebView.OVER_SCROLL_NEVER);
        updateDivider(mExpandableListView);
        setContentView(mExpandableListView);
        return (T) this;
    }

    /**
     * 设置是否有分割线
     *
     * @return
     */
    public T setHasDivider(boolean hasDivider) {
        mHasDivider = hasDivider;
        if (mExpandableListView != null) {
            updateDivider(mExpandableListView);
        }
        return (T) this;
    }

    private void updateDivider(ExpandableListView listView) {
        if (mHasDivider) {
            listView.setDivider(new ColorDrawable(EThemeUtils.resolveColor(getContext(), R.attr.eui_config_color_separator_light)));
            listView.setChildDivider(new ColorDrawable(EThemeUtils.resolveColor(getContext(), R.attr.eui_config_color_separator_light)));
            listView.setDividerHeight(EDensityUtils.dp2px(getContext(), EDeviceUtils.isTablet(getContext()) ? 1F : 0.5F));
        } else {
            listView.setDivider(null);
            listView.setChildDivider(null);
        }
    }

    /**
     * 设置Group分割线的资源
     *
     * @param divider
     * @return
     */
    public T setGroupDivider(Drawable divider) {
        if (mExpandableListView != null) {
            mExpandableListView.setDivider(divider);
        }
        return (T) this;
    }

    /**
     * 设置Child分割线的资源
     *
     * @param divider
     * @return
     */
    public T setChildDivider(Drawable divider) {
        if (mExpandableListView != null) {
            mExpandableListView.setChildDivider(divider);
        }
        return (T) this;
    }

    /**
     * 设置分割线的高度
     *
     * @param dividerHeight
     * @return
     */
    public T setDividerHeight(int dividerHeight) {
        if (mExpandableListView != null) {
            mExpandableListView.setDividerHeight(dividerHeight);
        }
        return (T) this;
    }

    public T setOnChildClickListener(ExpandableListView.OnChildClickListener onChildClickListener) {
        if (mExpandableListView != null) {
            mExpandableListView.setOnChildClickListener(onChildClickListener);
        }
        return (T) this;
    }

    public T setOnGroupClickListener(ExpandableListView.OnGroupClickListener onGroupClickListener) {
        if (mExpandableListView != null) {
            mExpandableListView.setOnGroupClickListener(onGroupClickListener);
        }
        return (T) this;
    }

    /**
     * 设置是否只展开一个
     *
     * @param enable
     * @return
     */
    public T setEnableOnlyExpandOne(boolean enable) {
        if (mExpandableListView != null) {
            if (enable) {
                mExpandableListView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {
                    @Override
                    public void onGroupExpand(int groupPosition) {
                        expandOnlyOne(groupPosition);
                    }
                });
            } else {
                mExpandableListView.setOnGroupExpandListener(null);
            }
        }
        return (T) this;
    }

    /**
     * 只展开一个
     *
     * @param expandedPosition
     * @return
     */
    public boolean expandOnlyOne(int expandedPosition) {
        if (mExpandableListView == null) {
            return false;
        }
        boolean result = true;
        int groupLength = mExpandableListView.getExpandableListAdapter().getGroupCount();
        for (int i = 0; i < groupLength; i++) {
            if (i != expandedPosition && mExpandableListView.isGroupExpanded(i)) {
                result &= mExpandableListView.collapseGroup(i);
            }
        }
        return result;
    }

    /**
     * 展开所有组
     */
    public void expandAllGroup() {
        if (mExpandableListView != null) {
            int groupLength = mExpandableListView.getExpandableListAdapter().getGroupCount();
            for (int i = 0; i < groupLength; i++) {
                if (!mExpandableListView.isGroupExpanded(i)) {
                    mExpandableListView.expandGroup(i);
                }
            }
        }
    }

    /**
     * 收起所有组
     */
    public void collapseAllGroup() {
        if (mExpandableListView != null) {
            int groupLength = mExpandableListView.getExpandableListAdapter().getGroupCount();
            for (int i = 0; i < groupLength; i++) {
                if (mExpandableListView.isGroupExpanded(i)) {
                    mExpandableListView.collapseGroup(i);
                }
            }
        }
    }

    /**
     * 清空展开状态
     *
     * @return
     */
    public void clearExpandStatus() {
        collapseAllGroup();
    }


    public ExpandableListAdapter getAdapter() {
        return mAdapter;
    }

    public ExpandableListView getExpandableListView() {
        return mExpandableListView;
    }

}

