package com.stocksmarket.utils;

import com.stocksmarket.vo.StockHisDataVo;

import java.util.List;

/**
 * <p>@author : Joesea Lea</p>
 * <p>@date : 2019/12/25</p>
 * <p>@description : </p>
 */
public class StockDataAnalysisUtil {
    /**
     * 获取股票收盘价均值
     * @param stockHisDataList
     * @return
     */
    public static double getAvgtClose(List<StockHisDataVo> stockHisDataList) {
        double sumtClose = 0;
        for (StockHisDataVo stockHisDataVo : stockHisDataList) {
            sumtClose += stockHisDataVo.gettClose();
        }

        return sumtClose/stockHisDataList.size();
    }
}
