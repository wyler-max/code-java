package org.example.practice.practicecode.skill.designpattern.actionType.observer;

/**
 * 观察者模式，当对象间存在一对多关系时，修改一个对象，自动通知依赖它的对象。 </br>
 * 多个观察者关注同一个目标对象的变化，一旦产生变化，通知所有的观察者。
 *
 * setData -> notifyObservers -> observer.update
 */
public class Test {
    public static void main(String[] args) {
        WeatherData weatherData = new WeatherData();
        // 添加观察者
        new CurrentConditionDisplay(weatherData);
        new ForecastDisplay(weatherData);
        new StatisticsDisplay(weatherData);
        // 修改数据
        System.out.println("更新天气数据 2020-02-02");
        weatherData.setMeasurements(80, 65, 30.4f);
        System.out.println("===================");

        System.out.println("更新天气数据 2020-03-02");
        weatherData.setMeasurements(82, 70, 29.2f);
        System.out.println("===================");

        System.out.println("更新天气数据 2020-04-02");
        weatherData.setMeasurements(78, 90, 29.2f);
    }
}
