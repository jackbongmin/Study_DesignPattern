package chapter5.ThreadSafe;

/**
 * [4단계: DCL 싱글톤 (Double-Checked Locking Singleton)]
 * 
 * 특징:
 * - 인스턴스가 생성되어 있는지 먼저 검사하고, 생성되지 않은 경우에만 synchronized 블록에 진입한다.
 * - 블록 안에서 다시 한 번 null 체크를 수행(Double Check)하여 동시 접근 스레드의 중복 생성을 차단한다.
 * - 최초 1회 인스턴스화 시점에만 동기화가 발생하므로 이후 호출에서는 동기화 오버헤드가 거의 없다.
 * 
 * ★ volatile 키워드가 필수적인 이유 (JMM: Java Memory Model):
 * 객체 생성 연산 (new DclSingleton())은 바이트코드 상에서 3단계로 분리된다:
 *   1. 메모리 공간 할당 (allocate)
 *   2. 생성자 호출 및 인스턴스 초기화 (invokespecial <init>)
 *   3. 참조 변수에 할당된 메모리 주소 기록 (putstatic)
 * 
 * 만약 volatile이 없다면 컴파일러/CPU 최적화에 의해 명령어 재배열(Instruction Reordering)이 발생하여
 * 1 -> 3 -> 2 순서로 실행될 수 있다.
 * 이 경우 다른 스레드가 첫 번째 if (uniqueInstance == null)을 확인할 때
 * '주소는 할당되었으나 내부 필드는 초기화되지 않은 껍데기 객체'를 유효한 인스턴스로 오인하고
 * 참조하여 치명적인 런타임 오류가 발생할 수 있다.
 * 
 * volatile 키워드는:
 * 1) 메모리 장벽(Memory Barrier)을 세워 명령어 재배열을 방지한다.
 * 2) CPU 캐시가 아닌 메인 메모리에 즉시 반영하여 메모리 가시성(Visibility)을 보장한다.
 */
public class DclSingleton {

    // volatile 필수! (Java 5 이상 지원)
    private static volatile DclSingleton uniqueInstance;

    private DclSingleton() {
        System.out.println("-> [DclSingleton] 인스턴스가 DCL로 안전하게 생성되었습니다. (Hash: " + System.identityHashCode(this) + ")");
    }

    public static DclSingleton getInstance() {
        // 1차 체크: 인스턴스가 이미 있으면 락을 걸지 않고 즉시 반환 (성능 최적화)
        if (uniqueInstance == null) {
            synchronized (DclSingleton.class) {
                // 2차 체크: 락을 획득한 후 다른 스레드가 먼저 만들었는지 다시 확인
                if (uniqueInstance == null) {
                    uniqueInstance = new DclSingleton();
                }
            }
        }
        return uniqueInstance;
    }
}
