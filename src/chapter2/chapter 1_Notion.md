# chapter 1

내용: 전략패턴
작성일: 2026/08/28
중요: No
분야: 디자인패턴 스터디 (https://app.notion.com/p/3c9c9dbbf2a880ceb2f7d88404185c88?pvs=21)
유형: 헤드퍼스트 (https://app.notion.com/p/3c9c9dbbf2a880739f4cc73385614382?pvs=21)

# 전략 패턴 - 오리 시뮬레이션 게임🦆

---

### 전략 패턴 요약

1. 알고리즘군을 정의하고 각각 캡슐화하여 교환해서 사용할 수 있게 만드는 패턴
2. 클래스가 수행해야 하는 변수행동들을 독립적인 클래스로 만든뒤 필요할때마다 교체하는 설계방식
3. 게임 개발 당시 하루종일 사용하던 패턴

### 도서 내 문제 상황

1. 처음 오리 시뮬레이션 게임을 만들때 가장 직관적인 객체지향의 상속을 Is-A로 사용했다.
2. 그럴 시 추후에 변경사항이 생길 시 난관에 부딪힌다.
3. 예 시
    1. 부모 클래스 Duck()에 fly() 메서드를 추가했더니 게임 속 고무 오리와 나무 조각상까지 날아다니는 버그 발생
    2. 고무 오리 클래스에 fly()를 아무것도 안하도록 오버라이드해서 땜빵 처리
    3. 추후에 변경해야 하는 상황마다 유지보수를 할 수 없다.

### 문제를 해결한 3대 객체지향 디자인 원칙

1. 달라지는 부분을 찾아내고 달라지지 않는 부분과 분리한다.
    1. 오리마다 달라지는 부분인 나느 행동과 소리내는 행동을 Duck 클래스에서 완전히 뜯어내며 별도의 클래스 집합으로 캡술화했다.
2. 구현보다는 인터페이스에 맞춰서 프로그래밍한다.
    1. Duck클래스 안에는 특정 클래스(FlyWithWings)가 아니라, 상위 인터페이스인 FlyBehavior타입의 변수를 둔다. 이렇게 하면 다형성 덕분에 인터페이스를 구현한 어떤 부분이든 장착 가능하다.
3. 상속보다는 합성을 활용한다.
    1. A = B(Is-A) 관계인 상속 대신 A는 B를 가지고 있다(HAS - A)관계인 합성을 사용했다. 행동을 물려받는게 아니라 행동 객체를 변수로 가져다 쓰는 방식을 활용

### 구조 다이어그램 및 설계 분석

![image.png](image.png)

1. Duck : 행동을 사용하는 주체. 자신이 직접 행동을 실행하지 않고, 가지고 있는 전략 객체에게 실행을 위임한다.
2. FlyBehavior, QuackBehavior : 교체 가능한 행동들의 인터페이스를 정의한다.
3. FlyWithWings, FlyNoWay 등 : 실제로 행동이 어떻게 동작할지 구현하는 클래스들.

<aside>
📌

### 오리 시뮬레이터 게임 다이어그램

![image.png](image%201.png)

</aside>

### 게임 개발시 적용 사례

1. 객체 스위칭 시스템
    1. 무기가 검에서 활로 혹은 원거리로 바뀔 때
    2. 캐릭터가 인간형 캐릭터에서 자동차를 탔을 때 자동차 캐릭터를 움직여야할 때 등
2. FSM 및 AI 패턴
    1. 보스몬스터의 행동 패턴을 일반 몬스터들의 패턴과 차별화를 두어 설계할 때 유용하다.
    2. 현재상태에 따라 업데이트로직이 달라져야하는데 각 상태를 독립한 전략 클래스로 만들어두면 조건문 떡칠을 피하고 사애 전이를 깔끔하게 객체 교체로 처리할 수 있다.
3. 오브젝트 풀링
    1. 메모리 관리를 위해 오브젝트 풀을 사용할 때 풀이 꽉찼을떄의 처리방식을 전략패턴으로 ㅈ분리해두면 상황에 맞는 최적화 전략을 쉽게 뗐다 붙였다 할 수 있다.

### 장점과 단점 요약

1. 장점
    1. 개방 폐쇄의 원칙 준수 / 새로운 행동 패턴이 추가되어도 기존 코드를 수정할 필요가 없다.
    2. 런타임에 언제든지 객체의 행동을 동적으로 변경할 수 있다.
    3. 상속을 쓰지 않음으로 부모 자식간의 의존성이 사라진다.
2. 단점
    1. 애플리케이션에 들어가는 전체 클래스와 객체의 수가 늘어난다.
    2. 클라이언트가 각 전략의 차이점을 명확히 이해하고 있어야만 올바른 코드가 꼬이지 않는다.

<aside>
📌

- **abstract관련 공부**
    
    ### Duck과 display()에 abstract가 붙은 이유
    
    - 흔한 오해: "자식 클래스가 부모 것을 갖다 쓰려고 abstract를 붙였다?" ❌
    • 실제 이유: "부모는 뼈대(이름)만 둘 테니, 자식 클래스 네가 무조건 직접 구현해라!" 하고 강제하기 위함. ⭕
    
    #### 왜 display()는 전략 패턴(인터페이스)을 안 썼을까?
    
    - 날기/울기 (fly, quack) → 전략 패턴 적용
    • 오리마다 행동이 다르고, 다른 오리끼리 같은 행동을 공유할 수 있음 (예: 못 나는 오리들은 FlyNoWay 재사용).
    • 런타임에 행동을 동적으로 갈아끼울 필요가 있음 (setFlyBehavior).
    • 외모 (display) → 추상 메서드 (abstract) 적용
    • 모든 오리는 생김새가 100% 제각각이라 코드를 공유할 일이 없음.
    • 런타임에 외모 알고리즘을 바꿀 일도 없음.
    • 따라서 별도 인터페이스 없이 자식이 직접 @Override하도록 강제하는 것만으로 충분함.
    
    ──────
    
    ### 자바 문법 규칙: 추상 메서드와 추상 클래스의 관계
    
    | 조건 | 규칙 | 이유 |
    | --- | --- | --- |
    | 추상 메서드가 1개라도 있는 경우 | 클래스에 abstract 필수 | 미완성 메서드가 있으므로 new로 직접 객체를 만들면 안됨 (컴파일 에러로 강제) |
    | 추상 메서드가 하나도 없는 경우 | 클래스에 abstract 선택 가능 | 메서드는 다 완성되었더라도, 순수하게 상속용으로만 쓰고 직접 객체 생성을 막고 싶을 때 사용 |
    
    ### Duck과 display에서 abstract를 지우면 생기는 일
    
    #### ① 문법적인 컴파일 에러 (당장 발생)
    
    ```java
    public class Duck {
        public void display(); // ❌ 컴파일 에러: Missing method body, or declare abstract
    }
    ```
    
    - 일반 메서드는 반드시 몸통({ ... })이 있어야 하므로 컴파일 자체가 실패함.
    
    #### ② 빈 본문({ })을 만들어 에러를 막았을 때 생기는 문제
    
    - 문제 1: 자식 클래스의 구현 누락 버그 (침묵하는 버그)
        - abstract가 없으면 자식 클래스(예: RubberDuck)를 만들 때 display() 구현을 깜빡해도 컴파일러가 안 잡아줌.
        - 실행 시 오리 모습이 안 나오거나 엉뚱한 기본값이 출력됨.
    - 문제 2: 불완전한 객체 생성 허용 → 런타임 에러(NPE) 발생
        - Duck duck = new Duck();으로 정체불명의 오리 생성이 가능해짐.
        - 부모 생성자는 행동 인터페이스(flyBehavior)를 초기화하지 않으므로, duck.performFly() 호출 시
        **NullPointerException**이 발생함.
    
    ──────
    
    ### 4. Duck.java 속 3가지 메서드 설계 비교
    
    ```java
    public abstract class Duck {
        // 1. 전략 패턴 (Composition): 알고리즘 캡슐화 + 런타임 교체 가능
        protected FlyBehavior flyBehavior;
    
        // 2. 추상 메서드 (abstract): 자식 클래스에게 고유한 구현을 "강제"
        public abstract void display();
    
        // 3. 일반 메서드: 모든 자식 클래스가 그대로 물려받아 "재사용"
        public void swim() {
            System.out.println("모든 오리는 물에 뜹니다.");
        }
    }
    ```
    
    - swim() (일반 메서드): 변하지 않는 공통 기능 → 부모가 구현해서 자식에게 재사용 제공.
    - display() (추상 메서드): 오리마다 완전히 다른 고유 기능 → 부모는 선언만 하고 자식에게 구현 강제.
    - performFly() / performQuack() (전략 패턴): 오리마다 다르고 공유 및 동적 변경이 필요한 기능 → 인터페이스로분리(구성).
</aside>

---

### 📂 chapter1 폴더

- **DuckSimulator.java**
    
    ```java
    package chapter1;
    
    import chapter1.Actor.Duck;
    import chapter1.Actor.MallardDuck;
    import chapter1.Actor.ModelDuck;
    import chapter1.Interface.Fly.FlyRocketPowered;
    
    public class DuckSimulator {
    
        public static void main(String[] args){
            Duck mallard = new MallardDuck();
            mallard.performQuack();
            mallard.performFly();
    
            Duck model = new ModelDuck();
            model.performFly();
            model.setFlyBehavior(new FlyRocketPowered());
            model.performFly();
        }
    }
    
    ```
    

### 📂 chapter1.Actor 폴더

- **Duck.java**
    
    ```java
    package chapter1.Actor;
    
    import chapter1.Interface.Fly.FlyBehavior;
    import chapter1.Interface.Quack.QuackBehavior;
    
    public abstract class Duck {
    
        protected FlyBehavior flyBehavior;
        protected  QuackBehavior quackBehavior;
    
        public Duck() {};
    
        public abstract void display();
    
        public void performFly(){
            flyBehavior.fly();
        }
    
        public void performQuack(){
            quackBehavior.quack();
        }
        public void swim(){
            System.out.println("모든 오리는 물에 뜹니다. 가짜 오리도 뜨죠");
        }
    
        public void setFlyBehavior(FlyBehavior fb){
            flyBehavior = fb;
        }
    
        public void setQuackBehavior(QuackBehavior qb){
            quackBehavior = qb;
        }
    }
    
    ```
    
- **MallardDuck.java**
    
    ```java
    package chapter1.Actor;
    
    import chapter1.Interface.Fly.FlyWithWings;
    import chapter1.Interface.Quack.Quack;
    
    public class MallardDuck extends Duck{
        public MallardDuck(){
            flyBehavior = new FlyWithWings();
            quackBehavior = new Quack();
        }
    
        @Override
        public void display(){
            System.out.println("저는 물오리입니다");
        }
    }
    
    ```
    
- **ModelDuck.java**
    
    ```java
    package chapter1.Actor;
    
    import chapter1.Interface.Fly.FlyNoWay;
    import chapter1.Interface.Quack.Quack;
    
    public class ModelDuck extends Duck{
        public ModelDuck(){
            flyBehavior = new FlyNoWay();
            quackBehavior = new Quack();
        }
    
        @Override
        public void display(){
            System.out.println("저는 모형 오리입니다.");
        }
    }
    
    ```
    

### 📂 chapter1.Interface.Fly

- **FlyBehavior.java**
    
    ```java
    package chapter1.Interface.Fly;
    
    public interface FlyBehavior {
        void fly();
    }
    
    ```
    
- **FlyWithWings.java**
    
    ```java
    packagechapter1.Interface.Fly;
    
    public classFlyWithWings implementsFlyBehavior{
        @Overridepublic voidfly(){
            System.out.println("날고 있어요!!");
        }
    }
    ```
    
- **FlyNoWay.java**
    
    ```java
    packagechapter1.Interface.Fly;
    
    public classFlyNoWay implementsFlyBehavior{
        @Overridepublic voidfly(){
            System.out.println("저는 못날아요");
        }
    }
    ```
    
- **FlyRocketPowered.java**
    
    ```java
    packagechapter1.Interface.Fly;
    
    public classFlyRocketPowered implementsFlyBehavior{
    
        public voidfly(){
            System.out.println("로켓 추진으로 날아갑니다");
        }
    }
    ```
    

### 📂 chapter1.Interface.Quack 폴더

- **QuackBehavior.java**
    
    ```java
    package chapter1.Interface.Quack;
    
    public interface QuackBehavior {
        void quack();
    }
    
    ```
    
- **Quack.java**
    
    ```java
    package chapter1.Interface.Quack;
    
    public class Quack implements QuackBehavior{
        @Override
        public void quack(){
            System.out.println("꽥");
        }
    }
    
    ```
    
- **Squeak.java**
    
    ```java
    package chapter1.Interface.Quack;
    
    public class Squeak implements QuackBehavior{
        @Override
        public void quack(){
            System.out.println("삑");
        }
    }
    
    ```
    
- **MuteQuack.java**
    
    ```java
    package chapter1.Interface.Quack;
    
    public class MuteQuack implements QuackBehavior{
        @Override
        public void quack(){
            System.out.println("<< 조용~ >>");
        }
    }
    
    ```