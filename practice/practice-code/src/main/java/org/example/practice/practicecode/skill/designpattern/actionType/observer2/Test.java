package org.example.practice.practicecode.skill.designpattern.actionType.observer2;

/**
 * 观察者模式测试类
 */
public class Test {

    public static void main(String[] args) {
        WechatServer wechatServer = new WechatServer();
        // 注册用户-被观察者
        Observer obs1 = new User("userA");
        Observer obs2 = new User("userB");
        Observer obs3 = new User("userC");
        wechatServer.registerObserver(obs1);
        wechatServer.registerObserver(obs2);
        wechatServer.registerObserver(obs3);
        wechatServer.setInfomation("PHP是最好的语言！");

        System.out.println("======================");
        wechatServer.removeObserver(obs2);
        wechatServer.setInfomation("其实，Java才是世界上最好的语言！");
    }
}
