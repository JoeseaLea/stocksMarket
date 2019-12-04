package com.stocksmarket.schedule;

import com.stocksmarket.service.StockService;
import com.stocksmarket.thread.MaNGtMaKThread;
import com.stocksmarket.thread.ThreadPoolExecutorManager;
import com.stocksmarket.vo.StockVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
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
    private StockService stockService;

    @Scheduled(cron = "0/5 * * * * ?")
    public void ma5Gtma10() {
        List<StockVo> stockVos = stockService.getAllDownHisDataStockVo();
        for (StockVo stockVo: stockVos) {
            ThreadPoolExecutorManager.execute(new MaNGtMaKThread(stockVo, 1, 2));
        }
    }
}
