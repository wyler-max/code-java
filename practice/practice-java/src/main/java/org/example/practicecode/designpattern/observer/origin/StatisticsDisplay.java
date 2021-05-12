package org.example.practicecode.designpattern.observer.origin;

/**
 * @author wangyulin
 * @date 2020/6/1
 */
public class StatisticsDisplay implements Display {

    private float temperature;
    private float humidity;
    private float pressure;

    @Override
    public void update(float temperature, float humidity, float pressure) {
        this.temperature = temperature;
        this.humidity = humidity;
        this.pressure = pressure;
    }

    @Override
    public void display() {
        System.out.println("今日分析：\r\ntemperature: " + this.temperature +
                "; humidity: " + this.humidity +
                "; pressure: " + this.pressure);
    }
}
