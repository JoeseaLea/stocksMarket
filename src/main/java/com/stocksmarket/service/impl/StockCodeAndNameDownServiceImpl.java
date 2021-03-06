package com.stocksmarket.service.impl;

import com.stocksmarket.EnvConfig;
import com.stocksmarket.dao.StockDao;
import com.stocksmarket.dao.StockHisDataDao;
import com.stocksmarket.service.StockCodeAndNameDownService;
import com.stocksmarket.utils.StockCurDataDownUtil;
import com.stocksmarket.vo.StockVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * <p>@author : Joesea Lea</p>
 * <p>@date : 2019/7/2</p>
 * <p>@description : 下载股票代码及名称</p>
 */
@Service
public class StockCodeAndNameDownServiceImpl implements StockCodeAndNameDownService {

    private static final Logger logger = LoggerFactory.getLogger(StockCodeAndNameDownServiceImpl.class);

    @Autowired
    private StockDao stockDao;
    @Autowired
    private StockHisDataDao stockHisDataDao;

    private static Date startTime = null;

    @Override
    public void downAllStockCodeAndName() {

        List<String> stockCodes = new ArrayList<String>();

        /*
         * 深圳A股
         */
        for (int codeNum = 0; codeNum <= 100000; codeNum ++) {
            stockCodes.add(String.format("%06d", codeNum));

            if (100 <= stockCodes.size() || 100000 <= codeNum) {
                getValidStockCodeAndName(stockCodes);

                stockCodes.clear();
            }
        }

        /*
         * 深圳B股及创业板
         */
        for (int codeNum = 200000; codeNum <= 400000; codeNum ++) {
            stockCodes.add(String.format("%06d", codeNum));

            if (100 <= stockCodes.size() || 400000 <= codeNum) {
                getValidStockCodeAndName(stockCodes);

                stockCodes.clear();
            }
        }

        /*
         * 上海A股
         */
        for (int codeNum = 600000; codeNum <= 700000; codeNum ++) {
            stockCodes.add(String.format("%06d", codeNum));

            if (100 <= stockCodes.size() || 700000 <= codeNum) {
                getValidStockCodeAndName(stockCodes);

                stockCodes.clear();
            }
        }

        /*
         * 上海B股
         */
        for (int codeNum = 900000; codeNum < 1000000; codeNum ++) {
            stockCodes.add(String.format("%06d", codeNum));

            if (100 <= stockCodes.size() || 999999 <= codeNum) {
                getValidStockCodeAndName(stockCodes);

                stockCodes.clear();
            }
        }

        logger.info("股票代码及名称下载完成！");

    }

    @Override
    public void getValidStockCodeAndName(List<String> stockCodes) {
        String stockCurDataStr = StockCurDataDownUtil.curDataDown(stockCodes);
        if (StringUtils.isEmpty(stockCurDataStr)) {
            return;
        }
        String[] stockCurDatas = stockCurDataStr.split(";");
        for (String stockCurData : stockCurDatas) {
            String[] stockCurDataArrayTemp = stockCurData.split("=");
            if (!(stockCurDataArrayTemp[1].equals("") || stockCurDataArrayTemp[1].length() <=2)) {
                StockVo stock = new StockVo();

                String stockCode = stockCurDataArrayTemp[0].substring(stockCurDataArrayTemp[0].length()-6, stockCurDataArrayTemp[0].length());
                String[] stockCurDataArray = stockCurDataArrayTemp[1].replaceAll("\"", "").split(",");
                String stockName = stockCurDataArray[0];

                stock.setCode(stockCode);
                stock.setName(stockName);
                stock.setHisDataTableName(getHisDataTableName(stockCode));
                stock.setLastHisDataDownDate(EnvConfig.START_TIME);
                stock.setLastHisDataDownFlag(0);

                stockDao.insertOrUpdateStock(stock);
            }
        }
    }

    private String getHisDataTableName(String stockCode) {
        String hisDataTableName = stockDao.getHisDataTableName(stockCode);
        if (StringUtils.isEmpty(hisDataTableName)) {
            hisDataTableName = stockDao.getLastHisDataTableName();

            int count = stockDao.countHisDataTableName(hisDataTableName);

            if (StringUtils.isEmpty(hisDataTableName) || count >= 100) {
                hisDataTableName = "stock_his_data_" + UUID.randomUUID().toString().replace("-", "");
                stockHisDataDao.dropStockHisDataTable(hisDataTableName);
                stockHisDataDao.createStockHisDataTable(hisDataTableName);
            }
        }

        return hisDataTableName;
    }
}
