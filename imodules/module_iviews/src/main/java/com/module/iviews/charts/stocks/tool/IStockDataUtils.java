package com.module.iviews.charts.stocks.tool;

/**
 * @ClassName:  IStockDataUtils
 * @Author: maiduoduo
 * @BLOG: https://blog.csdn.net/Maiduoudo
 * @Date: 2021/2/22
 * @des: 常量
 */
public class IStockDataUtils {

  /**
   * +0
   */
  public static float EPSILON = 0.0000001f;

  /**
   * -0
   */
  public static float EPSILONNGT = -0.0000001f;

  /**
   * 浮点零值判定
   */
  public static boolean isZero(float val) {
    return (val > EPSILONNGT && val < EPSILON);
  }
}
