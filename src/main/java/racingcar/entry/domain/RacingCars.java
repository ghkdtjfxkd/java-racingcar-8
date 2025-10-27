package racingcar.entry.domain;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class RacingCars {

    private final List<RacingCar> entry;

    RacingCars(List<RacingCar> entry) {
        this.entry = List.copyOf(entry);
    }

    public void updateCarPositions() {
        entry.forEach(RacingCar::drive);
    }

    /**
     * 자동차들의 현재 이름과 위치 상태를 반환합니다.
     * <p>
     * <b>(중요) 반환되는 Map은 자동차가 입력된 순서를 보장합니다.</b>
     *
     * @return 순서가 보장되는, 수정 불가능한 (Unmodifiable) LinkedHashMap
     */
    public Map<String, Integer> status() {
        LinkedHashMap<String, Integer> status = new LinkedHashMap<>();
        for (RacingCar racingCar : entry) {
            status.put(racingCar.name(), racingCar.position());
        }
        return Collections.unmodifiableMap(status);
    }
}
