package chapter6.Command.Garage;

import chapter6.Interface.Command;
import chapter6.Receiver.GarageDoor;

/**
 * [ConcreteCommand: 차고 문 열기 커맨드]
 */
public class GarageDoorUpCommand implements Command {
    private final GarageDoor garageDoor;

    public GarageDoorUpCommand(GarageDoor garageDoor) {
        this.garageDoor = garageDoor;
    }

    @Override
    public void execute() {
        garageDoor.up();
    }

    @Override
    public void undo() {
        garageDoor.down();
    }
}
