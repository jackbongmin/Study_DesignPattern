package chapter4.Actor.Pizza;

public class ChicagoStylePepperoniPizza extends Pizza {

    public ChicagoStylePepperoniPizza() {
        name = "시카고 스타일 딥 디시 페퍼로니 피자";
        dough = "아주 두꺼운 크러스트 도우";
        sauce = "플럼토마토 소스";

        toppings.add("잘게 조각낸 모차렐라 치즈");
        toppings.add("블랙 올리브");
        toppings.add("시금치");
        toppings.add("가지");
        toppings.add("슬라이스 페퍼로니");
    }

    @Override
    public void cut() {
        System.out.println("네모난 모양으로 피자 자르기");
    }
}
