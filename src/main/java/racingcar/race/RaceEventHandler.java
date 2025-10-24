package racingcar.race;

import racingcar.common.EventBus;
import racingcar.common.schema.InputEvents.UserEnteredLapCount;
import racingcar.common.schema.RaceEvents;

public class RaceEventHandler {
    private final EventBus eventBus;
    private final RaceService raceService;

    public RaceEventHandler(EventBus eventBus, RaceService raceService) {
        this.eventBus = eventBus;
        this.raceService = raceService;
        registerHandlers();
    }

    private void registerHandlers() {
        eventBus.subscribe(UserEnteredLapCount.class, this::handleUserEnteredLapCount);
        // 랩 결과 출력함
    }

    private void handleUserEnteredLapCount(UserEnteredLapCount userEnteredLapCount) {
        raceService.startRace(userEnteredLapCount.input());

        if(!raceService.isFinished()) {
            raceService.executeNextLap();
//            eventBus.publish(new RaceEvents);
        }
    }
}
