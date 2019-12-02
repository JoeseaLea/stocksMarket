package com.stocksmarket.dao;

import com.stocksmarket.vo.StockVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>@author : Joesea Lea</p>
 * <p>@date : 2019/7/1</p>
 * <p>@description : </p>
 */
@Repository
public interface StockDao {
    /**
     * 插入股票
     * @param stock 股票实体
     */
    void insertOrUpdateStock(@Param(value = "stock") StockVo stock);

    /**
     * 获取所有股票实体
     * @return 所有股票实体
     */
    List<StockVo> getAllStockVo();

    /**
     * 获取所有股票代码
     * @return 所有股票代码
     */
    List<String> getAllStockCode();

    /**
     * 获取所有未下载历史数据的股票实体
     * @return 所有股票实体
     */
    List<StockVo> getAllNotDownHisDataStockVo();

    /**
     * 获取所有未下载历史数据的股票代码
     * @return 所有未下载历史数据的股票代码
     */
    List<String> getAllNotDownHisDataStockCode();

    /**
     * 更新历史数据下载情况
     * @param stock 股票实体
     */
    void updateLastHisDataDownFlag(@Param(value = "stock") StockVo stock);

    /**
     * 重置历史数据下载标识
     * 重置为未下载状态
     */
    void resetLastHisDataDownFlag();

    /**
     * 获取所有已下载历史数据的股票代码
     * @return 所有已下载历史数据的股票代码
     */
    List<StockVo> getAllDownHisDataStockVo();

    /**
     * 获取股票历史数据存储表名称
     * @return 股票历史数据存储表名称
     */
    String getHisDataTableName(String code);

    /**
     * 获取最后记录的股票历史数据保存的表名称
     * @return
     */
    String getLastHisDataTableName();

    /**
     * 获取指定历史数据保存表中记录的股票历史数据数量（保存了多少支股票的历史数据）
     * @param hisDataTableName
     * @return
     */
    int countHisDataTableName(String hisDataTableName);
}
