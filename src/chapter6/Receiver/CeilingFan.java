package chapter6.Receiver;

/**
 * [Receiver: 천장 선풍기(CeilingFan) 제어 클래스]
 * 단순 ON/OFF가 아니라 여러 단계의 속도 상태(HIGH, MEDIUM, LOW, OFF)를 가지는 리시버.
 * 커맨드 패턴에서 '상태 기반 작업 취소(Stateful Undo)'를 구현할 때 핵심적으로 사용된다.
 */
public class CeilingFan {
    public static final int HIGH = 3;
    public static final int MEDIUM = 2;
    public static final int LOW = 1;
    public static final int OFF = 0;

    private final String location;
    private int speed;

    public CeilingFan(String location) {
        this.location = location;
        this.speed = OFF;
    }

    public void high() {
        speed = HIGH;
        System.out.println("🌪️ [" + location + " 천장 선풍기] 속도가 '강(HIGH, 3단)'으로 설정되었습니다.");
    }

    public void medium() {
        speed = MEDIUM;
        System.out.println("💨 [" + location + " 천장 선풍기] 속도가 '중(MEDIUM, 2단)'으로 설정되었습니다.");
    }

    public void low() {
        speed = LOW;
        System.out.println("🍃 [" + location + " 천장 선풍기] 속도가 '약(LOW, 1단)'으로 설정되었습니다.");
    }

    public void off() {
        speed = OFF;
        System.out.println("🛑 [" + location + " 천장 선풍기] 작동이 멈췄습니다 (OFF, 0단).");
    }

    public int getSpeed() {
        return speed;
    }

    public String getLocation() {
        return location;
    }
}
