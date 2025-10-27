# 의사 결정 기록 모음

> 고민되는 사안에 대한 의사결정 기록을 모아두는 곳

```
2025-10-23: <주제>
 - 고민: 
 - 결정: 
 - 이유:
 - Etc: 
```

## 구현

---
- [x] 2025-10-23: 팩토리 매서드에서 검증 vs 생성자 검증
  - 결정: 생성자에서 검증
  - 이유: 팩토리 매서드 추가에도 불변식 보장, 캡슐화
  - Etc: 개발 속도가 늦어져, 이미 만든 클래스는 MVP 구현 완료 리팩토링
---
- [x] 2025-10-23: 도메인 내 애그리게이트 루트가 의존하는 도메인 클래스에서도 방어적 복사가 필요할까?
  - 결정: 같은 애그리게이트 내부는 하지 않음
  - 이유: 불변 값을 기본으로 하고 있음, 같은 애그리게이트 내부라 신뢰가 충분하다고 판단
---
- [x] 2025-10-23: 불변 객체에 대한 고민(Engine) Engine의 Mileage를 가변객체로 놔도 괜찮을까?
  - 결정: 일단은 가변 객체로 둔다.
  - 이유: Engine은 값 객체가 아니라 상태이기 때문에 가변 객체가 자연스러운거 같음. 
---
- [x] 2025-10-23: (Engine)에 대한 테스트를 어떻게 할 수 있을까??
  - 결정: 페달링을 전략 패턴으로 구현하자.
  - 이유: Engine의 책임이 과하다고 생각함
---
- [x] 2025-10-23: 도메인 에그리게이트 내의 루트를 제외한 클래스의 접근제어자(public -> package private)변경
  - 결정: 리팩토링 진행
  - 이유: 접근 제어자로 막는게 좀 더 캡슐화 하는 느낌을 줄 수 있다고 생각함
  - Etc : 하더라도 리팩토링 할 때
---
- [x] 2025-10-24: View의 책임은 어디까지일까? 받은 데이터를 출력형식에 맞게 조작하는? 아니면 단순 출력만? 
  - 결정: 포맷이나 출력 형식을 정하는 건 View의 책임으로 본다.
  - 이유: View 계층은 FE를 표현한다고 전제하고 진행해 왔기에 받은 데이터를 기반으로 꾸며내는건 View의 몫이다.
---
- [x] 2025-10-24: entry 도메인의 register를 별도의 도메인으로 보는게 낫지 않을까??
  - 결정: 분리하고 리팩토링 함.
  - 이유: 유연한 설계를 원해서 EDA로 설계했는데 굳이 car와 register랑 entry라는 관심사로 묶이는게 맞을까??
---
- [x] 2025-10-24: 현재 설계에서 서비스 레이어가 필요할까? 관심사를 분리해놔서 하나의 관심사마다 애그리게이트 루트는 하나뿐인데, 굳이? 서비스랑 리포까지?
  - 결정: 일단은 진행하자.
  - 이유: 이미 각 모듈을 만들어놨기도 하고, 리포지토리까지 개념적으로 표현해놨는데 굳이 지울 필요는 없을 것 같다.
---
- [x] 2025-10-25: MockedStatic이 비동기 VirtualThread에 전파되지 않아 Mock 값(4,3) 대신 실제 Random 값 호출 해결을 위해서는 순차 동작을 보장해야하는데 그러면 비동기의 이점을 얻을 수 없음
  - 결정: 비동기의 이점을 포기하고 이벤트 체인으로 순서를 보장하자
  - 이유: 문의에 대한 답이 오지는 않았지만 졸업작품과 미션 데드라인이 얼마 남지 않아서 많은 시간을 투자하긴 힘들다, 우선 통과는 해야할 것 같다.
---
- [x] 2025-10-27: FirstLapRacingCarsMoved, RacingCarsMoved 의 공통 인터페이스를 둘 것인가?
  - 결정: 두지 않는다. 대신 각 Map을 풀도록 조정한다.
  - 이유: 이벤트의 순수성을 깨고 싶지 않다.
---

## 고민들
<details>
<summary>입/출력 책임에 대한 고민</summary>

순수자바 + CLI 환경에서 EDA 구현을 위해 모듈러 모놀리식 아키텍처를 택했다.
입력을 각 모듈에서 받는게 맞을까? 아니면 I/O를 담당하는 모듈을 별도로 두는게 맞을까?
- 순수자바 + CLI 환경에서 입/출력을 한다.
- 분산 환경에서 입력이 들어오는 경우를 대입해서 생각해보자.
- 입력과 출력은 실제 구동하는 Application.main()을 실행하면서 들어온다.
- Application.main()에 InputView(FE)가 있는게 자연스럽다. ->  CLI에 입력 하기 때문에
- Application.main()에 OutputView(FE)가 있는게 자연스럽다. -> CLI로 결과를 보기 때문에
- 분산 환경에서 FE를 하나의 프로젝트로 두는가? -> 백엔드가 분산되어도, 사용자가 보는 단일한 경험을 위해 모놀리식 프론트엔드?
</details>

<details>
<summary>DTO로의 전환의 책임은 어느 계층이 맡아야 할까</summary>

- RacingCars 는 일급컬렉션이자, 애그리게이트 car 의 루트다. 
- 기존에 API 중 lapScore는 LinkedHashMap을 반환했었다.
- 하지만 호출마다 굳이 매번 LinkedHashMap 객체를 만들어야만 할까?? 
- Stream으로 넘기고자 했을 때, 파라미터가 2개라서 넘길 수가 없었다.
- Stream<Entry<String, Integer>>로 넘기자니 깔끔하지 않다고 느꼈다. 
- 그렇다고, DTO를 도메인 계층에서 DTO로 래핑하는게 맞을까? 메모리 이점을 위해 Stream을 하려고 했는데 굳이 ??
</details>

<details> <summary>컴포넌트 조립(Wiring) 책임과 main의 역할</summary>

- 순수자바 CLI 환경이므로 `InputView`와 `OutputView`가 `Application.main`에 존재하는 것은 자연스럽다고 생각했다..
- io에 관련된 EventHandler들이 Adapter 역할을 하는 것으로 이해해도 될까??
- 만약 그렇다면, main이 이 Handler들이 Adapter라는 건데 
- Adapter가 EventBus에 발행 (publish) 하거나 구독(subscribe) 시키는 역할도 맡아야 하는가?
- 예를들어, `InputView`가 `EventBus`의 존재를 모르게 하려면, main에서 Adapter를 통해 `InputView`의 입력을 받아 이벤트로 변환하여 발행(publish)하는 역할까지 수행해야 하는지 그 범위가 헷갈린다.
</details>

<details> <summary>Application과 Adapter와 DTO</summary>

현재 RacingCarGames는 이벤트 버스를 내부적으로 가지고 있다.
경주 로직을 시작하고, 결과 로우값(문자열 리스트)을 반환하는 역할을 가지는데
현재 구조를 보면,

```
RacinCarGames -> (결과 값) -> Application -> (결과 값) -> OutputAdapter -> (DTO) -> OutputView 
```
로 가는데 Application에서 결과 로우 값이 노출되어도 될까? 하는 생각에 고민이 된다.

`InputEventAdapter`와 `OutputEventAdapter`는 이벤트 버스 내에서 발생하는 이벤트에 따라 
InputView 와 OutputView와 소통하는 역할을 한다. 따라서 DTO로 변환하는 책임을 가지는게 자연스럽다고 생각했다.

하지만 `OutputAdapter`는? 이벤트 버스 내에 있는게 아니라 `Application`과 `OutputView`가 직접적으로 알지 못하게
두 계층을 연결해주는 통로 역할을 한다. 연결해주는 곳(OutputAdapter)에서 변환하는게 자연스러운것 같기도 하고, 
`Application`에서 발생한 값이니 이쪽에서 감싼 다음 넘기는게 자연스러운 것 같기도 하고 애매하다.

1. `Application`에서 DTO로 감싼다.
2. `OutputAdapter`에서 DTO로 감싼다.
3. `RacingCarGames`에서 DTO로 감싼다.
4. `RacingCarGames`에서 DTO로 감싸서 넘기고, `Application`에서 `OutputAdapter`로 전달한 뒤 `OutputAdapter`에서 다시 변환

어떤 것이 좋을까?

</details>