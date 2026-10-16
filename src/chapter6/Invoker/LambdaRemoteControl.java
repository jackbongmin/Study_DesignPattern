package chapter6.Invoker;

/**
 * [Invoker 3: 자바 8+ 람다 표현식 및 메서드 레퍼런스 전용 리모컨 (LambdaRemoteControl)]
 * 
 * 헤드퍼스트 디자인 패턴 최신 개정판에서 다루는 함수형 인터페이스 기반 리모컨.
 * 추상 메서드가 딱 하나뿐인 함수형 인터페이스(Runnable 또는 단일 메서드 Command)를 사용하면,
 * 수많은 구상 커맨드 클래스(LightOnCommand, LightOffCommand 등)를 일일이 파일로 만들지 않고도
 * 람다 표현식(() -> light.on())이나 메서드 레퍼런스(light::on)로 간결하게 주입할 수 있다.
 * 
 * ⚠️ 한계점: 추상 메서드가 2개(execute, undo)이거나 내부 상태(prevSpeed)를 저장해야 하는 경우에는
 * 단일 람다식만으로 표현할 수 없으며, 전통적인 구상 Command 클래스나 별도의 상태 클로저가 필요하다.
 */
public class LambdaRemoteControl {
    private static final int SLOT_COUNT = 7;

    private final Runnable[] onCommands;
    private final Runnable[] offCommands;

    public LambdaRemoteControl() {
        onCommands = new Runnable[SLOT_COUNT];
        offCommands = new Runnable[SLOT_COUNT];

        // Null Object Pattern을 람다식 () -> {} 한 줄로 대체!
        Runnable noCommand = () -> {};
        for (int i = 0; i < SLOT_COUNT; i++) {
            onCommands[i] = noCommand;
            offCommands[i] = noCommand;
        }
    }

    public void setCommand(int slot, Runnable onCommand, Runnable offCommand) {
        onCommands[slot] = onCommand;
        offCommands[slot] = offCommand;
    }

    public void onButtonWasPushed(int slot) {
        onCommands[slot].run();
    }

    public void offButtonWasPushed(int slot) {
        offCommands[slot].run();
    }
}
