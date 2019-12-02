package com.stocksmarket.thread;

import com.stocksmarket.service.StockHisDataAnalyzeService;
import com.stocksmarket.utils.SpringContextUtil;
import com.stocksmarket.vo.StockVo;

/**
 * <p>@author : Joesea Lea</p>
 * <p>@date : 2019/11/28</p>
 * <p>@description : </p>
 */
public class Ma5GtMa10Thread implements Runnable {

    private StockVo stockVo;

    public Ma5GtMa10Thread(StockVo stockVo) {
        this.stockVo = stockVo;
    }

    @Override
    public void run() {
        StockHisDataAnalyzeService stockHisDataAnalyzeService = SpringContextUtil.getBean(StockHisDataAnalyzeService.class);
        stockHisDataAnalyzeService.ma5Gtma10(this.stockVo.getCode());
    }
}
