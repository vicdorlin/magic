package org.vic.enums;

/**
 * @author vicdor
 * @create 2016-07-18 02:32
 */
public enum CharsetEnum {
    UTF_8("UTF-8")
    ;
    private String value;

    private CharsetEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
