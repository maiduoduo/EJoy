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
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;

/**
 * CN:      EGridLayoutManager
 * Author： JSYL-DINGCL (dingcl@jsyl.com.cn)
 * Date:   2019/12/30
 * Des:    TODO:防止数据错位导致数组越界的错误
 */
public class EGridLayoutManager extends GridLayoutManager {

    /**
     * 是否支持滑动
     */
    private boolean mIsScrollEnabled = true;

    public EGridLayoutManager(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    public EGridLayoutManager(Context context, int spanCount) {
        super(context, spanCount);
    }

    public EGridLayoutManager(Context context, int spanCount, int orientation, boolean reverseLayout) {
        super(context, spanCount, orientation, reverseLayout);
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
    public EGridLayoutManager setScrollEnabled(boolean flag) {
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
