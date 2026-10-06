package chapter5;

import chapter5.Chocolate.ChocolateBoiler;
import chapter5.Chocolate.ThreadSafeChocolateBoiler;
import chapter5.Chocolate.EnumChocolateBoiler;
import chapter5.Classic.ClassicSingleton;
import chapter5.ThreadSafe.SynchronizedSingleton;
import chapter5.ThreadSafe.EagerSingleton;
import chapter5.ThreadSafe.DclSingleton;
import chapter5.Modern.HolderSingleton;
import chapter5.Modern.EnumSingleton;
import chapter5.BreakAttack.ReflectionAttackDemo;
import chapter5.BreakAttack.SerializationAttackDemo;

import java.util.Collections;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SingletonTestDrive {

    public static void main(String[] args) throws InterruptedException {
        System.out.println("==========================================================================");
        System.out.println(" 🍫 헤드퍼스트 디자인 패턴 Chapter 05: 하나뿐인 특별한 객체 만들기 (싱글톤 패턴) 🍫");
        System.out.println("==========================================================================");

        // ---------------------------------------------------------------------
        // PART 1. 헤드퍼스트 초콜릿 보일러 (ChocolateBoiler) 시뮬레이션
        // ---------------------------------------------------------------------
        System.out.println("\n[PART 1. 초콜릿 공장(Choc-O-Holic) 보일러 제어 시뮬레이션]");
        System.out.println("두 개의 참조 변수(boiler1, boiler2)가 동일한 보일러 인스턴스를 제어하는지 확인");

        ChocolateBoiler boiler1 = ChocolateBoiler.getInstance();
        ChocolateBoiler boiler2 = ChocolateBoiler.getInstance();

        System.out.println("boiler1 해시코드: " + System.identityHashCode(boiler1));
        System.out.println("boiler2 해시코드: " + System.identityHashCode(boiler2));
        System.out.println("boiler1과 boiler2는 동일 인스턴스인가? -> " + (boiler1 == boiler2));

        System.out.println("\n>>> [공정 진행 1] boiler1에서 우유와 초콜릿을 채웁니다.");
        boiler1.fill();

        System.out.println(">>> [공정 진행 2] boiler2에서 혼합물을 끓입니다. (boiler1의 상태가 공유됨)");
        boiler2.boil();

        System.out.println(">>> [공정 진행 3] boiler1에서 완성된 초콜릿을 배출합니다.");
        boiler1.drain();

        System.out.println(">>> [오류 방어 테스트] 비어있는 보일러를 끓이려고 시도합니다.");
        boiler2.boil();

        // ---------------------------------------------------------------------
        // PART 2. 멀티스레드 환경에서 Classic Singleton의 Race Condition 검증
        // ---------------------------------------------------------------------
        System.out.println("\n==========================================================================");
        System.out.println("[PART 2. 멀티스레드 환경에서 Classic Singleton의 취약점 재현 (Race Condition)]");
        System.out.println("5개의 스레드가 동시에 ClassicSingleton.getInstance()를 호출합니다.");
        System.out.println("==========================================================================");

        ClassicSingleton.resetForTesting();
        int threadCount = 5;
        ExecutorService executor = Executors.newFixedThreadPool(threadCount);
        CountDownLatch readyLatch = new CountDownLatch(threadCount);
        CountDownLatch startLatch = new CountDownLatch(1);
        Set<Integer> classicHashCodes = Collections.newSetFromMap(new ConcurrentHashMap<>());

        for (int i = 0; i < threadCount; i++) {
            executor.submit(() -> {
                try {
                    readyLatch.countDown();
                    startLatch.await(); // 모든 스레드가 준비될 때까지 대기 후 동시 발사
                    ClassicSingleton instance = ClassicSingleton.getInstance();
                    classicHashCodes.add(System.identityHashCode(instance));
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            });
        }

        readyLatch.await();
        startLatch.countDown(); // 동시 시작 신호
        Thread.sleep(200); // 작업 완료 대기

        System.out.println("생성된 서로 다른 ClassicSingleton 인스턴스 개수: " + classicHashCodes.size());
        if (classicHashCodes.size() > 1) {
            System.out.println("🚨 [버그 발생!] 싱글톤임에도 불구하고 멀티스레드 경쟁으로 인해 " 
                    + classicHashCodes.size() + "개의 인스턴스가 생성되었습니다!");
        } else {
            System.out.println("운 좋게 단 1개만 생성되었으나, 타이밍에 따라 언제든 깨질 수 있습니다.");
        }

        // ---------------------------------------------------------------------
        // PART 3. 안전한 싱글톤 기법들의 멀티스레드 동시성 검증
        // ---------------------------------------------------------------------
        System.out.println("\n==========================================================================");
        System.out.println("[PART 3. 스레드 안전 싱글톤들의 동시성 검증 (30개 동시 스레드 요청)]");
        System.out.println("==========================================================================");

        testThreadSafety("DCL Singleton", () -> System.identityHashCode(DclSingleton.getInstance()));
        testThreadSafety("Static Inner Holder Singleton", () -> System.identityHashCode(HolderSingleton.getInstance()));
        testThreadSafety("Enum Singleton", () -> System.identityHashCode(EnumSingleton.INSTANCE));

        // ---------------------------------------------------------------------
        // PART 4. 싱글톤 파괴 및 방어 실습 (리플렉션 공격)
        // ---------------------------------------------------------------------
        ReflectionAttackDemo.testReflectionAttack();

        // ---------------------------------------------------------------------
        // PART 5. 싱글톤 파괴 및 방어 실습 (직렬화 공격)
        // ---------------------------------------------------------------------
        SerializationAttackDemo.testSerializationAttack();

        System.out.println("\n==========================================================================");
        System.out.println(" 🎉 Chapter 05 싱글톤 패턴 종합 학습 및 검증 완료!");
        System.out.println("==========================================================================");

        executor.shutdown();
    }

    private static void testThreadSafety(String name, java.util.function.Supplier<Integer> supplier) throws InterruptedException {
        int threads = 30;
        ExecutorService exec = Executors.newFixedThreadPool(threads);
        CountDownLatch ready = new CountDownLatch(threads);
        CountDownLatch start = new CountDownLatch(1);
        Set<Integer> hashCodes = Collections.newSetFromMap(new ConcurrentHashMap<>());

        for (int i = 0; i < threads; i++) {
            exec.submit(() -> {
                try {
                    ready.countDown();
                    start.await();
                    hashCodes.add(supplier.get());
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            });
        }

        ready.await();
        start.countDown();
        exec.shutdown();
        exec.awaitTermination(2, java.util.concurrent.TimeUnit.SECONDS);

        System.out.println("▶ [" + name + "] 30개 동시 스레드 테스트 결과 생성된 고유 인스턴스 수: " + hashCodes.size()
                + (hashCodes.size() == 1 ? " (✅ 완벽한 스레드 안전성 보장)" : " (❌ 동시성 실패)"));
    }
}
