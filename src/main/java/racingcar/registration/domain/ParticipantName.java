package racingcar.registration.domain;

public class ParticipantName {

    private static final short MAX_LENGTH = 5;

    private final String name;

    private ParticipantName(String nameToken) {
        this.name = nameToken;
    }

    static ParticipantName of(String nameToken) {
        requireNonEmpty(nameToken);
        requireNoWhitespace(nameToken);
        requireAcceptableMaxLength(nameToken);
        return new ParticipantName(nameToken);
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
