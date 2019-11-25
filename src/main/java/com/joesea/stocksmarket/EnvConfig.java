package com.joesea.stocksmarket;

/**
 * <p>@author : Joesea Lea</p>
 * <p>@date : 2019/11/20</p>
 * <p>@description : </p>
 */
public class EnvConfig {
    public static String STOCK_CODE_NAME_DOWN_URI;      //股票代码及名称下载地址
    public static String STOCK_HIS_DATA_DOWN_URI;       //股票历史数据下载地址
    public static String FIELDS;                        //股票历史数据下载字段，多个字段以分号间隔（不配置默认为全部字段: TCLOSE;HIGH;LOW;TOPEN;LCLOSE;CHG;PCHG;TURNOVER;VOTURNOVER;VATURNOVER;TCAP;MCAP）
    public static String START_TIME;                    //股票历史数据下载起始时间（不配置默认为19900101）
    public static String END_TIME;                      //股票历史数据下载结束时间（不配置默认为当前时间）
    public static String LOCAL_DIR;                     //股票历史数据下载csv文件保存路径（不配置默认当前目录下的stockHisData）
    public static int MAX_QUEUE_TASK_SIZE = 5;          //股票历史数据下载解析csv文件最大线程数（不配置默认为5）
}
