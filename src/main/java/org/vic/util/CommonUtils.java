package org.vic.util;

import com.alibaba.fastjson.JSON;
import org.vic.enums.DateFormatEnum;

import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by Vicdor on 2016-04-30-0030.
 */
public class CommonUtils {
    /**
     * 判断对象是否不为空
     *
     * @param o
     * @return
     */
    public static boolean exist(Object o) {
        if (o == null) return false;
        if (o instanceof String) {
            return ((String) o).trim().length() > 0;
        }
        if (o instanceof Collection) {
            return ((Collection) o).size() > 0;
        }
        if (o instanceof Map) {
            return ((Map) o).size() > 0;
        }
        return true;
    }

    /**
     * 判断对象是否为空
     *
     * @param o
     * @return
     */
    public static boolean notExist(Object o) {
        return !exist(o);
    }

    /**
     * 判断字符串是否是纯数字
     *
     * @param str
     * @return
     */
    public static boolean isStringNumber(String str) {
        if (notExist(str)) return false;
        return str.matches("^[0-9]*$");
    }

    /**
     * 判断一个集合是否不存在非空子集
     *
     * @param collection
     * @return
     */
    public static boolean isSetEmpty(Collection collection) {
        return collection == null || collection.size() == 0;
    }

    /**
     * 判断一个集合是否存在非空子集
     *
     * @param collection
     * @return
     */
    public static boolean isSetNotEmpty(Collection collection) {
        return !isSetEmpty(collection);
    }

    /**
     * 可以理解为万用toString
     *
     * @param t
     * @param <T>
     * @return
     */
    public static <T> String transferToString(T t) {
        if (t == null) return null;
        if (t instanceof String) return (String) t;
        if (t instanceof Date) return new SimpleDateFormat(DateFormatEnum.DEFAULT.getValue()).format(t);
        if (t instanceof Number || t instanceof Character || t instanceof Boolean) return String.valueOf(t);
        return JSON.toJSONString(t);
    }

    /**
     * 得到一个Bean类型的所有字段名
     *
     * @param clazz
     * @return
     */
    public static List<String> extractFieldNames(Class<?> clazz) {
        Field[] fields = clazz.getDeclaredFields();
        List<String> fieldNames = new ArrayList<String>();
        if (fields.length > 0) {
            for (Field field : fields) {
                fieldNames.add(field.getName());
            }
        }
        return fieldNames;
    }

    /**
     * Mark key msg string.
     *
     * @param msg   the msg
     * @param color the color
     * @return the string
     */
    public static String markKeyMsg(String msg, String color) {
        return markKeyMsg(msg, color, "元", "免息");
    }

    /**
     * Mark key msg string.
     *
     * @param msg   the msg
     * @param color the color
     * @param marks the marks
     * @return the string
     */
    public static String markKeyMsg(String msg, String color, String... marks) {
        for (String mark : marks) {
            if (!msg.contains(mark)) continue;
            int markIndex = msg.indexOf(mark);
            int startIndex = markIndex - 1;
            int endIntex = markIndex + mark.length();
            for (; startIndex >= 0 && Character.isDigit(msg.charAt(startIndex)); startIndex--) {}
            if (++startIndex == markIndex) {
                continue;
            }
            String prefixMsg = msg.substring(0, startIndex);
            String suffixMsg = msg.substring(endIntex);
            String content = "<font color='" + color + "'>" + msg.substring(startIndex, endIntex) + "</font>";

            msg = prefixMsg + content + suffixMsg;
        }
        return msg;
    }

    public static void main(String[] args) {
        System.out.println("=== kkk === " + markKeyMsg("2776免息注册爱学贷立享35元红包", "#125353"));
    }
}
