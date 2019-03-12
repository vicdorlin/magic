package org.vic.test;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializeConfig;
import org.vic.enums.DateFormatEnum;
import org.vic.test.domain.Beer;
import org.vic.util.EnumUtils;

import java.lang.reflect.InvocationTargetException;

/**
 * @author vicdor
 * @create 2016-07-11 02:48
 */
public class EnumTest {
    public static void main(String[] args) throws InvocationTargetException, IllegalAccessException {
//        System.out.println(EnumUtils.valueOf(DateFormatEnum.class,"yyyy-MM"));
//        List<Ox> oxen = new ArrayList<Ox>();
//        oxen.add(new Ox("小玩啊",12));
//        oxen.add(new Ox("你好",22));
//        Ox ox = new Ox("你好",22);
//        System.out.println("===  === " + oxen.contains(ox));
//        System.out.println("===  === " + ox.getClass().getSimpleName());
        /*Stack<String> stack = new Stack<>();
        stack.push("p1");
        stack.push("p2");
        stack.push("p3");
        System.out.println("=== poped === " + stack.pop());
        System.out.println(stack.peek());
        System.out.println(stack);*/

//        System.out.println("===  === " + checkPwd("孬种的样子吖吖22"));

//        String word = "22";
//        String[] words = word.split(",");
//        System.out.println("===  === " + words.length);
//        System.out.println(calBloodLeft(100.0, 9, 0.08));
        Beer beer = new Beer();
        beer.setName("牛");
        beer.setDateFormatEnum(DateFormatEnum.$DEFAULT);
        System.out.println(JSON.toJSONString(beer));
        System.out.println(EnumUtils.toJson(beer.getDateFormatEnum()));
        SerializeConfig config = new SerializeConfig();
        config.configEnumAsJavaBean(DateFormatEnum.class);
        System.out.println(JSON.toJSONString(beer,config));
//        String word = "{\"dateFormatEnum\":{\"value\":\"yyyy/MM/dd HH:mm:ss\"},\"name\":\"牛\"}";
//        Beer b = JSON.toJavaObject(JSONObject.parseObject(word),Beer.class);
//        System.out.println(b.getDateFormatEnum());
    }

    public static boolean checkPwd(String pwd) {
        return pwd != null && pwd.matches("^[\\x21-\\x7e\\u4E00-\\u9FA5A-Za-z0-9_\\·]{1,8}$");
    }

    public static double calBloodLeft(double startBlood, int times, double maskHurt) {
        if (times == 1) {
            return startBlood * (1 - maskHurt);
        }
        return calBloodLeft(startBlood * (1 - maskHurt), --times, maskHurt);
    }
}
