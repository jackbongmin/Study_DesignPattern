package chapter2.Actor;

import chapter2.Interface.Display.DisplayElement;
import chapter2.Interface.Observer.Observer;
import chapter2.Interface.Subject.Subject;

public class StatisticsDisplay implements Observer, DisplayElement {
    private float maxTemp = 0.0f;
    private float minTemp = 200;
    private float tempSum = 0.0f;
    private int numReadings;
    private Subject weatherData;

    public StatisticsDisplay(Subject weatherData) {
        this.weatherData = weatherData;
        weatherData.registerObserver(this);
    }

    @Override
    public void update(float temp, float humidity, float pressure) {
        tempSum += temp;
        numReadings++;

        if (temp > maxTemp) {
            maxTemp = temp;
        }

        if (temp < minTemp) {
            minTemp = temp;
        }

        display();
    }

    @Override
    public void display() {
        System.out.println("기상 통계: 평균/최고/최저 온도 = " + (tempSum / numReadings) + "/" + maxTemp + "/" + minTemp);
    }
}
