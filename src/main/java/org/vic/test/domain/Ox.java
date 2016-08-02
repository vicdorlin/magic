package org.vic.test.domain;

/**
 * @author vicdor
 * @create 2016-08-02 21:15
 */
public class Ox {
    private String name;
    private Integer age;
    private Sheep2 baby;

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

    public Sheep2 getBaby() {
        return baby;
    }

    public void setBaby(Sheep2 baby) {
        this.baby = baby;
    }
}
