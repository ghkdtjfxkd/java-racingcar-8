package racingcar.entry.domain;

import java.util.stream.Stream;

class TestDataProvider {

    static Stream<Integer> providePressingForces() {
        return Stream.of(0, 1, 2, 3, 4, 5, 6, 7, 8, 9);
    }

    static Stream<Integer> provideMovementCount() {
        return Stream.of(
                0,
                1,
                10,
                111
        );
    }

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
