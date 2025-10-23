package racingcar.race.domain;

import static org.junit.jupiter.api.Assertions.*;

import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

class LapCountTest {

    @ParameterizedTest(name = "[{index}] 잘못된 랩 횟수 예외 발생 테스트")
    @MethodSource("provideWrongLapCounts")
    @DisplayName("잘못된 랩 횟수 예외 발생 테스트")
    void wrong_lap_count_exception_test(String wrongLapCount) {
        assertThrows(IllegalArgumentException.class, () -> LapCount.from(wrongLapCount));
    }

    @Test
    @DisplayName("랩 횟수 줄이기 테스트")
    void decrease_lap_count_test() {
        LapCount OneLapRemaining = LapCount.of(1);
        assertFalse(OneLapRemaining.decrease().hasMore());
    }

    @ParameterizedTest(name = "[{index}] 0보다 큰 랩 횟수")
    @MethodSource("provideCorrectLapCounts")
    @DisplayName("잔여 랩 횟수 존재 시 true 테스트")
    void has_more_lap_count_test(int lapCount) {
        assertTrue(LapCount.of(lapCount).hasMore());
    }

    private static Stream<String> provideWrongLapCounts() {
        return Stream.of(
                "-1",
                "0",
                "2147483648",// 2147483647(Integer.MAX) + 1
                "-2147483649" // -2147483648(Integer.MIN) - 1
        );
    }

    private static Stream<Integer> provideCorrectLapCounts() {
        return Stream.of(
                1,
                2,
                10,
                11111
        );
    }
}
