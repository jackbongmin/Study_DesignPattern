package chapter4.AbstractFactory.Ingredient.Veggies;

import chapter4.AbstractFactory.Interface.Veggies;

public class RedPepper implements Veggies {
    @Override
    public String getName() {
        return "붉은 피망";
    }

    @Override
    public String toString() {
        return getName();
    }
}
