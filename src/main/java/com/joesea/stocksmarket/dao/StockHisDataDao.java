package com.joesea.stocksmarket.dao;

import com.joesea.stocksmarket.vo.StockHisDataVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * <p>@author : Joesea Lea</p>
 * <p>@date : 2019/7/4</p>
 * <p>@description : </p>
 */
@Repository
public interface StockHisDataDao {
    /**
     * 插入或更新股票历史数据
     * @param stockHisDataVo
     */
    void insertOrUpdateStockHisData(@Param(value = "stockHisData") StockHisDataVo stockHisDataVo);
}
