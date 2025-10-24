package racingcar.common.schema;

import racingcar.entry.EntryEventHandler;

public class EntryEvents {

    /**
     * 자동차 생성(검증) 완료 이벤트
     * <p>
     * <b>발행:</b> {@link EntryEventHandler},
     * <br>
     * <b>구독:</b>
     */
    public record CarsPrepared() {}
}
