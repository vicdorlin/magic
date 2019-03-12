package org.vic.test.domain.price;

import java.util.Objects;

/**
 * @author vicdor
 * @program magic
 * @create 2019-02-22 14:55
 */
public class Goods {
    private String name;
    private Integer money;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Goods goods = (Goods) o;
        return name.equals(goods.name) &&
                money.equals(goods.money);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, money);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getMoney() {
        return money;
    }

    public void setMoney(Integer money) {
        this.money = money;
    }
}
