package racingcar.registration.domain;

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

class ParticipantsTest {

    private static final String DELIMITER = ",";

    @Test
    @DisplayName("입력이 `null`일 때, IllegalArgumentException() 예외 발생 테스트")
    void null_throws_IllegalArgumentException() {
        Exception exception = assertThrows(IllegalArgumentException.class,
                () -> Participants.from(null));

        assertThat(exception)
                .isInstanceOf(IllegalArgumentException.class)
                .isNotInstanceOf(NullPointerException.class);
    }

    @ParameterizedTest(name = "[{index}] 입력이 비어있을 때 예외 메지시 정상 출력 테스트: {1}")
    @MethodSource("provideEmptyCarNameTokens")
    @DisplayName("입력이 비어있을 때(BLANK) 예외 메지시 정상 출력 테스트")
    void empty_input_exception_test(String carNameToken, String description) {
        AssertionsForClassTypes.assertThatThrownBy(() -> Participants.from(carNameToken))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR]: 빈 입력은 올 수 없습니다.(BLANK)");
    }

    @ParameterizedTest(name = "[{index}] 구분자로 끝날 때: {0}")
    @MethodSource("provideCorrectNamesInputsAddDelimiter")
    @DisplayName("입력이 구분자로 끝날 때 예외 발생 테스트")
    void ends_delimiter_input_exception_test(String carNameToken) {
        AssertionsForClassTypes.assertThatThrownBy(() -> Participants.from(carNameToken))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR]: 구분자로 입력이 종료되고 있습니다.");
    }

    private static Stream<Arguments> provideEmptyCarNameTokens() {
        return GlobalTestDataProvider.provideOnlyBlanksTokens();
    }

    private static Stream<String> provideCorrectNamesInputsAddDelimiter() {
        return TestDataProvider.provideCorrectNamesInputs().map(s -> s + DELIMITER);
    }
}
