package org.vic.test;

import org.vic.test.domain.Cat;
import org.vic.test.domain.Dog;
import org.vic.test.domain.Rabbit;
import org.vic.util.CommonUtils;
import org.vic.warrior.CatDataCopier;
import org.vic.warrior.DataPorter;

import java.util.HashMap;
import java.util.Map;

/**
 * @author vicdor
 * @create 2016-08-02 20:13
 */
public class CatTestWithRefType {
    public static void main(String[] args) {
        Rabbit rabbit = new Rabbit();
        rabbit.setAge(5);
        rabbit.setName("兔子哦");
        rabbit.setBaby(new Dog("babyDog","1"));
        rabbit.setHello(false);
        DataPorter porter = DataPorter.newCustomizePorter(CatDataCopier.class);
        Map<String, String> correspondingFieldsMap = new HashMap<>();
        correspondingFieldsMap.put("hi","hello");
        Cat cat = porter.copyData(Cat.class,rabbit,correspondingFieldsMap);
        System.out.println("=== ca === " + CommonUtils.transferToString(cat));
        /*cat.setName("Cat咯");
        Rabbit rabbit1 = catPorter.copyData(Rabbit.class,cat);
        System.out.println("=== rabbit1 === " + CommonUtils.transferToString(rabbit1));
        Ox ox = catPorter.copyData(Ox.class,cat);
        System.out.println("=== ox === " + CommonUtils.transferToString(ox));*/

//        String[] userid = "".split(",");
//        System.out.println("=== toWho === " + userid.length);
/*
        StringBuilder sb = new StringBuilder();
        sb.append(",123");
        sb.append(",321");
        sb.append(",haha");
        sb.deleteCharAt(0);
        String sbs = sb.toString();
        System.out.println("===  === " +"---"+sb.length());*/
    }

    public static String getToWho(String toWho){
        if("" != toWho && null != toWho){
            if(!(",".equals(toWho.substring(0, 1)))){
                toWho = ","+toWho;
            }
            if(!(",".equals(toWho.substring(toWho.length() - 1,toWho.length())))){
                toWho = toWho+",";
            }
        }else{
            toWho = "";
        }
        return toWho;
    }
}
