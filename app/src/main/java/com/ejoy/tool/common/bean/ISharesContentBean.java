package com.ejoy.tool.common.bean;

import java.util.List;


public class ISharesContentBean {
    /**
     * detail : ["1","2","3","4","5","6","7","8","9","10"]
     * stockName : 大唐电信
     */
    /**
     * 股票名称
     */
    private String stockName;
    private List<String> detail;

    public String getStockName() {
        return stockName;
    }

    public void setStockName(String stockName) {
        this.stockName = stockName;
    }

    public List<String> getDetail() {
        return detail;
    }

    public void setDetail(List<String> detail) {
        this.detail = detail;
    }

}
