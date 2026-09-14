package chapter4.AbstractFactory.Ingredient.Clams;

import chapter4.AbstractFactory.Interface.Clams;

public class FrozenClams implements Clams {
    @Override
    public String getName() {
        return "냉동 조개 (체사피크만산 냉동 조개)";
    }

    @Override
    public String toString() {
        return getName();
    }
}
