package org.vic.util;

import java.lang.reflect.Field;

/**
 * Created by Vicdor on 2016-05-07-0007.
 */
public class EnumUtils {

    /**
     * 根据枚举对象属性值获取枚举对象
     * 注：只适用于查询value值归属在名为"value"字段下的枚举类
     *
     * @param enumClazz 枚举类的类对象
     * @param value     被查询的属性值
     * @param <T>       枚举类
     * @return 返回对应的枚举对象
     */
    public static <T> T valueOf(Class<T> enumClazz, Object value) {
        return valueOf(enumClazz, "value", value);
    }

    /**
     * 根据枚举对象属性值获取枚举对象
     *
     * @param enumClazz 枚举类的类对象
     * @param fieldName 字段名称（如:"value","key","content"...）
     * @param value     枚举对象某属性值
     * @param <T>       枚举类
     * @return 返回对应的枚举对象
     */
    public static <T> T valueOf(Class<T> enumClazz, String fieldName, Object value) {
        if (value != null && enumClazz.isEnum()) {
            T[] enumConstants = enumClazz.getEnumConstants();
            for (T enumConstant : enumConstants) {
                try {
                    Field field = enumConstant.getClass().getDeclaredField(fieldName);
                    field.setAccessible(true);
                    if (value.equals(field.get(enumConstant)))
                        return enumConstant;
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        }
        return null;
    }
}
