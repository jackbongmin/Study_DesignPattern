package chapter6.Receiver;

/**
 * [Receiver: TV 제어 클래스]
 * 매크로 커맨드(파티 모드) 시나리오에서 함께 제어되는 TV 가전 클래스.
 */
public class TV {
    private final String location;
    private int channel;

    public TV(String location) {
        this.location = location;
    }

    public void on() {
        System.out.println("📺 [" + location + " TV] 전원이 켜졌습니다.");
    }

    public void off() {
        System.out.println("⬛ [" + location + " TV] 전원이 꺼졌습니다.");
    }

    public void setInputChannel() {
        this.channel = 3;
        System.out.println("📡 [" + location + " TV] 입력 채널이 DVD(3번)로 설정되었습니다.");
    }

    public int getChannel() {
        return channel;
    }
}
