package org.example.practicejava.javaBase;

import org.junit.Test;

/**
 * 传参
 */
public class TestParams {

    @Test
    public void testDemo1(){
        Demo1 d1 = new Demo1();
        d1.temp = 20;
        System.out.println(d1.temp);
        // 传递的是对象地址，即 d1 的地址
        func(d1);
        System.out.println(d1.temp);
    }
    @Test
    public void testDemo2(){
        String s1 = "hello";
        System.out.println(s1);
        // 传递的是对象地址，即 new String("hello") 对象的地址
        func(s1);
        System.out.println(s1);
    }
    @Test
    public void testDemo3(){
        Demo3 d1 = new Demo3();
        d1.temp = "world";
        System.out.println(d1.temp);
        func(d1);
        System.out.println(d1.temp);
    }

    private static void func(Demo1 d2) {
        /**
         * 1. d2 指向 d1 对象的地址
         * 2. d2.temp（=d1.temp） 对象指向 new
         */
        d2.temp = 30;
    }
    private static void func(String s2) {
        /**
         * 1. s2 指向 s1 = new String("hello") 对象的地址
         * 2. s2 指向 new String("world") 对象的地址
         * 3. 方法内的局部变量 s2 被回收
         */
        s2 = "world";
    }
    private static void func(Demo3 d2) {
        d2.temp = "test";
    }

    private static class Demo1{
        int temp = 10;
    }
    private static class Demo3{
        String temp = "hello";
    }
}
