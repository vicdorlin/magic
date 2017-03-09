package org.vic.warrior;

import org.vic.test.domain.Dog;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.DecimalFormat;
import java.util.*;

import static org.vic.util.CommonUtils.*;

/**
 * 数据搬运工
 * Data Carrier
 * <p>
 * 0,Transfer data to another type
 * 1,Copy data from one object to another
 * 2,Copy list form one list to another
 *
 * @author vicdor
 * @create 2016-07-11 01:42
 */
public class DataPorter {

    private static DataPorter porter;

    private IDataPorterCopier dataPorterCopier;

    private DataPorter() {
    }

    /**
     * 返回一个porter
     *
     * @return
     */
    public static DataPorter newPorter() {
        if (porter != null) {
            return porter;
        }
        porter = new DataPorter();
        porter.dataPorterCopier = new SimpleDataCopier();
        return porter;
    }

    /**
     * 返回一个定制的porter
     *
     * @param clazz 传入的IDataPorterCopier具体实例类型
     * @param <T> IDataPorterCopier接口的具体实现类
     * @return 返回一个定制的porter
     */
    public static <T extends IDataPorterCopier> DataPorter newCustomizePorter(Class<T> clazz) {
        DataPorter customizePorter = new DataPorter();
        try {
            customizePorter.dataPorterCopier = clazz.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return customizePorter;
    }

    /**
     * 返回一个对象的class类型，并强转为D的class
     */
    @SuppressWarnings(value = "unchecked")
    public <D> Class<D> classCast(D d) {
        return (Class<D>) d.getClass();
    }

    /**
     * 清除对象个别字段的数据，重置为初始化值
     *
     * @param eraseFieldsSet 要清除数据的字段集
     */
    public <D> D eraseData(D d, Set<String> eraseFieldsSet) {
        if (d == null || eraseFieldsSet == null || eraseFieldsSet.size() <= 0) return d;
        return copyData(classCast(d), d, eraseFieldsSet);
    }

    /**
     * （若符合要求（即存在数据传递关系的类之间相同字段名对应字段类型也相同，
     * 且对参与数据传递的字段没有其他限制（即无需fieldNames），
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
     * 使用指定的fieldNames中的D字段
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
     * 将fromData对象按照fieldNames指定的字段,根据名称一一对应（部分按照keyMap指定对应）copy为一个新的D类型对象
     *
     * @param clazzD                 要生成的对象的类型
     * @param fromData               数据来源
     * @param fieldNames             指定要copy数据的字段名（为null默认D字段名list,size为0默认A字段名list）
     * @param correspondingFieldsMap <k,v> k:D中字段 v:A中字段
     */
    public <D, A> D copyData(Class<D> clazzD, A fromData, List<String> fieldNames, Map<String, String> correspondingFieldsMap, Set<String> exceptFieldsSet) {
        if (fromData == null || clazzD == null) return null;

        if (fieldNames == null) {               //1,如果未提供fieldNames则默认使用D类所有字段名
            fieldNames = extractFieldNames(clazzD);
        } else if (fieldNames.isEmpty()) {       //2,如果提供内容为空的fieldNames,则默认使用A类型所有字段名
            fieldNames = extractFieldNames(fromData.getClass());
        }

        return compose(fromData, clazzD, fieldNames, correspondingFieldsMap, exceptFieldsSet);
    }

    /*===copyList意味着生成=======【list数据操作】======attachList意味着附加，即不断往传入的list中添加数据===*//*

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
     * 将A的数据集，按照fieldNames中指定的D的字段，依据名称直接对应copy为一个新的D的数据集
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
     * 将A的数据集，按照fieldNames中指定的D的字段，按照字段名称直接对应(部分字段按照keyMap中指定的关系对应)copy为一个新的D的数据集
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
     * 擦除list中所有元素中指定的几个字段数据
     *
     * @param dList          操作集合
     * @param eraseFieldsSet 要擦除后期数据的字段
     */
    public <D> List<D> eraseList(List<D> dList, Set<String> eraseFieldsSet) {
        if (dList == null || dList.isEmpty() || eraseFieldsSet == null || eraseFieldsSet.isEmpty()) return dList;
        return attachList(classCast(dList.get(0)), null, dList, null, null, eraseFieldsSet);
    }

    /**
     * 将A的数据集，按照fieldNames中指定的D的字段，依据名称直接对应copy添加到D的数据集，以丰富list
     * 要求：相同的字段名有相同的数据类型（可解决）
     * 要求：fieldNames当有数据
     */
    public <D, A> List<D> attachList(List<D> list, List<A> fromList, List<String> fieldNames) {
        return attachList(null, list, fromList, fieldNames, null, null);
    }

    /**
     * 有map无fieldNames有clazzD
     */
    public <D, A> List<D> attachList(Class<D> clazzD, List<D> list, List<A> fromList, Map<String, String> correspondingFieldsMap) {
        return attachList(clazzD, list, fromList, null, correspondingFieldsMap, null);
    }

    /**
     * 有map无fieldNames有clazzD带exceptKeySet
     */
    public <D, A> List<D> attachList(Class<D> clazzD, List<D> list, List<A> fromList, Map<String, String> correspondingFieldsMap, Set<String> exceptFieldsSet) {
        return attachList(clazzD, list, fromList, null, correspondingFieldsMap, exceptFieldsSet);
    }

    /**
     * 有map有fieldNames无clazzD
     */
    public <D, A> List<D> attachList(List<D> list, List<A> fromList, List<String> fieldNames, Map<String, String> correspondingFieldsMap) {
        return attachList(null, list, fromList, fieldNames, correspondingFieldsMap, null);
    }

    /**
     * 将A类型的集合fromList中的部分数据(通过fieldNames和keyMap指定)添加进入D类型的集合list中
     * 注：   0，D，A都应符合标准的bean规范（无视serialVersionUID）
     * 1，clazzD、list、fieldNames 至少应该有一个是有数据(非空)的，否则调用本方法无意义
     * 这里的数据依据，fieldNames的优先级最高，其次为clazzD，再次为list中的元素；
     *
     * @param clazzD                 加这个参数的目的是如若list无值，那么我将无法获取它，而我又存在需要通过它获取到fieldNames的状况
     * @param list                   指代我们要合成的数据集
     * @param fromList               现有的数据集，要将<A>fromList中的数据植入<D>list
     * @param fieldNames             指定D类中哪些字段参与数据合成（为null默认D字段名list,size为0默认A字段名list）
     * @param correspondingFieldsMap <k,v> 用于处理两个类因字段名不同导致的尴尬 k:应当为fieldNames中存在的属于D的字段名，v：应当为A中存在的字段名
     * @param <D>                    要合成的数据类型
     * @param <A>                    现提供数据的集合元素的数据类型
     * @return 处理后的D类型数据集合
     */
    public <D, A> List<D> attachList(Class<D> clazzD, List<D> list, List<A> fromList, List<String> fieldNames, Map<String, String> correspondingFieldsMap, Set<String> exceptFieldsSet) {
        if (fromList == null || fromList.isEmpty()) return list;

        //1,如果未提供fieldNames则默认使用D类所有字段名
        if (fieldNames == null) {
            if (clazzD == null) {
                //fieldNames没有，classD有咩有，list也没点东西，玩毛...
                if (list == null || list.isEmpty()) return list;
                fieldNames = extractFieldNames(list.get(0).getClass());
            } else {
                fieldNames = extractFieldNames(clazzD);
            }
        } else if (fieldNames.isEmpty()) {
            //所提供的fieldNames无数据则使用A类所有字段名
            fieldNames = extractFieldNames(fromList.get(0).getClass());
        }

        if (list == null) list = new ArrayList<D>();

        //2,遍历fromList
        for (A a : fromList) {
            list.add(compose(a, clazzD, fieldNames, correspondingFieldsMap, exceptFieldsSet));
        }
        return list;
    }

    /**
     * 将A类对象a中由fieldNames指定的数据，但是不包括exceptFieldsSet（默认serialVersionUID）中包含的字段，
     * 按照correspondingFieldsMap中配置的对应关系（默认同名对应），拷贝或生成类型D的一个实例
     */
    private <D, A> D compose(A a, Class<D> clazzD, List<String> fieldNames, Map<String, String> correspondingFieldsMap, Set<String> exceptFieldsSet) {
        try {
            D d = clazzD.newInstance();
            if (exceptFieldsSet == null) {
                exceptFieldsSet = new HashSet<String>();
            }
            exceptFieldsSet.add("serialVersionUID");
            for (String fieldName : fieldNames) {
                if (exceptFieldsSet.contains(fieldName)) continue;
                //参数对象相对于fieldName的字段名
                String fieldNameOfA;
                //由字段对应Map决定
                if (correspondingFieldsMap != null && correspondingFieldsMap.keySet().contains(fieldName)) {
                    fieldNameOfA = correspondingFieldsMap.get(fieldName);
                } else {
                    //默认取相同名称的字段
                    fieldNameOfA = fieldName;
                }
                try {
                    PropertyDescriptor pdA = new PropertyDescriptor(fieldNameOfA, a.getClass());
                    Method aGetter = pdA.getReadMethod();
                    Object fieldValueA = aGetter.invoke(a);

                    PropertyDescriptor pdD = new PropertyDescriptor(fieldName, clazzD);
                    Method dSetter = pdD.getWriteMethod();
                    dataPorterCopier.copyValue(d, fieldValueA, dSetter, fieldName);
                } catch (IntrospectionException e) {
                /*创建PropertyDescriptor失败，指定字段名（fieldName）不存在于参数类中
                或无其对应getters(boolean字段可以为is开头) or setters*/
                    continue;
                }
            }
            return d;
        } catch (Exception e) {
            throw new RuntimeException(e);
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
        Map<String, Object> map = new HashMap<String, Object>();
        if (b == null) return map;
        Class clazz = b.getClass();
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            String fieldName = field.getName();
            if (fieldName.startsWith("serialVersionUID")) continue;
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
        HashMap<String, String> map = new HashMap<String, String>();
        if (b == null) return map;
        Class clazz = b.getClass();
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            String fieldName = field.getName();
            if (fieldName.startsWith("serialVersionUID")) continue;
            PropertyDescriptor descriptor = new PropertyDescriptor(fieldName, clazz);
            Method getter = descriptor.getReadMethod();
            Object o = getter.invoke(b);
            if (o == null) continue;
            map.put(fieldName, transferToString(o));
        }
        return map;
    }


    public static void main(String[] args) {
        Dog dog = DataPorter.newPorter().createBean(Dog.class);
        System.out.println("===  === " + transferToString(dog));
    }

    /**
     * 生成虚拟对象，默认支持递归生成
     * 有内存溢出风险，可在{@link DataPorter#createBean(java.lang.Class, boolean)}中添加前置条件加以限制
     *
     * @param clazz
     * @param <B>
     * @return
     */
    public <B> B createBean(Class<B> clazz) {
        return createBean(clazz, true);
    }

    /**
     * 生成虚拟对象
     * 使用者请自行判断是否不宜递归
     *
     * @param clazz
     * @param recursionSupport 是否打开递归，如传入true则针对非基本数据类型以及非常用类型字段进行递归虚拟
     * @param <B>
     * @return
     */
    public <B> B createBean(Class<B> clazz, boolean recursionSupport) {
        try {
            B b = clazz.newInstance();
            List<String> fieldNames = extractFieldNames(clazz);
            if (isSetEmpty(fieldNames)) return b;
            for (String fieldName : fieldNames) {
                PropertyDescriptor pd;
                try {
                    pd = new PropertyDescriptor(fieldName, clazz);
                } catch (IntrospectionException e) {
                    continue;
                }
                Method fieldSetter = pd.getWriteMethod();
                String type = pd.getPropertyType().getName();
                Object value;
                switch (type) {
                    case "java.lang.String":
                        value = fieldName + "_" + (int) (Math.random() * 100000);
                        break;
                    case "long":
                    case "java.lang.Long":
                        value = new Long((long) (Math.random() * Long.MAX_VALUE));
                        break;
                    case "int":
                    case "java.lang.Integer":
                        value = new Integer((int) (Math.random() * Integer.MAX_VALUE));
                        break;
                    case "java.util.Date":
                        value = new Date();
                        break;
                    case "boolean":
                    case "java.lang.Boolean":
                        value = new Boolean((int) (Math.random() * 2) < 1);
                        break;
                    case "char":
                    case "java.lang.Character":
                        char min = Character.MIN_VALUE;
                        char max = Character.MAX_VALUE;
                        value = new Character((char) (Math.random() * (max - min) + min));
                        break;
                    case "byte":
                    case "java.lang.Byte":
                        value = new Byte((byte) (Math.random() * Byte.MAX_VALUE));
                        break;
                    case "short":
                    case "java.lang.Short":
                        value = new Short((short) (Math.random() * Short.MAX_VALUE));
                        break;
                    case "double":
                    case "java.lang.Double":
                        value = new Double(Math.random() * Short.MAX_VALUE);
                        value = Double.valueOf(new DecimalFormat("#.00").format(value));
                        break;
                    case "float":
                    case "java.lang.Float":
                        value = new Float(Math.random() * Short.MAX_VALUE);
                        value = Float.valueOf(new DecimalFormat("#.00").format(value));
                        break;
                    default:
                        if (recursionSupport) {
                            value = createBean(pd.getPropertyType(), recursionSupport);
                        } else {
                            value = null;
                        }
                        break;
                }
                fieldSetter.invoke(b, value);
            }
            return b;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
