package org.vic.util;

import java.util.Collection;
import java.util.Map;

/**
 * Created by Vicdor on 2016-04-30-0030.
 */
public class CommonUtils {
    /**
     * 判断对象是否不为空
     * @param o
     * @return
     */
    public static boolean exist(Object o){
        if(o == null) return false;
        if(o instanceof String){
            return ((String) o).trim().length() > 0;
        }
        if(o instanceof Collection){
            return ((Collection) o).size() > 0;
        }
        if(o instanceof Map){
            return ((Map) o).size() > 0;
        }
        return true;
    }

    /**
     * 判断对象是否为空
     * @param o
     * @return
     */
    public static boolean notExist(Object o){
        if(o == null) return true;
        if(o instanceof String){
            return ((String) o).trim().length() == 0;
        }
        if(o instanceof Collection){
            return ((Collection) o).size() == 0;
        }
        if(o instanceof Map){
            return ((Map) o).size() == 0;
        }
        return false;
    }

    /**
     * 判断字符串是否是纯数字
     * @param str
     * @return
     */
    public static boolean isStringNumber(String str){
        if(notExist(str)) return false;
        return str.matches("^[0-9]*$");
    }

    /**
     * 判断一个集合是否不存在非空子集
     * @param collection
     * @return
     */
    public static boolean isSetEmpty(Collection collection){
        return collection == null || collection.size() == 0;
    }

    /**
     * 判断一个集合是否存在非空子集
     * @param collection
     * @return
     */
    public static boolean isSetNotEmpty(Collection collection){
        return collection != null && collection.size() > 0;
    }

}
