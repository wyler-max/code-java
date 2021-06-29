package org.example.practicecode.designpattern.actionType.observer.origin;

/**
 * @author wangyulin
 * @date 2020/6/2
 */
public class ForecastDisplay implements Display {
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
        System.out.println("明日预报：\ntemperature: " + temperature +
                "; humidity: " + humidity +
                "; pressure: " + pressure);
    }
}
