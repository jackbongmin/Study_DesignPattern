package chapter4.AbstractFactory.Ingredient.Pepperoni;

import chapter4.AbstractFactory.Interface.Pepperoni;

public class SlicedPepperoni implements Pepperoni {
    @Override
    public String getName() {
        return "슬라이스 페퍼로니";
    }

    @Override
    public String toString() {
        return getName();
    }
}
