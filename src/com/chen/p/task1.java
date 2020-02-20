package com.chen.p;

//1) Написать метод который принимает 2 строки - a, b.
// Метод должне вернуть количество символов которые у них различаются.
// Если строки разной длины то вернуть -1.
//
//        Примеры:
//
//        a="asdf", b="qwerty" => -1
//        a="abb", b="bba" => 0
//        a="abc", b="dca" => 1
//        a="aaa", b="fga" => 2
//        a="abc", b="dfg" => 3

import java.util.HashSet;

public class task1 {

    public static void main(String[] args) {
        String a = "asdasdasdasasfasd";
        String b = "x";
        String c = "abcdefghijklmnopq";
        System.out.println(compare(a, a));
        System.out.println(compare(a, b));
        System.out.println(compare(a, c));
    }

    public static int compare(String a, String b) {
        if (a.length() != b.length()) {
            return -1;
        }
        String s = a + b;
        HashSet<Character> setA = new HashSet<>();
        HashSet<Character> setB = new HashSet<>();
        for (int i = 0; i < a.length(); i++) {
            setA.add(a.charAt(i));
        }
        for (int j = 0; j < b.length(); j++) {
            setB.add(b.charAt(j));
        }
        return Math.max(setA.size(), setB.size()) - Math.min(setA.size(), setB.size());
    }
}
