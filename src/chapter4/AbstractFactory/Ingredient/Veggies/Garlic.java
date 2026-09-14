package chapter4.AbstractFactory.Ingredient.Veggies;

import chapter4.AbstractFactory.Interface.Veggies;

public class Garlic implements Veggies {
    @Override
    public String getName() {
        return "마늘";
    }

    @Override
    public String toString() {
        return getName();
    }
}
