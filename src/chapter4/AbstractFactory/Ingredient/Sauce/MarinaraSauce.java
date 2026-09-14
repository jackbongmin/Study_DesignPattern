package chapter4.AbstractFactory.Ingredient.Sauce;

import chapter4.AbstractFactory.Interface.Sauce;

public class MarinaraSauce implements Sauce {
    @Override
    public String getName() {
        return "마리나라 소스";
    }

    @Override
    public String toString() {
        return getName();
    }
}
