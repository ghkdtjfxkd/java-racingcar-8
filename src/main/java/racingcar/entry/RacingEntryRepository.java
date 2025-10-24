package racingcar.entry;

import racingcar.entry.domain.RacingCars;

/**
 * <p>현재는 Repository 계층의 `개념적 표현`만을 위한 목적으로 작성되었습니다.
 *
 * <p>Service 레이어가 도메인 객체를 직접 보유하지 않고
 * Repository를 통해서만 접근하도록 강제합니다.
 */
class RacingEntryRepository {

    private RacingCars racingCars;

    void save(RacingCars racingCars) {
        this.racingCars = racingCars;
    }

    RacingCars getEntry() {
        return racingCars;
    }
}
