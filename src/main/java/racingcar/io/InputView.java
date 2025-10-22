package racingcar.io;

import camp.nextstep.edu.missionutils.Console;
import racingcar.dto.RacingCarNamesRequest;

public class InputView {

    private static final String GUIDE_CAR_NAMES = "경주할 자동차 이름(이름은 쉼표(,) 기준으로 구분)";

    private InputView() {}

    public static RacingCarNamesRequest requestRacingCarNames () {
        return RacingCarNamesRequest.from(announce(GUIDE.CAR_NAMES));
    }

    private static String announce(GUIDE guide) {
        System.out.println(guide.message());
        return readInput();
    }

    private static String readInput() {
        return Console.readLine();
    }

    public static void close() {
        Console.close();
    }

    private enum GUIDE {
        CAR_NAMES("경주할 자동차 이름을 입력하세요.(이름은 쉼표(,) 기준으로 구분)");

        private final String message;

        GUIDE(String message) {
            this.message = message;
        }

        String message() {
            return message;
        }
    }
}
