package chapter4.SimpleFactory;

import chapter4.Actor.Pizza.NYStyleCheesePizza;
import chapter4.Actor.Pizza.NYStyleClamPizza;
import chapter4.Actor.Pizza.NYStylePepperoniPizza;
import chapter4.Actor.Pizza.NYStyleVeggiePizza;
import chapter4.Actor.Pizza.Pizza;

/**
 * 간단한 팩토리 (Simple Factory)
 * 디자인 패턴이라기보다는 객체 생성 책임을 한 곳으로 모으는 프로그래밍 관용구(Idiom)
 */
public class SimplePizzaFactory {

    public Pizza createPizza(String type) {
        Pizza pizza = null;

        if (type.equalsIgnoreCase("cheese")) {
            pizza = new NYStyleCheesePizza();
        } else if (type.equalsIgnoreCase("pepperoni")) {
            pizza = new NYStylePepperoniPizza();
        } else if (type.equalsIgnoreCase("clam")) {
            pizza = new NYStyleClamPizza();
        } else if (type.equalsIgnoreCase("veggie")) {
            pizza = new NYStyleVeggiePizza();
        }

        return pizza;
    }
}
