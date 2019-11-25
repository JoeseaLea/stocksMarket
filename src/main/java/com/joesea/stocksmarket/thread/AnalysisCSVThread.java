package com.joesea.stocksmarket.thread;

import com.joesea.stocksmarket.dao.StockDao;
import com.joesea.stocksmarket.dao.StockHisDataDao;
import com.joesea.stocksmarket.vo.StockHisDataVo;
import com.joesea.stocksmarket.vo.StockVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

/**
 * <p>@author : Joesea Lea</p>
 * <p>@date : 2019/11/20</p>
 * <p>@description : </p>
 */
public class AnalysisCSVThread implements Runnable {

    private static final Logger logger = LoggerFactory.getLogger(AnalysisCSVThread.class);

    private String localFilePath;
    private String stockCode;
    private StockHisDataDao stockHisDataDao;
    private StockDao stockDao;

    public AnalysisCSVThread(String localFilePath, String stockCode, StockHisDataDao stockHisDataDao, StockDao stockDao) {
        this.localFilePath = localFilePath;
        this.stockCode = stockCode;
        this.stockHisDataDao = stockHisDataDao;
        this.stockDao = stockDao;
    }

    @Override
    public void run() {
        Date lastHisDataDownDate = Calendar.getInstance().getTime();
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
            stockVo.setLastHisDataDownFlag(1);
            stockVo.setLastHisDataDownDate(lastHisDataDownDate);
            stockDao.updateLastHisDataDownFlag(stockVo);

            file.delete();
        } catch (FileNotFoundException e) {
            logger.error(localFilePath + "文件不存在", e);
        } catch (IOException e) {
            logger.error("文件解析错误", e);
        } catch (Exception e) {
            logger.error("数据异常", e);
        }
    }
}
