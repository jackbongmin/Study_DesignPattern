package chapter4.AbstractFactory.Interface;

/**
 * 추상 팩토리 인터페이스
 * 서로 연관되거나 의존적인 객체들의 군(원재료 family)을 생성하기 위한 규격 제공
 */
public interface PizzaIngredientFactory {
    Dough createDough();
    Sauce createSauce();
    Cheese createCheese();
    Veggies[] createVeggies();
    Pepperoni createPepperoni();
    Clams createClam();
}
