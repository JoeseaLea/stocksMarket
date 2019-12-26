package com.stocksmarket.utils;

import com.stocksmarket.vo.StockHisDataVo;

import java.util.List;

/**
 * <p>@author : Joesea Lea</p>
 * <p>@date : 2019/12/25</p>
 * <p>@description : 计算各种股票数据</p>
 */
public class StockDataAnalysisUtil {
    /**
     * 获取股票收盘价均值
     * @param stockHisDataList
     * @return 股票收盘价均值
     */
    public static double getAvgtClose(List<StockHisDataVo> stockHisDataList) {
        double sumtClose = 0;
        for (StockHisDataVo stockHisDataVo : stockHisDataList) {
            sumtClose += stockHisDataVo.gettClose();
        }

        return sumtClose/stockHisDataList.size();
    }

    /**
     * 获取股票收盘价最大值
     * @param stockHisDataList
     * @return 股票收盘价最大值
     */
    public static double getMaxtClose(List<StockHisDataVo> stockHisDataList) {
        double maxtClose = 0;

        for (StockHisDataVo vo : stockHisDataList) {
            if (vo.gettClose() > maxtClose) {
                maxtClose = vo.gettClose();
            }
        }

        return maxtClose;
    }

    /**
     * 获取股票收盘价最小值
     * @param stockHisDataList
     * @return 股票收盘价最小值
     */
    public static double getMintClose(List<StockHisDataVo> stockHisDataList) {
        double mintClose = Double.MAX_VALUE;

        for (StockHisDataVo vo : stockHisDataList) {
            if (vo.gettClose() < mintClose) {
                mintClose = vo.gettClose();
            }
        }

        return mintClose;
    }
}
