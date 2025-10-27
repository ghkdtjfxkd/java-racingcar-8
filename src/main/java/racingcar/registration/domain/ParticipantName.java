package racingcar.registration.domain;

class ParticipantName {

    private static final short MAX_LENGTH = 5;
    private static final String WHITE_SPACE = " ";

    private final String name;

    private ParticipantName(String nameToken) {
        requireValid(nameToken);
        this.name = nameToken;
    }

    static ParticipantName of(String nameToken) {
        return new ParticipantName(nameToken);
    }

    private void requireValid(String nameToken) {
        requireNonEmpty(nameToken);
        requireNoWhitespace(nameToken);
        requireAcceptableMaxLength(nameToken);
    }

    private void requireNonEmpty(String nameToken) {
        if (nameToken == null || nameToken.isBlank()) {
            throw new IllegalArgumentException(ErrorMessage.NAME_IS_BLANK.getMessage());
        }
    }

    private void requireNoWhitespace(String nameToken) {
        if (nameToken.contains(WHITE_SPACE)) {
            throw new IllegalArgumentException(ErrorMessage.WHITE_SPACES_EXIST.getMessage());
        }
    }

    private void requireAcceptableMaxLength(String nameToken) {
        if (nameToken.length() > MAX_LENGTH) {
            throw new IllegalArgumentException(ErrorMessage.EXCEEDS_MAX_LENGTH.getMessageWith(MAX_LENGTH));
        }
    }

    String get() {
        return name;
    }
}
