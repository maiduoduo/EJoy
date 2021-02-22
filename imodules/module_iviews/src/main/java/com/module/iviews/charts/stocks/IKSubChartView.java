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
import com.module.iviews.charts.bean.IStockExtremeValue;
import com.module.iviews.charts.bean.IStockLineRectItem;
import com.module.iviews.charts.bean.IStockTechItem;
import com.module.iviews.charts.bean.KLineToDrawItem;
import com.module.iviews.charts.stocks.tool.INumFormatUtils;
import com.module.iviews.charts.stocks.tool.IStockChartDataSourceHelper;
import com.module.iviews.charts.stocks.tool.IStockChartTouchHelper;
import com.module.iviews.charts.stocks.tool.IStockSubChartData;
import com.module.iviews.charts.stocks.tool.IStockTechParamType;

import java.util.List;


/**
 * @ClassName:  IKSubChartView
 * @Author: maiduoduo
 * @BLOG: https://blog.csdn.net/Maiduoudo
 * @Date: 2020/04/22
 * @des: K线的副图
 */
public class IKSubChartView extends IStockBaseChartView {

  /**
   * 绘制的蜡烛线数据（主副图的数据）
   */
  private List<KLineToDrawItem> mToDrawList;

  /**
   * 最大值最小值
   */
  private IStockExtremeValue mIStockExtremeValue;

  private IStockSubChartData mIStockSubChartData;

  /**
   * 主图文本间隔
   */
  private int TEXT_PADDING;

  /**
   * 十字线长按选中的点
   */
  private int mFocusIndex;

  /**
   * 长按十字线的位置
   */
  private PointF mFocusPoint;

  /**
   * 手指是否长按
   */
  private boolean onLongPress;

  /**
   * 附图类型
   */
  private IStockTechParamType mIStockTechParamType;

  public IKSubChartView(Context context) {
    this(context, null);
  }

  public IKSubChartView(Context context, @Nullable AttributeSet attrs) {
    this(context, attrs, 0);
  }

  public IKSubChartView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);

    TEXT_PADDING = EDensityUtils.dp2px(context, 5);
  }

  @Override
  protected void onSizeChanged(int w, int h, int oldw, int oldh) {
    mViewPortHandler.restrainViewPort(EDensityUtils.dp2px(getContext(), 10),
        EDensityUtils.dp2px(getContext(), 15), EDensityUtils.dp2px(getContext(), 10),
        0);
    super.onSizeChanged(w, h, oldw, oldh);
  }

  public IStockViewPortHandler getViewPortHandler() {
    return mViewPortHandler;
  }

  @Override
  protected void drawFrame(Canvas canvas) {
    super.drawFrame(canvas);
    if (mToDrawList == null || mToDrawList.isEmpty()) {
      return;
    }

    // 绘制边框和刻度
    drawOutLine(canvas);

    RectF contentRect = mViewPortHandler.mContentRect;

    KLineToDrawItem item = mToDrawList.get(mToDrawList.size() - 1);
    if (mIStockTechParamType == IStockTechParamType.VOLUME) {
      drawVolume(canvas, contentRect);
      if (!onLongPress) {
        drawVolumeDes(canvas, contentRect, item);
      }
    } else if (mIStockTechParamType == IStockTechParamType.MACD) {

      drawMacd(canvas, contentRect);
      if (!onLongPress) {
        drawTechDes(canvas, contentRect, item);
      }
    }

    if (mFocusPoint != null && onLongPress) {

      // 附图实际y轴位置
      float focusY = mFocusPoint.y - getY() - contentRect.top;

      if (contentRect.contains(mFocusPoint.x, focusY)) {
        canvas.drawLine(contentRect.left, focusY, contentRect.right, focusY,
            EPaintUtils.FOCUS_LINE_PAINT);
      }
      canvas.drawLine(mFocusPoint.x, contentRect.top, mFocusPoint.x, contentRect.bottom,
          EPaintUtils.FOCUS_LINE_PAINT);
      KLineToDrawItem drawItem = mToDrawList.get(mFocusIndex);
      if (mIStockTechParamType == IStockTechParamType.MACD) {
        drawTechDes(canvas, contentRect, drawItem);
      } else if (mIStockTechParamType == IStockTechParamType.VOLUME) {
        drawVolumeDes(canvas, contentRect, drawItem);
      }
    }
  }

  /**
   * 绘制成交量左上角指标
   */
  private void drawVolumeDes(Canvas canvas, RectF contentRect, KLineToDrawItem item) {
    long volume = item.klineItem.volume;
    String volumeDes = "成交量:" + INumFormatUtils.formatBigFloatAll(volume, 2);
    canvas.drawText(volumeDes, contentRect.left, contentRect.top - TEXT_PADDING,
        EPaintUtils.TEXT_PAINT);
  }

  /**
   * 绘制MACD左上角指标显示
   */
  public void drawTechDes(Canvas canvas, RectF contentRect, KLineToDrawItem drawItem) {

    IStockTechItem IStockTechItem = drawItem.techItem;
    float dif = INumFormatUtils.formatFloat(IStockTechItem.dif, 2);
    String difDes = "MACD  DIF:" + dif;
    Rect rectMid = new Rect();
    EPaintUtils.TEXT_YELLOW_PAINT.getTextBounds(difDes, 0, difDes.length(), rectMid);
    canvas.drawText(difDes, contentRect.left, contentRect.top - TEXT_PADDING,
        EPaintUtils.TEXT_YELLOW_PAINT);

    float dea = INumFormatUtils.formatFloat(IStockTechItem.dea, 2);
    String deaDes = "DEA:" + dea;
    Rect rectUpper = new Rect();
    EPaintUtils.TEXT_BLUE_PAINT.getTextBounds(deaDes, 0, deaDes.length(), rectUpper);
    canvas.drawText(deaDes, contentRect.left + rectMid.width() + TEXT_PADDING,
        contentRect.top - TEXT_PADDING, EPaintUtils.TEXT_BLUE_PAINT);

    float macd = INumFormatUtils.formatFloat(IStockTechItem.macd, 2);
    String macdDes = "MACD:" + macd;
    canvas.drawText(macdDes,
        contentRect.left + rectMid.width() + rectUpper.width() + TEXT_PADDING * 2,
        contentRect.top - TEXT_PADDING, EPaintUtils.TEXT_PURPLE_PAINT);
  }

  /**
   * 绘制附图MACD
   */
  private void drawMacd(Canvas canvas, RectF contentRect) {
    if (mIStockSubChartData != null) {
      canvas.drawPath(mIStockSubChartData.macdPaths[0], EPaintUtils.LINE_BLUE_PAINT);
      canvas.drawPath(mIStockSubChartData.macdPaths[1], EPaintUtils.LINE_YELLOW_PAINT);
      List<IStockLineRectItem> macdRects = mIStockSubChartData.macdRects;
      if (macdRects != null && !macdRects.isEmpty()) {
        for (int i = 0; i < macdRects.size(); i++) {
          IStockLineRectItem IStockLineRectItem = macdRects.get(i);
          if (IStockLineRectItem != null) {
            boolean isFall = IStockLineRectItem.isFall;
            if (isFall) {
              canvas.drawRect(IStockLineRectItem.rect, mPaintGreen);
            } else {
              canvas.drawRect(IStockLineRectItem.rect, mPaintRed);
            }
          }
        }
      }
    }
  }

  /**
   * 绘制成交量
   */
  private void drawVolume(Canvas canvas, RectF contentRect) {
    for (int i = 0; i < mToDrawList.size(); i++) {
      KLineToDrawItem drawItem = mToDrawList.get(i);
      if (drawItem != null) {
        // 绘制内部分隔线
        if (!TextUtils.isEmpty(drawItem.date)) {
          canvas.drawLine(drawItem.rect.centerX(), contentRect.top, drawItem.rect.centerX(),
              contentRect.bottom, EPaintUtils.GRID_INNER_DIVIDER);
        }
        // 绘制成交量柱
        if (drawItem.isFall) {
          canvas.drawRect(drawItem.volumeRect, mPaintGreen);
        } else {
          canvas.drawRect(drawItem.volumeRect, mPaintRed);
        }
      }
    }
  }

  /**
   * 绘制外围边框和刻度
   */
  private void drawOutLine(Canvas canvas) {
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
    canvas.drawLine(contentRect.left, contentRect.centerY(), contentRect.right,
        contentRect.centerY(), EPaintUtils.GRID_INNER_DIVIDER);
    // 绘制附图最大刻度
    if (mIStockExtremeValue != null && mIStockTechParamType == IStockTechParamType.VOLUME) {
      String maxVolume = INumFormatUtils.formatBigFloatAll(mIStockExtremeValue.maxVolume, 2);
      Rect rect = new Rect();
      EPaintUtils.TEXT_PAINT.getTextBounds(maxVolume, 0, maxVolume.length(), rect);
      canvas.drawText(maxVolume, contentRect.left + TEXT_PADDING,
          contentRect.top + rect.height() + TEXT_PADDING,
          EPaintUtils.TEXT_PAINT);
    }
  }

  /**
   * 设置副图数据，并触发绘制
   */
  public void initData(List<KLineToDrawItem> data, IStockExtremeValue IStockExtremeValue,
      IStockTechParamType IStockTechParamType, IStockSubChartData IStockSubChartData) {
    this.mToDrawList = data;
    this.mIStockExtremeValue = IStockExtremeValue;
    this.mIStockTechParamType = IStockTechParamType;
    this.mIStockSubChartData = IStockSubChartData;
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
    //if (mIStockTechParamType == IStockTechParamType.VOLUME) {
    //  mIStockTechParamType = IStockTechParamType.MACD;
    //} else if (mIStockTechParamType == IStockTechParamType.MACD) {
    //  mIStockTechParamType = IStockTechParamType.VOLUME;
    //}
    //invalidateView();
  }
}
