package chapter4.AbstractFactory.Ingredient.Veggies;

import chapter4.AbstractFactory.Interface.Veggies;

public class Mushroom implements Veggies {
    @Override
    public String getName() {
        return "버섯";
    }

    @Override
    public String toString() {
        return getName();
    }
}
