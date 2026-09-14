package chapter4.AbstractFactory.Ingredient.Veggies;

import chapter4.AbstractFactory.Interface.Veggies;

public class Spinach implements Veggies {
    @Override
    public String getName() {
        return "시금치";
    }

    @Override
    public String toString() {
        return getName();
    }
}
