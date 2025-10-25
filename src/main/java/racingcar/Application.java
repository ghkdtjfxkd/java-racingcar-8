package racingcar;

import racingcar.common.RacingCarGames;
import racingcar.common.GameConfiguration;
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
        GameConfiguration setup = GameConfiguration.setup();
        RacingCarGames game = setup.getGames();

        game.start();
        OutputAdapter.announceWinners(game.result());
    }
}
