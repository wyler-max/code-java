package org.example.practice.practicecode.skill.designpattern.actionType.observer;

/**
 * @author wangyulin
 * @date 2020/6/2
 */
public interface Subject {
    void addObserver(Observer o);

    void removeObserver(Observer o);

    void notifyObservers();
}
