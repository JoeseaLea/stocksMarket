package com.stocksmarket.dao;

import com.stocksmarket.vo.StockHisDataVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>@author : Joesea Lea</p>
 * <p>@date : 2019/7/4</p>
 * <p>@description : </p>
 */
@Repository
public interface StockHisDataDao {
    /**
     * 删除股票历史数据表(指定股票代码为后缀)
     * @param hisDataTableName
     */
    void dropStockHisDataTable(@Param(value = "hisDataTableName") String hisDataTableName);
    /**
     * 创建股票历史数据表(指定股票代码为后缀)
     * @param hisDataTableName
     */
    void createStockHisDataTable(@Param(value = "hisDataTableName") String hisDataTableName);
    /**
     * 插入或更新股票历史数据
     * @param stockHisDataVo
     */
    void insertOrUpdateStockHisData(@Param(value = "stockHisData") StockHisDataVo stockHisDataVo);
    /**
     * 获取股票历史交易数据
     * @param stockHisDataVo
     * @return 股票历史交易数据
     */
    List<StockHisDataVo> getStockHisData(@Param(value = "stockHisData") StockHisDataVo stockHisDataVo);
}
