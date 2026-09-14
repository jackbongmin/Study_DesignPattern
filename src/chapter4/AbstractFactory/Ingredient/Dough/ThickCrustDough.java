package chapter4.AbstractFactory.Ingredient.Dough;

import chapter4.AbstractFactory.Interface.Dough;

public class ThickCrustDough implements Dough {
    @Override
    public String getName() {
        return "두꺼운 크러스트 도우 (딥 디시용 도우)";
    }

    @Override
    public String toString() {
        return getName();
    }
}
