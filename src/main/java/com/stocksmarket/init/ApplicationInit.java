package com.stocksmarket.init;

import com.stocksmarket.EnvConfig;
import com.stocksmarket.service.StockCodeAndNameDownService;
import com.stocksmarket.service.StockHisDataDownService;
import com.stocksmarket.thread.ThreadPoolExecutorManager;
import com.stocksmarket.utils.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.Calendar;

/**
 * <p>@author : Joesea Lea</p>
 * <p>@date : 2019/11/20</p>
 * <p>@description : </p>
 */
@Component
public class ApplicationInit {

    @Autowired
    private StockCodeAndNameDownService stockCodeAndNameDownService;
    @Autowired
    private StockHisDataDownService stockHisDataDownService;


    public void init() {
        initParam();
        ThreadPoolExecutorManager.createExecutorService();

        new Thread(() -> {
            if (EnvConfig.START_DOWN_STOCK_CODE_AND_NAME) {
                stockCodeAndNameDownService.downAllStockCodeAndName();
            }
            if (EnvConfig.START_DOWN_STOCK_HISDATA) {
                stockHisDataDownService.downAllStockHisData();
            }
        }).start();

    }

    private void initParam() {

        EnvConfig.STOCK_CODE_NAME_DOWN_URI =
                StringUtils.isEmpty(this.STOCK_CODE_NAME_DOWN_URI) ? "http://hq.sinajs.cn/list=" : this.STOCK_CODE_NAME_DOWN_URI;
        EnvConfig.STOCK_HIS_DATA_DOWN_URI =
                StringUtils.isEmpty(this.STOCK_HIS_DATA_DOWN_URI) ? "http://quotes.money.163.com/service/chddata.html" : this.STOCK_HIS_DATA_DOWN_URI;
        EnvConfig.FIELDS =
                StringUtils.isEmpty(this.FIELDS) ? "TCLOSE;HIGH;LOW;TOPEN;LCLOSE;CHG;PCHG;TURNOVER;VOTURNOVER;VATURNOVER;TCAP;MCAP" : this.FIELDS;
        EnvConfig.START_TIME =
                DateUtil.parse(StringUtils.isEmpty(this.START_TIME) ? "19900101" : this.START_TIME, "yyyyMMdd");
        EnvConfig.END_TIME =
                DateUtil.parse(StringUtils.isEmpty(this.END_TIME) ? DateUtil.format(Calendar.getInstance().getTime(), "yyyyMMdd") : this.END_TIME, "yyyyMMdd");
        EnvConfig.LOCAL_DIR =
                StringUtils.isEmpty(this.LOCAL_DIR) ? "stockHisDataCsv" : this.LOCAL_DIR;
        EnvConfig.MAX_QUEUE_TASK_SIZE =
                StringUtils.isEmpty(this.MAX_QUEUE_TASK_SIZE) ? 5 : Integer.parseInt(this.MAX_QUEUE_TASK_SIZE);
        EnvConfig.START_DOWN_STOCK_CODE_AND_NAME =
                StringUtils.isEmpty(this.START_DOWN_STOCK_CODE_AND_NAME) ? false : Boolean.parseBoolean(this.START_DOWN_STOCK_CODE_AND_NAME);
        EnvConfig.START_DOWN_STOCK_HISDATA =
                StringUtils.isEmpty(this.START_DOWN_STOCK_HISDATA) ? false : Boolean.parseBoolean(this.START_DOWN_STOCK_HISDATA);
    }

    @Value("${stock.curdata.down.host:http://hq.sinajs.cn/list=}")
    private String STOCK_CODE_NAME_DOWN_URI;      //股票代码及名称下载地址
    @Value("${stock.hisdata.down.host:http://quotes.money.163.com/service/chddata.html}")
    private String STOCK_HIS_DATA_DOWN_URI;       //股票历史数据下载地址
    @Value(value = "${stock.hisdata.down.fields:TCLOSE;HIGH;LOW;TOPEN;LCLOSE;CHG;PCHG;TURNOVER;VOTURNOVER;VATURNOVER;TCAP;MCAP}")
    private String FIELDS;                        //股票历史数据下载字段，多个字段以分号间隔（不配置默认为全部字段: TCLOSE;HIGH;LOW;TOPEN;LCLOSE;CHG;PCHG;TURNOVER;VOTURNOVER;VATURNOVER;TCAP;MCAP）
    @Value("${stock.hisdata.down.startTime:19900101}")
    private String START_TIME;                    //股票历史数据下载起始时间（不配置默认为19900101）
    @Value("${stock.hisdata.down.endTime:}")
    private String END_TIME;                      //股票历史数据下载结束时间（不配置默认为当前时间）
    @Value(value = "${stock.hisdata.down.localDir:stockHisDataCsv}")
    private String LOCAL_DIR;                     //股票历史数据下载csv文件保存路径（不配置默认当前目录下的stockHisData）
    @Value(value = "${stock.hisdata.down.maxQueueTaskSize:5}")
    private String MAX_QUEUE_TASK_SIZE;           //股票历史数据下载解析csv文件最大线程数（不配置默认为5）
    @Value(value = "${start.down.stockCodeAndName:off}")
    private String START_DOWN_STOCK_CODE_AND_NAME;//项目启动时是否开始下载股票代码及名称（是：true 否：false）
    @Value(value = "${start.down.stockHisData:off}")
    private String START_DOWN_STOCK_HISDATA;      //项目启动时是否开始下载股票历史数据（是：true 否：false）
}

