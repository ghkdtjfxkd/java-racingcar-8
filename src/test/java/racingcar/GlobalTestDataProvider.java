package racingcar;

import java.util.stream.Stream;
import org.junit.jupiter.params.provider.Arguments;

public final class GlobalTestDataProvider {

    public static final short MAX_NAME_LENGTH = 5;

    private static String lengthFormatOf(short length) {
        return String.format("-[길이: %d]", length);
    }

    public static Stream<Arguments> provideOnlyBlanksTokens() {
        return Stream.of(
                Arguments.of("", "공백(EMPTY) 문자열-[길이: 0]"),
                Arguments.of("", "공백(BLANK) 문자열-[길이: 0]"),
                Arguments.of("   ", "공백 문자열-[길이: 3]"),
                Arguments.of(" ".repeat(MAX_NAME_LENGTH + 1), "공백 문자열-[길이: 6]")
        );
    }

    public static Stream<Arguments> provideContainBlankCarNameTokens() {
        return Stream.of(
                Arguments.of("a b", "(a\" \"b)문자열 가운데 공백-[길이: 3]"),
                Arguments.of(" ab", "(\" \"ab)문자열 앞에 공백-[길이: 3]"),
                Arguments.of("ab ", "(ab\" \")문자열 뒤에 공백-[길이: 3]")
        );
    }

    public static Stream<Arguments> provideOversizeCarNameTokens() {
        short oversize = MAX_NAME_LENGTH + 1;
        String lengthFormat = lengthFormatOf(oversize);
        return Stream.of(
                Arguments.of("1".repeat(oversize), "숫자 문자열" + lengthFormat),
                Arguments.of("a".repeat(oversize), "일반 문자열" + lengthFormat),
                Arguments.of("pobbb1".repeat(oversize), "혼합 문자열" + lengthFormat)
        );
    }
}
