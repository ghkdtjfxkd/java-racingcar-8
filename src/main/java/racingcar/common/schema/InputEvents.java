package racingcar.common.schema;


import racingcar.registration.RegistrationEventHandler;

public class InputEvents {

    /**
     * 게임 시작 이벤트
     * <p>
     * <b>발행:</b>
     * <br>
     * <b>구독:</b> {@link RegistrationEventHandler}
     *
     * @param names 사용자가 입력한 참가자들 문자열
     */
    public record UserEnteredParticipants (String names) {}
}
