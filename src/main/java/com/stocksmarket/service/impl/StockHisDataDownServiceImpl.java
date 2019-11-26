package com.stocksmarket.service.impl;

import com.stocksmarket.EnvConfig;
import com.stocksmarket.dao.StockDao;
import com.stocksmarket.dao.StockHisDataDao;
import com.stocksmarket.service.StockHisDataDownService;
import com.stocksmarket.thread.AnalysisCSVThread;
import com.stocksmarket.thread.ThreadPoolExecutorManager;
import com.stocksmarket.uitl.StockHisDataDownUtil;
import com.stocksmarket.vo.StockVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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


    @Override
    public void downAllStockHisData() {
        List<StockVo> stockVos =  stockDao.getAllStockVo();

        for (StockVo stockVo : stockVos) {
            String localFilePath = EnvConfig.LOCAL_DIR + File.separator + stockVo.getCode() + ".csv";
            boolean downResult = StockHisDataDownUtil.downloadStockHisDataCsv(stockVo, localFilePath);
            /* 下载成功，解析csv文件*/
            if (downResult) {
                ThreadPoolExecutorManager.execute(new AnalysisCSVThread(localFilePath, stockVo.getCode(), stockHisDataDao, stockDao));
            } else {
                downFailLogger.info("股票历史数据csv文件下载失败:" + stockVo.getCode());
            }

        }
    }

    @Override
    public void downAllStockHisDataCsv() {
        List<StockVo> stockVos = stockDao.getAllNotDownHisDataStockVo();
        for (StockVo stockVo : stockVos) {
            boolean downResult = StockHisDataDownUtil.downloadStockHisDataCsv(stockVo, EnvConfig.LOCAL_DIR + File.separator + stockVo.getCode() + ".csv");
            if (!downResult) {
                downFailLogger.info("股票历史数据csv文件下载失败:" + stockVo.getCode());
            }
        }
    }
}