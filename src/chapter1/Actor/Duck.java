package chapter1.Actor;

import chapter1.Interface.Fly.FlyBehavior;
import chapter1.Interface.Quack.QuackBehavior;

public abstract class Duck {

    protected FlyBehavior flyBehavior;
    protected  QuackBehavior quackBehavior;

    public Duck() {};

    public abstract void display();

    public void performFly(){
        flyBehavior.fly();
    }

    public void performQuack(){
        quackBehavior.quack();
    }
    public void swim(){
        System.out.println("모든 오리는 물에 뜹니다. 가짜 오리도 뜨죠");
    }
}
