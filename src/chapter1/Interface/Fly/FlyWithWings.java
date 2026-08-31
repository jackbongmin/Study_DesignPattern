package chapter1.Interface.Fly;

public class FlyWithWings implements  FlyBehavior{
    @Override
    public void fly(){
        System.out.println("날고 있어요!!");
    }
}
