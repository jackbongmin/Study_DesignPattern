package chapter4.AbstractFactory.Factory;

import chapter4.AbstractFactory.Ingredient.Cheese.ReggianoCheese;
import chapter4.AbstractFactory.Ingredient.Clams.FreshClams;
import chapter4.AbstractFactory.Ingredient.Dough.ThinCrustDough;
import chapter4.AbstractFactory.Ingredient.Pepperoni.SlicedPepperoni;
import chapter4.AbstractFactory.Ingredient.Sauce.MarinaraSauce;
import chapter4.AbstractFactory.Ingredient.Veggies.Garlic;
import chapter4.AbstractFactory.Ingredient.Veggies.Mushroom;
import chapter4.AbstractFactory.Ingredient.Veggies.Onion;
import chapter4.AbstractFactory.Ingredient.Veggies.RedPepper;
import chapter4.AbstractFactory.Interface.Cheese;
import chapter4.AbstractFactory.Interface.Clams;
import chapter4.AbstractFactory.Interface.Dough;
import chapter4.AbstractFactory.Interface.Pepperoni;
import chapter4.AbstractFactory.Interface.PizzaIngredientFactory;
import chapter4.AbstractFactory.Interface.Sauce;
import chapter4.AbstractFactory.Interface.Veggies;

public class NYPizzaIngredientFactory implements PizzaIngredientFactory {

    @Override
    public Dough createDough() {
        return new ThinCrustDough();
    }

    @Override
    public Sauce createSauce() {
        return new MarinaraSauce();
    }

    @Override
    public Cheese createCheese() {
        return new ReggianoCheese();
    }

    @Override
    public Veggies[] createVeggies() {
        return new Veggies[] { new Garlic(), new Onion(), new Mushroom(), new RedPepper() };
    }

    @Override
    public Pepperoni createPepperoni() {
        return new SlicedPepperoni();
    }

    @Override
    public Clams createClam() {
        return new FreshClams();
    }
}
