package chapter1;

import chapter1.Actor.Duck;
import chapter1.Actor.MallardDuck;
import chapter1.Actor.ModelDuck;
import chapter1.Interface.Fly.FlyRocketPowered;

public class DuckSimulator {

    public static void main(String[] args){
        Duck mallard = new MallardDuck();
        mallard.performQuack();
        mallard.performFly();

        Duck model = new ModelDuck();
        model.performFly();
        model.setFlyBehavior(new FlyRocketPowered());
        model.performFly();
    }
}
