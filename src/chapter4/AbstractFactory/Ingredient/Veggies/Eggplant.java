package chapter4.AbstractFactory.Ingredient.Veggies;

import chapter4.AbstractFactory.Interface.Veggies;

public class Eggplant implements Veggies {
    @Override
    public String getName() {
        return "가지";
    }

    @Override
    public String toString() {
        return getName();
    }
}
