package racingcar;

import java.util.concurrent.CompletionException;
import racingcar.common.RacingCarGames;
import racingcar.common.GameConfiguration;
import racingcar.io.output.OutputAdapter;

public class Application {

    public static void main(String[] args) {
        try {
            runWithEventDriven();
        } catch (Exception e) {
            convertRequiredTypeException(e);
        }
    }

    private static void runWithEventDriven() {
        GameConfiguration setup = GameConfiguration.setup();
        RacingCarGames game = setup.getGames();

        game.start();
        OutputAdapter.announceWinners(game.result());
    }

    private static void convertRequiredTypeException(Exception e) {
        if(e instanceof CompletionException && e.getCause() != null) {
            throw new IllegalArgumentException(e.getCause().getMessage());
        }
        throw new RuntimeException("[ERROR] : 예상치 못한 에러", e);
    }
}
