package chapter4.Actor.Store;

import chapter4.Actor.Pizza.NYStyleCheesePizza;
import chapter4.Actor.Pizza.NYStyleClamPizza;
import chapter4.Actor.Pizza.NYStylePepperoniPizza;
import chapter4.Actor.Pizza.NYStyleVeggiePizza;
import chapter4.Actor.Pizza.Pizza;

public class NYPizzaStore extends PizzaStore {

    @Override
    protected Pizza createPizza(String item) {
        if (item.equalsIgnoreCase("cheese")) {
            return new NYStyleCheesePizza();
        } else if (item.equalsIgnoreCase("veggie")) {
            return new NYStyleVeggiePizza();
        } else if (item.equalsIgnoreCase("clam")) {
            return new NYStyleClamPizza();
        } else if (item.equalsIgnoreCase("pepperoni")) {
            return new NYStylePepperoniPizza();
        } else {
            return null;
        }
    }
}
