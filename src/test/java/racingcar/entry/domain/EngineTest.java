package racingcar.entry.domain;

import static org.junit.jupiter.api.Assertions.*;

import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class EngineTest {

    @ParameterizedTest(name = "[{index}] {1}")
    @MethodSource("mileages")
    @DisplayName("이동 거리 증가 테스트")
    void mileage_update_test(Mileage mileage, String description) {
        //given
        int torque = 1;
        int previousDistance = mileage.getDistance();

        //when
        mileage = mileage.add(torque);

        //then
        int expected = previousDistance + torque;
        assertEquals(expected, mileage.getDistance());
    }

    @ParameterizedTest(name = "[{index}] {1}")
    @MethodSource("torques")
    @DisplayName("출력에 따른 이동 거리 증가 테스트")
    void mileage_updated_from_torque_test(int torque, String description) {
        //given
        Mileage mileage = Mileage.setup();

        //when
        mileage = mileage.add(torque);

        //then
        assertEquals(torque, mileage.getDistance());
    }

    private static Stream<Arguments> torques() {
        return TestDataProvider.provideTorques()
                .flatMap(torque ->
                        Stream.of(Arguments.of(torque, "제공된 출력 : " + torque))
                );
    }

    private static Stream<Arguments> mileages() {
        return TestDataProvider.provideMileages()
                .flatMap(mileage ->
                        Stream.of(Arguments.of(mileage, "제공된 이동 거리 : " + mileage.getDistance()))
                );
    }
}