package chapter6.Command;

import chapter6.Interface.Command;

/**
 * [Composite + Command: MacroCommand (매크로 커맨드)]
 * 
 * 여러 개의 커맨드 배열을 내부에 보관하고 있다가,
 * 한 번의 버튼 클릭(execute)으로 소속된 모든 커맨드를 순차적으로 일괄 실행한다.
 * 
 * ★ 핵심 포인트: undo() 실행 시에는 작업 간의 선후 의존성 충돌을 막기 위해
 * 반드시 실행 순서의 '역순(Reverse Order: 마지막 인덱스 -> 0번 인덱스)'으로 취소해야 한다!
 */
public class MacroCommand implements Command {
    private final Command[] commands;

    public MacroCommand(Command[] commands) {
        this.commands = commands;
    }

    @Override
    public void execute() {
        for (int i = 0; i < commands.length; i++) {
            commands[i].execute();
        }
    }

    @Override
    public void undo() {
        // 역순(LIFO)으로 실행 취소하여 상태 복원 무결성 보장
        for (int i = commands.length - 1; i >= 0; i--) {
            commands[i].undo();
        }
    }
}
