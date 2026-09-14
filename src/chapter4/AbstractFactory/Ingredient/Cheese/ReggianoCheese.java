package chapter4.AbstractFactory.Ingredient.Cheese;

import chapter4.AbstractFactory.Interface.Cheese;

public class ReggianoCheese implements Cheese {
    @Override
    public String getName() {
        return "레지아노 치즈";
    }

    @Override
    public String toString() {
        return getName();
    }
}
