package chapter5.Modern;

/**
 * [6단계: 열거 타입 싱글톤 (Enum Singleton)]
 * 
 * 특징:
 * - Joshua Bloch(Effective Java 저자, 전 구글/썬 수석 아키텍트)가 제시하고
 *   헤드퍼스트 디자인 패턴 최신 개정판에서도 극찬하는 가장 진보된 싱글톤 구현 기법.
 * 
 * 장점:
 * 1. 극도의 간결함: 단 몇 줄의 코드로 구현 가능.
 * 2. 완벽한 스레드 안전성: JVM이 enum 상수의 인스턴스화를 언어 레벨에서 보장함.
 * 3. 직렬화(Serialization) 안전성: 기본적으로 직렬화가 가능하며,
 *    역직렬화 시 새로운 인스턴스가 생성되는 현상을 JVM이 자체적으로 방지함.
 * 4. 리플렉션 공격(Reflection Attack) 완벽 방어:
 *    Constructor.newInstance() 내부에서 Enum 타입인 경우 IllegalArgumentException을
 *    던지도록 자바 언어 스펙상 하드코딩되어 있어 악의적인 private 생성자 호출이 불가능함.
 */
public enum EnumSingleton {
    INSTANCE;

    private int value = 0;

    EnumSingleton() {
        System.out.println("-> [EnumSingleton] 열거형 인스턴스가 초기화되었습니다. (Hash: " + System.identityHashCode(this) + ")");
    }

    public void doSomething() {
        System.out.println("EnumSingleton 비즈니스 로직 수행 (value: " + value + ")");
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
