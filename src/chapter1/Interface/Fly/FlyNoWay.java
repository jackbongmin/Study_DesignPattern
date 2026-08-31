package chapter1.Interface.Fly;

public class FlyNoWay implements FlyBehavior{
    @Override
    public void fly(){
        System.out.println("저는 못날아요");
    }
}
