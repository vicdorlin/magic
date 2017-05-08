package org.vic.test;

import org.vic.test.domain.Dog;

import java.beans.IntrospectionException;
import java.lang.reflect.InvocationTargetException;
import java.util.*;

import static org.vic.util.CommonUtils.*;

/**
 * @author vicdor
 * @create 2016-12-01 15:18
 */
public class JustTest {
    public static void main(String[] args) throws IntrospectionException, InvocationTargetException, IllegalAccessException {

        String reg = "(^[1-9]\\d*$)|(^([1-9]\\d*))";//不能以0开头

        String reg2 = "^\\s{0,}[\\S]{1,}[\\s\\S]{0,}";//非空
        System.out.println("===  === " + "      ".matches(reg2));

        /*String s = "0.00";
        DecimalFormat dFormat = new DecimalFormat(s);
        String twoDecimal = dFormat.format(6);
        Double d = Double.parseDouble(twoDecimal);
        System.out.println("===  === " + d);


        JSONObject jsonObject = new JSONObject();

        jsonObject.put("name", "阿狗");
        jsonObject.put("age", 1);
        jsonObject.put("color", "green");
        jsonObject.put("cool", true);
        jsonObject.put("birthday", new Date());

        JSONArray jsonArray = new JSONArray();
        jsonArray.add(jsonObject);

        Doggy doggy = JSON.toJavaObject(jsonObject, Doggy.class);
        DataPorter.newPorter().setObjField(doggy, "happyDay", "never");
        DataPorter.newPorter().setObjField(doggy, "cool", false);
//        DataPorter.newPorter().setObjField(doggy, "age", "82");//不起作用
        System.out.println("===  === " + doggy);

        List<Doggy> doggies = jsonArray.toJavaList(Doggy.class);
        doggies.remove(doggies.get(0));
        System.out.println("===  === " + doggies.size());

        Dog dog = new Dog();
        DataPorter.newPorter().setObjField(dog, "cats", Arrays.asList(new Cat("mm"), new Cat("nini")));
        System.out.println("===  === " + dog);

        Map<String, Object> map = new HashMap<>();
        System.out.println("===  === " + replaceIframeToVideoMark(null, map));
        System.out.println("===  === " + map.get("showCredit"));*/

//        String content = "<p style=\"text-align:center\"><iframe src=\"https://gss3.bdY-gjrm0nxhhbt.mp4?channel=tieba\" frameborder=\"0\" width=\"640\" height=\"498\"></iframe></p><p><br></p><p><span style=\"color: rgb(51, 51, 51); font-family: &quot;microsoft yahei&quot;, simhei, sans-serif;<iframe src=\"https://gss3.bdY-gjrm0nxhhbt.mp4?channel=tieba\" frameborder=\"0\" width=\"640\" height=\"498\"></iframe> font-size: 16px; white-space: nowrap;\">小女孩张嘴吓狗狗，没想到狗狗的回应如此温暖</span><br></p>";
//        content = "st<iframe src=\"http://ssa\"></iframe><iframe src=\"https://hello\">dddw</iframe>asd";
//        System.out.println("===  === " + replaceIframeToVideoMark(content));

//        System.out.println("===  === " + String.format("我的%2$s叫%1$s", "名字", "小网"));

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

//        DataPorter porter = DataPorter.newPorter();
//        Dog dog = porter.createBean(Dog.class);
//        System.out.println("=== dog === " + dog);


//        String str = "2017-03-20 星期一 15:35";
//        System.out.println(str.replaceAll("[\\D]",""));

//        System.out.println(JSON.toJavaObject(null, Dog.class));


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

//        System.out.println("===  === " + JSON.parseObject("[]"));
        Map<String, String> map = new HashMap<>();
        map.put("url", "http://www.baidu.com?name=历史");
        map.put("rewardId", "213_1");
        map.put("title", "我了个大曹");
        System.out.println("===  === " + addUrlParam("www.jd.com?hk=ni", "name", "老高", "age", "15"));

        Dog dog = new Dog();
        dog.setAge("12");
        dog.setBirthday("sada");
        dog.setColor("green");
        dog.setCool(true);
        System.out.println("===  === " + transBean2StringMap(dog));

        List<String> fieldNames = extractFieldNames(Dog.class);
        System.out.println("===  === " + fieldNames);
    }

    public static String addUrlParam(String url, Object bean) {
        return addUrlParam(url, false, transBean2StringMap(bean));
    }

    public static String addUrlParam(String url, boolean encodeAll, Object bean) {
        return addUrlParam(url, encodeAll, transBean2StringMap(bean));
    }

    /**
     * 为url添加参数
     * 默认不限制所有参数encode，如有需要可在参数map传入的时候先行encode即可
     *
     * @param url    待整改url
     * @param params k,v,k,v,k,v键值对依次排列
     * @return 整改后的url
     */
    public static String addUrlParam(String url, String... params) {
        return addUrlParam(url, false, params);
    }

    /**
     * 为url添加参数
     *
     * @param url       待整改url
     * @param encodeAll 为true则所有参数值都会被encode,都则全部不会encode
     * @param params    k,v,k,v,k,v键值对依次排列
     * @return 整改后的url
     */
    public static String addUrlParam(String url, boolean encodeAll, String... params) {
        return addUrlParam(url, encodeAll, buildSSMap(params));
    }

    /**
     * 为url添加参数
     * 默认不限制所有参数encode，如有需要可在参数map传入的时候先行encode即可
     *
     * @param url      待整改url
     * @param paramMap 需要添加的参数map
     * @return 整改后的url
     */
    public static String addUrlParam(String url, Map<String, String> paramMap) {
        return addUrlParam(url, false, paramMap);
    }

    /**
     * 为url添加参数
     *
     * @param url       待整改url
     * @param encodeAll 为true则所有参数值都会被encode,都则全部不会encode
     * @param paramMap  需要添加的参数map
     * @return 整改后的url
     */
    public static String addUrlParam(String url, boolean encodeAll, Map<String, String> paramMap) {
        if (!isStringEmpty(url) && exist(paramMap)) {
            StringBuilder sb = new StringBuilder(url);
            String askSign = "?";
            String andSign = "&";
            String equalSign = "=";
            if (!url.contains(askSign)) {
                sb.append(askSign);
            } else if (!url.endsWith(askSign)) {
                sb.append(andSign);
            }
            Set<String> keys = paramMap.keySet();
            Iterator<String> iterator = keys.iterator();
            if (encodeAll) {
                while (iterator.hasNext()) {
                    String next = iterator.next();
                    sb.append(next).append(equalSign).append(lightUrlEncode(paramMap.get(next))).append(andSign);
                }
            } else {
                while (iterator.hasNext()) {
                    String next = iterator.next();
                    sb.append(next).append(equalSign).append(paramMap.get(next)).append(andSign);
                }
            }
            sb.setLength(sb.length() - 1);
            url = sb.toString();
        }
        return url;
    }

    public static String replaceIframeToVideoMark(String word, Map<String, Object> map) {
        map.put("showCredit", false);
        String mark = "iframe";
        if (notExist(word) || !word.contains(mark)) return word;
        String replaceMark = "video";
        String resourceHand = "src=";
        String pre = "";
        int indexStart = word.indexOf(mark);
        int indexEnd = word.indexOf(mark, ++indexStart);
        if (indexStart > 1) {
            pre = word.substring(0, indexStart - 2);
        }
        String middle = word.substring(indexStart + mark.length(), indexEnd - 2);
        String follow = word.substring(indexEnd + mark.length() + 1);
        String mid = "";
        int middleEnd = middle.indexOf(">");
        if (++middleEnd < middle.length()) {
            mid = middle.substring(middleEnd);
        }
        //middle.substring(middle.indexOf(">")+1);
        if (middle.contains(resourceHand)) {
            int indexH1 = middle.indexOf(resourceHand) + resourceHand.length();
            int indexH2 = middle.indexOf("\"", ++indexH1);
            middle = middle.substring(indexH1, indexH2);
        }
        middle = "<" + replaceMark + " src=\"" + middle + "\" width=\"100%\" controls=\"controls\"" + ">" + mid + "</" + replaceMark + ">";
        follow = replaceIframeToVideoMark(follow, null);
        return pre + middle + follow;
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
