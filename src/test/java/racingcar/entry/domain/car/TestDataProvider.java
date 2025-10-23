package racingcar.entry.domain.car;

import java.util.stream.Stream;

class TestDataProvider {

    static Stream<Integer> provideTorques() {
        return Stream.of(
                0,
                1,
                10,
                111
        );
    }

    static Stream<Mileage> provideMileages() {
        return Stream.of(
                Mileage.setup(),    // 기본
                Mileage.setup().add(1),
                Mileage.setup().add(10),
                Mileage.setup().add(111)
        );
    }
}
