package chapter3.Actor;

public abstract class Beverage {
    public enum Size { TALL, GRANDE, VENTI }
    private Size size = Size.TALL;
    protected String description = "제목 없음";

    public String getDescription() {
        return description;
    }

    public void setSize(Size size) {
        this.size = size;
    }

    public Size getSize() {
        return this.size;
    }

    public abstract double cost();
}
