package racingcar.config;

import racingcar.common.EventBus;
import racingcar.common.RacingCarGames;
import racingcar.entry.EntryEventHandler;
import racingcar.entry.EntryServiceImpl;
import racingcar.io.input.InputEventAdapter;
import racingcar.io.output.OutputEventAdapter;
import racingcar.race.RaceEventHandler;
import racingcar.race.RaceServiceImpl;
import racingcar.registration.RegistrationEventHandler;
import racingcar.registration.RegistrationServiceImpl;
import racingcar.result.ResultEventHandler;

public class GameConfiguration {
    public static RacingCarGames setupGame(EventBus eventBus) {
        registerEventHandlers(eventBus);
        return new RacingCarGames(eventBus);
    }

    private static void registerEventHandlers(EventBus eventBus) {
        new OutputEventAdapter(eventBus);
        new InputEventAdapter(eventBus);

        new RegistrationEventHandler(eventBus, new RegistrationServiceImpl());
        new EntryEventHandler(eventBus,  new EntryServiceImpl());
        new RaceEventHandler(eventBus, new RaceServiceImpl());
        new ResultEventHandler(eventBus);
    }
}
