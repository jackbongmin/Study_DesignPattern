package chapter4.Actor.Store;

import chapter4.Actor.Pizza.Pizza;

public abstract class PizzaStore {

    public Pizza orderPizza(String type) {
        Pizza pizza = createPizza(type);

        if (pizza == null) {
            System.out.println("죄송합니다. 요청하신 '" + type + "' 종류의 피자는 메뉴에 없습니다.");
            return null;
        }

        System.out.println("\n--- " + pizza.getName() + " 주문 접수 완료 (조리 시작) ---");
        pizza.prepare();
        pizza.bake();
        pizza.cut();
        pizza.box();

        return pizza;
    }

    // 팩토리 메서드 (Factory Method)
    // 객체 생성을 서브클래스에 캡슐화/위임함
    protected abstract Pizza createPizza(String type);
}
