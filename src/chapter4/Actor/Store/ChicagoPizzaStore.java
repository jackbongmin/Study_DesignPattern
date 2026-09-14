package chapter4.Actor.Store;

import chapter4.Actor.Pizza.ChicagoStyleCheesePizza;
import chapter4.Actor.Pizza.ChicagoStyleClamPizza;
import chapter4.Actor.Pizza.ChicagoStylePepperoniPizza;
import chapter4.Actor.Pizza.ChicagoStyleVeggiePizza;
import chapter4.Actor.Pizza.Pizza;

public class ChicagoPizzaStore extends PizzaStore {

    @Override
    protected Pizza createPizza(String item) {
        if (item.equalsIgnoreCase("cheese")) {
            return new ChicagoStyleCheesePizza();
        } else if (item.equalsIgnoreCase("veggie")) {
            return new ChicagoStyleVeggiePizza();
        } else if (item.equalsIgnoreCase("clam")) {
            return new ChicagoStyleClamPizza();
        } else if (item.equalsIgnoreCase("pepperoni")) {
            return new ChicagoStylePepperoniPizza();
        } else {
            return null;
        }
    }
}
