package racingcar.race.domain;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

class RaceTest {

    @Test
    @DisplayName("입력이 `null`일 때, IllegalArgumentException() 예외 발생 테스트")
    void null_throws_IllegalArgumentException() {
        Exception exception = assertThrows(IllegalArgumentException.class,
                () -> Race.from(null));

        assertThat(exception)
                .isInstanceOf(IllegalArgumentException.class)
                .isNotInstanceOf(NullPointerException.class);
    }

    @ParameterizedTest(name = "[{index}] 잘못된 시도 횟수 입력 : \"{0}\"")
    @MethodSource("provideBlankLapCounts")
    @DisplayName("잘못된 시도 횟수 입력 시 예외 발생 테스트")
    void wrong_lap_count_exception_test(String wrongLapCount) {
        assertThrows(IllegalArgumentException.class, () -> LapCount.from(wrongLapCount));
    }

    @Test
    @DisplayName("마지막 랩일 경우 경기 진행 후, 잔여 랩 없음 테스트")
    void decrease_lap_count_test() {
        //given
        String finalLapCountInput = "1";
        Race race = Race.from(finalLapCountInput);

        //when
        race.advanceLap();

        //then
        assertFalse(race.hasRemainingLaps());
    }

    @ParameterizedTest(name = "[{index}] 제공된 랩 : {0}")
    @MethodSource("provideNonFinalLapCounts")
    @DisplayName("마지막 랩이 아닐 경우 경기 진행 후, 잔여 랩 존재 테스트")
    void remaining_laps_test(String nonFinalLapCount) {
        //given
        Race race = Race.from(nonFinalLapCount);

        //when
        race.advanceLap();

        //then
        assertTrue(race.hasRemainingLaps());
    }

    private static Stream<String> provideBlankLapCounts() {
        return Stream.of(
                "",
                "       ",
                "\n",
                "\t"
        );
    }

    private static Stream<String> provideNonFinalLapCounts() {
        return Stream.of(
                "2",
                "10",
                "11111"
        );
    }
}
