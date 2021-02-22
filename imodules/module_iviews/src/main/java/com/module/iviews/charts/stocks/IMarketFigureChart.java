package com.module.iviews.charts.stocks;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.module.ires.bean.utils.EDensityUtils;
import com.module.iviews.charts.stocks.listener.IChartGestureListener;
import com.module.iviews.charts.stocks.listener.IPressChangeListener;
import com.module.iviews.charts.stocks.tool.IStockChartTouchHelper;


/**
 * @ClassName:  IMarketFigureChart
 * @Author: maiduoduo
 * @BLOG: https://blog.csdn.net/Maiduoudo
 * @Date: 2021/2/22
 * @des: 行情图容器
 * #######################（1）动态添加主图和副图 ########################
 * #######################（2）处理主图和副图的手势 ######################
 */
public class IMarketFigureChart extends LinearLayout implements IChartGestureListener {

  private Context mContext;

  private IPressChangeListener mPressChangeListener;

  public IMarketFigureChart(Context context) {
    this(context, null);
  }

  public IMarketFigureChart(Context context,
      @Nullable AttributeSet attrs) {
    this(context, attrs, 0);
  }

  public IMarketFigureChart(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
    this.mContext = context;
    setOrientation(VERTICAL);
    setLayoutParams(
        new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
    // 设置容器的手势监听
    IStockChartTouchHelper chartTouchHelper = new IStockChartTouchHelper(this);
    chartTouchHelper.setChartGestureListener(this);
  }

  /**
   * 向容器中添加主图、附图视图
   */
  public void addChildChart(IStockBaseChartView childView, float height) {
    LayoutParams params =
        new LayoutParams(LayoutParams.MATCH_PARENT,
            EDensityUtils.dp2px(mContext, height));
    addView(childView, params);
  }

  public void setPressChangeListener(IPressChangeListener listener) {
    this.mPressChangeListener = listener;
  }

  @Override
  public void onChartGestureStart(MotionEvent me,
                                  IStockChartTouchHelper.ChartGesture lastPerformedGesture) {

  }

  @Override
  public void onChartGestureEnd(MotionEvent me,
                                IStockChartTouchHelper.ChartGesture lastPerformedGesture) {
    for (int i = 0; i < getChildCount(); i++) {
      IStockBaseChartView baseChartView = (IStockBaseChartView) getChildAt(i);
      if (baseChartView != null) {
        baseChartView.onChartGestureEnd(me, lastPerformedGesture);
      }
    }
  }

  @Override
  public void onChartLongPressed(MotionEvent me) {
    for (int i = 0; i < getChildCount(); i++) {
      IStockBaseChartView baseChartView = (IStockBaseChartView) getChildAt(i);
      if (baseChartView != null) {
        baseChartView.onChartLongPressed(me);
      }
    }
  }

  @Override
  public void onChartDoubleTapped(MotionEvent me) {

  }

  @Override
  public void onChartSingleTapped(MotionEvent me) {
    for (int i = 0; i < getChildCount(); i++) {
      IStockBaseChartView baseChartView = (IStockBaseChartView) getChildAt(i);
      if (baseChartView != null) {
        baseChartView.onChartSingleTapped(me);
      }
    }
  }

  @Override
  public void onChartFling(float distanceX) {
    if (mPressChangeListener != null) {
      mPressChangeListener.onChartFling(distanceX);
    }
  }

  @Override
  public void onChartScale(MotionEvent me, float scaleX, float scaleY) {
    if (mPressChangeListener != null) {
      mPressChangeListener.onChartScale(me, scaleX, scaleY);
    }
  }

  @Override
  public void onChartTranslate(MotionEvent me, float dX) {
    if (mPressChangeListener != null) {
      mPressChangeListener.onChartTranslate(me, dX);
    }
  }
}
