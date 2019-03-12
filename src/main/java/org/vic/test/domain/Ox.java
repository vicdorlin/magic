package org.vic.test.domain;

/**
 * @author vicdor
 * @create 2016-08-02 21:15
 */
public abstract class Ox {
    private String name = backName();
    private Integer age;
    private Sheep2 baby;

    public Ox() {
    }

    public abstract String backName();

    public Ox(String name, Integer age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public boolean equals(Object obj) {
        if (super.equals(obj)) return true;
        if (obj instanceof Ox) {
            return this.name.equals(((Ox) obj).getName());
        }
        return false;
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

    public Sheep2 getBaby() {
        return baby;
    }

    public void setBaby(Sheep2 baby) {
        this.baby = baby;
    }
}
