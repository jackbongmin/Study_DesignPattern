package chapter5.ThreadSafe;

/**
 * [3단계: 즉시 로딩 싱글톤 (Eager Initialization Singleton)]
 * 
 * 특징:
 * - 정적 초기화 블록(static initializer)에서 인스턴스를 즉시 생성한다.
 * - JVM이 클래스를 메모리에 로딩할 때 단 한 번만 생성되므로 완벽히 스레드 안전하다.
 * - 장점: 별도의 동기화(synchronized) 블록이나 복잡한 락 메커니즘이 없어 호출 속도가 매우 빠르고 단순하다.
 * - 단점: 지연 로딩(Lazy Loading) 불가.
 *   해당 클래스가 로딩되는 순간 바로 메모리를 차지하므로, 객체 생성 비용이 매우 크거나(무거운 DB 커넥션 등)
 *   실제 프로그램 실행 중에 사용되지 않는다면 불필요한 메모리 및 리소스 낭비가 발생한다.
 */
public class EagerSingleton {

    // 클래스가 로딩될 때 즉시 인스턴스를 생성함
    private static final EagerSingleton UNIQUE_INSTANCE = new EagerSingleton();

    private EagerSingleton() {
        System.out.println("-> [EagerSingleton] 클래스 로딩 시 인스턴스가 즉시 생성되었습니다. (Hash: " + System.identityHashCode(this) + ")");
    }

    public static EagerSingleton getInstance() {
        return UNIQUE_INSTANCE;
    }
}
