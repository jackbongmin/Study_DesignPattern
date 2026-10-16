package chapter6.Command.CeilingFan;

import chapter6.Interface.Command;
import chapter6.Receiver.CeilingFan;

/**
 * [ConcreteCommand: 천장 선풍기 끄기(OFF) 커맨드]
 * 선풍기를 끄기 전 속도 상태(prevSpeed)를 기억하여 undo() 시 원래 회전 속도로 복구한다.
 */
public class CeilingFanOffCommand implements Command {
    private final CeilingFan ceilingFan;
    private int prevSpeed;

    public CeilingFanOffCommand(CeilingFan ceilingFan) {
        this.ceilingFan = ceilingFan;
    }

    @Override
    public void execute() {
        prevSpeed = ceilingFan.getSpeed();
        ceilingFan.off();
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
