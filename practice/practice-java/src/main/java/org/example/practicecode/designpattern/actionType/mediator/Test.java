package org.example.practicecode.designpattern.actionType.mediator;

/**
 * 中介者模式测试类
 *
 * 使用 User 对象来显示他们之间的通信。
 */
public class Test {
    public static void main(String[] args) {
        User robert = new User("Robert");
        User john = new User("John");

        robert.sendMessage("Hi! John!");
        john.sendMessage("Hello! Robert!");
    }
}
