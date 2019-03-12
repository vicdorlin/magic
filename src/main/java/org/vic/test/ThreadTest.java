package org.vic.test;

import com.alibaba.fastjson.JSON;

import java.util.ArrayList;
import java.util.List;

/**
 * @author vicdor
 * @create 2018-05-17 18:30
 */
public class ThreadTest {
    public static List<Integer> list = new ArrayList<>();

    public static void main(String[] args) {
        Thread thread1 = new Thread(new Runnable(){
            @Override
            public void run() {
                for (int j = 0; j < 10; j++) {
                    printNum(j);
                }
            }
        });
        Thread thread2 = new Thread(new Runnable(){
            @Override
            public void run() {
                for (int i = 0; i < 10; i++) {
                    printNum(i+1000000);
                }
            }
        });
        thread1.start();
        thread2.start();

    }

    public static synchronized void printNum(int num){
        list.add(num);
        System.out.println(JSON.toJSON(list));
    }
}    
