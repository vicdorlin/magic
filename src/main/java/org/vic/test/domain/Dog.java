package org.vic.test.domain;

import java.util.Date;

/**
 * @author vicdor
 * @create 2016-06-06 13:59
 */
public class Dog {
    private static final long serialVersionUID = -1L;

    private String name;
    private String age;
    private String color;
    private Boolean cool;
    private String birthday;
    private Date happyDay;
    private String nani;

    public Dog() {
    }

    public Dog(String name, String age) {
        this.name = name;
        this.age = age;
    }

    public Date getHappyDay() {
        return happyDay;
    }

    public String getNani() {
        return nani;
    }

    public void setNani(String nani) {
        this.nani = nani;
    }

    public void setHappyDay(Date happyDay) {
        this.happyDay = happyDay;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public Boolean getCool() {
        return cool;
    }

    public void setCool(Boolean cool) {
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

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }
}
