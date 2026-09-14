package chapter4.AbstractFactory.Ingredient.Dough;

import chapter4.AbstractFactory.Interface.Dough;

public class ThinCrustDough implements Dough {
    @Override
    public String getName() {
        return "씬 크러스트 도우 (얇은 도우)";
    }

    @Override
    public String toString() {
        return getName();
    }
}
