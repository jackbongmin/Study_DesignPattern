package chapter4.AbstractFactory.Factory;

import chapter4.AbstractFactory.Ingredient.Cheese.MozzarellaCheese;
import chapter4.AbstractFactory.Ingredient.Clams.FrozenClams;
import chapter4.AbstractFactory.Ingredient.Dough.ThickCrustDough;
import chapter4.AbstractFactory.Ingredient.Pepperoni.SlicedPepperoni;
import chapter4.AbstractFactory.Ingredient.Sauce.PlumTomatoSauce;
import chapter4.AbstractFactory.Ingredient.Veggies.BlackOlives;
import chapter4.AbstractFactory.Ingredient.Veggies.Eggplant;
import chapter4.AbstractFactory.Ingredient.Veggies.Spinach;
import chapter4.AbstractFactory.Interface.Cheese;
import chapter4.AbstractFactory.Interface.Clams;
import chapter4.AbstractFactory.Interface.Dough;
import chapter4.AbstractFactory.Interface.Pepperoni;
import chapter4.AbstractFactory.Interface.PizzaIngredientFactory;
import chapter4.AbstractFactory.Interface.Sauce;
import chapter4.AbstractFactory.Interface.Veggies;

public class ChicagoPizzaIngredientFactory implements PizzaIngredientFactory {

    @Override
    public Dough createDough() {
        return new ThickCrustDough();
    }

    @Override
    public Sauce createSauce() {
        return new PlumTomatoSauce();
    }

    @Override
    public Cheese createCheese() {
        return new MozzarellaCheese();
    }

    @Override
    public Veggies[] createVeggies() {
        return new Veggies[] { new BlackOlives(), new Spinach(), new Eggplant() };
    }

    @Override
    public Pepperoni createPepperoni() {
        return new SlicedPepperoni();
    }

    @Override
    public Clams createClam() {
        return new FrozenClams();
    }
}
