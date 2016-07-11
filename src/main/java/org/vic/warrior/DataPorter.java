package org.vic.warrior;

import org.vic.enums.DateFormatEnum;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import static org.vic.util.CommonUtils.transferToString;

/**
 * 数据搬运工
 * 0,To transfer message to another type
 * 1,To copy data from one object to another object which holds different type
 * 2,To copy list form one list to another list which holds different type of elements
 *
 * @author vicdor
 * @create 2016-07-11 01:42
 */
public class DataPorter {

    private DataPorter() {
    }

    protected DataPorter(boolean forExtends) {
    }

    private static class PorterHolder {
        private static final DataPorter PORTER = new DataPorter();
    }

    public static DataPorter getPorter() {
        return PorterHolder.PORTER;
    }

    /**
     * 清除对象个别字段的数据，重置为初始化值
     *
     * @param eraseFieldsSet 要清除数据的字段集
     */
    public <D> D eraseData(D d, Set<String> eraseFieldsSet) {
        if (d == null || eraseFieldsSet == null || eraseFieldsSet.size() <= 0) return d;
        return (D) copyData(d.getClass(), d, eraseFieldsSet);
    }

    /**
     * （若符合要求（即存在数据传递关系的类之间相同字段名对应字段类型也相同，
     * 且对参与数据传递的字段没有其他限制（即无需keys），
     * 并且只进行同名字段传递(即无需keyMap)），
     * 则推荐使用）
     * 要求字段名称和类型一一对应
     */
    public <D, A> D copyData(Class<D> clazzD, A fromData) {
        return copyData(clazzD, fromData, null, null, null);
    }

    /**
     * 加了个需要排除的字段集集
     *
     * @param exceptFieldsSet 排除的字段集
     */
    public <D, A> D copyData(Class<D> clazzD, A fromData, Set<String> exceptFieldsSet) {
        return copyData(clazzD, fromData, null, null, exceptFieldsSet);
    }

    /**
     * 使用指定的keys中的D字段
     */
    public <D, A> D copyData(Class<D> clazzD, A fromData, List<String> fieldNames) {
        return copyData(clazzD, fromData, fieldNames, null, null);
    }

    /**
     * 部分字段使用keyMap指定的关系对应起来
     *
     * @param correspondingFieldsMap <k,v> k:D中字段 v:A中字段
     */
    public <D, A> D copyData(Class<D> clazzD, A fromData, Map<String, String> correspondingFieldsMap) {
        return copyData(clazzD, fromData, null, correspondingFieldsMap, null);
    }

    /**
     * 部分字段使用keyMap指定的关系对应起来
     *
     * @param correspondingFieldsMap <k,v> k:D中字段 v:A中字段
     * @param exceptFieldsSet        排除的字段集
     */
    public <D, A> D copyData(Class<D> clazzD, A fromData, Map<String, String> correspondingFieldsMap, Set<String> exceptFieldsSet) {
        return copyData(clazzD, fromData, null, correspondingFieldsMap, exceptFieldsSet);
    }

    /**
     * 将fromData对象按照keys指定的字段,根据名称一一对应（部分按照keyMap指定对应）copy为一个新的D类型对象
     *
     * @param clazzD                 要生成的对象的类型
     * @param fromData               数据来源
     * @param fieldNames             指定要copy数据的字段名
     * @param correspondingFieldsMap <k,v> k:D中字段 v:A中字段
     */
    public <D, A> D copyData(Class<D> clazzD, A fromData, List<String> fieldNames, Map<String, String> correspondingFieldsMap, Set<String> exceptFieldsSet) {
        if (fromData == null || clazzD == null) return null;

        //1,如果未提供keys则默认使用D类所有字段名
        if (fieldNames == null || fieldNames.size() == 0) {
            Field[] dFields = clazzD.getDeclaredFields();
            fieldNames = new ArrayList();
            for (Field field : dFields) {
                fieldNames.add(field.getName());
            }
        }

        //用于存储收集的问题key
        Set<String> errorFieldsSet = new HashSet();
        Class clazzA = fromData.getClass();

        if (errorFieldsSet.size() > 0) dealWithErrorFieldsSet(errorFieldsSet);
        return (D) compose(fromData, clazzD, clazzA, fieldNames, correspondingFieldsMap, exceptFieldsSet, errorFieldsSet);
    }

    /*===copyList意味着生成=======【list数据操作】======attachList意味着附加，即不断往传入的list中添加数据===*/

    /**
     * (若符合默认规则推荐使用)
     * 将A的数据集，按照名称直接对应copy为一个新的D的数据集
     * 要求：相同的字段名有相同的数据类型（可解决）
     *
     * @param fromList A的数据集
     * @return D的数据集
     */
    public <D, A> List<D> copyList(Class<D> clazzD, List<A> fromList) {
        return attachList(clazzD, null, fromList, null, null, null);
    }

    /**
     * 将A的数据集，按照名称直接对应copy为一个新的D的数据集
     *
     * @param fromList        A的数据集
     * @param exceptFieldsSet 排除的字段集
     */
    public <D, A> List<D> copyList(Class<D> clazzD, List<A> fromList, Set<String> exceptFieldsSet) {
        return attachList(clazzD, null, fromList, null, null, exceptFieldsSet);
    }

    /**
     * 将A的数据集，按照keys中指定的D的字段，依据名称直接对应copy为一个新的D的数据集
     * 要求：相同的字段名有相同的数据类型（可解决）
     *
     * @param fromList   A的数据集
     * @param fieldNames 指定的D的字段
     * @return D的数据集
     */
    public <D, A> List<D> copyList(Class<D> clzzD, List<A> fromList, List<String> fieldNames) {
        return attachList(clzzD, null, fromList, fieldNames, null, null);
    }

    /**
     * 将A的数据集按照字段名称直接对应(部分字段按照keyMap中指定的关系对应)copy为一个新的D的数据集
     * 要求：相同的字段名有相同的数据类型（可解决）
     *
     * @param fromList               A的数据集
     * @param correspondingFieldsMap <k,v> k为D中字段名，v为A中字段名
     * @return D的数据集
     */
    public <D, A> List<D> copyList(Class<D> clzzD, List<A> fromList, Map<String, String> correspondingFieldsMap) {
        return attachList(clzzD, null, fromList, null, correspondingFieldsMap, null);
    }

    /**
     * 将A的数据集按照字段名称直接对应(部分字段按照keyMap中指定的关系对应)copy为一个新的D的数据集
     *
     * @param fromList               A的数据集
     * @param correspondingFieldsMap <k,v> k为D中字段名，v为A中字段名
     * @param exceptFieldsSet        排除的字段集
     */
    public <D, A> List<D> copyList(Class<D> clzzD, List<A> fromList, Map<String, String> correspondingFieldsMap, Set<String> exceptFieldsSet) {
        return attachList(clzzD, null, fromList, null, correspondingFieldsMap, exceptFieldsSet);
    }

    /**
     * 将A的数据集，按照keys中指定的D的字段，按照字段名称直接对应(部分字段按照keyMap中指定的关系对应)copy为一个新的D的数据集
     * 要求：相同的字段名有相同的数据类型（可解决）
     *
     * @param fromList               A的数据集
     * @param fieldNames             指定的D的字段
     * @param correspondingFieldsMap <k,v> k为D中字段名，v为A中字段名
     * @return D的数据集
     */
    public <D, A> List<D> copyList(Class<D> clazzD, List<A> fromList, List<String> fieldNames, Map<String, String> correspondingFieldsMap) {
        return attachList(clazzD, null, fromList, fieldNames, correspondingFieldsMap, null);
    }

    /**
     * （list有数据后，若符合默认规则，推荐使用）
     * 将A的数据集，按照字段对应关系copy添加到D的数据集，以丰富list
     * 要求：相同的字段名有相同的数据类型（可解决）
     * 要求：list中至少要有一条数据
     *
     * @param list     D的数据集
     * @param fromList A的数据集
     * @return D的数据集
     */
    public <D, A> List<D> attachList(List<D> list, List<A> fromList) {
        return attachList(null, list, fromList, null, null, null);
    }

    /**
     * 将A的数据集，按照字段对应关系copy添加到D的数据集，以丰富list
     * 要求：list中至少要有一条数据
     *
     * @param list            D的数据集
     * @param fromList        A的数据集
     * @param exceptFieldsSet 排除的字段集
     */
    public <D, A> List<D> attachList(List<D> list, List<A> fromList, Set<String> exceptFieldsSet) {
        return attachList(null, list, fromList, null, null, exceptFieldsSet);
    }

    /**
     * （若符合默认规则，推荐使用）
     * 将A的数据集，按照字段对应关系copy添加到D的数据集，以丰富list
     * 要求：相同的字段名有相同的数据类型（可解决）
     * 说明，list可以为空或空集
     */
    public <D, A> List<D> attachList(Class<D> clazzD, List<D> list, List<A> fromList) {
        return attachList(clazzD, list, fromList, null, null, null);
    }

    /**
     * 将A的数据集，按照字段对应关系copy添加到D的数据集，以丰富list
     * 说明，list可以为空或空集
     *
     * @param exceptFieldsSet 排除的字段
     */
    public <D, A> List<D> attachList(Class<D> clazzD, List<D> list, List<A> fromList, Set<String> exceptFieldsSet) {
        return attachList(clazzD, list, fromList, null, null, exceptFieldsSet);
    }

    /**
     * 擦除list中所有元素中指定的几个字段
     *
     * @param dList       操作集合
     * @param eraseFieldsSet 要擦除后期数据的字段
     */
    public <D> List<D> eraseList(List<D> dList, Set<String> eraseFieldsSet) {
        if (dList == null || dList.size() <= 0 || eraseFieldsSet == null || eraseFieldsSet.size() <= 0) return dList;
        return (List<D>) attachList(dList.get(0).getClass(), null, dList, null, null, eraseFieldsSet);
    }

    /**
     * 将A的数据集，按照keys中指定的D的字段，依据名称直接对应copy添加到D的数据集，以丰富list
     * 要求：相同的字段名有相同的数据类型（可解决）
     * 要求：keys当有数据
     */
    public <D, A> List<D> attachList(List<D> list, List<A> fromList, List<String> fieldNames) {
        return attachList(null, list, fromList, fieldNames, null, null);
    }

    /**
     * 有map无keys有clazzD
     */
    public <D, A> List<D> attachList(Class<D> clazzD, List<D> list, List<A> fromList, Map<String, String> correspondingFieldsMap) {
        return attachList(clazzD, list, fromList, null, correspondingFieldsMap, null);
    }

    /**
     * 有map无keys有clazzD带exceptKeySet
     */
    public <D, A> List<D> attachList(Class<D> clazzD, List<D> list, List<A> fromList, Map<String, String> correspondingFieldsMap, Set<String> exceptFieldsSet) {
        return attachList(clazzD, list, fromList, null, correspondingFieldsMap, exceptFieldsSet);
    }

    /**
     * 有map有keys无clazzD
     */
    public <D, A> List<D> attachList(List<D> list, List<A> fromList, List<String> fieldNames, Map<String, String> correspondingFieldsMap) {
        return attachList(null, list, fromList, fieldNames, correspondingFieldsMap, null);
    }

    /**
     * 将A类型的集合fromList中的部分数据(通过fieldNames和keyMap指定)添加进入D类型的集合list中
     * 注：   0，D，A都应符合标准的bean规范（无视serialVersionUID）
     * 1，clazzD、list、fieldNames 至少应该有一个是有数据(非空)的，否则调用本方法无意义
     * 这里的数据依据，keys的优先级最高，其次为clazzD，再次为list中的元素；
     *
     * @param clazzD                 加这个参数的目的是如若list无值，那么我将无法获取它，而我又存在需要通过它获取到keys的状况
     * @param list                   指代我们要合成的数据集
     * @param fromList               现有的数据集，要将<A>fromList中的数据植入<D>list
     * @param fieldNames             指定D类中哪些字段参与数据合成
     * @param correspondingFieldsMap <k,v> 用于处理两个类因字段名不同导致的尴尬 k:应当为keys中存在的属于D的字段名，v：应当为A中存在的字段名
     * @param <D>                    要合成的数据类型
     * @param <A>                    现提供数据的集合元素的数据类型
     * @return 处理后的D类型数据集合
     */
    public <D, A> List<D> attachList(Class<D> clazzD, List<D> list, List<A> fromList, List<String> fieldNames, Map<String, String> correspondingFieldsMap, Set<String> exceptFieldsSet) {
        if (fromList == null || fromList.size() <= 0) return list;

        //1,如果未提供keys则默认使用D类所有字段名
        if (fieldNames == null || fieldNames.size() == 0) {
            Field[] dFields;
            if (clazzD == null) {
                //keys没有，classD有咩有，list也没点东西，玩毛...
                if (list == null || list.size() <= 0) return list;
                //list中有数据，则通过list中的一条数据来获取D类字段集
                dFields = list.get(0).getClass().getDeclaredFields();
            } else {
                dFields = clazzD.getDeclaredFields();
            }
            fieldNames = new ArrayList();
            for (Field field : dFields) {
                fieldNames.add(field.getName());
            }
        }

        if (list == null) list = new ArrayList();

        //用于存储收集的问题key
        Set<String> errorFieldsSet = new HashSet();
        //2,遍历fromList
        for (A a : fromList) {
            Class clazzA = a.getClass();
            list.add((D) compose(a, clazzD, clazzA, fieldNames, correspondingFieldsMap, exceptFieldsSet, errorFieldsSet));
        }
        if (errorFieldsSet.size() > 0) dealWithErrorFieldsSet(errorFieldsSet);
        return list;
    }

    private <D, A> D compose(A a, Class<D> clazzD, Class<A> clazzA, List<String> fieldNames, Map<String, String> correspondingFieldsMap, Set<String> exceptFieldsSet, Set<String> errorFieldsSet) {
        D d = null;
        try {
            d = clazzD.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        for (String fieldName : fieldNames) {
            if ("serialVersionUID".equals(fieldName) || (exceptFieldsSet != null && exceptFieldsSet.contains(fieldName)))
                continue;

            //默认取相同名称的字段
            String fieldNameOfA = fieldName;
            if (correspondingFieldsMap != null && correspondingFieldsMap.size() > 0) {
                Set<String> keySet = correspondingFieldsMap.keySet();
                if (keySet.contains(fieldName)) {
                    fieldNameOfA = correspondingFieldsMap.get(fieldName);
                }
            }

            try {
                transferFieldValue(d, clazzD, a, clazzA, fieldName, fieldNameOfA);
            } catch (IntrospectionException e) {
                /*创建PropertyDescriptor失败，指定字段名（fieldName）不存在于相应类中
                或无其对应getters(boolean字段可以为is开头) or setters*/
                if (!errorFieldsSet.contains(fieldName)) {
                    errorFieldsSet.add(fieldName);
                }
                continue;
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                /*参数类型不匹配*/
                dealWithErrorFieldName(e,fieldName);
            }
        }
        return d;
    }

    /**
     * fieldValueA类型可能与D中相应字段不匹配
     * 需要在子类中重写本方法，根据key值区别处理
     * 默认可以转为String或Date
     */
    protected <D, A> void transferFieldValue(D d, Class<D> clazzD, A a, Class<A> clazzA, String fieldName, String fieldNameOfA) throws IntrospectionException, InvocationTargetException, IllegalAccessException {
        PropertyDescriptor pdA = new PropertyDescriptor(fieldNameOfA, clazzA);
        Method aGetter = pdA.getReadMethod();
        Object fieldValueA = aGetter.invoke(a);

        PropertyDescriptor pdD = new PropertyDescriptor(fieldName, clazzD);
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
    }

    /**
     * cover it in the subclasses to deal with errorFieldName
     * always something
     */
    protected void dealWithErrorFieldName(IllegalAccessException e,String fieldName) {
        //LOGGER.info("1002 === 参数类型不匹配 === fieldName:{}", fieldName);
        e.printStackTrace();
    }

    /**
     * cover it in the subclasses to deal with errorFieldsSet
     * which may always for show error fields on server logs
     */
    protected void dealWithErrorFieldsSet(Set<String> errorFieldsSet) {
        //LOGGER.info("1000 === 创建PropertyDescriptor失败 === errorFieldsSet:{}", errorFieldsSet);
        System.out.println("=== errorFieldsSet === " + transferToString(errorFieldsSet));
    }

    /**
     * transfer o to a date object
     *
     * @param o the object to transfer
     */
    public <O> Date transferToDate(O o) {
        if (o == null) return null;
        if (o instanceof Date) return (Date) o;

        if (o instanceof String) {
            DateFormatEnum[] dateFormatEna = DateFormatEnum.values();
            for (DateFormatEnum dateFormatEnum : dateFormatEna) {
                Date date = transferStringToDate((String) o, dateFormatEnum.getValue());
                if (date != null) return date;
            }
        }
        return null;
    }

    /**
     * transfer text to date by specify date format
     *
     * @param text   the text to transfer, not null
     * @param format
     * @return
     */
    public Date transferStringToDate(String text, String format) {
        try {
            return new SimpleDateFormat(format).parse(text);
        } catch (ParseException e) {
            return null;
        }
    }

    /**
     * 将符合bean规范的对象按<字段名,属性值>转为Map
     *
     * @throws IntrospectionException
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     */
    public <B> Map<String, Object> transferBeanToMap(B b) throws IntrospectionException, InvocationTargetException, IllegalAccessException {
        Map<String, Object> map = new HashMap();
        if (b == null) return map;
        Class clazz = b.getClass();
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            String fieldName = field.getName();
            if ("serialVersionUID".equals(fieldName)) continue;
            PropertyDescriptor descriptor = new PropertyDescriptor(fieldName, clazz);
            Method getter = descriptor.getReadMethod();
            Object o = getter.invoke(b);
            if (o == null) continue;
            map.put(fieldName, o);
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
    public <B> Map<String, String> transferBeanToStringMap(B b) throws IntrospectionException, InvocationTargetException, IllegalAccessException {
        HashMap<String, String> map = new HashMap();
        if (b == null) return map;
        Class clazz = b.getClass();
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            String fieldName = field.getName();
            if ("serialVersionUID".equals(fieldName)) continue;
            PropertyDescriptor descriptor = new PropertyDescriptor(fieldName, clazz);
            Method getter = descriptor.getReadMethod();
            Object o = getter.invoke(b);
            if (o == null) continue;
            map.put(fieldName, transferToString(o));
        }
        return map;
    }

}
