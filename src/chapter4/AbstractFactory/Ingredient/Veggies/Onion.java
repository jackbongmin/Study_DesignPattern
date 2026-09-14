package chapter4.AbstractFactory.Ingredient.Veggies;

import chapter4.AbstractFactory.Interface.Veggies;

public class Onion implements Veggies {
    @Override
    public String getName() {
        return "양파";
    }

    @Override
    public String toString() {
        return getName();
    }
}
