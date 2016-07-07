package org.vic.util;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;

import java.util.Date;

/**
 * Created by Vicdor on 2016-05-07-0007.
 */
public class DateUtils {
    /**
     * 默认时间格式
     */
    public static final String DATE_DEFAULT = "yyyy-MM-dd HH:mm:ss";

    public static final String DATE_YYYYMMDD = "yyyyMMdd";
    public static final String DATE_YYYY_MM = "yyyy-MM";
    public static final String DATE_YYYY_MM_DD = "yyyy-MM-dd";
    public static final String DATE_M_D = "M-d";

    /**
     * 获取当前时间指定格式时间字符串
     * @param format 格式
     * @return 指定格式时间字符串
     */
    public static String getDateStr(String format){
        return new DateTime().toString(format);
    }

    /**
     * 将当前时间转为默认格式字符串
     * @return 默认格式字符串
     */
    public static String getDateStr(){
        return getDateStr(DATE_DEFAULT);
    }

    /**
     * 将date转为默认格式的时间字符串
     * @param date 要被转换的date
     * @return 默认格式的时间字符串
     */
    public static String getDateStr(Date date){
        return new DateTime(date).toString(DATE_DEFAULT);
    }

    /**
     * 将date转为指定格式的时间字符串
     * @param date 要被转换的date
     * @param format 指定转换格式
     * @return 指定格式的时间字符串
     */
    public static String getDateStr(Date date,String format){
        if(CommonUtils.notExist(format)) return getDateStr(date);
        return new DateTime(date).toString(format);
    }

    /**
     * 得到某一天0时的DateTime
     * @param year
     * @param monthOfYear
     * @param dayOfMonth
     * @return
     */
    public static DateTime getDateTime(int year,int monthOfYear,int dayOfMonth){
        return new DateTime(year,monthOfYear,dayOfMonth,0,0);
    }

    /**
     * 指定格式时间字符串转DateTime
     * @param timeText
     * @param format
     * @return
     */
    public static DateTime getDateTime(String timeText, String format){
        return DateTimeFormat.forPattern(format).parseDateTime(timeText);
    }
}
