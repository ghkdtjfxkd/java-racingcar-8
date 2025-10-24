package racingcar.io;

import racingcar.common.EventBus;
import racingcar.common.schema.InputEvents.UserEnteredLapCount;
import racingcar.common.schema.InputEvents.UserEnteredParticipants;
import racingcar.common.schema.RegistrationEvents.ParticipantsValidated;
import racingcar.common.schema.StartEvent;
import racingcar.dto.LapCountRequest;
import racingcar.dto.RacingCarNamesRequest;

public class InputEventHandler {

    private final EventBus eventBus;

    public InputEventHandler(EventBus eventBus) {
        this.eventBus = eventBus;
        registerHandlers();
    }

    private void registerHandlers() {
        eventBus.subscribe(StartEvent.class, this::handleUserEnteredParticipants);
        eventBus.subscribe(ParticipantsValidated.class, this::handleUserEnteredLapCount);
    }

    private void handleUserEnteredParticipants(StartEvent startEvent) {
        RacingCarNamesRequest carNames = InputView.requestRacingCarNames();
        eventBus.publish(new UserEnteredParticipants(carNames.rawInput()));
    }

    private void handleUserEnteredLapCount(ParticipantsValidated event) {
        LapCountRequest request = InputView.requestLapCount();
        // 마지막 입력 발생 지점, 입력 재시도 로직 없음
        InputView.close();
        eventBus.publish(new UserEnteredLapCount(request.lapCount()));
    }
}
