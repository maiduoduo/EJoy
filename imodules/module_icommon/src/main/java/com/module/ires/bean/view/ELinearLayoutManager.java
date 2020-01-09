package com.module.ires.bean.view;
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
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;

/**
 * CN:      ELinearLayoutManager
 * Author： JSYL-DINGCL (dingcl@jsyl.com.cn)
 * Date:   2019/12/30
 * Des:    TODO:LinearLayoutManager防止数据错位导致数组越界的错误
 */
public class ELinearLayoutManager extends LinearLayoutManager {

    /**
     * 是否支持滑动
     */
    private boolean mIsScrollEnabled = true;

    public ELinearLayoutManager(Context context) {
        super(context);
    }

    public ELinearLayoutManager(Context context, int orientation, boolean reverseLayout) {
        super(context, orientation, reverseLayout);
    }

    public ELinearLayoutManager(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    public void onLayoutChildren(RecyclerView.Recycler recycler, RecyclerView.State state) {
        try {
            super.onLayoutChildren(recycler, state);
        } catch (IndexOutOfBoundsException e) {
            e.printStackTrace();
        }
    }

    /**
     * 是否支持滑动
     *
     * @param flag
     */
    public ELinearLayoutManager setScrollEnabled(boolean flag) {
        mIsScrollEnabled = flag;
        return this;
    }

    @Override
    public boolean canScrollVertically() {
        return mIsScrollEnabled && super.canScrollVertically();
    }

    @Override
    public boolean canScrollHorizontally() {
        return mIsScrollEnabled && super.canScrollHorizontally();
    }
}

