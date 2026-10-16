package chapter6.Command.Light;

import chapter6.Interface.Command;
import chapter6.Receiver.Light;

/**
 * [ConcreteCommand: 조명 끄기 커맨드]
 * execute() 시 조명을 끄고, undo() 시 조명을 다시 켠다.
 */
public class LightOffCommand implements Command {
    private final Light light;

    public LightOffCommand(Light light) {
        this.light = light;
    }

    @Override
    public void execute() {
        light.off();
    }

    @Override
    public void undo() {
        light.on();
    }
}
