package org.vic.test.domain;

/**
 * @author vicdor
 * @program magic
 * @create 2019-01-10 14:02
 */
public class Ani<T> {
    private T animal;
    private Long man;

    public T getAnimal() {
        return animal;
    }

    public void setAnimal(T animal) {
        this.animal = animal;
    }

    public Long getMan() {
        return man;
    }

    public void setMan(Long man) {
        this.man = man;
    }
}
