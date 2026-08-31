package chapter1.Interface.Quack;

public class Squeak implements QuackBehavior{
    @Override
    public void quack(){
        System.out.println("삑");
    }
}
