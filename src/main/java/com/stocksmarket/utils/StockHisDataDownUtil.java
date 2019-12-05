package com.stocksmarket.utils;

import com.stocksmarket.EnvConfig;
import com.stocksmarket.vo.StockVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.regex.Pattern;

/**
 * <p>@author : Joesea Lea</p>
 * <p>@date : 2019/6/28</p>
 * <p>@description : </p>
 */
@Component
public class StockHisDataDownUtil {

    private static final Logger logger = LoggerFactory.getLogger(StockHisDataDownUtil.class);
    private static final Logger downFailLogger = LoggerFactory.getLogger("stockHisDataDownLoadFail");


    public static boolean downloadStockHisDataCsv(StockVo stockVo, String localFilePath){
        String startTime = DateFormatUtil.format(stockVo.getLastHisDataDownDate(), "yyyyMMdd");

        for (int i = 0; i < 10; i ++) {

            if (Pattern.matches("^0.*", stockVo.getCode()) && 0 == i) {
                continue;
            }


            boolean downResult = false;
            StringBuilder url = new StringBuilder();

            url.append(EnvConfig.STOCK_HIS_DATA_DOWN_URI)
                    .append("?code=").append(i).append(stockVo.getCode())
                    .append("&start=").append(startTime);
            url.append("&fields=").append(EnvConfig.FIELDS);

            try {
                URL urlFile = new URL(url.toString());
                downResult = downloadCsv(urlFile, localFilePath);
            } catch (MalformedURLException e) {
                logger.error("url解析错误", e);
            }

            if (true == downResult) {
                File file = new File(localFilePath);
                if(108 < file.length()) {
                    logger.info(url.toString());
                    return true;
                } else {
                    file.delete();
                    downFailLogger.error("文件下载失败: " + url.toString());
                    continue;
                }
            }
        }

        return false;
    }

    /**
     * 下载指定url数据，并保存至指定路径
     * @param urlFile csv文件下载URL
     * @param localFilePath csv文件保存路径
     */
    public static boolean downloadCsv(URL urlFile, String localFilePath){
        HttpURLConnection httpUrl = null;
        BufferedInputStream bis = null;
        BufferedOutputStream bos = null;
        File localFile = new File(localFilePath);

        if (!localFile.getParentFile().exists()) {
            localFile.getParentFile().mkdirs();
        }

        try {
            httpUrl = (HttpURLConnection)urlFile.openConnection();
            httpUrl.connect();

            bis = new BufferedInputStream(httpUrl.getInputStream());
            bos = new BufferedOutputStream(new FileOutputStream(localFile));

            int len = 2048;
            byte[] b = new byte[len];
            while ((len = bis.read(b)) != -1) {
                bos.write(b, 0, len);
            }

            bos.flush();
            logger.info(localFilePath + "下载完成");
            return true;
        } catch (MalformedURLException e) {
            logger.error("url格式不正确", e);
        } catch (IOException e) {
            logger.error("文件下载异常", e);
        } finally {
            try {
                if(null !=  bos) {
                    bos.close();
                }
                if (null != bis) {
                    bis.close();
                }
                if (null != httpUrl) {
                    httpUrl.disconnect();
                }
            } catch (IOException e) {
                logger.error("", e);
            }

        }

        return false;
    }

}
