package com.joesea.stocksmarket.uitl;

import com.joesea.stocksmarket.EnvConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

/**
 * <p>@author : Joesea Lea</p>
 * <p>@date : 2019/6/30</p>
 * <p>@description : </p>
 */
@Component
public class StockCurDataDownUtil {

    private static final Logger logger = LoggerFactory.getLogger(StockCurDataDownUtil.class);

    /**
     * 下载股票交易当前数据，默认返回结果编码为gbk
     * @param stockCodes 股票代码集合（股票代码数据不能带前缀"sh"或者"sz"）
     * @return
     */
    public static String curDataDown(List<String> stockCodes) {

        StringBuilder sb = new StringBuilder();

        for (String code : stockCodes) {
            if (Pattern.matches("^6.*", code) || Pattern.matches("^9.*", code)) {
                sb.append("sh" + code + ",");
            } else if (Pattern.matches("^0.*", code) || Pattern.matches("^3.*", code)) {
                sb.append("sz" + code + ",");
            }else {
                continue;
            }
        }

        if (sb.length() <= 0) {
            return null;
        }
        String url = EnvConfig.STOCK_CODE_NAME_DOWN_URI + sb.toString().substring(0, sb.toString().length() - 1);

        return curDataDown(url);
    }

    /**
     * 下载股票交易当前数据，默认返回结果编码为gbk
     * @param url 请求地址
     * @return
     */
    public static String curDataDown(String url) {
        return curDataDown(url, "gbk");
    }

    /**
     * 下载股票交易当前数据，并放回指定编码格式结果
     * @param url 请求地址
     * @param charset 返回结果编码格式
     * @return
     */
    public static String curDataDown(String url, String charset) {
        return curDataDown(url, null, charset);
    }

    /**
     * 指定参数条件下载股票交易当前数据，并放回指定编码格式结果
     * @param url 请求地址
     * @param params 请求参数
     * @param charset 返回结果编码格式
     * @return
     */
    public static String curDataDown(String url, Map<String, Object> params, String charset) {
        /*
         * 构建请求参数
         */
        StringBuffer reqParams = new StringBuffer();
        if (params != null && params.size() > 0) {
            for (Map.Entry<String, Object> entry : params.entrySet()) {
                reqParams.append(entry.getKey());
                reqParams.append("=");
                reqParams.append(entry.getValue());
                reqParams.append("&");
            }
        }

        HttpURLConnection connection = null;
        BufferedReader bufferedReader = null;
        StringBuffer resultBuffer = new StringBuffer();

        try {
            /*
             * 构建完整请求URL
             */
            URL urlGet = null;
            if (reqParams != null && reqParams.length() > 0) {
                urlGet = new URL(url + "?" + reqParams.substring(0, reqParams.length() - 1));
            } else {
                urlGet = new URL(url);
            }

            connection = (HttpURLConnection) urlGet.openConnection();
            connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            connection.connect();
            bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream(), charset));
            String temp;
            while ((temp = bufferedReader.readLine()) != null) {
                resultBuffer.append(temp);
            }
        } catch (Exception e) {
            logger.error("请求超时异常", e);
        } finally {
            /*
             * 断开连接
             */
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                } finally {
                    if (connection != null) {
                        connection.disconnect();
                    }
                }
            }
        }
        return resultBuffer.toString();
    }
}
