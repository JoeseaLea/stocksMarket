package com.joesea.stocksmarket.service;

import java.util.List;

/**
 * <p>@author : Joesea Lea</p>
 * <p>@date : 2019/7/2</p>
 * <p>@description : 下载股票代码及名称</p>
 */
public interface StockCodeAndNameDownService {
    /**
     * 下载所有股票代码及名称
     * @return 所有股票代码及名称
     */
    void downAllStockCodeAndName();

    /**
     * 根据传入股票代码筛选有效股票代码及名称
     * @param stockCodes 股票代码
     * @return 有效股票代码及名称
     */
    void getValidStockCodeAndName(List<String> stockCodes);
}
