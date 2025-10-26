package racingcar.common;

import racingcar.entry.EntryEventHandler;
import racingcar.entry.EntryServiceImpl;
import racingcar.entry.domain.RacingCarFactory;
import racingcar.entry.strategy.PedalingStrategy;
import racingcar.entry.strategy.RandomPedalingStrategy;
import racingcar.io.input.InputEventAdapter;
import racingcar.io.output.OutputEventAdapter;
import racingcar.race.RaceEventHandler;
import racingcar.race.RaceServiceImpl;
import racingcar.registration.RegistrationEventHandler;
import racingcar.registration.RegistrationServiceImpl;
import racingcar.result.ResultEventHandler;

public class GameConfiguration {

    private final RacingCarGames racingCarGames;

    private GameConfiguration(RacingCarGames racingCarGames) {
        this.racingCarGames = racingCarGames;
    }

    public static GameConfiguration setup() {
        EventBus eventBus = EventBus.getInstance();
        registerEventHandlers(eventBus);
        return new GameConfiguration(RacingCarGames.with(eventBus));
    }

    public RacingCarGames getGames() {
        return racingCarGames;
    }

    private static void registerEventHandlers(EventBus eventBus) {
        new OutputEventAdapter(eventBus);
        new InputEventAdapter(eventBus);

        new RegistrationEventHandler(eventBus, new RegistrationServiceImpl());

        new EntryEventHandler(eventBus, new EntryServiceImpl(new RandomPedalingStrategy()));
        new RaceEventHandler(eventBus, new RaceServiceImpl());
        new ResultEventHandler(eventBus);
    }

    private static EntryServiceImpl entryServiceWithPedalingStrategy() {
        PedalingStrategy pedalingStrategy = new RandomPedalingStrategy();
        return new EntryServiceImpl(pedalingStrategy);
    }
}
