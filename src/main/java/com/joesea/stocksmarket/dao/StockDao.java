package com.joesea.stocksmarket.dao;

import com.joesea.stocksmarket.vo.StockVo;
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
     * 获取所有股票代码
     * @return
     */
    List<String> getAllStockCode();
}
