package chapter6.Receiver;

/**
 * [Receiver: 오디오(Stereo) 제어 클래스]
 * 전원 제어뿐만 아니라 CD/DVD/라디오 모드 설정 및 볼륨 조절 등
 * 복합적인 초기화 절차가 필요한 가전제품 클래스.
 */
public class Stereo {
    private final String location;
    private int volume;

    public Stereo(String location) {
        this.location = location;
        this.volume = 0;
    }

    public void on() {
        System.out.println("🎵 [" + location + " 오디오] 전원이 켜졌습니다.");
    }

    public void off() {
        System.out.println("🔇 [" + location + " 오디오] 전원이 꺼졌습니다.");
    }

    public void setCd() {
        System.out.println("💿 [" + location + " 오디오] CD 재생 모드로 설정되었습니다.");
    }

    public void setDvd() {
        System.out.println("📀 [" + location + " 오디오] DVD 재생 모드로 설정되었습니다.");
    }

    public void setRadio() {
        System.out.println("📻 [" + location + " 오디오] 라디오 모드로 설정되었습니다.");
    }

    public void setVolume(int volume) {
        this.volume = volume;
        System.out.println("🔊 [" + location + " 오디오] 볼륨이 " + volume + "(으)로 설정되었습니다.");
    }

    public int getVolume() {
        return volume;
    }
}
