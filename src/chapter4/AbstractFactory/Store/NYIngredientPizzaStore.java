package chapter4.AbstractFactory.Store;

import chapter4.AbstractFactory.Factory.NYPizzaIngredientFactory;
import chapter4.AbstractFactory.Interface.PizzaIngredientFactory;
import chapter4.AbstractFactory.Pizza.CheesePizza;
import chapter4.AbstractFactory.Pizza.ClamPizza;
import chapter4.AbstractFactory.Pizza.IngredientPizza;
import chapter4.AbstractFactory.Pizza.PepperoniPizza;
import chapter4.AbstractFactory.Pizza.VeggiePizza;

public class NYIngredientPizzaStore extends IngredientPizzaStore {

    @Override
    protected IngredientPizza createPizza(String item) {
        IngredientPizza pizza = null;
        PizzaIngredientFactory ingredientFactory = new NYPizzaIngredientFactory();

        if (item.equalsIgnoreCase("cheese")) {
            pizza = new CheesePizza(ingredientFactory);
            pizza.setName("뉴욕 스타일 치즈 피자 (엄선된 뉴욕 원재료)");
        } else if (item.equalsIgnoreCase("veggie")) {
            pizza = new VeggiePizza(ingredientFactory);
            pizza.setName("뉴욕 스타일 야채 피자 (엄선된 뉴욕 원재료)");
        } else if (item.equalsIgnoreCase("clam")) {
            pizza = new ClamPizza(ingredientFactory);
            pizza.setName("뉴욕 스타일 조개 피자 (엄선된 뉴욕 원재료)");
        } else if (item.equalsIgnoreCase("pepperoni")) {
            pizza = new PepperoniPizza(ingredientFactory);
            pizza.setName("뉴욕 스타일 페퍼로니 피자 (엄선된 뉴욕 원재료)");
        }

        return pizza;
    }
}
