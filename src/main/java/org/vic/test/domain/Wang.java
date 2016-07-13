package org.vic.test.domain;

import java.util.ArrayList;
import java.util.List;

/**
 * 汪汪
 *
 * @author vicdor
 * @create 2016-07-13 17:42
 */
public class Wang extends Dog {
    private String ali;

    public Wang() {
    }

    public static List<Wang> getWangs(int num){
        List<Wang> wangs = new ArrayList<Wang>();
        for (int i = 0; i < num; i++) {
            Wang wang = new Wang();
            wang.setName("wang"+ i +"号");
            wang.setAge((int) (Math.random()*10)+"");
            wang.setColor(Doggy.getColorNum()+"");
            wang.setAli("ali");
            wangs.add(wang);
        }
        return wangs;
    }

    public String getAli() {
        return ali;
    }

    public void setAli(String ali) {
        this.ali = ali;
    }
}
