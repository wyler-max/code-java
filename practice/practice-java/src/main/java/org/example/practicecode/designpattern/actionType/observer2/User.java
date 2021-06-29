package org.example.practicecode.designpattern.actionType.observer2;

/**
 * 具体观察者，微信公众号的具体观察者为用户User
 */
public class User implements Observer {

    private String name;
    private String message;

    public User(String name) {
        this.name = name;
    }

    @Override
    public void update(String message) {
        this.message = message;
        read();
    }

    public void read() {
        System.out.println(name + " 收到消息： " + message);
    }
}
