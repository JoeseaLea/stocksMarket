package com.joesea.stocksmarket.service.impl;

import com.joesea.stocksmarket.dao.StockDao;
import com.joesea.stocksmarket.dao.StockHisDataDao;
import com.joesea.stocksmarket.service.StockHisDataDownService;
import com.joesea.stocksmarket.thread.AnalysisCSVThread;
import com.joesea.stocksmarket.thread.ThreadPoolExecutorManager;
import com.joesea.stocksmarket.uitl.StockHisDataDownUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.List;

/**
 * <p>@author : Joesea Lea</p>
 * <p>@date : 2019/7/2</p>
 * <p>@description : </p>
 */
@Service
public class StockHisDataDownServiceImpl implements StockHisDataDownService {

    @Autowired
    private StockDao stockDao;
    @Autowired
    private StockHisDataDao stockHisDataDao;

    private static final Logger logger = LoggerFactory.getLogger(StockHisDataDownServiceImpl.class);
    private static final Logger downFailLogger = LoggerFactory.getLogger("stockHisDataDownLoadFail");

    @Value(value = "${stock.hisdata.down.localDir:/Users/joesealea/IdeaProjects/stocksMarket/stockHisDataCsv}")
    private String localDir;

    @Override
    public void downAllStockHisData() {
        List<String> stockCodes = stockDao.getAllStockCode();
        for (String stockCode : stockCodes) {
            String localFilePath = localDir + File.separator + stockCode + ".csv";
            boolean downResult = StockHisDataDownUtil.downloadStockHisDataCsv(stockCode, localFilePath);
            /* 下载成功，解析csv文件 ThreadPoolExecutor*/
            if (downResult) {
                ThreadPoolExecutorManager.execute(new AnalysisCSVThread(localFilePath, stockCode, stockHisDataDao, stockDao));
            } else {
                downFailLogger.info("股票历史数据csv文件下载失败:" + stockCode);
            }

        }
    }

    @Override
    public void downAllStockHisDataCsv() {
        List<String> stockCodes = stockDao.getAllNotDownHisDataStockCode();
        for (String stockCode : stockCodes) {
            boolean downResult = StockHisDataDownUtil.downloadStockHisDataCsv(stockCode, localDir + File.separator + stockCode + ".csv");
            if (!downResult) {
                downFailLogger.info("股票历史数据csv文件下载失败:" + stockCode);
            }
        }
    }
}
