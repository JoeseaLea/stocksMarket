package com.joesea.stocksmarket.service.impl;

import com.joesea.stocksmarket.dao.StockDao;
import com.joesea.stocksmarket.dao.StockHisDataDao;
import com.joesea.stocksmarket.service.StockHisDataDownService;
import com.joesea.stocksmarket.uitl.StockHisDataDownUtil;
import com.joesea.stocksmarket.vo.StockHisDataVo;
import com.joesea.stocksmarket.vo.StockVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.List;
import java.util.UUID;

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
        List<String> stockCodes = stockDao.getAllNotDownHisDataStockCode();
        for (String stockCode : stockCodes) {
            String localFilePath = localDir + File.separator + stockCode + ".csv";
            boolean downResult = StockHisDataDownUtil.downloadStockHisDataCsv(stockCode, localFilePath);
            if (downResult) {
                File file = new File(localFilePath);
                try {
                    BufferedReader reader = new BufferedReader(new FileReader(file));
                    reader.readLine();
                    String readLine;

                    stockHisDataDao.dropStockHisData(stockCode);
                    stockHisDataDao.createStockHisData(stockCode);
                    while ((readLine = reader.readLine()) != null) {
                        String[] stockHisData = readLine.split(",");
                        StockHisDataVo stockHisDataVo = new StockHisDataVo();
                        stockHisDataVo.setId(UUID.randomUUID().toString().replaceAll("-", ""));

                        stockHisDataVo.setTransDate(stockHisData[0]);
                        stockHisDataVo.setStockCode(stockHisData[1].replaceAll("'", ""));
                        stockHisDataVo.settClose(Double.valueOf(stockHisData[3]));
                        stockHisDataVo.settHigh(Double.valueOf(stockHisData[4]));
                        stockHisDataVo.settLow(Double.valueOf(stockHisData[5]));
                        stockHisDataVo.settOpen(Double.valueOf(stockHisData[6]));
                        stockHisDataVo.setlClose(Double.valueOf(stockHisData[7]));//前收盘
                        stockHisDataVo.setChg(Double.valueOf(stockHisData[8].equals("None") ? "0" : stockHisData[8]));
                        stockHisDataVo.setPchg(Double.valueOf(stockHisData[9].equals("None") ? "0" : stockHisData[9]));
                        stockHisDataVo.setTurnOver(Double.valueOf(stockHisData[10].equals("None") ? "0" : stockHisData[10]));
                        stockHisDataVo.setVoTurnOver(stockHisData[11]);
                        stockHisDataVo.setVaTurnOver(stockHisData[12]);
                        stockHisDataVo.setTcap(stockHisData[13]);
                        stockHisDataVo.setMcap(stockHisData[14]);

                        if (stockHisData[8].equals("None") || stockHisData[9].equals("None") || stockHisData[10].equals("None") || Double.valueOf(stockHisData[4])==Double.valueOf(stockHisData[5])){
                            stockHisDataVo.setMarketFlag(0);
                        } else {
                            stockHisDataVo.setMarketFlag(1);
                        }

                        stockHisDataDao.insertOrUpdateStockHisData(stockHisDataVo);
                    }

                    StockVo stockVo = new StockVo();
                    stockVo.setCode(stockCode);
                    stockVo.setHisDataDownFlag(1);
                    stockDao.updateHisDataDownFlag(stockVo);

                    file.delete();
                } catch (FileNotFoundException e) {
                    logger.error(localFilePath + "文件不存在", e);
                } catch (IOException e) {
                    logger.error("文件解析错误", e);
                } catch (Exception e) {
                    logger.error("数据异常", e);
                }
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
