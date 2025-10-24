package racingcar.common.schema;

import racingcar.io.output.OutputEventAdapter;
import racingcar.race.RaceEventHandler;

public class OutputEvents {
    /**
     * 랩 내역 출력 완료 이벤트
     * <p>
     * <b>발행:</b> {@link OutputEventAdapter}
     * <br>
     * <b>구독:</b> {@link RaceEventHandler}
     */
    public record LapResultAnnounced() {}
}
