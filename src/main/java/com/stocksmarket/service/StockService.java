package com.stocksmarket.service;

import com.stocksmarket.vo.StockVo;

import java.util.List;

/**
 * <p>@author : Joesea Lea</p>
 * <p>@date : 2019/7/2</p>
 * <p>@description : </p>
 */
public interface StockService {
    /**
     * 插入或更新股票实体
     * @param stock
     */
    void insertOrUpdateStock(StockVo stock);

    /**
     * 获取所有股票代码
     * @return 股票代码list
     */
    List<String> getAllStockCode();

    /**
     * 重置股票历史数据下载标识
     * 说明：每天重置股票历史数据下载标识后定时任务才能下载数据
     */
    void resetLastHisDataDownFlag();

    /**
     * 获取所有未下载历史数据的股票实体
     * @return 未下载历史数据的股票实体
     */
    List<StockVo> getAllNotDownHisDataStockVo();

    /**
     * 获取所有已下载历史数据的股票实体
     * @return 已下载历史数据的股票实体
     */
    List<StockVo> getAllDownHisDataStockVo();
}
