package chapter6.Command.Stereo;

import chapter6.Interface.Command;
import chapter6.Receiver.Stereo;

/**
 * [ConcreteCommand: 오디오 켜기 및 CD 재생 자동화 커맨드]
 * 
 * 단순히 메서드 하나만 부르는 것이 아니라,
 * 1) 오디오 전원 켜기 (stereo.on())
 * 2) CD 재생 모드 설정 (stereo.setCd())
 * 3) 볼륨을 11로 맞추기 (stereo.setVolume(11))
 * 라는 일련의 복합 동작을 하나의 execute() 안에 캡슐화한다.
 */
public class StereoOnWithCDCommand implements Command {
    private final Stereo stereo;

    public StereoOnWithCDCommand(Stereo stereo) {
        this.stereo = stereo;
    }

    @Override
    public void execute() {
        stereo.on();
        stereo.setCd();
        stereo.setVolume(11);
    }

    @Override
    public void undo() {
        stereo.off();
    }
}
