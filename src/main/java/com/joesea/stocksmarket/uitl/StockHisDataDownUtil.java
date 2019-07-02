package com.joesea.stocksmarket.uitl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * <p>@author : Joesea Lea</p>
 * <p>@date : 2019/6/28</p>
 * <p>@description : </p>
 */
public class StockHisDataDownUtil {

    private static final Logger logger = LoggerFactory.getLogger(StockHisDataDownUtil.class);

    /**
     * 下载指定url数据，并保存至指定路径
     * @param url csv文件下载路径
     * @param localFilePath csv文件保存路径
     */
    public static void downloadCsv(String url, String localFilePath){

        URL urlFile;
        HttpURLConnection httpUrl = null;
        BufferedInputStream bis = null;
        BufferedOutputStream bos = null;
        File localFile = new File(localFilePath);

        try {
            urlFile = new URL(url);
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


    }
}
