package com.stocksmarket.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * <p>@author : Joesea Lea</p>
 * <p>@date : 2019/12/3</p>
 * <p>@description : </p>
 */
public class DateUtil {
    private static final Logger logger = LoggerFactory.getLogger(DateUtil.class);

    public static String format(Date date, String format) {
        DateFormat df = new SimpleDateFormat(format);
        return  df.format(date);
    }

    public static Date parse(String date, String format) {
        DateFormat df = new SimpleDateFormat(format);
        Date d = null;
        try {
            d = df.parse(date);
        } catch (ParseException e) {
            logger.error("日期格式化错误(date=" + date + ", format=" + format + "):", e);
        }
        return d;
    }

    public static Date add(Date date, int field, int amount) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);

        calendar.add(field, amount);
        return calendar.getTime();
    }
}
