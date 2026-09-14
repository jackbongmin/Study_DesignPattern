package chapter4.AbstractFactory.Ingredient.Cheese;

import chapter4.AbstractFactory.Interface.Cheese;

public class MozzarellaCheese implements Cheese {
    @Override
    public String getName() {
        return "모차렐라 치즈";
    }

    @Override
    public String toString() {
        return getName();
    }
}
