package racingcar.registration.domain;

import java.util.stream.Stream;

class TestDataProvider {

    static Stream<String> provideCorrectNumericNames() {
        return Stream.of(
                "1",
                "12",
                "123",
                "1234",
                "01234"
        );
    }

    static Stream<String> provideCorrectNonNumericNames() {
        return Stream.of(
                "a",
                "ab",
                "abc",
                "abcd",
                "abcde"
        );
    }
}
