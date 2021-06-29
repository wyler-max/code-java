package org.example.practicecode.designpattern.createType.builder;

/**
 * 构造器不要用单例，否则会有多线程问题
 */
public class UserBuilder {

    private UserContext userContext;

    public UserBuilder() {
        this.userContext = new UserContext();
    }

    public UserBuilder setUserName(String name) {
        // System.out.println("do action a");
        this.userContext.setName(name);
        return this;
    }

    public UserBuilder setUserGender(int gender) {
        // System.out.println("do action b");
        this.userContext.setGender(gender);
        return this;
    }

    public UserBuilder setUserAge(int age) {
        // System.out.println("do action c");
        this.userContext.setAge(age);
        return this;
    }

    public UserContext getUserContext() {
        return this.userContext;
    }
}
