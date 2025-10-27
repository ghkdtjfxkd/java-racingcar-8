package racingcar.io.output;

import racingcar.dto.response.LapStateResponse;
import racingcar.dto.response.RacingResultResponse;

class OutputView {

    private static final String EXECUTE_RESULT = "실행 결과";

    private OutputView() {}

    static void announceExecuteHeader() {
        System.out.println(System.lineSeparator() + EXECUTE_RESULT);
    }

    static void announce(LapStateResponse lapState) {
        printRacingDetails(lapState);
    }

    private static void printRacingDetails(LapStateResponse lapState) {
        lapState.racingRecords().stream()
                .map(racingCar -> OutputFormat.from(racingCar.name(), racingCar.position()))
                .forEach(System.out::println);

        System.out.println();
    }

    static void announce(RacingResultResponse racingResult) {
        System.out.println(OutputFormat.from(racingResult.winners()));
    }
}
