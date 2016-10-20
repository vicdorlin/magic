package org.vic.test;

import java.util.ArrayList;
import java.util.List;

/**
 * @author vicdor
 * @create 2016-08-02 20:13
 */
public class CatTestWithRefType {
    public static void main(String[] args) {
        /*Rabbit rabbit = new Rabbit();
        rabbit.setAge(5);
        rabbit.setName("兔子哦");
        rabbit.setBaby(new Dog("babyDog","1"));
        CatPorter catPorter = new CatPorter();
        Cat cat = catPorter.copyData(Cat.class,rabbit);
        System.out.println("=== ca === " + CommonUtils.transferToString(cat));
        cat.setName("Cat咯");
        Rabbit rabbit1 = catPorter.copyData(Rabbit.class,cat);
        System.out.println("=== rabbit1 === " + CommonUtils.transferToString(rabbit1));
        Ox ox = catPorter.copyData(Ox.class,cat);
        System.out.println("=== ox === " + CommonUtils.transferToString(ox));*/

//        String[] userid = "".split(",");
//        System.out.println("=== toWho === " + userid.length);

        List<String> list = new ArrayList<String>();
        System.out.println("=== s === " + list.get(0));
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
