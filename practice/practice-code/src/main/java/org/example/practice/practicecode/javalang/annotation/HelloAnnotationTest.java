package org.example.practice.practicecode.javalang.annotation;

import java.lang.reflect.Method;

import org.example.practice.practicecode.javalang.annotation.anno.Hello;

/**
 * 测试注解
 */
public class HelloAnnotationTest {
    @Hello("hello")
    public static void main(String[] args) throws NoSuchMethodException {
        Class cls = HelloAnnotationTest.class;
        Method method = cls.getMethod("main", String[].class);
        Hello hello = method.getAnnotation(Hello.class);
        System.out.println(hello.value());
    }
}
