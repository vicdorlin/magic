package org.vic.test.domain;

/**
 * @author vicdor
 * @create 2016-08-02 20:07
 */
public class Cat {
    private String name;
    private Integer age;
    private Sheep baby;
    private String other;
    private Integer hi;

    public Integer getHi() {
        return hi;
    }

    public void setHi(Integer hi) {
        this.hi = hi;
    }

    public String getOther() {
        return other;
    }

    public void setOther(String other) {
        this.other = other;
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

    public Sheep getBaby() {
        return baby;
    }

    public void setBaby(Sheep baby) {
        this.baby = baby;
    }
}
