package racingcar.io.output;

import racingcar.dto.response.RaceStateResponse;
import racingcar.dto.response.RacingResultResponse;

class OutputView {

    private static final String EXECUTE_RESULT = "실행 결과";

    private OutputView() {}

    static void announceExecuteHeader() {
        System.out.println(System.lineSeparator() + EXECUTE_RESULT);
    }

    static void announce(RaceStateResponse raceState) {
        racingDetails(raceState);
    }

    private static void racingDetails(RaceStateResponse raceState) {
        raceState.racingRecords().stream()
                .map(cars -> OutputFormat.from(cars.getKey(), cars.getValue()))
                .forEach(System.out::println);

        System.out.println();
    }

    static void announce(RacingResultResponse racingResult) {
        System.out.println(OutputFormat.from(racingResult.winners()));
    }
}
