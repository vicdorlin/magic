package org.vic.test;

import org.vic.test.domain.Cat;
import org.vic.test.domain.Dog;
import org.vic.test.domain.Rabbit;
import org.vic.util.CommonUtils;
import org.vic.warrior.DataPorter;

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
        DataPorter porter = DataPorter.getPorter();
        Cat cat = porter.copyData(Cat.class,rabbit);
        System.out.println("=== ca === " + CommonUtils.transferToString(cat));
    }
}
