package com.joesea.stocksmarket.schedule;

import com.joesea.stocksmarket.service.StockHisDataDownService;
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

    @Scheduled(cron = "0 30 15 * * ?")
    public void stockHisDataDown() {
        stockHisDataDownService.downAllStockHisData();
    }
}
