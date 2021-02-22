package com.module.iviews.charts.stocks.tool;

import com.module.iviews.charts.bean.IStockTechItem;
import com.module.iviews.charts.bean.KLineItem;

import java.util.ArrayList;
import java.util.List;


/**
 * @ClassName:  TechParamsHelper
 * @Author: maiduoduo
 * @BLOG: https://blog.csdn.net/Maiduoudo
 * @Date: 2020/06/10
 * @des: 技术指标相关计算
 */
public class IStockTechParamsHelper {

  public class Limit {
    public float max = .0f;
    public float min = .0f;
  }

  public List<IStockTechItem> listParams = new ArrayList<>();

  private IStockTechItem getIStockTechItem(int index) {
    int size = listParams.size();
    IStockTechItem itemT = null;
    if (index < size) {
      itemT = listParams.get(index);
    } else {
      while (index >= size) {
        itemT = new IStockTechItem();
        listParams.add(itemT);
        size++;
      }
    }
    return itemT;
  }

  /**
   * 获取技术指标的极值
   */
  public Limit getLimitValue(IStockTechItem IStockTechItem, IStockTechParamType IStockTechParamType) {
    Limit limit = new Limit();
    if (IStockTechParamType == IStockTechParamType.MACD) {
      limit.max = IStockTechItem.dea;
      limit.min = IStockTechItem.dif;
      if (IStockTechItem.dea < IStockTechItem.dif) {
        limit.max = IStockTechItem.dif;
        limit.min = IStockTechItem.dea;
      }
      if (limit.max < IStockTechItem.macd) {
        limit.max = IStockTechItem.macd;
      }
      if (limit.min > IStockTechItem.macd) {
        limit.min = IStockTechItem.macd;
      }
    } else if (IStockTechParamType == IStockTechParamType.BOLL) {
      limit.max = IStockTechItem.upper;
      limit.min = IStockTechItem.lower;
    }
    return limit;
  }

  /**
   * 计算技术指标
   */
  public void caculateTechParams(List<KLineItem> list, IStockTechParamType IStockTechParamType) {
    // 附图MACD
    if (IStockTechParamType == IStockTechParamType.MACD) {
      linkDataMACD(list);
    } else if (IStockTechParamType == IStockTechParamType.BOLL) {
      linkDataBOLL(list);
    }
  }

  /**
   * BOLL
   */
  private void linkDataBOLL(List<KLineItem> list) {
    int len = list.size();
    KLineItem itemK;
    IStockTechItem itemT;

    final int _EN = 20;
    float sum = .0f, std2 = .0f, stdsum = .0f;
    float tmpArr[] = new float[len];

    for (int i = 0; i < len; i++) {
      itemK = list.get(i);
      itemT = getIStockTechItem(i);

      sum += itemK.close;
      if (i >= _EN - 1) {
        itemT.boll = sum / _EN;
        sum -= list.get(i - _EN + 1).close;
      } else {
        itemT.boll = sum / (i + 1);
      }
      // 差值的平方
      tmpArr[i] = (float) Math.pow((itemK.close - itemT.boll), 2);
      stdsum += tmpArr[i];
      if (i > _EN - 1) {
        stdsum -= tmpArr[i - _EN];
        if (stdsum < .0) {
          stdsum = Math.abs(stdsum);
        }
      }
      std2 = (float) (2 * Math.sqrt(stdsum / Math.min(i + 1, _EN)));
      if (std2 < IStockDataUtils.EPSILON) {
        std2 = itemT.boll * 0.1f;
      }
      itemT.upper = itemT.boll + std2;
      itemT.lower = itemT.boll - std2;
    }
  }

  /**
   * MACD
   */
  public void linkDataMACD(List<KLineItem> list) {
    int len = list.size();
    KLineItem itemK = list.get(0);
    IStockTechItem IStockTechItem = null;
    IStockTechItem preIStockTechItem = getIStockTechItem(0);

    final int _E1 = 12, _E2 = 26, _EDEA = 9;
    float ema1 = itemK.close, ema2 = itemK.close;

    for (int i = 1; i < len; i++) {
      itemK = list.get(i);
      IStockTechItem = getIStockTechItem(i);

      ema1 = _calcEMA(ema1, itemK.close, _E1);
      ema2 = _calcEMA(ema2, itemK.close, _E2);
      IStockTechItem.dif = ema1 - ema2;
      IStockTechItem.dea = _calcEMA(preIStockTechItem.dea, IStockTechItem.dif, _EDEA);
      IStockTechItem.macd = (IStockTechItem.dif - IStockTechItem.dea) * 2;

      preIStockTechItem = IStockTechItem;
    }
  }

  public static float _calcEMA(float ema0_, float close_, int cycle_) {
    int cycleDiv = cycle_ + 1;
    return (cycle_ - 1) * ema0_ / cycleDiv + close_ * 2 / cycleDiv;
  }
}
