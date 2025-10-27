package racingcar.race;

import java.util.concurrent.CompletableFuture;
import racingcar.common.EventBus;
import racingcar.common.schema.EntryEvents.CarsPrepared;
import racingcar.common.schema.InputEvents.UserEnteredLapCount;
import racingcar.common.schema.OutputEvents.LapResultAnnounced;
import racingcar.common.schema.RaceEvents.LapExecuted;
import racingcar.common.schema.RaceEvents.RaceCompleted;
import racingcar.common.schema.RaceEvents.RaceStarted;

public class RaceEventHandler {

    private final CompletableFuture<CarsPrepared> carsPreparedFuture = new CompletableFuture<>();
    private final CompletableFuture<UserEnteredLapCount> lapCountFuture = new CompletableFuture<>();

    private final EventBus eventBus;
    private final RaceService raceService;

    public RaceEventHandler(EventBus eventBus, RaceService raceService) {
        this.eventBus = eventBus;
        this.raceService = raceService;
        registerHandlers();
        CompletableFuture.allOf(carsPreparedFuture, lapCountFuture)
                .thenRun(this::startRace);
    }

    private void registerHandlers() {
        eventBus.subscribe(CarsPrepared.class, this::handleCarsPrepared);
        eventBus.subscribe(UserEnteredLapCount.class, this::handleUserEnteredLapCount);
        eventBus.subscribe(LapResultAnnounced.class, this::handleLapResultAnnounced);
    }

    private void startRace() {
        UserEnteredLapCount userEnteredLapCount = lapCountFuture.join();

        try {
            raceService.startRace(userEnteredLapCount.input());

            raceService.executeNextLap();
            eventBus.publish(new RaceStarted());
        } catch (IllegalArgumentException e) {
            eventBus.handleException(e);
        }
    }

    private void handleLapResultAnnounced(LapResultAnnounced lapResultAnnounced) {
        if (!raceService.isFinished()) {
            raceService.executeNextLap();
            eventBus.publish(new LapExecuted());
            return;
        }
        eventBus.publish(new RaceCompleted());
    }

    private void handleUserEnteredLapCount(UserEnteredLapCount event) {
        this.lapCountFuture.complete(event);
    }

    private void handleCarsPrepared(CarsPrepared event) {
        this.carsPreparedFuture.complete(event);
    }
}
