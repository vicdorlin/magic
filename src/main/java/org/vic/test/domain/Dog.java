package org.vic.test.domain;

import com.alibaba.fastjson.annotation.JSONField;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @author vicdor
 * @create 2016-06-06 13:59
 */
public class Dog extends Animal implements Serializable{
    private static final long serialVersionUID = -1L;

    private String name;
    private String age;
    private String color;
    private Boolean cool;
    private String birthday;
    @JSONField(format="yyyy-MM-dd HH:mm:ss",name="happyDate")
    private Date happyDay;
    private String nani;

    private Long hairs;
    private Integer toothNum;
    private Short fingerNum;
    private Byte feedTimes;
    private Character likeWord;
    private Double height;
    private Float weight;
    private Cat cat;
    private List<Cat> cats;

    private boolean likeMe;
    private char hotWord;
    private long worldAge;
    private int myAge;
    private float len;
    private double heg;
    private short sht;
    private byte bte;
    private int doubleMyAge;

    public int getDoubleMyAge() {
        return 2 * myAge;
    }

    public void setDoubleMyAge(int doubleMyAge) {
        this.doubleMyAge = doubleMyAge;
    }

    public Dog() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Dog dog = (Dog) o;

        return name != null ? name.equals(dog.name) : dog.name == null;
    }

    @Override
    public int hashCode() {
        return name != null ? name.hashCode() : 0;
    }

    public Dog(String name, String age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public String toString() {
        return this.name;
    }

    public List<Cat> getCats() {
        return cats;
    }

    public void setCats(List<Cat> cats) {
        this.cats = cats;
    }

    public boolean isLikeMe() {
        return likeMe;
    }

    public void setLikeMe(boolean likeMe) {
        this.likeMe = likeMe;
    }

    public char getHotWord() {
        return hotWord;
    }

    public void setHotWord(char hotWord) {
        this.hotWord = hotWord;
    }

    public long getWorldAge() {
        return worldAge;
    }

    public void setWorldAge(long worldAge) {
        this.worldAge = worldAge;
    }

    public int getMyAge() {
        return myAge;
    }

    public void setMyAge(int myAge) {
        this.myAge = myAge;
    }

    public float getLen() {
        return len;
    }

    public void setLen(float len) {
        this.len = len;
    }

    public double getHeg() {
        return heg;
    }

    public void setHeg(double heg) {
        this.heg = heg;
    }

    public short getSht() {
        return sht;
    }

    public void setSht(short sht) {
        this.sht = sht;
    }

    public byte getBte() {
        return bte;
    }

    public void setBte(byte bte) {
        this.bte = bte;
    }

    public Long getHairs() {
        return hairs;
    }

    public void setHairs(Long hairs) {
        this.hairs = hairs;
    }

    public Integer getToothNum() {
        return toothNum;
    }

    public void setToothNum(Integer toothNum) {
        this.toothNum = toothNum;
    }

    public Short getFingerNum() {
        return fingerNum;
    }

    public void setFingerNum(Short fingerNum) {
        this.fingerNum = fingerNum;
    }

    public Byte getFeedTimes() {
        return feedTimes;
    }

    public void setFeedTimes(Byte feedTimes) {
        this.feedTimes = feedTimes;
    }

    public Character getLikeWord() {
        return likeWord;
    }

    public void setLikeWord(Character likeWord) {
        this.likeWord = likeWord;
    }

    public Double getHeight() {
        return height;
    }

    public void setHeight(Double height) {
        this.height = height;
    }

    public Float getWeight() {
        return weight;
    }

    public void setWeight(Float weight) {
        this.weight = weight;
    }

    public Cat getCat() {
        return cat;
    }

    public void setCat(Cat cat) {
        this.cat = cat;
    }

    public Date getHappyDay() {
        return happyDay;
    }

    public String getNani() {
        return nani;
    }

    public void setNani(String nani) {
        this.nani = nani;
    }

    public void setHappyDay(Date happyDay) {
        this.happyDay = happyDay;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public Boolean getCool() {
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }
}
