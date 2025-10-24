package racingcar.common.schema;

import java.util.List;
import racingcar.entry.EntryEventHandler;
import racingcar.registration.RegistrationEventHandler;

public class RegistrationEvents {

    /**
     * 자동차 이름 등록(검증) 완료 이벤트
     * <p>
     * <b>발행:</b> {@link RegistrationEventHandler}
     * <br>
     * <b>구독:</b> {@link EntryEventHandler},
     *
     *  @param names 검증 완료된 참가자 이름 목록
     */
    public record ParticipantsValidated(List<String> names) {}
}
