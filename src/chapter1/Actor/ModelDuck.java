package chapter1.Actor;

import chapter1.Interface.Fly.FlyNoWay;
import chapter1.Interface.Quack.Quack;

public class ModelDuck extends Duck{
    public ModelDuck(){
        flyBehavior = new FlyNoWay();
        quackBehavior = new Quack();
    }

    @Override
    public void display(){
        System.out.println("저는 모형 오리입니다.");
    }
}
