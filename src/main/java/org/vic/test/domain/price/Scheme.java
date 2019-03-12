package org.vic.test.domain.price;

import java.util.Objects;
import java.util.Set;

/**
 * @author vicdor
 * @program magic
 * @create 2019-02-22 15:04
 */
public class Scheme {
    private int sumPrice;
    private Set<Goods> goodsSet;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Scheme scheme = (Scheme) o;
        return sumPrice == scheme.sumPrice &&
                goodsSet.equals(scheme.goodsSet);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sumPrice, goodsSet);
    }

    public int getSumPrice() {
        return sumPrice;
    }

    public void setSumPrice(int sumPrice) {
        this.sumPrice = sumPrice;
    }

    public Set<Goods> getGoodsSet() {
        return goodsSet;
    }

    public void setGoodsSet(Set<Goods> goodsSet) {
        this.goodsSet = goodsSet;
    }
}
