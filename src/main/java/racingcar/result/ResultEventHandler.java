package racingcar.result;

import racingcar.common.EventBus;
import racingcar.common.schema.EntryEvents.FinalCarPositionsRecorded;
import racingcar.common.schema.ResultEvents.WinnersDetermined;
import racingcar.result.domain.FinalStandings;

public class ResultEventHandler {

    private final EventBus eventBus;

    public ResultEventHandler(EventBus eventBus) {
        this.eventBus = eventBus;
        registerHandlers();
    }

    private void registerHandlers() {
        eventBus.subscribe(FinalCarPositionsRecorded.class, this::handleFinalCarPositionsRecorded);
    }

    private void handleFinalCarPositionsRecorded(FinalCarPositionsRecorded finalCarPositionsRecorded) {
        FinalStandings finalStandings = FinalStandings.of(finalCarPositionsRecorded.carsPositions());
        eventBus.publish(new WinnersDetermined(finalStandings.winners()));
    }
}
