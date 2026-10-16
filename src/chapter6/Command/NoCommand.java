package chapter6.Command;

import chapter6.Interface.Command;

/**
 * [Null Object Pattern: NoCommand]
 * 
 * 아무 일도 하지 않는 빈(Null) 커맨드 객체.
 * 리모컨(RemoteControl)의 모든 슬롯을 초기 생성 시점에 NoCommand 인스턴스로 채워두면,
 * 버튼을 누를 때마다 if (onCommands[slot] != null) 같은 지저분한 null 체크 분기문을
 * 작성할 필요가 없어지고 NullPointerException(NPE)을 원천 차단할 수 있다.
 */
public class NoCommand implements Command {

    @Override
    public void execute() {
        // 아무 작업도 수행하지 않음 (Do Nothing)
    }

    @Override
    public void undo() {
        // 아무 작업도 취소하지 않음 (Do Nothing)
    }
}
