package racingcar.entry;

import java.util.List;
import java.util.Map.Entry;
import racingcar.common.EventBus;
import racingcar.common.schema.EntryEvents.CarsPrepared;
import racingcar.common.schema.EntryEvents.FinalCarPositionsRecorded;
import racingcar.common.schema.EntryEvents.RacingCarsMoved;
import racingcar.common.schema.RaceEvents.LapExecuted;
import racingcar.common.schema.RaceEvents.RaceCompleted;
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
        eventBus.subscribe(LapExecuted.class, this::handleLapExecuted);
        eventBus.subscribe(RaceCompleted.class, this::handleRaceCompleted);
    }

    private void handleParticipantsValidated(ParticipantsValidated validated) {
        entryService.registerCars(validated.names());

        eventBus.publish(new CarsPrepared());
    }

    private void handleLapExecuted(LapExecuted lapExecuted) {
        entryService.executeRound();
        List<Entry<String, Integer>> currentCarPositions = entryService.currentScores();

        eventBus.publish(new RacingCarsMoved(currentCarPositions));
    }

    private void handleRaceCompleted(RaceCompleted raceCompleted) {
        List<Entry<String, Integer>> finalPositions = entryService.currentScores();
        eventBus.publish(new FinalCarPositionsRecorded(finalPositions));
    }
}
