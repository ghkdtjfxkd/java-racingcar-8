package racingcar.entry.domain;

import static org.junit.jupiter.api.Assertions.*;

import java.util.function.Consumer;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import racingcar.entry.strategy.PedalingStrategy;

class EngineTest {

    private static final int TORQUE = 1;
    private static final int STOP = 3;
    private static final int REPULSIVE_OF_PEDAL = 4;

    @ParameterizedTest(name = "[{index}] {1}")
    @MethodSource("mileages")
    @DisplayName("이동 거리 증가 테스트")
    void mileage_update_test(int mileage, String description) {
        //given
        PedalingStrategy movingForward = () -> REPULSIVE_OF_PEDAL;
        Engine engine = Engine.setup(movingForward);

        //when
        for (int i = 0; i < mileage; i++) {
            engine.movement();
        }

        //then
        assertEquals(mileage, engine.mileage());
    }

    @ParameterizedTest(name = "[{index}] {1}")
    @MethodSource("mileages")
    @DisplayName("출력에 따른 이동 거리 증가 테스트")
    void stop_test(int mileage, String description) {
        //given
        PedalingStrategy stop = () -> STOP;
        Engine engine = Engine.setup(stop);

        //when
        for (int i = 0; i < mileage; i++) {
            engine.movement();
        }

        //then
        assertEquals(0, engine.mileage());
    }

    @ParameterizedTest(name = "[{index}] {1}")
    @MethodSource("weakPressingForces")
    @DisplayName("페달링(약한 힘)에 따른 이동 거리 테스트")
    void pedaling_weak_pressing_force_test(int pressing, String description) {
        //given
        PedalingStrategy dummyPedaling = () -> pressing;
        Engine engine = Engine.setup(dummyPedaling);

        //when
        engine.movement();

        //then
        assertNotEquals(TORQUE, engine.mileage());
    }

    @ParameterizedTest(name = "[{index}] {1}")
    @MethodSource("enoughPressingForces")
    @DisplayName("페달링(일정 이상 힘)에 따른 이동 거리 테스트")
    void pedaling_enough_pressing_force_test(int pressing, String description) {
        //given
        PedalingStrategy dummyPedaling = () -> pressing;
        Engine engine = Engine.setup(dummyPedaling);

        //when
        engine.movement();

        //then
        assertEquals(TORQUE, engine.mileage());
    }

    private static Stream<Arguments> torques() {
        return TestDataProvider.provideTorques()
                .flatMap(torque ->
                        Stream.of(Arguments.of(torque, "제공된 출력 : " + torque))
                );
    }

    private static Stream<Arguments> mileages() {
        return TestDataProvider.provideMovementCount()
                .flatMap(mileage ->
                        Stream.of(Arguments.of(mileage, "제공된 이동 거리 : " + mileage))
                );
    }

    private static Stream<Arguments> weakPressingForces() {
        return TestDataProvider.providePressingForces()
                .limit(REPULSIVE_OF_PEDAL).map( force -> Arguments.of(force, "제공된 힘 : " + force));
    }

    private static Stream<Arguments> enoughPressingForces() {
        return TestDataProvider.providePressingForces()
                .skip(REPULSIVE_OF_PEDAL).map( force -> Arguments.of(force, "제공된 힘 : " + force));
    }
}