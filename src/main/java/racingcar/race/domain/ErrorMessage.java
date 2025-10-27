package racingcar.race.domain;

enum ErrorMessage {

    // Race
    IS_BLANK("빈 입력은 올 수 없습니다.(BLANK)"),
    IS_NOT_DIGIT("숫자만 입력해야 합니다."),

    // LapCount
    IS_OUT_OF_INTEGER("int 범위를 넘어가는 값은 입력할 수 없습니다."),
    IS_NOT_POSITIVE("양수(0보다 큰 값)를 입력해야 합니다.");

    private static final String ERROR_PREFIX = "[ERROR]";

    private final String message;

    ErrorMessage(String message) {
        this.message = message;
    }

    String getMessage() {
        return String.format("%s: %s", ERROR_PREFIX, message);
    }
}
