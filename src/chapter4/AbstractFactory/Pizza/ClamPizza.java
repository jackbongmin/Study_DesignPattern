package chapter4.AbstractFactory.Pizza;

import chapter4.AbstractFactory.Interface.PizzaIngredientFactory;

public class ClamPizza extends IngredientPizza {
    private PizzaIngredientFactory ingredientFactory;

    public ClamPizza(PizzaIngredientFactory ingredientFactory) {
        this.ingredientFactory = ingredientFactory;
    }

    @Override
    public void prepare() {
        System.out.println("준비 중: " + name);
        dough = ingredientFactory.createDough();
        sauce = ingredientFactory.createSauce();
        cheese = ingredientFactory.createCheese();
        clam = ingredientFactory.createClam();
        System.out.println("  도우 준비: " + dough.getName());
        System.out.println("  소스 추가: " + sauce.getName());
        System.out.println("  치즈 토핑: " + cheese.getName());
        System.out.println("  조개 토핑: " + clam.getName());
    }
}
