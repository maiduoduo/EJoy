package com.module.iviews.charts.stocks.listener;


import com.module.iviews.charts.bean.IStockExtremeValue;
import com.module.iviews.charts.stocks.tool.IStockSubChartData;

/**
 * @ClassName:  IChartDataCountListener
 * @Author: maiduoduo
 * @BLOG: https://blog.csdn.net/Maiduoudo
 * @Date: 2020/04/11
 * @des: 行情图数据准备完毕回调
 */
public interface IChartDataCountListener<T> {

  void onReady(T data, IStockExtremeValue extremeValue, IStockSubChartData subChartData);
}
