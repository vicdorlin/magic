package org.vic.test;

import org.vic.test.domain.price.Goods;
import org.vic.test.domain.price.Scheme;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * @author vicdor
 * @program magic
 * @create 2019-02-22 14:57
 */
public class GoodsUtil {
    public static List<Scheme> schemes(List<Goods> goodsList, int budget) {
        Collections.sort(goodsList, new Comparator<Goods>() {
            @Override
            public int compare(Goods o1, Goods o2) {
                if (o1.getMoney() > o2.getMoney()) return 1;
                else if (o1.getMoney() < o2.getMoney()) return -1;
                return 0;
            }
        });
        List<Scheme> schemes = new ArrayList<>();
        for (int goodsNum = 1;; goodsNum++) {
            List<Goods> scheme = new ArrayList<>();
            for (Goods goods : goodsList) {

            }

        }
    }
}
