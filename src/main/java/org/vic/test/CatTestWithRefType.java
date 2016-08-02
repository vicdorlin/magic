package org.vic.test;

import org.vic.test.domain.Cat;
import org.vic.test.domain.Dog;
import org.vic.test.domain.Ox;
import org.vic.test.domain.Rabbit;
import org.vic.util.CommonUtils;
import org.vic.warrior.porters.CatPorter;

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
        CatPorter catPorter = new CatPorter();
        Cat cat = catPorter.copyData(Cat.class,rabbit);
        System.out.println("=== ca === " + CommonUtils.transferToString(cat));
        cat.setName("Cat咯");
        Rabbit rabbit1 = catPorter.copyData(Rabbit.class,cat);
        System.out.println("=== rabbit1 === " + CommonUtils.transferToString(rabbit1));
        Ox ox = catPorter.copyData(Ox.class,cat);
        System.out.println("=== ox === " + CommonUtils.transferToString(ox));
    }
}
