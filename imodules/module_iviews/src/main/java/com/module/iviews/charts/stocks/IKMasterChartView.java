package com.module.iviews.charts.stocks;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Path;
import android.graphics.PointF;
import android.graphics.Rect;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.MotionEvent;

import com.module.ires.bean.utils.EDensityUtils;
import com.module.ires.bean.utils.EPaintUtils;
import com.module.iviews.R;
import com.module.iviews.charts.bean.IStockExtremeValue;
import com.module.iviews.charts.bean.IStockTechItem;
import com.module.iviews.charts.bean.KLineItem;
import com.module.iviews.charts.bean.KLineToDrawItem;
import com.module.iviews.charts.stocks.tool.IDateUtils;
import com.module.iviews.charts.stocks.tool.INumFormatUtils;
import com.module.iviews.charts.stocks.tool.IStockChartDataSourceHelper;
import com.module.iviews.charts.stocks.tool.IStockChartTouchHelper;
import com.module.iviews.charts.stocks.tool.IStockSubChartData;

import java.util.List;



/**
 * @ClassName:  IKMasterChartView
 * @Author: maiduoduo
 * @BLOG: https://blog.csdn.net/Maiduoudo
 * @Date: 2020/03/07
 * @des: K线的主图
 */
public class IKMasterChartView extends IStockBaseChartView {

  /**
   * 绘制的蜡烛线数据（主副图的数据）
   */
  private List<KLineToDrawItem> mToDrawList;

  /**
   * 蜡烛线价格最大最小值
   */
  private IStockExtremeValue mExtremeValue;

  /**
   * 主图文本间隔
   */
  private int TEXT_PADDING;

  /**
   * 刻度间隔
   */
  private int CAL_PADDING;

  /**
   * 十字线长按选中的点
   */
  private int mFocusIndex;

  /**
   * 长按十字线的位置
   */
  private PointF mFocusPoint;

  /**
   * 是否是长按
   */
  private boolean onLongPress;

  /**
   * 长按弹出的弹框
   */
  private RectF popRect = new RectF();

  private IStockSubChartData mIStockSubChartData;

  public IKMasterChartView(Context context) {
    this(context, null);
  }

  public IKMasterChartView(Context context,
      @Nullable AttributeSet attrs) {
    this(context, attrs, 0);
  }

  public IKMasterChartView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);

    TEXT_PADDING = EDensityUtils.dp2px(context, 5);
    CAL_PADDING = EDensityUtils.dp2px(context, 3);

    mViewPortHandler.setContentRatio(0.9f);
  }

  @Override
  protected void onSizeChanged(int w, int h, int oldw, int oldh) {
    mViewPortHandler.restrainViewPort(EDensityUtils.dp2px(getContext(), 10),
        EDensityUtils.dp2px(getContext(), 20), EDensityUtils.dp2px(getContext(), 10),
        0);
    super.onSizeChanged(w, h, oldw, oldh);
  }

  public IStockViewPortHandler getViewPortHandler() {
    return mViewPortHandler;
  }

  @Override
  protected void drawFrame(Canvas canvas) {
    super.drawFrame(canvas);
    drawOutLine(canvas);
    if (mToDrawList == null || mToDrawList.isEmpty()) {
      return;
    }
    RectF contentRect = mViewPortHandler.mContentRect;
    for (int i = 0; i < mToDrawList.size(); i++) {
      KLineToDrawItem drawItem = mToDrawList.get(i);
      if (drawItem != null) {
        // 绘制蜡烛线日期（只绘制每月第一个交易日）
        if (!TextUtils.isEmpty(drawItem.date)) {
          Rect rect = new Rect();
          EPaintUtils.TEXT_PAINT.getTextBounds(drawItem.date, 0, drawItem.date.length(), rect);
          canvas.drawText(drawItem.date, drawItem.rect.centerX() - rect.width() / 2,
              contentRect.bottom + rect.height() + TEXT_PADDING,
              EPaintUtils.TEXT_PAINT);
          canvas.drawLine(drawItem.rect.centerX(), contentRect.top, drawItem.rect.centerX(),
              contentRect.bottom, EPaintUtils.GRID_INNER_DIVIDER);
        }
        // 绘制蜡烛线
        if (drawItem.isFall) {
          canvas.drawRect(drawItem.rect, mPaintGreen);
          canvas.drawRect(drawItem.shadowRect, mPaintGreen);
        } else {
          canvas.drawRect(drawItem.rect, mPaintRed);
          canvas.drawRect(drawItem.shadowRect, mPaintRed);
        }
      }
    }

    // 绘制BOLL（布林指标）
    if (!onLongPress) {
      KLineToDrawItem lastItem = mToDrawList.get(mToDrawList.size() - 1);
      drawBollDes(canvas, contentRect, lastItem);
    }

    canvas.drawPath(mIStockSubChartData.bollPaths[0], EPaintUtils.LINE_BLUE_PAINT);
    canvas.drawPath(mIStockSubChartData.bollPaths[1], EPaintUtils.LINE_PURPLE_PAINT);
    canvas.drawPath(mIStockSubChartData.bollPaths[2], EPaintUtils.LINE_YELLOW_PAINT);

    // 绘制长按十字线
    if (mFocusPoint != null && onLongPress) {
      if (contentRect.contains(mFocusPoint.x, mFocusPoint.y)) {
        canvas.drawLine(contentRect.left, mFocusPoint.y, contentRect.right, mFocusPoint.y,
            EPaintUtils.FOCUS_LINE_PAINT);
      }
      canvas.drawLine(mFocusPoint.x, contentRect.top, mFocusPoint.x, contentRect.bottom,
          EPaintUtils.FOCUS_LINE_PAINT);
      KLineToDrawItem item = mToDrawList.get(mFocusIndex);
      drawBollDes(canvas, contentRect, item);
    }

    // 长按显示的弹框
    showLongPressDialog(canvas, contentRect);
  }

  /**
   * 绘制主图左上角默认显示和长按BOLL指标展示
   */
  private void drawBollDes(Canvas canvas, RectF contentRect, KLineToDrawItem drawItem) {
    IStockTechItem techItem = drawItem.techItem;
    float mid = INumFormatUtils.formatFloat((techItem.upper + techItem.lower) / 2, 2);
    String midDes = "BOLL  MID:" + mid;
    Rect rectMid = new Rect();
    EPaintUtils.TEXT_YELLOW_PAINT.getTextBounds(midDes, 0, midDes.length(), rectMid);
    canvas.drawText(midDes, contentRect.left, contentRect.top - TEXT_PADDING,
        EPaintUtils.TEXT_YELLOW_PAINT);

    float upper = INumFormatUtils.formatFloat(techItem.upper, 2);
    String upperDes = "UPPER:" + upper;
    Rect rectUpper = new Rect();
    EPaintUtils.TEXT_BLUE_PAINT.getTextBounds(upperDes, 0, upperDes.length(), rectUpper);
    canvas.drawText(upperDes, contentRect.left + rectMid.width() + TEXT_PADDING,
        contentRect.top - TEXT_PADDING, EPaintUtils.TEXT_BLUE_PAINT);

    float lower = INumFormatUtils.formatFloat(techItem.lower, 2);
    String lowerDes = "LOWER:" + lower;
    canvas.drawText(lowerDes,
        contentRect.left + rectMid.width() + rectUpper.width() + TEXT_PADDING * 2,
        contentRect.top - TEXT_PADDING, EPaintUtils.TEXT_PURPLE_PAINT);
  }

  /**
   * 长按显示的弹框内容
   */
  private void showLongPressDialog(Canvas canvas, RectF contentRect) {
    if (onLongPress) {
      KLineToDrawItem item = mToDrawList.get(mFocusIndex);
      float left = contentRect.right - TEXT_PADDING * 2 - EDensityUtils.dp2px(getContext(), 110);
      float top = contentRect.top + TEXT_PADDING;
      popRect.set(left, top,
          left + EDensityUtils.dp2px(getContext(), 110),
          top + EDensityUtils.dp2px(getContext(), 120));
      canvas.drawRect(popRect, EPaintUtils.POP_DIALOG_PAINT);

      KLineItem klineItem = item.klineItem;
      float perHeight = popRect.height() / 7;

      drawPopText(canvas, popRect, getString(R.string.stock_date), IDateUtils.getYMD(klineItem.day),
          item.isFall, perHeight);
      drawPopText(canvas, popRect, getString(R.string.stock_open), String.valueOf(klineItem.open),
          item.isFall,
          perHeight * 2);
      drawPopText(canvas, popRect, getString(R.string.stock_hign), String.valueOf(klineItem.high),
          item.isFall,
          perHeight * 3);
      drawPopText(canvas, popRect, getString(R.string.stock_low), String.valueOf(klineItem.low),
          item.isFall, perHeight * 4);
      drawPopText(canvas, popRect, getString(R.string.stock_close), String.valueOf(klineItem.close),
          item.isFall,
          perHeight * 5);
      drawPopText(canvas, popRect, getString(R.string.stock_diff),
          String.valueOf(INumFormatUtils.formatFloat(klineItem.open - klineItem.preClose, 2)),
          item.isFall,
          perHeight * 6);
      float chg = (klineItem.open - klineItem.preClose) * 100 / klineItem.preClose;
      String chgDesc = INumFormatUtils.formatFloat(chg, 2, true, true, "--", "--", false);
      drawPopText(canvas, popRect, getString(R.string.stock_chg), chgDesc, item.isFall, perHeight * 7);
    }
  }

  public void drawPopText(Canvas canvas, RectF popRect, String title, String value, boolean isFall,
      float y) {
    Rect rect = new Rect();
    EPaintUtils.TEXT_POP_PAINT.getTextBounds(value, 0, value.length(), rect);
    canvas.drawText(title, popRect.left + TEXT_PADDING, popRect.top + y - TEXT_PADDING,
        EPaintUtils.TEXT_POP_PAINT);
    if (title.equals(getString(R.string.stock_diff)) || title.equals(getString(R.string.stock_chg))) {
      if (isFall) {
        canvas.drawText(value, popRect.right - rect.width() - TEXT_PADDING,
            popRect.top + y - TEXT_PADDING,
            EPaintUtils.TEXT_GREEN_PAINT);
      } else {
        canvas.drawText(value, popRect.right - rect.width() - TEXT_PADDING,
            popRect.top + y - TEXT_PADDING,
            EPaintUtils.TEXT_RED_PAINT);
      }
    } else {
      canvas.drawText(value, popRect.right - rect.width() - TEXT_PADDING,
          popRect.top + y - TEXT_PADDING,
          EPaintUtils.TEXT_POP_PAINT);
    }
  }

  /**
   * 绘制行情图边框
   */
  private void drawOutLine(Canvas canvas) {
    if (mExtremeValue == null) {
      return;
    }
    RectF contentRect = mViewPortHandler.mContentRect;
    if (contentRect != null) {

      Path path = new Path();
      path.moveTo(contentRect.left, contentRect.top);
      path.lineTo(contentRect.right, contentRect.top);
      path.lineTo(contentRect.right, contentRect.bottom);
      path.lineTo(contentRect.left, contentRect.bottom);
      path.close();
      canvas.drawPath(path, EPaintUtils.GRID_DIVIDER);
    }

    // 绘制价格刻度和价格分隔线
    float maxPrice = INumFormatUtils.formatFloat(mExtremeValue.maxPrice, 2);
    float minPrice = INumFormatUtils.formatFloat(mExtremeValue.minPrice, 2);
    Rect rect = new Rect();
    EPaintUtils.TEXT_PAINT.getTextBounds(maxPrice + "", 0, String.valueOf(maxPrice).length(), rect);
    canvas.drawText(maxPrice + "", contentRect.left + CAL_PADDING,
        contentRect.top + rect.height() + CAL_PADDING,
            EPaintUtils.TEXT_PAINT);
    float perHeight = contentRect.height() / 4;
    float perPrice = INumFormatUtils.formatFloat((maxPrice - minPrice) / 4, 2);

    for (int i = 1; i <= 3; i++) {
      canvas.drawLine(contentRect.left, contentRect.top + perHeight * i, contentRect.right,
          contentRect.top + perHeight * i, EPaintUtils.GRID_INNER_DIVIDER);
      float value = INumFormatUtils.formatFloat(maxPrice - perPrice * i, 2);
      canvas.drawText(value + "", contentRect.left + CAL_PADDING,
          contentRect.top + perHeight * i - CAL_PADDING, EPaintUtils.TEXT_PAINT);
    }

    canvas.drawText(minPrice + "", contentRect.left + CAL_PADDING, contentRect.bottom - CAL_PADDING,
            EPaintUtils.TEXT_PAINT);
  }

  /**
   * 设置主图数据并触发绘制
   */
  public void initData(List<KLineToDrawItem> klineList, IStockExtremeValue extremeValue,
                       IStockSubChartData subChartData) {
    this.mToDrawList = klineList;
    this.mExtremeValue = extremeValue;
    this.mIStockSubChartData = subChartData;
    invalidateView();
  }

  /**
   * 长按
   */
  @Override
  public void onChartLongPressed(MotionEvent me) {

    onLongPress = true;
    mFocusPoint = new PointF();
    mFocusPoint.set(me.getX(), me.getY());

    RectF contentRect = mViewPortHandler.mContentRect;

    if (contentRect == null || contentRect.width() <= 0) {
      return;
    }
    mFocusIndex = (int) ((mFocusPoint.x - contentRect.left) * IStockChartDataSourceHelper.K_D_COLUMNS
        / contentRect.width());
    mFocusIndex = Math.max(0, Math.min(mFocusIndex, IStockChartDataSourceHelper.K_D_COLUMNS - 1));

    invalidateView();
  }

  @Override
  public void onChartGestureEnd(MotionEvent me,
      IStockChartTouchHelper.ChartGesture lastPerformedGesture) {
    if (lastPerformedGesture == IStockChartTouchHelper.ChartGesture.LONG_PRESS) {
      if (mFocusPoint != null) {
        mFocusPoint.set(me.getX(), me.getY());
      }
      onLongPress = false;
    }
    invalidateView();
  }

  @Override
  public void onChartSingleTapped(MotionEvent me) {

  }
}
