package chapter6.Command.Party;

import chapter6.Interface.Command;
import chapter6.Receiver.Hottub;

/**
 * [ConcreteCommand: 온수 욕조 종료 커맨드]
 */
public class HottubOffCommand implements Command {
    private final Hottub hottub;

    public HottubOffCommand(Hottub hottub) {
        this.hottub = hottub;
    }

    @Override
    public void execute() {
        hottub.jetsOff();
        hottub.setTemperature(36);
        hottub.off();
    }

    @Override
    public void undo() {
        hottub.on();
        hottub.setTemperature(40);
        hottub.circulate();
        hottub.jetsOn();
    }
}
