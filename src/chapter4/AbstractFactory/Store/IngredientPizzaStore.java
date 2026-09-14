package chapter4.AbstractFactory.Store;

import chapter4.AbstractFactory.Pizza.IngredientPizza;

public abstract class IngredientPizzaStore {

    public IngredientPizza orderPizza(String type) {
        IngredientPizza pizza = createPizza(type);

        if (pizza == null) {
            System.out.println("해당 피자 종류가 존재하지 않습니다: " + type);
            return null;
        }

        System.out.println("\n--- [Abstract Factory] " + pizza.getName() + " 주문 접수 ---");
        pizza.prepare();
        pizza.bake();
        pizza.cut();
        pizza.box();

        return pizza;
    }

    protected abstract IngredientPizza createPizza(String item);
}
