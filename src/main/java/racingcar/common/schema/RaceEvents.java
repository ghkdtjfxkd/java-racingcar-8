package racingcar.common.schema;

import racingcar.race.RaceEventHandler;
import racingcar.entry.EntryEventHandler;

public class RaceEvents {

    /**
     * 사용자의 경기 횟수 입력 이벤트
     * <p>
     * <b>발행:</b> {@link RaceEventHandler}
     * <br>
     * <b>구독:</b> {@link EntryEventHandler}
     */
    public record RaceStarted () {}

    /**
     * 랩 실행됨 이벤트
     * <p>
     * <b>발행:</b> {@link RaceEventHandler}
     * <br>
     * <b>구독:</b> {@link EntryEventHandler}
     */
    public record LapExecuted () {}

    /**
     * 랩 종료됨 이벤트
     * <p>
     * <b>발행:</b> {@link RaceEventHandler}
     * <br>
     * <b>구독:</b> {@link EntryEventHandler}
     */
    public record RaceCompleted() {}
}
