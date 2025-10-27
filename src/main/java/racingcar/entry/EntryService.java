package racingcar.entry;

import java.util.List;
import java.util.Map;

public interface EntryService {
    void registerCars(List<String> carNames);
    void executeLap();

    /**
     * 자동차들의 현재 이름과 위치 상태를 반환합니다.
     * <p>
     * <b>(중요) 반환되는 Map은 자동차가 입력된 순서를 보장합니다.</b>
     *
     * @return 순서가 보장되는, 수정 불가능한 (Unmodifiable) LinkedHashMap
     */
    Map<String, Integer> currentScores();
}
