package org.example.practice.practicespring.annotation.importDir;

import org.springframework.context.annotation.Configuration;

/**
 * @author wangyulin
 * @date 2021/6/16
 */
@Configuration
public class TestB {

    public void run(String str) {
        System.out.println(str);
    }

    public void printName() {
        System.out.println("类名： " + Thread.currentThread().getStackTrace()[1].getClassName());
    }
}
