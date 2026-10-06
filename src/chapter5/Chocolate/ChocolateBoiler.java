package chapter5.Chocolate;

/**
 * [헤드퍼스트 디자인 패턴 Chapter 05]
 * 초콜릿 공장(Choc-O-Holic Inc.)의 초콜릿 보일러 클래스
 * 
 * 보일러가 비어있을 때만 재료를 채우고,
 * 가득 차 있고 끓지 않았을 때만 가열하며,
 * 다 끓었을 때만 배출해야 한다.
 * 인스턴스가 2개 이상 생성되면 심각한 오작동(과열, 원료 유출 등)이 발생할 수 있다.
 */
public class ChocolateBoiler {

    private boolean empty;
    private boolean boiled;

    // 유일한 인스턴스를 저장하기 위한 정적 변수 (고전적 싱글톤 방식)
    private static ChocolateBoiler uniqueInstance;

    // 생성자를 private으로 선언하여 외부에서 new ChocolateBoiler() 호출 불가
    private ChocolateBoiler() {
        empty = true;
        boiled = false;
    }

    // 인스턴스를 가져오는 전역 접근점 메서드
    public static ChocolateBoiler getInstance() {
        if (uniqueInstance == null) {
            System.out.println("[ChocolateBoiler] 새로운 초콜릿 보일러 인스턴스를 생성합니다.");
            uniqueInstance = new ChocolateBoiler();
        }
        return uniqueInstance;
    }

    // 보일러에 우유와 초콜릿 혼합물을 채움
    public void fill() {
        if (isEmpty()) {
            empty = false;
            boiled = false;
            System.out.println("🍫 [fill] 보일러에 신선한 우유와 초콜릿 혼합물을 가득 채웁니다.");
        } else {
            System.out.println("⚠️ [fill 경고] 보일러가 이미 차 있어 재료를 더 채울 수 없습니다!");
        }
    }

    // 다 끓인 혼합물을 다음 제조 공정으로 배출
    public void drain() {
        if (!isEmpty() && isBoiled()) {
            System.out.println("🍫 [drain] 다 끓인 초콜릿 혼합물을 배출하여 초콜릿 바로 성형합니다.");
            empty = true;
        } else if (isEmpty()) {
            System.out.println("⚠️ [drain 경고] 보일러가 비어있어 배출할 수 없습니다!");
        } else if (!isBoiled()) {
            System.out.println("⚠️ [drain 경고] 아직 끓지 않은 원료는 배출할 수 없습니다!");
        }
    }

    // 혼합물을 끓임
    public void boil() {
        if (!isEmpty() && !isBoiled()) {
            System.out.println("🔥 [boil] 보일러를 가열하여 초콜릿 혼합물을 끓입니다.");
            boiled = true;
        } else if (isEmpty()) {
            System.out.println("⚠️ [boil 경고] 빈 보일러를 가열하면 기계가 손상되거나 화재가 발생합니다!");
        } else if (isBoiled()) {
            System.out.println("⚠️ [boil 경고] 혼합물이 이미 다 끓었습니다!");
        }
    }

    public boolean isEmpty() {
        return empty;
    }

    public boolean isBoiled() {
        return boiled;
    }

    // 테스트를 위해 인스턴스 초기화 메서드 (실제 프로덕션에서는 제공하지 않음)
    public static void resetInstanceForTesting() {
        uniqueInstance = null;
    }
}
