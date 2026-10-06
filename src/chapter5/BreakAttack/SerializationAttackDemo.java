package chapter5.BreakAttack;

import java.io.*;
import chapter5.Modern.EnumSingleton;

/**
 * [싱글톤 깨뜨리기 실습 2: 직렬화/역직렬화 공격 (Serialization Attack)]
 * 
 * 싱글톤 클래스가 Serializable을 구현했을 때,
 * 바이트 스트림으로 직렬화(Serialize)한 뒤 역직렬화(Deserialize)하면
 * new 연산자나 생성자 호출 없이도 새로운 인스턴스가 생성되어 싱글톤이 파괴된다.
 * 
 * 방어 방법:
 * 1. 일반 클래스: readResolve() 메서드를 구현하여 역직렬화 시 기존 INSTANCE를 반환하도록 재정의.
 * 2. Enum 클래스: JVM 명세상 Enum의 직렬화는 상수의 이름만 저장하고,
 *    역직렬화 시 Enum.valueOf()를 호출하여 동일 인스턴스를 찾아오므로 추가 작업 없이도 완벽 방어됨.
 */
public class SerializationAttackDemo {

    // 1. 공격에 취약한 직렬화 가능 싱글톤
    public static class VulnerableSerializableSingleton implements Serializable {
        private static final long serialVersionUID = 1L;
        private static final VulnerableSerializableSingleton INSTANCE = new VulnerableSerializableSingleton();
        private VulnerableSerializableSingleton() {}
        public static VulnerableSerializableSingleton getInstance() { return INSTANCE; }
    }

    // 2. readResolve()로 방어된 싱글톤
    public static class DefendedSerializableSingleton implements Serializable {
        private static final long serialVersionUID = 1L;
        private static final DefendedSerializableSingleton INSTANCE = new DefendedSerializableSingleton();
        private DefendedSerializableSingleton() {}
        public static DefendedSerializableSingleton getInstance() { return INSTANCE; }

        // 역직렬화 시 새 객체 대신 기존 싱글톤 인스턴스를 반환하도록 강제
        protected Object readResolve() {
            return INSTANCE;
        }
    }

    public static void testSerializationAttack() {
        System.out.println("\n-----------------------------------------------------------");
        System.out.println(" 🚨 [실습 2] 직렬화/역직렬화(Serialization)를 통한 싱글톤 파괴 시도");
        System.out.println("-----------------------------------------------------------");

        try {
            // (1) 취약한 싱글톤 직렬화/역직렬화
            VulnerableSerializableSingleton vOrig = VulnerableSerializableSingleton.getInstance();
            byte[] vBytes = serialize(vOrig);
            VulnerableSerializableSingleton vCopy = deserialize(vBytes);

            System.out.println("[취약한 싱글톤] 원본 인스턴스 Hash: " + System.identityHashCode(vOrig));
            System.out.println("[취약한 싱글톤] 역직렬화 인스턴스 Hash: " + System.identityHashCode(vCopy));
            System.out.println("결과: 두 인스턴스가 동일한가? -> " + (vOrig == vCopy) + " (❌ 싱글톤 원칙 파괴됨!)");

            // (2) readResolve() 방어 싱글톤
            DefendedSerializableSingleton dOrig = DefendedSerializableSingleton.getInstance();
            byte[] dBytes = serialize(dOrig);
            DefendedSerializableSingleton dCopy = deserialize(dBytes);

            System.out.println("\n[readResolve 방어 싱글톤] 원본 인스턴스 Hash: " + System.identityHashCode(dOrig));
            System.out.println("[readResolve 방어 싱글톤] 역직렬화 인스턴스 Hash: " + System.identityHashCode(dCopy));
            System.out.println("결과: 두 인스턴스가 동일한가? -> " + (dOrig == dCopy) + " (✅ 방어 성공! 동일 인스턴스 유지)");

            // (3) Enum 싱글톤 직렬화/역직렬화
            EnumSingleton eOrig = EnumSingleton.INSTANCE;
            byte[] eBytes = serialize(eOrig);
            EnumSingleton eCopy = deserialize(eBytes);

            System.out.println("\n[Enum 싱글톤] 원본 인스턴스 Hash: " + System.identityHashCode(eOrig));
            System.out.println("[Enum 싱글톤] 역직렬화 인스턴스 Hash: " + System.identityHashCode(eCopy));
            System.out.println("결과: 두 인스턴스가 동일한가? -> " + (eOrig == eCopy) + " (✅ JVM 언어 스펙상 자동 방어 완료!)");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static byte[] serialize(Object obj) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try (ObjectOutputStream oos = new ObjectOutputStream(baos)) {
            oos.writeObject(obj);
        }
        return baos.toByteArray();
    }

    @SuppressWarnings("unchecked")
    private static <T> T deserialize(byte[] bytes) throws IOException, ClassNotFoundException {
        ByteArrayInputStream bais = new ByteArrayInputStream(bytes);
        try (ObjectInputStream ois = new ObjectInputStream(bais)) {
            return (T) ois.readObject();
        }
    }
}
