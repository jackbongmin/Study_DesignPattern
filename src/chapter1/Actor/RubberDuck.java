package chapter1.Actor;

import chapter1.Interface.Fly.FlyWithWings;
import chapter1.Interface.Quack.Quack;

public class RubberDuck extends Duck{
    public RubberDuck(){
        flyBehavior = new FlyWithWings();
        quackBehavior = new Quack();
    }

    @Override
    public void display() {
        System.out.println("저는 고무 오리입니다");
    }
}
