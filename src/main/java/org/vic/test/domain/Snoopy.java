package org.vic.test.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author vicdor
 * @create 2016-06-13 16:44
 */
public class Snoopy {
    private static final long serialVersionUID = -1L;

    private String snoopyName;
    private String color;
    private Boolean cool;
    private List<String> girlfriends;

    public Snoopy() {
    }

    /**
     * 产生指定数量的Snoopy
     * @param num
     * @return
     */
    public static List<Snoopy> generateDoggies(int num){
        List<Snoopy> doggies = new ArrayList<Snoopy>();
        for (int i = 0; i < num; i++) {
            String name = "snoopy"+ i +"号";
            int age = (int) (Math.random()*10);
            String color = Doggy.getColorNum()+"";
            doggies.add(new Snoopy(name,age+"",color,true));
        }
        return doggies;
    }

    public Snoopy(List<String> girlfriends) {
        this.girlfriends = girlfriends;
        Collections.reverse(this.girlfriends);
    }

    public List<String> getGirlfriends() {
        return girlfriends;
    }

    public void setGirlfriends(List<String> girlfriends) {
        this.girlfriends = girlfriends;
    }

    public Snoopy(String snoopyName, String age) {
        this.snoopyName = snoopyName;
    }

    public Snoopy(String snoopyName, String age, String color) {
        this.snoopyName = snoopyName;
        this.color = color;
    }

    public Snoopy(String snoopyName, String age, String color, Boolean cool) {
        this.snoopyName = snoopyName;
        this.color = color;
        this.cool = cool;
    }

    public Boolean isCool() {
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

    public String getSnoopyName() {
        return snoopyName;
    }

    public void setSnoopyName(String snoopyName) {
        this.snoopyName = snoopyName;
    }
}
