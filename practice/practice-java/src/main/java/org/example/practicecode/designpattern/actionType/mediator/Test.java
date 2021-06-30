package org.example.practicecode.designpattern.actionType.mediator;

/**
 * 中介者模式，是用来降低多个对象和类之间的通信复杂性。
 *
 * 这种模式提供了一个中介类，该类通常处理不同类之间的通信，并支持松耦合，使代码易于维护。
 */
public class Test {
    public static void main(String[] args) {
        User robert = new User("Robert");
        User john = new User("John");

        robert.sendMessage("Hi! John!");
        john.sendMessage("Hello! Robert!");
    }
}
