package chapter6.Command.Light;

import chapter6.Interface.Command;
import chapter6.Receiver.Light;

/**
 * [ConcreteCommand: 조명 켜기 커맨드]
 * Light 리시버를 합성(Composition)으로 보유하며,
 * execute() 시 조명을 켜고, undo() 시 조명을 끈다.
 */
public class LightOnCommand implements Command {
    private final Light light;

    public LightOnCommand(Light light) {
        this.light = light;
    }

    @Override
    public void execute() {
        light.on();
    }

    @Override
    public void undo() {
        light.off();
    }
}
