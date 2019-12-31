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

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.RecyclerView;
import android.view.Window;
import android.view.WindowManager;

import com.module.ires.bean.view.EDividerItemDecoration;
import com.module.ires.bean.view.EGridDividerItemDecoration;
import com.module.ires.bean.view.EGridLayoutManager;
import com.module.ires.bean.view.ELinearLayoutManager;

/**
 * CN:      WidgetUtils
 * Author： JSYL-DINGCL (dingcl@jsyl.com.cn)
 * Date:   2019/12/30
 * Des:    TODO:
 */
public final class WidgetUtils {
    public static final int HORIZONTAL = RecyclerView.HORIZONTAL;

    public static final int VERTICAL = RecyclerView.VERTICAL;

    private WidgetUtils() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }

    /**
     * 让Activity全屏显示
     *
     * @param activity
     */
    public static void requestFullScreen(@NonNull Activity activity) {
        activity.requestWindowFeature(Window.FEATURE_NO_TITLE);
        activity.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }

    //===============Spinner=============//



    //===============recyclerView=============//

    /**
     * 初始化Grid网格RecyclerView
     *
     * @param recyclerView
     * @param spanCount    一行的数量
     */
    public static void initGridRecyclerView(@NonNull RecyclerView recyclerView, int spanCount) {
        recyclerView.setLayoutManager(new EGridLayoutManager(recyclerView.getContext(), spanCount));
        recyclerView.addItemDecoration(new EGridDividerItemDecoration(recyclerView.getContext(), spanCount));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
    }

    /**
     * 初始化Grid网格RecyclerView
     *
     * @param recyclerView
     * @param spanCount    一行的数量
     * @param dividerWidth 分割线的宽度
     */
    public static void initGridRecyclerView(@NonNull RecyclerView recyclerView, int spanCount, int dividerWidth) {
        recyclerView.setLayoutManager(new EGridLayoutManager(recyclerView.getContext(), spanCount));
        recyclerView.addItemDecoration(new EGridDividerItemDecoration(recyclerView.getContext(), spanCount, dividerWidth));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
    }

    /**
     * 初始化Grid网格RecyclerView
     *
     * @param recyclerView
     * @param spanCount    一行的数量
     * @param dividerWidth 分割线宽度
     * @param dividerColor 分割线的颜色
     */
    public static void initGridRecyclerView(@NonNull RecyclerView recyclerView, int spanCount, int dividerWidth, int dividerColor) {
        recyclerView.setLayoutManager(new EGridLayoutManager(recyclerView.getContext(), spanCount));
        recyclerView.addItemDecoration(new EGridDividerItemDecoration(recyclerView.getContext(), spanCount, dividerWidth, dividerColor));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
    }

    /**
     * 初始化Grid网格RecyclerView
     *
     * @param recyclerView
     * @param canScroll    是否支持滑动
     * @param spanCount    一行的数量
     * @param dividerWidth 分割线宽度
     * @param dividerColor 分割线的颜色
     */
    public static void initGridRecyclerView(@NonNull RecyclerView recyclerView, boolean canScroll, int spanCount, int dividerWidth, int dividerColor) {
        recyclerView.setLayoutManager(new EGridLayoutManager(recyclerView.getContext(), spanCount).setScrollEnabled(canScroll));
        recyclerView.addItemDecoration(new EGridDividerItemDecoration(recyclerView.getContext(), spanCount, dividerWidth, dividerColor));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
    }

    /**
     * 初始化RecyclerView
     *
     * @param recyclerView
     */
    public static void initRecyclerView(@NonNull RecyclerView recyclerView) {
        recyclerView.setLayoutManager(new ELinearLayoutManager(recyclerView.getContext()));
        recyclerView.addItemDecoration(new DividerItemDecoration(recyclerView.getContext(), VERTICAL));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
    }

    /**
     * 初始化RecyclerView
     *
     * @param recyclerView
     * @param dividerHeight 分割线的高度
     */
    public static void initRecyclerView(@NonNull RecyclerView recyclerView, int dividerHeight) {
        recyclerView.setLayoutManager(new ELinearLayoutManager(recyclerView.getContext()));
        recyclerView.addItemDecoration(new EDividerItemDecoration(recyclerView.getContext(), VERTICAL, dividerHeight));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
    }


    /**
     * 初始化RecyclerView
     *
     * @param recyclerView
     * @param dividerHeight 分割线的高度
     * @param dividerColor  分割线的颜色
     */
    public static void initRecyclerView(@NonNull RecyclerView recyclerView, int dividerHeight, int dividerColor) {
        recyclerView.setLayoutManager(new ELinearLayoutManager(recyclerView.getContext()));
        recyclerView.addItemDecoration(new EDividerItemDecoration(recyclerView.getContext(), VERTICAL, dividerHeight, dividerColor));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
    }

    /**
     * 初始化RecyclerView
     *
     * @param recyclerView
     * @param canScroll     是否支持滑动
     * @param dividerHeight 分割线的高度
     * @param dividerColor  分割线的颜色
     */
    public static void initRecyclerView(@NonNull RecyclerView recyclerView, boolean canScroll, int dividerHeight, int dividerColor) {
        recyclerView.setLayoutManager(new ELinearLayoutManager(recyclerView.getContext()).setScrollEnabled(canScroll));
        recyclerView.addItemDecoration(new EDividerItemDecoration(recyclerView.getContext(), VERTICAL, dividerHeight, dividerColor));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
    }

    //===============Loading=============//


}

