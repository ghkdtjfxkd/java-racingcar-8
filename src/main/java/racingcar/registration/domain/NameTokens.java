package racingcar.registration.domain;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

public class NameTokens {

    private final List<ParticipantName> participantNames;

    private NameTokens(List<ParticipantName> participantNames) {
        this.participantNames = participantNames;
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

    private static List<ParticipantName> uniqueCarNamesFrom(List<String> nameTokens) {
        return nameTokens.stream()
                .map(NameTokens::uniqueName)
                .toList();
    }

    private static ParticipantName uniqueName(String unique) {
        return ParticipantName.of(unique);
    }

    Stream<String> getUniqueCarNames() {
        return participantNames.stream()
                .map(ParticipantName::get);
    }
}
