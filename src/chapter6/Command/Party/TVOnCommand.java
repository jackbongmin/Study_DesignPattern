package chapter6.Command.Party;

import chapter6.Interface.Command;
import chapter6.Receiver.TV;

/**
 * [ConcreteCommand: TV 켜기 및 DVD 채널 설정 커맨드]
 */
public class TVOnCommand implements Command {
    private final TV tv;

    public TVOnCommand(TV tv) {
        this.tv = tv;
    }

    @Override
    public void execute() {
        tv.on();
        tv.setInputChannel();
    }

    @Override
    public void undo() {
        tv.off();
    }
}
