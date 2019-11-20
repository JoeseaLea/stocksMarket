package com.joesea.stocksmarket.schedule;

import com.joesea.stocksmarket.service.StockCodeAndNameDownService;
import com.joesea.stocksmarket.uitl.StockCurDataDownUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * <p>@author : Joesea Lea</p>
 * <p>@date : 2019/11/19</p>
 * <p>@description : 下载股票代码</p>
 */
@Component
public class StockCodeAndNameDownSchedule {

    @Autowired
    private StockCodeAndNameDownService stockCodeAndNameDownService;

    @Value("${stock.curdata.down.host}")
    private String host;

    @PostConstruct
    public void stockCodeAndNameDown() {

        StockCurDataDownUtil.setHost(host);

        stockCodeAndNameDownService.downAllStockCodeAndName();
    }
}
