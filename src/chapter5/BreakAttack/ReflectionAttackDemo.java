package chapter5.BreakAttack;

import java.lang.reflect.Constructor;
import chapter5.Modern.EnumSingleton;

/**
 * [싱글톤 깨뜨리기 실습 1: 리플렉션 공격 (Reflection Attack)]
 * 
 * 리플렉션을 사용하면 setAccessible(true)를 통해 private 생성자의 접근 제어를 무력화하고
 * 외부에서 강제로 두 번째 인스턴스를 생성할 수 있다.
 * 
 * 방어 방법:
 * 1. 일반 클래스: 생성자 내부에서 이미 생성된 인스턴스가 있다면 예외(RuntimeException)를 던지도록 방어.
 * 2. Enum 클래스: JVM 명세상 리플렉션으로 Enum 객체를 생성하려 하면
 *    IllegalArgumentException("Cannot reflectively create enum objects")이 발생하여 원천 차단됨.
 */
public class ReflectionAttackDemo {

    // 1. 공격에 취약한 일반 싱글톤
    public static class VulnerableSingleton {
        private static final VulnerableSingleton INSTANCE = new VulnerableSingleton();
        private VulnerableSingleton() {}
        public static VulnerableSingleton getInstance() { return INSTANCE; }
    }

    // 2. 리플렉션 방어 로직이 적용된 싱글톤
    public static class DefendedSingleton {
        private static final DefendedSingleton INSTANCE = new DefendedSingleton();
        private static boolean isCreated = false;

        private DefendedSingleton() {
            if (isCreated) {
                throw new IllegalStateException("❌ [보안 경고] 리플렉션을 통한 싱글톤 생성 시도가 감지되어 차단되었습니다!");
            }
            isCreated = true;
        }

        public static DefendedSingleton getInstance() { return INSTANCE; }
    }

    public static void testReflectionAttack() {
        System.out.println("\n-----------------------------------------------------------");
        System.out.println(" 🚨 [실습 1] 리플렉션(Reflection)을 통한 싱글톤 파괴 시도");
        System.out.println("-----------------------------------------------------------");

        // (1) 취약한 싱글톤 공격
        try {
            VulnerableSingleton inst1 = VulnerableSingleton.getInstance();
            Constructor<VulnerableSingleton> constructor = VulnerableSingleton.class.getDeclaredConstructor();
            constructor.setAccessible(true); // private 무력화
            VulnerableSingleton inst2 = constructor.newInstance();

            System.out.println("[취약한 싱글톤] 정상 인스턴스 Hash: " + System.identityHashCode(inst1));
            System.out.println("[취약한 싱글톤] 침투 인스턴스 Hash: " + System.identityHashCode(inst2));
            System.out.println("결과: 두 인스턴스가 동일한가? -> " + (inst1 == inst2) + " (❌ 싱글톤 원칙 파괴됨!)");
        } catch (Exception e) {
            System.out.println("예외 발생: " + e.getMessage());
        }

        // (2) 방어 코드가 적용된 싱글톤 공격
        try {
            DefendedSingleton inst1 = DefendedSingleton.getInstance();
            Constructor<DefendedSingleton> constructor = DefendedSingleton.class.getDeclaredConstructor();
            constructor.setAccessible(true);
            DefendedSingleton inst2 = constructor.newInstance();
        } catch (Exception e) {
            System.out.println("[방어된 싱글톤] 공격 차단 성공: " + e.getCause().getMessage());
        }

        // (3) Enum 싱글톤 공격
        try {
            Constructor<EnumSingleton> constructor = EnumSingleton.class.getDeclaredConstructor(String.class, int.class);
            constructor.setAccessible(true);
            EnumSingleton inst2 = constructor.newInstance("HACKED", 1);
        } catch (Exception e) {
            System.out.println("[Enum 싱글톤] JVM 언어 차원의 공격 차단: " + e.getClass().getSimpleName() + " - " + e.getMessage());
        }
    }
}
