package chapter6.Command.Garage;

import chapter6.Interface.Command;
import chapter6.Receiver.GarageDoor;

/**
 * [ConcreteCommand: 차고 문 닫기 커맨드]
 */
public class GarageDoorDownCommand implements Command {
    private final GarageDoor garageDoor;

    public GarageDoorDownCommand(GarageDoor garageDoor) {
        this.garageDoor = garageDoor;
    }

    @Override
    public void execute() {
        garageDoor.down();
    }

    @Override
    public void undo() {
        garageDoor.up();
    }
}
