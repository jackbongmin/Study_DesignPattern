package chapter4;

import chapter4.Actor.Pizza.Pizza;
import chapter4.Actor.Store.ChicagoPizzaStore;
import chapter4.Actor.Store.NYPizzaStore;
import chapter4.Actor.Store.PizzaStore;
import chapter4.AbstractFactory.Pizza.IngredientPizza;
import chapter4.AbstractFactory.Store.ChicagoIngredientPizzaStore;
import chapter4.AbstractFactory.Store.IngredientPizzaStore;
import chapter4.AbstractFactory.Store.NYIngredientPizzaStore;
import chapter4.SimpleFactory.SimplePizzaFactory;
import chapter4.SimpleFactory.SimplePizzaStore;

public class PizzaTestDrive {

    public static void main(String[] args) {
        System.out.println("===============================================================");
        System.out.println(" 🍕 헤드퍼스트 디자인 패턴 Chapter 04: 객체지향 빵 굽기 (팩토리 패턴) 🍕");
        System.out.println("===============================================================");

        // ---------------------------------------------------------------------
        // 1. 간단한 팩토리 (Simple Factory) 테스트
        // ---------------------------------------------------------------------
        System.out.println("\n[PART 1. 간단한 팩토리 (Simple Factory 관용구)]");
        SimplePizzaFactory simpleFactory = new SimplePizzaFactory();
        SimplePizzaStore simpleStore = new SimplePizzaStore(simpleFactory);

        Pizza simplePizza = simpleStore.orderPizza("cheese");
        System.out.println("완성된 피자: " + simplePizza.getName());

        // ---------------------------------------------------------------------
        // 2. 팩토리 메서드 패턴 (Factory Method Pattern) 테스트
        // ---------------------------------------------------------------------
        System.out.println("\n===============================================================");
        System.out.println("[PART 2. 팩토리 메서드 패턴 (Factory Method Pattern)]");
        System.out.println("생성 작업을 서브클래스(NYPizzaStore, ChicagoPizzaStore)에 캡슐화");
        System.out.println("===============================================================");

        PizzaStore nyStore = new NYPizzaStore();
        PizzaStore chicagoStore = new ChicagoPizzaStore();

        System.out.println("\n>>> 에단(Ethan)이 뉴욕 스타일 치즈 피자를 주문합니다.");
        Pizza pizza1 = nyStore.orderPizza("cheese");
        System.out.println("에단이 주문한 피자 완성: " + pizza1.getName());

        System.out.println("\n>>> 조엘(Joel)이 시카고 스타일 치즈 피자를 주문합니다.");
        Pizza pizza2 = chicagoStore.orderPizza("cheese");
        System.out.println("조엘이 주문한 피자 완성: " + pizza2.getName());

        System.out.println("\n>>> 추가 주문: 뉴욕 페퍼로니 & 시카고 조개 피자");
        Pizza pizza3 = nyStore.orderPizza("pepperoni");
        System.out.println("피자 준비 완료: " + pizza3.getName());

        Pizza pizza4 = chicagoStore.orderPizza("clam");
        System.out.println("피자 준비 완료: " + pizza4.getName());

        // ---------------------------------------------------------------------
        // 3. 추상 팩토리 패턴 (Abstract Factory Pattern) 테스트
        // ---------------------------------------------------------------------
        System.out.println("\n===============================================================");
        System.out.println("[PART 3. 추상 팩토리 패턴 (Abstract Factory Pattern)]");
        System.out.println("원재료 제품군(PizzaIngredientFactory)을 지역별로 통제 및 품질 관리");
        System.out.println("===============================================================");

        IngredientPizzaStore nyIngredientStore = new NYIngredientPizzaStore();
        IngredientPizzaStore chicagoIngredientStore = new ChicagoIngredientPizzaStore();

        System.out.println("\n>>> 뉴욕 원재료 공장 기반 치즈 피자 제작");
        IngredientPizza afPizza1 = nyIngredientStore.orderPizza("cheese");
        System.out.println("\n[피자 상세 스펙]");
        System.out.print(afPizza1);

        System.out.println("\n>>> 시카고 원재료 공장 기반 조개 피자 제작");
        IngredientPizza afPizza2 = chicagoIngredientStore.orderPizza("clam");
        System.out.println("\n[피자 상세 스펙]");
        System.out.print(afPizza2);

        System.out.println("\n===============================================================");
        System.out.println(" 🎉 팩토리 패턴을 통한 객체지향 빵 굽기 시뮬레이션 성공적 완료! 🎉");
        System.out.println("===============================================================");
    }
}
