package racingcar.common.schema;

import java.util.List;
import java.util.Map.Entry;
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

    public record RacingCarsMoved(List<Entry<String, Integer>> carsPositions) {}
    public record FinalCarPositionsRecorded(List<Entry<String, Integer>> carsPositions) {}
}
