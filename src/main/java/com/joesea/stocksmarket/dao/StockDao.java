package com.joesea.stocksmarket.dao;

import com.joesea.stocksmarket.vo.StockVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * <p>@author : Joesea Lea</p>
 * <p>@date : 2019/7/1</p>
 * <p>@description : </p>
 */
@Repository
public interface StockDao {
    void insertOrUpdateStock(@Param(value = "stock") StockVo stock);
}
