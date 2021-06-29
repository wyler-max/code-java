package org.example.practicecode.designpattern.actionType.observer2;

import com.google.common.collect.Lists;

import java.util.List;

/**
 * 被观察者实现类
 * 1.实现了 Observerable 接口
 * 2.维护一个观察者的 List 集合，用户注册和通知
 */
public class WechatServer implements Subject{

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
