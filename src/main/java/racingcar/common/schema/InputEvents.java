package racingcar.common.schema;

import racingcar.io.input.InputEventAdapter;
import racingcar.race.RaceEventHandler;
import racingcar.registration.RegistrationEventHandler;

public class InputEvents {

    /**
     * 사용자의 참가자들 입력 이벤트
     * <p>
     * <b>발행:</b> {@link InputEventAdapter}
     * <br>
     * <b>구독:</b> {@link RegistrationEventHandler}
     *
     * @param names 사용자가 입력한 참가자들 문자열
     */
    public record UserEnteredParticipants(String names) {
    }


    /**
     * 사용자의 경기 횟수 입력 이벤트
     * <p>
     * <b>발행:</b> {@link InputEventAdapter}
     * <br>
     * <b>구독:</b> {@link RaceEventHandler}
     *
     * @param input 사용자가 입력한 경기 시도 횟수 문자열
     */
    public record UserEnteredLapCount(String input) {
    }
}
