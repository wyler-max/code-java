package org.example.practice.practicecode.skill.designpattern.createType.builder;

import java.util.Random;

/**
 * 构造器不要用单例，否则会有多线程问题
 */
public class UserBuilderSingleton {

    private static UserBuilderSingleton instance = null;
    private UserContext userContext;

    public UserBuilderSingleton() {
        this.userContext = new UserContext();
    }

    public static UserBuilderSingleton getInstance() {
        if (instance == null) {
            synchronized (UserBuilderSingleton.class) {
                instance = new UserBuilderSingleton();
            }
        }
        return instance;
    }

    public UserBuilderSingleton setUserName(String name) {
        // System.out.println("name=");
        this.userContext.setName(name);
        try {
            Thread.sleep(new Random().nextInt(3));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return this;
    }

    public UserBuilderSingleton setUserGender(int gender) {
        // System.out.println("do action b");
        this.userContext.setGender(gender);
        return this;
    }

    public UserBuilderSingleton setUserAge(int age) {
        // System.out.println("do action c");
        this.userContext.setAge(age);
        return this;
    }

    public UserContext getUserContext() {
        return this.userContext;
    }
}
