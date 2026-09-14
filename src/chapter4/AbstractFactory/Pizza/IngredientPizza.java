package chapter4.AbstractFactory.Pizza;

import chapter4.AbstractFactory.Interface.Cheese;
import chapter4.AbstractFactory.Interface.Clams;
import chapter4.AbstractFactory.Interface.Dough;
import chapter4.AbstractFactory.Interface.Pepperoni;
import chapter4.AbstractFactory.Interface.Sauce;
import chapter4.AbstractFactory.Interface.Veggies;

/*
 * 원재료 팩토리를 주입받아 완성되는 피자 추상 클래스
 * prepare() 메서드가 추상 메서드로서 구체 피자에서 원재료를 조달받음
 */
public abstract class IngredientPizza {
    protected String name;

    protected Dough dough;
    protected Sauce sauce;
    protected Veggies[] veggies;
    protected Cheese cheese;
    protected Pepperoni pepperoni;
    protected Clams clam;

    // 원재료 공장에서 재료들을 가져와 준비하는 핵심 추상 메서드
    public abstract void prepare();

    public void bake() {
        System.out.println("175도에서 25분간 굽기 (Baking...)");
    }

    public void cut() {
        System.out.println("피자를 대각선으로 자르기");
    }

    public void box() {
        System.out.println("상자에 피자 담기");
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        result.append("---- ").append(name).append(" ----\n");
        if (dough != null) {
            result.append("도우: ").append(dough.getName()).append("\n");
        }
        if (sauce != null) {
            result.append("소스: ").append(sauce.getName()).append("\n");
        }
        if (cheese != null) {
            result.append("치즈: ").append(cheese.getName()).append("\n");
        }
        if (veggies != null && veggies.length > 0) {
            result.append("야채 토핑: ");
            for (int i = 0; i < veggies.length; i++) {
                result.append(veggies[i].getName());
                if (i < veggies.length - 1) {
                    result.append(", ");
                }
            }
            result.append("\n");
        }
        if (clam != null) {
            result.append("조개: ").append(clam.getName()).append("\n");
        }
        if (pepperoni != null) {
            result.append("페퍼로니: ").append(pepperoni.getName()).append("\n");
        }
        return result.toString();
    }
}
