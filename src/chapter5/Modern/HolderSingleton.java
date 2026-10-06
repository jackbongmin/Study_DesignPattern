package chapter5.Modern;

/**
 * [5단계: Static Inner Holder 싱글톤 (Bill Pugh Idiom)]
 * 
 * 특징:
 * - 정적 내부 클래스(Static Inner Class)를 활용하여 지연 로딩(Lazy Loading)과
 *   스레드 안전성(Thread-Safety)을 동기화(synchronized) 키워드 없이 완벽하게 달성하는 기법.
 * - 메릴랜드 대학교의 Bill Pugh 교수가 제안한 방식으로, 자바 표준에서 가장 널리 권장되는 싱글톤 관용구.
 * 
 * 동작 원리 (JVM 클래스 로더 메커니즘):
 * 1. HolderSingleton 클래스가 처음 JVM에 로드되더라도, 정적 내부 클래스인 SingletonHolder는 메모리에 로드되지 않는다.
 * 2. 외부에서 HolderSingleton.getInstance()를 최초로 호출하는 순간,
 *    SingletonHolder 클래스에 대한 참조가 발생하며 비로소 로딩 및 초기화가 진행된다.
 * 3. JVM의 클래스 초기화 단계는 Java Language Specification(JLS)에 따라
 *    JVM 내부의 초기화 락(Initialization Lock)에 의해 상호 배제(동기화)가 완벽히 보장된다.
 * 4. 결과적으로 volatile이나 synchronized 없이도 고성능 + 안전한 지연 로딩이 가능하다.
 */
public class HolderSingleton {

    private HolderSingleton() {
        System.out.println("-> [HolderSingleton] Holder 내부 클래스에 의해 인스턴스가 안전하게 생성되었습니다. (Hash: " + System.identityHashCode(this) + ")");
    }

    // JVM이 getInstance() 호출 전까지 로딩하지 않는 정적 내부 클래스
    private static class SingletonHolder {
        private static final HolderSingleton INSTANCE = new HolderSingleton();
    }

    public static HolderSingleton getInstance() {
        return SingletonHolder.INSTANCE;
    }
}
