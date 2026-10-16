package chapter6.Command.CeilingFan;

import chapter6.Interface.Command;
import chapter6.Receiver.CeilingFan;

/**
 * [ConcreteCommand: 천장 선풍기 '강(HIGH)' 설정 커맨드]
 * 
 * ★ 상태를 활용한 작업 취소(Stateful Undo) 구현:
 * execute()에서 속도를 변경하기 직전에 ceilingFan.getSpeed()를 호출하여
 * 이전 속도(prevSpeed)를 내부 필드에 백업해 두고,
 * undo()가 호출되면 백업된 prevSpeed 값에 따라 이전 속도 상태로 정확히 복원한다.
 */
public class CeilingFanHighCommand implements Command {
    private final CeilingFan ceilingFan;
    private int prevSpeed;

    public CeilingFanHighCommand(CeilingFan ceilingFan) {
        this.ceilingFan = ceilingFan;
    }

    @Override
    public void execute() {
        prevSpeed = ceilingFan.getSpeed(); // 상태 변경 전 직전 속도 저장
        ceilingFan.high();
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
