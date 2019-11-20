package com.joesea.stocksmarket.schedule;

import com.joesea.stocksmarket.service.StockCodeAndNameDownService;
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

    @Scheduled(cron = "0 30 0 * * ?")
    public void stockCodeAndNameDown() {
        stockCodeAndNameDownService.downAllStockCodeAndName();
    }
}
