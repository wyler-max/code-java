package org.example.practicecode.designpattern.observer.origin;

import com.example.designpattern.observer.origin.WeatherData;

/**
 * @author wangyulin
 * @date 2020/6/2
 */
public class Test {
    public static void main(String[] args) {
        WeatherData weatherData = new WeatherData();
        weatherData.setWeathData(80, 65, 30.4f);
        //weatherData.setWeathData(82, 70, 29.2f);
        //weatherData.setWeathData(78, 90, 29.2f);

        weatherData.currentConditionDisplay.display();
        //weatherData.statisticsDisplay.display();
        //weatherData.forecastDisplay.display();
    }
}
