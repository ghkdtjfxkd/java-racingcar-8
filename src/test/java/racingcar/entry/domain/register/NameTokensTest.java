package racingcar.entry.domain.register;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

class NameTokensTest {

    @ParameterizedTest(name = "[{index}] 중복된 자동차 이름이 식별: {1}")
    @MethodSource("provideSameNamesExistTokens")
    @DisplayName("중복된 자동차 이름이 식별 시 예외 발생 테스트")
    void wrong_names_exception_test(List<String> nameTokens) {
        assertThrows(IllegalArgumentException.class, () -> NameTokens.of(nameTokens));
    }

    @ParameterizedTest(name = "[{index}] 중복된 자동차 이름이 식별: {1}")
    @MethodSource("provideSameNamesExistTokens")
    @DisplayName("중복된 자동차 이름이 식별 시 예외 메시지 테스트")
    void wrong_names_exception_message_test(List<String> carNameToken) {
        assertThatThrownBy(() -> NameTokens.of(carNameToken))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR]: 중복되는 자동차 이름이 존재합니다.");
    }


    @ParameterizedTest(name = "[{index}] 증복되지 않은 자동차 이름들 : {0}")
    @MethodSource("uniqueNamesOnlyExistTokens")
    @DisplayName("중복된 자동차 이름이 없을 경우 예외 미 발생 테스트")
    void correct_names_exception_test(List<String> nameTokens) {
        assertDoesNotThrow(() -> NameTokens.of(nameTokens));
    }

    private static Stream<List<String>> provideSameNamesExistTokens() {
        return TestDataProvider.provideCorrectNumericNames()
                .map(name -> List.of(name, name));
    }

    private static Stream<List<String>> uniqueNamesOnlyExistTokens() {
        List<String> numericNames = TestDataProvider.provideCorrectNumericNames().toList();
        List<String> nonNumericNames = TestDataProvider.provideCorrectNonNumericNames().toList();

        return numericNames.stream()
                .flatMap(numeric -> nonNumericNames.stream()
                        .map(nonNumeric -> List.of(numeric, nonNumeric)));
    }
}