package racingcar.entry;

import java.util.Map;
import racingcar.common.EventBus;
import racingcar.common.schema.EntryEvents.CarsPrepared;
import racingcar.common.schema.EntryEvents.FinalCarPositionsRecorded;
import racingcar.common.schema.EntryEvents.FirstLapRacingCarsMoved;
import racingcar.common.schema.EntryEvents.RacingCarsMoved;
import racingcar.common.schema.RaceEvents.LapExecuted;
import racingcar.common.schema.RaceEvents.RaceCompleted;
import racingcar.common.schema.RaceEvents.RaceStarted;
import racingcar.common.schema.RegistrationEvents.ParticipantsValidated;

public class EntryEventHandler {

    private final EventBus eventBus;
    private final EntryService entryService;

    public EntryEventHandler(EventBus eventBus, EntryService entryService) {
        this.eventBus = eventBus;
        this.entryService = entryService;
        registerHandlers();
    }

    private void registerHandlers() {
        eventBus.subscribe(ParticipantsValidated.class, this::handleParticipantsValidated);
        eventBus.subscribe(RaceStarted.class, this::handleRaceStarted);
        eventBus.subscribe(LapExecuted.class, this::handleLapExecuted);
        eventBus.subscribe(RaceCompleted.class, this::handleRaceCompleted);
    }

    private void handleParticipantsValidated(ParticipantsValidated validated) {
        entryService.registerCars(validated.names());
        eventBus.publish(new CarsPrepared());
    }

    private void handleRaceStarted(RaceStarted raceStarted) {
        eventBus.publish(new FirstLapRacingCarsMoved(currentPositions()));
    }

    private void handleLapExecuted(LapExecuted lapExecuted) {
        entryService.executeLap();
        eventBus.publish(new RacingCarsMoved(currentPositions()));
    }

    private void handleRaceCompleted(RaceCompleted raceCompleted) {
        eventBus.publish(new FinalCarPositionsRecorded(currentPositions()));
    }

    private Map<String, Integer> currentPositions() {
        return entryService.currentScores();
    }
}
