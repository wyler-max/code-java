package org.example.practice.practicespring.annotation.importAnnotation;

import org.springframework.context.annotation.Configuration;

/**
 * 会自动注册该类，
 */
@Configuration
public class ClassB {

    public void run(String str) {
        System.out.println(str);
    }

    public void printName() {
        System.out.println("类名： " + Thread.currentThread().getStackTrace()[1].getClassName());
    }
}
