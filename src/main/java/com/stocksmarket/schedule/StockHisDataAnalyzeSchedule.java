package com.stocksmarket.schedule;

import com.stocksmarket.service.StockHisDataAnalyzeService;
import com.stocksmarket.service.StockService;
import com.stocksmarket.thread.Ma5GtMa10Thread;
import com.stocksmarket.thread.ThreadPoolExecutorManager;
import com.stocksmarket.vo.StockVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Component;

import java.util.List;

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
    @Autowired
    private StockService stockService;

//    @Scheduled(cron = "0/5 * * * * ?")
    public void ma5Gtma10() {
        List<StockVo> stockVos = stockService.getAllDownHisDataStockVo();
        for (StockVo stockVo: stockVos) {
            ThreadPoolExecutorManager.execute(new Ma5GtMa10Thread(stockVo));
        }
//        stockHisDataAnalyzeService.ma5Gtma10(new StockHisDataVo());
    }
}
