package com.joesea.stocksmarket.service;

import com.joesea.stocksmarket.vo.StockHisDataVo;

/**
 * <p>@author : Joesea Lea</p>
 * <p>@date : 2019/7/5</p>
 * <p>@description : </p>
 */
public interface StockHisDataAnalyzeService {
    /**
     * 5日均线大于10日均线
     */
    void ma5Gtma10(StockHisDataVo stockHisDataVo);
}
