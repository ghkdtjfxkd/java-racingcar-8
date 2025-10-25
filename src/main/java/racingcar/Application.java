package racingcar;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import racingcar.common.EventBus;
import racingcar.common.RacingCarGames;
import racingcar.common.schema.ResultEvents.WinnersDetermined;
import racingcar.config.GameConfiguration;
import racingcar.io.output.OutputAdapter;

public class Application {
    public static void main(String[] args) {
        try {
            runWithEventDriven();
        } catch (Exception e) {
            throw new IllegalArgumentException(e);
        }
    }

    private static void runWithEventDriven() {
        EventBus eventBus = new EventBus();
        CompletableFuture<List<String>> resultFuture = new CompletableFuture<>();

        RacingCarGames game = GameConfiguration.setupGame(eventBus);
        subscribeGameResult(eventBus, resultFuture);

        game.start();

        OutputAdapter.announceWinners(resultFuture.join());
    }

    private static void subscribeGameResult(EventBus eventBus, CompletableFuture<List<String>> resultFuture) {
        eventBus.subscribe(WinnersDetermined.class, racingResult -> {
            resultFuture.complete(racingResult.names());
            eventBus.shutdown();
        });
    }
}
