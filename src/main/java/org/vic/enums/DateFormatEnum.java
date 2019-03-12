package org.vic.enums;

/**
 * Created by vicdor on 2016-07-11 02:13
 */
public enum DateFormatEnum {
    DEFAULT("yyyy-MM-dd HH:mm:ss"),
    $DEFAULT("yyyy/MM/dd HH:mm:ss"),
    YYYY_MM_DD("yyyy-MM-dd"),
    YYYY$MM$DD("yyyy/MM/dd"),
    YYYYMMDD("yyyyMMdd"),
    YYYY_MM("yyyy-MM"),
    YYYY$MM("yyyy/MM"),
    YYYYMM("yyyyMM"),
    MM_DD("MM-dd"),
    MM$DD("MM/dd"),
    MMDD("MMdd"),
    M_D("M-d"),
    M$D("M/d"),
    TIME_SEQUENCE("yyyyMMddHHmmss"),
    DF_YYYY_MM_DD_E_HH_MM("yyyy-MM-dd E HH:mm");
    private String value;

    private DateFormatEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
