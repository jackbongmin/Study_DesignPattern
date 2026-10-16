package chapter6.Receiver;

/**
 * [Receiver: 조명 제어 클래스]
 * 가전제품 제조사에서 제공한 조명 하드웨어 제어 클래스.
 * 켜기(on), 끄기(off), 밝기 조절(dim) 기능을 제공한다.
 */
public class Light {
    private final String location;
    private int level;

    public Light(String location) {
        this.location = location;
        this.level = 0;
    }

    public void on() {
        level = 100;
        System.out.println("💡 [" + location + " 조명] 조명이 켜졌습니다. (밝기: 100%)");
    }

    public void off() {
        level = 0;
        System.out.println("🌑 [" + location + " 조명] 조명이 꺼졌습니다. (밝기: 0%)");
    }

    public void dim(int level) {
        this.level = level;
        if (level == 0) {
            off();
        } else {
            System.out.println("✨ [" + location + " 조명] 조명 밝기가 " + level + "%로 조절되었습니다.");
        }
    }

    public int getLevel() {
        return level;
    }

    public String getLocation() {
        return location;
    }
}
