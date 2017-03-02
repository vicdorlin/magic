package org.vic.test.domain;

import java.io.Serializable;

/**
 * @author vicdor
 * @create 2016-08-02 20:10
 */
public class Rabbit implements Serializable{
    private static final long serialVersionUID = -433133405129095166L;
    private String name;
    private Integer age;
    private Dog baby;
    private boolean hello;

    public boolean isHello() {
        return hello;
    }

    public void setHello(boolean hello) {
        this.hello = hello;
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

    public Dog getBaby() {
        return baby;
    }

    public void setBaby(Dog baby) {
        this.baby = baby;
    }
}
