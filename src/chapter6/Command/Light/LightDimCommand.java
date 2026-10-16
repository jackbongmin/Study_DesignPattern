package chapter6.Command.Light;

import chapter6.Interface.Command;
import chapter6.Receiver.Light;

/**
 * [ConcreteCommand: 조명 밝기 조절(Dimming) 커맨드]
 * 변경 전 밝기(prevLevel)를 저장해 두었다가 undo() 시 원래 밝기로 복원하는 상태 기반 커맨드.
 */
public class LightDimCommand implements Command {
    private final Light light;
    private final int targetLevel;
    private int prevLevel;

    public LightDimCommand(Light light, int targetLevel) {
        this.light = light;
        this.targetLevel = targetLevel;
    }

    @Override
    public void execute() {
        prevLevel = light.getLevel();
        light.dim(targetLevel);
    }

    @Override
    public void undo() {
        light.dim(prevLevel);
    }
}
