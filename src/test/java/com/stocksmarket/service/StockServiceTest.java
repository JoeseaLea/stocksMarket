package com.stocksmarket.service;

import com.stocksmarket.base.BaseTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class StockServiceTest extends BaseTest {
    @Autowired
    private StockService stockService;

    @Test
    public void insertOrUpdateStock() {
    }

    @Test
    public void getAllStockCode() {
        List<String> list = stockService.getAllStockCode();
        System.out.println(list.size());
    }

    @Test
    public void resetLastHisDataDownFlag() {
    }

    @Test
    public void getAllNotDownHisDataStockVo() {
    }

    @Test
    public void getAllDownHisDataStockVo() {
    }
}