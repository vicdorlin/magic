package org.vic.test;


import org.vic.test.domain.Dog;
import org.vic.test.domain.Doggy;
import org.vic.test.domain.Wang;
import org.vic.warrior.DataPorter;

import java.util.*;

/**
 * 反射设值
 *
 * @author vicdor
 * @create 2016-06-13 16:40
 */
public class TestReflectSet {
    public static void main(String[] args) throws Exception {
        DataPorter porter = DataPorter.getPorter();

        List<Doggy> doggies = Doggy.generateDoggies(10);
//        List<Snoopy> snoopies = Snoopy.generateDoggies(5);
        List<Wang> wangs = Wang.getWangs(6);
//        System.out.println("=== doggies === " + doggies);
//        System.out.println("=== snoopies === " + snoopies);
        List<Dog> dogs = new ArrayList<Dog>();
//        Map<String,String> snoopyMap = new HashMap<String, String>();
//        snoopyMap.put("name","snoopyName");
        Set<String> eraseFieldsSet = new HashSet<String>();
        eraseFieldsSet.add("cool");
        dogs = porter.attachList(Dog.class,dogs,doggies,eraseFieldsSet);
//        dogs = porter.attachList(Dog.class,dogs,snoopies,snoopyMap);
        dogs = porter.attachList(Dog.class,dogs,wangs);
        System.out.println("=== dogs.size() === " + dogs.size());

        //抹掉所有的cool
        dogs = porter.eraseList(dogs,eraseFieldsSet);
        System.out.println("=== dogs.eraseData === " + dogs.size());

        /*String name = "doggyBaby";
        int age = (int) (Math.random()*10);
        String color = getColorNum()+"";
        Doggy doggy = new Doggy(name,age,color,false,new Date(),"2016-05-27 11:12:13");
        Dog dog = porter.copyData(Dog.class,doggy);
        System.out.println("=== dog === " + transferToString(dog));*/
    }

}
