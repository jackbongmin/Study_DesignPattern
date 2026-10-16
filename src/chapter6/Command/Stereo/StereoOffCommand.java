package chapter6.Command.Stereo;

import chapter6.Interface.Command;
import chapter6.Receiver.Stereo;

/**
 * [ConcreteCommand: 오디오 끄기 커맨드]
 */
public class StereoOffCommand implements Command {
    private final Stereo stereo;

    public StereoOffCommand(Stereo stereo) {
        this.stereo = stereo;
    }

    @Override
    public void execute() {
        stereo.off();
    }

    @Override
    public void undo() {
        stereo.on();
        stereo.setCd();
        stereo.setVolume(11);
    }
}
