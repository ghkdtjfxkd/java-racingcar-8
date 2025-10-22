package racingcar.io;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrowsExactly;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.NoSuchElementException;
import java.util.stream.Stream;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import racingcar.dto.RacingCarNamesRequest;

class InputViewTest {
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private final InputStream originalIn = System.in;

    @BeforeEach
    public void setUp() {
        outputStreamCaptor.reset();
        System.setOut(new PrintStream(outputStreamCaptor));
        InputView.close();
    }

    @AfterEach
    public void tearDown() {
        System.setOut(originalOut);
        System.setIn(originalIn);
        InputView.close();
    }

    @ParameterizedTest(name = "[{index}] 자동차 이름 입력 테스트: {1}")
    @MethodSource("provideRacingCarNamesInput")
    @DisplayName("사용자가 입력한 요청과 전달된 값은 같아야 한다.")
    void correctInputTest(String input, String description) {
        //given
        String simulatedInput = input + System.lineSeparator();

        //when
        System.setIn(new ByteArrayInputStream(simulatedInput.getBytes()));
        RacingCarNamesRequest request = InputView.requestRacingCarNames();

        //then
        assertEquals(input, request.rawInput());
    }

    @Test
    @DisplayName("콘솔이 닫힌 뒤에는 추가적인 입력을 받을 수 없다.")
    void afterCloseConsoleTest() {
        //given
        String simulatedInput = "test" + System.lineSeparator();
        InputStream inputStream = new ByteArrayInputStream(simulatedInput.getBytes());

        //when
        System.setIn(inputStream);
        RacingCarNamesRequest previous = InputView.requestRacingCarNames();
        InputView.close();

        //then
        assertThrowsExactly(NoSuchElementException.class, InputView::requestRacingCarNames);
    }

    @Test
    @DisplayName("차량 이름 입력 안내 메시지 출력 테스트")
    void guideMessageTest() {
        //given
        String simulatedInput = "test" + System.lineSeparator();
        InputStream inputStream = new ByteArrayInputStream(simulatedInput.getBytes());
        OutputStream outputStream = new ByteArrayOutputStream();
        System.setIn(inputStream);
        System.setOut(new PrintStream(outputStream));

        //when
        RacingCarNamesRequest userInput = InputView.requestRacingCarNames();

        //then
        String expected = "경주할 자동차 이름을 입력하세요.(이름은 쉼표(,) 기준으로 구분)" + System.lineSeparator();
        String actualAnnouncement = outputStream.toString();

        assertEquals(expected, actualAnnouncement);
    }


    private static Stream<Arguments> provideRacingCarNamesInput() {
        return Stream.of(
                Arguments.of("", "공백 문자"),
                Arguments.of("    ", "공백 문자열"),
                Arguments.of("123", "숫자 문자열"),
                Arguments.of("car", "문자열(단어)"),
                Arguments.of("pobi,woni", "문자열(구분자 포함)")
        );
    }
}
