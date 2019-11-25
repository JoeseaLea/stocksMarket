package com.joesea.stocksmarket.schedule;

import com.joesea.stocksmarket.service.StockCodeAndNameDownService;
import com.joesea.stocksmarket.service.impl.StockServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * <p>@author : Joesea Lea</p>
 * <p>@date : 2019/11/19</p>
 * <p>@description : 下载股票代码</p>
 */
@Component
@EnableScheduling
public class StockCodeAndNameDownSchedule {

    @Autowired
    private StockCodeAndNameDownService stockCodeAndNameDownService;
    @Autowired
    private StockServiceImpl stockService;

    @Scheduled(cron = "0 0 8 * * ?")
    public void stockCodeAndNameDown() {
        stockCodeAndNameDownService.downAllStockCodeAndName();
    }

    @Scheduled(cron = "0 0 9 * * ?")
    public void stockCodeAndNameDownFlagUpdate() {
        stockService.resetLastHisDataDownFlag();
    }
}
