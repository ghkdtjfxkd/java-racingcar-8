package racingcar.entry.domain.register;

public class CarName {

    private static final short MAX_LENGTH = 5;

    private final String name;

    private CarName(String nameToken) {
        this.name = nameToken;
    }

    static CarName of(String nameToken) {
        requireNonEmpty(nameToken);
        requireNoWhitespace(nameToken);
        requireAcceptableMaxLength(nameToken);
        return new CarName(nameToken);
    }

    private static void requireNonEmpty(String name) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException(ErrorMessage.IS_BLANK.getMessage());
        }
    }

    private static void requireNoWhitespace(String nameToken) {
        if (nameToken.contains(" ")) {
            throw new IllegalArgumentException(ErrorMessage.WHITE_SPACES_EXIST.getMessage());
        }
    }

    private static void requireAcceptableMaxLength(String nameToken) {
        if (nameToken.length() > MAX_LENGTH) {
            throw new IllegalArgumentException(ErrorMessage.EXCEEDS_MAX_LENGTH.getMessageWith(MAX_LENGTH));
        }
    }

    String get(){
        return name;
    }
}
