package chapter3;

import chapter3.Actor.Beverage;
import chapter3.Actor.DarkRoast;
import chapter3.Actor.Espresso;
import chapter3.Actor.HouseBlend;
import chapter3.Decorator.Condiment.Milk;
import chapter3.Decorator.Condiment.Mocha;
import chapter3.Decorator.Condiment.Soy;
import chapter3.Decorator.Condiment.Whip;

public class StarbuzzCoffee {

    public static void main(String[] args) {
        System.out.println("=== 1. 기본 에스프레소 (추가 첨가물 없음) ===");
        Beverage beverage = new Espresso();
        System.out.println(beverage.getDescription() + " $" + String.format("%.2f", beverage.cost()));

        System.out.println("\n=== 2. 다크 로스트 + 더블 모카 + 휘핑크림 ===");
        Beverage beverage2 = new DarkRoast();
        beverage2 = new Mocha(beverage2);
        beverage2 = new Mocha(beverage2);
        beverage2 = new Whip(beverage2);
        System.out.println(beverage2.getDescription() + " $" + String.format("%.2f", beverage2.cost()));

        System.out.println("\n=== 3. 하우스 블렌드 (벤티 사이즈) + 두유 + 모카 + 휘핑크림 ===");
        Beverage beverage3 = new HouseBlend();
        beverage3.setSize(Beverage.Size.VENTI);
        beverage3 = new Soy(beverage3);
        beverage3 = new Mocha(beverage3);
        beverage3 = new Whip(beverage3);
        System.out.println(beverage3.getDescription() + " (" + beverage3.getSize() + ") $" + String.format("%.2f", beverage3.cost()));

        System.out.println("\n=== 4. 에스프레소 + 스팀 우유 (카페 라떼) ===");
        Beverage beverage4 = new Espresso();
        beverage4 = new Milk(beverage4);
        System.out.println(beverage4.getDescription() + " $" + String.format("%.2f", beverage4.cost()));
    }
}
