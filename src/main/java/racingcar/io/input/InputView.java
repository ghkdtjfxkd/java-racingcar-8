package racingcar.io.input;

import camp.nextstep.edu.missionutils.Console;
import racingcar.dto.request.LapCountRequest;
import racingcar.dto.request.RacingCarNamesRequest;

class InputView {

    static RacingCarNamesRequest requestRacingCarNames() {
        return RacingCarNamesRequest.from(prompt(Guide.CAR_NAMES));
    }

    static LapCountRequest requestLapCount() {
        return LapCountRequest.from(prompt(Guide.LAP_COUNT));
    }

    private static String prompt(Guide guide) {
        System.out.println(guide.message());
        return Console.readLine();
    }

    static void close() {
        Console.close();
    }
}
