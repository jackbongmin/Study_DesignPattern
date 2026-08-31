package chapter1.Actor;

import chapter1.Interface.Fly.FlyWithWings;
import chapter1.Interface.Quack.Quack;

public class MallardDuck extends Duck{
    public MallardDuck(){
        flyBehavior = new FlyWithWings();
        quackBehavior = new Quack();
    }

    @Override
    public void display(){
        System.out.println("저는 물오리입니다");
    }
}
