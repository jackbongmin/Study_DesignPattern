package chapter6.Invoker;

import chapter6.Command.NoCommand;
import chapter6.Interface.Command;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * [Invoker 2: 7개 슬롯 + Undo/History 스택을 갖춘 만능 IoT 리모컨 (RemoteControl)]
 * 
 * 헤드퍼스트 디자인 패턴 Chapter 06의 핵심 호출자(Invoker) 클래스.
 * 1. 7개의 프로그래밍 가능한 슬롯(onCommands[7], offCommands[7])을 제공한다.
 * 2. 생성자에서 모든 슬롯과 undoCommand를 NoCommand(Null Object)로 초기화하여
 *    버튼 클릭 시 불필요한 null 체크 분기문을 완전히 제거한다.
 * 3. 직전 작업 1단계 취소(undoButtonWasPushed)뿐만 아니라,
 *    Deque(Stack) 기반 히스토리를 통해 다단계 연속 작업 취소(undoHistoryStack)까지 지원한다.
 */
public class RemoteControl {
    private static final int SLOT_COUNT = 7;

    private final Command[] onCommands;
    private final Command[] offCommands;
    private Command undoCommand;
    private final Deque<Command> historyStack;

    public RemoteControl() {
        onCommands = new Command[SLOT_COUNT];
        offCommands = new Command[SLOT_COUNT];
        historyStack = new ArrayDeque<>();

        Command noCommand = new NoCommand();
        for (int i = 0; i < SLOT_COUNT; i++) {
            onCommands[i] = noCommand;
            offCommands[i] = noCommand;
        }
        undoCommand = noCommand;
    }

    public void setCommand(int slot, Command onCommand, Command offCommand) {
        if (slot < 0 || slot >= SLOT_COUNT) {
            throw new IllegalArgumentException("슬롯 번호는 0부터 " + (SLOT_COUNT - 1) + " 사이여야 합니다.");
        }
        onCommands[slot] = onCommand;
        offCommands[slot] = offCommand;
    }

    public void onButtonWasPushed(int slot) {
        onCommands[slot].execute();
        undoCommand = onCommands[slot];
        historyStack.push(onCommands[slot]);
    }

    public void offButtonWasPushed(int slot) {
        offCommands[slot].execute();
        undoCommand = offCommands[slot];
        historyStack.push(offCommands[slot]);
    }

    /**
     * 헤드퍼스트 기본 사양: 마지막에 실행된 단일 커맨드를 취소한다.
     */
    public void undoButtonWasPushed() {
        System.out.print("↩️ [UNDO 버튼 클릭] ");
        undoCommand.undo();
        if (!historyStack.isEmpty()) {
            historyStack.pop();
        }
    }

    /**
     * 심화 확장: 스택(Stack)에 기록된 히스토리를 역순(LIFO)으로 꺼내며 다단계 Undo를 수행한다.
     */
    public void undoFromHistoryStack() {
        if (historyStack.isEmpty()) {
            System.out.println("↩️ [History UNDO] 더 이상 취소할 작업 이력이 없습니다.");
            return;
        }
        Command lastCommand = historyStack.pop();
        System.out.print("↩️ [History UNDO (" + lastCommand.getClass().getSimpleName() + ")] ");
        lastCommand.undo();
        undoCommand = historyStack.isEmpty() ? new NoCommand() : historyStack.peek();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("\n------ 🎛️ 만능 IoT 리모컨 슬롯 설정 상태 ------\n");
        for (int i = 0; i < onCommands.length; i++) {
            sb.append(String.format("[슬롯 %d] %-28s | %-28s%n",
                    i,
                    onCommands[i].getClass().getSimpleName(),
                    offCommands[i].getClass().getSimpleName()));
        }
        sb.append(String.format("[UNDO]   %s (히스토리 스택 크기: %d)%n",
                undoCommand.getClass().getSimpleName(),
                historyStack.size()));
        sb.append("-----------------------------------------------");
        return sb.toString();
    }
}
