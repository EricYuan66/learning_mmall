package com.mmall.util;

import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.util.Date;

/**
 * Created by yqn19 on 2017-10-05.
 * 时间相关的工具类
 */
public class DateTimeUtil {
    //joda-time开源包
    public static final String STANDARD_FORMAT = "yyyy-MM-dd HH:mm:ss";

    //str->date
    public static Date strToDate(String dateTimeStr, String formatStr) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormat.forPattern(formatStr);
        DateTime dateTime = dateTimeFormatter.parseDateTime(dateTimeStr);
        return dateTime.toDate();
    }

    public static Date strToDate(String dateTimeStr) {
        return strToDate(dateTimeStr, STANDARD_FORMAT);
    }

    //date->str
    public static String dateToStr(Date date, String formatStr) {
        if (date == null)
            return StringUtils.EMPTY;
        DateTime dateTime = new DateTime(date);
        return dateTime.toString(formatStr);
    }

    public static String dateToStr(Date date) {
        return dateToStr(date, STANDARD_FORMAT);
    }

    private static void test(){}
}
