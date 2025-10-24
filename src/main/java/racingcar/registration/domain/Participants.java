package racingcar.registration.domain;

import java.util.List;
import java.util.stream.Stream;

public class Participants {

    private static final String DELIMITER = ",";
    private final NameTokens nameTokens;

    private Participants(NameTokens nameTokens) {
        this.nameTokens = nameTokens;
    }

    public static Participants from(String carNamesInput) {
        requireNonBlank(carNamesInput);
        return new Participants(NameTokens.of(extract(carNamesInput)));
    }

    private static void requireNonBlank(String carNamesInput) {
        if(carNamesInput == null || carNamesInput.isBlank()) {
            throw new IllegalArgumentException(ErrorMessage.IS_BLANK.getMessage());
        }
    }

    private static List<String> extract(String carNamesInput) {
        return List.of(carNamesInput.split(DELIMITER));
    }

    public List<String> names() {
        return nameTokens.getUniqueCarNames().toList();
    }
}
