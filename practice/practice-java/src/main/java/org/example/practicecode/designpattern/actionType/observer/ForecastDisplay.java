package org.example.practicecode.designpattern.actionType.observer;

/**
 * @author wangyulin
 * @date 2020/6/2
 */
public class ForecastDisplay implements Observer, DisplayElement {

    private float temperature;
    private float humidity;
    private float pressure;

    private Subject weatherData;

    public ForecastDisplay(Subject weatherData) {
        this.weatherData = weatherData;
        weatherData.addObserver(this);
    }

    @Override
    public void display() {
        System.out.println("明日预报：\ntemperature: " + temperature +
                "; humidity: " + humidity +
                "; pressure: " + pressure);
    }

    @Override
    public void update(float temperature, float humidity, float pressure) {
        this.temperature = temperature;
        this.humidity = humidity;
        this.pressure = pressure;
        display();
    }
}
