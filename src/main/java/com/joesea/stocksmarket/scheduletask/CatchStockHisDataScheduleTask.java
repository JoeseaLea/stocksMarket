package com.joesea.stocksmarket.scheduletask;

import com.joesea.stocksmarket.uitl.StockHisDataDownUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.*;

/**
 * <p>@author : Joesea Lea</p>
 * <p>@date : 2019/6/28</p>
 * <p>@description : 抓取股票历史数据定时任务。</p>
 */
@Component
//@EnableScheduling
public class CatchStockHisDataScheduleTask {
    private static final Logger logger = LoggerFactory.getLogger(CatchStockHisDataScheduleTask.class);

    @Scheduled(cron = "0/50 * * * * ?")
    private void catchStocksHisData() {


        /*
         * 六位代码00、200、300开头的都是深圳的股票，00开头的是深圳A股，200开头的是深圳B股，300开头的是创业板(创业板都是在深市交易的)。
         * 六位代码60、900开头的是上海的股票，60开头的都是上海A股，900开头的是上海B股。
         */

        InputStream in = StockHisDataDownUtil.download("http://quotes.money.163.com/service/chddata.html?code=1002152&start=20070813&end=20190621&fields=TCLOSE;HIGH;LOW;TOPEN;LCLOSE;CHG;PCHG;TURNOVER;VOTURNOVER;VATURNOVER;TCAP;MCAP");
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(in, "gbk"));
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
        }
    }


}
