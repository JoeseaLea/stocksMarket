package com.stocksmarket.schedule;

import com.stocksmarket.service.StockHisDataDownService;
import com.stocksmarket.service.impl.StockServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * <p>@author : Joesea Lea</p>
 * <p>@date : 2019/11/19</p>
 * <p>@description : 下载股票历史数据</p>
 */
@Component
@EnableScheduling
public class StockHisDataDownSchedule {
    @Autowired
    private StockHisDataDownService stockHisDataDownService;
    @Autowired
    private StockServiceImpl stockService;

    @Scheduled(cron = "0 0 17 * * ?")
    public void stockHisDataDown() {
        stockService.resetLastHisDataDownFlag();
        stockHisDataDownService.downAllStockHisData();
    }
}
