package com.stocksmarket.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;


@RunWith(SpringRunner.class)
@SpringBootTest
public class StockServiceTest {
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