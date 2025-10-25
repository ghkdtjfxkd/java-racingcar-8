package racingcar;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import racingcar.common.EventBus;
import racingcar.common.RacingCarGames;
import racingcar.common.schema.ResultEvents.WinnersDetermined;
import racingcar.config.GameConfiguration;
import racingcar.dto.RacingResultResponse;
import racingcar.io.output.OutputAdapter;
import racingcar.io.output.OutputEventAdapter;
import racingcar.io.output.OutputView;

public class Application {
    public static void main(String[] args) {
        try {
            run();
        } catch (Exception e) {
            throw new IllegalArgumentException(e);
        }
    }

    private static void run() {
        EventBus eventBus = new EventBus();
        CompletableFuture<List<String>> resultFuture = new CompletableFuture<>();

        RacingCarGames game = GameConfiguration.setupGame(eventBus);
        game.start();

        subscribeGameResult(eventBus, resultFuture);
        OutputAdapter.announceWinners(resultFuture.join());
    }

    private static void subscribeGameResult(EventBus eventBus, CompletableFuture<List<String>> resultFuture) {
        eventBus.subscribe(WinnersDetermined.class, racingResult -> {
            resultFuture.complete(racingResult.names());
            eventBus.shutdown();
        });
    }
}
