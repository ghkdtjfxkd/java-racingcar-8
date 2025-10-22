package racingcar.entry.domain.register;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

public class NameTokens {

    private final List<CarName> carNames;

    private NameTokens(List<CarName> carNames) {
        this.carNames = carNames;
    }

    static NameTokens of(List<String> nameTokens) {
        requireUniqueNamesIn(nameTokens);
        return new NameTokens(uniqueCarNamesFrom(nameTokens));
    }

    private static void requireUniqueNamesIn(List<String> nameTokens) {
        if (hasSameCarNamesIn(nameTokens)) {
            throw new IllegalArgumentException(ErrorMessage.SAME_NAMES_EXIST.getMessage());
        }
    }

    private static boolean hasSameCarNamesIn(List<String> nameTokens) {
        return uniquesNamesIn(nameTokens).size() < nameTokens.size();
    }

    private static Set<String> uniquesNamesIn(List<String> nameTokens) {
        return new HashSet<>(nameTokens);
    }

    private static List<CarName> uniqueCarNamesFrom(List<String> nameTokens) {
        return nameTokens.stream()
                .map(NameTokens::uniqueName)
                .toList();
    }

    private static CarName uniqueName(String unique) {
        return CarName.of(unique);
    }

    Stream<String> getUniqueCarNames() {
        return carNames.stream()
                .map(CarName::get);
    }
}
