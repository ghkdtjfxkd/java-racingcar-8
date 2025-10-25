# java-racingcar-precourse

> Event-Driven-Architecture로 구현하는 자동차 경주

## 왜 EDA를 도입했나요??
### 배경
저는 최근 시스템 아키텍처와 동시성 프로그래밍에 관심을 가지고 있습니다.
특히 이벤트 버스를 활용한 비동기 EDA의 유연성과 확장성에 주목했고,
2주차 자동차 경주 미션에서 이를 적용해볼 기회를 발견했습니다.

이 미션은 두 번의 사용자 입력을 필요로 합니다
```
[자동차 이름 입력] → (사용자 생각 시간) → [시도 횟수 입력]
```
사용자가 시도 횟수를 생각하고 입력하는 동안,
이미 받은 자동차 이름들을 검증하고 경주 준비하는 작업을 비동기로 처리한다면
시도 횟수 입력 즉시 레이스를 시작할 수 있다고 생각했습니다.

또한 EDA를 통해 관심사를 분리하면
- Registration 도메인: 자동차 이름 검증만(Entry에서 분리 예정) 
- Entry 도메인: 자동차 움직임만
- Race 도메인: 경기 진행만
- Result 도메인: 경기 결과만
각 도메인이 이벤트로만 통신하므로 응집도 높고 변경에 유연한 구조를 만들 수 있다고 생각했습니다.

### 오버엔지니어링
CLI 환경의 순수 자바로 진행되는 과제에서 이런 아키텍처는 오버엔지니어링이라고 생각합니다.
그럼에도, EDA 패턴 학습과 I/O 병목을 동시성으로 해결하려는
아키텍처 사고를 개념적으로 표현하는 것에 초점을 맞춰 진행해봤습니다.
---

### 🧩EventBus
- [x] Event Bus
- [ ] Event Bus 에서 사용될 이벤트 스키마 정의

---
### 📝 Registration
- [x] 입력 받은 자동차 이름 목록을 파싱한다.
  - [x] 입력 받은 자동차 이름 목록을 검증한다.
  - [x] 입력 받은 자동차 이름을 검증한다.
- [x] 경기에 참여할 자동차 이름 목록을 반환한다.

---
### 🏎️ Entry
- [x] 자동차를 움직인다.
- [x] 자동차의 이름과 움직인 거리를 반환한다.

---
###  💻 I/O 
- [x] 사용자로부터 입력을 받는다.
  - [x] 사용자로부터 참여할 자동차 목록을 입력 받는다.
  - [x] 사용자로부터 시도할 횟수(Lap Count)를 입력 받는다.
- [x] 실행 결과를 출력한다.
  - [x] 경기 현황을 출력한다.
  - [x] 우승자를 출력한다.

---
### 🏟️ RACE
- [x] 랩 횟수 입력 값을 검증한다.
- [x] 남은 랩 횟수를 반환한다.

---
### 🏆 Result
- [x] 경주 우승자(들)를 선별한다.

---

## 이슈 
- [x] 기능 테스트를 통과하지 못하는 이슈
  - 병렬처리 과정이 있는데 SingleThreadExecutor 사용으로 생긴 문제 -> newVirtualThreadPerTaskExecutor 사용하도록 변경해서 해결
- [ ] 예외처리 이슈
  - 잘못된 입력에 `IllegalArgumentException`이 발생해도 나머지 스레드가 살아있어 프로그램이 종료되지 않음.

---

#### 1. 병렬 처리
`ParticipantsValidated` 이벤트 발행 시, 두 핸들러가 **병렬로 실행**됩니다:
- `EntryEventHandler`: 자동차 등록
- `InputEventAdapter`: 랩 카운트 입력

두 작업은 독립적이며, `CompletableFuture.allOf()`로 동기화됩니다.

#### 2. 순서 보장
- **이벤트 체인**: 각 이벤트는 이전 이벤트 처리 완료 후 발행
- **Lap 순서**: 각 Lap은 이전 Lap의 출력 완료 후 실행

#### 3. 동시성 제어
```java
private final ExecutorService executor = Executors.newVirtualThreadPerTaskExecutor();
```
- **VirtualThread**: 경량 스레드로 블로킹 I/O 처리
- **ConcurrentHashMap**: 멀티스레드 환경에서 안전한 핸들러 관리
- **이벤트 체인**: 순서가 필요한 작업은 체인으로 연결

### 이벤트별 책임

| 이벤트 | 발행자 | 구독자 | 역할 |
|--------|--------|--------|------|
| `StartEvent` | Application | InputEventAdapter | 게임 시작 신호 |
| `UserEnteredParticipants` | InputEventAdapter | RegistrationEventHandler | 참가자 이름 전달 |
| `ParticipantsValidated` | RegistrationEventHandler | EntryEventHandler, InputEventAdapter | 검증 완료 신호 |
| `CarsPrepared` | EntryEventHandler | RaceEventHandler | 자동차 준비 완료 |
| `UserEnteredLapCount` | InputEventAdapter | RaceEventHandler | 랩 카운트 전달 |
| `RaceStarted` | RaceEventHandler | EntryEventHandler | 레이스 시작 |
| `FirstLapRacingCarsMoved` | EntryEventHandler | OutputEventAdapter | 첫 랩 결과 |
| `RacingCarsMoved` | EntryEventHandler | OutputEventAdapter | 각 랩 결과 |
| `LapResultAnnounced` | OutputEventAdapter | RaceEventHandler | 출력 완료 신호 |
| `RaceCompleted` | RaceEventHandler | EntryEventHandler | 레이스 종료 |
| `FinalCarPositionsRecorded` | EntryEventHandler | ResultEventHandler | 최종 위치 전달 |
| `WinnersDetermined` | ResultEventHandler | Application | 우승자 결정 |
