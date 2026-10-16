package chapter6.Receiver;

/**
 * [Receiver: 차고 문 제어 클래스]
 * 차고 문 개폐기 제조사에서 제공한 제어 클래스.
 * 문 올리기(up), 내리기(down), 정지(stop), 차고 조명 제어 기능을 가진다.
 */
public class GarageDoor {
    private final String location;

    public GarageDoor(String location) {
        this.location = location;
    }

    public void up() {
        System.out.println("🚗 [" + location + " 차고 문] 차고 문이 열립니다 (Up).");
    }

    public void down() {
        System.out.println("🔒 [" + location + " 차고 문] 차고 문이 닫힙니다 (Down).");
    }

    public void stop() {
        System.out.println("⏸️ [" + location + " 차고 문] 차고 문 작동이 멈췄습니다.");
    }

    public void lightOn() {
        System.out.println("💡 [" + location + " 차고 문] 차고 내부 조명이 켜졌습니다.");
    }

    public void lightOff() {
        System.out.println("🌑 [" + location + " 차고 문] 차고 내부 조명이 꺼졌습니다.");
    }
}
