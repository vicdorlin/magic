package org.vic.util;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

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
                    if (value.equals(field.get(enumConstant))) return enumConstant;
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    public static <T> T getEnum(Class<T> enumClazz, String enumWord) {
        if(enumWord != null && enumClazz.isEnum()){
            T[] enumConstants = enumClazz.getEnumConstants();
            for (T enumConstant : enumConstants) {
                try {
                    if(enumConstant.getClass().getDeclaredField(enumWord).getName().equals(enumWord)) return enumConstant;
                } catch (NoSuchFieldException e) {
                    return null;
                }
            }
        }
        return null;
    }

    public static String toJson(Enum<?>[] enumValues) throws IllegalAccessException, InvocationTargetException {
        StringBuffer buffer=new StringBuffer("[");
        boolean obj1st=true;
        for (Object obj : enumValues) {
            if(obj1st){
                obj1st=false;
            }else{
                buffer.append(",");
            }
            buffer.append("{");

            Method[] methods = obj.getClass().getMethods();
            boolean method1st=true;
            for (int i = 0; i < methods.length; i++) {

                Method method = methods[i];
                //获取枚举值的get方法
                if (method.getName().startsWith("get") && method.getParameterTypes().length == 0 && !method.getName().contains("Class")) {
                    //处理逗号
                    if(method1st){
                        method1st=false;
                    }else{
                        buffer.append(",");
                    }
                    //将get方法的get去掉,并且首字母小写
                    String name = method.getName().replace("get","");
                    buffer.append("\"" + name.substring(0, 1).toLowerCase() + name.substring(1) + "\":\"");
                    buffer.append(method.invoke(obj)+"\"");
                }
            }
            buffer.append("}");
        }
        buffer.append("]");
        return buffer.toString();
    }

    public static String toJson(Enum enumValue) throws IllegalAccessException, InvocationTargetException {
        StringBuffer buffer=new StringBuffer("{");

        Method[] methods = enumValue.getClass().getMethods();
        boolean method1st=true;
        for (int i = 0; i < methods.length; i++) {

            Method method = methods[i];
            //获取枚举值的get方法
            if (method.getName().startsWith("get") && method.getParameterTypes().length == 0 && !method.getName().contains("Class")) {
                //处理逗号
                if(method1st){
                    method1st=false;
                }else{
                    buffer.append(",");
                }
                //将get方法的get去掉,并且首字母小写
                String name = method.getName().replace("get","");
                buffer.append("\"" + name.substring(0, 1).toLowerCase() + name.substring(1) + "\":\"");
                buffer.append(method.invoke(enumValue)+"\"");
            }
        }
        buffer.append("}");
        return buffer.toString();
    }
}
