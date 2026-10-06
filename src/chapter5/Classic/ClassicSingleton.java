package chapter5.Classic;

/**
 * [1단계: 고전적 싱글톤 (Classic Singleton)]
 * 
 * 특징:
 * - 게으른 인스턴스화 (Lazy Initialization): 실제로 객체가 필요한 순간(getInstance 호출)까지 인스턴스 생성을 미룬다.
 * - 단점: 멀티스레드 환경에서 안전하지 않음 (Thread-unsafe).
 *   여러 스레드가 동시에 if (uniqueInstance == null) 조건을 평가하면
 *   서로 다른 인스턴스가 2개 이상 생성되는 Race Condition(경쟁 상태)이 발생한다.
 */
public class ClassicSingleton {

    private static ClassicSingleton uniqueInstance;

    private ClassicSingleton() {
        System.out.println("-> [ClassicSingleton] 인스턴스가 생성되었습니다. (Hash: " + System.identityHashCode(this) + ")");
    }

    public static ClassicSingleton getInstance() {
        if (uniqueInstance == null) {
            // 멀티스레드 환경에서 동시에 여기까지 진입하면 복수의 인스턴스가 생성되는 버그 발생!
            try {
                // 동시성 문제를 명확히 확인하기 위해 약간의 딜레이 부여
                Thread.sleep(10);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            uniqueInstance = new ClassicSingleton();
        }
        return uniqueInstance;
    }

    public static void resetForTesting() {
        uniqueInstance = null;
    }
}
