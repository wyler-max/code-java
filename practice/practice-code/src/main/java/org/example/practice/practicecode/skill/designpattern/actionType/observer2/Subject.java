package org.example.practice.practicecode.skill.designpattern.actionType.observer2;

/**
 * 被观察者接口
 */
public interface Subject {
    void registerObserver(Observer observer);

    void removeObserver(Observer observer);

    void notifyObserver();
}
