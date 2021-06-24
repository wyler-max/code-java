package org.example.practice.practicespring.annotation.importDir;

/**
 * @author wangyulin
 * @date 2021/6/16
 */
public class TestC {

    public void run(String str) {
        System.out.println(str);
    }

    public void printName() {
        System.out.println("类名： " + Thread.currentThread().getStackTrace()[1].getClassName());
    }
}
