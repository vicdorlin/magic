package org.vic.test;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import org.vic.test.domain.Ani;
import org.vic.test.domain.Sheep;

import java.text.ParseException;

/**
 * @author vicdor
 * @create 2018-09-25 12:14
 */
public class LikeTest {
    public static void main(String[] args) throws ParseException {
        /*String date1 = "2018-12-12 10:11:12";
        String date2 = "2018-12-12 10:11:18";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Long d1 = sdf.parse(date1).getTime();
        Long d2 = sdf.parse(date2).getTime();
        System.out.println((d1-d2)/1000);*/
//        String alex = "iPhone X";
//        String ale = "iPhone11,6";
//        String wo = ale.replaceAll("\\s+","").toLowerCase();
//        System.out.println(wo.contains("iphone11"));
//        System.out.println(new SimpleDateFormat("yyMMdd").format(new Date()));
//        {"man":"1222","animal","{\"name\":\"laoda\",\"age\",\"15\"}"}
//        {"name":"laoda","age","15"}
        Sheep ox = JSON.toJavaObject(JSON.parseObject(getRequestParam("{\"man\":\"1222\",\"animal\":\"{\\\"name\\\":\\\"laoda\\\",\\\"age\\\":\\\"15\\\"}\"}")),Sheep.class);
        System.out.println(JSON.toJSONString(ox));
    }
    protected static final <T> T getRequestParam(String json) {
        Ani<T> ani = getRequestParam(json,Ani.class);
        return ani.getAnimal();
    }
    protected static final <T> T getRequestParam(String json,Class<T> t) {
        try {
            JSONObject cJson = JSON.parseObject(json);
            json = JSON.toJSONString(cJson, SerializerFeature.DisableCircularReferenceDetect);
            T formvo = (T) JSON.parseObject(json, t);
            return formvo;
        } catch (Exception e) {
            System.out.println("err");
        }
        return null;
    }


    public static int dateDiff(String interval, long date1, long date2) {
        Long l = Long.valueOf("10000000000");
        if (date1 >= l.longValue())
            date1 = date1 / 1000;
        if (date2 >= l.longValue())
            date2 = date2 / 1000;

        long d = 0;
        if (interval.equals("d")) { // 天
            d = (date1 - date2) / (24 * 60 * 60);
        } else if (interval.equals("w")) { // 星期
            d = (date1 - date2) / (24 * 60 * 60 * 7);
        } else if (interval.equals("m")) { // 月
            d = (date1 - date2) / (24 * 60 * 60 * 30);
        } else if (interval.equals("y")) { // 年
            d = (date1 - date2) / (24 * 60 * 60 * 365);
        } else if (interval.equals("h")) { // 小时
            d = (date1 - date2) / (60 * 60);
        } else if (interval.equals("n")) { // 分钟
            d = (date1 - date2) / (60);
        } else if (interval.equals("s")) { // 秒
            d = date1 - date2;
        }
        return (int) Math.abs(d);
    }
}
