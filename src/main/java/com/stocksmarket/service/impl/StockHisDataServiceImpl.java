package com.stocksmarket.service.impl;

import com.stocksmarket.dao.StockHisDataDao;
import com.stocksmarket.service.StockHisDataService;
import com.stocksmarket.vo.StockHisDataVo;
import com.stocksmarket.vo.StockVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>@author : Joesea Lea</p>
 * <p>@date : 2019/12/3</p>
 * <p>@description : </p>
 */
@Service
public class StockHisDataServiceImpl implements StockHisDataService {
    @Autowired
    private StockHisDataDao stockHisDataDao;

    @Override
    public List<StockHisDataVo> getAllStockHisDataAsc(StockVo stockVo, int marketFlag) {
        Map<String, Object> param = new HashMap<>();
        param.put("hisDataTableName", stockVo.getHisDataTableName());
        param.put("stockCode", stockVo.getCode());
        param.put("marketFlag", marketFlag);
        return stockHisDataDao.getAllStockHisDataAsc(param);
    }

    @Override
    public List<StockHisDataVo> getStockHisDataDesc(StockVo stockVo, int marketFlag, int size) {
        return getStockHisDataDesc(stockVo, marketFlag, 0, size);
    }

    @Override
    public List<StockHisDataVo> getStockHisDataDesc(StockVo stockVo, int marketFlag, int startIndex, int size) {
        Map<String, Object> param = new HashMap<>();
        param.put("hisDataTableName", stockVo.getHisDataTableName());
        param.put("stockCode", stockVo.getCode());
        param.put("marketFlag", marketFlag);
        param.put("startIndex", startIndex);
        param.put("size", size);
        return stockHisDataDao.getStockHisDataDesc(param);
    }
}
