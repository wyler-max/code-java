package org.example.springstart;

import java.lang.reflect.Method;

/**
 * 测试JDK8
 */
public class JDK8Test {
    //循环次数定义：10亿次
    private static final int loopCnt = 1000 * 1000 * 1000;

    public static void main(String[] args) throws Exception {
        //输出jdk的版本
        System.out.println("java.version=" + System.getProperty("java.version"));
        t1();
        t2();
        t3();
    }

    // 每次重新生成对象
    public static void t1() {
        long s = System.currentTimeMillis();
        for (int i = 0; i < loopCnt; i++) {
            Person p = new Person();
            p.setAge(31);
        }
        long e = System.currentTimeMillis();
        System.out.println("循环10亿次创建对象的时间：" + (e - s));
    }

    // 同一个对象
    public static void t2() {
        long s = System.currentTimeMillis();
        Person p = new Person();
        for (int i = 0; i < loopCnt; i++) {
            p.setAge(32);
        }
        long e = System.currentTimeMillis();
        System.out.println("循环10亿次给同一对象赋值的时间： " + (e - s));
    }

    //使用反射创建对象
    public static void t3() throws Exception {
        long s = System.currentTimeMillis();
        Class<Person> c = Person.class;
        Person p = c.newInstance();
        Method m = c.getMethod("setAge", Integer.class);
        for (int i = 0; i < loopCnt; i++) {
            m.invoke(p, 33);
        }
        long e = System.currentTimeMillis();
        System.out.println("循环10亿次反射创建对象的时间：" + (e - s));
    }

    static class Person {
        private int age = 20;

        public int getAge() {
            return age;
        }

        public void setAge(Integer age) {
            this.age = age;
        }
    }
}
