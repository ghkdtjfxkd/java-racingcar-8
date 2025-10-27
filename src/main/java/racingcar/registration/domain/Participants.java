package racingcar.registration.domain;

import java.util.List;

public class Participants {

    private static final String DELIMITER = ",";

    private final NameTokens nameTokens;

    private Participants(String carNamesInput) {
        requireDelimiterIsNotTerminator(carNamesInput);
        this.nameTokens = NameTokens.of(extract(carNamesInput));
    }

    public static Participants from(String carNamesInput) {
        requireNonBlank(carNamesInput);
        return new Participants(carNamesInput);
    }

    private static void requireNonBlank(String carNamesInput) {
        if (carNamesInput == null || carNamesInput.isBlank()) {
            throw new IllegalArgumentException(ErrorMessage.IS_BLANK.getMessage());
        }
    }

    private void requireDelimiterIsNotTerminator(String carNamesInput) {
        if (carNamesInput.endsWith(DELIMITER)) {
            throw new IllegalArgumentException(ErrorMessage.ENDS_WITH_DELIMITER.getMessage());
        }
    }

    private List<String> extract(String carNamesInput) {
        return List.of(carNamesInput.split(DELIMITER));
    }

    public List<String> names() {
        return nameTokens.getUniqueCarNames().toList();
    }
}
