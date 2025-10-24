package racingcar.io.output;

import racingcar.common.EventBus;
import racingcar.common.schema.EntryEvents.RacingCarsMoved;
import racingcar.common.schema.OutputEvents.LapResultAnnounced;
import racingcar.common.schema.OutputEvents.RaceResultAnnounced;
import racingcar.common.schema.ResultEvents.WinnersDetermined;
import racingcar.dto.RaceStateResponse;
import racingcar.dto.RacingResultResponse;

public class OutputEventAdapter {

    private final EventBus eventBus;

    public OutputEventAdapter(EventBus eventBus) {
        this.eventBus = eventBus;
        registerHandlers();
    }

    private void registerHandlers() {
        eventBus.subscribe(RacingCarsMoved.class, this::handleRacingCarsMoved);
        eventBus.subscribe(WinnersDetermined.class, this::handleWinnersDetermined);
    }

    private void handleRacingCarsMoved(RacingCarsMoved racingCarsMoved) {
        RaceStateResponse response = RaceStateResponse.of(racingCarsMoved.carsPositions());
        OutputView.announce(response);
        eventBus.publish(new LapResultAnnounced());
    }

    private void handleWinnersDetermined(WinnersDetermined winnersDetermined) {
        RacingResultResponse response = RacingResultResponse.of(winnersDetermined.names());
        OutputView.announce(response);
        eventBus.publish(new RaceResultAnnounced());
    }
}
