package org.example.practicecode.designpattern.actionType.observer;

/**
 * @author wangyulin
 * @date 2020/6/2
 */
public interface Observer {
    void update(float temperature, float humidity, float pressure);
}