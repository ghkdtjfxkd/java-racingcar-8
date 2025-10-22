package racingcar.entry.domain.register;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import racingcar.TestDataProvider;

class CarNameTest {

    private static final short MAX_NAME_LENGTH = 5;

    /**
     * 빈 입력(empty) 검증 -> 공백 문자 포함 검증 -> 이름 길이 검증
     */

    @ParameterizedTest(name = "[{index}] 잘못된 자동차 이름이 오는 경우 예외 발생 테스트: {1}")
    @MethodSource("provideWrongCarNameTokens")
    @DisplayName("잚못된 자동차 이름이 오는 경우 예외 발생 테스트")
    void wrong_name_exception_test(String carNameToken, String description) {
        assertThrows(IllegalArgumentException.class, () -> CarName.of(carNameToken));
    }

    @ParameterizedTest(name = "[{index}] 자동차 이름이 비어있을 때 예외 메지시 정상 출력 테스트: {1}")
    @MethodSource("provideEmptyCarNameTokens")
    @DisplayName("자동차 이름이 비어있을 때(BLNAKf) 예외 메지시 정상 출력 테스트")
    void empty_name_exception_message_test(String carNameToken, String description) {
        assertThatThrownBy(() -> CarName.of(carNameToken))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR]: 빈 입력은 올 수 없습니다.(BLANK)");
    }

    @ParameterizedTest(name = "[{index}] 자동차 이름 공백 문자 포함 시 예외 메시지 테스트: {1}")
    @MethodSource("provideBlankCarNameTokens")
    @DisplayName("자동차 이름 자동차 이름 공백 문자 포함 시 예외 메시지 정상 출력 테스트")
    void blank_name_exception_message_test(String carNameToken, String description) {
        assertThatThrownBy(() -> CarName.of(carNameToken))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR]: 자동차 이름에 공백 문자가 포함되어 있습니다.");
    }

    @ParameterizedTest(name = "[{index}] 자동차 이름 글자수 최대 값 초과 시 예외 메시지 테스트: {1}")
    @MethodSource("provideOversizeCarNameTokens")
    @DisplayName("자동차 이름 글자 수가 최대 값을 초과하는 경우 예외 메시지 정상 출력 테스트")
    void oversize_name_exception_message_test(String carNameToken, String description) {
        assertThatThrownBy(() -> CarName.of(carNameToken))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(String.format("[ERROR]: 자동차 이름은 %d글자 이하여야 합니다.", MAX_NAME_LENGTH));
    }

    private static Stream<Arguments> provideWrongCarNameTokens() {
        return Stream.of(
                provideEmptyCarNameTokens(),
                provideBlankCarNameTokens(),
                provideOversizeCarNameTokens()
        ).flatMap(stream -> stream);
    }

    private static Stream<Arguments> provideEmptyCarNameTokens() {
        return TestDataProvider.provideOnlyBlankCarNameTokens();
    }

    private static Stream<Arguments> provideBlankCarNameTokens() {
        return TestDataProvider.provideContainBlankCarNameTokens();
    }

    private static Stream<Arguments> provideOversizeCarNameTokens() {
        return TestDataProvider.provideOversizeCarNameTokens();
    }
}