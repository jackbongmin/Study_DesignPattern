package chapter2;

import chapter2.Actor.CurrentConditionsDisplay;
import chapter2.Actor.ForecastDisplay;
import chapter2.Actor.StatisticsDisplay;
import chapter2.Actor.WeatherData;

public class WeatherStation {

    public static void main(String[] args) {
        WeatherData weatherData = new WeatherData();

        CurrentConditionsDisplay currentDisplay = new CurrentConditionsDisplay(weatherData);
        StatisticsDisplay statisticsDisplay = new StatisticsDisplay(weatherData);
        ForecastDisplay forecastDisplay = new ForecastDisplay(weatherData);

        System.out.println("=== 첫 번째 기상 데이터 업데이트 ===");
        weatherData.setMeasurements(80, 65, 30.4f);

        System.out.println("\n=== 두 번째 기상 데이터 업데이트 ===");
        weatherData.setMeasurements(82, 70, 29.2f);

        System.out.println("\n=== 세 번째 기상 데이터 업데이트 ===");
        weatherData.setMeasurements(78, 90, 29.2f);
    }
}
