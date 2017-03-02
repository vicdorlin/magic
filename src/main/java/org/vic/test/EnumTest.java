package org.vic.test;

import org.vic.test.domain.Ox;

import java.util.ArrayList;
import java.util.List;
/**
 * @author vicdor
 * @create 2016-07-11 02:48
 */
public class EnumTest {
    public static void main(String[] args) {
//        System.out.println(EnumUtils.valueOf(DateFormatEnum.class,"yyyy-MM"));
        List<Ox> oxen = new ArrayList<Ox>();
        oxen.add(new Ox("小玩啊",12));
        oxen.add(new Ox("你好",22));
        Ox ox = new Ox("你好",22);
        System.out.println("===  === " + oxen.contains(ox));
        System.out.println("===  === " + ox.getClass().getSimpleName());
    }
}
