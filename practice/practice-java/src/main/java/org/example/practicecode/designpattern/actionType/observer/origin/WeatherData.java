package org.example.practicecode.designpattern.actionType.observer.origin;

/**
 * @author wangyulin
 * @date 2020/6/1
 */
public class WeatherData {

    private float temperature;
    private float humidity;
    private float pressure;

    CurrentConditionDisplay currentConditionDisplay;
    StatisticsDisplay statisticsDisplay;
    ForecastDisplay forecastDisplay;

    public WeatherData() {
        currentConditionDisplay = new CurrentConditionDisplay();
        statisticsDisplay = new StatisticsDisplay();
        forecastDisplay = new ForecastDisplay();
    }

    public void measurementChanged() {
        float temp = getTemperature();
        float humidity = getHumidity();
        float pressure = getPressure();

        currentConditionDisplay.update(temp, humidity, pressure);
        statisticsDisplay.update(temp, humidity, pressure);
        currentConditionDisplay.update(temp, humidity, pressure);

    }

    public float getTemperature() {
        return this.temperature;
    }

    public void setTemperature(float temperature) {
        this.temperature = temperature;
    }

    public float getHumidity() {
        return humidity;
    }

    public void setHumidity(float humidity) {
        this.humidity = humidity;
    }

    public float getPressure() {
        return pressure;
    }

    public void setPressure(float pressure) {
        this.pressure = pressure;
    }

    public void setWeathData(float temperature, float humidity, float pressure) {
        this.temperature = temperature;
        this.humidity = humidity;
        this.pressure = pressure;
        measurementChanged();
    }
}
