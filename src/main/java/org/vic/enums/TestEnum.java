package org.vic.enums;

/**
 * @author vicdor
 * @create 2016-11-15 18:45
 */
public enum TestEnum {
    FIRST("first",1,1),
    SECOND("second",2,2)
    ;
    private String key;
    private Integer value;
    private int ook;

    private TestEnum(String key, Integer value, int ook) {
        this.key = key;
        this.value = value;
        this.ook = ook;
    }

    public String getKey() {
        return key;
    }

    public Integer getValue() {
        return value;
    }

    public int getOok() {
        return ook;
    }
}
