package racingcar.io.output;

import racingcar.dto.RaceStateResponse;
import racingcar.dto.RacingResultResponse;

class OutputView {

    static void announce(RaceStateResponse raceState) {
        raceState.racingRecords().stream()
                .map(cars -> OutputFormat.from(cars.getKey(), cars.getValue()))
                .forEach(System.out::println);

        System.out.println();
    }

    static void announce(RacingResultResponse racingResult) {
        System.out.println(OutputFormat.from(racingResult.winners()));
    }
}
