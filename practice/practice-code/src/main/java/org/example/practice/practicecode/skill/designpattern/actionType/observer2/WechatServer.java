package org.example.practice.practicecode.skill.designpattern.actionType.observer2;

import java.util.List;

import com.google.common.collect.Lists;

/**
 * 被观察者实现类 1.实现了 Observerable 接口 2.维护一个观察者的 List 集合，用户注册和通知
 */
public class WechatServer implements Subject {

    private List<Observer> observerList;
    String message = "";

    public WechatServer() {
        this.observerList = Lists.newArrayList();
    }

    public List<Observer> getObserverList() {
        return observerList;
    }

    public void setObserverList(List<Observer> observerList) {
        this.observerList = observerList;
    }

    @Override
    public void registerObserver(Observer observer) {
        observerList.add(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        if (!observerList.isEmpty()) {
            observerList.remove(observer);
        }
    }

    @Override
    public void notifyObserver() {
        System.out.println("现在，通知所有用户！");
        observerList.forEach(o -> {
            o.update(message);
        });
    }

    public void setInfomation(String str) {
        this.message = str;
        System.out.println("微信服务更新消息：" + str);
        notifyObserver();
    }
}
