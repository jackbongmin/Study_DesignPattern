package chapter6.Interface;

/**
 * [헤드퍼스트 디자인 패턴 Chapter 06: 커맨드 패턴 (Command Pattern)]
 * 
 * 모든 커맨드 객체가 구현해야 하는 핵심 인터페이스.
 * 요청(호출) 자체를 캡슐화하여 호출자(Invoker: 리모컨)와 수신자(Receiver: 가전제품)를 완전히 분리한다.
 * 
 * - execute(): 캡슐화된 리시버의 행동을 실행한다.
 * - undo(): 직전에 실행한 작업을 이전 상태로 되돌린다(실행 취소).
 */
public interface Command {
    void execute();
    void undo();
}
