package com.module.iviews.charts.stocks.listener;

import android.view.MotionEvent;

public interface IPressChangeListener {

  void onChartTranslate(MotionEvent me, float dX);

  void onChartFling(float distanceX);

  void onChartScale(MotionEvent me, float scaleX, float scaleY);
}
