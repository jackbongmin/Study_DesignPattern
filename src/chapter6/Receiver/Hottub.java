package chapter6.Receiver;

/**
 * [Receiver: 온수 욕조(Hottub) 제어 클래스]
 * 매크로 커맨드(파티 모드) 시나리오에서 물 데우기, 거품 분사, 순환 등을 수행하는 고급 가전 클래스.
 */
public class Hottub {
    private boolean on;
    private int temperature;

    public Hottub() {
        this.on = false;
        this.temperature = 36;
    }

    public void on() {
        on = true;
    }

    public void off() {
        on = false;
    }

    public void circulate() {
        if (on) {
            System.out.println("🫧 [온수 욕조] 물 순환 및 버블 기능이 작동 중입니다!");
        }
    }

    public void jetsOn() {
        if (on) {
            System.out.println("🌊 [온수 욕조] 마사지 제트 물줄기가 켜졌습니다.");
        }
    }

    public void jetsOff() {
        if (on) {
            System.out.println("💤 [온수 욕조] 마사지 제트 물줄기가 꺼졌습니다.");
        }
    }

    public void setTemperature(int targetTemp) {
        if (targetTemp > temperature) {
            System.out.println("🔥 [온수 욕조] 물 온도를 " + temperature + "°C에서 " + targetTemp + "°C로 가열합니다.");
        } else if (targetTemp < temperature) {
            System.out.println("❄️ [온수 욕조] 물 온도를 " + temperature + "°C에서 " + targetTemp + "°C로 식힙니다.");
        }
        this.temperature = targetTemp;
    }
}
