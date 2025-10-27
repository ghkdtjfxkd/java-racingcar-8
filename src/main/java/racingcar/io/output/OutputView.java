package racingcar.io.output;

import racingcar.dto.response.CarPositionDto;
import racingcar.dto.response.LapStateResponse;
import racingcar.dto.response.RacingResultResponse;

class OutputView {

    private static final String EXECUTE_RESULT = "실행 결과";

    private OutputView() {
    }

    static void announceExecutionHeader() {
        System.out.println(System.lineSeparator() + EXECUTE_RESULT);
    }

    static void announce(LapStateResponse lapState) {
        printCarPositions(lapState);
    }

    private static void printCarPositions(LapStateResponse lapState) {
        lapState.racingRecords().stream()
                .map(OutputView::format)
                .forEach(System.out::println);

        System.out.println();
    }

    private static String format(CarPositionDto racingCar) {
        return OutputFormat.from(racingCar.name(), racingCar.position());
    }

    static void announce(RacingResultResponse racingResult) {
        System.out.println(OutputFormat.from(racingResult.winners()));
    }
}
