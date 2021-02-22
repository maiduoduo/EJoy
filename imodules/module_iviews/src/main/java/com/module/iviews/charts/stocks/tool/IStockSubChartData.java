package com.module.iviews.charts.stocks.tool;

import android.graphics.Path;

import com.module.iviews.charts.bean.IStockLineRectItem;

import java.util.ArrayList;
import java.util.List;


/**
 * @ClassName:  IStockSubChartData
 * @Author: maiduoduo
 * @BLOG: https://blog.csdn.net/Maiduoudo
 * @Date: 2020/6/13
 * @des: 副图数据
 */
public class IStockSubChartData {

  /**
   * MACD
   */
  public int MACDLINES = 2;

  /**
   * macd线
   */
  public Path[] macdPaths = new Path[2];

  public List<IStockLineRectItem> macdRects = new ArrayList<>();

  public Path[] bollPaths = new Path[3];

  public IStockSubChartData() {
    for (int i = 0; i < MACDLINES; i++) {
      macdPaths[i] = new Path();
    }
    for (int j = 0; j < 3; j++) {
      bollPaths[j] = new Path();
    }
  }

  public void reset() {
    if (macdPaths != null) {
      for (Path macdPath : macdPaths) {
        if (macdPath != null) {
          macdPath.reset();
        }
      }
    }
    if (bollPaths != null) {
      for (Path bollPath : bollPaths) {
        bollPath.reset();
      }
    }
    if (macdRects != null) {
      macdRects.clear();
    }
  }
}
