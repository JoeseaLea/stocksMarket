package com.stocksmarket.service.impl;

import com.stocksmarket.dao.StockDao;
import com.stocksmarket.service.StockService;
import com.stocksmarket.vo.StockVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>@author : Joesea Lea</p>
 * <p>@date : 2019/7/2</p>
 * <p>@description : </p>
 */
@Service
public class StockServiceImpl implements StockService {

    @Autowired
    private StockDao stockDao;

    @Override
    public void insertOrUpdateStock(StockVo stock) {
        stockDao.insertOrUpdateStock(stock);
    }

    @Override
    public List<String> getAllStockCode() {
        return stockDao.getAllStockCode();
    }

    @Override
    public void resetLastHisDataDownFlag() {
        stockDao.resetLastHisDataDownFlag();
    }

    @Override
    public List<StockVo> getAllNotDownHisDataStockVo() {
        return stockDao.getAllNotDownHisDataStockVo();
    }

    @Override
    public List<StockVo> getAllDownHisDataStockVo() {
        return stockDao.getAllDownHisDataStockVo();
    }
}
