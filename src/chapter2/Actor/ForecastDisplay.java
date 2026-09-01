package chapter2.Actor;

import chapter2.Interface.Display.DisplayElement;
import chapter2.Interface.Observer.Observer;
import chapter2.Interface.Subject.Subject;

public class ForecastDisplay implements Observer, DisplayElement {
    private float currentPressure = 29.92f;
    private float lastPressure;
    private Subject weatherData;

    public ForecastDisplay(Subject weatherData) {
        this.weatherData = weatherData;
        weatherData.registerObserver(this);
    }

    @Override
    public void update(float temp, float humidity, float pressure) {
        lastPressure = currentPressure;
        currentPressure = pressure;

        display();
    }

    @Override
    public void display() {
        System.out.print("기상 예보: ");
        if (currentPressure > lastPressure) {
            System.out.println("날씨가 좋아지고 있습니다!");
        } else if (currentPressure == lastPressure) {
            System.out.println("지금과 비슷한 날씨가 유지됩니다.");
        } else if (currentPressure < lastPressure) {
            System.out.println("쌀쌀하고 비가 오는 날씨에 유의하세요.");
        }
    }
}
