package com.stocksmarket.service;

import com.stocksmarket.vo.StockVo;

import java.util.List;

/**
 * <p>@author : Joesea Lea</p>
 * <p>@date : 2019/7/2</p>
 * <p>@description : 股票历史数据下载</p>
 */
public interface StockHisDataDownService {

    /**
     * 下载所有股票数据
     */
    void downAllStockHisData();

    /**
     * 下载指定股票数据
     */
    void downAllStockHisData(List<StockVo> stockVos);

    /**
     * 下载所有股票数据的csv文件
     */
    void downAllStockHisDataCsv();
    /**
     * 下载指定股票数据的csv文件
     */
    void downAllStockHisDataCsv(List<StockVo> stockVos);
}
