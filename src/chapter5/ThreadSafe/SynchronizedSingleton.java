package chapter5.ThreadSafe;

/**
 * [2단계: 동기화 메서드 싱글톤 (Synchronized Method Singleton)]
 * 
 * 특징:
 * - getInstance() 메서드에 synchronized 키워드를 붙여 한 번에 하나의 스레드만 진입하도록 강제.
 * - 장점: 스레드 안전성(Thread-Safe)이 보장되며 구현이 매우 단순함.
 * - 단점: 성능 저하 (락 경합 오버헤드).
 *   인스턴스가 이미 생성된 후에도 매번 getInstance()를 호출할 때마다 불필요하게 락을 획득/반환하므로,
 *   빈번하게 호출되는 고성능 시스템에서는 성능이 최대 100배까지 떨어질 수 있다.
 */
public class SynchronizedSingleton {

    private static SynchronizedSingleton uniqueInstance;

    private SynchronizedSingleton() {
        System.out.println("-> [SynchronizedSingleton] 인스턴스가 생성되었습니다. (Hash: " + System.identityHashCode(this) + ")");
    }

    public static synchronized SynchronizedSingleton getInstance() {
        if (uniqueInstance == null) {
            uniqueInstance = new SynchronizedSingleton();
        }
        return uniqueInstance;
    }
}
