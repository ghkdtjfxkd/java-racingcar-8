package racingcar.io.output;

import racingcar.common.EventBus;
import racingcar.common.schema.EntryEvents.FirstLapRacingCarsMoved;
import racingcar.common.schema.EntryEvents.RacingCarsMoved;
import racingcar.common.schema.OutputEvents.LapResultAnnounced;
import racingcar.dto.RaceStateResponse;

// EDA 전용
public class OutputEventAdapter {

    private final EventBus eventBus;

    public OutputEventAdapter(EventBus eventBus) {
        this.eventBus = eventBus;
        registerHandlers();
    }

    private void registerHandlers() {
        eventBus.subscribe(FirstLapRacingCarsMoved.class, this::handleFirstLapRacingCarMoved);
        eventBus.subscribe(RacingCarsMoved.class, this::handleRacingCarsMoved);
    }

    private void handleFirstLapRacingCarMoved(FirstLapRacingCarsMoved firstLapRacingCarsMoved) {
        OutputView.announceExecuteHeader();
        RaceStateResponse response = RaceStateResponse.of(firstLapRacingCarsMoved.carsPositions());
        OutputView.announce(response);
        eventBus.publish(new LapResultAnnounced());
    }

    private void handleRacingCarsMoved(RacingCarsMoved racingCarsMoved) {
        RaceStateResponse response = RaceStateResponse.of(racingCarsMoved.carsPositions());
        OutputView.announce(response);
        eventBus.publish(new LapResultAnnounced());
    }
}
