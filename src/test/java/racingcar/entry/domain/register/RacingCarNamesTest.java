package racingcar.entry.domain.register;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.stream.Stream;
import org.assertj.core.api.AssertionsForClassTypes;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import racingcar.GlobalTestDataProvider;

class RacingCarNamesTest {

    @Test
    @DisplayName("입력이 `null`일 때, IllegalArgumentException() 예외 발생 테스트")
    void null_throws_IllegalArgumentException() {
        Exception exception = assertThrows(IllegalArgumentException.class,
                () -> RacingCarNames.from(null));

        assertThat(exception)
                .isInstanceOf(IllegalArgumentException.class)
                .isNotInstanceOf(NullPointerException.class);
    }

    @ParameterizedTest(name = "[{index}] 입력이 비어있을 때 예외 메지시 정상 출력 테스트: {1}")
    @MethodSource("provideEmptyCarNameTokens")
    @DisplayName("입력이 비어있을 때(BLANK) 예외 메지시 정상 출력 테스트")
    void empty_input_exception_test(String carNameToken, String description) {
        AssertionsForClassTypes.assertThatThrownBy(() -> RacingCarNames.from(carNameToken))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR]: 빈 입력은 올 수 없습니다.(BLANK)");
    }

    /***
     * 개발 속도 저하로 RacingCarNamesTest의 추가적인 테스트는 MVP 완성 이후 작성.
     */

    private static Stream<Arguments> provideEmptyCarNameTokens() {
        return GlobalTestDataProvider.provideOnlyBlanksTokens();
    }
}
