package com.stocksmarket.service;

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
     * 下载所有股票数据的csv文件
     */
    void downAllStockHisDataCsv();
}
