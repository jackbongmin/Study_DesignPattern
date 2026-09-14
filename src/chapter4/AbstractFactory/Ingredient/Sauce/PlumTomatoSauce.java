package chapter4.AbstractFactory.Ingredient.Sauce;

import chapter4.AbstractFactory.Interface.Sauce;

public class PlumTomatoSauce implements Sauce {
    @Override
    public String getName() {
        return "플럼토마토 소스";
    }

    @Override
    public String toString() {
        return getName();
    }
}
