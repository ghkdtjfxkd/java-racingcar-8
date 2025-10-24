package racingcar.registration.domain;

enum ErrorMessage {

    IS_BLANK("빈 입력은 올 수 없습니다.(BLANK)"),
    SAME_NAMES_EXIST("중복되는 자동차 이름이 존재합니다."),

    // CarName
    BELOW_MIN_LENGTH("자동차 이름은 %d글자 이상이어야 합니다."),
    EXCEEDS_MAX_LENGTH("자동차 이름은 %d글자 이하여야 합니다."),
    WHITE_SPACES_EXIST("자동차 이름에 공백 문자가 포함되어 있습니다.");

    private static final String ERROR_PREFIX = "[ERROR]";
    private final String message;

    ErrorMessage(String message) {
        this.message = message;
    }

    String getMessage() {
        return String.format("%s: %s", ERROR_PREFIX, message);
    }

    String getMessageWith(int length) {
        return String.format(getMessage(), length);
    }
}
