package chapter4.AbstractFactory.Ingredient.Clams;

import chapter4.AbstractFactory.Interface.Clams;

public class FreshClams implements Clams {
    @Override
    public String getName() {
        return "신선한 조개 (롱아일랜드산 바지락)";
    }

    @Override
    public String toString() {
        return getName();
    }
}
