package com.module.iviews.charts.stocks.tool;

/**
 * @ClassName:  IDateUtils
 * @Author: maiduoduo
 * @BLOG: https://blog.csdn.net/Maiduoudo
 * @Date: 2020/04/22
 * @des: 日期相关处理
 */
public class IDateUtils {

  public static int getMonth(String date) {
    if (date != null && date.length() >= 10) {
      // "yyyy-MM-dd"
      StringBuffer sb = new StringBuffer(date);
      int month = Short.parseShort(sb.substring(5, 7));
      return month;
    }
    return -1;
  }

  public static String getYMD(String date) {
    if (date != null && date.length() >= 10) {
      return date.substring(0, 10);
    }
    return date;
  }
}
