package chapter6;

import chapter6.Command.CeilingFan.CeilingFanHighCommand;
import chapter6.Command.CeilingFan.CeilingFanLowCommand;
import chapter6.Command.CeilingFan.CeilingFanMediumCommand;
import chapter6.Command.CeilingFan.CeilingFanOffCommand;
import chapter6.Command.Garage.GarageDoorDownCommand;
import chapter6.Command.Garage.GarageDoorUpCommand;
import chapter6.Command.Light.LightDimCommand;
import chapter6.Command.Light.LightOffCommand;
import chapter6.Command.Light.LightOnCommand;
import chapter6.Command.MacroCommand;
import chapter6.Command.Party.HottubOffCommand;
import chapter6.Command.Party.HottubOnCommand;
import chapter6.Command.Party.TVOffCommand;
import chapter6.Command.Party.TVOnCommand;
import chapter6.Command.Stereo.StereoOffCommand;
import chapter6.Command.Stereo.StereoOnWithCDCommand;
import chapter6.Interface.Command;
import chapter6.Invoker.LambdaRemoteControl;
import chapter6.Invoker.RemoteControl;
import chapter6.Invoker.SimpleRemoteControl;
import chapter6.Receiver.CeilingFan;
import chapter6.Receiver.GarageDoor;
import chapter6.Receiver.Hottub;
import chapter6.Receiver.Light;
import chapter6.Receiver.Stereo;
import chapter6.Receiver.TV;

public class RemoteLoader {

    public static void main(String[] args) {
        System.out.println("==========================================================================");
        System.out.println(" 🎛️ 헤드퍼스트 디자인 패턴 Chapter 06: 호출 캡슐화하기 (커맨드 패턴) 🎛️");
        System.out.println("==========================================================================");

        // ---------------------------------------------------------------------
        // PART 1. SimpleRemoteControl (슬롯 1개짜리 미니 리모컨 기초 테스트)
        // ---------------------------------------------------------------------
        System.out.println("\n[PART 1. 미니 리모컨 (SimpleRemoteControl) - 첫 번째 커맨드 객체 테스트]");
        SimpleRemoteControl simpleRemote = new SimpleRemoteControl();

        Light kitchenLight = new Light("주방");
        GarageDoor mainGarage = new GarageDoor("본채");

        LightOnCommand kitchenLightOn = new LightOnCommand(kitchenLight);
        GarageDoorUpCommand garageOpen = new GarageDoorUpCommand(mainGarage);

        System.out.println(">>> 슬롯에 '주방 조명 켜기' 커맨드를 장착하고 버튼을 누릅니다.");
        simpleRemote.setCommand(kitchenLightOn);
        simpleRemote.buttonWasPressed();

        System.out.println(">>> 동일한 슬롯에 '차고 문 열기' 커맨드로 동적 교체 후 버튼을 누릅니다.");
        simpleRemote.setCommand(garageOpen);
        simpleRemote.buttonWasPressed();

        // ---------------------------------------------------------------------
        // PART 2. 7개 슬롯 만능 리모컨 (RemoteControl) & NoCommand 널 객체 패턴
        // ---------------------------------------------------------------------
        System.out.println("\n==========================================================================");
        System.out.println("[PART 2. 7개 슬롯 만능 IoT 리모컨 & NoCommand(널 객체 패턴) 검증]");
        System.out.println("==========================================================================");

        RemoteControl remoteControl = new RemoteControl();

        // 가전제품(Receivers) 생성
        Light livingRoomLight = new Light("거실");
        CeilingFan livingRoomFan = new CeilingFan("거실");
        GarageDoor garageDoor = new GarageDoor("차고");
        Stereo stereo = new Stereo("거실");

        // 커맨드 객체(ConcreteCommands) 생성
        LightOnCommand livingRoomLightOn = new LightOnCommand(livingRoomLight);
        LightOffCommand livingRoomLightOff = new LightOffCommand(livingRoomLight);
        LightOnCommand kitchenLightOffCmd = new LightOnCommand(kitchenLight);
        LightOffCommand kitchenLightOff = new LightOffCommand(kitchenLight);

        CeilingFanHighCommand ceilingFanHigh = new CeilingFanHighCommand(livingRoomFan);
        CeilingFanOffCommand ceilingFanOff = new CeilingFanOffCommand(livingRoomFan);

        GarageDoorUpCommand garageDoorUp = new GarageDoorUpCommand(garageDoor);
        GarageDoorDownCommand garageDoorDown = new GarageDoorDownCommand(garageDoor);

        StereoOnWithCDCommand stereoOnWithCD = new StereoOnWithCDCommand(stereo);
        StereoOffCommand stereoOff = new StereoOffCommand(stereo);

        // 리모컨 슬롯(0~4번)에 커맨드 로드 (5, 6번 슬롯은 비워둠 -> NoCommand 유지)
        remoteControl.setCommand(0, livingRoomLightOn, livingRoomLightOff);
        remoteControl.setCommand(1, kitchenLightOffCmd, kitchenLightOff);
        remoteControl.setCommand(2, ceilingFanHigh, ceilingFanOff);
        remoteControl.setCommand(3, stereoOnWithCD, stereoOff);
        remoteControl.setCommand(4, garageDoorUp, garageDoorDown);

        // 리모컨 슬롯 설정 출력
        System.out.println(remoteControl);

        // 각 슬롯 ON / OFF 버튼 순차 클릭
        for (int i = 0; i <= 4; i++) {
            System.out.println("\n>>> [슬롯 " + i + "] ON 버튼 클릭 -> OFF 버튼 클릭");
            remoteControl.onButtonWasPushed(i);
            remoteControl.offButtonWasPushed(i);
        }

        System.out.println("\n>>> [빈 슬롯 6번 클릭 테스트] NoCommand 덕분에 null 체크 없이도 NPE가 발생하지 않습니다.");
        remoteControl.onButtonWasPushed(6);
        remoteControl.offButtonWasPushed(6);
        System.out.println("-> (아무 동작도 일어나지 않고 안전하게 통과!)");

        // ---------------------------------------------------------------------
        // PART 3. 상태 기반 작업 취소 (Stateful Undo - 천장 선풍기 속도 복원 & 다단계 스택)
        // ---------------------------------------------------------------------
        System.out.println("\n==========================================================================");
        System.out.println("[PART 3. 상태 기반 UNDO (CeilingFan 속도 복원) 및 다단계 History 스택 검증]");
        System.out.println("==========================================================================");

        RemoteControl undoRemote = new RemoteControl();
        CeilingFan bedroomFan = new CeilingFan("안방");

        CeilingFanLowCommand fanLow = new CeilingFanLowCommand(bedroomFan);
        CeilingFanMediumCommand fanMedium = new CeilingFanMediumCommand(bedroomFan);
        CeilingFanHighCommand fanHigh = new CeilingFanHighCommand(bedroomFan);
        CeilingFanOffCommand fanOff = new CeilingFanOffCommand(bedroomFan);

        undoRemote.setCommand(0, fanLow, fanOff);
        undoRemote.setCommand(1, fanMedium, fanOff);
        undoRemote.setCommand(2, fanHigh, fanOff);

        System.out.println("\n1) 선풍기를 '중(MEDIUM, 2단)'으로 켭니다.");
        undoRemote.onButtonWasPushed(1);
        System.out.println("2) 선풍기를 '강(HIGH, 3단)'으로 변경합니다. (내부에 prevSpeed = MEDIUM 저장됨)");
        undoRemote.onButtonWasPushed(2);
        System.out.println("3) UNDO 버튼을 눌러 이전 속도('중')로 되돌아가는지 확인합니다.");
        undoRemote.undoButtonWasPushed();

        System.out.println("\n--- [심화: History Stack을 이용한 연속 다단계 UNDO 테스트] ---");
        Light studyLight = new Light("서재");
        undoRemote.setCommand(3, new LightOnCommand(studyLight), new LightOffCommand(studyLight));
        undoRemote.setCommand(4, new LightDimCommand(studyLight, 30), new LightOffCommand(studyLight));

        System.out.println(">>> 서재 조명 켜기(100%) -> 서재 조명 디밍(30%) -> 안방 선풍기 끄기(OFF) 연속 실행:");
        undoRemote.onButtonWasPushed(3); // 서재 조명 100%
        undoRemote.onButtonWasPushed(4); // 서재 조명 30% (prevLevel=100 저장)
        undoRemote.offButtonWasPushed(1); // 안방 선풍기 OFF (prevSpeed=MEDIUM 저장)

        System.out.println("\n>>> History Stack에서 최근 작업 3개를 역순(LIFO)으로 연속 취소합니다:");
        undoRemote.undoFromHistoryStack(); // 1. 안방 선풍기 다시 MEDIUM으로 복구
        undoRemote.undoFromHistoryStack(); // 2. 서재 조명 다시 100%로 복구
        undoRemote.undoFromHistoryStack(); // 3. 서재 조명 OFF로 복구

        // ---------------------------------------------------------------------
        // PART 4. 매크로 커맨드 (MacroCommand - 원클릭 파티 모드!)
        // ---------------------------------------------------------------------
        System.out.println("\n==========================================================================");
        System.out.println("[PART 4. 매크로 커맨드 (MacroCommand) - 원클릭 파티 모드 ON / OFF / UNDO]");
        System.out.println("==========================================================================");

        TV livingRoomTV = new TV("거실");
        Hottub hottub = new Hottub();

        LightDimCommand partyLightDim = new LightDimCommand(livingRoomLight, 20);
        TVOnCommand tvOn = new TVOnCommand(livingRoomTV);
        HottubOnCommand hottubOn = new HottubOnCommand(hottub);

        TVOffCommand tvOff = new TVOffCommand(livingRoomTV);
        HottubOffCommand hottubOff = new HottubOffCommand(hottub);

        // 파티 ON 매크로 묶음 & 파티 OFF 매크로 묶음 생성
        Command[] partyOn = { livingRoomLightOn, partyLightDim, stereoOnWithCD, tvOn, hottubOn };
        Command[] partyOff = { livingRoomLightOff, stereoOff, tvOff, hottubOff };

        MacroCommand partyOnMacro = new MacroCommand(partyOn);
        MacroCommand partyOffMacro = new MacroCommand(partyOff);

        remoteControl.setCommand(5, partyOnMacro, partyOffMacro);

        System.out.println("\n🎉 >>> [슬롯 5] 파티 모드 ON 버튼을 누릅니다! (5개 작업 일괄 실행)");
        remoteControl.onButtonWasPushed(5);

        System.out.println("\n↩️ >>> 파티 모드 UNDO 버튼을 누릅니다! (역순으로 안전하게 원상 복구)");
        remoteControl.undoButtonWasPushed();

        // ---------------------------------------------------------------------
        // PART 5. 자바 8+ 람다 표현식과 메서드 레퍼런스를 활용한 리모컨 간소화
        // ---------------------------------------------------------------------
        System.out.println("\n==========================================================================");
        System.out.println("[PART 5. 자바 8+ 람다 표현식 & 메서드 레퍼런스 (LambdaRemoteControl)]");
        System.out.println("구상 Command 클래스 생성 없이 메서드 레퍼런스(light::on)와 람다식으로 직결");
        System.out.println("==========================================================================");

        LambdaRemoteControl lambdaRemote = new LambdaRemoteControl();

        // 메서드 레퍼런스(Method Reference)와 람다 표현식(Lambda Expression) 주입
        lambdaRemote.setCommand(0, livingRoomLight::on, livingRoomLight::off);
        lambdaRemote.setCommand(1,
                () -> {
                    stereo.on();
                    stereo.setCd();
                    stereo.setVolume(11);
                },
                stereo::off
        );

        System.out.println("\n>>> [람다 슬롯 0] 거실 조명 ON / OFF (메서드 레퍼런스 호출)");
        lambdaRemote.onButtonWasPushed(0);
        lambdaRemote.offButtonWasPushed(0);

        System.out.println("\n>>> [람다 슬롯 1] 거실 오디오 복합 ON / OFF (람다 블록 호출)");
        lambdaRemote.onButtonWasPushed(1);
        lambdaRemote.offButtonWasPushed(1);

        System.out.println("\n==========================================================================");
        System.out.println(" 🎉 Chapter 06 커맨드 패턴 종합 실습 및 검증 완료!");
        System.out.println("==========================================================================");
    }
}
