package chapter6.Command.Party;

import chapter6.Interface.Command;
import chapter6.Receiver.TV;

/**
 * [ConcreteCommand: TV 끄기 커맨드]
 */
public class TVOffCommand implements Command {
    private final TV tv;

    public TVOffCommand(TV tv) {
        this.tv = tv;
    }

    @Override
    public void execute() {
        tv.off();
    }

    @Override
    public void undo() {
        tv.on();
        tv.setInputChannel();
    }
}
