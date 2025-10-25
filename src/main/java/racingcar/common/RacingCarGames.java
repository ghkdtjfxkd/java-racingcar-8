package racingcar.common;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import racingcar.common.schema.ResultEvents.WinnersDetermined;
import racingcar.common.schema.StartEvent;

public class RacingCarGames {

    private final EventBus eventBus;
    private final CompletableFuture<List<String>> resultFuture;

    RacingCarGames(EventBus eventBus) {
        this.eventBus = eventBus;
        this.resultFuture = new CompletableFuture<>();
    }

    static RacingCarGames with(EventBus eventBus) {
        return new RacingCarGames(eventBus);
    }

    public void start() {
        eventBus.setExceptionCallback(resultFuture::completeExceptionally);
        subscribeGameResult(eventBus, resultFuture);
        eventBus.publish(new StartEvent());
    }

    public List<String> result() {
        return resultFuture.join();
    }

    private void subscribeGameResult(EventBus eventBus, CompletableFuture<List<String>> resultFuture) {
        eventBus.subscribe(WinnersDetermined.class,
                racingResult -> {
                    resultFuture.complete(racingResult.names());
                    eventBus.shutdown();
                }
        );
    }
}
