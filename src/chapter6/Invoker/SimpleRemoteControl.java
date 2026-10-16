package chapter6.Invoker;

import chapter6.Interface.Command;

/**
 * [Invoker 1: 슬롯 1개짜리 미니 리모컨 (SimpleRemoteControl)]
 * 
 * 커맨드 패턴의 기본 동작 원리를 확인하기 위한 가장 단순한 호출자(Invoker).
 * 리모컨은 자신이 어떤 가전제품(조명, 차고 문 등)을 제어하는지 전혀 모르며,
 * 오직 Command 인터페이스의 execute()만 호출한다.
 */
public class SimpleRemoteControl {
    private Command slot;

    public SimpleRemoteControl() {}

    public void setCommand(Command command) {
        this.slot = command;
    }

    public void buttonWasPressed() {
        if (slot != null) {
            slot.execute();
        }
    }
}
