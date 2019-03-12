package org.vic.test;

import org.vic.test.domain.Animal;
import org.vic.test.domain.Dog;
import org.vic.util.MD5Util;

import java.beans.IntrospectionException;
import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.util.*;

import static org.vic.util.CommonUtils.*;

/**
 * @author vicdor
 * @create 2016-12-01 15:18
 */
public class JustTest {
    public static void main(String[] args) throws IntrospectionException, InvocationTargetException, IllegalAccessException, IOException {

        /*OldOx ox = new OldOx();

        System.out.println("===  === " + "nos_sas".indexOf("os_"));

        String reg = "(^[1-9]\\d*$)|(^([1-9]\\d*))";//不能以0开头

        String reg2 = "^\\s{0,}[\\S]{1,}[\\s\\S]{0,}";//非空
        System.out.println("===  === " + "      ".matches(reg2));*/

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
        /*Map<String, String> map = new HashMap<>();
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
        System.out.println("===  === " + fieldNames);*/

//        String sa = "s000k@ion111@sja";
////        String[] s = sa.split("@",4);
//        sa = sa.replaceAll("[^10]","1");
//        System.out.println("===  === " + sa);

//        DateTime dateTime = new DateTime(new Date());

        /*DateTime dateTime = new DateTime(2017,8,10,10,10);
        dateTime = dateTime.dayOfWeek().addToCopy(1-dateTime.getDayOfWeek());
        System.out.println("===  === " + dateTime.getDayOfWeek());
        for (int i = 1; i <= 16; i++) {
            System.out.println("===  === " + i+"周");
            System.out.println("===  === " + "第一学期第"+i+"周");
            System.out.println("===  === " + dateTime.getMonthOfYear());
            System.out.println("-------- ");
            dateTime = dateTime.dayOfYear().addToCopy(7);
        }*/

        /*byte hh = 1;
        switch (hh) {
            case 1:
                System.out.println("===  === " + 2);
                break;
            default:
                System.out.println("===  === " + 1);
                break;
        }
        String re = "(^[1-9]\\d{5}(18|19|([23]\\d))\\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\\d{3}[0-9Xx]$)|(^[1-9]\\d{5}\\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\\d{2}[0-9Xx]$)";
        System.out.println("===  === " + "330382199107088718".matches(re));*/

        /*Map<String,String> map = new HashMap<>();
        map.put("ss","hy");
        System.out.println("===  === " + getDog(map).getName());
        List<String> list = new ArrayList<>();
        list.add("22");
        list.add("222");
        list.add("233");
        List<String> list1 = new ArrayList<>();
        list1.add("21");
        list1.add("233");
        list.removeAll(list1);
        System.out.println("===  === " + list.size());
        System.out.println("===  === " + list.get(0));
        list.clear();
        System.out.println("===  === " + list.size());*/

//        StringBuilder sb = new StringBuilder();
//        sb.append(",123");
//        sb.append(",333");
//        sb.deleteCharAt(0);
//        System.out.println("===  === " + sb.toString());

       /* StringBuilder builder = new StringBuilder();
        builder.append(",");
        builder.append("jjjj");
        builder.append("kkk");
        builder.deleteCharAt(0);
        System.out.println("===  === " + builder.toString());
        Dog dog = new Dog();
        dog.setName("dd");
        dog.setAge("2");
        dog.setColor("gr");
        String jsonDog = JSON.toJSONString(dog);
        Cat cat = JSON.toJavaObject(JSON.parseObject(jsonDog),Cat.class);
        System.out.println("===  === " + cat);*/

        /*List<Dog> list = new ArrayList<>();
        list.add(new Dog("haha","12"));
        list.add(new Dog("dd","5"));
        list.add(new Dog("sksk","22"));
        Dog dog = list.get(1);
        dog.setName("hhha");
        Dog inx = new Dog("sksk","99");
        System.out.println("===  === " + list.get(1));*/
//        String data = "[\"20\",\"33\"]";
//        List<Integer> keys = JSON.toJavaObject(JSON.parseArray(data), List.class);
//        StringBuilder builder = new StringBuilder("skk");
//        builder = builder.append("kklllll");
//        System.out.println("===  === " + builder.toString());

//        String jj = "j";
//        heeh(jj);
//        System.out.println("===  === " + jj);

//        System.out.println("=== version check === " + compareVersion("1.3.20", "2.2.1223"));
/*
        IPorter<Dog,Cat> porter = new DogPotter();
        Cat cat = porter.transferF2T(new Dog("旺旺","3"));
        System.out.println("===  === " + cat);*/

        /*Teleporter<Dog,Cat> porter2 = Teleporter.newTeleporter();
        Cat cat = porter2.copyData(new Dog("旺旺","3"),new DogPotter());
        Cat cat1 = porter2.copyData(new Dog("hah", "1"), new IPorter<Dog, Cat>() {
            @Override
            public Cat transferF2T(Dog dog) {
                Cat cat = new Cat(dog);
                cat.setHi(233);
                return cat;
            }
        });
        Cat cat2 = porter2.copyData(new Dog("jjjj","2"),(dog) -> {
            Cat c = new Cat(dog);
            c.setOther("haha");
            return c;
        });
        System.out.println("===  === " + cat);*/

        /*Dog dog = new Dog();
        dog.setColor("red");
        dog.setCool(true);
        dog.setMyAge(2);*/
//        Animal animal = new JustTest().doWithAnimal(dog);
        /*long now = System.currentTimeMillis();
        Date date = new Date();
        long time = date.getTime();*/
        /*int a = 3;
        int b = 5;
        a = a ^ b;
        b = a ^ b;
        a = a ^ b;

        System.out.println("a=" + a + ",b=" + b);
        ESex sex = ESex.male;
        ESex sex1 = ESex.male;
        System.out.println("===  === " + (sex == sex1));*/
        /*String aa = "http://baidu.com.www.ok{3223}jkl{232}sk78kk,{987}ii65";
        String reg = "(\\{[^{}]*\\})+";
        String[] as = aa.split(reg);
        System.out.println("===  === " + 1);*/
        /*String word = "sk,al";
        String w[] = word.split(",");
        System.out.println(w);*/

        /*List<Dog> list1 = new ArrayList<>();
        list1.add(new Dog("kk","12"));
        list1.add(new Dog("mm","11"));
        list1.add(new Dog("kj","12"));
        list1.add(new Dog("mm","12"));

        Iterator<Dog> iterator = list1.iterator();
        while (iterator.hasNext()){
            Dog dog = iterator.next();
            if(dog.getName() == "mm") iterator.remove();
        }


        Set<Dog> set = new HashSet<>(list1);

        List<Dog> list2 = new ArrayList<>();
        list2.add(new Dog("mm","22"));
        list2.add(new Dog("kj","1"));
        list1.removeAll(list2);
        System.out.println();*/

//        String word = "我爱#￥赵，欣,哈哈haha my wife 。123";
////        String rex = "[^\\dA-Za-z\\u3007\\u3400-\\u4DB5\\u4E00-\\u9FCB\\uE815-\\uE864]";

//        String word = "序列号:191766     验证码:124572";
//        String rex = "[\\s]+";
//        String[] list = word.split(rex);

//        String code = "1212766 |1252372";
        /*String name = "序列号|验证码";
        String[] codes = code.split("[|]");
        String[] names = name.split("[|]");
        for (int i = 0; i < 2; i++) {
            System.out.println((codes[i]));
            System.out.println((names[i]));
        }*/
//        String val = code;
//        code = code.replace("66", "99");
//        System.out.println(val + "  " + code);
//        File file = new File("D://eula.2052.txt");
//        System.out.println(file.getAbsoluteFile());
//        System.out.println(file.getAbsolutePath());
//        insert("D://test.csv", 0, "key1,key2,key3,key4\n");
        List<String> list = new ArrayList<>();
        for (int i = 0; i < 20000; i++) {
            list.add("\"1232skkl29993k\"");
        }
        String word = list.get(1);
        long time1 = System.currentTimeMillis();
        System.out.println(MD5Util.getMD5(word));
        long time2 = System.currentTimeMillis();;
        System.out.println(time2 - time1);
        System.out.println(word);
    }

    public static void insert(String filename, int pos, String insertContent) throws IOException {//pos是插入的位置
        File tmp = File.createTempFile("tmp", null);
        tmp.deleteOnExit();
        RandomAccessFile raf = new RandomAccessFile(filename, "rw");
        FileOutputStream tmpOut = new FileOutputStream(tmp);
        FileInputStream tmpIn = new FileInputStream(tmp);
        raf.seek(pos);//首先的话是0
        byte[] buf = new byte[64];
        int hasRead = 0;
        while ((hasRead = raf.read(buf)) > 0) {
            //把原有内容读入临时文件
            tmpOut.write(buf, 0, hasRead);

        }
        raf.seek(pos);
        raf.write(insertContent.getBytes());
        //追加临时文件的内容
        while ((hasRead = tmpIn.read(buf)) > 0) {
            raf.write(buf, 0, hasRead);
        }
    }

    public Animal doWithAnimal(Animal animal) {
        animal.setLegNum("12");
        return animal;
    }

    /**
     * 比较版本号的大小,前者大则返回一个正数,后者大返回一个负数,相等则返回0
     *
     * @param version1
     * @param version2
     */
    public static int compareVersion(String version1, String version2) {
        String[] versionArray1 = version1.split("\\.");//注意此处为正则匹配，不能用.；
        String[] versionArray2 = version2.split("\\.");
        int idx = 0;
        int minLength = Math.min(versionArray1.length, versionArray2.length);//取最小长度值
        int diff = 0;
        while (idx < minLength
                && (diff = versionArray1[idx].length() - versionArray2[idx].length()) == 0//先比较长度
                && (diff = versionArray1[idx].compareTo(versionArray2[idx])) == 0) {//再比较字符
            ++idx;
        }
        //如果已经分出大小，则直接返回，如果未分出大小，则再比较位数，有子版本的为大；
        diff = (diff != 0) ? diff : versionArray1.length - versionArray2.length;
        return diff;
    }

    public static void heeh(String bool) {
        bool = "skkk";
    }

    public static Dog getDog(Map<String, String> map) {
        Dog dog = new Dog();
        dog.setName(map.get("ss"));
        map.clear();
        return dog;
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



    /*public static String calculateSign(Map<String, String> map) {
        String result = "";
        try {
            List<Map.Entry<String, String>> infoIds = new ArrayList<Map.Entry<String, String>>(map.entrySet());
            // 对所有传入参数按照字段名的 ASCII 码从小到大排序（字典序）
            Collections.sort(infoIds, new Comparator<Map.Entry<String, String>>() {

                public int compare(Map.Entry<String, String> o1, Map.Entry<String, String> o2) {
                    return (o1.getKey()).toString().compareTo(o2.getKey());
                }
            });

            // 构造签名键值对的格式
            StringBuilder sb = new StringBuilder();
            for (Map.Entry<String, String> item : infoIds) {
                if (item != null) {
                    String key = item.getKey();
                    String val = item.getValue();
                    if (exist(key) && exist(val) && !"sign".equals(key)) {
                        sb.append(key + "=" + val + "&");
                    }
                }
            }
//            sb.append("key=" + Config.getString("qmm.key"));
            sb.append("key=" + "7sERsqfBD^Y8eyrUixJ7W!MY1MeT$pY7");
            result = sb.toString();

            //进行MD5加密
            result = DigestUtils.md5Hex(result);
        } catch (Exception e) {
            return null;
        }
        return result;
    }*/
}