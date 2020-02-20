package com.chen.p;

import java.util.Random;

public class task2 {

    public static final Random RANDOM = new Random();

    public static void main(String[] args) {
        // Map<String, String> map = new HashMap<>(); - аналог
        MyMap myMap = new MyHashMap();
        myMap.put("1", "asdf");
        myMap.put("2", "qwert");
        myMap.put("3", "zxcv");
        myMap.put("1", "zzz");
        for (int i = 0; i < 100; i++) {
            myMap.put(String.valueOf(RANDOM.nextInt(2000)), "SSS");
        }
        System.out.println(myMap); // [1=zzz, 2=qwert, 3=zxcv]
        String key = "3";
        // containsKey(key) test
        System.out.println(myMap.containsKey(key));
        // get(key) test
        if (myMap.containsKey(key)) {
            System.out.println(myMap.get(key));
        }


        MySet set = new MyHashSet();
        set.add("asdf");
        set.add("qwer");
        set.add("zxcv");
        set.add("asdf");
        System.out.println(set); // [zxcv, qwer, asdf]
    }


}
