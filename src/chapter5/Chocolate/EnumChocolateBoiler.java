package chapter5.Chocolate;

/**
 * [Enum 기반 초콜릿 보일러]
 * Joshua Bloch(Effective Java)와 헤드퍼스트 최신 개정판에서
 * 가장 권장하는 싱글톤 구현 기법.
 * 
 * - 직렬화/역직렬화 시 새로운 인스턴스가 생성되는 문제 완벽 해결
 * - 리플렉션 공격(Reflection Attack)을 통한 private 생성자 강제 호출 원천 차단
 * - JVM 언어 명세상 스레드 안전성 100% 보장
 */
public enum EnumChocolateBoiler {
    UNIQUE_INSTANCE;

    private boolean empty;
    private boolean boiled;

    // enum의 생성자는 private이며 클래스 로드 시 1회만 실행됨
    EnumChocolateBoiler() {
        empty = true;
        boiled = false;
    }

    public synchronized void fill() {
        if (isEmpty()) {
            empty = false;
            boiled = false;
            System.out.println("🍫 [EnumBoiler fill] 원료를 채웠습니다.");
        } else {
            System.out.println("⚠️ [EnumBoiler fill] 보일러가 이미 차 있습니다.");
        }
    }

    public synchronized void boil() {
        if (!isEmpty() && !isBoiled()) {
            System.out.println("🔥 [EnumBoiler boil] 원료를 가열했습니다.");
            boiled = true;
        } else {
            System.out.println("⚠️ [EnumBoiler boil] 가열 조건 불만족 (empty=" + empty + ", boiled=" + boiled + ")");
        }
    }

    public synchronized void drain() {
        if (!isEmpty() && isBoiled()) {
            System.out.println("🍫 [EnumBoiler drain] 초콜릿을 배출했습니다.");
            empty = true;
        } else {
            System.out.println("⚠️ [EnumBoiler drain] 배출 조건 불만족 (empty=" + empty + ", boiled=" + boiled + ")");
        }
    }

    public synchronized boolean isEmpty() {
        return empty;
    }

    public synchronized boolean isBoiled() {
        return boiled;
    }
}
