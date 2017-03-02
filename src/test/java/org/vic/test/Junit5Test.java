package org.vic.test;

import org.junit.Test;
import org.vic.enums.TestEnum;
import org.vic.util.EnumUtils;

/**
 * @author vicdor
 * @create 2016-10-27 11:30
 */
public class Junit5Test {
    @Test
    public void testAssert() {
//        Integer s = null;
//        System.out.println( s != 3);
//        String js = "";
//        JSON json = JSON.parseObject(js);
//        Map<String,String> map = JSON.toJavaObject(json,Map.class);
//        Set<String> keySet = map.keySet();
//        for (String s : keySet) {
//            System.out.println("=== s === " + s);
//        }
        String a = null;
        String b = "2";
        Integer c = 2;
        int d = 2;
        System.out.println("===  === " + a + "3");

        TestEnum testEnum = EnumUtils.valueOf(TestEnum.class, "ook", d);
        System.out.println("===  === " + testEnum);

        System.out.println("===  === " + markSides("330382199107188718", "#", 3, 2));
        System.out.println("===  === " + markCenter("330382199107188718", "#", 4, 3));
    }

    public static String markIDCard(String idCard, String mark, int markBefore, int markAfter) {
        String bEx = "\\S(?=[\\S]{" + (idCard.length() - markBefore) + "})";
        String aEx = "(?<=[\\S]{" + (idCard.length() - markAfter) + "})\\S";
        return idCard.replaceAll(bEx, mark).replaceAll(aEx, mark);
    }

    /**
     * 为字符串打上一连串的码
     * @param words 待操作字符串
     * @param mark 替换标识{如：*}
     * @param start 打码起始位置
     * @param length 打码长度
     * @return
     */
    public static String markCenter(String words, String mark, int start, int length) {
        String ex = "(?<=[\\S]{" + start + "})\\S(?=[\\S]{" + (words.length() - start - length) + "})";
        return words.replaceAll(ex, mark);
    }

    /**
     * 为字符串两边打码，仅留中间部分
     * @param words 待操作字符串
     * @param mark 替换标识{如：*}
     * @param markNumBefore 前标识位数
     * @param markNumAfter 后标识位数
     * @return
     */
    public static String markSides(String words, String mark, int markNumBefore, int markNumAfter) {
        return markCenter(markCenter(words, mark, 0, markNumBefore), mark, words.length() - markNumAfter, markNumAfter);
    }

}
