package com.stocksmarket.service;

import com.stocksmarket.vo.StockVo;

import java.util.List;

/**
 * <p>@author : Joesea Lea</p>
 * <p>@date : 2019/7/2</p>
 * <p>@description : </p>
 */
public interface StockService {
    void insertOrUpdateStock(StockVo stock);
    List<String> getAllStockCode();

    void resetLastHisDataDownFlag();
}
