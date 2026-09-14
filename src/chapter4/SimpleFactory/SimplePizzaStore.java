package chapter4.SimpleFactory;

import chapter4.Actor.Pizza.Pizza;

/**
 * 간단한 팩토리를 활용하는 피자 매장
 */
public class SimplePizzaStore {
    private SimplePizzaFactory factory;

    public SimplePizzaStore(SimplePizzaFactory factory) {
        this.factory = factory;
    }

    public Pizza orderPizza(String type) {
        Pizza pizza = factory.createPizza(type);

        if (pizza == null) {
            System.out.println("해당 메뉴가 존재하지 않습니다: " + type);
            return null;
        }

        System.out.println("\n--- [Simple Factory] " + pizza.getName() + " 주문 접수 ---");
        pizza.prepare();
        pizza.bake();
        pizza.cut();
        pizza.box();

        return pizza;
    }
}
