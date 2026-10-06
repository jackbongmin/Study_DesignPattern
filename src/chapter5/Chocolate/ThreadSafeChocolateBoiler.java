package chapter5.Chocolate;

/**
 * [멀티스레드 환경 대응 초콜릿 보일러]
 * DCL (Double-Checked Locking)과 volatile 키워드를 적용하여
 * 멀티스레드 환경에서도 단 하나의 인스턴스만 생성됨을 보장하며,
 * 보일러 작업 메서드(fill, boil, drain)에도 synchronized 처리를 하여
 * 동시 접근 시 상태 불일치(Race Condition)를 방지한다.
 */
public class ThreadSafeChocolateBoiler {

    private boolean empty;
    private boolean boiled;

    // volatile 키워드: CPU 캐시가 아닌 메인 메모리 동기화 및 명령어 재정렬(Reordering) 방지
    private static volatile ThreadSafeChocolateBoiler uniqueInstance;

    private ThreadSafeChocolateBoiler() {
        empty = true;
        boiled = false;
    }

    public static ThreadSafeChocolateBoiler getInstance() {
        if (uniqueInstance == null) {
            synchronized (ThreadSafeChocolateBoiler.class) {
                if (uniqueInstance == null) {
                    System.out.println("[ThreadSafeChocolateBoiler] DCL 방식으로 안전하게 인스턴스를 생성합니다.");
                    uniqueInstance = new ThreadSafeChocolateBoiler();
                }
            }
        }
        return uniqueInstance;
    }

    public synchronized void fill() {
        if (isEmpty()) {
            empty = false;
            boiled = false;
            System.out.println("🍫 [ThreadSafe fill] 보일러에 원료를 안전하게 채웠습니다.");
        } else {
            System.out.println("⚠️ [ThreadSafe fill] 보일러가 이미 차 있습니다.");
        }
    }

    public synchronized void boil() {
        if (!isEmpty() && !isBoiled()) {
            System.out.println("🔥 [ThreadSafe boil] 원료를 안전하게 끓였습니다.");
            boiled = true;
        } else if (isEmpty()) {
            System.out.println("⚠️ [ThreadSafe boil] 빈 보일러 가열 금지!");
        } else {
            System.out.println("⚠️ [ThreadSafe boil] 이미 다 끓었습니다.");
        }
    }

    public synchronized void drain() {
        if (!isEmpty() && isBoiled()) {
            System.out.println("🍫 [ThreadSafe drain] 끓인 초콜릿을 안전하게 배출했습니다.");
            empty = true;
        } else {
            System.out.println("⚠️ [ThreadSafe drain] 배출 조건 미충족 (empty=" + empty + ", boiled=" + boiled + ")");
        }
    }

    public synchronized boolean isEmpty() {
        return empty;
    }

    public synchronized boolean isBoiled() {
        return boiled;
    }
}
