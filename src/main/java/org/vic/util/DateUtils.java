package org.vic.util;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.vic.enums.DateFormatEnum;

import java.util.Date;

/**
 * 对DateTime的简单封装
 * Created by Vicdor on 2016-05-07-0007.
 */
public class DateUtils {

    /**
     * 获取当前时间指定格式时间字符串
     *
     * @param format 格式
     * @return 指定格式时间字符串
     */
    public static String getDateStr(String format) {
        return new DateTime().toString(format);
    }

    /**
     * 将当前时间转为默认格式字符串
     *
     * @return 默认格式字符串
     */
    public static String getDateStr() {
        return getDateStr(DateFormatEnum.DEFAULT.getValue());
    }

    /**
     * 将date转为默认格式的时间字符串
     *
     * @param date 要被转换的date
     * @return 默认格式的时间字符串
     */
    public static String getDateStr(Date date) {
        return new DateTime(date).toString(DateFormatEnum.DEFAULT.getValue());
    }

    /**
     * 将date转为指定格式的时间字符串
     *
     * @param date   要被转换的date
     * @param format 指定转换格式
     * @return 指定格式的时间字符串
     */
    public static String getDateStr(Date date, String format) {
        if (CommonUtils.notExist(format)) return getDateStr(date);
        return new DateTime(date).toString(format);
    }

    /**
     * 得到某一天0时的DateTime
     *
     * @param year
     * @param monthOfYear
     * @param dayOfMonth
     * @return
     */
    public static DateTime getDateTime(int year, int monthOfYear, int dayOfMonth) {
        return new DateTime(year, monthOfYear, dayOfMonth, 0, 0);
    }

    /**
     * 指定格式时间字符串转DateTime
     *
     * @param timeText
     * @param format
     * @return
     */
    public static DateTime getDateTime(String timeText, String format) {
        return DateTimeFormat.forPattern(format).parseDateTime(timeText);
    }

    /**
     * 转date为默认格式
     *
     * @param date
     * @return
     */
    public static Date formatDate(Date date) {
        return formatDate(date, DateFormatEnum.DEFAULT.getValue());
    }

    /**
     * 转date为指定格式
     *
     * @param date
     * @param format
     * @return
     */
    public static Date formatDate(Date date, String format) {
        String dateStr = getDateStr(date, format);
        return DateTimeFormat.forPattern(format).parseDateTime(dateStr).toDate();
    }

}
