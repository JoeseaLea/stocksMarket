package com.stocksmarket.service;

import com.stocksmarket.vo.StockHisDataVo;
import com.stocksmarket.vo.StockVo;

import java.util.List;

/**
 * <p>@author : Joesea Lea</p>
 * <p>@date : 2019/12/3</p>
 * <p>@description : </p>
 */
public interface StockHisDataService {
    /**
     * 获取指定股票的所有历史数据
     * @param stockVo 股票信息
     * @param marketFlag 股票交易标志(1:正常    0:停牌)
     * @return 指定股票的所有历史数据
     */
    List<StockHisDataVo> getAllStockHisDataAsc(StockVo stockVo, int marketFlag);
    /**
     * 获取指定数量的股票历史数据
     * @param stockVo 股票信息
     * @param marketFlag 股票交易标志(1:正常    0:停牌)
     * @param size 股票历史数据数量
     * @return 指定数量的股票历史数据
     */
    List<StockHisDataVo> getStockHisDataDesc(StockVo stockVo, int marketFlag, int size);

    /**
     * 获取指定起始大小、指定数量的股票历史数据
     * @param stockVo 股票信息
     * @param marketFlag 股票交易标志(1:正常    0:停牌)
     * @param startIndex 股票历史数据起始值
     * @param size 股票历史数据数量
     * @return 指定起始大小的股票历史数据
     */
    List<StockHisDataVo> getStockHisDataDesc(StockVo stockVo, int marketFlag, int startIndex, int size);
}
