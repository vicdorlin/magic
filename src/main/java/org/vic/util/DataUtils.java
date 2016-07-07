package org.vic.util;

import com.alibaba.fastjson.JSON;
import org.vic.exception.FailToCreatePropertyDescriptorException;
import org.vic.exception.ParameterTypeMismatchException;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 用于封装一些数据的处理
 * 1，A copyTo D : [组装另一个类型的对象]：【推荐】 D copyData(Class<D> clazzD, A arg)
 * 2，List<A> copyListTo List<D> : [填充新的list]：【推荐】 List<D> copyList(Class<D> clazzD, List<A> args)
 * 3，List<A> copyListTo List<D> : [补充原有的list]：【推荐】 List<D> attachList(List<D> dogs, List<A> args)
 * 4，List<A> copyListTo List<D> : [补充原有的list（dogs为空或空集则同2）]：【推荐】 List<D> attachList(Class<D> clazzD, List<D> dogs, List<A> args)
 *
 * @author vicdor
 * @create 2016-06-15 11:17
 */
public class DataUtils {

    private static final List<String> DATE_FORMATS = Arrays.asList("yyyy-MM-dd HH:mm:ss", "yyyy/MM/dd HH:mm:ss"
            , "yyyy-MM-dd", "yyyyMMdd", "yyyy/MM/dd", "yyyy-MM", "yyyyMM", "yyyy/MM");

    /**
     * 可以理解为万用toString
     *
     * @param t
     * @param <T>
     * @return
     */
    public static <T> String transferToString(T t) {
        if (t == null) return null;
        if (t instanceof String) return (String) t;
        if (t instanceof Date) return new SimpleDateFormat(DATE_FORMATS.get(0)).format(t);
        if (t instanceof Number || t instanceof Character || t instanceof Boolean) return String.valueOf(t);
        return JSON.toJSONString(t);
    }

    /**
     * 尝试将对象转为Date
     *
     * @param t
     * @param <T>
     * @return
     */
    public static <T> Date transferToDate(T t) {
        if (t == null) return null;
        if (t instanceof Date) return (Date) t;

        if (t instanceof String) {
            for (String dateFormat : DATE_FORMATS) {
                Date date = transferStringToDate((String) t, dateFormat);
                if (date != null) return date;
            }
        }
        return null;
    }

    /**
     * 将字符串按指定格式转为Date
     */
    public static Date transferStringToDate(String text, String format) {
        try {
            return new SimpleDateFormat(format).parse(text);
        } catch (ParseException e) {
            return null;
        }
    }

    /**
     * 清除对象个别字段的数据，重置为初始化值
     *
     * @param eraseKeySet 要清除数据的字段集
     */
    public static <D> D eraseData(D d, Set<String> eraseKeySet) {
        if (d == null || eraseKeySet == null || eraseKeySet.size() <= 0) return d;
        return (D) copyData(d.getClass(), d, eraseKeySet);
    }

    /**
     * （若符合要求（即存在数据传递关系的类之间相同字段名对应字段类型也相同，
     * 且对参与数据传递的字段没有其他限制（即无需keys），
     * 并且只进行同名字段传递(即无需keyMap)），
     * 则推荐使用）
     * 要求字段名称和类型一一对应
     */
    public static <D, A> D copyData(Class<D> clazzD, A arg) {
        return copyData(clazzD, arg, null, null, null);
    }

    /**
     * 加了个需要排除的字段集集
     *
     * @param exceptKeySet 排除的字段集
     */
    public static <D, A> D copyData(Class<D> clazzD, A arg, Set<String> exceptKeySet) {
        return copyData(clazzD, arg, null, null, exceptKeySet);
    }

    /**
     * 使用指定的keys中的D字段
     */
    public static <D, A> D copyData(Class<D> clazzD, A arg, List<String> keys) {
        return copyData(clazzD, arg, keys, null, null);
    }

    /**
     * 部分字段使用keyMap指定的关系对应起来
     *
     * @param keyMap <k,v> k:D中字段 v:A中字段
     */
    public static <D, A> D copyData(Class<D> clazzD, A arg, Map<String, String> keyMap) {
        return copyData(clazzD, arg, null, keyMap, null);
    }

    /**
     * 部分字段使用keyMap指定的关系对应起来
     *
     * @param keyMap       <k,v> k:D中字段 v:A中字段
     * @param exceptKeySet 排除的字段集
     */
    public static <D, A> D copyData(Class<D> clazzD, A arg, Map<String, String> keyMap, Set<String> exceptKeySet) {
        return copyData(clazzD, arg, null, keyMap, exceptKeySet);
    }

    /**
     * 将arg对象按照keys指定的字段,根据名称一一对应（部分按照keyMap指定对应）copy为一个新的D类型对象
     *
     * @param clazzD 要生成的对象的类型
     * @param arg    数据来源
     * @param keys   指定要copy数据的字段名
     * @param keyMap <k,v> k:D中字段 v:A中字段
     */
    public static <D, A> D copyData(Class<D> clazzD, A arg, List<String> keys, Map<String, String> keyMap, Set<String> exceptKeySet) {
        if (arg == null || clazzD == null) return null;

        //1,如果未提供keys则默认使用D类所有字段名
        if (keys == null || keys.size() == 0) {
            Field[] dFields = clazzD.getDeclaredFields();
            keys = new ArrayList();
            for (Field field : dFields) {
                keys.add(field.getName());
            }
        }

        //用于存储收集的问题key
        Set<String> errorKeySet = new HashSet();
        Class clazzA = arg.getClass();

        if (errorKeySet.size() > 0)
            throw new FailToCreatePropertyDescriptorException("Fail to create PropertyDescriptor for errorKeySet:" + transferToString(errorKeySet));
        return (D) compose(arg, clazzD, clazzA, keys, keyMap, exceptKeySet, errorKeySet);
    }



    /*===copyList意味着生成=======【list数据操作】======attachList意味着附加，即不断往传入的dogs中添加数据===*/

    /**
     * (若符合默认规则推荐使用)
     * 将A的数据集，按照名称直接对应copy为一个新的D的数据集
     * 要求：相同的字段名有相同的数据类型（可解决）
     *
     * @param args A的数据集
     * @return D的数据集
     */
    public static <D, A> List<D> copyList(Class<D> clazzD, List<A> args) {
        return attachList(clazzD, null, args, null, null, null);
    }

    /**
     * 将A的数据集，按照名称直接对应copy为一个新的D的数据集
     *
     * @param args         A的数据集
     * @param exceptKeySet 排除的字段集
     */
    public static <D, A> List<D> copyList(Class<D> clazzD, List<A> args, Set<String> exceptKeySet) {
        return attachList(clazzD, null, args, null, null, exceptKeySet);
    }

    /**
     * 将A的数据集，按照keys中指定的D的字段，依据名称直接对应copy为一个新的D的数据集
     * 要求：相同的字段名有相同的数据类型（可解决）
     *
     * @param args A的数据集
     * @param keys 指定的D的字段
     * @return D的数据集
     */
    public static <D, A> List<D> copyList(Class<D> clzzD, List<A> args, List<String> keys) {
        return attachList(clzzD, null, args, keys, null, null);
    }

    /**
     * 将A的数据集按照字段名称直接对应(部分字段按照keyMap中指定的关系对应)copy为一个新的D的数据集
     * 要求：相同的字段名有相同的数据类型（可解决）
     *
     * @param args   A的数据集
     * @param keyMap <k,v> k为D中字段名，v为A中字段名
     * @return D的数据集
     */
    public static <D, A> List<D> copyList(Class<D> clzzD, List<A> args, Map<String, String> keyMap) {
        return attachList(clzzD, null, args, null, keyMap, null);
    }

    /**
     * 将A的数据集按照字段名称直接对应(部分字段按照keyMap中指定的关系对应)copy为一个新的D的数据集
     *
     * @param args         A的数据集
     * @param keyMap       <k,v> k为D中字段名，v为A中字段名
     * @param exceptKeySet 排除的字段集
     */
    public static <D, A> List<D> copyList(Class<D> clzzD, List<A> args, Map<String, String> keyMap, Set<String> exceptKeySet) {
        return attachList(clzzD, null, args, null, keyMap, exceptKeySet);
    }

    /**
     * 将A的数据集，按照keys中指定的D的字段，按照字段名称直接对应(部分字段按照keyMap中指定的关系对应)copy为一个新的D的数据集
     * 要求：相同的字段名有相同的数据类型（可解决）
     *
     * @param args   A的数据集
     * @param keys   指定的D的字段
     * @param keyMap <k,v> k为D中字段名，v为A中字段名
     * @return D的数据集
     */
    public static <D, A> List<D> copyList(Class<D> clazzD, List<A> args, List<String> keys, Map<String, String> keyMap) {
        return attachList(clazzD, null, args, keys, keyMap, null);
    }

    /**
     * （dogs有数据后，若符合默认规则，推荐使用）
     * 将A的数据集，按照字段对应关系copy添加到D的数据集，以丰富dogs
     * 要求：相同的字段名有相同的数据类型（可解决）
     * 要求：dogs中至少要有一条数据
     *
     * @param dogs D的数据集
     * @param args A的数据集
     * @return D的数据集
     */
    public static <D, A> List<D> attachList(List<D> dogs, List<A> args) {
        return attachList(null, dogs, args, null, null, null);
    }

    /**
     * 将A的数据集，按照字段对应关系copy添加到D的数据集，以丰富dogs
     * 要求：dogs中至少要有一条数据
     *
     * @param dogs         D的数据集
     * @param args         A的数据集
     * @param exceptKeySet 排除的字段集
     */
    public static <D, A> List<D> attachList(List<D> dogs, List<A> args, Set<String> exceptKeySet) {
        return attachList(null, dogs, args, null, null, exceptKeySet);
    }

    /**
     * （若符合默认规则，推荐使用）
     * 将A的数据集，按照字段对应关系copy添加到D的数据集，以丰富dogs
     * 要求：相同的字段名有相同的数据类型（可解决）
     * 说明，dogs可以为空或空集
     */
    public static <D, A> List<D> attachList(Class<D> clazzD, List<D> dogs, List<A> args) {
        return attachList(clazzD, dogs, args, null, null, null);
    }

    /**
     * 将A的数据集，按照字段对应关系copy添加到D的数据集，以丰富dogs
     * 说明，dogs可以为空或空集
     *
     * @param exceptKeySet 排除的字段
     */
    public static <D, A> List<D> attachList(Class<D> clazzD, List<D> dogs, List<A> args, Set<String> exceptKeySet) {
        return attachList(clazzD, dogs, args, null, null, exceptKeySet);
    }

    /**
     * 擦除list中所有元素中指定的几个字段
     *
     * @param dList       操作集合
     * @param eraseKeySet 要擦除后期数据的字段
     */
    public static <D> List<D> eraseList(List<D> dList, Set<String> eraseKeySet) {
        if (dList == null || dList.size() <= 0 || eraseKeySet == null || eraseKeySet.size() <= 0) return dList;
        return (List<D>) attachList(dList.get(0).getClass(), null, dList, null, null, eraseKeySet);
    }

    /**
     * 将A的数据集，按照keys中指定的D的字段，依据名称直接对应copy添加到D的数据集，以丰富dogs
     * 要求：相同的字段名有相同的数据类型（可解决）
     * 要求：keys当有数据
     */
    public static <D, A> List<D> attachList(List<D> dogs, List<A> args, List<String> keys) {
        return attachList(null, dogs, args, keys, null, null);
    }

    /**
     * 有map无keys有clazzD
     */
    public static <D, A> List<D> attachList(Class<D> clazzD, List<D> dogs, List<A> args, Map<String, String> keyMap) {
        return attachList(clazzD, dogs, args, null, keyMap, null);
    }

    /**
     * 有map无keys有clazzD带exceptKeySet
     */
    public static <D, A> List<D> attachList(Class<D> clazzD, List<D> dogs, List<A> args, Map<String, String> keyMap, Set<String> exceptKeySet) {
        return attachList(clazzD, dogs, args, null, keyMap, exceptKeySet);
    }

    /**
     * 有map有keys无clazzD
     */
    public static <D, A> List<D> attachList(List<D> dogs, List<A> args, List<String> keys, Map<String, String> keyMap) {
        return attachList(null, dogs, args, keys, keyMap, null);
    }

    /**
     * 将A类型的集合args中的部分数据(通过keys和keyMap指定)添加进入D类型的集合dogs中
     * 注：   0，D，A都应符合标准的bean规范（无视serialVersionUID）
     * 1，clazzD、dogs、keys 至少应该有一个是有数据(非空)的，否则调用本方法无意义
     * 这里的数据依据，keys的优先级最高，其次为clazzD，再次为dogs中的元素；
     *
     * @param clazzD 加这个参数的目的是如若dogs无值，那么我将无法获取它，而我又存在需要通过它获取到keys的状况
     * @param dogs   指代我们要合成的数据集
     * @param args   现有的数据集，要将<A>args中的数据植入<D>dogs
     * @param keys   指定D类中哪些字段参与数据合成
     * @param keyMap <k,v> 用于处理两个类因字段名不同导致的尴尬 k:应当为keys中存在的属于D的字段名，v：应当为A中存在的字段名
     * @param <D>    要合成的数据类型
     * @param <A>    现提供数据的集合元素的数据类型
     * @return 处理后的D类型数据集合
     */
    public static <D, A> List<D> attachList(Class<D> clazzD, List<D> dogs, List<A> args, List<String> keys, Map<String, String> keyMap, Set<String> exceptKeySet) {
        if (args == null || args.size() <= 0) return dogs;

        //1,如果未提供keys则默认使用D类所有字段名
        if (keys == null || keys.size() == 0) {
            Field[] dFields;
            if (clazzD == null) {
                //keys没有，classD有咩有，dogs也没点东西，玩毛...
                if (dogs == null || dogs.size() <= 0) return dogs;
                //dogs中有数据，则通过dogs中的一条数据来获取D类字段集
                dFields = dogs.get(0).getClass().getDeclaredFields();
            } else {
                dFields = clazzD.getDeclaredFields();
            }
            keys = new ArrayList();
            for (Field field : dFields) {
                keys.add(field.getName());
            }
        }

        if (dogs == null) dogs = new ArrayList();

        //用于存储收集的问题key
        Set<String> errorKeySet = new HashSet();
        //2,遍历args
        for (A a : args) {
            Class clazzA = a.getClass();
            dogs.add((D) compose(a, clazzD, clazzA, keys, keyMap, exceptKeySet, errorKeySet));
        }
        if (errorKeySet.size() > 0)
            throw new FailToCreatePropertyDescriptorException("Fail to create PropertyDescriptor for errorKeySet:" + transferToString(errorKeySet));
        return dogs;
    }

    private static <D, A> D compose(A a, Class<D> clazzD, Class<A> clazzA, List<String> keys, Map<String, String> keyMap, Set<String> exceptKeySet, Set<String> errorKeySet) {
        D d = null;
        try {
            d = clazzD.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        for (String key : keys) {
            if ("serialVersionUID".equals(key) || (exceptKeySet != null && exceptKeySet.contains(key))) continue;

            //默认取相同名称的字段
            String valueKey = key;
            if (keyMap != null && keyMap.size() > 0) {
                Set<String> keySet = keyMap.keySet();
                if (keySet.contains(key)) {
                    valueKey = keyMap.get(key);
                }
            }

            try {
                PropertyDescriptor pdA = new PropertyDescriptor(valueKey, clazzA);
                Method aGetter = pdA.getReadMethod();
                Object fieldValueA = aGetter.invoke(a);

                //此处fieldValueA类型可能与D中相应字段不匹配，需要根据key值区别处理，默认可以转为String

                PropertyDescriptor pdD = new PropertyDescriptor(key, clazzD);
                Method dSetter = pdD.getWriteMethod();
                try {
                    dSetter.invoke(d, fieldValueA);
                } catch (IllegalArgumentException e) {
                    try {
                        //如果类型不对应,尝试转为String
                        dSetter.invoke(d, transferToString(fieldValueA));
                    } catch (IllegalArgumentException exception) {
                        //如果类型依旧不对，尝试转为Date
                        dSetter.invoke(d, transferToDate(fieldValueA));
                    }
                }
            } catch (IntrospectionException e) {
                    /*创建PropertyDescriptor失败，指定字段名（key）不存在于相应类中
                    或无其对应getters(boolean字段可以为is开头) or setters*/
                if (!errorKeySet.contains(key)) {
                    errorKeySet.add(key);
                }
                continue;
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                /*参数类型不匹配*/
                throw new ParameterTypeMismatchException("parameter type mismatch. field name:" + key, e);
            }
        }
        return d;
    }

    /**
     * 将符合bean规范的对象按<字段名,属性值>转为Map
     *
     * @throws IntrospectionException
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     */
    public static <T> Map<String, Object> transferBeanToMap(T t) throws IntrospectionException, InvocationTargetException, IllegalAccessException {
        Map<String, Object> map = new HashMap();
        if (t == null) return map;
        Class clazz = t.getClass();
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            String key = field.getName();
            if ("serialVersionUID".equals(key)) continue;
            PropertyDescriptor descriptor = new PropertyDescriptor(key, clazz);
            Method getter = descriptor.getReadMethod();
            Object o = getter.invoke(t);
            if (o == null) continue;
            map.put(key, o);
        }
        return map;
    }

    /**
     * 将符合bean规范的对象按<字段名,属性值>转为Map<String,String> value若为非常用引用类型，则转为json
     *
     * @throws IntrospectionException
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     */
    public static <T> Map<String, String> transferBeanToStringMap(T t) throws IntrospectionException, InvocationTargetException, IllegalAccessException {
        HashMap<String, String> map = new HashMap();
        if (t == null) return map;
        Class clazz = t.getClass();
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            String key = field.getName();
            if ("serialVersionUID".equals(key)) continue;
            PropertyDescriptor descriptor = new PropertyDescriptor(key, clazz);
            Method getter = descriptor.getReadMethod();
            Object o = getter.invoke(t);
            if (o == null) continue;
            map.put(key, transferToString(o));
        }
        return map;
    }
}
