package racingcar.io.output;

import java.util.List;
import racingcar.dto.RacingResultResponse;

// Application 전용
public class OutputAdapter {

    private OutputAdapter() {}

    public static void announceWinners(List<String> winners) {
        RacingResultResponse response = RacingResultResponse.of(winners);
        OutputView.announce(response);
    }
}
