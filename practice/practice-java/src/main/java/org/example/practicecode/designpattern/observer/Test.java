package org.example.practicecode.designpattern.observer;

/**
 * @author wangyulin
 * @date 2020/6/2
 */
public class Test {
    public static void main(String[] args) {
        WeatherData weatherData = new WeatherData();
        CurrentConditionDisplay currentConditionDisplay = new CurrentConditionDisplay(weatherData);
        StatisticsDisplay statisticsDisplay = new StatisticsDisplay(weatherData);
        ForecastDisplay forecastDisplay = new ForecastDisplay(weatherData);

        weatherData.setMeasurements(80, 65, 30.4f);
        System.out.println("===================");
        weatherData.setMeasurements(82, 70, 29.2f);
        System.out.println("===================");
        weatherData.setMeasurements(78, 90, 29.2f);

    }
}
