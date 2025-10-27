package racingcar.common.schema;

import java.util.Map;
import racingcar.entry.EntryEventHandler;
import racingcar.entry.domain.RacingCars;
import racingcar.io.output.OutputEventAdapter;
import racingcar.race.RaceEventHandler;
import racingcar.result.ResultEventHandler;

public class EntryEvents {

    /**
     * 자동차 생성(검증) 완료 이벤트
     * <p>
     * <b>발행:</b> {@link EntryEventHandler}
     * <br>
     * <b>구독:</b> {@link RaceEventHandler}
     */
    public record CarsPrepared() {
    }

    /**
     * 첫 랩 자동차 움직임 이벤트
     * <p>
     * <b>발행:</b> {@link EntryEventHandler}
     * <br>
     * <b>구독:</b> {@link OutputEventAdapter}
     * <br>
     * <br>
     * <b>(중요) 현재 Map 은 자동차가 입력된 순서를 보장합니다.((Unmodifiable) LinkedHashMap)
     * <p> 참고 : {@link RacingCars}
     * @param carsPositions 첫 랩에서 움직인 자동차들의 이름과 거리들
     */
    public record FirstLapRacingCarsMoved(Map<String, Integer> carsPositions) {
    }

    /**
     * 자동차 움직임 이벤트
     * <p>
     * <b>발행:</b> {@link EntryEventHandler}
     * <br>
     * <b>구독:</b> {@link OutputEventAdapter}
     * <br>
     * <br>
     * <b>(중요) 현재 Map 은 자동차가 입력된 순서를 보장합니다.((Unmodifiable) LinkedHashMap)
     * <p> 참고 : {@link RacingCars}
     * @param carsPositions 움직인 자동차들의 이름과 거리 목록
     */
    public record RacingCarsMoved(Map<String, Integer> carsPositions) {
    }

    /**
     * 경기 종료 후 자동차 위치 저장됨 이벤트
     * <p>
     * <b>발행:</b> {@link EntryEventHandler}
     * <br>
     * <b>구독:</b> {@link ResultEventHandler}
     * <br>
     * <br>
     * <b>(중요) 현재 Map 은 자동차가 입력된 순서를 보장합니다.((Unmodifiable) LinkedHashMap)
     * <p> 참고 : {@link RacingCars}
     * @param carsPositions 움직인 자동차들의 이름과 거리 목록
     */
    public record FinalCarPositionsRecorded(Map<String, Integer> carsPositions) {
    }
}
