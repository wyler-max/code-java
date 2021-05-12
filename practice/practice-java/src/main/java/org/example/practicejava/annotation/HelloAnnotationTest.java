package org.example.practicejava.annotation;

import com.example.javalab.annotation.anno.Hello;

import java.lang.reflect.Method;

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
