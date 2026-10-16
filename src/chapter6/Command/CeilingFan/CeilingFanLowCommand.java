package chapter6.Command.CeilingFan;

import chapter6.Interface.Command;
import chapter6.Receiver.CeilingFan;

/**
 * [ConcreteCommand: 천장 선풍기 '약(LOW)' 설정 커맨드]
 * 이전 속도 상태(prevSpeed)를 기억하여 undo() 시 정확히 복구한다.
 */
public class CeilingFanLowCommand implements Command {
    private final CeilingFan ceilingFan;
    private int prevSpeed;

    public CeilingFanLowCommand(CeilingFan ceilingFan) {
        this.ceilingFan = ceilingFan;
    }

    @Override
    public void execute() {
        prevSpeed = ceilingFan.getSpeed();
        ceilingFan.low();
    }

    @Override
    public void undo() {
        if (prevSpeed == CeilingFan.HIGH) {
            ceilingFan.high();
        } else if (prevSpeed == CeilingFan.MEDIUM) {
            ceilingFan.medium();
        } else if (prevSpeed == CeilingFan.LOW) {
            ceilingFan.low();
        } else if (prevSpeed == CeilingFan.OFF) {
            ceilingFan.off();
        }
    }
}
