package chapter6.Command.Party;

import chapter6.Interface.Command;
import chapter6.Receiver.Hottub;

/**
 * [ConcreteCommand: 온수 욕조 가동 커맨드]
 * 파티 모드 실행 시 온수 욕조 온도를 40도로 올리고 버블 순환 및 제트를 가동한다.
 */
public class HottubOnCommand implements Command {
    private final Hottub hottub;

    public HottubOnCommand(Hottub hottub) {
        this.hottub = hottub;
    }

    @Override
    public void execute() {
        hottub.on();
        hottub.setTemperature(40);
        hottub.circulate();
        hottub.jetsOn();
    }

    @Override
    public void undo() {
        hottub.jetsOff();
        hottub.setTemperature(36);
        hottub.off();
    }
}
