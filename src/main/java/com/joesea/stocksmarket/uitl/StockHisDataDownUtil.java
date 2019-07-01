package com.joesea.stocksmarket.uitl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * <p>@author : Joesea Lea</p>
 * <p>@date : 2019/6/28</p>
 * <p>@description : </p>
 */
public class StockHisDataDownUtil {

    private static final Logger logger = LoggerFactory.getLogger(StockHisDataDownUtil.class);

    /**
     * 下载指定url数据
     * @param url
     * @return 指定url的下载数据
     */
    public static InputStream download(String url){

        URL urlFile;
        HttpURLConnection httpUrl = null;

        try {
            urlFile = new URL(url);
            httpUrl = (HttpURLConnection)urlFile.openConnection();
            httpUrl.connect();
            InputStream in = httpUrl.getInputStream();
            return in;
        } catch (Exception e) {
            logger.error("", e);
        } finally {
            if (null != httpUrl) {
//                httpUrl.disconnect();
            }
        }
        return null;
    }

    /**
     * 下载指定url数据，并保存至指定路径
     * @param url
     * @param localFilePath
     */
    public static void download(String url, String localFilePath){

        URL urlFile = null;
        HttpURLConnection httpUrl = null;
        BufferedInputStream bis = null;
        BufferedOutputStream bos = null;
        File f = new File(localFilePath);
        try {
            urlFile = new URL(url);
            httpUrl = (HttpURLConnection)urlFile.openConnection();
            httpUrl.connect();
            bis = new BufferedInputStream(httpUrl.getInputStream());
            bos = new BufferedOutputStream(new FileOutputStream(f));
            int len = 2048;
            byte[] b = new byte[len];
            while ((len = bis.read(b)) != -1)
            {
                bos.write(b, 0, len);
            }
            System.out.println("下载完成");
            bos.flush();
            bis.close();
            httpUrl.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                bis.close();
                bos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
