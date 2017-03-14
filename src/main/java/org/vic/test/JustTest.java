package org.vic.test;

import org.vic.test.domain.Dog;
import org.vic.warrior.DataPorter;

import java.beans.IntrospectionException;
import java.lang.reflect.InvocationTargetException;

/**
 * @author vicdor
 * @create 2016-12-01 15:18
 */
public class JustTest {
    public static void main(String[] args) throws IntrospectionException, InvocationTargetException, IllegalAccessException {
        /*List<Dog> dogs = Arrays.asList(new Dog("1","1"),new Dog("2","2"));
        dogs.get(0).setAge("21");
        dogs.get(dogs.size()-1).setAge("33");
        System.out.println("=== ss === " + dogs.get(0).getAge());*/
//        String hh = "sad:kcont=2jks&hello=123&hell=98&misa=shinozaki&ell=21";
//        System.out.println("===  === " + getParamValueFromUrl(hh, "hell"));

//        BigDecimal bg = new BigDecimal(123L);
//        System.out.println("===  === " + String.valueOf(bg.intValue()));

//        String list = "[\"123\",\"221\",\"787\"]";
//        List<Long> numbers = JSON.parseArray(list,Long.class);
//        System.out.println("===  === " + numbers.size());

//        DataPorter porter = DataPorter.newPorter();
//        Dog dog = new Dog("旺财","12");
//        dog = porter.fillInBean(dog);
//        System.out.println("=== dog === " + dog);

        DataPorter porter = DataPorter.newPorter();
        Dog dog = porter.createBean(Dog.class);
        System.out.println("=== dog === " + dog);

//        Dog[] dogs = new Dog[]{porter.createBean(Dog.class), porter.createBean(Dog.class), porter.createBean(Dog.class)};
//        Dog[] newDogs = dogs.clone();
//        System.out.println(newDogs.length);

//        Dog dog = new Dog();
//        Object o = dog;
//        List<String> fieldNames = extractFieldNames(Dog.class);
//        for (String fieldName : fieldNames) {
//            if("serialVersionUID".equals(fieldName)) continue;
//            PropertyDescriptor pd = new PropertyDescriptor(fieldName,Dog.class);
//            Method reader = pd.getReadMethod();
//            Object obj = reader.invoke(o);
//            continue;
//        }
//        System.out.println("=== dog === " + dog);
    }

    public static String getParamValueFromUrl(String url, String key) {
        key += "=";
        if (url == null || key == null) return null;
        int index = url.indexOf(key);
        if (index < 1) return null;
        char beforeIndex = url.charAt(index - 1);
        if ('&' != beforeIndex && '?' != beforeIndex) {
            url = url.substring(index + key.length());
            index = url.indexOf(key);
            if (index < 0) return null;
        }
        url = url.substring(index + key.length());
        int indexOfAndSign = url.indexOf("&");//&符号位置
        if (indexOfAndSign < 0) return url;
        return url.substring(0, indexOfAndSign);
    }

    public static void testBool(Boolean haha) {
        haha = true;
    }
}
