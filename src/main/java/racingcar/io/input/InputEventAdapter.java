package racingcar.io.input;

import racingcar.common.EventBus;
import racingcar.common.schema.InputEvents.UserEnteredLapCount;
import racingcar.common.schema.InputEvents.UserEnteredParticipants;
import racingcar.common.schema.RegistrationEvents.ParticipantsValidated;
import racingcar.common.schema.StartEvent;
import racingcar.dto.LapCountRequest;
import racingcar.dto.RacingCarNamesRequest;

public class InputEventAdapter {

    private final EventBus eventBus;

    public InputEventAdapter(EventBus eventBus) {
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

    // 마지막 입력 발생 지점. 입력 재시도 로직 없음.
    private void handleUserEnteredLapCount(ParticipantsValidated event) {
        LapCountRequest request = InputView.requestLapCount();
        InputView.close();
        eventBus.publish(new UserEnteredLapCount(request.lapCount()));
    }
}
