package org.vic.test.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author vicdor
 * @create 2016-06-13 16:41
 */
public class Doggy {
    private static final long serialVersionUID = -1L;

    private String name;
    private Integer age;
    private String color;
    private boolean cool;
    private Date birthday;
    private String happyDay;

    public static int getColorNum() {
        int color = (int) (Math.random() * 1000) + 100;
        if (color >= 1000) {
            color = getColorNum();
        }
        return color;
    }

    /**
     * 产生指定数量的狗
     *
     * @param num
     * @return
     */
    public static List<Doggy> generateDoggies(int num) {
        List<Doggy> doggies = new ArrayList<Doggy>();
        for (int i = 0; i < num; i++) {
            String name = "doggy" + i + "号";
            int age = (int) (Math.random() * 10) + 1;
            String color = getColorNum() + "";
            doggies.add(new Doggy(name, age, color, false, new Date(),"2016-07-08 12:12:18"));
        }
        return doggies;
    }

    public int tryReturnSix(int num) {
        if (num == 1) return 6;
        if (num == 2) return 66;
        return 666;
    }

    public Doggy() {
    }

    public Doggy(String name, Integer age) {
        this.name = name;
        this.age = age;
    }

    public Doggy(String name, Integer age, String color) {
        this(name, age);
        this.color = color;
    }

    public Doggy(String name, Integer age, String color, boolean cool) {
        this(name, age, color);
        this.cool = cool;
    }

    public Doggy(String name, Integer age, String color, boolean cool, Date birthday) {
        this(name, age, color, cool);
        this.birthday = birthday;
    }

    public Doggy(String name, Integer age, String color, boolean cool, Date birthday, String happyDay) {
        this(name, age, color, cool, birthday);
        this.happyDay = happyDay;
    }

    public String getHappyDay() {
        return happyDay;
    }

    public void setHappyDay(String happyDay) {
        this.happyDay = happyDay;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public boolean isCool() {
        return cool;
    }

    public void setCool(boolean cool) {
        this.cool = cool;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}
