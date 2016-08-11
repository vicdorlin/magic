package org.vic.test;

import org.vic.test.domain.Dog;
import org.vic.test.domain.Doggy;
import org.vic.warrior.DataPorter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * @author vicdor
 * @create 2016-07-25 18:01
 */
public class MyCopyToTest {
    public static void main(String[] args) throws ParseException {
        List<Doggy> doggies = Doggy.generateDoggies(1000);
        Doggy d = doggies.get(0);
        DataPorter porter = DataPorter.newPorter();

        long startTime = System.nanoTime();
        List<Dog> dogs = porter.copyList(Dog.class, doggies);
        long endTime = System.nanoTime();

//        System.out.println("=== dogs === " + CommonUtils.transferToString(dogs));
        System.out.println("=== porter:doggies to dogs 字段不完全一致 === " + (endTime - startTime) * 1.0 / 1000000 + "毫秒");

        long startTime1 = System.nanoTime();
        List<Dog> list = new ArrayList<Dog>();
        for (Doggy doggy : doggies) {
            Dog dog = new Dog();
            dog.setAge(doggy.getAge() + "");
            dog.setBirthday(doggy.getBirthday() + "");
            dog.setColor(doggy.getColor());
            dog.setHappyDay(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(doggy.getHappyDay()));
            dog.setName(doggy.getName());
            dog.setCool(doggy.isCool());
            list.add(dog);
        }
        long endTime1 = System.nanoTime();

//        System.out.println("=== dogs2 === " + CommonUtils.transferToString(list));
        System.out.println("=== 人工:doggies to dogs 字段不完全一致 === " + (endTime1 - startTime1) * 1.0 / 1000000 + "毫秒");

        long start = System.nanoTime();
        List<Doggy> doggies1 = porter.copyList(Doggy.class,doggies);
        long end = System.nanoTime();
        System.out.println("=== porter：n条字段完全一致的对象数据传递 === " + (end - start) * 1.0 / 1000000 + "毫秒");

        long start1 = System.nanoTime();
        List<Doggy> doggies2 = new ArrayList<Doggy>();
        for (Doggy doggy : doggies) {
            Doggy dy5 = new Doggy();
            dy5.setName(doggy.getName());
            dy5.setColor(doggy.getColor());
            dy5.setAge(doggy.getAge());
            dy5.setCool(doggy.isCool());
            dy5.setBirthday(doggy.getBirthday());
            dy5.setHappyDay(doggy.getHappyDay());
            doggies2.add(dy5);
        }
        long end1 = System.nanoTime();
        System.out.println("=== 人工：n条字段完全一致的对象数据传递 === " + (end1 - start1) * 1.0 / 1000000 + "毫秒");


        long startTime2 = System.nanoTime();
        Dog dd = porter.copyData(Dog.class, d);
        long endTime2 = System.nanoTime();
        System.out.println("=== porter:单条异类（指字段不完全一致的类）拷贝 === " + (endTime2 - startTime2) * 1.0 / 1000000 + "毫秒");

        long startTime3 = System.nanoTime();
        Dog dog = new Dog();
        dog.setAge(d.getAge() + "");
        dog.setBirthday(d.getBirthday() + "");
        dog.setColor(d.getColor());
        dog.setHappyDay(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(d.getHappyDay()));
        dog.setName(d.getName());
        dog.setCool(d.isCool());
        long endTime3 = System.nanoTime();
        System.out.println("=== 人工：单条异类（指字段不完全一致的类）拷贝 === " + (endTime3 - startTime3) * 1.0 / 1000000 + "毫秒");

        long startTime4 = System.nanoTime();
        Doggy dy4 = porter.copyData(Doggy.class, d);
        long endTime4 = System.nanoTime();
        System.out.println("=== porter:单条同类拷贝 === " + (endTime4 - startTime4) * 1.0 / 1000000 + "毫秒");

        long startTime5 = System.nanoTime();
        Doggy dy5 = new Doggy();
        dy5.setName(d.getName());
        dy5.setColor(d.getColor());
        dy5.setAge(d.getAge());
        dy5.setCool(d.isCool());
        dy5.setBirthday(d.getBirthday());
        dy5.setHappyDay(d.getHappyDay());
        long endTime5 = System.nanoTime();
        System.out.println("=== 人工：单条同类拷贝 === " + (endTime5 - startTime5) * 1.0 / 1000000 + "毫秒");

    }
}
