package org.example.practice.practicecode.javalang.grammarSenior.genericity;

/**
 * 测试泛型方法
 */
public class TestGenericMethod {

    class Fruit {
        @Override
        public String toString() {
            return "fruit";
        }
    }

    class Apple extends Fruit {
        @Override
        public String toString() {
            return "apple";
        }
    }

    class Person {
        @Override
        public String toString() {
            return "person";
        }
    }

    class GeneriteTest<T> {
        public void show1(T t) {
            System.out.println(t.toString());
        }

        public <E> void show2(E t) {
            System.out.println(t.toString());
        }

        public <T> void show3(T t) {
            System.out.println(t.toString());
        }
    }
}
