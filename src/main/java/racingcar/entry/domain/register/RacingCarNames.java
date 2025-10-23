package racingcar.entry.domain.register;

import java.util.Deque;
import java.util.List;
import java.util.stream.Stream;

public class RacingCarNames {

    private static final String DELIMITER = ",";
    private final NameTokens nameTokens;

    private RacingCarNames(NameTokens nameTokens) {
        this.nameTokens = nameTokens;
    }

    public static RacingCarNames from(String carNamesInput) {
        requireNonBlank(carNamesInput);
        return new RacingCarNames(NameTokens.of(extract(carNamesInput)));
    }

    private static void requireNonBlank(String carNamesInput) {
        if(carNamesInput == null || carNamesInput.isBlank()) {
            throw new IllegalArgumentException(ErrorMessage.IS_BLANK.getMessage());
        }
    }

    private static List<String> extract(String carNamesInput) {
        return List.of(carNamesInput.split(DELIMITER));
    }

    public Stream<String> provide() {
        return nameTokens.getUniqueCarNames();
    }
}
