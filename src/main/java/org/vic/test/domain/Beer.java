package org.vic.test.domain;

import org.vic.enums.DateFormatEnum;

/**
 * @author vicdor
 * @program magic
 * @create 2019-01-22 18:10
 */
public class Beer {
    private String name;
    private DateFormatEnum dateFormatEnum;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public DateFormatEnum getDateFormatEnum() {
        return dateFormatEnum;
    }

    public void setDateFormatEnum(DateFormatEnum dateFormatEnum) {
        this.dateFormatEnum = dateFormatEnum;
    }
}
