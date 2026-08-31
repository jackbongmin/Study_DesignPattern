package chapter1;

import chapter1.Actor.Duck;
import chapter1.Actor.MallardDuck;

public class DuckSimulator {

    public static void main(String[] args){
        Duck mallard = new MallardDuck();
        mallard.performQuack();
        mallard.performFly();
    }
}
