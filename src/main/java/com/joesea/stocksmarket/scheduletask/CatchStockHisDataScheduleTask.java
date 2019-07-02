package com.joesea.stocksmarket.scheduletask;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * <p>@author : Joesea Lea</p>
 * <p>@date : 2019/6/28</p>
 * <p>@description : 抓取股票历史数据定时任务。</p>
 */
@Component
@EnableScheduling
public class CatchStockHisDataScheduleTask {
    private static final Logger logger = LoggerFactory.getLogger(CatchStockHisDataScheduleTask.class);

//    @Scheduled(cron = "0/50 * * * * ?")
    @Scheduled(cron = "0 1/1 * * * ?")
    private void catchStocksHisData() {
        logger.info("------------------------------");

       /* try {
            BufferedReader reader = StockHisDataDownUtil.download("http://quotes.money.163.com/service/chddata.html?code=1002152&start=20070813&end=20190621&fields=TCLOSE;HIGH;LOW;TOPEN;LCLOSE;CHG;PCHG;TURNOVER;VOTURNOVER;VATURNOVER;TCAP;MCAP");
            reader.readLine();
            String line = null;
            while((line=reader.readLine())!=null){
                String item[] = line.split(",");//CSV格式文件为逗号分隔符文件，这里根据逗号切分
                StringBuilder data = new StringBuilder();
                for (String i : item) {
                    data.append(i + " ");
                }
                System.out.println(data);
            }
        } catch (UnsupportedEncodingException e) {
            logger.error("csv文件编码格式解析异常", e);
        } catch (IOException e) {
            logger.error("csv文件读取异常", e);
        }*/
    }


}
