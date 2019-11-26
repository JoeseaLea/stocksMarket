package com.stocksmarket.schedule;

import com.stocksmarket.service.StockHisDataAnalyzeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * <p>@author : Joesea Lea</p>
 * <p>@date : 2019/11/19</p>
 * <p>@description : 历史数据分析</p>
 */
@Component
@EnableScheduling
public class StockHisDataAnalyzeSchedule {
    @Autowired
    private StockHisDataAnalyzeService stockHisDataAnalyzeService;

    @Scheduled(cron = "0/5 * * * * ?")
    public void ma5Gtma10() {
//        stockHisDataAnalyzeService.ma5Gtma10(new StockHisDataVo());
    }
}
