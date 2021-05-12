package org.example.practicecode.designpattern.observer.origin;

/**
 * @author wangyulin
 * @date 2020/6/1
 */
public interface Display {
    void update(float temperature, float humidity, float pressure);
    void display();
}
