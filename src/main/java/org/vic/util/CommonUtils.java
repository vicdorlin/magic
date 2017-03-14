package org.vic.util;

import com.alibaba.fastjson.JSON;
import org.vic.enums.DateFormatEnum;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.text.ParseException;
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
        if (o instanceof CharSequence) {
            return ((CharSequence) o).length() > 0;
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
     * transfer o to a date object
     *
     * @param o the object to transfer
     */
    public static Date transferToDate(Object o) {
        if (o == null) return null;
        if (o instanceof Date) return (Date) o;

        if (o instanceof String) {
            DateFormatEnum[] dateFormatEna = DateFormatEnum.values();
            for (DateFormatEnum dateFormatEnum : dateFormatEna) {
                Date date = transferStringToDate((String) o, dateFormatEnum.getValue());
                if (date != null) return date;
            }
        }
        return null;
    }

    /**
     * transfer text to date by specify date format
     *
     * @param text   the text to transfer, not null
     * @param format
     * @return
     */
    public static Date transferStringToDate(String text, String format) {
        try {
            return new SimpleDateFormat(format).parse(text);
        } catch (ParseException e) {
            return null;
        }
    }

    /**
     * 得到一个Bean类型的所有字段名
     *
     * @param clazz
     * @return
     */
    public static List<String> extractFieldNames(Class<?> clazz) {
        List<String> fieldNames = new ArrayList<String>();
        while (clazz != null){
            Field[] fields = clazz.getDeclaredFields();
            if (fields.length > 0) {
                for (Field field : fields) {
                    fieldNames.add(field.getName());
                }
            }
            clazz = clazz.getSuperclass();
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
            for (; startIndex >= 0 && Character.isDigit(msg.charAt(startIndex)); startIndex--) {
            }
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

    /**
     * Build search map by keywords. 要求参数数量必须为偶数数量 奇数位字符串为key,偶数位为其前位key对应之value
     *
     * @param keywords the keywords
     * @return the map
     */
    public static Map<String, Object> buildSearchMap(String... keywords) {
        Map<String, Object> map = new HashMap<String, Object>();
        for (int i = 0; i < keywords.length; i += 2) {
            map.put(keywords[i], keywords[i + 1]);
        }
        return map;
    }

    /**
     * just like {@link #buildSearchMap}
     *
     * @param keywords the keywords
     * @return the map
     */
    public static Map<String, String> buildTestSearchMap(String... keywords) {
        Map<String, String> map = new HashMap<String, String>();
        for (int i = 0; i < keywords.length; i += 2) {
            map.put(keywords[i], keywords[i + 1]);
        }
        return map;
    }

    public static void main(String[] args) {
//        System.out.println("=== kkk === " + markKeyMsg("2776免息注册爱学贷立享35元红包", "#125353"));
//        String tags = "[\"ss\",\"sda\",\"jjj\"]";
//
//        List<String> tagList = JSON.parseArray(tags,String.class);
//        System.out.println("===  === " + tagList.size());

//        List<String> list = Arrays.asList("ss","sda","jjj");
//        System.out.println("===  === " + JSONArray.toJSONString(list));;

        /*List<Dog> dogs = new ArrayList<Dog>();
        for (Dog dog : dogs) {
            System.out.println("===  === " + dog.getName());
        }*/

//        System.out.println("=== map === " + buildSearchMap());
//        System.out.println("=== toString === " + transferToString(new Hey()));
//
//        System.out.println("=== get === " + getByFieldName(new Hey(), "hello"));
    }

    public static <T> Object getByFieldName(T t, String fieldName) {
        try {
            PropertyDescriptor pd = new PropertyDescriptor(fieldName,t.getClass());
            Method getter = pd.getReadMethod();
            return getter.invoke(t);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    static class Hey {
        private String hello = "sa";
        private boolean bool = true;

        public String getHello() {
            return hello;
        }

        public void setHello(String hello) {
            this.hello = hello;
        }

        public boolean isBool() {
            return bool;
        }

        public void setBool(boolean bool) {
            this.bool = bool;
        }
    }
}
