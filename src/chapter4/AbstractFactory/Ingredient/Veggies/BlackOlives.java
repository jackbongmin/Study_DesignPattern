package chapter4.AbstractFactory.Ingredient.Veggies;

import chapter4.AbstractFactory.Interface.Veggies;

public class BlackOlives implements Veggies {
    @Override
    public String getName() {
        return "블랙 올리브";
    }

    @Override
    public String toString() {
        return getName();
    }
}
