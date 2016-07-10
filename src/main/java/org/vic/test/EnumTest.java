package org.vic.test;

import org.vic.enums.DateFormatEnum;
import org.vic.util.EnumUtils;

/**
 * @author vicdor
 * @create 2016-07-11 02:48
 */
public class EnumTest {
    public static void main(String[] args) {
        System.out.println(EnumUtils.valueOf(DateFormatEnum.class,"yyyy-MM"));
    }
}
