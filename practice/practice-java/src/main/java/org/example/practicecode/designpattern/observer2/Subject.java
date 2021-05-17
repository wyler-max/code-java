package org.example.practicecode.designpattern.observer2;

/**
 * 被观察者接口
 */
public interface Subject {
    void registerObserver(Observer observer);

    void removeObserver(Observer observer);

    void notifyObserver();
}
