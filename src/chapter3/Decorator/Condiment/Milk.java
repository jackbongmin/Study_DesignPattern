package chapter3.Decorator.Condiment;

import chapter3.Actor.Beverage;

public class Milk extends CondimentDecorator {

    public Milk(Beverage beverage) {
        this.beverage = beverage;
    }

    @Override
    public String getDescription() {
        return beverage.getDescription() + ", 스팀 우유";
    }

    @Override
    public double cost() {
        return beverage.cost() + 0.10;
    }
}
