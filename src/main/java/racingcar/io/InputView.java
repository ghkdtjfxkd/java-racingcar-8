package racingcar.io;

import camp.nextstep.edu.missionutils.Console;
import racingcar.dto.LapCountRequest;
import racingcar.dto.RacingCarNamesRequest;

public class InputView {
    private InputView() {
    }

    public static RacingCarNamesRequest requestRacingCarNames() {
        return RacingCarNamesRequest.from(prompt(GUIDE.CAR_NAMES));
    }

    public static LapCountRequest requestLapCount() {
        return LapCountRequest.from(prompt(GUIDE.LAP_COUNT));
    }

    private static String prompt(GUIDE guide) {
        System.out.println(guide.message());
        return Console.readLine();
    }

    public static void close() {
        Console.close();
    }

    private enum GUIDE {
        CAR_NAMES("경주할 자동차 이름을 입력하세요.(이름은 쉼표(,) 기준으로 구분)"),
        LAP_COUNT("시도할 횟수는 몇 회인가요?");

        private final String message;

        GUIDE(String message) {
            this.message = message;
        }

        String message() {
            return message;
        }
    }
}
